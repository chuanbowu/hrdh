package com.flym.hrdh.mapper.single.system;

import com.flym.hrdh.pojo.system.SysPower;

public interface SysPowerMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(SysPower record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(SysPower record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    SysPower selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(SysPower record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(SysPower record);
}
