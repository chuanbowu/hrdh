package com.flym.hrdh.pojo.common;

import java.io.Serializable;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:滚动宣传管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class RollPropaganda implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 滚动内容
     */
    private String content;

    /**
     * c_roll_propaganda
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
     * 滚动内容
     * @return content 滚动内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 滚动内容
     * @param content 滚动内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}