package com.flym.hrdh.service.common;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.model.common.AdvertisementVm;
import com.flym.hrdh.api.service.common.IAdvertisementService;
import com.flym.hrdh.mapper.single.common.AdvertisementMapper;
import com.flym.hrdh.pojo.common.Advertisement;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:广告管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class AdvertisementServiceImpl implements IAdvertisementService {

	@Resource
	private AdvertisementMapper advertisementMapper;

	@Override
	public Advertisement get(Long id) {
		if (id == null) {
			return null;
		}
		return advertisementMapper.selectByPrimaryKey(id);
	}

	@Override
	public Advertisement save(Advertisement advertisement) {
		if(advertisement.getId() != null && advertisement.getId() > 0){
			advertisementMapper.updateByPrimaryKeySelective(advertisement);
		}else{
			advertisementMapper.insert(advertisement);
		}
		return advertisement;
	}

	@Override
	public List<AdvertisementVm> findAdvertisementVmList() {
		return advertisementMapper.findAdvertisementVmList();
	}

	@Override
	public List<AdvertisementVm> findAdvertisementVmTotalList() {
		return advertisementMapper.findAdvertisementVmTotalList();
	}

	@Override
	public int updateStatus(String advertisementIds, Integer status, Long userId, Date date) {
		return advertisementMapper.updateStatus(advertisementIds, status, userId, date);
	}

}
