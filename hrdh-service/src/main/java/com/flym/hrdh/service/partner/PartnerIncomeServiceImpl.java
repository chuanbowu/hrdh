package com.flym.hrdh.service.partner;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.service.partner.IPartnerIncomeService;
import com.flym.hrdh.mapper.single.partner.PartnerIncomeMapper;
import com.flym.hrdh.pojo.partner.PartnerIncome;

import javax.annotation.Resource;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:合伙人收入明细管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class PartnerIncomeServiceImpl implements IPartnerIncomeService {

	@Resource
	private PartnerIncomeMapper partnerIncomeMapper;

	@Override
	public PartnerIncome get(Long id) {
		if (id == null) {
			return null;
		}
		return partnerIncomeMapper.selectByPrimaryKey(id);
	}

	@Override
	public PartnerIncome save(PartnerIncome partnerIncome) {
		if(partnerIncome.getId() != null && partnerIncome.getId() > 0){
			partnerIncomeMapper.updateByPrimaryKeySelective(partnerIncome);
		}else{
			partnerIncomeMapper.insert(partnerIncome);
		}
		return partnerIncome;
	}

}
