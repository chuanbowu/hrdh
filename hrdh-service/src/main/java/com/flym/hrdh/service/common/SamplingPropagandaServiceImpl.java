package com.flym.hrdh.service.common;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.service.common.ISamplingPropagandaService;
import com.flym.hrdh.mapper.single.common.SamplingPropagandaMapper;
import com.flym.hrdh.pojo.common.SamplingPropaganda;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:领样宣传管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class SamplingPropagandaServiceImpl implements ISamplingPropagandaService {

	@Resource
	private SamplingPropagandaMapper samplingPropagandaMapper;

	@Override
	public SamplingPropaganda get(Long id) {
		if (id == null) {
			return null;
		}
		return samplingPropagandaMapper.selectByPrimaryKey(id);
	}

	@Override
	public SamplingPropaganda save(SamplingPropaganda samplingPropaganda) {
		if(samplingPropaganda.getId() != null && samplingPropaganda.getId() > 0){
			samplingPropagandaMapper.updateByPrimaryKeySelective(samplingPropaganda);
		}else{
			samplingPropagandaMapper.insert(samplingPropaganda);
		}
		return samplingPropaganda;
	}

	@Override
	public List<SamplingPropaganda> findSamplingPropagandaRandList(Integer randNum) {
		return samplingPropagandaMapper.findSamplingPropagandaRandList(randNum);
	}

}
