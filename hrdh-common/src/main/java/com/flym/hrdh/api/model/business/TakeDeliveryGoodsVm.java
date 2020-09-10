package com.flym.hrdh.api.model.business;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:拿货商品管理</p>
 * <p>Copyright: Copyright (c) 2020-05-18</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class TakeDeliveryGoodsVm implements Serializable {

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
     * 商品价格
     */
    private Double goodsPrice;

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
     * 状态：1-正常、2-禁用、3-删除
     */
    private Integer status;

    /**
     * 创建人
     */
    private Long createSysUser;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改人
     */
    private Long modifySysUser;

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
     * 商家客服电话
     */
    private String customerPhone;

    /**
     * 商家客服微信二维码
     */
    private String customerWeChatPic;

    /**
     * 淘宝信誉等级：1-20
     */
    private Integer reputationType;

    /**
     * b_take_delivery_goods
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
     * 商品价格
     * @return goods_price 商品价格
     */
    public Double getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * 商品价格
     * @param goodsPrice 商品价格
     */
    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
     * 创建人
     * @return create_sys_user 创建人
     */
    public Long getCreateSysUser() {
        return createSysUser;
    }

    /**
     * 创建人
     * @param createSysUser 创建人
     */
    public void setCreateSysUser(Long createSysUser) {
        this.createSysUser = createSysUser;
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
     * 修改人
     * @return modify_sys_user 修改人
     */
    public Long getModifySysUser() {
        return modifySysUser;
    }

    /**
     * 修改人
     * @param modifySysUser 修改人
     */
    public void setModifySysUser(Long modifySysUser) {
        this.modifySysUser = modifySysUser;
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
}