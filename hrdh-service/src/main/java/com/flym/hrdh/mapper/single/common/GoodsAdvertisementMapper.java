package com.flym.hrdh.mapper.single.common;

import com.flym.hrdh.api.model.common.GoodsAdvertisementVm;
import com.flym.hrdh.pojo.common.GoodsAdvertisement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsAdvertisementMapper {
    /**
     *
     * @mbggenerated 2020-06-22
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-06-22
     */
    int insert(GoodsAdvertisement record);

    /**
     *
     * @mbggenerated 2020-06-22
     */
    int insertSelective(GoodsAdvertisement record);

    /**
     *
     * @mbggenerated 2020-06-22
     */
    GoodsAdvertisement selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-06-22
     */
    int updateByPrimaryKeySelective(GoodsAdvertisement record);

    /**
     *
     * @mbggenerated 2020-06-22
     */
    int updateByPrimaryKey(GoodsAdvertisement record);

    /**
     * 获取广告列表
     * @return
     */
    List<GoodsAdvertisement> findGoodsAdvertisementVmList(@Param("type") Integer type);

    /**
     * 获取广告列表
     * @return
     */
    List<GoodsAdvertisementVm> findGoodsAdvertisementVmTotalList();
}