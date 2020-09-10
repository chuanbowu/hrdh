package com.flym.hrdh.service.common;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.model.common.AdvertVm;
import com.flym.hrdh.api.service.common.IAdvertService;
import com.flym.hrdh.mapper.single.common.AdvertMapper;
import com.flym.hrdh.pojo.common.Advert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:轮播图管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class AdvertServiceImpl implements IAdvertService {

	@Resource
	private AdvertMapper advertMapper;

	@Override
	public Advert get(Long id) {
		if (id == null) {
			return null;
		}
		return advertMapper.selectByPrimaryKey(id);
	}

	@Override
	public Advert save(Advert advert) {
		if(advert.getId() != null && advert.getId() > 0){
			advertMapper.updateByPrimaryKeySelective(advert);
		}else{
			advertMapper.insert(advert);
		}
		return advert;
	}

	@Override
	public List<Advert> findAdvertList() {
		return advertMapper.findAdvertList();
	}

	@Override
	public List<AdvertVm> findAdvertVmList() {
		return advertMapper.findAdvertVmList();
	}

	@Override
	public int updateStatus(String advertIds, Integer status, Long userId, Date date) {
		return advertMapper.updateStatus(advertIds, status, userId, date);
	}

}
