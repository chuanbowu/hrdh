package com.flym.hrdh.service.common;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.service.common.IRollPropagandaService;
import com.flym.hrdh.mapper.single.common.RollPropagandaMapper;
import com.flym.hrdh.pojo.common.RollPropaganda;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:滚动宣传管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class RollPropagandaServiceImpl implements IRollPropagandaService {

	@Resource
	private RollPropagandaMapper rollPropagandaMapper;

	@Override
	public RollPropaganda get(Long id) {
		if (id == null) {
			return null;
		}
		return rollPropagandaMapper.selectByPrimaryKey(id);
	}

	@Override
	public RollPropaganda save(RollPropaganda rollPropaganda) {
		if(rollPropaganda.getId() != null && rollPropaganda.getId() > 0){
			rollPropagandaMapper.updateByPrimaryKeySelective(rollPropaganda);
		}else{
			rollPropagandaMapper.insert(rollPropaganda);
		}
		return rollPropaganda;
	}

	@Override
	public List<RollPropaganda> findRollPropagandaRandList(Integer randNum) {
		return rollPropagandaMapper.findRollPropagandaRandList(randNum);
	}

}
