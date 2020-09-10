package com.flym.hrdh.api.model.sensation;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:红人提现明细管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class SensationWithdrawInfoVm implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 红人ID
     */
    private Long sensationId;

    /**
     * 手机号码
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
     * 账号
     */
    private String account;

    /**
     * 真实姓名
     */
    private String trueName;

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
     * 返回所有信息
     */
    private String returnInfo;

    /**
     * 审核人
     */
    private Long checkSysUser;

    /**
     * 审核人名称
     */
    private String checkSysUserName;

    /**
     * 拒绝理由
     */
    private String refuseContent;

    /**
     * 审核时间
     */
    private Date checkDate;

    /**
     * s_sensation_withdraw_info
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

    /**
     * 返回所有信息
     * @return return_info 返回所有信息
     */
    public String getReturnInfo() {
        return returnInfo;
    }

    /**
     * 返回所有信息
     * @param returnInfo 返回所有信息
     */
    public void setReturnInfo(String returnInfo) {
        this.returnInfo = returnInfo == null ? null : returnInfo.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public Long getCheckSysUser() {
        return checkSysUser;
    }

    public void setCheckSysUser(Long checkSysUser) {
        this.checkSysUser = checkSysUser;
    }

    public String getRefuseContent() {
        return refuseContent;
    }

    public void setRefuseContent(String refuseContent) {
        this.refuseContent = refuseContent;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckSysUserName() {
        return checkSysUserName;
    }

    public void setCheckSysUserName(String checkSysUserName) {
        this.checkSysUserName = checkSysUserName;
    }
}
