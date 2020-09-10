package com.flym.hrdh.service.sensation;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.model.sensation.SensationCheckVm;
import com.flym.hrdh.api.service.sensation.ISensationCheckService;
import com.flym.hrdh.mapper.single.sensation.SensationCheckMapper;
import com.flym.hrdh.pojo.sensation.SensationCheck;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:红人认证审核管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class SensationCheckServiceImpl implements ISensationCheckService {

	@Resource
	private SensationCheckMapper sensationCheckMapper;

	@Override
	public SensationCheck get(Long id) {
		if (id == null) {
			return null;
		}
		return sensationCheckMapper.selectByPrimaryKey(id);
	}

	@Override
	public SensationCheck save(SensationCheck sensationCheck) {
		if(sensationCheck.getId() != null && sensationCheck.getId() > 0){
			sensationCheckMapper.updateByPrimaryKeySelective(sensationCheck);
		}else{
			sensationCheckMapper.insert(sensationCheck);
		}
		return sensationCheck;
	}

	@Override
	public SensationCheck getSensationCheckBySensation(Long sensationId) {
		return sensationCheckMapper.getSensationCheckBySensation(sensationId);
	}

	@Override
	public int getSensationCheckVmNum(Long sensationId, Integer checkStatus) {
		return sensationCheckMapper.getSensationCheckVmNum(sensationId, checkStatus);
	}

	@Override
	public List<SensationCheckVm> findSensationCheckVmList(Long sensationId, Integer checkStatus, Integer beginNum, Integer pageSize) {
		return sensationCheckMapper.findSensationCheckVmList(sensationId, checkStatus, beginNum, pageSize);
	}

}
