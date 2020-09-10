package com.flym.hrdh.api.service.sensation;

import com.flym.hrdh.pojo.sensation.SensationAlipay;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:红人支付宝账号管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface ISensationAlipayService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    SensationAlipay get(Long id);

    /**
     * 保存或编辑
     * @param sensationAlipay
     * @return
     */
    SensationAlipay save(SensationAlipay sensationAlipay);
}
