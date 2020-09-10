package com.flym.hrdh.pojo.common;

import java.io.Serializable;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:领样宣传管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class SamplingPropaganda implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 领样内容
     */
    private String content;

    /**
     * c_sampling_propaganda
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
     * 领样内容
     * @return content 领样内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 领样内容
     * @param content 领样内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}