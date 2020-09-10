package com.flym.hrdh.service.common;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.service.common.IAreaService;
import com.flym.hrdh.api.service.common.IGoodsNumConfigService;
import com.flym.hrdh.mapper.single.common.AreaMapper;
import com.flym.hrdh.mapper.single.common.GoodsNumConfigMapper;
import com.flym.hrdh.pojo.common.Advert;
import com.flym.hrdh.pojo.common.Area;
import com.flym.hrdh.pojo.common.GoodsNumConfig;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:商品数量配置管理</p>
 * <p>Copyright: Copyright (c) 2020-07-18</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class GoodsNumConfigServiceImpl implements IGoodsNumConfigService {

	@Resource
	private GoodsNumConfigMapper goodsNumConfigMapper;

	@Override
	public GoodsNumConfig get(Long id) {
		if (id == null) {
			return null;
		}
		return goodsNumConfigMapper.selectByPrimaryKey(id);
	}

	@Override
	public GoodsNumConfig save(GoodsNumConfig goodsNumConfig) {
		if(goodsNumConfig.getId() != null && goodsNumConfig.getId() > 0){
			goodsNumConfigMapper.updateByPrimaryKeySelective(goodsNumConfig);
		}else{
			goodsNumConfigMapper.insert(goodsNumConfig);
		}
		return goodsNumConfig;
	}

	@Override
	public GoodsNumConfig getGoodsNumConfig() {
		return goodsNumConfigMapper.getGoodsNumConfig();
	}
}
