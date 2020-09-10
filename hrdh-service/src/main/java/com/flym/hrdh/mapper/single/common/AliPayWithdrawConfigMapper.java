package com.flym.hrdh.mapper.single.common;

import com.flym.hrdh.pojo.common.AliPayWithdrawConfig;

public interface AliPayWithdrawConfigMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(AliPayWithdrawConfig record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(AliPayWithdrawConfig record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    AliPayWithdrawConfig selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(AliPayWithdrawConfig record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeyWithBLOBs(AliPayWithdrawConfig record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(AliPayWithdrawConfig record);

    /**
     * 获取自动提现配置
     * @return
     */
    AliPayWithdrawConfig getNormal();
}