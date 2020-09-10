package com.flym.hrdh.service.system;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.mapper.single.system.SysRoleMapper;
import com.flym.hrdh.api.model.system.SysRoleVm;
import com.flym.hrdh.api.service.system.ISysRoleService;
import com.flym.hrdh.pojo.system.SysRole;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:系统角色管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class SysRoleServiceImpl implements ISysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public SysRole get(Long sysroleId) {
        if(sysroleId == null){
            return null;
        }
        return sysRoleMapper.selectByPrimaryKey(sysroleId);
    }

    @Override
    public SysRole save(SysRole sysRole) {
        if(sysRole.getId() != null && sysRole.getId() > 0){
            sysRoleMapper.updateByPrimaryKey(sysRole);
        }else{
            sysRoleMapper.insert(sysRole);
        }
        return sysRole;
    }

    @Override
    public List<SysRole> findSysRoleByLimit(String roleName, String roleRemark, int beginNum, int pageSize) {
        return sysRoleMapper.findSysRoleByLimit( roleName, roleRemark, beginNum, pageSize);
    }

    @Override
    public Long getValidSysRoleNum(String roleName, String roleRemark) {
        return sysRoleMapper.getValidSysRoleNum( roleName, roleRemark);
    }

    /**
     * 查询角色名是否存在
     * @return
     */
    @Override
    public int findRoleNameExist(String roleName){
        return sysRoleMapper.findRoleNameExist(roleName);
    };

    /**
     * 根据角色IDs 更新角色状态
     * @param idList 角色id集合
     * @param status 状态
     * @param modifySysUser 修改人
     * @param modifyDate 修改时间
     * @return
     */
    @Override
    public void updateStatusByIds(List<Long> idList, int status, Long modifySysUser, Date modifyDate) {
        sysRoleMapper.updateStatusByIds(idList, status, modifySysUser, modifyDate);
    }

    @Override
    public List<SysRoleVm> findSysUserRoleListByUserId(Long userId) {
        return sysRoleMapper.findSysUserRoleListByUserId(userId);
    }
}

