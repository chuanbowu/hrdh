package com.flym.hrdh.pojo.partner;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:合伙人支付宝账号管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class PartnerAlipay implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 合伙人ID
     */
    private Long partnerId;

    /**
     * 账号
     */
    private String account;

    /**
     * 真实姓名
     */
    private String trueName;

    /**
     * 状态：1-正常、2-删除
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
     * p_partner_alipay
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
     * 账号
     * @return account 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 账号
     * @param account 账号
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 真实姓名
     * @return true_name 真实姓名
     */
    public String getTrueName() {
        return trueName;
    }

    /**
     * 真实姓名
     * @param trueName 真实姓名
     */
    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    /**
     * 状态：1-正常、2-删除
     * @return status 状态：1-正常、2-删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态：1-正常、2-删除
     * @param status 状态：1-正常、2-删除
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
}