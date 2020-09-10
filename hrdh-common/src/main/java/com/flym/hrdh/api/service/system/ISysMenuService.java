package com.flym.hrdh.api.service.system;

import com.flym.hrdh.api.model.system.SysMenuVm;
import com.flym.hrdh.pojo.system.SysMenu;

import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:系统菜单管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface ISysMenuService {

    /**
     * 根据ID获取信息
     * @author xpf
     * @param id
     * @return
     */
    SysMenu get(Long id);

    /**
     * 保存信息
     * @author xpf
     * @param sysMenu
     */
    SysMenu save(SysMenu sysMenu);

    /**
     * 根据角色ID和父级ID获取列表
     * @param roleId
     * @return
     */
    List<SysMenuVm> findSysMenuListByRoleId(Long roleId, Long parentId);
}
