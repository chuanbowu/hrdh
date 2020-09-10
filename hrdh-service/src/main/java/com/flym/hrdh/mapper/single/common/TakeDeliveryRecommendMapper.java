package com.flym.hrdh.mapper.single.common;

import com.flym.hrdh.api.model.common.TakeDeliveryRecommendVm;
import com.flym.hrdh.pojo.common.TakeDeliveryRecommend;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TakeDeliveryRecommendMapper {
    /**
     *
     * @mbggenerated 2020-05-13
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-05-13
     */
    int insert(TakeDeliveryRecommend record);

    /**
     *
     * @mbggenerated 2020-05-13
     */
    int insertSelective(TakeDeliveryRecommend record);

    /**
     *
     * @mbggenerated 2020-05-13
     */
    TakeDeliveryRecommend selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-05-13
     */
    int updateByPrimaryKeySelective(TakeDeliveryRecommend record);

    /**
     *
     * @mbggenerated 2020-05-13
     */
    int updateByPrimaryKey(TakeDeliveryRecommend record);

    /**
     * 获取拿货推荐商品列表
     * @return
     */
    List<TakeDeliveryRecommendVm> findTakeDeliveryRecommendVmList(@Param("typeId") Long typeId);

    /**
     * 获取拿货推荐商品列表
     * @return
     */
    List<TakeDeliveryRecommendVm> findTakeDeliveryRecommendVmTotalList();

    /**
     * 修改状态
     * @param takeDeliveryRecommendIds
     * @param status
     * @param userId
     * @param date
     * @return
     */
    int updateStatus(@Param("takeDeliveryRecommendIds") String takeDeliveryRecommendIds, @Param("status") Integer status,
                     @Param("userId") Long userId, @Param("date") Date date);
}