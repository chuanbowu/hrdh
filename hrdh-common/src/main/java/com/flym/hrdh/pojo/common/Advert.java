package com.flym.hrdh.pojo.common;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:轮播图管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class Advert implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 轮播图
     */
    private String advertPic;

    /**
     * 排序号
     */
    private Integer sort;

    /**
     * 类型：1-返佣商品、2-拿货商品
     */
    private Integer type;

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
     * c_advert
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
     * 商品ID
     * @return goods_id 商品ID
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 商品ID
     * @param goodsId 商品ID
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 轮播图
     * @return advert_pic 轮播图
     */
    public String getAdvertPic() {
        return advertPic;
    }

    /**
     * 轮播图
     * @param advertPic 轮播图
     */
    public void setAdvertPic(String advertPic) {
        this.advertPic = advertPic == null ? null : advertPic.trim();
    }

    /**
     * 排序号
     * @return sort 排序号
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 排序号
     * @param sort 排序号
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 类型：1-返佣商品、2-拿货商品
     * @return type 类型：1-返佣商品、2-拿货商品
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型：1-返佣商品、2-拿货商品
     * @param type 类型：1-返佣商品、2-拿货商品
     */
    public void setType(Integer type) {
        this.type = type;
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
}