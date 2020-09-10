package com.flym.hrdh.pojo.business;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:拿货商品管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class TakeDeliveryGoods implements Serializable {

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
     * 商品详细
     */
    private String details;

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