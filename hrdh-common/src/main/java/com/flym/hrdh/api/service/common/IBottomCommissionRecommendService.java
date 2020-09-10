package com.flym.hrdh.api.service.common;

import com.flym.hrdh.api.model.common.BottomCommissionRecommendVm;
import com.flym.hrdh.pojo.common.BottomCommissionRecommend;

import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:底部返佣推荐商品管理</p>
 * <p>Copyright: Copyright (c) 2020-05-13</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface IBottomCommissionRecommendService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    BottomCommissionRecommend get(Long id);

    /**
     * 保存或编辑
     * @param bottomCommissionRecommend
     * @return
     */
    BottomCommissionRecommend save(BottomCommissionRecommend bottomCommissionRecommend);

    /**
     * 获取底部返佣推荐商品列表
     * @return
     */
    List<BottomCommissionRecommendVm> findBottomCommissionRecommendVmList();

    /**
     * 获取底部返佣推荐商品列表
     * @return
     */
    List<BottomCommissionRecommendVm> findBottomCommissionRecommendVmTotalList();

    /**
     * 修改状态
     * @param bottomCommissionRecommendIds
     * @param status
     * @param userId
     * @param date
     * @return
     */
    int updateStatus(String bottomCommissionRecommendIds, Integer status, Long userId, Date date);
}
