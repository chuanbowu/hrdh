package com.flym.hrdh.service.common;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.model.common.GoodsAdvertisementVm;
import com.flym.hrdh.api.service.common.IGoodsAdvertisementService;
import com.flym.hrdh.mapper.single.common.GoodsAdvertisementMapper;
import com.flym.hrdh.pojo.common.GoodsAdvertisement;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:广告管理</p>
 * <p>Copyright: Copyright (c) 2020-06-22</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class GoodsAdvertisementServiceImpl implements IGoodsAdvertisementService {

	@Resource
	private GoodsAdvertisementMapper goodsAdvertisementMapper;

	@Override
	public GoodsAdvertisement get(Long id) {
		if (id == null) {
			return null;
		}
		return goodsAdvertisementMapper.selectByPrimaryKey(id);
	}

	@Override
	public GoodsAdvertisement save(GoodsAdvertisement goodsAdvertisement) {
		if(goodsAdvertisement.getId() != null && goodsAdvertisement.getId() > 0){
			goodsAdvertisementMapper.updateByPrimaryKeySelective(goodsAdvertisement);
		}else{
			goodsAdvertisementMapper.insert(goodsAdvertisement);
		}
		return goodsAdvertisement;
	}

	@Override
	public List<GoodsAdvertisement> findGoodsAdvertisementVmList(Integer type) {
		return goodsAdvertisementMapper.findGoodsAdvertisementVmList(type);
	}

	@Override
	public List<GoodsAdvertisementVm> findGoodsAdvertisementVmTotalList() {
		return goodsAdvertisementMapper.findGoodsAdvertisementVmTotalList();
	}


}
