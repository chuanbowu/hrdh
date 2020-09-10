package com.flym.hrdh.controller.system.ststem;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.api.model.system.SysRoleVm;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.api.service.system.ISysRoleService;
import com.flym.hrdh.api.service.system.ISysUserRoleService;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.system.SysUserRole;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:用户角色关系信息</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@RestController
@RequestMapping("/system/userRole")
public class SysUserRoleController extends BaseController {

    @Reference(version = "1.0.0")
    IHrdhCacheService jyhCacheService;

    @Reference(version = "1.0.0")
    ISysRoleService sysRoleService;

    @Reference(version = "1.0.0")
    ISysUserRoleService sysUserRoleService;

    //用户分配角色列表  -116
    @ResponseBody
    @RequestMapping("/list")
    public ResponseMessage list(@RequestBody Map<String, String> map){

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

            //检测用户ID不能为空
            String userIdStr = map.get("userId");
            if(StringUtils.isBlank(userIdStr)){
                respMsg.setResult("116010");
                respMsg.setMessage(ResultCode.result_116010);
                return respMsg;
            }

            //格式化用户ID
            Long userIds = Long.valueOf(userIdStr);

            //获取用户角色列表
            List<SysRoleVm> sysRoleList = sysRoleService.findSysUserRoleListByUserId(userIds);

            JSONArray ja = new JSONArray();

            for(SysRoleVm r : sysRoleList){

                JSONObject job= new JSONObject();

                //ID
                job.put("id",r.getId());
                //角色名称
                job.put("roleName",r.getRoleName());
                //类型：true-已选中、flase-未选中
                job.put("type",r.getType());

                ja.add(job);
            }

            //封装返回信息
            JSONObject jb = new JSONObject();
            //信息集合
            jb.put("list", ja);
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

    //授权角色给用户  -117
    @ResponseBody
    @RequestMapping("/addUserRole")
    public ResponseMessage addUserRole(@RequestBody Map<String, String> map){

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

            //检测用户ID不能为空
            String userIdStr = map.get("userId");
            if(StringUtils.isBlank(userIdStr)){
                respMsg.setResult("117010");
                respMsg.setMessage(ResultCode.result_117010);
                return respMsg;
            }

            //检测角色ID不能为空
            String roleIdStr = map.get("roleId");
            if(StringUtils.isBlank(roleIdStr)){
                respMsg.setResult("117020");
                respMsg.setMessage(ResultCode.result_117020);
                return respMsg;
            }

            //格式化用户ID
            Long userIds = Long.valueOf(userIdStr);

            //先把已有角色删除
            sysUserRoleService.deleteByUserIds(userIds);

            //封装集合
            String[] roleIdArr = roleIdStr.split(",");
            List<SysUserRole> beans = new ArrayList<>();

            for(String role : roleIdArr){

                //格式化角色ID
                Long roleId = Long.parseLong(role);
                SysUserRole bean = new SysUserRole();

                //用户ID
                bean.setSysUserId(userIds);
                //角色ID
                bean.setRoleId(roleId);

                beans.add(bean);
            }

            sysUserRoleService.addUserRole(beans);

            respMsg.setResult("1");
            respMsg.setMessage(ResultCode.result_13);
        }catch (Exception e){
            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(ResultCode.result_100000);
        }
        return respMsg;
    }
}
