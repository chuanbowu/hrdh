package com.flym.hrdh.mapper.single.common;

import com.flym.hrdh.pojo.common.Area;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AreaMapper {
    /**
     *
     * @mbggenerated 2020-05-12
     */
    int insert(Area record);

    /**
     *
     * @mbggenerated 2020-05-12
     */
    int insertSelective(Area record);

    /**
     * 根据城市ID获取区
     * @param cid
     * @return
     */
    List<Area> findAreaList(@Param("cid") String cid);
}