package com.flym.hrdh.service.system;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.mapper.single.system.SysMenuMapper;
import com.flym.hrdh.api.model.system.SysMenuVm;
import com.flym.hrdh.api.service.system.ISysMenuService;
import com.flym.hrdh.pojo.system.SysMenu;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:系统菜单管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class SysMenuServiceImpl implements ISysMenuService {

	@Resource
	private SysMenuMapper sysMenuMapper;

	@Override
	public SysMenu get(Long id) {
		if (id == null) {
			return null;
		}
		return sysMenuMapper.selectByPrimaryKey(id);
	}

	@Override
	public SysMenu save(SysMenu sysMenu) {
		if(sysMenu.getId() != null && sysMenu.getId() > 0){
			sysMenuMapper.updateByPrimaryKey(sysMenu);
		}else{
			sysMenuMapper.insert(sysMenu);
		}
		return sysMenu;
	}

	@Override
	public List<SysMenuVm> findSysMenuListByRoleId(Long roleId, Long parentId) {
		return sysMenuMapper.findSysMenuListByRoleId(roleId, parentId);
	}
}
