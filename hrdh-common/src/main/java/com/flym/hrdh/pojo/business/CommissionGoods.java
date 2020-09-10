package com.flym.hrdh.pojo.business;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:佣金商品管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class CommissionGoods implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 商家ID
     */
    private Long businessId;

    /**
     * 商品标题
     */
    private String businessTitle;

    /**
     * 主图
     */
    private String mainPic;

    /**
     * 商品分类ID
     */
    private Long typeId;

    /**
     * 券后价价格
     */
    private Double couponAfterPrice;

    /**
     * 优惠券金额
     */
    private Double couponPrice;

    /**
     * 佣金
     */
    private Double commissionPrice;

    /**
     * 比例
     */
    private Integer proportion;

    /**
     * 样品剩余数量
     */
    private Integer surplusNum;

    /**
     * 拍摄要求
     */
    private String shotRequirement;

    /**
     * 规则说明
     */
    private String ruleDescription;

    /**
     * 取样条件数量
     */
    private Integer conditionNum;

    /**
     * 押金金额
     */
    private Double depositPric;

    /**
     * 活动开始时间
     */
    private Date activityStartDate;

    /**
     * 活动结束时间
     */
    private Date activityEndDate;

    /**
     * 宝贝ID
     */
    private Long itemId;

    /**
     * 宝贝地址
     */
    private String itemUrl;

    /**
     * 推广链接
     */
    private String promotionLink;

    /**
     * 宝贝+券二合一推广链接
     */
    private String couponShareUrl;

    /**
     * 状态：1-正常、2-禁用、3-删除
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 商品详细
     */
    private String details;

    /**
     * b_commission_goods
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
     * 商品标题
     * @return business_title 商品标题
     */
    public String getBusinessTitle() {
        return businessTitle;
    }

    /**
     * 商品标题
     * @param businessTitle 商品标题
     */
    public void setBusinessTitle(String businessTitle) {
        this.businessTitle = businessTitle == null ? null : businessTitle.trim();
    }

    /**
     * 主图
     * @return main_pic 主图
     */
    public String getMainPic() {
        return mainPic;
    }

    /**
     * 主图
     * @param mainPic 主图
     */
    public void setMainPic(String mainPic) {
        this.mainPic = mainPic == null ? null : mainPic.trim();
    }

    /**
     * 商品分类ID
     * @return type_id 商品分类ID
     */
    public Long getTypeId() {
        return typeId;
    }

    /**
     * 商品分类ID
     * @param typeId 商品分类ID
     */
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    /**
     * 券后价价格
     * @return coupon_after_price 券后价价格
     */
    public Double getCouponAfterPrice() {
        return couponAfterPrice;
    }

    /**
     * 券后价价格
     * @param couponAfterPrice 券后价价格
     */
    public void setCouponAfterPrice(Double couponAfterPrice) {
        this.couponAfterPrice = couponAfterPrice;
    }

    /**
     * 优惠券金额
     * @return coupon_price 优惠券金额
     */
    public Double getCouponPrice() {
        return couponPrice;
    }

    /**
     * 优惠券金额
     * @param couponPrice 优惠券金额
     */
    public void setCouponPrice(Double couponPrice) {
        this.couponPrice = couponPrice;
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
     * 比例
     * @return proportion 比例
     */
    public Integer getProportion() {
        return proportion;
    }

    /**
     * 比例
     * @param proportion 比例
     */
    public void setProportion(Integer proportion) {
        this.proportion = proportion;
    }

    /**
     * 样品剩余数量
     * @return surplus_num 样品剩余数量
     */
    public Integer getSurplusNum() {
        return surplusNum;
    }

    /**
     * 样品剩余数量
     * @param surplusNum 样品剩余数量
     */
    public void setSurplusNum(Integer surplusNum) {
        this.surplusNum = surplusNum;
    }

    /**
     * 拍摄要求
     * @return shot_requirement 拍摄要求
     */
    public String getShotRequirement() {
        return shotRequirement;
    }

    /**
     * 拍摄要求
     * @param shotRequirement 拍摄要求
     */
    public void setShotRequirement(String shotRequirement) {
        this.shotRequirement = shotRequirement == null ? null : shotRequirement.trim();
    }

    /**
     * 规则说明
     * @return rule_description 规则说明
     */
    public String getRuleDescription() {
        return ruleDescription;
    }

    /**
     * 规则说明
     * @param ruleDescription 规则说明
     */
    public void setRuleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription == null ? null : ruleDescription.trim();
    }

    /**
     * 取样条件数量
     * @return condition_num 取样条件数量
     */
    public Integer getConditionNum() {
        return conditionNum;
    }

    /**
     * 取样条件数量
     * @param conditionNum 取样条件数量
     */
    public void setConditionNum(Integer conditionNum) {
        this.conditionNum = conditionNum;
    }

    /**
     * 押金金额
     * @return deposit_pric 押金金额
     */
    public Double getDepositPric() {
        return depositPric;
    }

    /**
     * 押金金额
     * @param depositPric 押金金额
     */
    public void setDepositPric(Double depositPric) {
        this.depositPric = depositPric;
    }

    /**
     * 活动开始时间
     * @return activity_start_date 活动开始时间
     */
    public Date getActivityStartDate() {
        return activityStartDate;
    }

    /**
     * 活动开始时间
     * @param activityStartDate 活动开始时间
     */
    public void setActivityStartDate(Date activityStartDate) {
        this.activityStartDate = activityStartDate;
    }

    /**
     * 活动结束时间
     * @return activity_end_date 活动结束时间
     */
    public Date getActivityEndDate() {
        return activityEndDate;
    }

    /**
     * 活动结束时间
     * @param activityEndDate 活动结束时间
     */
    public void setActivityEndDate(Date activityEndDate) {
        this.activityEndDate = activityEndDate;
    }

    /**
     * 宝贝ID
     * @return item_id 宝贝ID
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * 宝贝ID
     * @param itemId 宝贝ID
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * 宝贝地址
     * @return item_url 宝贝地址
     */
    public String getItemUrl() {
        return itemUrl;
    }

    /**
     * 宝贝地址
     * @param itemUrl 宝贝地址
     */
    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl == null ? null : itemUrl.trim();
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
     * 状态：1-正常、2-禁用、3-删除
     * @return status 状态：1-正常、2-禁用、3-删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态：1-正常、2-禁用、3-删除
     * @param status 状态：1-正常、2-禁用、3-删除
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * 商品详细
     * @return details 商品详细
     */
    public String getDetails() {
        return details;
    }

    /**
     * 商品详细
     * @param details 商品详细
     */
    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }
}