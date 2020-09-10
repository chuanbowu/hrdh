package com.flym.hrdh.api.service.order;

import com.flym.hrdh.api.model.order.OrderVm;
import com.flym.hrdh.pojo.order.Order;

import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:取样管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface IOrderService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    Order get(Long id);

    /**
     * 保存或编辑
     * @param order
     * @return
     */
    Order save(Order order);

    /**
     * 根据红人ID获好取样总条数
     * @param sensationId
     * @return
     */
    int getOrderVmNumBySensationId(Long sensationId);

    /**
     * 根据红人ID获好取样列表
     * @param sensationId
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<OrderVm> findOrderVmListBySensationId(Long sensationId, Integer beginNum, Integer pageSize);

    /**
     * 获取取样详情
     * @param orderId
     * @return
     */
    OrderVm  getOrderInfo(Long orderId);

    /**
     * 根据红人ID和商品ID获取条数
     * @param sensationId
     * @param goodsId
     * @return
     */
    int getOrderNumSensationIdOrGoodsId(Long sensationId, Long goodsId);

    /**
     * 根据商家ID获取总条数
     * @param businessId
     * @param status
     * @param businessTitle
     * @return
     */
    int getOrderVmNumByBusinessId(Long businessId, Integer status, String businessTitle);

    /**
     * 根据商家ID获取列表
     * @param businessId
     * @param status
     * @param businessTitle
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<OrderVm> findOrderVmListByBusinessId(Long businessId, Integer status,
                                              String businessTitle, Integer beginNum,
                                              Integer pageSize);

    /**
     * 获取取样总条数
     * @param status
     * @param businessTitle
     */
    int getOrderVmNum(Integer status, String businessTitle);

    /**
     * 获取取样列表
     * @param status
     * @param businessTitle
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<OrderVm> findOrderVmList(Integer status, String businessTitle,
                                  Integer beginNum, Integer pageSize);
}
