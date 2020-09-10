package com.flym.hrdh.pojo.common;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:快递公司管理</p>
 * <p>Copyright: Copyright (c) 2020-06-04</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class Express implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 快递名称
     */
    private String expressName;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * c_express
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
     * 快递名称
     * @return express_name 快递名称
     */
    public String getExpressName() {
        return expressName;
    }

    /**
     * 快递名称
     * @param expressName 快递名称
     */
    public void setExpressName(String expressName) {
        this.expressName = expressName == null ? null : expressName.trim();
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
}