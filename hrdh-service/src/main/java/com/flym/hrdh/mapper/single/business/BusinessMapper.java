package com.flym.hrdh.mapper.single.business;

import com.flym.hrdh.api.model.business.BusinessVm;
import com.flym.hrdh.pojo.business.Business;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BusinessMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(Business record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(Business record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    Business selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(Business record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(Business record);

    /**
     * 根据手机号码和密码获取信息
     * @param phone
     * @param password
     * @return
     */
    Business findBusinessByPhoneNoPassword(@Param("phone") String phone, @Param("password") String password);

    /**
     * 获取商家列表
     * @param phone
     * @param nickName
     * @param gradeType
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<BusinessVm> findBusinessVmList(@Param("phone") String phone, @Param("nickName") String nickName,
                                        @Param("gradeType") Integer gradeType, @Param("beginNum") Integer beginNum,
                                        @Param("pageSize") Integer pageSize);

    /**
     * 获取商家总条数
     * @param phone
     * @param nickName
     * @param gradeType
     * @return
     */
    int getBusinessVmNum(@Param("phone") String phone, @Param("nickName") String nickName,
                         @Param("gradeType") Integer gradeType);

    /**
     * 获取手机号码条数
     * @param phone
     * @return
     */
    int getBusinessPhone(@Param("phone") String phone);

    /**
     * 修改状态
     * @param businessIds
     * @param status
     * @param userId
     * @param date
     * @return
     */
    int updateStatus(@Param("businessIds") String businessIds, @Param("status") Integer status,
                     @Param("userId") Long userId, @Param("date") Date date);
}