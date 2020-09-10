package com.flym.hrdh.service.business;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.model.business.CommissionGoodsVm;
import com.flym.hrdh.api.service.business.ICommissionGoodsService;
import com.flym.hrdh.mapper.single.business.CommissionGoodsMapper;
import com.flym.hrdh.pojo.business.CommissionGoods;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:佣金商品管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class CommissionGoodsServiceImpl implements ICommissionGoodsService {

	@Resource
	private CommissionGoodsMapper commissionGoodsMapper;

	@Override
	public CommissionGoods get(Long id) {
		if (id == null) {
			return null;
		}
		return commissionGoodsMapper.selectByPrimaryKey(id);
	}

	@Override
	public CommissionGoods save(CommissionGoods commissionGoods) {
		if(commissionGoods.getId() != null && commissionGoods.getId() > 0){
			commissionGoodsMapper.updateByPrimaryKeySelective(commissionGoods);
		}else{
			commissionGoodsMapper.insert(commissionGoods);
		}
		return commissionGoods;
	}

	@Override
	public List<CommissionGoods> findCommissionGoodsListByGoodsName(String goodsName, Integer beginNum, Integer pageSize) {
		return commissionGoodsMapper.findCommissionGoodsListByGoodsName(goodsName, beginNum, pageSize);
	}

	@Override
	public int getCommissionGoodsListByGoodsNameNum(String goodsName) {
		return commissionGoodsMapper.getCommissionGoodsListByGoodsNameNum(goodsName);
	}

	@Override
	public List<CommissionGoods> findCommissionGoodsListByType(Long typeId, Integer beginNum, Integer pageSize) {
		return commissionGoodsMapper.findCommissionGoodsListByType(typeId, beginNum, pageSize);
	}

	@Override
	public int getCommissionGoodsListByTypeNum(Long typeId) {
		return commissionGoodsMapper.getCommissionGoodsListByTypeNum(typeId);
	}

	@Override
	public CommissionGoodsVm getCommissionGoodsVm(Long id) {
		return commissionGoodsMapper.getCommissionGoodsVm(id);
	}

	@Override
	public List<CommissionGoods> findCommissionGoodsListByGrade(String goodsName, Long typeId , Integer beginNum, Integer pageSize) {
		return commissionGoodsMapper.findCommissionGoodsListByGrade(goodsName, typeId, beginNum, pageSize);
	}

	@Override
	public int getCommissionGoodsListByGradeNum(String goodsName, Long typeId) {
		return commissionGoodsMapper.getCommissionGoodsListByGradeNum(goodsName,typeId);
	}

	@Override
	public List<CommissionGoodsVm> findCommissionGoodsListByBusinessId(Long businessId, String businessTitle, Long typeId, Integer beginNum, Integer pageSize) {
		return commissionGoodsMapper.findCommissionGoodsListByBusinessId(businessId, businessTitle, typeId, beginNum, pageSize);
	}

	@Override
	public int getCommissionGoodsListByBusinessId(Long businessId, String businessTitle, Long typeId) {
		return commissionGoodsMapper.getCommissionGoodsListByBusinessId(businessId, businessTitle, typeId);
	}

	@Override
	public int updateStatus(String goodsIds, Integer status, Date date) {
		return commissionGoodsMapper.updateStatus(goodsIds, status, date);
	}

	@Override
	public int getNewCommissionGoodsNum(Long businessId, Date date) {
		return commissionGoodsMapper.getNewCommissionGoodsNum(businessId, date);
	}

	@Override
	public List<CommissionGoodsVm> findCommissionGoodsVmList(Long businessId, String businessTitle, Long typeId, Integer beginNum, Integer pageSize) {
		return commissionGoodsMapper.findCommissionGoodsVmList(businessId, businessTitle, typeId, beginNum, pageSize);
	}

	@Override
	public int getCommissionGoodsVmNum(Long businessId, String businessTitle, Long typeId) {
		return commissionGoodsMapper.getCommissionGoodsVmNum(businessId, businessTitle, typeId);
	}

	@Override
	public int updateSurplusNum(Long goodsId, Integer status) {
		return commissionGoodsMapper.updateSurplusNum(goodsId, status);
	}

	@Override
	public Long getIdByItemId(Long itemId) {
		return commissionGoodsMapper.getIdByItemId(itemId);
	}

}
