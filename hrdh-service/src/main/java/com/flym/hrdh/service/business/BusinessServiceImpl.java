package com.flym.hrdh.service.business;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.model.business.BusinessVm;
import com.flym.hrdh.api.service.business.IBusinessService;
import com.flym.hrdh.mapper.single.business.BusinessMapper;
import com.flym.hrdh.pojo.business.Business;

import javax.annotation.Resource;
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
@Service(version = "1.0.0")
public class BusinessServiceImpl implements IBusinessService {

	@Resource
	private BusinessMapper businessMapper;

	@Override
	public Business get(Long id) {
		if (id == null) {
			return null;
		}
		return businessMapper.selectByPrimaryKey(id);
	}

	@Override
	public Business save(Business business) {
		if(business.getId() != null && business.getId() > 0){
			businessMapper.updateByPrimaryKeySelective(business);
		}else{
			businessMapper.insert(business);
		}
		return business;
	}

	@Override
	public Business findBusinessByPhoneNoPassword(String phone, String password) {
		return businessMapper.findBusinessByPhoneNoPassword(phone, password);
	}

	@Override
	public List<BusinessVm> findBusinessVmList(String phone, String nickName, Integer gradeType, Integer beginNum, Integer pageSize) {
		return businessMapper.findBusinessVmList(phone, nickName, gradeType, beginNum, pageSize);
	}

	@Override
	public int getBusinessVmNum(String phone, String nickName, Integer gradeType) {
		return businessMapper.getBusinessVmNum(phone, nickName, gradeType);
	}

	@Override
	public int getBusinessPhone(String phone) {
		return businessMapper.getBusinessPhone(phone);
	}

	@Override
	public int updateStatus(String businessIds, Integer status, Long userId, Date date) {
		return businessMapper.updateStatus(businessIds, status, userId, date);
	}

}
