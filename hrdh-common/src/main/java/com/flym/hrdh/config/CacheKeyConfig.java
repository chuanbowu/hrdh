package com.flym.hrdh.config;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description: 红人带货系统redis管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class CacheKeyConfig {

    /**
     * Redis模块：后台用户令牌
     */
    public final static String REDIS_SYS_USER_TOKEN = "_hrdh_sys_user_token_";

    /**
     * Redis模块：后台登录用户令牌
     */
    public final static String REDIS_SYS_USER_LOGIN_TOKEN = "_hrdh_sys_user_login_token_";

    /**
     * 后台令牌有效期，秒
     */
    public static final int REDIS_SYS_USER_LOGIN_TOKEN_VALID_TIME = 172800;

    /**
     * Redis模块：H5用户令牌
     */
    public final static String REDIS_MEMBER_TOKEN = "_hrdh_member_token_";

    /**
     * Redis模块：H5登录用户令牌
     */
    public final static String REDIS_MEMBER_LOGIN_TOKEN = "_hrdh_member_login_token_";

    /**
     * H5用户令牌有效期，秒
     */
    public static final int REDIS_MEMBER_LOGIN_TOKEN_VALID_TIME = 172800;

    /**
     * 所有验证码
     */
    public final static String REDIS_SMS_CODE = "sms_code";

    /**
     * Redis模块：验证码令牌
     */
    public final static String REDIS_SMS_CODE_TOKEN = "sms_code_token";

    /**
     * 验证码有效期，秒
     */
    public static final int REDIS_SMS_CODE_VALID_TIME = 300;

    /**
     * Redis模块：后台用户令牌
     */
    public final static String REDIS_BUSINESS_TOKEN = "_hrdh_business_token_";

    /**
     * Redis模块：后台登录用户令牌
     */
    public final static String REDIS_BUSINESS_LOGIN_TOKEN = "_hrdh_business_login_token_";

    /**
     * 后台令牌有效期，秒
     */
    public static final int REDIS_BUSINESS_LOGIN_TOKEN_VALID_TIME = 172800;

}
