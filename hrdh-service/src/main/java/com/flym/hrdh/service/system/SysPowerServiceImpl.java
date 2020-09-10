package com.flym.hrdh.service.system;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.mapper.single.system.SysPowerMapper;
import com.flym.hrdh.api.service.system.ISysPowerService;
import com.flym.hrdh.pojo.system.SysPower;

import javax.annotation.Resource;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:系统权限管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class SysPowerServiceImpl implements ISysPowerService {

    @Resource
    private SysPowerMapper selectByPrimaryKey;

    @Override
    public SysPower get(Long id) {
        if(id == null){
            return null;
        }
        return selectByPrimaryKey.selectByPrimaryKey(id);
    }

    @Override
    public SysPower save(SysPower sysPower) {
        if(sysPower.getId() != null && sysPower.getId() > 0) {
            selectByPrimaryKey.updateByPrimaryKey(sysPower);
        }else{
            selectByPrimaryKey.insert(sysPower);
        }
        return sysPower;
    }

}
