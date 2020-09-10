package com.flym.hrdh.api.service.sensation;

import com.flym.hrdh.api.model.sensation.SensationVm;
import com.flym.hrdh.pojo.sensation.Sensation;
import com.flym.hrdh.utils.MD5Util;
import com.sun.org.apache.bcel.internal.generic.INEG;

import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:红人管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface ISensationService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    Sensation get(Long id);

    /**
     * 保存或编辑
     * @param sensation
     * @return
     */
    Sensation save(Sensation sensation);

    /**
     * 根据红人手机号码获取条数
     * @param phone
     * @return
     */
    int getSensationPhoneNum(String phone);

    /**
     * 根据手机号码和密码获取信息
     * @param phone
     * @param password
     * @return
     */
    Sensation findSensationByPhoneNoPassword(String phone, String password);

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
    List<SensationVm> findSensationVmListByType(Integer contentType, Integer followersType,
                                                Integer followersSpreadType, Integer totalLikedType,
                                                Long provinceId, String[] extensionType,
                                                Integer beginNum, Integer pageSize);

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
    int getSensationVmListByTypeNum(Integer contentType, Integer followersType,
                                    Integer followersSpreadType, Integer totalLikedType,
                                    Long provinceId, String[] extensionType);

    /**
     * 根据昵称获取红人列表
     * @param nickName
     * @return
     */
    List<SensationVm> findSensationVmListByNickName(String nickName, Integer beginNum, Integer pageSize);

    /**
     * 根据昵称获取红人总条数
     * @param nickName
     * @return
     */
    int getSensationVmListByNickNameNum(String nickName);

    /**
     * 根据红人ID获取信息
     * @param sensationId
     * @return
     */
    SensationVm getSensationVmById(Long sensationId);

    /**
     * 获取红人总条数
     * @return
     */
    int getSensationVmNum(Integer sensationType, String nickName, Integer status,
                          Integer contentType, Integer followersType,
                          Integer followersSpreadType, Integer totalLikedType,
                          Long provinceId, Integer extensionType);

    /**
     * 获取红人列表
     * @return
     */
    List<SensationVm> findSensationVmList(Integer sensationType, String nickName, Integer status,
                                          Integer contentType, Integer followersType,
                                          Integer followersSpreadType, Integer totalLikedType,
                                          Long provinceId, Integer extensionType,
                                          Integer beginNum, Integer pageSize);

    /**
     * 修改状态
     * @param sensationsIds
     * @param status
     * @param userId
     * @param date
     * @return
     */
    int updateStatus(String sensationsIds, Integer status, Long userId, Date date);

    /**
     * 增加红人账户余额
     * @param sensationId
     * @param balancePrice
     * @return
     */
    int plusBalancePrice(Long sensationId, Double balancePrice);

    /**
     * 减少红人账户余额
     * @param sensationId
     * @param balancePrice
     * @return
     */
    int reduceBalancePrice(Long sensationId, Double balancePrice, Integer status);

    /**
     * 更新授权信息
     * @param sensationId
     * @param relationId
     * @param accountName
     * @return
     */
    int updateRelationId(Long sensationId, Long relationId, String accountName);

    /**
     * 根据授权ID获取红人ID
     * @param relationId
     * @return
     */
    Long getSensationIdByRelationId(Long relationId);

    /**
     * 增加红人预估收入余额
     * @param sensationId
     * @param estimatedRevenuePrice
     * @return
     */
    int plusEstimatedRevenuePrice(Long sensationId, Double estimatedRevenuePrice);

    /**
     * 减少红人预估收入余额
     * @param sensationId
     * @param estimatedRevenuePrice
     * @return
     */
    int reduceEstimatedRevenuePrice(Long sensationId, Double estimatedRevenuePrice);
}
