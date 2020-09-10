package com.flym.hrdh.pojo.common;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:红人推荐管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class SensationRecommend implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 红人ID
     */
    private Long sensationId;

    /**
     * 找红人ID
     */
    private Long seekSensationId;

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
     * c_sensation_recommend
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
     * 找红人ID
     * @return seek_sensation_id 找红人ID
     */
    public Long getSeekSensationId() {
        return seekSensationId;
    }

    /**
     * 找红人ID
     * @param seekSensationId 找红人ID
     */
    public void setSeekSensationId(Long seekSensationId) {
        this.seekSensationId = seekSensationId;
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
}