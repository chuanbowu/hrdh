package com.flym.hrdh.api.service.common;

import com.flym.hrdh.api.model.common.AdvertisementVm;
import com.flym.hrdh.pojo.common.Advertisement;

import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:广告管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface IAdvertisementService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    Advertisement get(Long id);

    /**
     * 保存或编辑
     * @param advertisement
     * @return
     */
    Advertisement save(Advertisement advertisement);

    /**
     * 获取广告列表
     * @return
     */
    List<AdvertisementVm> findAdvertisementVmList();

    /**
     * 获取广告列表
     * @return
     */
    List<AdvertisementVm> findAdvertisementVmTotalList();

    /**
     * 修改状态
     * @param advertisementIds
     * @param status
     * @param userId
     * @param date
     * @return
     */
    int updateStatus(String advertisementIds, Integer status, Long userId, Date date);
}
