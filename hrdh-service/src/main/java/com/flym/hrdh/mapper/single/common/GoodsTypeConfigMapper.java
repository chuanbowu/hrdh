package com.flym.hrdh.mapper.single.common;

import com.flym.hrdh.api.model.common.GoodsTypeConfigVm;
import com.flym.hrdh.pojo.common.GoodsTypeConfig;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface GoodsTypeConfigMapper {
    /**
     *
     * @mbggenerated 2020-07-17
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-07-17
     */
    int insert(GoodsTypeConfig record);

    /**
     *
     * @mbggenerated 2020-07-17
     */
    int insertSelective(GoodsTypeConfig record);

    /**
     *
     * @mbggenerated 2020-07-17
     */
    GoodsTypeConfig selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-07-17
     */
    int updateByPrimaryKeySelective(GoodsTypeConfig record);

    /**
     *
     * @mbggenerated 2020-07-17
     */
    int updateByPrimaryKey(GoodsTypeConfig record);

    /**
     * 获取商品数量配置
     * @return
     */
    List<GoodsTypeConfigVm> finGoodsNumConfigList();

    /**
     * 修改状态
     * @param ids
     * @param status
     * @param userId
     * @param date
     * @return
     */
    int updateStatus(@Param("ids") String ids, @Param("status") Integer status, @Param("userId") Long userId, @Param("date") Date date);
}