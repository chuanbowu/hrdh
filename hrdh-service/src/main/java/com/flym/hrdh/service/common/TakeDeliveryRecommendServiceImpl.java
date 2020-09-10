package com.flym.hrdh.service.common;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.model.common.TakeDeliveryRecommendVm;
import com.flym.hrdh.api.service.common.ITakeDeliveryRecommendService;
import com.flym.hrdh.mapper.single.common.TakeDeliveryRecommendMapper;
import com.flym.hrdh.pojo.common.TakeDeliveryRecommend;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:拿货推荐商品管理</p>
 * <p>Copyright: Copyright (c) 2020-05-13</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class TakeDeliveryRecommendServiceImpl implements ITakeDeliveryRecommendService {

	@Resource
	private TakeDeliveryRecommendMapper takeDeliveryRecommendMapper;

	@Override
	public TakeDeliveryRecommend get(Long id) {
		if (id == null) {
			return null;
		}
		return takeDeliveryRecommendMapper.selectByPrimaryKey(id);
	}

	@Override
	public TakeDeliveryRecommend save(TakeDeliveryRecommend takeDeliveryRecommend) {
		if(takeDeliveryRecommend.getId() != null && takeDeliveryRecommend.getId() > 0){
			takeDeliveryRecommendMapper.updateByPrimaryKeySelective(takeDeliveryRecommend);
		}else{
			takeDeliveryRecommendMapper.insert(takeDeliveryRecommend);
		}
		return takeDeliveryRecommend;
	}

	@Override
	public List<TakeDeliveryRecommendVm> findTakeDeliveryRecommendVmList(Long typeId) {
		return takeDeliveryRecommendMapper.findTakeDeliveryRecommendVmList(typeId);
	}

	@Override
	public List<TakeDeliveryRecommendVm> findTakeDeliveryRecommendVmTotalList() {
		return takeDeliveryRecommendMapper.findTakeDeliveryRecommendVmTotalList();
	}

	@Override
	public int updateStatus(String takeDeliveryRecommendIds, Integer status, Long userId, Date date) {
		return takeDeliveryRecommendMapper.updateStatus(takeDeliveryRecommendIds, status, userId, date);
	}

}
