package com.flym.hrdh.service.common;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.model.common.SensationRecommendVm;
import com.flym.hrdh.api.service.common.ISensationRecommendService;
import com.flym.hrdh.mapper.single.common.SensationRecommendMapper;
import com.flym.hrdh.pojo.common.SensationRecommend;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:红人推荐管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class SensationRecommendServiceImpl implements ISensationRecommendService {

	@Resource
	private SensationRecommendMapper sensationRecommendMapper;

	@Override
	public SensationRecommend get(Long id) {
		if (id == null) {
			return null;
		}
		return sensationRecommendMapper.selectByPrimaryKey(id);
	}

	@Override
	public SensationRecommend save(SensationRecommend sensationRecommend) {
		if(sensationRecommend.getId() != null && sensationRecommend.getId() > 0){
			sensationRecommendMapper.updateByPrimaryKeySelective(sensationRecommend);
		}else{
			sensationRecommendMapper.insert(sensationRecommend);
		}
		return sensationRecommend;
	}

	@Override
	public List<SensationRecommendVm> findSensationRecommendVmList() {
		return sensationRecommendMapper.findSensationRecommendVmList();
	}

	@Override
	public int updateStatus(String sensationRecommendIds, Integer status, Long userId, Date date) {
		return sensationRecommendMapper.updateStatus(sensationRecommendIds, status, userId, date);
	}

}
