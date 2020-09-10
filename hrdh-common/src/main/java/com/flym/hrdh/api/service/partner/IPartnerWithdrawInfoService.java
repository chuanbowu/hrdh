package com.flym.hrdh.api.service.partner;

import com.flym.hrdh.pojo.partner.PartnerWithdrawInfo;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:合伙人提现明细管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface IPartnerWithdrawInfoService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    PartnerWithdrawInfo get(Long id);

    /**
     * 保存或编辑
     * @param partnerWithdrawInfo
     * @return
     */
    PartnerWithdrawInfo save(PartnerWithdrawInfo partnerWithdrawInfo);
}
