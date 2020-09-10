package com.flym.hrdh.pojo.common;

import java.io.Serializable;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:城市管理</p>
 * <p>Copyright: Copyright (c) 2020-05-12</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class City implements Serializable {

    /**
     * 省份ID
     */
    private String sid;

    /**
     * 城市
     */
    private String name;

    /**
     * 城市ID
     */
    private String cid;

    /**
     * c_city
     */
    private static final long serialVersionUID = 1L;

    /**
     * 省份ID
     * @return sid 省份ID
     */
    public String getSid() {
        return sid;
    }

    /**
     * 省份ID
     * @param sid 省份ID
     */
    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    /**
     * 城市
     * @return name 城市
     */
    public String getName() {
        return name;
    }

    /**
     * 城市
     * @param name 城市
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 城市
     * @return cid 城市
     */
    public String getCid() {
        return cid;
    }

    /**
     * 城市
     * @param cid 城市
     */
    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }
}