package com.flym.hrdh.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description: 正则处理工具</p>
 * <p>Copyright: Copyright (c) 2019-01-07</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class RegularUtils {

    /**
     * 手机号验证
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;

        // 验证手机号
        String s2="^1[34578]\\d{9}$";

        if(StringUtils.isNotBlank(str)){
            p = Pattern.compile(s2);
            m = p.matcher(str);
            b = m.matches();
        }
        return b;
    }

    /**
     * 验证密码
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isPassword(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;

        // 验证密码长度是否大于六位小于十六位
        String s2="^[a-zA-Z\\d_]{6,16}$";

        if(StringUtils.isNotBlank(str)){
            p = Pattern.compile(s2);
            m = p.matcher(str);
            b = m.matches();
        }
        return b;
    }

    /**
     * 验证IP
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isIp(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;

        String s2="((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}";

        String[] arr = str.split(",");

        p = Pattern.compile(s2);
        for (int i = 0; i < arr.length; i++){

            String ip = arr[i];

            m = p.matcher(ip);
            b = m.matches();
            if(!b){
                break;
            }
        }

        return b;
    }

    public static void main(String[] args) {
        System.out.println(RegularUtils.isPassword("123456"));
        System.out.println(RegularUtils.isMobile("13800138000"));
    }
}
