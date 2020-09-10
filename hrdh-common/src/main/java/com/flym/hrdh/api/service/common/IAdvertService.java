package com.flym.hrdh.api.service.common;

import com.flym.hrdh.api.model.common.AdvertVm;
import com.flym.hrdh.pojo.common.Advert;

import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:轮播图管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface IAdvertService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    Advert get(Long id);

    /**
     * 保存或编辑
     * @param advert
     * @return
     */
    Advert save(Advert advert);

    /**
     * 获取轮播图列表
     * @return
     */
    List<Advert> findAdvertList();

    /**
     * 获取轮播图列表
     * @return
     */
    List<AdvertVm> findAdvertVmList();

    /**
     * 修改状态
     * @param advertIds
     * @param status
     * @param userId
     * @param date
     * @return
     */
    int updateStatus(String advertIds, Integer status, Long userId, Date date);
}
