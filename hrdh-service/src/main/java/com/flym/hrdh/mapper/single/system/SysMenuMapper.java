package com.flym.hrdh.mapper.single.system;

import com.flym.hrdh.api.model.system.SysMenuVm;
import com.flym.hrdh.pojo.system.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(SysMenu record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(SysMenu record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    SysMenu selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(SysMenu record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(SysMenu record);

    /**
     * 根据角色ID和父级ID获取列表
     * @param roleId
     * @return
     */
    List<SysMenuVm> findSysMenuListByRoleId(@Param("roleId") Long roleId, @Param("parentId") Long parentId);

}
