package com.flym.hrdh.mapper.single.common;

import com.flym.hrdh.pojo.common.GoodsNumConfig;

public interface GoodsNumConfigMapper {
    /**
     *
     * @mbggenerated 2020-07-17
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-07-17
     */
    int insert(GoodsNumConfig record);

    /**
     *
     * @mbggenerated 2020-07-17
     */
    int insertSelective(GoodsNumConfig record);

    /**
     *
     * @mbggenerated 2020-07-17
     */
    GoodsNumConfig selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-07-17
     */
    int updateByPrimaryKeySelective(GoodsNumConfig record);

    /**
     *
     * @mbggenerated 2020-07-17
     */
    int updateByPrimaryKey(GoodsNumConfig record);

    /**
     * 获取商品数量配置
     * @return
     */
    GoodsNumConfig getGoodsNumConfig();
}