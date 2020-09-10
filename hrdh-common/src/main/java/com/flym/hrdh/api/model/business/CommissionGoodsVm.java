package com.flym.hrdh.api.model.business;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:佣金商品管理</p>
 * <p>Copyright: Copyright (c) 2020-05-18</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class CommissionGoodsVm implements Serializable {

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
     * 商品分类名称
     */
    private String typeName;

    /**
     * 商品详细
     */
    private String details;

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
     * 商家手机号
     */
    private String phone;

    /**
     * 商家头像
     */
    private String headPic;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 宝贝描述
     */
    private Double babyDescription;

    /**
     * 卖家服务
     */
    private Double sellerServices;

    /**
     * 物流服务
     */
    private Double logisticsService;

    /**
     * 商家等级：1-初级、2-中级、3-高级
     */
    private Integer gradeType;

    /**
     * 淘宝信誉等级：1-20
     */
    private Integer reputationType;

    /**
     * 商家客服电话
     */
    private String customerPhone;

    /**
     * 商家客服微信二维码
     */
    private String customerWeChatPic;

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

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
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

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Double getBabyDescription() {
        return babyDescription;
    }

    public void setBabyDescription(Double babyDescription) {
        this.babyDescription = babyDescription;
    }

    public Double getSellerServices() {
        return sellerServices;
    }

    public void setSellerServices(Double sellerServices) {
        this.sellerServices = sellerServices;
    }

    public Double getLogisticsService() {
        return logisticsService;
    }

    public void setLogisticsService(Double logisticsService) {
        this.logisticsService = logisticsService;
    }

    public Integer getGradeType() {
        return gradeType;
    }

    public void setGradeType(Integer gradeType) {
        this.gradeType = gradeType;
    }

    public Integer getReputationType() {
        return reputationType;
    }

    public void setReputationType(Integer reputationType) {
        this.reputationType = reputationType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public String getCouponShareUrl() {
        return couponShareUrl;
    }

    public void setCouponShareUrl(String couponShareUrl) {
        this.couponShareUrl = couponShareUrl;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerWeChatPic() {
        return customerWeChatPic;
    }

    public void setCustomerWeChatPic(String customerWeChatPic) {
        this.customerWeChatPic = customerWeChatPic;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}