package com.flym.hrdh.mapper.single.system;

import com.flym.hrdh.api.model.system.SysRoleMenuVm;
import com.flym.hrdh.pojo.system.SysRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMenuMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(SysRoleMenu record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(SysRoleMenu record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    SysRoleMenu selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(SysRoleMenu record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(SysRoleMenu record);

    /**
     * 以平台用户ID获取一级菜单
     * @param userId
     * @return
     */
    List<SysRoleMenuVm> findTopRoleMenu(@Param("userId") Long userId);

    /**
     * 以父级ID获取下级级菜单
     * @param parentId
     * @param userId
     * @return
     */
    List<SysRoleMenuVm> findTowRoleMenu(@Param("parentId") Long parentId, @Param("userId") Long userId);


    /**
     * 根据角色id删除角色菜单关系
     * @param sysRoleId 角色id
     * @return
     */
    void deleteBySysRoleId(@Param("sysRoleId") Long sysRoleId);

    /**
     * 批量保存角色菜单关系
     * @param beans
     */
    void addRoleMenu(List<SysRoleMenu> beans);
}
