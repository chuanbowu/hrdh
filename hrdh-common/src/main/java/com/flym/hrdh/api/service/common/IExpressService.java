package com.flym.hrdh.api.service.common;

import com.flym.hrdh.pojo.common.Express;

import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:快递公司管理</p>
 * <p>Copyright: Copyright (c) 2020-06-04</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface IExpressService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    Express get(Long id);

    /**
     * 保存或编辑
     * @param express
     * @return
     */
    Express save(Express express);

    /**
     * 获取快递公司列表
     * @return
     */
    List<Express> findExpressList();
}
