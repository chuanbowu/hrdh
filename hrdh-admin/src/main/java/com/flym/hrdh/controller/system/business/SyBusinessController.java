package com.flym.hrdh.controller.system.business;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.business.BusinessVm;
import com.flym.hrdh.api.service.business.IBusinessService;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.config.*;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.business.Business;
import com.flym.hrdh.utils.MD5Util;
import com.flym.hrdh.utils.NumUtils;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:商家相关接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-26</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/system/business")
public class SyBusinessController extends BaseController {

    @Reference(version = "1.0.0")
    protected IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected IBusinessService businessService;

    /**
     * 商家列表 -301
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/businessList")
    public ResponseMessage businessList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(hrdhCacheService,map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            //当前页码
            int pageNum = 1;
            String pageNumStr = map.get("pageNum");
            if (StringUtils.isNotBlank(pageNumStr)) {
                pageNum = Integer.parseInt(pageNumStr);
            }

            //每页显示数
            int pageSize = CommonConfig.RESTFUL_LIST_DEFAULT_PAGE_SIZE;
            String pageSizeStr = map.get("pageSize");
            if (StringUtils.isNotBlank(pageSizeStr)) {
                pageSize = Integer.parseInt(pageSizeStr);
            }

            //开始条数
            int beginNum = pageSize * (pageNum - 1);

            //手机号
            String phone = map.get("phone");

            //昵称
            String nickName = map.get("nickName");

            //商家等级：1-初级、2-中级、3-高级
            String gradeTypeStr = map.get("gradeType");
            Integer gradeType = null;
            if (StringUtils.isNotBlank(gradeTypeStr)){
                gradeType = Integer.parseInt(gradeTypeStr);
            }

            JSONArray ja = new JSONArray();

            //获取列表总数
            Integer totalRow = businessService.getBusinessVmNum(phone, nickName, gradeType);

            if (totalRow > 0) {

                List<BusinessVm> businessVmList = businessService.findBusinessVmList(phone, nickName, gradeType, beginNum, pageSize);

                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                for (BusinessVm b : businessVmList) {

                    JSONObject obj = new JSONObject();

                    //ID
                    obj.put("businessId", b.getId());
                    //手机号
                    obj.put("phone", b.getPhone());
                    //昵称
                    obj.put("nickName", b.getNickName() == null ? "" : b.getNickName());
                    //头像
                    obj.put("headPic", b.getHeadPic() == null ? "" : b.getHeadPic());
                    //店铺名称
                    obj.put("shopName", b.getShopName() == null ? "" : b.getShopName());
                    //淘宝或天猫店铺地址
                    obj.put("shopAddress", b.getShopAddress() == null ? "" : b.getShopAddress());
                    //邀请码
                    obj.put("code", b.getCode() == null ? "" : b.getCode());
                    //收样人姓名
                    obj.put("consigneeName", b.getConsigneeName() == null ? "" : b.getConsigneeName());
                    //收样的地址
                    obj.put("consigneeAddress", b.getConsigneeAddress() == null ? "" : b.getConsigneeAddress());
                    //宝贝描述
                    obj.put("babyDescription", NumUtils.doubleToScale(b.getBabyDescription(), 1));
                    //卖家服务
                    obj.put("sellerServices", NumUtils.doubleToScale(b.getSellerServices(), 1));
                    //物流服务
                    obj.put("logisticsService", NumUtils.doubleToScale(b.getLogisticsService(), 1));
                    //商家等级：1-初级、2-中级、3-高级
                    obj.put("gradeType", b.getGradeType());
                    //淘宝信誉等级：1-20
                    obj.put("reputationType", b.getReputationType());
                    //客服电话
                    obj.put("customerPhone", b.getCustomerPhone() == null ? "" : b.getCustomerPhone());
                    //客服微信二维码
                    obj.put("customerWeChatPic", b.getCustomerWeChatPic() == null ? "" : b.getCustomerWeChatPic());
                    //状态：1-正常、2-禁用、3-删除
                    obj.put("status", b.getStatus());
                    //操作人名称
                    obj.put("modifySysUserName", b.getModifySysUserName());
                    //操作时间
                    obj.put("modifyDate", sf.format(b.getModifyDate()));

                    ja.add(obj);
                }
            }

            JSONObject jb = new JSONObject();
            jb.put("list", ja);
            jb.put("totalRow", totalRow);
            returnMsg.setDatas(jb);
            returnMsg.setResult("0");
            returnMsg.setMessage(ResultCode.result_0);

        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(ResultCode.result_100000);
        }
        return returnMsg;
    }

    /**
     * 新增商家 -302
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/addBusiness")
    public ResponseMessage addBusiness(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkLogin(hrdhCacheService, map);

            //如果result为空则直接返回
            Long userId ;
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            } else {
                userId = returnMsg.getDatas().getLong("userId");
                returnMsg.setDatas(null);
            }

            //检测手机号不能为空
            String phone = map.get("phone");
            if(StringUtils.isBlank(phone)){
                returnMsg.setResult("302011");
                returnMsg.setMessage(ResultCode.result_302011);
                return returnMsg;
            }

            //检测密码不能为空
            String password = map.get("password");
            if(StringUtils.isBlank(password)){
                returnMsg.setResult("302021");
                returnMsg.setMessage(ResultCode.result_302021);
                return returnMsg;
            }

            //商家等级：1-初级、2-中级、3-高级
            String gradeType = map.get("gradeType");
            if(StringUtils.isBlank(gradeType)){
                returnMsg.setResult("302031");
                returnMsg.setMessage(ResultCode.result_302031);
                return returnMsg;
            }

            //淘宝信誉等级：1-20
            String reputationType = map.get("reputationType");
            if(StringUtils.isBlank(reputationType)){
                returnMsg.setResult("302041");
                returnMsg.setMessage(ResultCode.result_302041);
                return returnMsg;
            }

            int num = businessService.getBusinessPhone(phone);

            //判断是否手机号已经注册
            if(num > 0){
                returnMsg.setResult("302051");
                returnMsg.setMessage(ResultCode.result_302051);
                return returnMsg;
            }

            Business b = new Business();

            //手机号
            b.setPhone(phone);
            //密码
            b.setPassword(MD5Util.encoder(password));
            //宝贝描述
            b.setBabyDescription(0.00);
            //卖家服务
            b.setSellerServices(0.00);
            //物流服务
            b.setLogisticsService(0.00);
            //商家等级：1-初级、2-中级、3-高级
            b.setGradeType(Integer.parseInt(gradeType));
            //淘宝信誉等级：1-20
            b.setReputationType(Integer.parseInt(reputationType));
            //状态：1-正常、2-禁用、3-删除
            b.setStatus(1);
            //创建人
            b.setCreateSysUser(userId);
            //创建时间
            Date date = new Date();
            b.setCreateDate(date);
            //修改人
            b.setModifySysUser(userId);
            //修改时间
            b.setModifyDate(date);

            businessService.save(b);

            returnMsg.setResult("1");
            returnMsg.setMessage(ResultCode.result_9);

        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(ResultCode.result_100000);
        }
        return returnMsg;
    }

    /**
     * 修改商家 -303
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateBusiness")
    public ResponseMessage updateBusiness(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkLogin(hrdhCacheService, map);

            //如果result为空则直接返回
            Long userId ;
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            } else {
                userId = returnMsg.getDatas().getLong("userId");
                returnMsg.setDatas(null);
            }

            //检测商家ID不能为空
            String businessId = map.get("businessId");
            if(StringUtils.isBlank(businessId)){
                returnMsg.setResult("303010");
                returnMsg.setMessage(ResultCode.result_303010);
                return returnMsg;
            }

            //商家等级：1-初级、2-中级、3-高级
            String gradeType = map.get("gradeType");
            if(StringUtils.isBlank(gradeType)){
                returnMsg.setResult("303021");
                returnMsg.setMessage(ResultCode.result_303021);
                return returnMsg;
            }

            //淘宝信誉等级：1-20
            String reputationType = map.get("reputationType");
            if(StringUtils.isBlank(reputationType)){
                returnMsg.setResult("303031");
                returnMsg.setMessage(ResultCode.result_303031);
                return returnMsg;
            }

            Business b = businessService.get(Long.parseLong(businessId));

            //商家等级：1-初级、2-中级、3-高级
            b.setGradeType(Integer.parseInt(gradeType));
            //淘宝信誉等级：1-20
            b.setReputationType(Integer.parseInt(reputationType));
            //创建时间
            Date date = new Date();
            //修改人
            b.setModifySysUser(userId);
            //修改时间
            b.setModifyDate(date);

            businessService.save(b);

            returnMsg.setResult("1");
            returnMsg.setMessage(ResultCode.result_6);

        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(ResultCode.result_100000);
        }
        return returnMsg;
    }

    /**
     * 修改商家状态 -304
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateStatus")
    public ResponseMessage editStatus(@RequestBody Map<String, String> map){

        ResponseMessage respMsg = null;

        try {

            respMsg = super.checkLogin(hrdhCacheService, map);

            //如果result为空则直接返回
            Long userId ;
            if (StringUtils.isNotBlank(respMsg.result)) {
                return respMsg;
            } else {
                userId = respMsg.getDatas().getLong("userId");
                respMsg.setDatas(null);
            }

            //检测商家ID不能为空
            String businessIds = map.get("businessIds");
            if(StringUtils.isBlank(businessIds)){
                respMsg.setResult("304010");
                respMsg.setMessage(ResultCode.result_304010);
                return respMsg;
            }

            //检测操作类型不能为空
            String statusStr = map.get("status");
            if(StringUtils.isBlank(statusStr)){
                respMsg.setResult("304020");
                respMsg.setMessage(ResultCode.result_304020);
                return respMsg;
            }
            int status = Integer.parseInt(statusStr);

            String[] idArr = businessIds.split(",");
            for (int i = 0; i < idArr.length; i++){

                //删除用户令牌信息
                if(status != 1){
                    //用户登陆令牌
                    String userToken = idArr[i].toString() + CacheKeyConfig.REDIS_BUSINESS_LOGIN_TOKEN;
                    //获取用户令牌
                    String oldToken = hrdhCacheService.getCache(userToken);
                    if(StringUtils.isNotBlank(oldToken)){
                        hrdhCacheService.delCache(oldToken);
                    }
                    hrdhCacheService.delCache(userToken);
                }
            }

            //当前时间
            Date date = new Date();

            businessService.updateStatus(businessIds, status , userId, date);

            respMsg.setResult("1");
            respMsg.setMessage(ResultCode.result_10);
        }catch (Exception e){
            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(ResultCode.result_100000);
        }
        return respMsg;
    }

}
