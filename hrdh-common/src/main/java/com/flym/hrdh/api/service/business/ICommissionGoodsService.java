package com.flym.hrdh.api.service.business;

import com.flym.hrdh.api.model.business.CommissionGoodsVm;
import com.flym.hrdh.pojo.business.CommissionGoods;

import java.util.Date;
import java.util.List;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:佣金商品管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public interface ICommissionGoodsService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    CommissionGoods get(Long id);

    /**
     * 保存或编辑
     * @param commissionGoods
     * @return
     */
    CommissionGoods save(CommissionGoods commissionGoods);

    /**
     * 根据商品名称获取商品列表：从高级到低级
     * @param goodsName
     * @return
     */
    List<CommissionGoods> findCommissionGoodsListByGoodsName(String goodsName, Integer beginNum, Integer pageSize);

    /**
     * 根据商品名称获取商品总条数：从高级到低级
     * @param goodsName
     * @return
     */
    int getCommissionGoodsListByGoodsNameNum(String goodsName);

    /**
     * 根据商品分类获取商品列表
     * @param typeId
     * @return
     */
    List<CommissionGoods> findCommissionGoodsListByType(Long typeId, Integer beginNum, Integer pageSize);

    /**
     * 根据商品分类获取商品总条数
     * @param typeId
     * @return
     */
    int getCommissionGoodsListByTypeNum(Long typeId);

    /**
     * 根据ID获取商品
   * @param id
     * @return
     */
    CommissionGoodsVm getCommissionGoodsVm(Long id);

    /**
     * 获取高级商家的商品
     * @param goodsName
     * @param typeId
     * @return
     */
    List<CommissionGoods> findCommissionGoodsListByGrade(String goodsName, Long typeId, Integer beginNum, Integer pageSize);

    /**
     * 获取高级商家的商品总条数
     * @param goodsName
     * @param typeId
     * @return
     */
    int getCommissionGoodsListByGradeNum(String goodsName, Long typeId);

    /**
     * 根据商家ID获取商品列表
     * @param businessId
     * @param businessTitle
     * @param typeId
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<CommissionGoodsVm> findCommissionGoodsListByBusinessId(Long businessId, String businessTitle, Long typeId,
                                                              Integer beginNum, Integer pageSize);

    /**
     * 根据商家ID获取商品总条数
     * @param businessId
     * @param businessTitle
     * @param typeId
     * @return
     */
    int getCommissionGoodsListByBusinessId(Long businessId, String businessTitle, Long typeId);

    /**
     * 根据商品ID修改状态
     * @param goodsIds
     * @param status
     * @param date
     * @return
     */
    int updateStatus(String goodsIds, Integer status, Date date);

    /**
     * 获取商家当月上新数量
     * @param businessId
     * @param date
     * @return
     */
    int getNewCommissionGoodsNum(Long businessId, Date date);

    /**
     * 获取商品列表
     * @param businessId
     * @param businessTitle
     * @param typeId
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<CommissionGoodsVm> findCommissionGoodsVmList(Long businessId, String businessTitle, Long typeId,
                                                              Integer beginNum, Integer pageSize);

    /**
     * 获取商品总条数
     * @param businessId
     * @param businessTitle
     * @param typeId
     * @return
     */
    int getCommissionGoodsVmNum(Long businessId, String businessTitle, Long typeId);

    /**
     * 减少、相加样品剩余数量
     * @param status：1-相加、2-减少
     * @return
     */
    int updateSurplusNum(Long goodsId, Integer status);

    /**
     * 根据宝贝ID获取ID
     * @param itemId
     * @return
     */
    Long getIdByItemId(Long itemId);
}
