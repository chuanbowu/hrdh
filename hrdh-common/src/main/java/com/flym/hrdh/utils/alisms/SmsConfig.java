package com.flym.hrdh.utils.alisms;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:阿里云通讯参数管理</p>
 * <p>Copyright: Copyright (c) 2020-05-06</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class SmsConfig {

    /**
     * 阿里云——access_key_id
     */
    public static final String ALIYUN_SMS_ACCESS_KEY_ID = "LTAI4G78tFCJe41EcHC8FXHL";
    /**
     * 阿里云——access_key_secret
     */
    public static final String ALIYUN_SMS_ACCESS_KEY_SECRET = "dPNLJSvWGPpfeiVgKA0KnC0mxX69Di";

    /**
     * 阿里云通讯-短信签名
     */
    public final static String ALIYUN_SMS_SIGN_NAME = "传播物";

    /**
     * 发送短信-参数类型：连接超时
     */
    public static final String SYSTEM_KEY_CONNECT_TIMEOUT = "sun.net.client.defaultConnectTimeout";
    /**
     * 发送短信-参数值：连接超时
     */
    public static final String CONNECT_TIMEOUT_TIME = "10000";
    /**
     * 发送短信-参数类型：读取超时
     */
    public static final String SYSTEM_KEY_READ_TIMEOUT = "sun.net.client.defaultReadTimeout";
    /**
     * 发送短信-参数值：读取超时
     */
    public static final String READ_TIMEOUT_TIME = "10000";

    /**
     * 短信API产品名称（短信产品名固定，无需修改）
     */
    public static final String DY_SMS_API_PRODUCT = "Dysmsapi";
    /**
     * 短信API产品域名（接口地址固定，无需修改）
     */
    public static final String DO_MAIN = "dysmsapi.aliyuncs.com";
    /**
     * 端点名称
     */
    public static final String ENDPOINT_NAME = "cn-hangzhou";
    /**
     * 地区ID
     */
    public static final String REGION_ID = "cn-hangzhou";

}
