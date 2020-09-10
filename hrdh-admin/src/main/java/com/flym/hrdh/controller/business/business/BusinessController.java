package com.flym.hrdh.controller.business.business;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.service.business.IBusinessService;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.config.BusinessResultCode;
import com.flym.hrdh.config.CacheKeyConfig;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.business.Business;
import com.flym.hrdh.utils.MD5Util;
import com.flym.hrdh.utils.NumUtils;
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
 * <p>Description:商家相关接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-21</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/business/business")
public class BusinessController extends BaseController {

    @Reference(version = "1.0.0")
    protected IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected IBusinessService businessService;

    /**
     * 商家详情 -103
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/businessInfo")
    public ResponseMessage businessInfo(@RequestBody Map<String, String> map, HttpServletRequest request) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.businessCheckLogin(hrdhCacheService, map);

            //如果result为空则直接返回
            Long businessId ;
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            } else {
                businessId = returnMsg.getDatas().getLong("businessId");
                returnMsg.setDatas(null);
            }

            Business b = businessService.get(businessId);

            JSONObject job = new JSONObject();

            //手机号
            job.put("phone", b.getPhone());
            //昵称
            job.put("nickName", b.getNickName() == null ? "" : b.getNickName());
            //头像
            job.put("headPic", b.getHeadPic() == null ? "" : b.getHeadPic());
            String shopName = b.getShopName();
            //店铺名称
            job.put("shopName", shopName == null ? "" : shopName);
            //淘宝或天猫店铺地址
            job.put("shopAddress", b.getShopAddress() == null ? "" : b.getShopAddress());
            //邀请码
            job.put("code", b.getCode() == null ? "" : b.getCode());
            //收样人姓名
            job.put("consigneeName", b.getConsigneeName() == null ? "" : b.getConsigneeName());
            //收样的地址
            job.put("consigneeAddress", b.getConsigneeAddress() == null ? "" : b.getConsigneeAddress());
            //宝贝描述
            job.put("babyDescription", NumUtils.doubleToScale(b.getBabyDescription(), 1));
            //卖家服务
            job.put("sellerServices", NumUtils.doubleToScale(b.getSellerServices(), 1));
            //物流服务
            job.put("logisticsService", NumUtils.doubleToScale(b.getLogisticsService(), 1));
            //商家等级：1-初级、2-中级、3-高级
            job.put("gradeType", b.getGradeType());
            //淘宝信誉等级：1-20
            job.put("reputationType", b.getReputationType());
            //客服电话
            job.put("customerPhone", b.getCustomerPhone() == null ? "" : b.getCustomerPhone());
            //客服微信二维码
            job.put("customerWeChatPic", b.getCustomerWeChatPic() == null ? "" : b.getCustomerWeChatPic());
            //完善信息状态：1-未完善、2已完善
            Integer type = 2;
            if(shopName == null){
                type= 1;
            }
            job.put("type", type);

            returnMsg.setDatas(job);
            returnMsg.setResult("0");
            returnMsg.setMessage(BusinessResultCode.result_0);

        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(ResultCode.result_100000);
        }
        return returnMsg;
    }

    /**
     * 商家完善信息 -104
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/businessPerfect")
    public ResponseMessage businessPerfect(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.businessCheckLogin(hrdhCacheService, map);

            //如果result为空则直接返回
            Long businessId ;
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            } else {
                businessId = returnMsg.getDatas().getLong("businessId");
                returnMsg.setDatas(null);
            }

            //检测昵称不能为空
            String nickName = map.get("nickName");
            if(StringUtils.isBlank(nickName)){
                returnMsg.setResult("104011");
                returnMsg.setMessage(BusinessResultCode.result_104011);
                return returnMsg;
            }

            //检测头像不能为空
            String headPic = map.get("headPic");
            if(StringUtils.isBlank(headPic)){
                returnMsg.setResult("104021");
                returnMsg.setMessage(BusinessResultCode.result_104021);
                return returnMsg;
            }

            //检测店铺名称不能为空
            String shopName = map.get("shopName");
            if(StringUtils.isBlank(shopName)){
                returnMsg.setResult("104031");
                returnMsg.setMessage(BusinessResultCode.result_104031);
                return returnMsg;
            }

            //检测淘宝或天猫店铺地址不能为空
            String shopAddress = map.get("shopAddress");
            if(StringUtils.isBlank(shopAddress)){
                returnMsg.setResult("104041");
                returnMsg.setMessage(BusinessResultCode.result_104041);
                return returnMsg;
            }

            //检测邀请码不能为空
            String code = map.get("code");
            /*if(StringUtils.isBlank(code)){
                returnMsg.setResult("104051");
                returnMsg.setMessage(BusinessResultCode.result_104051);
                return returnMsg;
            }*/

