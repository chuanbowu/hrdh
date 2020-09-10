package com.flym.hrdh.api.service.common;

import com.flym.hrdh.api.model.common.AgentVm;
import com.flym.hrdh.pojo.common.Agent;

import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:经纪人管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface IAgentService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    Agent get(Long id);

    /**
     * 保存或编辑
     * @param agent
     * @return
     */
    Agent save(Agent agent);

    /**
     * 获取经纪人
     * @return
     */
    List<Agent> findAgentList();

    /**
     * 获取经纪人列表
     * 获取经纪人列表
     * @return
     */
    List<AgentVm> findAgentVmList();

    /**
     * 修改状态
     * @param agentIds
     * @param status
     * @param userId
     * @param date
     * @return
     */
    int updateStatus(String agentIds, Integer status, Long userId, Date date);
}
