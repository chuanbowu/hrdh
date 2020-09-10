package com.flym.hrdh.mapper.single.common;

import com.flym.hrdh.api.model.common.CustomerVm;
import com.flym.hrdh.pojo.common.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CustomerMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(Customer record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(Customer record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    Customer selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(Customer record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(Customer record);

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
    int updateStatus(@Param("customerIds") String customerIds, @Param("status") Integer status,
                     @Param("userId") Long userId, @Param("date") Date date);
}