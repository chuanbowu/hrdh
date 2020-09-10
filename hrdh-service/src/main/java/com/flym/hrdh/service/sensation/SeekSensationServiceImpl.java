package com.flym.hrdh.service.sensation;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.service.sensation.ISeekSensationService;
import com.flym.hrdh.mapper.single.sensation.SeekSensationMapper;
import com.flym.hrdh.pojo.sensation.SeekSensation;

import javax.annotation.Resource;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:找红人管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class SeekSensationServiceImpl implements ISeekSensationService {

	@Resource
	private SeekSensationMapper seekSensationMapper;

	@Override
	public SeekSensation get(Long id) {
		if (id == null) {
			return null;
		}
		return seekSensationMapper.selectByPrimaryKey(id);
	}

	@Override
	public SeekSensation save(SeekSensation seekSensation) {
		if(seekSensation.getId() != null && seekSensation.getId() > 0){
			seekSensationMapper.updateByPrimaryKeySelective(seekSensation);
		}else{
			seekSensationMapper.insert(seekSensation);
		}
		return seekSensation;
	}

}
