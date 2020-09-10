package com.flym.hrdh.controller.business.business;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.service.business.IBusinessService;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.api.service.sensation.ISensationService;
import com.flym.hrdh.config.BusinessResultCode;
import com.flym.hrdh.config.CacheKeyConfig;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.business.Business;
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
 * <p>Description:商家登录相关接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-20</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/business/businessLogin")
public class BusinessLoginController extends BaseController {

    @Reference(version = "1.0.0")
    protected IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected IBusinessService businessService;

    /**
     * 商家账号密码登录 -101
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/passwordLogin")
    public ResponseMessage passwordLogin(@RequestBody Map<String, String> map, HttpServletRequest request) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.businessCheckNotLogin(hrdhCacheService, map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            //检测手机号码不能为空
            String phone = map.get("phone");
            if(StringUtils.isBlank(phone)){
                returnMsg.setResult("101011");
                returnMsg.setMessage(BusinessResultCode.result_101011);
                return returnMsg;
            }

            //检测密码不能为空
            String password = map.get("password");
            if(StringUtils.isBlank(password)){
                returnMsg.setResult("101021");
                returnMsg.setMessage(BusinessResultCode.result_101021);
                return returnMsg;
            }

            //根据账号和密码获取会员信息
            Business business = businessService.findBusinessByPhoneNoPassword(phone, MD5Util.encoder(password));

            if(business == null){
                returnMsg.setResult("101031");
                returnMsg.setMessage(BusinessResultCode.result_101031);
                return returnMsg;
            }else if(business.getStatus() != 1){
                //状态：1-正常、2-禁用、3-删除
                returnMsg.setResult("101041");
                returnMsg.setMessage(BusinessResultCode.result_101041);
                return returnMsg;
            }

            //请求IP
            String ip = IpAddressUtil.getIpAddr(request);

            //登录IP
            business.setLoginIp(ip);
            //登录时间
            business.setLoginDate(new Date());

            businessService.save(business);

            //生成新的登录会员令牌:登录会员令牌:有效期24小时
            String  token = CacheKeyConfig.REDIS_BUSINESS_TOKEN + business.getId().toString() + IDUtils.getRandomCode(10);
            token = MD5Util.encoder(token);
            hrdhCacheService.setCache(token, business.getId().toString(), CacheKeyConfig.REDIS_BUSINESS_LOGIN_TOKEN_VALID_TIME);

            String memberTokenKey = business.getId() + CacheKeyConfig.REDIS_BUSINESS_LOGIN_TOKEN;
            //获取旧token
            String oldToken =  hrdhCacheService.getCache(memberTokenKey);
            //检查旧token是否存在
            if(StringUtils.isNotBlank(oldToken)){
                hrdhCacheService.delCache(oldToken);
            }
            hrdhCacheService.setCache(memberTokenKey, token, CacheKeyConfig.REDIS_BUSINESS_LOGIN_TOKEN_VALID_TIME);

            JSONObject job = new JSONObject();
            //token
            job.put("token",token);
            returnMsg.setDatas(job);
            returnMsg.setResult("1");
            returnMsg.setMessage(BusinessResultCode.result_1);

        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(ResultCode.result_100000);
        }
        return returnMsg;
    }

    /**
     * 退出登录 -102
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
            returnMsg.setMessage(ResultCode.result_2);

        }catch (Exception e){
            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(ResultCode.result_100000);
        }
        return returnMsg;
    }
}
