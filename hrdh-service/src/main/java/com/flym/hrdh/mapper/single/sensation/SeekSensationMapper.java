package com.flym.hrdh.mapper.single.sensation;

import com.flym.hrdh.pojo.sensation.SeekSensation;

public interface SeekSensationMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(SeekSensation record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(SeekSensation record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    SeekSensation selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(SeekSensation record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(SeekSensation record);
}