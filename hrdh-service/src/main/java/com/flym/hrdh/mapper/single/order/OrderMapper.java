package com.flym.hrdh.mapper.single.order;

import com.flym.hrdh.api.model.order.OrderVm;
import com.flym.hrdh.pojo.order.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(Order record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(Order record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    Order selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(Order record);

    /**
     * 根据红人ID获好取样总条数
     * @param sensationId
     * @return
     */
    int getOrderVmNumBySensationId(@Param("sensationId") Long sensationId);

    /**
     * 根据红人ID获好取样列表
     * @param sensationId
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<OrderVm> findOrderVmListBySensationId(@Param("sensationId") Long sensationId, @Param("beginNum") Integer beginNum,
                                               @Param("pageSize") Integer pageSize);

    /**
     * 获取取样详情
     * @param orderId
     * @return
     */
    OrderVm  getOrderInfo(@Param("orderId") Long orderId);

    /**
     * 根据红人ID和商品ID获取条数
     * @param sensationId
     * @param goodsId
     * @return
     */
    int getOrderNumSensationIdOrGoodsId(@Param("sensationId") Long sensationId, @Param("goodsId") Long goodsId);

    /**
     * 根据商家ID获取总条数
     * @param businessId
     * @param status
     * @param businessTitle
     * @return
     */
    int getOrderVmNumByBusinessId(@Param("businessId") Long businessId, @Param("status") Integer status,
                                  @Param("businessTitle") String businessTitle);

    /**
     * 根据商家ID获取列表
     * @param businessId
     * @param status
     * @param businessTitle
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<OrderVm> findOrderVmListByBusinessId(@Param("businessId") Long businessId, @Param("status") Integer status,
                                              @Param("businessTitle") String businessTitle, @Param("beginNum") Integer beginNum,
                                              @Param("pageSize") Integer pageSize);

    /**
     * 获取取样总条数
     * @param status
     * @param businessTitle
     */
    int getOrderVmNum(@Param("status") Integer status, @Param("businessTitle") String businessTitle);

    /**
     * 获取取样列表
     * @param status
     * @param businessTitle
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<OrderVm> findOrderVmList(@Param("status") Integer status, @Param("businessTitle") String businessTitle,
                                  @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize);
}