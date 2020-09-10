package com.flym.hrdh.api.service.common;

import com.flym.hrdh.api.model.common.GoodsAdvertisementVm;
import com.flym.hrdh.pojo.common.GoodsAdvertisement;

import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:商品广告管理</p>
 * <p>Copyright: Copyright (c) 2020-06-22</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface IGoodsAdvertisementService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    GoodsAdvertisement get(Long id);

    /**
     * 保存或编辑
     * @param goodsAdvertisement
     * @return
     */
    GoodsAdvertisement save(GoodsAdvertisement goodsAdvertisement);

    /**
     * 获取广告列表
     * @return
     */
    List<GoodsAdvertisement> findGoodsAdvertisementVmList(Integer type);

    /**
     * 获取广告列表
     * @return
     */
    List<GoodsAdvertisementVm> findGoodsAdvertisementVmTotalList();
}
