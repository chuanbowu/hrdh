package com.flym.hrdh.service.system;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.model.system.SysRoleMenuVm;
import com.flym.hrdh.api.service.system.ISysRoleMenuService;
import com.flym.hrdh.pojo.system.SysRoleMenu;
import com.flym.hrdh.mapper.single.system.SysRoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:角色菜单关系管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class SysRoleMenuServiceImpl implements ISysRoleMenuService {

    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public SysRoleMenu get(Long id) {
        if(id == null || id <= 0){
            return  null;
        }
        return sysRoleMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public SysRoleMenu save(SysRoleMenu sysRoleMenu) {

        if(sysRoleMenu.getId() == null || sysRoleMenu.getId() <= 0){
            sysRoleMenuMapper.insert(sysRoleMenu);
        }else{
            sysRoleMenuMapper.updateByPrimaryKeySelective(sysRoleMenu);
        }

        return sysRoleMenu;
    }

    @Override
    public List<SysRoleMenuVm> findTopRoleMenu(Long userId) {
        return sysRoleMenuMapper.findTopRoleMenu(userId);
    }

    @Override
    public List<SysRoleMenuVm> findTowRoleMenu(Long parentId, Long userId) {
        return sysRoleMenuMapper.findTowRoleMenu(parentId, userId);
    }

    /**
     * 根据角色id删除角色菜单关系
     * @param sysRoleId 角色id
     * @return
     */
    @Override
    public void deleteBySysRoleId(Long sysRoleId){
        sysRoleMenuMapper.deleteBySysRoleId(sysRoleId);
    }

    @Override
    public void addRoleMenu(List<SysRoleMenu> beans) {
        sysRoleMenuMapper.addRoleMenu(beans);
    }
}
