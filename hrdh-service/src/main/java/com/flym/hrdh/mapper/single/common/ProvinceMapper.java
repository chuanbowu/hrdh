package com.flym.hrdh.mapper.single.common;

import com.flym.hrdh.pojo.common.Province;

import java.util.List;

public interface ProvinceMapper {
    /**
     *
     * @mbggenerated 2020-05-12
     */
    int insert(Province record);

    /**
     *
     * @mbggenerated 2020-05-12
     */
    int insertSelective(Province record);

    /**
     * 获取全部省份
     * @return
     */
    List<Province> findProvinceList();
}