package com.flym.hrdh.controller.sensation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.api.service.sensation.ISensationService;
import com.flym.hrdh.config.CacheKeyConfig;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.sensation.Sensation;
import com.flym.hrdh.utils.IDUtils;
import com.flym.hrdh.utils.IpAddressUtil;
import com.flym.hrdh.utils.MD5Util;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:红人相关接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-06</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "sensationLogin")
public class SensationLoginController extends BaseController {

    @Reference(version = "1.0.0")
    protected IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected ISensationService sensationService;

    /**
     * 红人注册 -101
     * @param map
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/register")
    public ResponseMessage register(@RequestBody Map<String, String> map, HttpServletRequest request){

        ResponseMessage returnMsg = null;
        try {

            returnMsg = super.checkNotLogin(map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            //检测手机号码不能为空
            String phone = map.get("phone");
            if(StringUtils.isBlank(phone)){
                returnMsg.setResult("101011");
                returnMsg.setMessage(ResultCode.result_101011);
                return returnMsg;
            }

            //检测验证码是否为空
            String code = map.get("code");
            if(StringUtils.isBlank(code)){
                returnMsg.setResult("101021");
                returnMsg.setMessage(ResultCode.result_101021);
                return returnMsg;
            }

            //检测密码不能为空
            String password = map.get("password");
            if(StringUtils.isBlank(password)){
                returnMsg.setResult("101051");
                returnMsg.setMessage(ResultCode.result_101051);
                return returnMsg;
            }

            //校验验证码
            String redisCodeKey = 1 + CacheKeyConfig.REDIS_SMS_CODE + phone;

            String redisCode = hrdhCacheService.getCache(redisCodeKey);
            if (!code.equalsIgnoreCase(redisCode)) {
                returnMsg.setResult("101031");
                returnMsg.setMessage(ResultCode.result_101031);
                return returnMsg;
            }

            int num = sensationService.getSensationPhoneNum(phone);

            if(num > 0){
                returnMsg.setResult("101041");
                returnMsg.setMessage(ResultCode.result_101041);
                return returnMsg;
            }

            //用户信息
            Sensation sensation = new Sensation();

            //当前时间
            Date date = new Date();
            //获取请求IP
            String ip = IpAddressUtil.getIpAddr(request);

            //手机号码
            sensation.setPhone(phone);
            //密码
            sensation.setPassword(MD5Util.encoder(password));
            //性别：1-男、2-女、3-无
            sensation.setSex(3);
            //余额
            sensation.setBalancePrice(0.00);
            //累计余额
            sensation.setTotalBalancePrice(0.00);
            //预估收入余额
            sensation.setEstimatedRevenuePrice(0.00);
            //认证状态：1-未完善个人资料、2-未认证、3-认证中、4-认证拒绝、5-已认证
            sensation.setStatus(1);
            //粉丝数量
            sensation.setFollowersNum(0);
            //获赞总量
            sensation.setTotalLikedNum(0);
            //单条最高获赞
            sensation.setSingleHighestNum(0);
            //红人类型：1-红人、2-找红人
            sensation.setSensationType(1);
            //状态：1-正常、2-禁用、3-删除
            sensation.setSensationStatus(1);
            //注册时间
            sensation.setRegisterDate(date);
            //注册IP
            sensation.setRegisterIp(ip);
            //登录次数
            sensation.setLoginNum(0);
            //创建时间
            sensation.setCreateDate(date);

            sensationService.save(sensation);

            returnMsg.setResult("1");
            returnMsg.setMessage(ResultCode.result_6);
        }catch (Exception e){
            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(ResultCode.result_100000);
        }
        return returnMsg;
    }

    /**
     * 红人账号密码登录 -102
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/passwordLogin")
    public ResponseMessage passwordLogin(@RequestBody Map<String, String> map, HttpServletRequest request) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            //检测手机号码不能为空
            String phone = map.get("phone");
            if(StringUtils.isBlank(phone)){
                returnMsg.setResult("102011");
                returnMsg.setMessage(ResultCode.result_102011);
                return returnMsg;
            }

            //检测密码不能为空
            String password = map.get("password");
            if(StringUtils.isBlank(password)){
                returnMsg.setResult("102021");
                returnMsg.setMessage(ResultCode.result_102021);
                return returnMsg;
            }

            //根据账号和密码获取会员信息
            Sensation sensation = sensationService.findSensationByPhoneNoPassword(phone, MD5Util.encoder(password));

            if(sensation == null){
                returnMsg.setResult("102031");
                returnMsg.setMessage(ResultCode.result_102031);
                return returnMsg;
            }else if(sensation.getSensationStatus() != 1){
                //状态：1-正常、2-禁用、3-删除
                returnMsg.setResult("102041");
                returnMsg.setMessage(ResultCode.result_102041);
                return returnMsg;
            }

            //请求IP
            String ip = IpAddressUtil.getIpAddr(request);

            //登录IP
            sensation.setLoginIp(ip);
            //登录时间
            sensation.setLoginDate(new Date());
            //登录次数
            sensation.setLoginNum(sensation.getLoginNum() + 1);

            sensationService.save(sensation);

            //生成新的登录会员令牌:登录会员令牌:有效期24小时
            String  token = CacheKeyConfig.REDIS_MEMBER_TOKEN + sensation.getId().toString() + IDUtils.getRandomCode(10);
            token = MD5Util.encoder(token);
            hrdhCacheService.setCache(token, sensation.getId().toString(), CacheKeyConfig.REDIS_MEMBER_LOGIN_TOKEN_VALID_TIME);

            String memberTokenKey = sensation.getId() + CacheKeyConfig.REDIS_MEMBER_LOGIN_TOKEN;
            //获取旧token
            String oldToken =  hrdhCacheService.getCache(memberTokenKey);
            //检查旧token是否存在
            if(StringUtils.isNotBlank(oldToken)){
                hrdhCacheService.delCache(oldToken);
            }
            hrdhCacheService.setCache(memberTokenKey, token, CacheKeyConfig.REDIS_MEMBER_LOGIN_TOKEN_VALID_TIME);

            JSONObject job = new JSONObject();
            //token
            job.put("token",token);

            returnMsg.setDatas(job);
            returnMsg.setResult("1");
            returnMsg.setMessage(ResultCode.result_4);

        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(ResultCode.result_100000);
        }
        return returnMsg;
    }

    /**
     * 红人账号验证码登录 -103
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/codeLogin")
    public ResponseMessage codeLogin(@RequestBody Map<String, String> map, HttpServletRequest request) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            //检测手机号码不能为空
            String phone = map.get("phone");
            if(StringUtils.isBlank(phone)){
                returnMsg.setResult("103011");
                returnMsg.setMessage(ResultCode.result_103011);
                return returnMsg;
            }

            //检测验证码不能为空
            String code = map.get("code");
            if(StringUtils.isBlank(code)){
                returnMsg.setResult("103021");
                returnMsg.setMessage(ResultCode.result_103021);
                return returnMsg;
            }

            //校验验证码
            String redisCodeKey = 1 + CacheKeyConfig.REDIS_SMS_CODE + phone;

            String redisCode = hrdhCacheService.getCache(redisCodeKey);
            if (!code.equalsIgnoreCase(redisCode)) {
                returnMsg.setResult("103031");
                returnMsg.setMessage(ResultCode.result_103031);
                return returnMsg;
            }

            //根据账号和密码获取会员信息
            Sensation sensation = sensationService.findSensationByPhoneNoPassword(phone, null);

            if(sensation == null){
                returnMsg.setResult("103041");
                returnMsg.setMessage(ResultCode.result_103041);
                return returnMsg;
            }else if(sensation.getSensationStatus() != 1){
                //状态：1-正常、2-禁用、3-删除
                returnMsg.setResult("103051");
                returnMsg.setMessage(ResultCode.result_103051);
                return returnMsg;
            }

            //请求IP
            String ip = IpAddressUtil.getIpAddr(request);

            //登录IP
            sensation.setLoginIp(ip);
            //登录时间
            sensation.setLoginDate(new Date());
            //登录次数
            sensation.setLoginNum(sensation.getLoginNum() + 1);

            sensationService.save(sensation);

            //生成新的登录会员令牌:登录会员令牌:有效期24小时
            String  token = CacheKeyConfig.REDIS_MEMBER_TOKEN + sensation.getId().toString() + IDUtils.getRandomCode(10);
            token = MD5Util.encoder(token);
            hrdhCacheService.setCache(token, sensation.getId().toString(), CacheKeyConfig.REDIS_MEMBER_LOGIN_TOKEN_VALID_TIME);

            String memberTokenKey = sensation.getId() + CacheKeyConfig.REDIS_MEMBER_LOGIN_TOKEN;
            //获取旧token
            String oldToken =  hrdhCacheService.getCache(memberTokenKey);
            //检查旧token是否存在
            if(StringUtils.isNotBlank(oldToken)){
                hrdhCacheService.delCache(oldToken);
            }
            hrdhCacheService.setCache(memberTokenKey, token, CacheKeyConfig.REDIS_MEMBER_LOGIN_TOKEN_VALID_TIME);

            JSONObject job = new JSONObject();
            //token
            job.put("token",token);

            returnMsg.setDatas(job);
            returnMsg.setResult("1");
            returnMsg.setMessage(ResultCode.result_4);

        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(ResultCode.result_100000);
        }
        return returnMsg;
    }

    /**
     * 退出登录 -104
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/loginOut")
    public ResponseMessage loginOut(@RequestBody Map<String, String> map){
        ResponseMessage returnMsg =  new ResponseMessage();

        try {
            //检测用户令牌不能为空
            String token = map.get("token");

            if(StringUtils.isNotBlank(token)){

                hrdhCacheService.delCache(token);
            }

            returnMsg.setResult("1");
            returnMsg.setMessage(ResultCode.result_5);

        }catch (Exception e){
            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(ResultCode.result_100000);
        }
        return returnMsg;
    }
}
