package com.flym.hrdh.api.service.common;

import com.flym.hrdh.pojo.common.SamplingPropaganda;

import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:领样宣传管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface ISamplingPropagandaService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    SamplingPropaganda get(Long id);

    /**
     * 保存或编辑
     * @param samplingPropaganda
     * @return
     */
    SamplingPropaganda save(SamplingPropaganda samplingPropaganda);

    /**
     * 随机获取列表
     * @param randNum
     * @return
     */
    List<SamplingPropaganda> findSamplingPropagandaRandList(Integer randNum);
}
