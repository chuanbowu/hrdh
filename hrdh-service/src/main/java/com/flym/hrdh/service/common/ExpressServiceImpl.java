package com.flym.hrdh.service.common;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.service.common.IExpressService;
import com.flym.hrdh.mapper.single.common.ExpressMapper;
import com.flym.hrdh.pojo.common.Express;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:快递公司管理</p>
 * <p>Copyright: Copyright (c) 2020-06-04</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Service(version = "1.0.0")
public class ExpressServiceImpl implements IExpressService {

	@Resource
	private ExpressMapper expressMapper;

	@Override
	public Express get(Long id) {
		if (id == null) {
			return null;
		}
		return expressMapper.selectByPrimaryKey(id);
	}

	@Override
	public Express save(Express express) {
		if(express.getId() != null && express.getId() > 0){
			expressMapper.updateByPrimaryKeySelective(express);
		}else{
			expressMapper.insert(express);
		}
		return express;
	}

	@Override
	public List<Express> findExpressList() {
		return expressMapper.findExpressList();
	}

}
