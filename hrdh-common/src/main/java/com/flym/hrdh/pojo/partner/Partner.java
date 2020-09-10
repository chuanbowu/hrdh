package com.flym.hrdh.pojo.partner;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:合伙人管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class Partner implements Serializable {

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
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String name;

    /**
     * 身份证号码
     */
    private String cardNumber;

    /**
     * 身份证图片
     */
    private String cardPic;

    /**
     * 邀请码
     */
    private String code;

    /**
     * 商家入驻费率
     */
    private Double businessEntryRate;

    /**
     * 佣金费率
     */
    private Double commissionRate;

    /**
     * 余额
     */
    private Double balancePrice;

    /**
     * 预估收入余额
     */
    private Double estimatedRevenuePrice;

    /**
     * 状态：1-正常、2-禁用、3-删除
     */
    private Integer status;

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
     * p_partner
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
     * 昵称
     * @return name 昵称
     */
    public String getName() {
        return name;
    }

    /**
     * 昵称
     * @param name 昵称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 身份证号码
     * @return card_number 身份证号码
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * 身份证号码
     * @param cardNumber 身份证号码
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber == null ? null : cardNumber.trim();
    }

    /**
     * 身份证图片
     * @return card_pic 身份证图片
     */
    public String getCardPic() {
        return cardPic;
    }

    /**
     * 身份证图片
     * @param cardPic 身份证图片
     */
    public void setCardPic(String cardPic) {
        this.cardPic = cardPic == null ? null : cardPic.trim();
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
     * 商家入驻费率
     * @return business_entry_rate 商家入驻费率
     */
    public Double getBusinessEntryRate() {
        return businessEntryRate;
    }

    /**
     * 商家入驻费率
     * @param businessEntryRate 商家入驻费率
     */
    public void setBusinessEntryRate(Double businessEntryRate) {
        this.businessEntryRate = businessEntryRate;
    }

    /**
     * 佣金费率
     * @return commission_rate 佣金费率
     */
    public Double getCommissionRate() {
        return commissionRate;
    }

    /**
     * 佣金费率
     * @param commissionRate 佣金费率
     */
    public void setCommissionRate(Double commissionRate) {
        this.commissionRate = commissionRate;
    }

    /**
     * 余额
     * @return balance_price 余额
     */
    public Double getBalancePrice() {
        return balancePrice;
    }

    /**
     * 余额
     * @param balancePrice 余额
     */
    public void setBalancePrice(Double balancePrice) {
        this.balancePrice = balancePrice;
    }

    /**
     * 预估收入余额
     * @return estimated_revenue_price 预估收入余额
     */
    public Double getEstimatedRevenuePrice() {
        return estimatedRevenuePrice;
    }

    /**
     * 预估收入余额
     * @param estimatedRevenuePrice 预估收入余额
     */
    public void setEstimatedRevenuePrice(Double estimatedRevenuePrice) {
        this.estimatedRevenuePrice = estimatedRevenuePrice;
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