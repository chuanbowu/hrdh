package com.flym.hrdh.service.system;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.mapper.single.system.SysUserRoleMapper;
import com.flym.hrdh.api.model.system.SysUserRoleVm;
import com.flym.hrdh.api.service.system.ISysUserRoleService;
import com.flym.hrdh.pojo.system.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:用户角色关系管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class SysUserRoleServiceImpl implements ISysUserRoleService {

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public SysUserRole get(Long id) {
        if(id == null || id <= 0){
            return  null;
        }
        return sysUserRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public SysUserRole save(SysUserRole sysUserRole) {

        if(sysUserRole.getId() == null || sysUserRole.getId() <= 0){
            sysUserRoleMapper.insert(sysUserRole);
        }else{
            sysUserRoleMapper.updateByPrimaryKeySelective(sysUserRole);
        }

        return sysUserRole;
    }

    @Override
    public List<SysUserRoleVm> findUserRole(Long sysUserId) {
        return sysUserRoleMapper.findUserRole(sysUserId);
    }

    /**
     * 根据角色id删除用户角色关系
     * @param sysRoleId 角色id
     * @return
     */
    @Override
    public void deleteBySysRoleId(Long sysRoleId){
        sysUserRoleMapper.deleteBySysRoleId(sysRoleId);
    };


    @Override
    public void addUserRole(List<SysUserRole> beans) {
        sysUserRoleMapper.addUserRole(beans);
    }

    @Override
    public int deleteByUserIds(Long userId) {
        return sysUserRoleMapper.deleteByUserIds(userId);
    }
}