            //检测收样人姓名不能为空
            String consigneeName = map.get("consigneeName");
            if(StringUtils.isBlank(consigneeName)){
                returnMsg.setResult("104061");
                returnMsg.setMessage(BusinessResultCode.result_104061);
                return returnMsg;
            }

            //检测收样的地址不能为空
            String consigneeAddress = map.get("consigneeAddress");
            if(StringUtils.isBlank(consigneeAddress)){
                returnMsg.setResult("104071");
                returnMsg.setMessage(BusinessResultCode.result_104071);
                return returnMsg;
            }

            //检测宝贝描述不能为空
            String babyDescription = map.get("babyDescription");
            if(StringUtils.isBlank(babyDescription)){
                returnMsg.setResult("104081");
                returnMsg.setMessage(BusinessResultCode.result_104081);
                return returnMsg;
            }

            //检测卖家服务不能为空
            String sellerServices = map.get("sellerServices");
            if(StringUtils.isBlank(sellerServices)){
                returnMsg.setResult("104091");
                returnMsg.setMessage(BusinessResultCode.result_104091);
                return returnMsg;
            }

            //检测物流服务不能为空
            String logisticsService = map.get("logisticsService");
            if(StringUtils.isBlank(logisticsService)){
                returnMsg.setResult("104101");
                returnMsg.setMessage(BusinessResultCode.result_104101);
                return returnMsg;
            }

            //检测客服电话不能为空
            String customerPhone = map.get("customerPhone");
            if(StringUtils.isBlank(customerPhone)){
                returnMsg.setResult("104111");
                returnMsg.setMessage(BusinessResultCode.result_104111);
                return returnMsg;
            }

            //检测客服微信二维码不能为空
            String customerWeChatPic = map.get("customerWeChatPic");
            if(StringUtils.isBlank(customerWeChatPic)){
                returnMsg.setResult("104121");
                returnMsg.setMessage(BusinessResultCode.result_104121);
                return returnMsg;
            }

            //检测验证码不能为空
            /*String phoneCode = map.get("phoneCode");
            if(StringUtils.isBlank(phoneCode)){
                returnMsg.setResult("104131");
                returnMsg.setMessage(BusinessResultCode.result_104131);
                return returnMsg;
            }*/

            Business b = businessService.get(businessId);

            //校验验证码
            /*String redisCodeKey = 1 + CacheKeyConfig.REDIS_SMS_CODE + b.getPhone();

            String redisCode = hrdhCacheService.getCache(redisCodeKey);
            if (!phoneCode.equalsIgnoreCase(redisCode)) {
                returnMsg.setResult("104141");
                returnMsg.setMessage(BusinessResultCode.result_104141);
                return returnMsg;
            }*/

            //判断是否完善信息
            if(b.getShopName() != null){
                returnMsg.setResult("104151");
                returnMsg.setMessage(BusinessResultCode.result_104151);
                return returnMsg;
            }

            //昵称
            b.setNickName(nickName);
            //头像
            b.setHeadPic(headPic);
            //店铺名称
            b.setShopName(shopName);
            //淘宝或天猫店铺地址
            b.setShopAddress(shopAddress);
            //邀请码
            b.setCode(code);
            //收样人姓名
            b.setConsigneeName(consigneeName);
            //收样的地址
            b.setConsigneeAddress(consigneeAddress);
            //宝贝描述
            b.setBabyDescription(Double.parseDouble(babyDescription));
            //卖家服务
            b.setSellerServices(Double.parseDouble(sellerServices));
            //物流服务
            b.setLogisticsService(Double.parseDouble(logisticsService));
            //客服电话
            b.setCustomerPhone(customerPhone);
            //客服微信二维码
            b.setCustomerWeChatPic(customerWeChatPic);
            //修改时间
            b.setModifyDate(new Date());

            businessService.save(b);

