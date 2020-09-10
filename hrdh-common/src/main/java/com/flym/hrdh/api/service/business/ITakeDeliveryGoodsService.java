package com.flym.hrdh.api.service.business;

import com.flym.hrdh.api.model.business.TakeDeliveryGoodsVm;
import com.flym.hrdh.pojo.business.CommissionGoods;
import com.flym.hrdh.pojo.business.TakeDeliveryGoods;

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
public interface ITakeDeliveryGoodsService {

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    TakeDeliveryGoods get(Long id);

    /**
     * 保存或编辑
     * @param takeDeliveryGoods
     * @return
     */
    TakeDeliveryGoods save(TakeDeliveryGoods takeDeliveryGoods);

    /**
     * 根据商品名称获取商品列表：从高级到低级
     * @param goodsName
     * @return
     */
    List<TakeDeliveryGoods> findTakeDeliveryGoodsListByGoodsName(String goodsName, Integer beginNum, Integer pageSize);

    /**
     * 根据商品名称获取商品总条数：从高级到低级
     * @param goodsName
     * @return
     */
    int getTakeDeliveryGoodsListByGoodsNameNum(String goodsName);

    /**
     * 根据商品分类获取商品列表
     * @param typeId
     * @return
     */
    List<TakeDeliveryGoods> findTakeDeliveryGoodsListByType(Long typeId, Integer beginNum, Integer pageSize);

    /**
     * 根据商品分类获取商品总条数
     * @param typeId
     * @return
     */
    int getTakeDeliveryGoodsListByTypeNum(Long typeId);

    /**
     * 根据ID获取商品
     * @param id
     * @return
     */
    TakeDeliveryGoodsVm getTakeDeliveryGoodsVm(Long id);

    /**
     * 获取高级商家的商品
     * @param goodsName
     * @param typeId
     * @return
     */
    List<TakeDeliveryGoods> findTakeDeliveryGoodsListByGrade(String goodsName, Long typeId, Integer beginNum, Integer pageSize);

    /**
     * 获取高级商家的商品总条数
     * @param goodsName
     * @param typeId
     * @return
     */
    int getTakeDeliveryGoodsListByGradeNum(String goodsName, Long typeId);

    /**
     * 根据商家ID获取商品列表
     * @param businessId
     * @param businessTitle
     * @param typeId
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<TakeDeliveryGoodsVm> findTakeDeliveryGoodsListByBusinessId(Long businessId, String businessTitle, Long typeId,
                                                              Integer beginNum, Integer pageSize);

    /**
     * 根据商家ID获取商品总条数
     * @param businessId
     * @param businessTitle
     * @param typeId
     * @return
     */
    int getTakeDeliveryGoodsListByBusinessIdNum(Long businessId, String businessTitle, Long typeId);

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
    int getNewTakeDeliveryGoodsNum(Long businessId, Date date);

    /**
     * 获取商品列表
     * @param businessId
     * @param businessTitle
     * @param typeId
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<TakeDeliveryGoodsVm> findTakeDeliveryGoodsVmList(Long businessId, String businessTitle, Long typeId,
                                                                  Integer beginNum, Integer pageSize);

    /**
     * 获取商品总条数
     * @param businessId
     * @param businessTitle
     * @param typeId
     * @return
     */
    int getTakeDeliveryGoodsVmNum(Long businessId, String businessTitle, Long typeId);
}
