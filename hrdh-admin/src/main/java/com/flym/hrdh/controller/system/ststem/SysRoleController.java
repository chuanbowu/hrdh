package com.flym.hrdh.controller.system.ststem;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.api.service.system.ISysRoleMenuService;
import com.flym.hrdh.api.service.system.ISysRoleService;
import com.flym.hrdh.api.service.system.ISysUserRoleService;
import com.flym.hrdh.config.CommonConfig;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.system.SysRole;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:系统角色管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping("/system/sysRole")
public class SysRoleController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(SysRoleController.class);

    @Reference(version = "1.0.0")
    public IHrdhCacheService jyhCacheService;

    @Reference(version = "1.0.0")
    public ISysRoleService sysRoleService;

    @Reference(version = "1.0.0")
    public ISysRoleMenuService sysRoleMenuService;

    @Reference(version = "1.0.0")
    public ISysUserRoleService sysUserRoleService;

    //系统角色列表-111
    @ResponseBody
    @RequestMapping("/list")
    public ResponseMessage list(@RequestBody Map<String, String> map, HttpServletRequest request, HttpServletResponse response) {

        ResponseMessage respMsg = null;

        try {
            respMsg = super.checkLogin(jyhCacheService, map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(respMsg.result)) {
                return respMsg;
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

            //查询条件
            //角色备注
            String roleName = map.get("roleName");
            //角色备注
            String roleRemark = map.get("roleRemark");

            //开始条数
            int beginNum = pageSize * (pageNum - 1);
            List<SysRole> sysRoleList = sysRoleService.findSysRoleByLimit(roleName, roleRemark, beginNum, pageSize);

            //获取角色总数
            Long totalRow = sysRoleService.getValidSysRoleNum(roleName, roleRemark);

            //返回信息
            JSONObject jb = new JSONObject();
            JSONArray ja = new JSONArray();
            //遍历封装角色信息
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for (SysRole sysRole : sysRoleList) {

                JSONObject jobj = new JSONObject();
                //角色id
                jobj.put("sysRoleId", sysRole.getId());
                //角色名称
                jobj.put("roleName", sysRole.getRoleName());
                //角色备注
                jobj.put("roleRemark", sysRole.getRoleRemark());
                //创建时间
                jobj.put("createSysDate", sdf.format(sysRole.getCreateDate()));
                ja.add(jobj);
            }

            jb.put("sysRoleList", ja);
            jb.put("totalRow", totalRow);

            respMsg.setDatas(jb);
            respMsg.setResult("0");
            respMsg.setMessage(ResultCode.result_0);

        } catch (Exception e) {

            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(ResultCode.result_100000);
        }

        return respMsg;
    }

    //系统角色新增-112
    @ResponseBody
    @RequestMapping("/add")
    public ResponseMessage  add(@RequestBody Map<String, String> map, HttpServletRequest request, HttpServletResponse response) {

        ResponseMessage respMsg = null;

        try {
            respMsg = super.checkLogin(jyhCacheService, map);

            //如果result为空则直接返回
            Long sysUserId ;
            if (StringUtils.isNotBlank(respMsg.result)) {
                return respMsg;
            }else{
                sysUserId = respMsg.getDatas().getLong("userId");
                respMsg.setDatas(null);
            }

            //检测角色名称不能为空
            String roleName = map.get("roleName");
            if(StringUtils.isBlank(roleName)){
                respMsg.setResult("112011");
                respMsg.setMessage(ResultCode.result_112011);
                return respMsg;
            }

            //检测角色名是否存在
            int roleNameExist = sysRoleService.findRoleNameExist(roleName);
            if(roleNameExist > 0){
                respMsg.setResult("112021");
                respMsg.setMessage(ResultCode.result_112021);
                return respMsg;
            }

            //当前时间
            Date date = new Date();
            SysRole sysRole = new SysRole();
            //角色名称
            sysRole.setRoleName(roleName);
            //角色备注
            sysRole.setRoleRemark(map.get("roleRemark"));
            //状态：1-正常、2-删除
            sysRole.setStatus(1);
            //创建人
            sysRole.setCreateSysUser(sysUserId);
            //创建时间
            sysRole.setCreateDate(date);
            //修改人
            sysRole.setModifySysUser(sysUserId);
            //修改时间
            sysRole.setModifyDate(date);

            sysRoleService.save(sysRole);

            respMsg.setResult("1");
            respMsg.setMessage(ResultCode.result_9);

        } catch (Exception e) {

            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(ResultCode.result_100000);
        }

        return respMsg;
    }

    //根据角色ID获取角色信息-113
    @ResponseBody
    @RequestMapping("/info")
    public ResponseMessage info(@RequestBody Map<String, String> map, HttpServletRequest request, HttpServletResponse response) {

        ResponseMessage respMsg = null;

        try {
            respMsg = super.checkLogin(jyhCacheService, map);

            //如果result为空则直接返回
            Long sysUserId ;
            if (StringUtils.isNotBlank(respMsg.result)) {
                return respMsg;
            }else{
                sysUserId = respMsg.getDatas().getLong("userId");
                respMsg.setDatas(null);
            }

            //检测角色ID不能为空
            String sysRoleId = map.get("sysRoleId");
            if(StringUtils.isBlank(sysRoleId)){
                respMsg.setResult("113011");
                respMsg.setMessage(ResultCode.result_113011);
                return respMsg;
            }

            //根据角色id查询角色信息
            SysRole sysRole = sysRoleService.get(Long.parseLong(sysRoleId));
            //检测角色id是否存在
            if(sysRole == null ){
                respMsg.setResult("113021");
                respMsg.setMessage(ResultCode.result_113021);
                return respMsg;
            }

            //返回信息
            JSONObject jb = new JSONObject();

            //角色名称
            jb.put("roleName", sysRole.getRoleName());
            //角色备注
            jb.put("roleRemark", sysRole.getRoleRemark());

            respMsg.setDatas(jb);
            respMsg.setResult("0");
            respMsg.setMessage(ResultCode.result_0);

        } catch (Exception e) {

            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(ResultCode.result_100000);
        }

        return respMsg;
    }

    //修改角色信息-114
    @ResponseBody
    @RequestMapping("/edit")
    public ResponseMessage edit(@RequestBody Map<String, String> map, HttpServletRequest request, HttpServletResponse response) {

        ResponseMessage respMsg = null;

        try {
            respMsg = super.checkLogin(jyhCacheService, map);

            //如果result为空则直接返回
            Long sysUserId ;
            if (StringUtils.isNotBlank(respMsg.result)) {
                return respMsg;
            }else{
                sysUserId = respMsg.getDatas().getLong("userId");
                respMsg.setDatas(null);
            }

            //检测角色ID不能为空
            String sysRoleId = map.get("sysRoleId");
            if(StringUtils.isBlank(sysRoleId)){
                respMsg.setResult("114011");
                respMsg.setMessage(ResultCode.result_114011);
                return respMsg;
            }

            //根据角色id查询角色信息
            SysRole sysRole = sysRoleService.get(Long.parseLong(sysRoleId));
            //检测角色id是否存在
            if(sysRole == null ){
                respMsg.setResult("114021");
                respMsg.setMessage(ResultCode.result_114021);
                return respMsg;
            }

            //检测角色名称不能为空
            String roleName = map.get("roleName");
            if(StringUtils.isBlank(roleName)){
                respMsg.setResult("114031");
                respMsg.setMessage(ResultCode.result_114031);
                return respMsg;
            }

            //检测角色名是否存在
            if(!roleName.equals(sysRole.getRoleName())){
                int roleNameExist = sysRoleService.findRoleNameExist(roleName);
                if(roleNameExist > 0){
                    respMsg.setResult("114041");
                    respMsg.setMessage(ResultCode.result_114041);
                    return respMsg;
                }
            }

            //当前时间
            Date date = new Date();
            //角色名称
            sysRole.setRoleName(roleName);
            //角色备注
            sysRole.setRoleRemark(map.get("roleRemark"));
            //修改人
            sysRole.setModifySysUser(sysUserId);
            //修改时间
            sysRole.setModifyDate(date);

            sysRoleService.save(sysRole);

            respMsg.setResult("1");
            respMsg.setMessage(ResultCode.result_6);

        } catch (Exception e) {

            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(ResultCode.result_100000);
        }

        return respMsg;
    }

    //删除角色信息-115
    @ResponseBody
    @RequestMapping("/delete")
    public ResponseMessage delete(@RequestBody Map<String, String> map, HttpServletRequest request, HttpServletResponse response) {

        ResponseMessage respMsg = null;

        try {
            respMsg = super.checkLogin(jyhCacheService, map);

            //如果result为空则直接返回
            Long sysUserId ;
            if (StringUtils.isNotBlank(respMsg.result)) {
                return respMsg;
            }else{
                sysUserId = respMsg.getDatas().getLong("userId");
                respMsg.setDatas(null);
            }

            //检测角色ID不能为空
            String sysRoleIds = map.get("sysRoleIds");
            if(StringUtils.isBlank(sysRoleIds)){
                respMsg.setResult("115011");
                respMsg.setMessage(ResultCode.result_115011);
                return respMsg;
            }

            //组装id集合
            List <Long> idList = new ArrayList<Long>();
            String[] idArr = sysRoleIds.split(",");
            for (int i = 0; i < idArr.length; i++){
                if(idArr[i].equals("1")){
                    respMsg.setResult("115021");
                    respMsg.setMessage(ResultCode.result_115021);
                    return respMsg;
                }

                SysRole sysRole = sysRoleService.get(Long.parseLong(idArr[i]));
                if(sysRole == null || sysRole.getStatus()==2){
                    respMsg.setResult("115031");
                    respMsg.setMessage(ResultCode.result_115031);
                    return respMsg;
                }
                idList.add(Long.parseLong(idArr[i]));
            }

            //当前时间
            Date date = new Date();

            sysRoleService.updateStatusByIds(idList,2, sysUserId, date);

            for (int i = 0; i < idArr.length; i++){
                sysRoleMenuService.deleteBySysRoleId(Long.parseLong(idArr[i]));
                sysUserRoleService.deleteBySysRoleId(Long.parseLong(idArr[i]));
            }

            respMsg.setResult("1");
            respMsg.setMessage(ResultCode.result_8);

        } catch (Exception e) {

            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(ResultCode.result_100000);
        }

        return respMsg;
    }
}
