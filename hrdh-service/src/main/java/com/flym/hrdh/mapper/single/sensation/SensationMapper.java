package com.flym.hrdh.mapper.single.sensation;

import com.flym.hrdh.api.model.sensation.SensationVm;
import com.flym.hrdh.pojo.sensation.Sensation;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SensationMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(Sensation record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(Sensation record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    Sensation selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(Sensation record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(Sensation record);

    /**
     * 根据红人手机号码获取条数
     * @param phone
     * @return
     */
    int getSensationPhoneNum(@Param("phone") String phone);

    /**
     * 根据手机号码和密码获取信息
     * @param phone
     * @param password
     * @return
     */
    Sensation findSensationByPhoneNoPassword(@Param("phone") String phone, @Param("password") String password);

    /**
     * 根据类型获取红人列表
     * @param contentType
     * @param followersType
     * @param followersSpreadType
     * @param totalLikedType
     * @param provinceId
     * @param extensionType
     * @return
     */
    List<SensationVm> findSensationVmListByType(@Param("contentType") Integer contentType, @Param("followersType") Integer followersType,
                                                @Param("followersSpreadType") Integer followersSpreadType, @Param("totalLikedType") Integer totalLikedType,
                                                @Param("provinceId") Long provinceId, @Param("extensionType") String[] extensionType,
                                                @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize);

    /**
     * 根据类型获取红人总条数
     * @param contentType
     * @param followersType
     * @param followersSpreadType
     * @param totalLikedType
     * @param provinceId
     * @param extensionType
     * @return
     */
    int getSensationVmListByTypeNum(@Param("contentType") Integer contentType, @Param("followersType") Integer followersType,
                                    @Param("followersSpreadType") Integer followersSpreadType, @Param("totalLikedType") Integer totalLikedType,
                                    @Param("provinceId") Long provinceId, @Param("extensionType") String[] extensionType);

    /**
     * 根据昵称获取红人列表
     * @param nickName
     * @return
     */
    List<SensationVm> findSensationVmListByNickName(@Param("nickName") String nickName, @Param("beginNum") Integer beginNum,
                                                    @Param("pageSize") Integer pageSize);

    /**
     * 根据昵称获取红人总条数
     * @param nickName
     * @return
     */
    int getSensationVmListByNickNameNum(@Param("nickName") String nickName);

    /**
     * 根据红人ID获取信息
     * @param sensationId
     * @return
     */
    SensationVm getSensationVmById(@Param("sensationId") Long sensationId);

    /**
     * 获取红人总条数
     * @return
     */
    int getSensationVmNum(@Param("sensationType") Integer sensationType, @Param("nickName") String nickName, @Param("status") Integer status,
                          @Param("contentType") Integer contentType, @Param("followersType") Integer followersType,
                          @Param("followersSpreadType") Integer followersSpreadType, @Param("totalLikedType") Integer totalLikedType,
                          @Param("provinceId") Long provinceId, @Param("extensionType") Integer extensionType);

    /**
     * 获取红人列表
     * @return
     */
    List<SensationVm> findSensationVmList(@Param("sensationType") Integer sensationType, @Param("nickName") String nickName, @Param("status") Integer status,
                                          @Param("contentType") Integer contentType, @Param("followersType") Integer followersType,
                                          @Param("followersSpreadType") Integer followersSpreadType, @Param("totalLikedType") Integer totalLikedType,
                                          @Param("provinceId") Long provinceId, @Param("extensionType") Integer extensionType,
                                          @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize);


    /**
     * 修改状态
     * @param sensationsIds
     * @param status
     * @param userId
     * @param date
     * @return
     */
    int updateStatus(@Param("sensationsIds") String sensationsIds, @Param("status") Integer status,
                     @Param("userId") Long userId, @Param("date") Date date);

    /**
     * 增加红人账户余额
     * @param sensationId
     * @param balancePrice
     * @return
     */
    int plusBalancePrice(@Param("sensationId") Long sensationId, @Param("balancePrice") Double balancePrice);

    /**
     * 减少红人账户余额
     * @param sensationId
     * @param balancePrice
     * @return
     */
    int reduceBalancePrice(@Param("sensationId") Long sensationId, @Param("balancePrice") Double balancePrice, @Param("status") Integer status);

    /**
     * 更新授权信息
     * @param sensationId
     * @param relationId
     * @param accountName
     * @return
     */
    int updateRelationId(@Param("sensationId") Long sensationId, @Param("relationId") Long relationId, @Param("accountName") String accountName);

    /**
     * 根据授权ID获取红人ID
     * @param relationId
     * @return
     */
    Long getSensationIdByRelationId(@Param("relationId") Long relationId);

    /**
     * 增加红人预估收入余额
     * @param sensationId
     * @param estimatedRevenuePrice
     * @return
     */
    int plusEstimatedRevenuePrice(@Param("sensationId") Long sensationId, @Param("estimatedRevenuePrice") Double estimatedRevenuePrice);

    /**
     * 减少红人预估收入余额
     * @param sensationId
     * @param estimatedRevenuePrice
     * @return
     */
    int reduceEstimatedRevenuePrice(@Param("sensationId") Long sensationId, @Param("estimatedRevenuePrice") Double estimatedRevenuePrice);
}