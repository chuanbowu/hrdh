package com.flym.hrdh.service.common;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.model.common.AgentVm;
import com.flym.hrdh.api.service.common.IAgentService;
import com.flym.hrdh.mapper.single.common.AgentMapper;
import com.flym.hrdh.pojo.common.Agent;

import javax.annotation.Resource;
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
@Service(version = "1.0.0")
public class AgentServiceImpl implements IAgentService {

	@Resource
	private AgentMapper agentMapper;

	@Override
	public Agent get(Long id) {
		if (id == null) {
			return null;
		}
		return agentMapper.selectByPrimaryKey(id);
	}

	@Override
	public Agent save(Agent agent) {
		if(agent.getId() != null && agent.getId() > 0){
			agentMapper.updateByPrimaryKeySelective(agent);
		}else{
			agentMapper.insert(agent);
		}
		return agent;
	}

	@Override
	public List<Agent> findAgentList() {
		return agentMapper.findAgentList();
	}

	@Override
	public List<AgentVm> findAgentVmList() {
		return agentMapper.findAgentVmList();
	}

	@Override
	public int updateStatus(String agentIds, Integer status, Long userId, Date date) {
		return agentMapper.updateStatus(agentIds, status, userId, date);
	}

}
