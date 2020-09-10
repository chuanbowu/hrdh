package com.flym.hrdh.pojo.business;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:商家管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class Business implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String headPic;

    /**
     * 密码
     */
    private String password;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 淘宝或天猫店铺地址
     */
    private String shopAddress;

    /**
     * 合伙人ID
     */
    private Long partnerId;

    /**
     * 邀请码
     */
    private String code;

    /**
     * 收样人姓名
     */
    private String consigneeName;

    /**
     * 收样的地址
     */
    private String consigneeAddress;

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
     * 状态：1-正常、2-禁用、3-删除
     */
    private Integer status;

    /**
     * 客服电话
     */
    private String customerPhone;

    /**
     * 客服微信二维码
     */
    private String customerWeChatPic;

    /**
     * 登录时间
     */
    private Date loginDate;

    /**
     * 登录IP
     */
    private String loginIp;

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
     * b_business
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
     * 手机号
     * @return phone 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 昵称
     * @return nick_name 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 昵称
     * @param nickName 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * 头像
     * @return head_pic 头像
     */
    public String getHeadPic() {
        return headPic;
    }

    /**
     * 头像
     * @param headPic 头像
     */
    public void setHeadPic(String headPic) {
        this.headPic = headPic == null ? null : headPic.trim();
    }

    /**
     * 密码
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 店铺名称
     * @return shop_name 店铺名称
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 店铺名称
     * @param shopName 店铺名称
     */
    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    /**
     * 淘宝或天猫店铺地址
     * @return shop_address 淘宝或天猫店铺地址
     */
    public String getShopAddress() {
        return shopAddress;
    }

    /**
     * 淘宝或天猫店铺地址
     * @param shopAddress 淘宝或天猫店铺地址
     */
    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress == null ? null : shopAddress.trim();
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
     * 邀请码
     * @return code 邀请码
     */
    public String getCode() {
        return code;
    }

    /**
     * 邀请码
     * @param code 邀请码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 收样人姓名
     * @return consignee_name 收样人姓名
     */
    public String getConsigneeName() {
        return consigneeName;
    }

    /**
     * 收样人姓名
     * @param consigneeName 收样人姓名
     */
    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName == null ? null : consigneeName.trim();
    }

    /**
     * 收样的地址
     * @return consignee_address 收样的地址
     */
    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    /**
     * 收样的地址
     * @param consigneeAddress 收样的地址
     */
    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress == null ? null : consigneeAddress.trim();
    }

    /**
     * 宝贝描述
     * @return baby_description 宝贝描述
     */
    public Double getBabyDescription() {
        return babyDescription;
    }

    /**
     * 宝贝描述
     * @param babyDescription 宝贝描述
     */
    public void setBabyDescription(Double babyDescription) {
        this.babyDescription = babyDescription;
    }

    /**
     * 卖家服务
     * @return seller_services 卖家服务
     */
    public Double getSellerServices() {
        return sellerServices;
    }

    /**
     * 卖家服务
     * @param sellerServices 卖家服务
     */
    public void setSellerServices(Double sellerServices) {
        this.sellerServices = sellerServices;
    }

    /**
     * 物流服务
     * @return logistics_service 物流服务
     */
    public Double getLogisticsService() {
        return logisticsService;
    }

    /**
     * 物流服务
     * @param logisticsService 物流服务
     */
    public void setLogisticsService(Double logisticsService) {
        this.logisticsService = logisticsService;
    }

    /**
     * 商家等级：1-初级、2-中级、3-高级
     * @return grade_type 商家等级：1-初级、2-中级、3-高级
     */
    public Integer getGradeType() {
        return gradeType;
    }

    /**
     * 商家等级：1-初级、2-中级、3-高级
     * @param gradeType 商家等级：1-初级、2-中级、3-高级
     */
    public void setGradeType(Integer gradeType) {
        this.gradeType = gradeType;
    }

    /**
     * 淘宝信誉等级：1-20
     * @return reputation_type 淘宝信誉等级：1-20
     */
    public Integer getReputationType() {
        return reputationType;
    }

    /**
     * 淘宝信誉等级：1-20
     * @param reputationType 淘宝信誉等级：1-20
     */
    public void setReputationType(Integer reputationType) {
        this.reputationType = reputationType;
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
     * 客服电话
     * @return customer_phone 客服电话
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * 客服电话
     * @param customerPhone 客服电话
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone == null ? null : customerPhone.trim();
    }

    /**
     * 客服微信二维码
     * @return customer_we_chat_pic 客服微信二维码
     */
    public String getCustomerWeChatPic() {
        return customerWeChatPic;
    }

    /**
     * 客服微信二维码
     * @param customerWeChatPic 客服微信二维码
     */
    public void setCustomerWeChatPic(String customerWeChatPic) {
        this.customerWeChatPic = customerWeChatPic == null ? null : customerWeChatPic.trim();
    }

    /**
     * 登录时间
     * @return login_date 登录时间
     */
    public Date getLoginDate() {
        return loginDate;
    }

    /**
     * 登录时间
     * @param loginDate 登录时间
     */
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * 登录IP
     * @return login_ip 登录IP
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 登录IP
     * @param loginIp 登录IP
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
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
}