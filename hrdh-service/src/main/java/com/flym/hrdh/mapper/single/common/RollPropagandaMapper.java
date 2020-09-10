package com.flym.hrdh.mapper.single.common;

import com.flym.hrdh.pojo.common.RollPropaganda;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RollPropagandaMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(RollPropaganda record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(RollPropaganda record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    RollPropaganda selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(RollPropaganda record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(RollPropaganda record);

    /**
     * 随机获取列表
     * @param randNum
     * @return
     */
    List<RollPropaganda> findRollPropagandaRandList(@Param("randNum") Integer randNum);
}