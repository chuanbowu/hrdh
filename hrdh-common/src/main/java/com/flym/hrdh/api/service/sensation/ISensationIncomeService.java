package com.flym.hrdh.api.service.sensation;

import com.flym.hrdh.api.model.sensation.SensationIncomeVm;
import com.flym.hrdh.pojo.sensation.SensationIncome;

import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:红人收入明细管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface ISensationIncomeService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    SensationIncome get(Long id);

    /**
     * 保存或编辑
     * @param sensationIncome
     * @return
     */
    SensationIncome save(SensationIncome sensationIncome);

    /**
     * 根据红人获取收入明细总条数
     * @param sensationId
     * @return
     */
    int getSensationIncomeVmNumBySensationId(Long sensationId);

    /**
     * 根据红人获取收入明细列表
     * @param sensationId
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<SensationIncomeVm> findSensationIncomeVmListBySensationId(Long sensationId, Integer beginNum, Integer pageSize);

    /**
     * 获取收入明细总条数
     * @param sensationId
     * @param type
     * @return
     */
    int getSensationIncomeVmNum(Long sensationId, Integer type);

    /**
     * 获取收入明细列表
     * @param sensationId
     * @param type
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<SensationIncomeVm> findSensationIncomeVmList(Long sensationId, Integer type, Integer beginNum, Integer pageSize);

    /**
     * 获取最后一条数据
     * @return
     */
    SensationIncome getLastHandleDate();

    /**
     * 根据红人ID商品ID淘宝订单ID获取信息
     * @param sensationId
     * @param goodsId
     * @param tradeParentId
     * @return
     */
    SensationIncome getBySensationIdOrGoodsIdOrTradeParentId(Long sensationId, Long goodsId, String tradeParentId);

    /**
     * 更新最后一条数据
     * @param date
     * @return
     */
    int updateLastHandleDate(Date date);
}
