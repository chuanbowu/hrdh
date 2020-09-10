package com.flym.hrdh.mapper.single.common;

import com.flym.hrdh.api.model.common.AdvertisementVm;
import com.flym.hrdh.pojo.common.Advertisement;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AdvertisementMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(Advertisement record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(Advertisement record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    Advertisement selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(Advertisement record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(Advertisement record);

    /**
     * 获取广告列表
     * @return
     */
    List<AdvertisementVm> findAdvertisementVmList();

    /**
     * 获取广告列表
     * @return
     */
    List<AdvertisementVm> findAdvertisementVmTotalList();

    /**
     * 修改状态
     * @param advertisementIds
     * @param status
     * @param userId
     * @param date
     * @return
     */
    int updateStatus(@Param("advertisementIds") String advertisementIds, @Param("status") Integer status,
                     @Param("userId") Long userId, @Param("date") Date date);
}