package com.flym.hrdh.api.service.system;

import com.flym.hrdh.api.model.system.SysUserRoleVm;
import com.flym.hrdh.pojo.system.SysUserRole;

import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:用户关系管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface ISysUserRoleService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    SysUserRole get(Long id);

    /**
     * 保存或编辑
     * @param sysUserRole
     * @return
     */
    SysUserRole save(SysUserRole sysUserRole);

    /**
     * 已ID获取用户角色信息
     * @param sysUserId
     * @return
     */
    List<SysUserRoleVm> findUserRole(Long sysUserId);

    /**
     * 根据角色id删除用户角色关系
     * @param sysRoleId 角色id
     * @return
     */
    void deleteBySysRoleId(Long sysRoleId);


    /**
     * 批量保存用户角色关系
     * @param beans
     */
    void addUserRole(List<SysUserRole> beans);

    /**
     * 根据用户ID删除信息
     * @param userId
     * @return
     */
    int deleteByUserIds(Long userId);
}
