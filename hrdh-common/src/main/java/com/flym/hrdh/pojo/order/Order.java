package com.flym.hrdh.pojo.order;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:取样管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class Order implements Serializable {

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
     * 红人收样人手机号
     */
    private String sensationConsigneePhone;

    /**
     * 红人收样人姓名
     */
    private String sensationConsigneeName;

    /**
     * 红人收样地址
     */
    private String sensationConsigneeAddress;

    /**
     * 支付方式：1-支付宝、2-微信、3-不需要
     */
    private Integer paymentType;

    /**
     * 支付押金：1-是、2-否
     */
    private Integer paymentDeposit;

    /**
     * 押金金额
     */
    private Double depositPrice;

    /**
     * 取样状态：1-等待商家审核、2-商家审核失败、3-取样中、4-商家发样中、5-红人已收样、6-红人寄回样中、7-商家已收样
     */
    private Integer status;

    /**
     * 商家发样快递公司ID
     */
    private Long businessExpressId;

    /**
     * 商家发样快递单号
     */
    private String businessNum;

    /**
     * 商家寄回样品说明
     */
    private String businessExplain;

    /**
     * 商家收样人手机号
     */
    private String businessConsigneePhone;

    /**
     * 商家收样人姓名
     */
    private String businessConsigneeName;

    /**
     * 商家收样的地址
     */
    private String businessConsigneeAddress;

    /**
     * 红人发样快递公司ID
     */
    private Long sensationExpressId;

    /**
     * 红人发样快递单号
     */
    private String sensationNum;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 商家发样时间
     */
    private Date businessDeliverDate;

    /**
     * 红人收样时间
     */
    private Date sensationReceivingDate;

    /**
     * 红人寄样时间
     */
    private Date sensationSendDate;

    /**
     * 商家收样时间
     */
    private Date businessCollectDate;

    /**
     * 押金退回时间
     */
    private Date depositReturnDate;

    /**
     * 推广链接
     */
    private String promotionLink;

    /**
     * 宝贝+券二合一推广链接
     */
    private String couponShareUrl;

    /**
     * 拒绝理由
     */
    private String refuseContent;

    /**
     * 审核时间
     */
    private Date checkDate;

    /**
     * o_order
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
     * 红人收样人手机号
     * @return sensation_consignee_phone 红人收样人手机号
     */
    public String getSensationConsigneePhone() {
        return sensationConsigneePhone;
    }

    /**
     * 红人收样人手机号
     * @param sensationConsigneePhone 红人收样人手机号
     */
    public void setSensationConsigneePhone(String sensationConsigneePhone) {
        this.sensationConsigneePhone = sensationConsigneePhone == null ? null : sensationConsigneePhone.trim();
    }

    /**
     * 红人收样人姓名
     * @return sensation_consignee_name 红人收样人姓名
     */
    public String getSensationConsigneeName() {
        return sensationConsigneeName;
    }

    /**
     * 红人收样人姓名
     * @param sensationConsigneeName 红人收样人姓名
     */
    public void setSensationConsigneeName(String sensationConsigneeName) {
        this.sensationConsigneeName = sensationConsigneeName == null ? null : sensationConsigneeName.trim();
    }

    /**
     * 红人收样地址
     * @return sensation_consignee_address 红人收样地址
     */
    public String getSensationConsigneeAddress() {
        return sensationConsigneeAddress;
    }

    /**
     * 红人收样地址
     * @param sensationConsigneeAddress 红人收样地址
     */
    public void setSensationConsigneeAddress(String sensationConsigneeAddress) {
        this.sensationConsigneeAddress = sensationConsigneeAddress == null ? null : sensationConsigneeAddress.trim();
    }

    /**
     * 支付方式：1-支付宝、2-微信、3-不需要
     * @return payment_type 支付方式：1-支付宝、2-微信、3-不需要
     */
    public Integer getPaymentType() {
        return paymentType;
    }

    /**
     * 支付方式：1-支付宝、2-微信、3-不需要
     * @param paymentType 支付方式：1-支付宝、2-微信、3-不需要
     */
    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * 支付押金：1-是、2-否
     * @return payment_deposit 支付押金：1-是、2-否
     */
    public Integer getPaymentDeposit() {
        return paymentDeposit;
    }

    /**
     * 支付押金：1-是、2-否
     * @param paymentDeposit 支付押金：1-是、2-否
     */
    public void setPaymentDeposit(Integer paymentDeposit) {
        this.paymentDeposit = paymentDeposit;
    }

    /**
     * 押金金额
     * @return deposit_price 押金金额
     */
    public Double getDepositPrice() {
        return depositPrice;
    }

    /**
     * 押金金额
     * @param depositPrice 押金金额
     */
    public void setDepositPrice(Double depositPrice) {
        this.depositPrice = depositPrice;
    }

    /**
     * 取样状态：1-等待商家审核、2-商家审核失败、3-取样中、4-商家发样中、5-红人已收样、6-红人寄回样中、7-商家已收样
     * @return status 取样状态：1-等待商家审核、2-商家审核失败、3-取样中、4-商家发样中、5-红人已收样、6-红人寄回样中、7-商家已收样
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 取样状态：1-等待商家审核、2-商家审核失败、3-取样中、4-商家发样中、5-红人已收样、6-红人寄回样中、7-商家已收样
     * @param status 取样状态：1-等待商家审核、2-商家审核失败、3-取样中、4-商家发样中、5-红人已收样、6-红人寄回样中、7-商家已收样
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 商家发样快递公司ID
     * @return business_express_id 商家发样快递公司ID
     */
    public Long getBusinessExpressId() {
        return businessExpressId;
    }

    /**
     * 商家发样快递公司ID
     * @param businessExpressId 商家发样快递公司ID
     */
    public void setBusinessExpressId(Long businessExpressId) {
        this.businessExpressId = businessExpressId;
    }

    /**
     * 商家发样快递单号
     * @return business_num 商家发样快递单号
     */
    public String getBusinessNum() {
        return businessNum;
    }

    /**
     * 商家发样快递单号
     * @param businessNum 商家发样快递单号
     */
    public void setBusinessNum(String businessNum) {
        this.businessNum = businessNum == null ? null : businessNum.trim();
    }

    /**
     * 商家寄回样品说明
     * @return business_explain 商家寄回样品说明
     */
    public String getBusinessExplain() {
        return businessExplain;
    }

    /**
     * 商家寄回样品说明
     * @param businessExplain 商家寄回样品说明
     */
    public void setBusinessExplain(String businessExplain) {
        this.businessExplain = businessExplain == null ? null : businessExplain.trim();
    }

    /**
     * 商家收样人手机号
     * @return business_consignee_phone 商家收样人手机号
     */
    public String getBusinessConsigneePhone() {
        return businessConsigneePhone;
    }

    /**
     * 商家收样人手机号
     * @param businessConsigneePhone 商家收样人手机号
     */
    public void setBusinessConsigneePhone(String businessConsigneePhone) {
        this.businessConsigneePhone = businessConsigneePhone == null ? null : businessConsigneePhone.trim();
    }

    /**
     * 商家收样人姓名
     * @return business_consignee_name 商家收样人姓名
     */
    public String getBusinessConsigneeName() {
        return businessConsigneeName;
    }

    /**
     * 商家收样人姓名
     * @param businessConsigneeName 商家收样人姓名
     */
    public void setBusinessConsigneeName(String businessConsigneeName) {
        this.businessConsigneeName = businessConsigneeName == null ? null : businessConsigneeName.trim();
    }

    /**
     * 商家收样的地址
     * @return business_consignee_address 商家收样的地址
     */
    public String getBusinessConsigneeAddress() {
        return businessConsigneeAddress;
    }

    /**
     * 商家收样的地址
     * @param businessConsigneeAddress 商家收样的地址
     */
    public void setBusinessConsigneeAddress(String businessConsigneeAddress) {
        this.businessConsigneeAddress = businessConsigneeAddress == null ? null : businessConsigneeAddress.trim();
    }

    /**
     * 红人发样快递公司ID
     * @return sensation_express_id 红人发样快递公司ID
     */
    public Long getSensationExpressId() {
        return sensationExpressId;
    }

    /**
     * 红人发样快递公司ID
     * @param sensationExpressId 红人发样快递公司ID
     */
    public void setSensationExpressId(Long sensationExpressId) {
        this.sensationExpressId = sensationExpressId;
    }

    /**
     * 红人发样快递单号
     * @return sensation_num 红人发样快递单号
     */
    public String getSensationNum() {
        return sensationNum;
    }

    /**
     * 红人发样快递单号
     * @param sensationNum 红人发样快递单号
     */
    public void setSensationNum(String sensationNum) {
        this.sensationNum = sensationNum == null ? null : sensationNum.trim();
    }

    /**
     * 创建时间
     * @return create_date 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 商家发样时间
     * @return business_deliver_date 商家发样时间
     */
    public Date getBusinessDeliverDate() {
        return businessDeliverDate;
    }

    /**
     * 商家发样时间
     * @param businessDeliverDate 商家发样时间
     */
    public void setBusinessDeliverDate(Date businessDeliverDate) {
        this.businessDeliverDate = businessDeliverDate;
    }

    /**
     * 红人收样时间
     * @return sensation_receiving_date 红人收样时间
     */
    public Date getSensationReceivingDate() {
        return sensationReceivingDate;
    }

    /**
     * 红人收样时间
     * @param sensationReceivingDate 红人收样时间
     */
    public void setSensationReceivingDate(Date sensationReceivingDate) {
        this.sensationReceivingDate = sensationReceivingDate;
    }

    /**
     * 红人寄样时间
     * @return sensation_send_date 红人寄样时间
     */
    public Date getSensationSendDate() {
        return sensationSendDate;
    }

    /**
     * 红人寄样时间
     * @param sensationSendDate 红人寄样时间
     */
    public void setSensationSendDate(Date sensationSendDate) {
        this.sensationSendDate = sensationSendDate;
    }

    /**
     * 商家收样时间
     * @return business_collect_date 商家收样时间
     */
    public Date getBusinessCollectDate() {
        return businessCollectDate;
    }

    /**
     * 商家收样时间
     * @param businessCollectDate 商家收样时间
     */
    public void setBusinessCollectDate(Date businessCollectDate) {
        this.businessCollectDate = businessCollectDate;
    }

    /**
     * 押金退回时间
     * @return deposit_return_date 押金退回时间
     */
    public Date getDepositReturnDate() {
        return depositReturnDate;
    }

    /**
     * 押金退回时间
     * @param depositReturnDate 押金退回时间
     */
    public void setDepositReturnDate(Date depositReturnDate) {
        this.depositReturnDate = depositReturnDate;
    }

    /**
     * 推广链接
     * @return promotion_link 推广链接
     */
    public String getPromotionLink() {
        return promotionLink;
    }

    /**
     * 推广链接
     * @param promotionLink 推广链接
     */
    public void setPromotionLink(String promotionLink) {
        this.promotionLink = promotionLink == null ? null : promotionLink.trim();
    }

    /**
     * 宝贝+券二合一推广链接
     * @return coupon_share_url 宝贝+券二合一推广链接
     */
    public String getCouponShareUrl() {
        return couponShareUrl;
    }

    /**
     * 宝贝+券二合一推广链接
     * @param couponShareUrl 宝贝+券二合一推广链接
     */
    public void setCouponShareUrl(String couponShareUrl) {
        this.couponShareUrl = couponShareUrl == null ? null : couponShareUrl.trim();
    }

    /**
     * 拒绝理由
     * @return refuse_content 拒绝理由
     */
    public String getRefuseContent() {
        return refuseContent;
    }

    /**
     * 拒绝理由
     * @param refuseContent 拒绝理由
     */
    public void setRefuseContent(String refuseContent) {
        this.refuseContent = refuseContent == null ? null : refuseContent.trim();
    }

    /**
     * 审核时间
     * @return check_date 审核时间
     */
    public Date getCheckDate() {
        return checkDate;
    }

    /**
     * 审核时间
     * @param checkDate 审核时间
     */
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

}