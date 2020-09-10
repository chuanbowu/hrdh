package com.flym.hrdh.api.service.business;

import com.flym.hrdh.api.model.business.BusinessVm;
import com.flym.hrdh.pojo.business.Business;

import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:拿货商品管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface IBusinessService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    Business get(Long id);

    /**
     * 保存或编辑
     * @param business
     * @return
     */
    Business save(Business business);

    /**
     * 根据手机号码和密码获取信息
     * @param phone
     * @param password
     * @return
     */
    Business findBusinessByPhoneNoPassword(String phone, String password);

    /**
     * 获取商家列表
     * @param phone
     * @param nickName
     * @param gradeType
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<BusinessVm> findBusinessVmList(String phone, String nickName, Integer gradeType, Integer beginNum, Integer pageSize);

    /**
     * 获取商家总条数
     * @param phone
     * @param nickName
     * @param gradeType
     * @return
     */
    int getBusinessVmNum(String phone, String nickName, Integer gradeType);

    /**
     * 获取手机号码条数
     * @param phone
     * @return
     */
    int getBusinessPhone(String phone);

    /**
     * 修改状态
     * @param businessIds
     * @param status
     * @param userId
     * @param date
     * @return
     */
    int updateStatus(String businessIds, Integer status, Long userId, Date date);
}
