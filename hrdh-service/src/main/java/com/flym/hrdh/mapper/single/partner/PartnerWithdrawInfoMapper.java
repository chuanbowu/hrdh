package com.flym.hrdh.mapper.single.partner;

import com.flym.hrdh.pojo.partner.PartnerWithdrawInfo;

public interface PartnerWithdrawInfoMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(PartnerWithdrawInfo record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(PartnerWithdrawInfo record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    PartnerWithdrawInfo selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(PartnerWithdrawInfo record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(PartnerWithdrawInfo record);
}