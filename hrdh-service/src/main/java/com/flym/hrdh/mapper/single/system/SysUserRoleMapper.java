package com.flym.hrdh.mapper.single.system;

import com.flym.hrdh.api.model.system.SysUserRoleVm;
import com.flym.hrdh.pojo.system.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserRoleMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(SysUserRole record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(SysUserRole record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    SysUserRole selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(SysUserRole record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(SysUserRole record);

    /**
     * 已ID获取用户角色信息
     * @param sysUserId
     * @return
     */
    List<SysUserRoleVm> findUserRole(@Param("sysUserId") Long sysUserId);

    /**
     * 根据角色id删除角色菜单关系
     * @param sysRoleId 角色id
     * @return
     */
    void deleteBySysRoleId(@Param("sysRoleId") Long sysRoleId);

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
