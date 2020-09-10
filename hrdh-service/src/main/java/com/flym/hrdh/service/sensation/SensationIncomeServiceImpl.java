package com.flym.hrdh.service.sensation;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.model.sensation.SensationIncomeVm;
import com.flym.hrdh.api.service.sensation.ISensationIncomeService;
import com.flym.hrdh.mapper.single.sensation.SensationIncomeMapper;
import com.flym.hrdh.pojo.sensation.SensationIncome;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:红人收入明细管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class SensationIncomeServiceImpl implements ISensationIncomeService {

	@Resource
	private SensationIncomeMapper sensationIncomeMapper;

	@Override
	public SensationIncome get(Long id) {
		if (id == null) {
			return null;
		}
		return sensationIncomeMapper.selectByPrimaryKey(id);
	}

	@Override
	public SensationIncome save(SensationIncome sensationIncome) {
		if(sensationIncome.getId() != null && sensationIncome.getId() > 0){
			sensationIncomeMapper.updateByPrimaryKeySelective(sensationIncome);
		}else{
			sensationIncomeMapper.insert(sensationIncome);
		}
		return sensationIncome;
	}

	@Override
	public int getSensationIncomeVmNumBySensationId(Long sensationId) {
		return sensationIncomeMapper.getSensationIncomeVmNumBySensationId(sensationId);
	}

	@Override
	public List<SensationIncomeVm> findSensationIncomeVmListBySensationId(Long sensationId, Integer beginNum, Integer pageSize) {
		return sensationIncomeMapper.findSensationIncomeVmListBySensationId(sensationId, beginNum, pageSize);
	}

	@Override
	public int getSensationIncomeVmNum(Long sensationId, Integer type) {
		return sensationIncomeMapper.getSensationIncomeVmNum(sensationId, type);
	}

	@Override
	public List<SensationIncomeVm> findSensationIncomeVmList(Long sensationId, Integer type, Integer beginNum, Integer pageSize) {
		return sensationIncomeMapper.findSensationIncomeVmList(sensationId, type, beginNum, pageSize);
	}

	@Override
	public SensationIncome getLastHandleDate() {
		return sensationIncomeMapper.getLastHandleDate();
	}

	@Override
	public SensationIncome getBySensationIdOrGoodsIdOrTradeParentId(Long sensationId, Long goodsId, String tradeParentId) {
		return sensationIncomeMapper.getBySensationIdOrGoodsIdOrTradeParentId(sensationId, goodsId, tradeParentId);
	}

	@Override
	public int updateLastHandleDate(Date date) {
		return sensationIncomeMapper.updateLastHandleDate(date);
	}

}
