package com.flym.hrdh.mapper.single.partner;

import com.flym.hrdh.pojo.partner.PartnerIncome;

public interface PartnerIncomeMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(PartnerIncome record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(PartnerIncome record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    PartnerIncome selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(PartnerIncome record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(PartnerIncome record);
}