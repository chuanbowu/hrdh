package com.flym.hrdh.mapper.single.business;

import com.flym.hrdh.api.model.business.CommissionGoodsVm;
import com.flym.hrdh.pojo.business.CommissionGoods;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CommissionGoodsMapper {
    /**
     *
     * @mbggenerated 2020-04-28
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insert(CommissionGoods record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int insertSelective(CommissionGoods record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    CommissionGoods selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKeySelective(CommissionGoods record);

    /**
     *
     * @mbggenerated 2020-04-28
     */
    int updateByPrimaryKey(CommissionGoods record);

    /**
     * 根据商品名称获取商品列表：从高级到低级
     * @param goodsName
     * @return
     */
    List<CommissionGoods> findCommissionGoodsListByGoodsName(@Param("goodsName") String goodsName, @Param("beginNum") Integer beginNum,
                                                             @Param("pageSize") Integer pageSize);

    /**
     * 根据商品名称获取商品总条数：从高级到低级
     * @param goodsName
     * @return
     */
    int getCommissionGoodsListByGoodsNameNum(@Param("goodsName") String goodsName);

    /**
     * 根据商品分类获取商品列表
     * @param typeId
     * @return
     */
    List<CommissionGoods> findCommissionGoodsListByType(@Param("typeId") Long typeId, @Param("beginNum") Integer beginNum,
                                                        @Param("pageSize") Integer pageSize);

    /**
     * 根据商品分类获取商品总条数
     * @param typeId
     * @return
     */
    int getCommissionGoodsListByTypeNum(@Param("typeId") Long typeId);

    /**
     * 根据ID获取商品
     * @param id
     * @return
     */
    CommissionGoodsVm getCommissionGoodsVm(@Param("id") Long id);

    /**
     * 获取高级商家的商品
     * @param goodsName
     * @param typeId
     * @return
     */
    List<CommissionGoods> findCommissionGoodsListByGrade(@Param("goodsName") String goodsName, @Param("typeId")Long typeId,
                                                         @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize);

    /**
     * 获取高级商家的商品总条数
     * @param goodsName
     * @param typeId
     * @return
     */
    int getCommissionGoodsListByGradeNum(@Param("goodsName") String goodsName, @Param("typeId")Long typeId);

    /**
     * 根据商家ID获取商品列表
     * @param businessId
     * @param businessTitle
     * @param typeId
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<CommissionGoodsVm> findCommissionGoodsListByBusinessId(@Param("businessId") Long businessId, @Param("businessTitle") String businessTitle, @Param("typeId") Long typeId,
                                                              @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize);

    /**
     * 根据商家ID获取商品总条数
     * @param businessId
     * @param businessTitle
     * @param typeId
     * @param typeId
     * @return
     */
    int getCommissionGoodsListByBusinessId(@Param("businessId") Long businessId, @Param("businessTitle") String businessTitle, @Param("typeId") Long typeId);

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
    int getNewCommissionGoodsNum(@Param("businessId") Long businessId, @Param("date") Date date);

    /**
     * 获取商品列表
     * @param businessId
     * @param businessTitle
     * @param typeId
     * @param beginNum
     * @param pageSize
     * @return
     */
    List<CommissionGoodsVm> findCommissionGoodsVmList(@Param("businessId") Long businessId, @Param("businessTitle") String businessTitle, @Param("typeId") Long typeId,
                                                      @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize);

    /**
     * 获取商品总条数
     * @param businessId
     * @param businessTitle
     * @param typeId
     * @return
     */
    int getCommissionGoodsVmNum(@Param("businessId") Long businessId, @Param("businessTitle") String businessTitle, @Param("typeId") Long typeId);

    /**
     * 减少、相加样品剩余数量
     * @param status：1-相加、2-减少
     * @return
     */
    int updateSurplusNum(@Param("goodsId") Long goodsId, @Param("status") Integer status);

    /**
     * 根据宝贝ID获取ID
     * @param itemId
     * @return
     */
    Long getIdByItemId(@Param("itemId") Long itemId);

}