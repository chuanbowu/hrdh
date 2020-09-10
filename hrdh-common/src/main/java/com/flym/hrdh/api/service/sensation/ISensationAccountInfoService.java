package com.flym.hrdh.api.service.sensation;

import com.flym.hrdh.api.model.sensation.SensationAccountInfoVm;
import com.flym.hrdh.pojo.sensation.SensationAccountInfo;

import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:红人账户明细管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface ISensationAccountInfoService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    SensationAccountInfo get(Long id);

    /**
     * 保存或编辑
     * @param sensationAccountInfo
     * @return
     */
    SensationAccountInfo save(SensationAccountInfo sensationAccountInfo);

    /**
     * 获取统计
     * @param date
     * @return
     */
    SensationAccountInfoVm getStatistics(Date date);
}
