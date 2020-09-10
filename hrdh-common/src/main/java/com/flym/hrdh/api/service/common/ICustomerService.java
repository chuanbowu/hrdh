package com.flym.hrdh.api.service.common;

import com.flym.hrdh.api.model.common.CustomerVm;
import com.flym.hrdh.pojo.common.Customer;

import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:客服管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface ICustomerService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
   Customer get(Long id);

    /**
     * 保存或编辑
     * @param customer
     * @return
     */
    Customer save(Customer customer);

    /**
     * 获取客服列表
     * @return
     */
    List<Customer> findCustomerList();

    /**
     * 获取客服列表
     * @return
     */
    List<CustomerVm> findCustomerVmList();

    /**
     * 修改状态
     * @param customerIds
     * @param status
     * @param userId
     * @param date
     * @return
     */
    int updateStatus(String customerIds, Integer status, Long userId, Date date);
}
