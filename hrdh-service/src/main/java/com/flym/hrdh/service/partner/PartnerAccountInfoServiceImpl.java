package com.flym.hrdh.service.partner;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.service.partner.IPartnerAccountInfoService;
import com.flym.hrdh.mapper.single.partner.PartnerAccountInfoMapper;
import com.flym.hrdh.pojo.partner.PartnerAccountInfo;

import javax.annotation.Resource;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:合伙人账户明细管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class PartnerAccountInfoServiceImpl implements IPartnerAccountInfoService {

	@Resource
	private PartnerAccountInfoMapper partnerAccountInfoMapper;

	@Override
	public PartnerAccountInfo get(Long id) {
		if (id == null) {
			return null;
		}
		return partnerAccountInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public PartnerAccountInfo save(PartnerAccountInfo partnerAccountInfo) {
		if(partnerAccountInfo.getId() != null && partnerAccountInfo.getId() > 0){
			partnerAccountInfoMapper.updateByPrimaryKeySelective(partnerAccountInfo);
		}else{
			partnerAccountInfoMapper.insert(partnerAccountInfo);
		}
		return partnerAccountInfo;
	}

}
