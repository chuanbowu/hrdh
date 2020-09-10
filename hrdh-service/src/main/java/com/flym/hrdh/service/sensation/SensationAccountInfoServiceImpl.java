package com.flym.hrdh.service.sensation;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.model.sensation.SensationAccountInfoVm;
import com.flym.hrdh.api.service.sensation.ISensationAccountInfoService;
import com.flym.hrdh.mapper.single.sensation.SensationAccountInfoMapper;
import com.flym.hrdh.pojo.sensation.SensationAccountInfo;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:红人账户明细管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class SensationAccountInfoServiceImpl implements ISensationAccountInfoService {

	@Resource
	private SensationAccountInfoMapper sensationAccountInfoMapper;

	@Override
	public SensationAccountInfo get(Long id) {
		if (id == null) {
			return null;
		}
		return sensationAccountInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public SensationAccountInfo save(SensationAccountInfo sensationAccountInfo) {
		if(sensationAccountInfo.getId() != null && sensationAccountInfo.getId() > 0){
			sensationAccountInfoMapper.updateByPrimaryKeySelective(sensationAccountInfo);
		}else{
			sensationAccountInfoMapper.insert(sensationAccountInfo);
		}
		return sensationAccountInfo;
	}

	@Override
	public SensationAccountInfoVm getStatistics(Date date) {
		return sensationAccountInfoMapper.getStatistics(date);
	}

}
