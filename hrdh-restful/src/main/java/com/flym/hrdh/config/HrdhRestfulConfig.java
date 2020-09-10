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
public class HrdhRestfulConfig {

    /**
     * 签名秘钥：h5
     */
    public static String sign_app;

    /**
     * 阿里云Oss
     */
    public static String oss_base_url;

    /**
     * 图片保存地址
     */
    public static String image_base_url;

    /**
     * 文件保存nginx基础路径
     */
    public static String nginx_file_save_base_url;

    /**
     * 图片上传格式限制
     */
    public static String upload_image_limit_format;

    /**
     * 领样宣传条数
     */
    public static Integer sampling_propaganda_num;

    /**
     * 淘宝客请求地址
     */
    public static String server_url;

    /**
     * 淘宝客请求地址:https
     */
    public static String server_https_url;

    /**
     * 淘宝客appKey
     */
    public static String app_key;

    /**
     * 淘宝客appSecret
     */
    public static String app_secret;

    /**
     * 渠道备案-淘宝客邀请渠道的邀请码
     */
    public static String inviter_code;

    /**
     * 淘宝客授权URL
     */
    public static String tao_bao_tbk_url;

    @Value("${nginx_file_save_base_url}")
    public  void setNginx_file_save_base_url(String nginx_file_save_base_url) {
        HrdhRestfulConfig.nginx_file_save_base_url = nginx_file_save_base_url;
    }

    @Value("${sign_app}")
    public void setSign_app(String sign_app) {
        HrdhRestfulConfig.sign_app = sign_app;
    }

    @Value("${oss_base_url}")
    public void setOss_base_url(String oss_base_url) {
        HrdhRestfulConfig.oss_base_url = oss_base_url;
    }

    @Value("${image_base_url}")
    public void setImage_base_url(String image_base_url) {
        HrdhRestfulConfig.image_base_url = image_base_url;
    }

    @Value("${upload_image_limit_format}")
    public void setUpload_image_limit_format(String upload_image_limit_format) {
        HrdhRestfulConfig.upload_image_limit_format = upload_image_limit_format;
    }

    @Value("${sampling_propaganda_num}")
    public void setSampling_propaganda_num(Integer sampling_propaganda_num) {
        HrdhRestfulConfig.sampling_propaganda_num = sampling_propaganda_num;
    }

    @Value("${server_url}")
    public void setServer_url(String server_url) {
        HrdhRestfulConfig.server_url = server_url;
    }

    @Value("${app_key}")
    public void setApp_key(String app_key) {
        HrdhRestfulConfig.app_key = app_key;
    }

    @Value("${app_secret}")
    public void setApp_secret(String app_secret) {
        HrdhRestfulConfig.app_secret = app_secret;
    }

    @Value("${inviter_code}")
    public void setInviter_code(String inviter_code) {
        HrdhRestfulConfig.inviter_code = inviter_code;
    }

    @Value("${tao_bao_tbk_url}")
    public void setTao_bao_tbk_url(String tao_bao_tbk_url) {
        HrdhRestfulConfig.tao_bao_tbk_url = tao_bao_tbk_url;
    }

    @Value("${server_https_url}")
    public void setServer_https_url(String server_https_url) {
        HrdhRestfulConfig.server_https_url = server_https_url;
    }
}
