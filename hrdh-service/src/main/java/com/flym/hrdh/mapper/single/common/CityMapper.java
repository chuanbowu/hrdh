package com.flym.hrdh.mapper.single.common;

import com.flym.hrdh.pojo.common.City;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CityMapper {
    /**
     *
     * @mbggenerated 2020-05-12
     */
    int insert(City record);

    /**
     *
     * @mbggenerated 2020-05-12
     */
    int insertSelective(City record);

    /**
     * 根据省份ID获取城市列表
     * @param cid
     * @return
     */
    List<City> findCityList(@Param("cid") String cid);
}