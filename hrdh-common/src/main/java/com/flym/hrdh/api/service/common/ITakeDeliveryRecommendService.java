package com.flym.hrdh.api.service.common;

import com.flym.hrdh.api.model.common.TakeDeliveryRecommendVm;
import com.flym.hrdh.pojo.common.TakeDeliveryRecommend;

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
public interface ITakeDeliveryRecommendService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    TakeDeliveryRecommend get(Long id);

    /**
     * 保存或编辑
     * @param takeDeliveryRecommend
     * @return
     */
    TakeDeliveryRecommend save(TakeDeliveryRecommend takeDeliveryRecommend);

    /**
     * 获取拿货推荐商品列表
     * @return
     */
    List<TakeDeliveryRecommendVm> findTakeDeliveryRecommendVmList(Long typeId);

    /**
     * 获取拿货推荐商品列表
     * @return
     */
    List<TakeDeliveryRecommendVm> findTakeDeliveryRecommendVmTotalList();

    /**
     * 修改状态
     * @param takeDeliveryRecommendIds
     * @param status
     * @param userId
     * @param date
     * @return
     */
    int updateStatus(String takeDeliveryRecommendIds, Integer status, Long userId, Date date);
}
