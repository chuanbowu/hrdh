package com.flym.hrdh.api.service.sensation;

import com.flym.hrdh.api.model.sensation.SensationWithdrawInfoVm;
import com.flym.hrdh.pojo.sensation.SensationWithdrawInfo;

import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:红人提现明细管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface ISensationWithdrawInfoService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    SensationWithdrawInfo get(Long id);

    /**
     * 保存或编辑
     * @param sensationWithdrawInfo
     * @return
     */
    SensationWithdrawInfo save(SensationWithdrawInfo sensationWithdrawInfo);

    /**
     * 根据红人ID获取提现明细总条数
     * @param sensationId
     * @return
     */
    int getSensationWithdrawInfoNum(Long sensationId);

    /**
     * 根据红人ID获取提现明细列表
     * @param sensationId
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<SensationWithdrawInfo> findSensationWithdrawInfoList(Long sensationId, Integer beginNum, Integer pageSize);

    /**
     * 获取提现明细总条数
     * @param sensationId
     * @param status
     * @return
     */
    int getSensationWithdrawInfoVmNum(Long sensationId, Integer status);

    /**
     * 获取提现明细列表
     * @param sensationId
     * @param status
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<SensationWithdrawInfoVm> findSensationWithdrawInfoVmList(Long sensationId, Integer status, Integer beginNum, Integer pageSize);
}
