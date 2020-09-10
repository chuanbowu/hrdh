package com.flym.hrdh.api.service.common;

import com.flym.hrdh.pojo.common.RollPropaganda;

import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:滚动宣传管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface IRollPropagandaService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    RollPropaganda get(Long id);

    /**
     * 保存或编辑
     * @param rollPropaganda
     * @return
     */
    RollPropaganda save(RollPropaganda rollPropaganda);

    /**
     * 随机获取列表
     * @param randNum
     * @return
     */
    List<RollPropaganda> findRollPropagandaRandList(Integer randNum);
}
