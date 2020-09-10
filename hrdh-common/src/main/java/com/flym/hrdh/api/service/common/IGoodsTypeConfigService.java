package com.flym.hrdh.api.service.common;

import com.flym.hrdh.api.model.common.GoodsTypeConfigVm;
import com.flym.hrdh.pojo.common.GoodsNumConfig;
import com.flym.hrdh.pojo.common.GoodsTypeConfig;

import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:商品分类配置管理</p>
 * <p>Copyright: Copyright (c) 2020-07-21</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface IGoodsTypeConfigService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    GoodsTypeConfig get(Long id);

    /**
     * 保存或编辑
     * @param goodsTypeConfig
     * @return
     */
    GoodsTypeConfig save(GoodsTypeConfig goodsTypeConfig);

    /**
     * 获取商品数量配置
     * @return
     */
    List<GoodsTypeConfigVm> finGoodsNumConfigList();

    /**
     * 修改状态
     * @param ids
     * @param status
     * @param userId
     * @param date
     * @return
     */
    int updateStatus(String ids, Integer status, Long userId, Date date);
}
