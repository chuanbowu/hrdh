package com.flym.hrdh.mapper.single.partner;

import com.flym.hrdh.pojo.partner.Partner;

public interface PartnerMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(Partner record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(Partner record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    Partner selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(Partner record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(Partner record);
}