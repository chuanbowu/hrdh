package com.flym.hrdh.service.sensation;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.service.sensation.ISensationAlipayService;
import com.flym.hrdh.mapper.single.sensation.SensationAlipayMapper;
import com.flym.hrdh.pojo.sensation.SensationAlipay;

import javax.annotation.Resource;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:红人支付宝账号管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class SensationAlipayServiceImpl implements ISensationAlipayService {

	@Resource
	private SensationAlipayMapper sensationAlipayMapper;

	@Override
	public SensationAlipay get(Long id) {
		if (id == null) {
			return null;
		}
		return sensationAlipayMapper.selectByPrimaryKey(id);
	}

	@Override
	public SensationAlipay save(SensationAlipay sensationAlipay) {
		if(sensationAlipay.getId() != null && sensationAlipay.getId() > 0){
			sensationAlipayMapper.updateByPrimaryKeySelective(sensationAlipay);
		}else{
			sensationAlipayMapper.insert(sensationAlipay);
		}
		return sensationAlipay;
	}

}
