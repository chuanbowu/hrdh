package com.flym.hrdh.service.common;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.service.common.ICityService;
import com.flym.hrdh.mapper.single.common.CityMapper;
import com.flym.hrdh.pojo.common.City;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:城市管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class CityServiceImpl implements ICityService {

	@Resource
	private CityMapper cityMapper;

	@Override
	public List<City> findCityList(String cid) {
		return cityMapper.findCityList(cid);
	}
}
