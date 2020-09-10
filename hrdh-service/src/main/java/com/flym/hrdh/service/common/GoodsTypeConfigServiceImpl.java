package com.flym.hrdh.service.common;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.model.common.GoodsTypeConfigVm;
import com.flym.hrdh.api.service.common.IGoodsNumConfigService;
import com.flym.hrdh.api.service.common.IGoodsTypeConfigService;
import com.flym.hrdh.mapper.single.common.GoodsNumConfigMapper;
import com.flym.hrdh.mapper.single.common.GoodsTypeConfigMapper;
import com.flym.hrdh.pojo.common.GoodsNumConfig;
import com.flym.hrdh.pojo.common.GoodsTypeConfig;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:商品数配置管理</p>
 * <p>Copyright: Copyright (c) 2020-07-21</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class GoodsTypeConfigServiceImpl implements IGoodsTypeConfigService {

	@Resource
	private GoodsTypeConfigMapper goodsTypeConfigMapper;

	@Override
	public GoodsTypeConfig get(Long id) {
		if (id == null) {
			return null;
		}
		return goodsTypeConfigMapper.selectByPrimaryKey(id);
	}

	@Override
	public GoodsTypeConfig save(GoodsTypeConfig goodsTypeConfig) {
		if(goodsTypeConfig.getId() != null && goodsTypeConfig.getId() > 0){
			goodsTypeConfigMapper.updateByPrimaryKeySelective(goodsTypeConfig);
		}else{
			goodsTypeConfigMapper.insert(goodsTypeConfig);
		}
		return goodsTypeConfig;
	}

	@Override
	public List<GoodsTypeConfigVm> finGoodsNumConfigList() {
		return goodsTypeConfigMapper.finGoodsNumConfigList();
	}

	@Override
	public int updateStatus(String ids, Integer status, Long userId, Date date) {
		return goodsTypeConfigMapper.updateStatus(ids, status, userId, date);
	}

}
