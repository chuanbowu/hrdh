package com.flym.hrdh.mapper.single.common;

import com.flym.hrdh.api.model.common.AdvertVm;
import com.flym.hrdh.pojo.common.Advert;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AdvertMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(Advert record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(Advert record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    Advert selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(Advert record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(Advert record);

    /**
     * 获取轮播图列表
     * @return
     */
    List<Advert> findAdvertList();

    /**
     * 获取轮播图列表
     * @return
     */
    List<AdvertVm> findAdvertVmList();

    /**
     * 修改状态
     * @param advertIds
     * @param status
     * @param userId
     * @param date
     * @return
     */
    int updateStatus(@Param("advertIds") String advertIds, @Param("status") Integer status,
                     @Param("userId") Long userId, @Param("date") Date date);
}