package com.flym.hrdh.pojo.common;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:支付宝自动提现配置管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class AliPayWithdrawConfig implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * APPID
     */
    private String appId;

    /**
     * 付款方姓名
     */
    private String payerShowName;

    /**
     * 转账备注
     */
    private String remark;

    /**
     * 状态：1-正常、2-删除
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
     * 应用私钥
     */
    private String privateKey;

    /**
     * 支付宝公钥
     */
    private String publicKey;

    /**
     * c_ali_pay_withdraw_config
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
     * APPID
     * @return app_id APPID
     */
    public String getAppId() {
        return appId;
    }

    /**
     * APPID
     * @param appId APPID
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * 付款方姓名
     * @return payer_show_name 付款方姓名
     */
    public String getPayerShowName() {
        return payerShowName;
    }

    /**
     * 付款方姓名
     * @param payerShowName 付款方姓名
     */
    public void setPayerShowName(String payerShowName) {
        this.payerShowName = payerShowName == null ? null : payerShowName.trim();
    }

    /**
     * 转账备注
     * @return remark 转账备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 转账备注
     * @param remark 转账备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    /**
     * 应用私钥
     * @return private_key 应用私钥
     */
    public String getPrivateKey() {
        return privateKey;
    }

    /**
     * 应用私钥
     * @param privateKey 应用私钥
     */
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey == null ? null : privateKey.trim();
    }

    /**
     * 支付宝公钥
     * @return public_key 支付宝公钥
     */
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * 支付宝公钥
     * @param publicKey 支付宝公钥
     */
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey == null ? null : publicKey.trim();
    }
}