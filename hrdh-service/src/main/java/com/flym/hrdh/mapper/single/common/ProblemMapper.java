package com.flym.hrdh.mapper.single.common;

import com.flym.hrdh.api.model.common.ProblemVm;
import com.flym.hrdh.pojo.common.Problem;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ProblemMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(Problem record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(Problem record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    Problem selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(Problem record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(Problem record);

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
    int updateStatus(@Param("problemIds") String problemIds, @Param("status") Integer status,
                     @Param("userId") Long userId, @Param("date") Date date);
}