package com.flym.hrdh.mapper.single.sensation;

import com.flym.hrdh.api.model.sensation.SensationCheckVm;
import com.flym.hrdh.pojo.sensation.SensationCheck;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SensationCheckMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(SensationCheck record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(SensationCheck record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    SensationCheck selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(SensationCheck record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(SensationCheck record);

    /**
     * 根据红人ID获取审核信息
     * @param sensationId
     * @return
     */
    SensationCheck getSensationCheckBySensation(@Param("sensationId") Long sensationId);

    /**
     * 获取红人认证审核列表
     * @param sensationId
     * @param checkStatus
     * @return
     */
    int getSensationCheckVmNum(@Param("sensationId") Long sensationId, @Param("checkStatus") Integer checkStatus);

    /**
     * 获取红人认证审核总条数
     * @param sensationId
     * @param checkStatus
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<SensationCheckVm> findSensationCheckVmList(@Param("sensationId") Long sensationId, @Param("checkStatus") Integer checkStatus,
                                                    @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize);
}