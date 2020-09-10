package com.flym.hrdh.service.system;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.mapper.single.system.SysRolePowerMapper;
import com.flym.hrdh.api.service.system.ISysRolePowerService;
import com.flym.hrdh.pojo.system.SysRolePower;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:角色权限关系管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class SysRolePowerServiceImpl implements ISysRolePowerService {

    @Autowired
    SysRolePowerMapper sysRolePowerMapper;

    @Override
    public SysRolePower get(Long id) {
        if(id == null || id <= 0){
            return  null;
        }
        return sysRolePowerMapper.selectByPrimaryKey(id);
    }

    @Override
    public SysRolePower save(SysRolePower sysRolePower) {

        if(sysRolePower.getId() == null || sysRolePower.getId() <= 0){
            sysRolePowerMapper.insert(sysRolePower);
        }else{
            sysRolePowerMapper.updateByPrimaryKeySelective(sysRolePower);
        }

        return sysRolePower;
    }

}
