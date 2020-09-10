package com.flym.hrdh.pojo.sensation;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:红人收入明细管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class SensationIncome implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 红人ID
     */
    private Long sensationId;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 淘宝订单ID
     */
    private String orderId;

    /**
     * 佣金
     */
    private Double commissionPrice;

    /**
     * 类型：1-预估收入、2-已入账、3-违规处罚
     */
    private Integer type;

    /**
     * 收入时间
     */
    private Date incomeDate;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 订单创建的时间
     */
    private Date tkCreateDate;

    /**
     * 最后处理时间
     */
    private Date lastHandleDate;

    /**
     * s_sensation_income
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * @return id ID
     */
    public Long getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 红人ID
     * @return sensation_id 红人ID
     */
    public Long getSensationId() {
        return sensationId;
    }

    /**
     * 红人ID
     * @param sensationId 红人ID
     */
    public void setSensationId(Long sensationId) {
        this.sensationId = sensationId;
    }

    /**
     * 商品ID
     * @return goods_id 商品ID
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 商品ID
     * @param goodsId 商品ID
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 淘宝订单ID
     * @return order_id 淘宝订单ID
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 淘宝订单ID
     * @param orderId 淘宝订单ID
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * 佣金
     * @return commission_price 佣金
     */
    public Double getCommissionPrice() {
        return commissionPrice;
    }

    /**
     * 佣金
     * @param commissionPrice 佣金
     */
    public void setCommissionPrice(Double commissionPrice) {
        this.commissionPrice = commissionPrice;
    }

    /**
     * 类型：1-预估收入、2-已入账、3-违规处罚
     * @return type 类型：1-预估收入、2-已入账、3-违规处罚
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型：1-预估收入、2-已入账、3-违规处罚
     * @param type 类型：1-预估收入、2-已入账、3-违规处罚
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 收入时间
     * @return income_date 收入时间
     */
    public Date getIncomeDate() {
        return incomeDate;
    }

    /**
     * 收入时间
     * @param incomeDate 收入时间
     */
    public void setIncomeDate(Date incomeDate) {
        this.incomeDate = incomeDate;
    }

    /**
     * 修改时间
     * @return modify_date 修改时间
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * 修改时间
     * @param modifyDate 修改时间
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * 订单创建的时间
     * @return tk_create_date 订单创建的时间
     */
    public Date getTkCreateDate() {
        return tkCreateDate;
    }

    /**
     * 订单创建的时间
     * @param tkCreateDate 订单创建的时间
     */
    public void setTkCreateDate(Date tkCreateDate) {
        this.tkCreateDate = tkCreateDate;
    }

    /**
     * 最后处理时间
     * @return last_handle_date 最后处理时间
     */
    public Date getLastHandleDate() {
        return lastHandleDate;
    }

    /**
     * 最后处理时间
     * @param lastHandleDate 最后处理时间
     */
    public void setLastHandleDate(Date lastHandleDate) {
        this.lastHandleDate = lastHandleDate;
    }
}