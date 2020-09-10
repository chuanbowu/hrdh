package com.flym.hrdh.api.service.sensation;

import com.flym.hrdh.api.model.sensation.SensationCheckVm;
import com.flym.hrdh.pojo.sensation.SensationCheck;

import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:红人认证审核管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface ISensationCheckService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    SensationCheck get(Long id);

    /**
     * 保存或编辑
     * @param sensationCheck
     * @return
     */
    SensationCheck save(SensationCheck sensationCheck);

    /**
     * 根据红人ID获取审核信息
     * @param sensationId
     * @return
     */
    SensationCheck getSensationCheckBySensation(Long sensationId);

    /**
     * 获取红人认证审核列表
     * @param sensationId
     * @param checkStatus
     * @return
     */
    int getSensationCheckVmNum(Long sensationId, Integer checkStatus);

    /**
     * 获取红人认证审核总条数
     * @param sensationId
     * @param checkStatus
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<SensationCheckVm> findSensationCheckVmList(Long sensationId, Integer checkStatus, Integer beginNum, Integer pageSize);
}
