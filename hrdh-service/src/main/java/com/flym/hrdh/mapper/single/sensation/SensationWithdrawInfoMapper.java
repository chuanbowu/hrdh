package com.flym.hrdh.mapper.single.sensation;

import com.flym.hrdh.api.model.sensation.SensationWithdrawInfoVm;
import com.flym.hrdh.pojo.sensation.SensationWithdrawInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SensationWithdrawInfoMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(SensationWithdrawInfo record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(SensationWithdrawInfo record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    SensationWithdrawInfo selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(SensationWithdrawInfo record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(SensationWithdrawInfo record);

    /**
     * 根据红人ID获取提现明细总条数
     * @param sensationId
     * @return
     */
    int getSensationWithdrawInfoNum(@Param("sensationId") Long sensationId);

    /**
     * 根据红人ID获取提现明细列表
     * @param sensationId
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<SensationWithdrawInfo> findSensationWithdrawInfoList(@Param("sensationId") Long sensationId, @Param("beginNum") Integer beginNum,
                                                              @Param("pageSize") Integer pageSize);

    /**
     * 获取提现明细总条数
     * @param sensationId
     * @param status
     * @return
     */
    int getSensationWithdrawInfoVmNum(@Param("sensationId") Long sensationId, @Param("status") Integer status);

    /**
     * 获取提现明细列表
     * @param sensationId
     * @param status
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<SensationWithdrawInfoVm> findSensationWithdrawInfoVmList(@Param("sensationId") Long sensationId, @Param("status") Integer status,
                                                                  @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize);
}