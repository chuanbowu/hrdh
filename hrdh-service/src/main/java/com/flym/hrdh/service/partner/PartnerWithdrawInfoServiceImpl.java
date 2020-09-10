package com.flym.hrdh.service.partner;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.service.partner.IPartnerWithdrawInfoService;
import com.flym.hrdh.mapper.single.partner.PartnerWithdrawInfoMapper;
import com.flym.hrdh.pojo.partner.PartnerWithdrawInfo;

import javax.annotation.Resource;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:合伙人提现明细管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class PartnerWithdrawInfoServiceImpl implements IPartnerWithdrawInfoService {

	@Resource
	private PartnerWithdrawInfoMapper partnerWithdrawInfoMapper;

	@Override
	public PartnerWithdrawInfo get(Long id) {
		if (id == null) {
			return null;
		}
		return partnerWithdrawInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public PartnerWithdrawInfo save(PartnerWithdrawInfo partnerWithdrawInfo) {
		if(partnerWithdrawInfo.getId() != null && partnerWithdrawInfo.getId() > 0){
			partnerWithdrawInfoMapper.updateByPrimaryKeySelective(partnerWithdrawInfo);
		}else{
			partnerWithdrawInfoMapper.insert(partnerWithdrawInfo);
		}
		return partnerWithdrawInfo;
	}

}
