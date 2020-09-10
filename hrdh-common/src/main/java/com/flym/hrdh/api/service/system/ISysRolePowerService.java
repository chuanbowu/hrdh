package com.flym.hrdh.api.service.system;

import com.flym.hrdh.pojo.system.SysRolePower;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:角色权限关系管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface ISysRolePowerService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    SysRolePower get(Long id);

    /**
     * 保存或编辑
     * @param sysRolePower
     * @return
     */
    SysRolePower save(SysRolePower sysRolePower);

}
