package com.flym.hrdh.api.service.common;

import com.flym.hrdh.pojo.common.Area;

import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:区管理</p>
 * <p>Copyright: Copyright (c) 2020-05-12</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface IAreaService {

    /**
     * 根据城市ID获取区
     * @param cid
     * @return
     */
    List<Area> findAreaList(String cid);
}
