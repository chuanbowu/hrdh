package com.flym.hrdh.service.sensation;

import com.alibaba.dubbo.config.annotation.Service;
import com.flym.hrdh.api.model.sensation.SensationVm;
import com.flym.hrdh.api.service.sensation.ISensationService;
import com.flym.hrdh.mapper.single.sensation.SensationMapper;
import com.flym.hrdh.pojo.sensation.Sensation;

import javax.annotation.Resource;
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
@Service(version = "1.0.0")
public class SensationServiceImpl implements ISensationService {

	@Resource
	private SensationMapper sensationMapper;

	@Override
	public Sensation get(Long id) {
		if (id == null) {
			return null;
		}
		return sensationMapper.selectByPrimaryKey(id);
	}

	@Override
	public Sensation save(Sensation sensation) {
		if(sensation.getId() != null && sensation.getId() > 0){
			sensationMapper.updateByPrimaryKeySelective(sensation);
		}else{
			sensationMapper.insert(sensation);
		}
		return sensation;
	}

	@Override
	public int getSensationPhoneNum(String phone) {
		return sensationMapper.getSensationPhoneNum(phone);
	}

	@Override
	public Sensation findSensationByPhoneNoPassword(String phone, String password) {
		return sensationMapper.findSensationByPhoneNoPassword(phone, password);
	}

	@Override
	public List<SensationVm> findSensationVmListByType(Integer contentType, Integer followersType,
													   Integer followersSpreadType, Integer totalLikedType,
													   Long provinceId, String[] extensionType,
													   Integer beginNum, Integer pageSize) {
		return sensationMapper.findSensationVmListByType(contentType, followersType, followersSpreadType, totalLikedType,
				provinceId, extensionType, beginNum, pageSize);
	}

	@Override
	public int getSensationVmListByTypeNum(Integer contentType, Integer followersType, Integer followersSpreadType, Integer totalLikedType, Long provinceId, String[] extensionType) {
		return sensationMapper.getSensationVmListByTypeNum(contentType, followersType, followersSpreadType, totalLikedType,
				provinceId, extensionType);
	}

	@Override
	public List<SensationVm> findSensationVmListByNickName(String nickName, Integer beginNum, Integer pageSize) {
		return sensationMapper.findSensationVmListByNickName(nickName, beginNum, pageSize);
	}

	@Override
	public int getSensationVmListByNickNameNum(String nickName) {
		return sensationMapper.getSensationVmListByNickNameNum(nickName);
	}

	@Override
	public SensationVm getSensationVmById(Long sensationId) {
		return sensationMapper.getSensationVmById(sensationId);
	}

	@Override
	public int getSensationVmNum(Integer sensationType, String nickName, Integer status, Integer contentType, Integer followersType, Integer followersSpreadType, Integer totalLikedType, Long provinceId, Integer extensionType) {
		return sensationMapper.getSensationVmNum(sensationType, nickName, status, contentType, followersType, followersSpreadType, totalLikedType,
				provinceId, extensionType);
	}

	@Override
	public List<SensationVm> findSensationVmList(Integer sensationType, String nickName, Integer status, Integer contentType, Integer followersType, Integer followersSpreadType, Integer totalLikedType, Long provinceId, Integer extensionType, Integer beginNum, Integer pageSize) {
		return sensationMapper.findSensationVmList(sensationType, nickName, status, contentType, followersType, followersSpreadType, totalLikedType,
				provinceId, extensionType, beginNum, pageSize);
	}

	@Override
	public int updateStatus(String sensationsIds, Integer status, Long userId, Date date) {
		return sensationMapper.updateStatus(sensationsIds, status, userId, date);
	}

	@Override
	public int plusBalancePrice(Long sensationId, Double balancePrice) {
		return sensationMapper.plusBalancePrice(sensationId, balancePrice);
	}

	@Override
	public int reduceBalancePrice(Long sensationId, Double balancePrice, Integer status) {
		return sensationMapper.reduceBalancePrice(sensationId, balancePrice, status);
	}

	@Override
	public int updateRelationId(Long sensationId, Long relationId, String accountName) {
		return sensationMapper.updateRelationId(sensationId, relationId, accountName);
	}

	@Override
	public Long getSensationIdByRelationId(Long relationId) {
		return sensationMapper.getSensationIdByRelationId(relationId);
	}

	@Override
	public int plusEstimatedRevenuePrice(Long sensationId, Double estimatedRevenuePrice) {
		return sensationMapper.plusEstimatedRevenuePrice(sensationId, estimatedRevenuePrice);
	}

	@Override
	public int reduceEstimatedRevenuePrice(Long sensationId, Double estimatedRevenuePrice) {
		return sensationMapper.reduceEstimatedRevenuePrice(sensationId, estimatedRevenuePrice);
	}

}
