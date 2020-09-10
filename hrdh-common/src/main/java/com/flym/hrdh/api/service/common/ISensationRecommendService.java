package com.flym.hrdh.api.service.common;

import com.flym.hrdh.api.model.common.SensationRecommendVm;
import com.flym.hrdh.pojo.common.SensationRecommend;

import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:红人推荐管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface ISensationRecommendService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    SensationRecommend get(Long id);

    /**
     * 保存或编辑
     * @param sensationRecommend
     * @return
     */
    SensationRecommend save(SensationRecommend sensationRecommend);

    /**
     * 获取红人推荐列表
     * @return
     */
    List<SensationRecommendVm> findSensationRecommendVmList();

    /**
     * 修改状态
     * @param sensationRecommendIds
     * @param status
     * @param userId
     * @param date
     * @return
     */
    int updateStatus(String sensationRecommendIds, Integer status, Long userId, Date date);
}
