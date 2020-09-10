package com.flym.hrdh.api.service.system;

import com.flym.hrdh.pojo.system.SysPower;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:系统权限管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x$
 * @version $Revision: 1.0.0 $
 */
public interface ISysPowerService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    SysPower get(Long id);

    /**
     * 保存信息
     * @param sysPower
     */
    SysPower save(SysPower sysPower);
}
