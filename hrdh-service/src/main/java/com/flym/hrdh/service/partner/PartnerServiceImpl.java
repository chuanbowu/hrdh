package com.flym.hrdh.service.partner;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.service.partner.IPartnerService;
import com.flym.hrdh.mapper.single.partner.PartnerMapper;
import com.flym.hrdh.pojo.partner.Partner;

import javax.annotation.Resource;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:合伙人管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class PartnerServiceImpl implements IPartnerService {

	@Resource
	private PartnerMapper partnerMapper;

	@Override
	public Partner get(Long id) {
		if (id == null) {
			return null;
		}
		return partnerMapper.selectByPrimaryKey(id);
	}

	@Override
	public Partner save(Partner partner) {
		if(partner.getId() != null && partner.getId() > 0){
			partnerMapper.updateByPrimaryKeySelective(partner);
		}else{
			partnerMapper.insert(partner);
		}
		return partner;
	}

}
