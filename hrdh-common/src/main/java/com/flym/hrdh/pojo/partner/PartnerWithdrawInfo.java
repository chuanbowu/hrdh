package com.flym.hrdh.pojo.partner;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:合伙人提现明细管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class PartnerWithdrawInfo implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 合伙人ID
     */
    private Long partnerId;

    /**
     * 支付宝ID
     */
    private Long alipayId;

    /**
     * 金额
     */
    private Double moneyPrice;

    /**
     * 状态：1-提现中、2-已提现、3-提现失败
     */
    private Integer status;

    /**
     * 提现时间
     */
    private Date withdrawDate;

    /**
     * p_partner_withdraw_info
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
     * 支付宝ID
     * @return alipay_id 支付宝ID
     */
    public Long getAlipayId() {
        return alipayId;
    }

    /**
     * 支付宝ID
     * @param alipayId 支付宝ID
     */
    public void setAlipayId(Long alipayId) {
        this.alipayId = alipayId;
    }

    /**
     * 金额
     * @return money_price 金额
     */
    public Double getMoneyPrice() {
        return moneyPrice;
    }

    /**
     * 金额
     * @param moneyPrice 金额
     */
    public void setMoneyPrice(Double moneyPrice) {
        this.moneyPrice = moneyPrice;
    }

    /**
     * 状态：1-提现中、2-已提现、3-提现失败
     * @return status 状态：1-提现中、2-已提现、3-提现失败
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态：1-提现中、2-已提现、3-提现失败
     * @param status 状态：1-提现中、2-已提现、3-提现失败
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 提现时间
     * @return withdraw_date 提现时间
     */
    public Date getWithdrawDate() {
        return withdrawDate;
    }

    /**
     * 提现时间
     * @param withdrawDate 提现时间
     */
    public void setWithdrawDate(Date withdrawDate) {
        this.withdrawDate = withdrawDate;
    }
}