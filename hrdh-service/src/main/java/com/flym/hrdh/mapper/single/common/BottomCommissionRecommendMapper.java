package com.flym.hrdh.mapper.single.common;

import com.flym.hrdh.api.model.common.BottomCommissionRecommendVm;
import com.flym.hrdh.pojo.common.BottomCommissionRecommend;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BottomCommissionRecommendMapper {
    /**
     *
     * @mbggenerated 2020-05-13
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-05-13
     */
    int insert(BottomCommissionRecommend record);

    /**
     *
     * @mbggenerated 2020-05-13
     */
    int insertSelective(BottomCommissionRecommend record);

    /**
     *
     * @mbggenerated 2020-05-13
     */
    BottomCommissionRecommend selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-05-13
     */
    int updateByPrimaryKeySelective(BottomCommissionRecommend record);

    /**
     *
     * @mbggenerated 2020-05-13
     */
    int updateByPrimaryKey(BottomCommissionRecommend record);

    /**
     * 获取底部返佣推荐商品列表
     * @return
     */
    List<BottomCommissionRecommendVm> findBottomCommissionRecommendVmList();

    /**
     * 获取底部返佣推荐商品列表
     * @return
     */
    List<BottomCommissionRecommendVm> findBottomCommissionRecommendVmTotalList();

    /**
     * 修改状态
     * @param bottomCommissionRecommendIds
     * @param status
     * @param userId
     * @param date
     * @return
     */
    int updateStatus(@Param("bottomCommissionRecommendIds") String bottomCommissionRecommendIds, @Param("status") Integer status,
                     @Param("userId") Long userId, @Param("date") Date date);
}