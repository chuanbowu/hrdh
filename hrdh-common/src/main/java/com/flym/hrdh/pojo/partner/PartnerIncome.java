package com.flym.hrdh.pojo.partner;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:合伙人收入明细管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class PartnerIncome implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 合伙人ID
     */
    private Long partnerId;

    /**
     * 商家ID
     */
    private Long businessId;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 类型：1-佣金
     */
    private Integer type;

    /**
     * 金额
     */
    private Double commissionPrice;

    /**
     * 收入时间
     */
    private Date incomeDate;

    /**
     * p_partner_income
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
     * 合伙人ID
     * @return partner_id 合伙人ID
     */
    public Long getPartnerId() {
        return partnerId;
    }

    /**
     * 合伙人ID
     * @param partnerId 合伙人ID
     */
    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    /**
     * 商家ID
     * @return business_id 商家ID
     */
    public Long getBusinessId() {
        return businessId;
    }

    /**
     * 商家ID
     * @param businessId 商家ID
     */
    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
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
     * 类型：1-佣金
     * @return type 类型：1-佣金
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型：1-佣金
     * @param type 类型：1-佣金
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 金额
     * @return commission_price 金额
     */
    public Double getCommissionPrice() {
        return commissionPrice;
    }

    /**
     * 金额
     * @param commissionPrice 金额
     */
    public void setCommissionPrice(Double commissionPrice) {
        this.commissionPrice = commissionPrice;
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
}