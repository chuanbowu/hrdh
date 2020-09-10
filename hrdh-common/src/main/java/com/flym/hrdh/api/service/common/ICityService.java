package com.flym.hrdh.api.service.common;

import com.flym.hrdh.pojo.common.City;

import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:城市管理</p>
 * <p>Copyright: Copyright (c) 2020-05-12</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface ICityService {

    /**
     * 根据省份ID获取城市列表
     * @param cid
     * @return
     */
    List<City> findCityList(String cid);
}
