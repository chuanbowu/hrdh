package com.flym.hrdh.pojo.common;

import java.io.Serializable;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:省份管理</p>
 * <p>Copyright: Copyright (c) 2020-05-12</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class Province implements Serializable {

    /**
     * 省份
     */
    private String name;

    /**
     * 省份ID
     */
    private String cid;

    /**
     * c_province
     */
    private static final long serialVersionUID = 1L;

    /**
     * 省份ID
     * @return name 省份ID
     */
    public String getName() {
        return name;
    }

    /**
     * 省份ID
     * @param name 省份ID
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 省份
     * @return cid 省份
     */
    public String getCid() {
        return cid;
    }

    /**
     * 省份
     * @param cid 省份
     */
    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }
}