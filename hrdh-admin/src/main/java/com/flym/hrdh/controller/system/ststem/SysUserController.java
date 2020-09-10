package com.flym.hrdh.controller.system.ststem;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.api.model.system.SysRoleMenuVm;
import com.flym.hrdh.api.model.system.SysUserRoleVm;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.api.service.system.ISysRoleMenuService;
import com.flym.hrdh.api.service.system.ISysUserRoleService;
import com.flym.hrdh.api.service.system.ISysUserService;
import com.flym.hrdh.config.CacheKeyConfig;
import com.flym.hrdh.config.CommonConfig;
import com.flym.hrdh.pojo.system.SysUser;
import com.flym.hrdh.utils.IDUtils;
import com.flym.hrdh.utils.IpAddressUtil;
import com.flym.hrdh.utils.MD5Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:系统用户信息</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {

    @Reference(version = "1.0.0")
    IHrdhCacheService jyhCacheService;

    @Reference(version = "1.0.0")
    ISysUserService sysUserService;

    @Reference(version = "1.0.0")
    ISysRoleMenuService sysRoleMenuService;

    @Reference(version = "1.0.0")
    ISysUserRoleService sysUserRoleService;

    /**
     * 登录接口  -101
     * @param map
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public ResponseMessage login(@RequestBody Map<String, String> map, HttpServletRequest request){

        ResponseMessage respMsg = null;
        try {

            respMsg = super.checkNotLogin(jyhCacheService,map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(respMsg.result)) {
                return respMsg;
            }

            //检测账户名不能为空
            String userName = map.get("username");
            if(StringUtils.isBlank(userName)){
                respMsg.setResult("101011");
                respMsg.setMessage(ResultCode.result_101011);
                return respMsg;
            }

            //检测密码不能为空
            String password = map.get("password");
            if(StringUtils.isBlank(password)){
                respMsg.setResult("101021");
                respMsg.setMessage(ResultCode.result_101021);
                return respMsg;
            }

            //获取信息
            SysUser sysUser = sysUserService.loginUser(userName, MD5Util.encoder(password));

            if(sysUser == null){
                respMsg.setResult("101031");
                respMsg.setMessage(ResultCode.result_101031);
                return respMsg;
            }

            //检测是否冻结：1、显示  2、冻结 3、删除
            if(sysUser.getStatus() == 2){
                respMsg.setResult("101041");
                respMsg.setMessage(ResultCode.result_101041);
                return respMsg;
            }else  if(sysUser.getStatus() == 3){
                respMsg.setResult("101051");
                respMsg.setMessage(ResultCode.result_101051);
                return respMsg;
            }

            //登陆时间
            Date date = new Date();
            sysUser.setLoginDate(date);
            //登陆IP
            sysUser.setLoginIp(IpAddressUtil.getIpAddr(request));

            sysUserService.save(sysUser);

            //生成新的登录会员令牌:登录会员令牌:有效期24小时
            String token = sysUser.getId().toString() + CacheKeyConfig.REDIS_SYS_USER_TOKEN + IDUtils.getRandomCode(10);
            token = MD5Util.encoder(token);
            jyhCacheService.setCache(token, sysUser.getId().toString(), CacheKeyConfig.REDIS_SYS_USER_LOGIN_TOKEN_VALID_TIME);

            //保存用户登陆令牌
            String userToken = sysUser.getId().toString() + CacheKeyConfig.REDIS_SYS_USER_LOGIN_TOKEN;
            //获取旧的令牌
            String oldToken = jyhCacheService.getCache(userToken);
            if(StringUtils.isNotBlank(oldToken)){
                jyhCacheService.delCache(oldToken);
            }
            jyhCacheService.setCache(userToken, token, CacheKeyConfig.REDIS_SYS_USER_LOGIN_TOKEN_VALID_TIME);

            JSONObject js = new JSONObject();
            //用户令牌
            js.put("token",token);

            respMsg.setDatas(js);
            respMsg.setResult("1");
            respMsg.setMessage(ResultCode.result_1);
        }catch (Exception e){
            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(ResultCode.result_100000);
        }
        return respMsg;
    }

    //平台用户菜单信息  -102
    @ResponseBody
    @RequestMapping("/sysInfo")
    public ResponseMessage sysInfo(@RequestBody Map<String, String> map){

        ResponseMessage respMsg = null;
        try {

            respMsg = super.checkLogin(jyhCacheService, map);

            //如果result为空则直接返回
            Long userId ;
            if (StringUtils.isNotBlank(respMsg.result)) {
                return respMsg;
            } else {
                userId = respMsg.getDatas().getLong("userId");
                respMsg.setDatas(null);
            }

            JSONObject jb = new JSONObject();
            JSONArray ja = new JSONArray();

            //获取用户详情信息
            SysUser sysUser = sysUserService.get(userId);

            JSONObject sysUserJson = new JSONObject();
            JSONArray roleJSON = new JSONArray();

            //账户名
            sysUserJson.put("userName",sysUser.getUserName());

            //获取角色
            List<SysUserRoleVm> sysUserRoleVmList = sysUserRoleService.findUserRole(userId);
            for(SysUserRoleVm sysUserRole : sysUserRoleVmList){
                JSONObject sysUserRoleJson = new JSONObject();
                //角色ID
                Long roleId = sysUserRole.getRoleId();
                sysUserRoleJson.put("roleId", roleId);
                //角色名称
                sysUserRoleJson.put("roleName", sysUserRole.getRoleName());

                roleJSON.add(sysUserRoleJson);
            }

            //角色
            sysUserJson.put("role",roleJSON);

            //获取一级菜单
            List<SysRoleMenuVm> sysRoleMenu = sysRoleMenuService.findTopRoleMenu(userId);

            //获取二级菜单
            for(SysRoleMenuVm oneMenu : sysRoleMenu){

                JSONObject oneObj = new JSONObject();

                // 菜单ID
                oneObj.put("menuId", oneMenu.getMenuId());

                // 菜单名称
                oneObj.put("menuName", oneMenu.getMenuName());

                // 菜单LOGO
                oneObj.put("menuLogo", oneMenu.getMenuLogo());

                // 链接地址
                oneObj.put("menuUrl", oneMenu.getMenuUrl() == null ? "" : oneMenu.getMenuUrl());

                // 菜单等级
                oneObj.put("menuLevel", oneMenu.getMenuLevel());

                // 排序号
                oneObj.put("sorNum", oneMenu.getSorNum());

                JSONArray towJa = new JSONArray();

                //获取二级菜单
                List<SysRoleMenuVm> towRoleMenu = sysRoleMenuService.findTowRoleMenu(oneMenu.getMenuId() ,userId);

                for( SysRoleMenuVm towMenu : towRoleMenu){

                    JSONObject towObj = new JSONObject();

                    // 菜单ID
                    towObj.put("menuId",towMenu.getMenuId());

                    // 菜单名称
                    towObj.put("menuName",towMenu.getMenuName());

                    // 链接地址
                    towObj.put("menuUrl",towMenu.getMenuUrl() == null ? "" : towMenu.getMenuUrl());

                    // 菜单等级
                    towObj.put("menuLevel",towMenu.getMenuLevel());

                    // 排序号
                    towObj.put("sorNum",towMenu.getSorNum());

                    towJa.add(towObj);
                }

                oneObj.put("twoMenuList",towJa);

                ja.add(oneObj);
            }


            //封装返回信息
            jb.put("userInfo",sysUserJson);
            //菜单信息
            jb.put("oneMenuList", ja);
            respMsg.setDatas(jb);
            respMsg.setResult("0");
            respMsg.setMessage(ResultCode.result_0);
        }catch (Exception e){
            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(ResultCode.result_100000);
        }
        return respMsg;
    }

    //用户列表 -103
    @ResponseBody
    @RequestMapping("/list")
    public ResponseMessage list(@RequestBody Map<String, String> map){

        ResponseMessage respMsg = null;

        try {

            respMsg = super.checkLogin(jyhCacheService, map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(respMsg.result)) {
                return respMsg;
            } else {
                respMsg.setDatas(null);
            }

            //账户名称
            String trueName = map.get("trueName");
            if (StringUtils.isBlank(trueName)) {
                trueName = null;
            }

            //用户账户
            String userName = map.get("userName");
            if (StringUtils.isBlank(userName)) {
                userName = null;
            }

            //状态
            String statusStr = map.get("status");
            int status = 0;
            if (StringUtils.isNotBlank(statusStr)) {
                status = Integer.parseInt(statusStr);
            }

            //当前页码
            int pageNum = 1;
            String pageNumStr = map.get("pageNum");
            if (StringUtils.isNotBlank(pageNumStr)) {
                pageNum = Integer.parseInt(pageNumStr);
            }

            //每页显示数
            int pageSize = CommonConfig.ADMIN_LIST_DEFAULT_PAGE_SIZE;
            String pageSizeStr = map.get("pageSize");
            if (StringUtils.isNotBlank(pageSizeStr)) {
                pageSize = Integer.parseInt(pageSizeStr);
            }

            //开始条数
            int beginNum = pageSize * (pageNum - 1);

            List<SysUser> sysUserList = sysUserService.findSysUser(beginNum,pageSize,userName,trueName ,status);

            JSONObject jb = new JSONObject();
            JSONArray ja = new JSONArray();

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            //用户列表
            for(SysUser sysUser : sysUserList){

                JSONObject sysUserJson = new JSONObject();

                // id
                sysUserJson.put("id",sysUser.getId());

                // 账户名
                sysUserJson.put("userName",sysUser.getUserName());

                // 用户姓名
                sysUserJson.put("trueName",sysUser.getTrueName());

                // 登录IP
                sysUserJson.put("loginIp",sysUser.getLoginIp());

                // 登录时间

                sysUserJson.put("loginDate",sysUser.getLoginDate() == null ? "" : sf.format(sysUser.getLoginDate()));

                //状态：1-正常、2-冻结、3-删除
                sysUserJson.put("status",sysUser.getStatus());

                ja.add(sysUserJson);
            }

            int totalRow = sysUserService.getSysUserTotalRow(userName,trueName ,status);
            //封装用户列表
            jb.put("sysUser",ja);
            //总数
            jb.put("totalRow", totalRow);
            //当前页数
            jb.put("totalPage", totalRow/pageSize);

            respMsg.setDatas(jb);
            respMsg.setResult("0");
            respMsg.setMessage(ResultCode.result_0);
        }catch (Exception e){
            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(ResultCode.result_100000);
        }
        return respMsg;
    }

    //新增平台用户 -104
    @ResponseBody
    @RequestMapping("/add")
    public ResponseMessage add(@RequestBody Map<String, String> map){

        ResponseMessage respMsg = null;

        try {

            respMsg = super.checkLogin(jyhCacheService, map);

            //如果result为空则直接返回
            Long userId ;
            if (StringUtils.isNotBlank(respMsg.result)) {
                return respMsg;
            } else {
                userId = respMsg.getDatas().getLong("userId");
                respMsg.setDatas(null);
            }

            //账户名
            String userName = map.get("userName");
            if(StringUtils.isBlank(userName)){
                respMsg.setResult("104011");
                respMsg.setMessage(ResultCode.result_104011);
                return respMsg;
            }

            //登录密码
            String password = map.get("password");
            if(StringUtils.isBlank(password)){
                respMsg.setResult("104021");
                respMsg.setMessage(ResultCode.result_104021);
                return respMsg;
            }

            //用户姓名
            String trueName = map.get("trueName");
            if(StringUtils.isBlank(trueName)){
                respMsg.setResult("104031");
                respMsg.setMessage(ResultCode.result_104031);
                return respMsg;
            }

            //检测用户名是否存在
            int sysUserNameNum = sysUserService.findSysUserNameNum(userName, null);

            if(sysUserNameNum > 0 ){
                respMsg.setResult("104041");
                respMsg.setMessage(ResultCode.result_104041);
                return respMsg;
            }

            SysUser sysUser = new SysUser();

            // 账户名
            sysUser.setUserName(userName);

            // 登录密码
            sysUser.setPassword(MD5Util.encoder(password));

            // 用户姓名
            sysUser.setTrueName(trueName);

            // 状态：1-正常、2-冻结、3-删除
            sysUser.setStatus(1);

            // 创建人
            sysUser.setCreateSysUser(userId);

            Date date = new Date();

            // 创建时间
            sysUser.setCreateDate(date);

            // 修改人
            sysUser.setModifySysUser(userId);

            // 修改时间
            sysUser.setModifyDate(date);

            //创建用户
            sysUserService.save(sysUser);

            respMsg.setResult("1");
            respMsg.setMessage(ResultCode.result_9);
        }catch (Exception e){
            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(ResultCode.result_100000);
        }
        return respMsg;
    }

    //平台用户详情 -105
    @ResponseBody
    @RequestMapping("/userInfo")
    public ResponseMessage userInfo(@RequestBody Map<String, String> map){

        ResponseMessage respMsg = null;

        try {

            respMsg = super.checkLogin(jyhCacheService, map);

            //如果result为空则直接返回
            Long userId;
            if (StringUtils.isNotBlank(respMsg.result)) {
                return respMsg;
            } else {
                userId = respMsg.getDatas().getLong("userId");
                respMsg.setDatas(null);
            }

            // 账户ID
            String idStr = map.get("id");
            if(StringUtils.isBlank(idStr)){
                respMsg.setResult("105010");
                respMsg.setMessage(ResultCode.result_105010);
                return respMsg;
            }

            //格式化ID
            Long id = Long.parseLong(idStr);

            //获取平台用户信息
            SysUser sysUser = sysUserService.get(id);

            JSONObject jb = new JSONObject();

            // id
            jb.put("id",sysUser.getId());

            // 账户名
            jb.put("userName",sysUser.getUserName());

            // 用户姓名
            jb.put("trueName",sysUser.getTrueName());

            // 登录IP
            jb.put("loginIp",sysUser.getLoginIp());

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // 登录时间
            jb.put("loginDate",sf.format(sysUser.getLoginDate()));

            respMsg.setDatas(jb);
            respMsg.setResult("0");
            respMsg.setMessage(ResultCode.result_0);
        }catch (Exception e){
            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(ResultCode.result_100000);
        }
        return respMsg;
    }

    //修改用户资料 -106
    @ResponseBody
    @RequestMapping("/userEdit")
    public ResponseMessage userEdit(@RequestBody Map<String, String> map){

        ResponseMessage respMsg = null;

        try {

            respMsg = super.checkLogin(jyhCacheService, map);

            //如果result为空则直接返回
            Long userId ;
            if (StringUtils.isNotBlank(respMsg.result)) {
                return respMsg;
            } else {
                userId = respMsg.getDatas().getLong("userId");
                respMsg.setDatas(null);
            }

            // 账户ID
            String idStr = map.get("id");
            if(StringUtils.isBlank(idStr)){
                respMsg.setResult("106010");
                respMsg.setMessage(ResultCode.result_106010);
                return respMsg;
            }

            // 用户姓名
            String trueName = map.get("trueName");
            if(StringUtils.isBlank(trueName)){
                respMsg.setResult("106020");
                respMsg.setMessage(ResultCode.result_106020);
                return respMsg;
            }

            //格式化ID
            Long id = Long.parseLong(idStr);

            //获取平台用户信息
            SysUser sysUser = sysUserService.get(id);

            //用户姓名
            sysUser.setTrueName(trueName);
            //修改人
            sysUser.setModifySysUser(userId);

            Date date = new Date();
            //修改时间
            sysUser.setModifyDate(date);

            sysUserService.save(sysUser);

            respMsg.setResult("1");
            respMsg.setMessage(ResultCode.result_6);
        }catch (Exception e){
            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(ResultCode.result_100000);
        }
        return respMsg;
    }

    //修改用户状态：1-正常、2-冻结、3-删除  -107
    @ResponseBody
    @RequestMapping("/editStatus")
    public ResponseMessage editStatus(@RequestBody Map<String, String> map){

        ResponseMessage respMsg = null;

        try {

            respMsg = super.checkLogin(jyhCacheService, map);

            //如果result为空则直接返回
            Long userId ;
            if (StringUtils.isNotBlank(respMsg.result)) {
                return respMsg;
            } else {
                userId = respMsg.getDatas().getLong("userId");
                respMsg.setDatas(null);
            }

            //检测操作类型不能为空
            String typeStr= map.get("type");
            if(StringUtils.isBlank(typeStr)){
                respMsg.setResult("107010");
                respMsg.setMessage(ResultCode.result_107010);
                return respMsg;
            }
            int type = Integer.parseInt(typeStr);

            //检测旧账号不能为空
            String userIds = map.get("userIds");
            if(StringUtils.isBlank(userIds)){
                respMsg.setResult("107021");
                respMsg.setMessage(ResultCode.result_107021);
                return respMsg;
            }

            //组装id集合
            List<Long> idList = new ArrayList<Long>();
            String[] idArr = userIds.split(",");
            for (int i = 0; i < idArr.length; i++){

                if(idArr[i].equals("1") && type != 1){
                    respMsg.setResult("117031");
                    respMsg.setMessage(ResultCode.result_107031);
                    return respMsg;
                }

                SysUser sysUser = sysUserService.get(Long.parseLong(idArr[i]));
                if(sysUser == null || sysUser.getStatus()==3){
                    respMsg.setResult("107041");
                    respMsg.setMessage(ResultCode.result_107041);
                    return respMsg;
                }

                //删除用户令牌信息
                if(type != 1){
                    //用户登陆令牌
                    String userToken = sysUser.getId().toString() + CacheKeyConfig.REDIS_SYS_USER_LOGIN_TOKEN;
                    //获取用户令牌
                    String oldToken = jyhCacheService.getCache(userToken);
                    if(StringUtils.isNotBlank(oldToken)){
                        jyhCacheService.delCache(oldToken);
                    }
                    jyhCacheService.delCache(userToken);
                }

                idList.add(Long.parseLong(idArr[i]));
            }

            //当前时间
            Date date = new Date();

            sysUserService.updateStatusByIds(idList, type , userId, date);

            respMsg.setResult("1");
            respMsg.setMessage(ResultCode.result_10);
        }catch (Exception e){
            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(ResultCode.result_100000);
        }
        return respMsg;
    }

    //修改密码接口  -108
    @ResponseBody
    @RequestMapping("/edit")
    public ResponseMessage edit(@RequestBody Map<String, String> map){

        ResponseMessage respMsg = null;

        try {

            respMsg = super.checkLogin(jyhCacheService, map);

            //如果result为空则直接返回
            Long userId ;
            if (StringUtils.isNotBlank(respMsg.result)) {
                return respMsg;
            } else {
                userId = respMsg.getDatas().getLong("userId");
                respMsg.setDatas(null);
            }

            //检测旧密码不能为空
            String oldPassword = map.get("oldPassword");
            if(StringUtils.isBlank(oldPassword)){
                respMsg.setResult("108011");
                respMsg.setMessage(ResultCode.result_108011);
                return respMsg;
            }

            //检测新密码不能为空
            String password = map.get("newPassword");
            if(StringUtils.isBlank(password)){
                respMsg.setResult("108021");
                respMsg.setMessage(ResultCode.result_108021);
                return respMsg;
            }

            //获取系统用户信息
            SysUser sysUser = sysUserService.get(userId);

            //判断是否旧密码是否正确
            if(sysUser.getPassword().equals(MD5Util.encoder(oldPassword))){

                //修改密码
                sysUser.setPassword(MD5Util.encoder(password));
                Date date = new Date();

                //修改时间
                sysUser.setModifyDate(date);
                //修改人
                sysUser.setModifySysUser(sysUser.getId());

                sysUserService.save(sysUser);

            }else{

                respMsg.setResult("108031");
                respMsg.setMessage(ResultCode.result_108031);
                return respMsg;
            }

            respMsg.setResult("1");
            respMsg.setMessage(ResultCode.result_6);
        }catch (Exception e){
            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(ResultCode.result_100000);
        }
        return respMsg;
    }

    //重置密码接口  -109
    @ResponseBody
    @RequestMapping("/resetPassword")
    public ResponseMessage resetPassword(@RequestBody Map<String, String> map){

        ResponseMessage respMsg = null;

        try {

            respMsg = super.checkLogin(jyhCacheService, map);

            //如果result为空则直接返回
            Long userId ;
            if (StringUtils.isNotBlank(respMsg.result)) {
                return respMsg;
            } else {
                userId = respMsg.getDatas().getLong("userId");
                respMsg.setDatas(null);
            }

            //检测旧账号不能为空
            String userIds = map.get("userIds");
            if(StringUtils.isBlank(userIds)){
                respMsg.setResult("109011");
                respMsg.setMessage(ResultCode.result_109011);
                return respMsg;
            }

            //组装id集合
            List<Long> idList = new ArrayList<Long>();
            String[] idArr = userIds.split(",");
            for (int i = 0; i < idArr.length; i++){
                SysUser sysUser = sysUserService.get(Long.parseLong(idArr[i]));
                if(sysUser == null || sysUser.getStatus()==2){
                    respMsg.setResult("109021");
                    respMsg.setMessage(ResultCode.result_109021);
                    return respMsg;
                }
                idList.add(Long.parseLong(idArr[i]));
            }

            //设置默认密码为：123456
            String defaultPassword = "123456";
            //当前时间
            Date date = new Date();

            sysUserService.updatePasswordByIds(idList,MD5Util.encoder(defaultPassword), userId, date);

            respMsg.setResult("1");
            respMsg.setMessage(ResultCode.result_16 + ":" + defaultPassword);
        }catch (Exception e){
            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(ResultCode.result_100000);
        }
        return respMsg;
    }

    //用户退出  -110
    @ResponseBody
    @RequestMapping("/loginOut")
    public ResponseMessage loginOut(@RequestBody Map<String, String> map){

        ResponseMessage respMsg = null;

        try {

            respMsg = super.checkNotLogin(jyhCacheService, map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(respMsg.result)) {
                return respMsg;
            }

            //删除用户令牌信息
            String token = map.get("token");
            if(StringUtils.isNotBlank(token)){

                //获取用户ID
                String userId = jyhCacheService.getCache(token);

                //获取用户登陆令牌
                String userToken = userId + CacheKeyConfig.REDIS_SYS_USER_LOGIN_TOKEN;
                //获取旧的令牌
                String oldToken = jyhCacheService.getCache(userToken);
                if(StringUtils.isNotBlank(oldToken)){
                    jyhCacheService.delCache(oldToken);
                    jyhCacheService.delCache(userToken);
                }

            }

            respMsg.setResult("0");
            respMsg.setMessage(ResultCode.result_2);

        }catch (Exception e){
            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(ResultCode.result_100000);
        }
        return respMsg;
    }
}
