package com.flym.hrdh.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:系统参数管理</p>
 * <p>Copyright: Copyright (c) 2020-06-12</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Component
public class HrdhJobConfig {

    /**
     * 淘宝客请求地址
     */
    public static String server_url;

    /**
     * 淘宝客appKey
     */
    public static String app_key;

    /**
     * 淘宝客appSecret
     */
    public static String app_secret;

    @Value("${server_url}")
    public void setServer_url(String server_url) {
        HrdhJobConfig.server_url = server_url;
    }

    @Value("${app_key}")
    public void setApp_key(String app_key) {
        HrdhJobConfig.app_key = app_key;
    }

    @Value("${app_secret}")
    public void setApp_secret(String app_secret) {
        HrdhJobConfig.app_secret = app_secret;
    }
}
