package com.flym.hrdh.service.common;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.service.common.IAreaService;
import com.flym.hrdh.api.service.common.IProvinceService;
import com.flym.hrdh.mapper.single.common.AreaMapper;
import com.flym.hrdh.mapper.single.common.ProvinceMapper;
import com.flym.hrdh.pojo.common.Province;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:省份管理</p>
 * <p>Copyright: Copyright (c) 2020-05-12</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class ProvinceServiceImpl implements IProvinceService {

	@Resource
	private ProvinceMapper provinceMapper;

	@Override
	public List<Province> findProvinceList() {
		return provinceMapper.findProvinceList();
	}
}
