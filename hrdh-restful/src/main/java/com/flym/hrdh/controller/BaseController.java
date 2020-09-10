package com.flym.hrdh.controller;

import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.config.CacheKeyConfig;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.config.SignUtil;
import org.apache.commons.lang3.StringUtils;

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
     * @return ReturnMessage
     */
    public ResponseMessage checkNotLogin(Map<String, String> map){

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
        /*if(!isSign){
            result.setResult("100020");
            result.setMessage(ResultCode.result_100020);
            return result;
        }*/

        return result;
    }

    /**
     * 检测appKey、token、签名是否有效
     * @param jyhCacheService 获取缓存信息
     * @param map 接口参数信息
     * @return ReturnMessage
     */
    public ResponseMessage checkLogin(IHrdhCacheService jyhCacheService, Map<String, String> map){

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

        //检测用户令牌是否存在
        String userStrId = jyhCacheService.getCache(token);
        if(StringUtils.isEmpty(userStrId)){
            result.setResult("100040");
            result.setMessage(ResultCode.result_100040);
            return result;
        }else{
            //设置用户令牌失效时间
            jyhCacheService.setCacheTime(token, CacheKeyConfig.REDIS_MEMBER_LOGIN_TOKEN_VALID_TIME);
            //设置用户登录令牌失效时间
            String userToken = userStrId + CacheKeyConfig.REDIS_MEMBER_LOGIN_TOKEN;
            jyhCacheService.setCacheTime(userToken, CacheKeyConfig.REDIS_MEMBER_LOGIN_TOKEN_VALID_TIME);
            result.setDatas(userStrId);
        }

        return result;
    }

    /**
     * 根据key判断redis的值
     * @param key redis的key
     * @param value redis的value
     * @param isEnd 是否删除
     * @return boolean
     */
    public boolean checkRedisExist(IHrdhCacheService jyhCacheService, String key, String value, boolean isEnd){

        boolean obj = false;

        String value1 = jyhCacheService.getCache(key);

        if(value.equals(value1)){
            obj = true;
            if(isEnd){
                jyhCacheService.delCache(key);
            }
        }

        return obj;
    }

    /**
     * 生成redis的key值
     * @param objectType-模块名、param1-手机号码/用户ID、param2-用户类型/验证码类型
     * @return key
     */
    public String getRedisKey(String objectType, String param1, String param2){
        String redisKey;
        redisKey = param1 + "-" + objectType + "-" + param2;
        return redisKey;
    }

}
