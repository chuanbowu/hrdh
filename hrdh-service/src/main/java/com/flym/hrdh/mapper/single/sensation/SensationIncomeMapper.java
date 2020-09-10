package com.flym.hrdh.mapper.single.sensation;

import com.flym.hrdh.api.model.sensation.SensationIncomeVm;
import com.flym.hrdh.pojo.sensation.SensationIncome;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SensationIncomeMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(SensationIncome record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(SensationIncome record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    SensationIncome selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(SensationIncome record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(SensationIncome record);

    /**
     * 根据红人获取收入明细总条数
     * @param sensationId
     * @return
     */
    int getSensationIncomeVmNumBySensationId(@Param("sensationId") Long sensationId);

    /**
     * 根据红人获取收入明细列表
     * @param sensationId
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<SensationIncomeVm> findSensationIncomeVmListBySensationId(@Param("sensationId") Long sensationId, @Param("beginNum") Integer beginNum,
                                                                   @Param("pageSize") Integer pageSize);

    /**
     * 获取收入明细总条数
     * @param sensationId
     * @param type
     * @return
     */
    int getSensationIncomeVmNum(@Param("sensationId") Long sensationId, @Param("type") Integer type);

    /**
     * 获取收入明细列表
     * @param sensationId
     * @param type
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<SensationIncomeVm> findSensationIncomeVmList(@Param("sensationId") Long sensationId, @Param("type") Integer type,
                                                      @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize);

    /**
     * 获取最后一条数据
     * @return
     */
    SensationIncome getLastHandleDate();

    /**
     * 根据红人ID商品ID淘宝订单ID获取信息
     * @param sensationId
     * @param goodsId
     * @param tradeParentId
     * @return
     */
    SensationIncome getBySensationIdOrGoodsIdOrTradeParentId(@Param("sensationId") Long sensationId, @Param("goodsId") Long goodsId, @Param("tradeParentId") String tradeParentId);

    /**
     * 更新最后一条数据
     * @param date
     * @return
     */
    int updateLastHandleDate(@Param("date") Date date);
}