            returnMsg.setResult("1");
            returnMsg.setMessage(BusinessResultCode.result_8);

        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(ResultCode.result_100000);
        }
        return returnMsg;
    }

    /**
     * 商家修改信息 -105
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateBusiness")
    public ResponseMessage updateBusiness(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.businessCheckLogin(hrdhCacheService, map);

            //如果result为空则直接返回
            Long businessId ;
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            } else {
                businessId = returnMsg.getDatas().getLong("businessId");
                returnMsg.setDatas(null);
            }

            //检测收样人姓名不能为空
            String consigneeName = map.get("consigneeName");
            if(StringUtils.isBlank(consigneeName)){
                returnMsg.setResult("105011");
                returnMsg.setMessage(BusinessResultCode.result_105011);
                return returnMsg;
            }

            //检测收样的地址不能为空
            String consigneeAddress = map.get("consigneeAddress");
            if(StringUtils.isBlank(consigneeAddress)){
                returnMsg.setResult("105021");
                returnMsg.setMessage(BusinessResultCode.result_105021);
                return returnMsg;
            }

            //检测客服电话不能为空
            String customerPhone = map.get("customerPhone");
            if(StringUtils.isBlank(customerPhone)){
                returnMsg.setResult("105131");
                returnMsg.setMessage(BusinessResultCode.result_105131);
                return returnMsg;
            }

            //检测客服微信二维码不能为空
            String customerWeChatPic = map.get("customerWeChatPic");
            if(StringUtils.isBlank(customerWeChatPic)){
                returnMsg.setResult("10514");
                returnMsg.setMessage(BusinessResultCode.result_105141);
                return returnMsg;
            }

            Business b = businessService.get(businessId);

            //收样人姓名
            b.setConsigneeName(consigneeName);
            //收样的地址
            b.setConsigneeAddress(consigneeAddress);
            //客服电话
            b.setCustomerPhone(customerPhone);
            //客服微信二维码
            b.setCustomerWeChatPic(customerWeChatPic);
            //修改时间
            b.setModifyDate(new Date());

            businessService.save(b);

            returnMsg.setResult("1");
            returnMsg.setMessage(BusinessResultCode.result_4);

        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(ResultCode.result_100000);
        }
        return returnMsg;
    }

    /**
     * 修改密码 - 106
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updatePassword")
    public ResponseMessage updatePassword(@RequestBody Map<String, String> map){

        ResponseMessage respMsg = null;
        try {

            respMsg = super.businessCheckLogin(hrdhCacheService,map);

            //如果result为空则直接返回
            Long businessId ;
            if (StringUtils.isNotBlank(respMsg.result)) {
                return respMsg;
            } else {
                businessId = respMsg.getDatas().getLong("businessId");
                respMsg.setDatas(null);
            }

            //检测旧密码不能为空
            String oldPassword = map.get("oldPassword");
            if(StringUtils.isBlank(oldPassword)){
                respMsg.setResult("106011");
                respMsg.setMessage(BusinessResultCode.result_106011);
                return respMsg;
            }

            //检测新密码不能为空
            String password = map.get("password");
            if(StringUtils.isBlank(password)){
                respMsg.setResult("106021");
                respMsg.setMessage(BusinessResultCode.result_106021);
                return respMsg;
            }

            //检测确认密码不能为空
            String confirmPassword = map.get("confirmPassword");
            if(StringUtils.isBlank(confirmPassword)){
                respMsg.setResult("106031");
                respMsg.setMessage(BusinessResultCode.result_106031);
                return respMsg;
            }

            //检测新密码和确认密码是否一至
            if(!password.equals(confirmPassword)){
                respMsg.setResult("106041");
                respMsg.setMessage(BusinessResultCode.result_106041);
                return respMsg;
            }

            //会员信息
            Business b = businessService.get(businessId);

            //检测旧密码是否正确
            if(!b.getPassword().equals(MD5Util.encoder(oldPassword))){
                respMsg.setResult("106051");
                respMsg.setMessage(BusinessResultCode.result_106051);
                return respMsg;
            }

            //密码
            b.setPassword(MD5Util.encoder(password));
            //修改时间
            b.setModifyDate(new Date());

            businessService.save(b);

            //获取token
            String oldToken =  hrdhCacheService.getCache(b.getId() + CacheKeyConfig.REDIS_BUSINESS_LOGIN_TOKEN);
            //检查旧token是否存在
            if(StringUtils.isNotBlank(oldToken)){
                // 删除token
                hrdhCacheService.delCache(oldToken);
            }

            hrdhCacheService.delCache(b.getId() + CacheKeyConfig.REDIS_BUSINESS_LOGIN_TOKEN);

            respMsg.setResult("1");
            respMsg.setMessage(BusinessResultCode.result_4 + "，请重新登录");
        }catch (Exception e){
            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(BusinessResultCode.result_100000);
        }
        return respMsg;
    }
}
