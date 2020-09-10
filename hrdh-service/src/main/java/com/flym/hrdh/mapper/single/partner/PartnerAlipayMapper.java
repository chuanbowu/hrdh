package com.flym.hrdh.mapper.single.partner;

import com.flym.hrdh.pojo.partner.PartnerAlipay;

public interface PartnerAlipayMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(PartnerAlipay record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(PartnerAlipay record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    PartnerAlipay selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(PartnerAlipay record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(PartnerAlipay record);
}