package com.flym.hrdh.service.common;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.model.common.ProblemVm;
import com.flym.hrdh.api.service.common.IProblemService;
import com.flym.hrdh.mapper.single.common.ProblemMapper;
import com.flym.hrdh.pojo.common.Problem;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:常见问题配置管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class ProblemServiceImpl implements IProblemService {

	@Resource
	private ProblemMapper problemMapper;

	@Override
	public Problem get(Long id) {
		if (id == null) {
			return null;
		}
		return problemMapper.selectByPrimaryKey(id);
	}

	@Override
	public Problem save(Problem problem) {
		if(problem.getId() != null && problem.getId() > 0){
			problemMapper.updateByPrimaryKeySelective(problem);
		}else{
			problemMapper.insert(problem);
		}
		return problem;
	}

	@Override
	public List<Problem> findProblemList() {
		return problemMapper.findProblemList();
	}

	@Override
	public List<ProblemVm> findProblemVmList() {
		return problemMapper.findProblemVmList();
	}

	@Override
	public int updateStatus(String problemIds, Integer status, Long userId, Date date) {
		return problemMapper.updateStatus(problemIds, status, userId, date);
	}

}
