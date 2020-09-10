package com.flym.hrdh.service.common;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.service.common.IAliPayWithdrawConfigService;
import com.flym.hrdh.mapper.single.common.AliPayWithdrawConfigMapper;
import com.flym.hrdh.pojo.common.AliPayWithdrawConfig;

import javax.annotation.Resource;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:支付宝自动提现配置管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class AliPayWithdrawConfigServiceImpl implements IAliPayWithdrawConfigService {

	@Resource
	private AliPayWithdrawConfigMapper aliPayWithdrawConfigMapper;

	@Override
	public AliPayWithdrawConfig get(Long id) {
		if (id == null) {
			return null;
		}
		return aliPayWithdrawConfigMapper.selectByPrimaryKey(id);
	}

	@Override
	public AliPayWithdrawConfig save(AliPayWithdrawConfig aliPayWithdrawConfig) {
		if(aliPayWithdrawConfig.getId() != null && aliPayWithdrawConfig.getId() > 0){
			aliPayWithdrawConfigMapper.updateByPrimaryKeySelective(aliPayWithdrawConfig);
		}else{
			aliPayWithdrawConfigMapper.insert(aliPayWithdrawConfig);
		}
		return aliPayWithdrawConfig;
	}

	@Override
	public AliPayWithdrawConfig getNormal() {
		return aliPayWithdrawConfigMapper.getNormal();
	}

}
