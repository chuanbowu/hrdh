package com.flym.hrdh.mapper.single.sensation;

import com.flym.hrdh.pojo.sensation.SensationAlipay;

public interface SensationAlipayMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(SensationAlipay record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(SensationAlipay record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    SensationAlipay selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(SensationAlipay record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(SensationAlipay record);
}