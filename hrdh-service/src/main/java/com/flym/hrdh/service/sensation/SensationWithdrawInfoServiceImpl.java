package com.flym.hrdh.service.sensation;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.model.sensation.SensationWithdrawInfoVm;
import com.flym.hrdh.api.service.sensation.ISensationWithdrawInfoService;
import com.flym.hrdh.mapper.single.sensation.SensationWithdrawInfoMapper;
import com.flym.hrdh.pojo.sensation.SensationWithdrawInfo;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:红人提现明细管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class SensationWithdrawInfoServiceImpl implements ISensationWithdrawInfoService {

	@Resource
	private SensationWithdrawInfoMapper sensationWithdrawInfoMapper;

	@Override
	public SensationWithdrawInfo get(Long id) {
		if (id == null) {
			return null;
		}
		return sensationWithdrawInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public SensationWithdrawInfo save(SensationWithdrawInfo sensationWithdrawInfo) {
		if(sensationWithdrawInfo.getId() != null && sensationWithdrawInfo.getId() > 0){
			sensationWithdrawInfoMapper.updateByPrimaryKeySelective(sensationWithdrawInfo);
		}else{
			sensationWithdrawInfoMapper.insert(sensationWithdrawInfo);
		}
		return sensationWithdrawInfo;
	}

	@Override
	public int getSensationWithdrawInfoNum(Long sensationId) {
		return sensationWithdrawInfoMapper.getSensationWithdrawInfoNum(sensationId);
	}

	@Override
	public List<SensationWithdrawInfo> findSensationWithdrawInfoList(Long sensationId, Integer beginNum, Integer pageSize) {
		return sensationWithdrawInfoMapper.findSensationWithdrawInfoList(sensationId, beginNum, pageSize);
	}

	@Override
	public int getSensationWithdrawInfoVmNum(Long sensationId, Integer status) {
		return sensationWithdrawInfoMapper.getSensationWithdrawInfoVmNum(sensationId, status);
	}

	@Override
	public List<SensationWithdrawInfoVm> findSensationWithdrawInfoVmList(Long sensationId, Integer status, Integer beginNum, Integer pageSize) {
		return sensationWithdrawInfoMapper.findSensationWithdrawInfoVmList(sensationId, status, beginNum, pageSize);
	}

}
