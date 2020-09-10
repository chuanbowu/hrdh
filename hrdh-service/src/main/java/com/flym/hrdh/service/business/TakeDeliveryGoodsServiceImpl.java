package com.flym.hrdh.service.business;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.model.business.TakeDeliveryGoodsVm;
import com.flym.hrdh.api.service.business.ITakeDeliveryGoodsService;
import com.flym.hrdh.mapper.single.business.TakeDeliveryGoodsMapper;
import com.flym.hrdh.pojo.business.TakeDeliveryGoods;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:拿货商品管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class TakeDeliveryGoodsServiceImpl implements ITakeDeliveryGoodsService {

	@Resource
	private TakeDeliveryGoodsMapper takeDeliveryGoodsMapper;

	@Override
	public TakeDeliveryGoods get(Long id) {
		if (id == null) {
			return null;
		}
		return takeDeliveryGoodsMapper.selectByPrimaryKey(id);
	}

	@Override
	public TakeDeliveryGoods save(TakeDeliveryGoods takeDeliveryGoods) {
		if(takeDeliveryGoods.getId() != null && takeDeliveryGoods.getId() > 0){
			takeDeliveryGoodsMapper.updateByPrimaryKeySelective(takeDeliveryGoods);
		}else{
			takeDeliveryGoodsMapper.insert(takeDeliveryGoods);
		}
		return takeDeliveryGoods;
	}

	@Override
	public List<TakeDeliveryGoods> findTakeDeliveryGoodsListByGoodsName(String goodsName, Integer beginNum, Integer pageSize) {
		return takeDeliveryGoodsMapper.findTakeDeliveryGoodsListByGoodsName(goodsName, beginNum, pageSize);
	}

	@Override
	public int getTakeDeliveryGoodsListByGoodsNameNum(String goodsName) {
		return takeDeliveryGoodsMapper.getTakeDeliveryGoodsListByGoodsNameNum(goodsName);
	}

	@Override
	public List<TakeDeliveryGoods> findTakeDeliveryGoodsListByType(Long typeId, Integer beginNum, Integer pageSize) {
		return takeDeliveryGoodsMapper.findTakeDeliveryGoodsListByType(typeId, beginNum, pageSize);
	}

	@Override
	public int getTakeDeliveryGoodsListByTypeNum(Long typeId) {
		return takeDeliveryGoodsMapper.getTakeDeliveryGoodsListByTypeNum(typeId);
	}

	@Override
	public TakeDeliveryGoodsVm getTakeDeliveryGoodsVm(Long id) {
		return takeDeliveryGoodsMapper.getTakeDeliveryGoodsVm(id);
	}

	@Override
	public List<TakeDeliveryGoods> findTakeDeliveryGoodsListByGrade(String goodsName, Long typeId, Integer beginNum, Integer pageSize) {
		return takeDeliveryGoodsMapper.findTakeDeliveryGoodsListByGrade(goodsName, typeId, beginNum, pageSize);
	}

	@Override
	public int getTakeDeliveryGoodsListByGradeNum(String goodsName, Long typeId) {
		return takeDeliveryGoodsMapper.getTakeDeliveryGoodsListByGradeNum(goodsName, typeId);
	}

	@Override
	public List<TakeDeliveryGoodsVm> findTakeDeliveryGoodsListByBusinessId(Long businessId, String businessTitle, Long typeId, Integer beginNum, Integer pageSize) {
		return takeDeliveryGoodsMapper.findTakeDeliveryGoodsListByBusinessId(businessId, businessTitle, typeId, beginNum, pageSize);
	}

	@Override
	public int getTakeDeliveryGoodsListByBusinessIdNum(Long businessId, String businessTitle, Long typeId) {
		return takeDeliveryGoodsMapper.getTakeDeliveryGoodsListByBusinessIdNum(businessId, businessTitle, typeId);
	}

	@Override
	public int updateStatus(String goodsIds, Integer status, Date date) {
		return takeDeliveryGoodsMapper.updateStatus(goodsIds, status, date);
	}

	@Override
	public int getNewTakeDeliveryGoodsNum(Long businessId, Date date) {
		return takeDeliveryGoodsMapper.getNewTakeDeliveryGoodsNum(businessId, date);
	}

	@Override
	public List<TakeDeliveryGoodsVm> findTakeDeliveryGoodsVmList(Long businessId, String businessTitle, Long typeId, Integer beginNum, Integer pageSize) {
		return takeDeliveryGoodsMapper.findTakeDeliveryGoodsVmList(businessId, businessTitle, typeId, beginNum, pageSize);
	}

	@Override
	public int getTakeDeliveryGoodsVmNum(Long businessId, String businessTitle, Long typeId) {
		return takeDeliveryGoodsMapper.getTakeDeliveryGoodsVmNum(businessId, businessTitle, typeId);
	}

}
