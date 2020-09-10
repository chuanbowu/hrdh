package com.flym.hrdh.api.service.common;

import com.flym.hrdh.pojo.common.GoodsNumConfig;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:商品数量配置管理</p>
 * <p>Copyright: Copyright (c) 2020-07-18</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface IGoodsNumConfigService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    GoodsNumConfig get(Long id);

    /**
     * 保存或编辑
     * @param goodsNumConfig
     * @return
     */
    GoodsNumConfig save(GoodsNumConfig goodsNumConfig);

    /**
     * 获取商品数量配置
     * @return
     */
    GoodsNumConfig getGoodsNumConfig();
}
