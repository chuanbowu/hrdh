package com.flym.hrdh.mapper.single.sensation;

import com.flym.hrdh.api.model.sensation.SensationAccountInfoVm;
import com.flym.hrdh.pojo.sensation.SensationAccountInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface SensationAccountInfoMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(SensationAccountInfo record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(SensationAccountInfo record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    SensationAccountInfo selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(SensationAccountInfo record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(SensationAccountInfo record);

    /**
     * 获取统计
     * @param date
     * @return
     */
    SensationAccountInfoVm getStatistics(@Param("date") Date date);
}