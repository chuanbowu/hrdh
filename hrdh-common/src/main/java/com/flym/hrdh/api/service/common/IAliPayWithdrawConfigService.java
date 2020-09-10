package com.flym.hrdh.api.service.common;

import com.flym.hrdh.pojo.common.AliPayWithdrawConfig;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:支付宝自动提现配置管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface IAliPayWithdrawConfigService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    AliPayWithdrawConfig get(Long id);

    /**
     * 保存或编辑
     * @param aliPayWithdrawConfig
     * @return
     */
    AliPayWithdrawConfig save(AliPayWithdrawConfig aliPayWithdrawConfig);

    /**
     * 获取自动提现配置
     * @return
     */
    AliPayWithdrawConfig getNormal();
}
