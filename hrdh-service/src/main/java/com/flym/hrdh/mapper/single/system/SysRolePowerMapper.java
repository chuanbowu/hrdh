package com.flym.hrdh.mapper.single.system;

import com.flym.hrdh.pojo.system.SysRolePower;

public interface SysRolePowerMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(SysRolePower record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(SysRolePower record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    SysRolePower selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(SysRolePower record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(SysRolePower record);
}
