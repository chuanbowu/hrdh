package com.flym.hrdh.service.system;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.mapper.single.system.SysUserMapper;
import com.flym.hrdh.api.service.system.ISysUserService;
import com.flym.hrdh.pojo.system.SysUser;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:平台用户管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class SysUserServiceImpl implements ISysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser get(Long id) {
        if(id == null){
            return null;
        }
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public SysUser save(SysUser sysUser) {
        if(sysUser.getId() != null && sysUser.getId() > 0){
            sysUserMapper.updateByPrimaryKey(sysUser);
        }else{
            sysUserMapper.insert(sysUser);
        }
        return sysUser;
    }

    @Override
    public SysUser loginUser(String userName, String password) {
        return sysUserMapper.loginUser(userName,password);
    }

    @Override
    public List<SysUser> findSysUser(int pageNum,int pageSize,String userName, String trueName,int status) {
        return sysUserMapper.findSysUser( pageNum, pageSize, userName, trueName,status);
    }

    @Override
    public void updateStatusByIds(List<Long> idList, int type, Long modifySysUser, Date modifySysDate) {
        sysUserMapper.updateStatusByIds(idList, type, modifySysUser, modifySysDate);
    }

    @Override
    public void updatePasswordByIds(List<Long> idList, String defaultPassword, Long modifySysUser, Date modifySysDate) {
        sysUserMapper.updatePasswordByIds(idList, defaultPassword, modifySysUser, modifySysDate);
    }

    @Override
    public int getSysUserTotalRow(String userName, String trueName,int status) {
        return sysUserMapper.getSysUserTotalRow(userName,  trueName, status);
    }

    @Override
    public SysUser randGetSysUserByRole(Long roleId){
        return sysUserMapper.randGetSysUserByRole(roleId);
    }

    @Override
    public int findSysUserNameNum(String userName, Long id) {
        return sysUserMapper.findSysUserNameNum(userName, id);
    }
}
