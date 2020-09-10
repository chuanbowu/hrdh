package com.flym.hrdh.mapper.single.common;

import com.flym.hrdh.pojo.common.Express;

import java.util.List;

public interface ExpressMapper {
    /**
     *
     * @mbggenerated 2020-06-04
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-06-04
     */
    int insert(Express record);

    /**
     *
     * @mbggenerated 2020-06-04
     */
    int insertSelective(Express record);

    /**
     *
     * @mbggenerated 2020-06-04
     */
    Express selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-06-04
     */
    int updateByPrimaryKeySelective(Express record);

    /**
     *
     * @mbggenerated 2020-06-04
     */
    int updateByPrimaryKey(Express record);

    /**
     * 获取快递公司列表
     * @return
     */
    List<Express> findExpressList();
}