package com.flym.hrdh.controller.business.common;


import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.config.BusinessResultCode;
import com.flym.hrdh.config.CacheKeyConfig;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.utils.IDUtils;
import com.flym.hrdh.utils.alisms.SmsTemplateCode;
import com.flym.hrdh.utils.alisms.SmsUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description: 发送短信验证码</p>
 * <p>Copyright: Copyright (c) 2020-05-06</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/business/common/sms")
public class SmsController extends BaseController {

    @Reference(version = "1.0.0")
    protected IHrdhCacheService hrdhCacheService;

    /**
     * 发送验证码接口 -201
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/send")
    public ResponseMessage send(@RequestBody Map<String, String> map){

        ResponseMessage respMsg = null;
        try {

            respMsg = super.businessCheckNotLogin(hrdhCacheService, map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(respMsg.result)) {
                return respMsg;
            }

            //检测手机号码不能为空
            String phone = map.get("phone");
            if(StringUtils.isBlank(phone)){
                respMsg.setResult("201011");
                respMsg.setMessage(BusinessResultCode.result_201011);
                return respMsg;
            }

            //生成验证码
            String code = IDUtils.getRandomCode(6);

            //阿里云
            boolean obj = SmsUtils.sendSms(SmsTemplateCode.SMS_CODE_TEMPLATE, phone, code);

            if(obj) {

                //将验证码保存到redis，并设置时效:5分钟
                String str = 1 + CacheKeyConfig.REDIS_SMS_CODE + phone;
                hrdhCacheService.setCache(str, code, CacheKeyConfig.REDIS_SMS_CODE_VALID_TIME);
            } else{
                respMsg.setResult("201021");
                respMsg.setMessage(BusinessResultCode.result_201021);
            }

            respMsg.setResult("1");
            respMsg.setMessage(BusinessResultCode.result_9);
        }catch (Exception e){
            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(BusinessResultCode.result_100000);
        }
        return respMsg;
    }


}
