package com.flym.hrdh.service.common;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.service.common.IAreaService;
import com.flym.hrdh.api.service.common.IProblemService;
import com.flym.hrdh.mapper.single.common.AreaMapper;
import com.flym.hrdh.mapper.single.common.ProblemMapper;
import com.flym.hrdh.pojo.common.Area;
import com.flym.hrdh.pojo.common.Problem;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:区管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class AreaServiceImpl implements IAreaService {

	@Resource
	private AreaMapper areaMapper;

	@Override
	public List<Area> findAreaList(String cid) {
		return areaMapper.findAreaList(cid);
	}
}
