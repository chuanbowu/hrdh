package com.flym.hrdh.mapper.single.partner;

import com.flym.hrdh.pojo.partner.PartnerAccountInfo;

public interface PartnerAccountInfoMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(PartnerAccountInfo record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(PartnerAccountInfo record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    PartnerAccountInfo selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(PartnerAccountInfo record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(PartnerAccountInfo record);
}