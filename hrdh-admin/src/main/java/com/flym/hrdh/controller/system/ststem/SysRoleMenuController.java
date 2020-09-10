package com.flym.hrdh.controller.system.ststem;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.api.model.system.SysMenuVm;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.api.service.system.ISysMenuService;
import com.flym.hrdh.api.service.system.ISysRoleMenuService;
import com.flym.hrdh.pojo.system.SysRoleMenu;
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
 * <p>Description:角色菜单关系信息</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@RestController
@RequestMapping("system/roleMenu")
public class SysRoleMenuController extends BaseController {

    @Reference(version = "1.0.0")
    IHrdhCacheService jyhCacheService;

    @Reference(version = "1.0.0")
    ISysRoleMenuService sysRoleMenuService;

    @Reference(version = "1.0.0")
    ISysMenuService sysMenuService;

    //角色分配菜单列表  -118
    @ResponseBody
    @RequestMapping("/roleMenuList")
    public ResponseMessage roleMenuList(@RequestBody Map<String, String> map){

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

            //检测角色ID不能为空
            String roleIdStr = map.get("roleId");
            if(StringUtils.isBlank(roleIdStr)){
                respMsg.setResult("118010");
                respMsg.setMessage(ResultCode.result_118010);
                return respMsg;
            }

            //格式化角色ID
            Long roleId = Long.valueOf(roleIdStr);

            //获取一级菜单列表
            List<SysMenuVm> sysMenuVmList = sysMenuService.findSysMenuListByRoleId(roleId,0L);

            JSONArray ja = new JSONArray();

            for(SysMenuVm m : sysMenuVmList){

                JSONObject job= new JSONObject();

                //ID
                job.put("id",m.getId());
                //菜单名称
                job.put("menuName",m.getMenuName());
                //类型：true-已选中、flase-未选中
                job.put("type",m.getType());
                //菜单LOGO
                job.put("menuLogo",m.getMenuLogo() == null ? "" : m.getMenuLogo());

                //获取二级菜单列表
                List<SysMenuVm> ListSysMenuVm = sysMenuService.findSysMenuListByRoleId(roleId,m.getId());

                JSONArray jaMenu = new JSONArray();
                for(SysMenuVm me : ListSysMenuVm){

                    JSONObject jobMenu = new JSONObject();

                    //ID
                    jobMenu.put("id",me.getId());
                    //菜单名称
                    jobMenu.put("menuName",me.getMenuName());
                    //类型：true-已选中、flase-未选中
                    jobMenu.put("type",me.getType());
                    //菜单LOGO
                    job.put("menuLogo",me.getMenuLogo() == null ? "" : me.getMenuLogo());

                    jaMenu.add(jobMenu);
                }

                job.put("menuTwoList",jaMenu);

                ja.add(job);
            }

            //封装返回信息
            JSONObject jb = new JSONObject();
            //一级菜单信息集合
            jb.put("menuList", ja);
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

    //授权菜单给角色 -119
    @ResponseBody
    @RequestMapping("/addRoleMenu")
    public ResponseMessage addRoleMenu(@RequestBody Map<String, String> map){

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

            //检测角色ID不能为空
            String roleIdStr = map.get("roleId");
            if(StringUtils.isBlank(roleIdStr)){
                respMsg.setResult("119010");
                respMsg.setMessage(ResultCode.result_119010);
                return respMsg;
            }

            //检测菜单ID不能为空
            String menuIdStr = map.get("menuId");
            if(StringUtils.isBlank(menuIdStr)){
                respMsg.setResult("119020");
                respMsg.setMessage(ResultCode.result_119020);
                return respMsg;
            }

            //格式化用户ID
            Long roleId = Long.valueOf(roleIdStr);

            //先把已有菜单删除
            sysRoleMenuService.deleteBySysRoleId(roleId);

            //封装集合
            String[] menuIdArr = menuIdStr.split(",");
            List<SysRoleMenu> beans = new ArrayList<>();

            for(String menu : menuIdArr){

                //格式化角色ID
                Long menuId = Long.parseLong(menu);
                SysRoleMenu bean = new SysRoleMenu();

                //角色ID
                bean.setRoleId(roleId);
                //菜单ID
                bean.setMenuId(menuId);

                beans.add(bean);
            }

            sysRoleMenuService.addRoleMenu(beans);

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
