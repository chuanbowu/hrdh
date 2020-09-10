package com.flym.hrdh.service.common;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.model.common.CustomerVm;
import com.flym.hrdh.api.service.common.ICustomerService;
import com.flym.hrdh.mapper.single.common.CustomerMapper;
import com.flym.hrdh.pojo.common.Customer;

import javax.annotation.Resource;
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
@Service(version = "1.0.0")
public class CustomerServiceImpl implements ICustomerService {

	@Resource
	private CustomerMapper customerMapper;

	@Override
	public Customer get(Long id) {
		if (id == null) {
			return null;
		}
		return customerMapper.selectByPrimaryKey(id);
	}

	@Override
	public Customer save(Customer customer) {
		if(customer.getId() != null && customer.getId() > 0){
			customerMapper.updateByPrimaryKeySelective(customer);
		}else{
			customerMapper.insert(customer);
		}
		return customer;
	}

	@Override
	public List<Customer> findCustomerList() {
		return customerMapper.findCustomerList();
	}

	@Override
	public List<CustomerVm> findCustomerVmList() {
		return customerMapper.findCustomerVmList();
	}

	@Override
	public int updateStatus(String customerIds, Integer status, Long userId, Date date) {
		return customerMapper.updateStatus(customerIds, status, userId, date);
	}

}
