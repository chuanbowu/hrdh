package com.flym.hrdh.pojo.common;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:商品数量配置管理</p>
 * <p>Copyright: Copyright (c) 2020-07-17</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class GoodsNumConfig implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 初级数量
     */
    private Integer primaryNum;

    /**
     * 中级数量
     */
    private Integer intermediateNum;

    /**
     * 高级数量
     */
    private Integer seniorNum;

    /**
     * 修改人
     */
    private Long modifySysUser;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * c_goods_num_config
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
     * 初级数量
     * @return primary_num 初级数量
     */
    public Integer getPrimaryNum() {
        return primaryNum;
    }

    /**
     * 初级数量
     * @param primaryNum 初级数量
     */
    public void setPrimaryNum(Integer primaryNum) {
        this.primaryNum = primaryNum;
    }

    /**
     * 中级数量
     * @return intermediate_num 中级数量
     */
    public Integer getIntermediateNum() {
        return intermediateNum;
    }

    /**
     * 中级数量
     * @param intermediateNum 中级数量
     */
    public void setIntermediateNum(Integer intermediateNum) {
        this.intermediateNum = intermediateNum;
    }

    /**
     * 高级数量
     * @return senior_num 高级数量
     */
    public Integer getSeniorNum() {
        return seniorNum;
    }

    /**
     * 高级数量
     * @param seniorNum 高级数量
     */
    public void setSeniorNum(Integer seniorNum) {
        this.seniorNum = seniorNum;
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