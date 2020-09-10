package com.flym.hrdh.api.service.common;

import com.flym.hrdh.api.model.common.CommissionRecommendVm;
import com.flym.hrdh.pojo.common.CommissionRecommend;

import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:返佣推荐商品管理</p>
 * <p>Copyright: Copyright (c) 2020-05-13</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface ICommissionRecommendService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    CommissionRecommend get(Long id);

    /**
     * 保存或编辑
     * @param commissionRecommend
     * @return
     */
    CommissionRecommend save(CommissionRecommend commissionRecommend);

    /**
     * 获取返佣推荐商品列表
     * @param typeId
     * @return
     */
    List<CommissionRecommendVm> findCommissionRecommendVmList(Long typeId);

    /**
     * 获取返佣推荐商品列表
     * @return
     */
    List<CommissionRecommendVm> findCommissionRecommendVmTotalList();

    /**
     * 修改状态
     * @param commissionRecommendIds
     * @param status
     * @param userId
     * @param date
     * @return
     */
    int updateStatus(String commissionRecommendIds, Integer status, Long userId, Date date);
}
