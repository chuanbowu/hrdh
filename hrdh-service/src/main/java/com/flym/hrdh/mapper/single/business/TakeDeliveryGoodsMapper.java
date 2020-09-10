package com.flym.hrdh.mapper.single.business;

import com.flym.hrdh.api.model.business.TakeDeliveryGoodsVm;
import com.flym.hrdh.pojo.business.TakeDeliveryGoods;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TakeDeliveryGoodsMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(TakeDeliveryGoods record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(TakeDeliveryGoods record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    TakeDeliveryGoods selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(TakeDeliveryGoods record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(TakeDeliveryGoods record);

    /**
     * 根据商品名称获取商品列表：从高级到低级
     * @param goodsName
     * @return
     */
    List<TakeDeliveryGoods> findTakeDeliveryGoodsListByGoodsName(@Param("goodsName") String goodsName, @Param("beginNum") Integer beginNum,
                                                                 @Param("pageSize") Integer pageSize);

    /**
     * 根据商品名称获取商品总条数：从高级到低级
     * @param goodsName
     * @return
     */
    int getTakeDeliveryGoodsListByGoodsNameNum(@Param("goodsName") String goodsName);

    /**
     * 根据商品分类获取商品列表
     * @param typeId
     * @return
     */
    List<TakeDeliveryGoods> findTakeDeliveryGoodsListByType(@Param("typeId") Long typeId, @Param("beginNum") Integer beginNum,
                                                            @Param("pageSize") Integer pageSize);

    /**
     * 根据商品分类获取商品总条数
     * @param typeId
     * @return
     */
    int getTakeDeliveryGoodsListByTypeNum(@Param("typeId") Long typeId);

    /**
     * 根据ID获取商品
     * @param id
     * @return
     */
    TakeDeliveryGoodsVm getTakeDeliveryGoodsVm(@Param("id") Long id);

    /**
     * 获取高级商家的商品
     * @param goodsName
     * @param typeId
     * @return
     */
    List<TakeDeliveryGoods> findTakeDeliveryGoodsListByGrade(@Param("goodsName") String goodsName, @Param("typeId")Long typeId,
                                                             @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize);

    /**
     * 获取高级商家的商品总条数
     * @param goodsName
     * @param typeId
     * @return
     */
    int getTakeDeliveryGoodsListByGradeNum(@Param("goodsName") String goodsName, @Param("typeId")Long typeId);

    /**
     * 根据商家ID获取商品列表
     * @param businessId
     * @param businessTitle
     * @param typeId
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<TakeDeliveryGoodsVm> findTakeDeliveryGoodsListByBusinessId(@Param("businessId") Long businessId, @Param("businessTitle") String businessTitle, @Param("typeId") Long typeId,
                                                                  @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize);

    /**
     * 根据商家ID获取商品总条数
     * @param businessId
     * @param businessTitle
     * @param type
     * @return
     */
    int getTakeDeliveryGoodsListByBusinessIdNum(@Param("businessId") Long businessId, @Param("businessTitle") String businessTitle, @Param("typeId") Long typeId);

    /**
     * 根据商品ID修改状态
     * @param goodsIds
     * @param status
     * @param date
     * @return
     */
    int updateStatus(@Param("goodsIds") String goodsIds, @Param("status") Integer status, @Param("date") Date date);

    /**
     * 获取商家当月上新数量
     * @param businessId
     * @param date
     * @return
     */
    int getNewTakeDeliveryGoodsNum(@Param("businessId") Long businessId, @Param("date") Date date);

    /**
     * 获取商品列表
     * @param businessId
     * @param businessTitle
     * @param typeId
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<TakeDeliveryGoodsVm> findTakeDeliveryGoodsVmList(@Param("businessId") Long businessId, @Param("businessTitle") String businessTitle, @Param("typeId") Long typeId,
                                                        @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize);

    /**
     * 获取商品总条数
     * @param businessId
     * @param businessTitle
     * @param typeId
     * @return
     */
    int getTakeDeliveryGoodsVmNum(@Param("businessId") Long businessId, @Param("businessTitle") String businessTitle, @Param("typeId") Long typeId);
}