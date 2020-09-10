package com.flym.hrdh.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:系统参数管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Component
public class HrdhAdminConfig {

    /**
     * 签名秘钥：web
     */
    public static String sign_web;

    /**
     *  文件基础位置
     */
    public static String file_base_replace_url;

    /**
     *   文件本地位置
     */
    public static String  file_local_url;

    /**
     *  图片上传格式限制
     */
    public static String upload_image_limit_format;

    /**
     * app包格式限制上传
     */
    public static String upload_app_package_limit_format;

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

    /**
     * mm_xxx_xxx_12345678三段式的最后一段数字
     */
    public static Long adzone_id;

    @Value("${upload_image_limit_format}")
    public void setUpload_image_limit_format(String upload_image_limit_format) {
        HrdhAdminConfig.upload_image_limit_format = upload_image_limit_format;
    }
    @Value("${upload_app_package_limit_format}")
    public void setUpload_app_package_limit_format(String upload_app_package_limit_format) {
        HrdhAdminConfig.upload_app_package_limit_format = upload_app_package_limit_format;
    }

    @Value("${sign_web}")
    public void setSign_web(String sign_web) {
        HrdhAdminConfig.sign_web = sign_web;
    }

    @Value("${file_base_replace_url}")
    public void setFile_base_replace_url(String file_base_replace_url) {
        HrdhAdminConfig.file_base_replace_url = file_base_replace_url;
    }
    @Value("${file_local_url}")
    public void setFile_local_url(String file_local_url) {
        HrdhAdminConfig.file_local_url = file_local_url;
    }


    @Value("${server_url}")
    public void setServer_url(String server_url) {
        HrdhAdminConfig.server_url = server_url;
    }

    @Value("${app_key}")
    public void setApp_key(String app_key) {
        HrdhAdminConfig.app_key = app_key;
    }

    @Value("${app_secret}")
    public void setApp_secret(String app_secret) {
        HrdhAdminConfig.app_secret = app_secret;
    }

    @Value("${adzone_id}")
    public void setAdzone_id(Long adzone_id) {
        HrdhAdminConfig.adzone_id = adzone_id;
    }
}
