package com.flym.hrdh.utils.alisms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:阿里云通讯管理</p>
 * <p>Copyright: Copyright (c) 2020-05-06</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class SmsUtils {

    private static final Logger logger = LoggerFactory.getLogger(SmsUtils.class);

    /**
     * 阿里云通信：发送短信
     * @param phoneNumber 发送手机号码
     * @param templateCode 短信模板编码
     * @param code 短信JSON参数
     * @return
     */
    public static boolean sendSms(String templateCode, String phoneNumber, String code){

        boolean flag = false;

        try {
            //设置超时时间-可自行调整
            System.setProperty(SmsConfig.SYSTEM_KEY_CONNECT_TIMEOUT, SmsConfig.CONNECT_TIMEOUT_TIME);
            System.setProperty(SmsConfig.SYSTEM_KEY_READ_TIMEOUT, SmsConfig.READ_TIMEOUT_TIME);

            //初始化ascClient,暂时不支持多region（请勿修改）
            IClientProfile profile = DefaultProfile.getProfile(SmsConfig.REGION_ID, SmsConfig.ALIYUN_SMS_ACCESS_KEY_ID,  SmsConfig.ALIYUN_SMS_ACCESS_KEY_SECRET);
            DefaultProfile.addEndpoint(SmsConfig.ENDPOINT_NAME, SmsConfig.REGION_ID, SmsConfig.DY_SMS_API_PRODUCT, SmsConfig.DO_MAIN);

            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            //使用post提交
            request.setMethod(MethodType.POST);
            //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
            request.setPhoneNumbers(phoneNumber);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName( SmsConfig.ALIYUN_SMS_SIGN_NAME);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(templateCode);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
            request.setTemplateParam("{\"code\":\"" + code + "\"}");
            //请求失败这里会抛ClientException异常
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                //请求成功
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 阿里云通信：发送短信
     * @param phoneNumber 发送手机号码
     * @param templateCode 短信模板编码
     * @param jsonParam 短信JSON参数
     * @return
     */
    public static boolean sendSmsMessage(String templateCode, String phoneNumber, String jsonParam){

        boolean flag = false;
        try {
            //设置超时时间-可自行调整
            System.setProperty(SmsConfig.SYSTEM_KEY_CONNECT_TIMEOUT, SmsConfig.CONNECT_TIMEOUT_TIME);
            System.setProperty(SmsConfig.SYSTEM_KEY_READ_TIMEOUT, SmsConfig.READ_TIMEOUT_TIME);

            //初始化ascClient,暂时不支持多region（请勿修改）
            IClientProfile profile = DefaultProfile.getProfile(SmsConfig.REGION_ID, SmsConfig.ALIYUN_SMS_ACCESS_KEY_ID,  SmsConfig.ALIYUN_SMS_ACCESS_KEY_SECRET);
            DefaultProfile.addEndpoint(SmsConfig.ENDPOINT_NAME, SmsConfig.REGION_ID, SmsConfig.DY_SMS_API_PRODUCT, SmsConfig.DO_MAIN);

            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            //使用post提交
            request.setMethod(MethodType.POST);
            //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
            request.setPhoneNumbers(phoneNumber);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName( SmsConfig.ALIYUN_SMS_SIGN_NAME);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(templateCode);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
            request.setTemplateParam(jsonParam);
            //请求失败这里会抛ClientException异常
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                //请求成功
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static void main(String[] args) {
        boolean obj = SmsUtils.sendSms(SmsTemplateCode.SMS_CODE_TEMPLATE, "13726918004", "1233456");
    }
}
