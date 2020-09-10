package com.flym.hrdh.mapper.single.common;

import com.flym.hrdh.api.model.common.CommissionRecommendVm;
import com.flym.hrdh.pojo.common.CommissionRecommend;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CommissionRecommendMapper {
    /**
     *
     * @mbggenerated 2020-05-13
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-05-13
     */
    int insert(CommissionRecommend record);

    /**
     *
     * @mbggenerated 2020-05-13
     */
    int insertSelective(CommissionRecommend record);

    /**
     *
     * @mbggenerated 2020-05-13
     */
    CommissionRecommend selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-05-13
     */
    int updateByPrimaryKeySelective(CommissionRecommend record);

    /**
     *
     * @mbggenerated 2020-05-13
     */
    int updateByPrimaryKey(CommissionRecommend record);

    /**
     * 获取返佣推荐商品列表
     * @param typeId
     * @return
     */
    List<CommissionRecommendVm> findCommissionRecommendVmList(@Param("typeId") Long typeId);

    /**
     * 获取返佣推荐商品列表
     * @return
     */
    List<CommissionRecommendVm> findCommissionRecommendVmTotalList();

    /**
     * 修改状态
     * @param commissionRecommendIds
     * @param status
     * @param userId
     * @param date
     * @return
     */
    int updateStatus(@Param("commissionRecommendIds") String commissionRecommendIds, @Param("status") Integer status,
                     @Param("userId") Long userId, @Param("date") Date date);
}