package com.flym.hrdh.service.common;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.model.common.BottomCommissionRecommendVm;
import com.flym.hrdh.api.service.common.IBottomCommissionRecommendService;
import com.flym.hrdh.mapper.single.common.BottomCommissionRecommendMapper;
import com.flym.hrdh.pojo.common.BottomCommissionRecommend;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:返佣推荐商品管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class BottomCommissionRecommendServiceImpl implements IBottomCommissionRecommendService {

	@Resource
	private BottomCommissionRecommendMapper bottomCommissionRecommendMapper;

	@Override
	public BottomCommissionRecommend get(Long id) {
		if (id == null) {
			return null;
		}
		return bottomCommissionRecommendMapper.selectByPrimaryKey(id);
	}

	@Override
	public BottomCommissionRecommend save(BottomCommissionRecommend bottomCommissionRecommend) {
		if(bottomCommissionRecommend.getId() != null && bottomCommissionRecommend.getId() > 0){
			bottomCommissionRecommendMapper.updateByPrimaryKeySelective(bottomCommissionRecommend);
		}else{
			bottomCommissionRecommendMapper.insert(bottomCommissionRecommend);
		}
		return bottomCommissionRecommend;
	}

	@Override
	public List<BottomCommissionRecommendVm> findBottomCommissionRecommendVmList() {
		return bottomCommissionRecommendMapper.findBottomCommissionRecommendVmList();
	}

	@Override
	public List<BottomCommissionRecommendVm> findBottomCommissionRecommendVmTotalList() {
		return bottomCommissionRecommendMapper.findBottomCommissionRecommendVmTotalList();
	}

	@Override
	public int updateStatus(String bottomCommissionRecommendIds, Integer status, Long userId, Date date) {
		return bottomCommissionRecommendMapper.updateStatus(bottomCommissionRecommendIds, status, userId, date);
	}

}
