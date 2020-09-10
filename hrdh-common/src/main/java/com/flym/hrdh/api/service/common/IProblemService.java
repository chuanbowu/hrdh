package com.flym.hrdh.api.service.common;

import com.flym.hrdh.api.model.common.ProblemVm;
import com.flym.hrdh.pojo.common.Problem;

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
public interface IProblemService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    Problem get(Long id);

    /**
     * 保存或编辑
     * @param problem
     * @return
     */
    Problem save(Problem problem);

    /**
     * 获取常见问题配置列表
     * @return
     */
    List<Problem> findProblemList();

    /**
     * 获取常见问题配置列表
     * @return
     */
    List<ProblemVm> findProblemVmList();

    /**
     * 修改状态
     * @param problemIds
     * @param status
     * @param userId
     * @param date
     * @return
     */
    int updateStatus(String problemIds, Integer status, Long userId, Date date);
}
