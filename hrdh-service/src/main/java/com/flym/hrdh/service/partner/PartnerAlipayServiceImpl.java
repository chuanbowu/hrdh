package com.flym.hrdh.service.partner;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.service.partner.IPartnerAlipayService;
import com.flym.hrdh.mapper.single.partner.PartnerAlipayMapper;
import com.flym.hrdh.pojo.partner.PartnerAlipay;

import javax.annotation.Resource;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:合伙人支付宝账号管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class PartnerAlipayServiceImpl implements IPartnerAlipayService {

	@Resource
	private PartnerAlipayMapper partnerAlipayMapper;

	@Override
	public PartnerAlipay get(Long id) {
		if (id == null) {
			return null;
		}
		return partnerAlipayMapper.selectByPrimaryKey(id);
	}

	@Override
	public PartnerAlipay save(PartnerAlipay partnerAlipay) {
		if(partnerAlipay.getId() != null && partnerAlipay.getId() > 0){
			partnerAlipayMapper.updateByPrimaryKeySelective(partnerAlipay);
		}else{
			partnerAlipayMapper.insert(partnerAlipay);
		}
		return partnerAlipay;
	}

}
