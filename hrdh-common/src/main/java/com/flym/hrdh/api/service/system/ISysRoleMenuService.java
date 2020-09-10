package com.flym.hrdh.api.service.system;

import com.flym.hrdh.api.model.system.SysRoleMenuVm;
import com.flym.hrdh.pojo.system.SysRoleMenu;

import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:角色菜单关系管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface ISysRoleMenuService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    SysRoleMenu get(Long id);

    /**
     * 保存或编辑
     * @param sysRoleMenu
     * @return
     */
    SysRoleMenu save(SysRoleMenu sysRoleMenu);

    /**
     * 以平台用户ID获取一级菜单
     * @param userId
     * @return
     */
    List<SysRoleMenuVm> findTopRoleMenu(Long userId);

    /**
     * 以父级ID获取下级级菜单
     * @param parentId
     * @param userId
     * @return
     */
    List<SysRoleMenuVm> findTowRoleMenu(Long parentId, Long userId);

    /**
     * 根据角色id删除角色菜单关系
     * @param sysRoleId 角色id
     * @return
     */
    void deleteBySysRoleId(Long sysRoleId);

    /**
     * 批量保存角色菜单关系
     * @param beans
     */
    void addRoleMenu(List<SysRoleMenu> beans);
}
