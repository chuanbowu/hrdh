package com.flym.hrdh.config;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:接口签名管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class SignUtil {

    /**
     * 获取签名秘钥
     * @param  key 秘钥
     * @return 签名秘钥
     */
    public static String getSecretKey(String key){
        if("h5".equals(key)){
            return HrdhRestfulConfig.sign_app;
        }else{
            return "";
        }
    }

    /**
     * MD5签名
     * @param params 各个信息字段信息
     * @param secretKey 签名秘钥
     * @return 签名值
     */
    public static String getSign(Map<String, String> params, String secretKey){

        //将参数以参数名的字典升序排序
        Map<String, String> sortParams = new TreeMap<String, String>(params);
        Set<Map.Entry<String, String>> entrys = sortParams.entrySet();

        //遍历排序的字典,并拼接"key=value"格式
        StringBuilder baseString = new StringBuilder();
        for(Map.Entry<String, String> entry : entrys){

            if(!StringUtils.equals(entry.getKey(),"sign")){
                baseString.append(entry.getKey()).append("=").append(String.valueOf(entry.getValue()));
            }
        }
        //app_secret
        baseString.append(secretKey);

        //使用MD5对待签名串获取签名值
        byte[] bytes = null;
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            try {
                bytes = md5.digest(baseString.toString().getBytes("UTF-8"));

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //将MD5输出的二进制结果转换为小写的十六进制
        String sign= getHexString(bytes);
        return sign.toString();
    }

    /**
     * 判断MD5签名是否一致
     * @param params 各个信息字段信息、
     * @param secretKey 签名秘钥
     * @return 签名是否一致
     */
    public static boolean checkSign(Map<String, String> params, String secretKey){
        String sign = params.get("sign");
        String checkSign = getSign(params,secretKey);

        if(StringUtils.equals(sign,checkSign)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 判断MD5签名是否一致
     * @param baseString 各个信息字段信息、
     * @param sign 签名
     * @return 签名是否一致
     */
    public static boolean checkSign(String baseString, String sign){
        //使用MD5对待签名串获取签名值
        byte[] bytes = null;
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            try {
                bytes = md5.digest(baseString.getBytes("UTF-8"));

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //将MD5输出的二进制结果转换为小写的十六进制
        String sign1 = getHexString(bytes);
        if(sign.equals(sign1.toString())){
            return true;
        }else{
            return false;
        }
    }

    public static String getHexString(byte[] bytes){
        StringBuilder hexStr = new StringBuilder();
        for(int i=0; i<bytes.length; i++){
            String hex = Integer.toHexString(bytes[i]&0xFF);
            if(hex.length() == 1){
                hexStr.append("0");
            }
            hexStr.append(hex);
        }
        return hexStr.toString();
    }

}
