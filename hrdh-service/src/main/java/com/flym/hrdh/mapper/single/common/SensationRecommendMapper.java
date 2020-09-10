package com.flym.hrdh.mapper.single.common;

import com.flym.hrdh.api.model.common.SensationRecommendVm;
import com.flym.hrdh.pojo.common.SensationRecommend;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SensationRecommendMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(SensationRecommend record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(SensationRecommend record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    SensationRecommend selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(SensationRecommend record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(SensationRecommend record);

    /**
     * 获取红人推荐列表
     * @return
     */
    List<SensationRecommendVm> findSensationRecommendVmList();

    /**
     * 修改状态
     * @param sensationRecommendIds
     * @param status
     * @param userId
     * @param date
     * @return
     */
    int updateStatus(@Param("sensationRecommendIds") String sensationRecommendIds, @Param("status") Integer status,
                     @Param("userId") Long userId, @Param("date") Date date);
}