package com.flym.hrdh.mapper.single.common;

import com.flym.hrdh.api.model.common.AgentVm;
import com.flym.hrdh.pojo.common.Agent;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AgentMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(Agent record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(Agent record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    Agent selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(Agent record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(Agent record);

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
    int updateStatus(@Param("agentIds") String agentIds, @Param("status") Integer status,
                     @Param("userId") Long userId, @Param("date") Date date);
}