package com.flym.hrdh.mapper.single.common;

import com.flym.hrdh.pojo.common.SamplingPropaganda;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SamplingPropagandaMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(SamplingPropaganda record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(SamplingPropaganda record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    SamplingPropaganda selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(SamplingPropaganda record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(SamplingPropaganda record);

    /**
     * 随机获取列表
     * @param randNum
     * @return
     */
    List<SamplingPropaganda> findSamplingPropagandaRandList(@Param("randNum") Integer randNum);
}