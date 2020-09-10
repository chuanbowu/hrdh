package com.flym.hrdh.controller;

import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.config.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:基础接口信息管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class BaseController {

    /**
     * 检测appKey和签名是否有效
     * @param map 接口参数信息
     * @return ResponseMessage
     */
    public ResponseMessage checkNotLogin(IHrdhCacheService hrdhCacheService, Map<String, String> map){

        ResponseMessage result = new ResponseMessage();

        //获取平台秘钥
        String secretKey = SignUtil.getSecretKey(map.get("appKey"));

        //检测appKey是否有效
        if(StringUtils.isEmpty(secretKey)){
            result.setResult("100010");
            result.setMessage(ResultCode.result_100010);
            return result;
        }
        //检测签名是否有效
        boolean isSign = SignUtil.checkSign(map, secretKey);
        if(!isSign){
            result.setResult("100020");
            result.setMessage(ResultCode.result_100020);
            return result;
        }

        //延迟用户令牌不能为空
        String token = map.get("token");
        if(StringUtils.isNotEmpty(token)){
            //设置用户令牌失效时间
            hrdhCacheService.setCacheTime(token, CacheKeyConfig.REDIS_SYS_USER_LOGIN_TOKEN_VALID_TIME);
            //设置用户登录令牌失效时间
            String userStrId = hrdhCacheService.getCache(token);
            String userToken = userStrId + CacheKeyConfig.REDIS_SYS_USER_LOGIN_TOKEN;
            hrdhCacheService.setCacheTime(userToken, CacheKeyConfig.REDIS_SYS_USER_LOGIN_TOKEN_VALID_TIME);
        }

        return result;
    }

    /**
     * 检测appKey和token是否有效
     * @param hrdhCacheService
     * @param secretKey
     * @param token
     * @return
     */
    public ResponseMessage  checkNotSign(IHrdhCacheService hrdhCacheService, String secretKey, String token){

        ResponseMessage result = new ResponseMessage();

        //检测appKey是否有效
        if(StringUtils.isEmpty(secretKey)){
            result.setResult("100010");
            result.setMessage(ResultCode.result_100010);
            return result;
        }
        //检测用户令牌不能为空
        if(StringUtils.isEmpty(token)){
            result.setResult("100030");
            result.setMessage(ResultCode.result_100030);
            return result;
        }

        String userStrId = hrdhCacheService.getCache(token);
        if(StringUtils.isEmpty(userStrId)){
            result.setResult("100040");
            result.setMessage(ResultCode.result_100040);
            return result;
        }else{
            //设置用户令牌失效时间:24小时
            hrdhCacheService.setCacheTime(token, CacheKeyConfig.REDIS_SYS_USER_LOGIN_TOKEN_VALID_TIME);
            //设置用户登录令牌失效时间
            String userToken = userStrId + CacheKeyConfig.REDIS_SYS_USER_LOGIN_TOKEN;
            hrdhCacheService.setCacheTime(userToken, CacheKeyConfig.REDIS_SYS_USER_LOGIN_TOKEN_VALID_TIME);

            JSONObject jb = new JSONObject();
            jb.put("userId", Long.parseLong(userStrId));
            result.setDatas(jb);
        }
        return result;
    }

    /**
     * 检测appKey、token、签名是否有效
     * @param hrdhCacheService 获取缓存信息
     * @param map 接口参数信息
     * @return ResponseMessage
     */
    public ResponseMessage checkLogin(IHrdhCacheService hrdhCacheService, Map<String, String> map){

        ResponseMessage result = new ResponseMessage();

        //获取平台秘钥
        String secretKey = SignUtil.getSecretKey(map.get("appKey"));

        //检测appKey是否有效
        if(StringUtils.isEmpty(secretKey)){
            result.setResult("100010");
            result.setMessage(ResultCode.result_100010);
            return result;
        }
        //检测签名是否有效
        boolean isSign = SignUtil.checkSign(map, secretKey);
        if(!isSign){
            result.setResult("100020");
            result.setMessage(ResultCode.result_100020);
            return result;
        }

        //检测用户令牌不能为空
        String token = map.get("token");
        if(StringUtils.isEmpty(token)){
            result.setResult("100030");
            result.setMessage(ResultCode.result_100030);
            return result;
        }

        String userStrId = hrdhCacheService.getCache(token);
        if(StringUtils.isEmpty(userStrId)){
            result.setResult("100040");
            result.setMessage(ResultCode.result_100040);
            return result;
        }else{
            //设置用户令牌失效时间:24小时
            hrdhCacheService.setCacheTime(token, CacheKeyConfig.REDIS_SYS_USER_LOGIN_TOKEN_VALID_TIME);
            //设置用户登录令牌失效时间
            String userToken = userStrId + CacheKeyConfig.REDIS_SYS_USER_LOGIN_TOKEN;
            hrdhCacheService.setCacheTime(userToken, CacheKeyConfig.REDIS_SYS_USER_LOGIN_TOKEN_VALID_TIME);

            JSONObject jb = new JSONObject();
            jb.put("userId", Long.parseLong(userStrId));
            result.setDatas(jb);
        }
        return result;
    }

    /**
     * 检测appKey和签名是否有效
     * @param map 接口参数信息
     * @return ResponseMessage
     */
    public ResponseMessage businessCheckNotLogin(IHrdhCacheService hrdhCacheService, Map<String, String> map){

        ResponseMessage result = new ResponseMessage();

        //获取平台秘钥
        String secretKey = SignUtil.getSecretKey(map.get("appKey"));

        //检测appKey是否有效
        if(StringUtils.isEmpty(secretKey)){
            result.setResult("100010");
            result.setMessage(BusinessResultCode.result_100010);
            return result;
        }
        //检测签名是否有效
        boolean isSign = SignUtil.checkSign(map, secretKey);
        if(!isSign){
            result.setResult("100020");
            result.setMessage(BusinessResultCode.result_100020);
            return result;
        }

        //延迟用户令牌不能为空
        String token = map.get("token");
        if(StringUtils.isNotBlank(token)){
            //设置用户令牌失效时间
            hrdhCacheService.setCacheTime(token, CacheKeyConfig.REDIS_BUSINESS_LOGIN_TOKEN_VALID_TIME);
            //设置用户登录令牌失效时间
            String userStrId = hrdhCacheService.getCache(token);
            String userToken = userStrId + CacheKeyConfig.REDIS_BUSINESS_LOGIN_TOKEN;
            hrdhCacheService.setCacheTime(userToken, CacheKeyConfig.REDIS_BUSINESS_LOGIN_TOKEN_VALID_TIME);
        }

        return result;
    }

    /**
     * 检测appKey、token、签名是否有效
     * @param hrdhCacheService 获取缓存信息
     * @param map 接口参数信息
     * @return ResponseMessage
     */
    public ResponseMessage businessCheckLogin(IHrdhCacheService hrdhCacheService, Map<String, String> map){

        ResponseMessage result = new ResponseMessage();

        //获取平台秘钥
        String secretKey = SignUtil.getSecretKey(map.get("appKey"));

        //检测appKey是否有效
        if(StringUtils.isEmpty(secretKey)){
            result.setResult("100010");
            result.setMessage(BusinessResultCode.result_100010);
            return result;
        }
        //检测签名是否有效
        boolean isSign = SignUtil.checkSign(map, secretKey);
       /* if(!isSign){
            result.setResult("100020");
            result.setMessage(BusinessResultCode.result_100020);
            return result;
        }*/

        //检测用户令牌不能为空
        String token = map.get("token");
        if(StringUtils.isEmpty(token)){
            result.setResult("100030");
            result.setMessage(BusinessResultCode.result_100030);
            return result;
        }

        String userStrId = hrdhCacheService.getCache(token);
        if(StringUtils.isEmpty(userStrId)){
            result.setResult("100040");
            result.setMessage(BusinessResultCode.result_100040);
            return result;
        }else{
            //设置用户令牌失效时间:24小时
            hrdhCacheService.setCacheTime(token, CacheKeyConfig.REDIS_BUSINESS_LOGIN_TOKEN_VALID_TIME);
            //设置用户登录令牌失效时间
            String userToken = userStrId + CacheKeyConfig.REDIS_BUSINESS_LOGIN_TOKEN;
            hrdhCacheService.setCacheTime(userToken, CacheKeyConfig.REDIS_BUSINESS_LOGIN_TOKEN_VALID_TIME);

            JSONObject jb = new JSONObject();
            jb.put("businessId", userStrId);
            result.setDatas(jb);
        }
        return result;
    }

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();

        map.put("appKey", "web");
        map.put("advertisementPic", "/file/hrdhTwo/images/2020/5/23/860f1625-4f6f-4253-bf20-7bd8e9e58767.jpg");
        map.put("goodsAdvertisementId", "1");
        map.put("goodsId", "27");
        map.put("token", "7ff1d94ce9920f1f0ad6487c38a05636");
        String sign = SignUtil.getSign(map, "w5K21bOZdJPcFfq8AJtlG6kh8Cfc9wce");
        map.put("sign", sign);
        JSONObject jsonObject = JSONObject.fromObject(map);
        System.out.println(jsonObject.toString());
    }
}
