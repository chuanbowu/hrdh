package com.flym.hrdh.api.service.partner;

import com.flym.hrdh.pojo.partner.PartnerAccountInfo;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:合伙人账户明细管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface IPartnerAccountInfoService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    PartnerAccountInfo get(Long id);

    /**
     * 保存或编辑
     * @param partnerAccountInfo
     * @return
     */
    PartnerAccountInfo save(PartnerAccountInfo partnerAccountInfo);
}
