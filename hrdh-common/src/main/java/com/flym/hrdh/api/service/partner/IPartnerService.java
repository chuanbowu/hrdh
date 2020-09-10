package com.flym.hrdh.api.service.partner;

import com.flym.hrdh.pojo.partner.Partner;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:合伙人管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface IPartnerService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    Partner get(Long id);

    /**
     * 保存或编辑
     * @param partner
     * @return
     */
    Partner save(Partner partner);
}
