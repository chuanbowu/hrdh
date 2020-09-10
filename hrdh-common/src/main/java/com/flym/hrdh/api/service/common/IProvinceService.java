package com.flym.hrdh.api.service.common;

import com.flym.hrdh.pojo.common.Problem;
import com.flym.hrdh.pojo.common.Province;

import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:省份管理</p>
 * <p>Copyright: Copyright (c) 2020-05-12</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface IProvinceService {

    /**
     * 获取全部省份
     * @return
     */
    List<Province> findProvinceList();
}
