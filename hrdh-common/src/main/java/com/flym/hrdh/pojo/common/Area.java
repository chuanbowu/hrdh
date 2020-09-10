package com.flym.hrdh.pojo.common;

import java.io.Serializable;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:区管理</p>
 * <p>Copyright: Copyright (c) 2020-05-12</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class Area implements Serializable {

    /**
     * 城市ID
     */
    private String sid;

    /**
     * 区
     */
    private String name;

    /**
     * 区ID
     */
    private String cid;

    /**
     * c_area
     */
    private static final long serialVersionUID = 1L;

    /**
     * 城市ID
     * @return sid 城市ID
     */
    public String getSid() {
        return sid;
    }

    /**
     * 城市ID
     * @param sid 城市ID
     */
    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    /**
     * 区
     * @return name 区
     */
    public String getName() {
        return name;
    }

    /**
     * 区
     * @param name 区
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 区ID
     * @return cid 区ID
     */
    public String getCid() {
        return cid;
    }

    /**
     * 区ID
     * @param cid 区ID
     */
    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }
}