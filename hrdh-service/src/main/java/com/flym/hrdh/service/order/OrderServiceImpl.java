package com.flym.hrdh.service.order;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.model.order.OrderVm;
import com.flym.hrdh.api.service.order.IOrderService;
import com.flym.hrdh.mapper.single.order.OrderMapper;
import com.flym.hrdh.pojo.order.Order;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:取样管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class OrderServiceImpl implements IOrderService {

	@Resource
	private OrderMapper orderMapper;

	@Override
	public Order get(Long id) {
		if (id == null) {
			return null;
		}
		return orderMapper.selectByPrimaryKey(id);
	}

	@Override
	public Order save(Order order) {
		if(order.getId() != null && order.getId() > 0){
			orderMapper.updateByPrimaryKeySelective(order);
		}else{
			orderMapper.insert(order);
		}
		return order;
	}

	@Override
	public int getOrderVmNumBySensationId(Long sensationId) {
		return orderMapper.getOrderVmNumBySensationId(sensationId);
	}

	@Override
	public List<OrderVm> findOrderVmListBySensationId(Long sensationId, Integer beginNum, Integer pageSize) {
		return orderMapper.findOrderVmListBySensationId(sensationId, beginNum, pageSize);
	}

	@Override
	public OrderVm getOrderInfo(Long orderId) {
		return orderMapper.getOrderInfo(orderId);
	}

	@Override
	public int getOrderNumSensationIdOrGoodsId(Long sensationId, Long goodsId) {
		return orderMapper.getOrderNumSensationIdOrGoodsId(sensationId, goodsId);
	}

	@Override
	public int getOrderVmNumByBusinessId(Long businessId, Integer status, String businessTitle) {
		return orderMapper.getOrderVmNumByBusinessId(businessId, status, businessTitle);
	}

	@Override
	public List<OrderVm> findOrderVmListByBusinessId(Long businessId, Integer status, String businessTitle, Integer beginNum, Integer pageSize) {
		return orderMapper.findOrderVmListByBusinessId(businessId, status, businessTitle, beginNum, pageSize);
	}

	@Override
	public int getOrderVmNum(Integer status, String businessTitle) {
		return orderMapper.getOrderVmNum(status, businessTitle);
	}

	@Override
	public List<OrderVm> findOrderVmList(Integer status, String businessTitle, Integer beginNum, Integer pageSize) {
		return orderMapper.findOrderVmList(status, businessTitle, beginNum, pageSize);
	}
}
