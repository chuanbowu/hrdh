package com.flym.hrdh.api.model.sensation;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:红人账户明细管理</p>
 * <p>Copyright: Copyright (c) 2020-06-14</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class SensationAccountInfoVm implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 红人ID
     */
    private Long sensationId;

    /**
     * 明细类型：1-佣金收入、2-提现申请、3-提现失败、4-佣金预估收入
     */
    private Integer infoType;

    /**
     * 红人当前余额
     */
    private Double validPrice;

    /**
     * 金额数量
     */
    private Double infoNum;

    /**
     * 状态：1-增加、2-减少
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 今日流水数量
     */
    private Double dayPrice;

    /**
     * 今日预估流水数量
     */
    private Double dayEstimatedPrice;

    /**
     * 本月流水数量
     */
    private Double monthPrice;

    /**
     * 流水预估流水数量
     */
    private Double monthEstimatedPrice;

    /**
     * 总流水数量
     */
    private Double wholePrice;

    /**
     * 总预估流水数量
     */
    private Double wholeEstimatedPrice;

    /**
     * s_sensation_account_info
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
     * 明细类型：1-佣金收入、2-提现申请、3-提现失败、4-佣金预估收入
     * @return info_type 明细类型：1-佣金收入、2-提现申请、3-提现失败、4-佣金预估收入
     */
    public Integer getInfoType() {
        return infoType;
    }

    /**
     * 明细类型：1-佣金收入、2-提现申请、3-提现失败、4-佣金预估收入
     * @param infoType 明细类型：1-佣金收入、2-提现申请、3-提现失败、4-佣金预估收入
     */
    public void setInfoType(Integer infoType) {
        this.infoType = infoType;
    }

    /**
     * 红人当前余额
     * @return valid_price 红人当前余额
     */
    public Double getValidPrice() {
        return validPrice;
    }

    /**
     * 红人当前余额
     * @param validPrice 红人当前余额
     */
    public void setValidPrice(Double validPrice) {
        this.validPrice = validPrice;
    }

    /**
     * 金额数量
     * @return info_num 金额数量
     */
    public Double getInfoNum() {
        return infoNum;
    }

    /**
     * 金额数量
     * @param infoNum 金额数量
     */
    public void setInfoNum(Double infoNum) {
        this.infoNum = infoNum;
    }

    /**
     * 状态：1-增加、2-减少
     * @return status 状态：1-增加、2-减少
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态：1-增加、2-减少
     * @param status 状态：1-增加、2-减少
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

    public Double getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(Double dayPrice) {
        this.dayPrice = dayPrice;
    }

    public Double getDayEstimatedPrice() {
        return dayEstimatedPrice;
    }

    public void setDayEstimatedPrice(Double dayEstimatedPrice) {
        this.dayEstimatedPrice = dayEstimatedPrice;
    }

    public Double getMonthPrice() {
        return monthPrice;
    }

    public void setMonthPrice(Double monthPrice) {
        this.monthPrice = monthPrice;
    }

    public Double getMonthEstimatedPrice() {
        return monthEstimatedPrice;
    }

    public void setMonthEstimatedPrice(Double monthEstimatedPrice) {
        this.monthEstimatedPrice = monthEstimatedPrice;
    }

    public Double getWholePrice() {
        return wholePrice;
    }

    public void setWholePrice(Double wholePrice) {
        this.wholePrice = wholePrice;
    }

    public Double getWholeEstimatedPrice() {
        return wholeEstimatedPrice;
    }

    public void setWholeEstimatedPrice(Double wholeEstimatedPrice) {
        this.wholeEstimatedPrice = wholeEstimatedPrice;
    }
}