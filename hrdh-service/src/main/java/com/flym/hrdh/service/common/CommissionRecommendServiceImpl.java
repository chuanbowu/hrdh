package com.flym.hrdh.service.common;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.model.common.CommissionRecommendVm;
import com.flym.hrdh.api.service.common.ICommissionRecommendService;
import com.flym.hrdh.mapper.single.common.CommissionRecommendMapper;
import com.flym.hrdh.pojo.common.CommissionRecommend;

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
public class CommissionRecommendServiceImpl implements ICommissionRecommendService {

	@Resource
	private CommissionRecommendMapper commissionRecommendMapper;

	@Override
	public CommissionRecommend get(Long id) {
		if (id == null) {
			return null;
		}
		return commissionRecommendMapper.selectByPrimaryKey(id);
	}

	@Override
	public CommissionRecommend save(CommissionRecommend commissionRecommend) {
		if(commissionRecommend.getId() != null && commissionRecommend.getId() > 0){
			commissionRecommendMapper.updateByPrimaryKeySelective(commissionRecommend);
		}else{
			commissionRecommendMapper.insert(commissionRecommend);
		}
		return commissionRecommend;
	}

	@Override
	public List<CommissionRecommendVm> findCommissionRecommendVmList(Long typeId) {
		return commissionRecommendMapper.findCommissionRecommendVmList(typeId);
	}

	@Override
	public List<CommissionRecommendVm> findCommissionRecommendVmTotalList() {
		return commissionRecommendMapper.findCommissionRecommendVmTotalList();
	}

	@Override
	public int updateStatus(String commissionRecommendIds, Integer status, Long userId, Date date) {
		return commissionRecommendMapper.updateStatus(commissionRecommendIds, status, userId, date);
	}
}
