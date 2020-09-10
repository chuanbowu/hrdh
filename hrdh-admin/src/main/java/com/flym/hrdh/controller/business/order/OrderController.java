package com.flym.hrdh.controller.business.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.order.OrderVm;
import com.flym.hrdh.api.service.business.IBusinessService;
import com.flym.hrdh.api.service.business.ICommissionGoodsService;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.api.service.order.IOrderService;
import com.flym.hrdh.api.service.sensation.ISensationService;
import com.flym.hrdh.config.BusinessResultCode;
import com.flym.hrdh.config.CommonConfig;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.business.Business;
import com.flym.hrdh.pojo.business.CommissionGoods;
import com.flym.hrdh.pojo.order.Order;
import com.flym.hrdh.pojo.sensation.Sensation;
import com.flym.hrdh.utils.NumUtils;
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
 * <p>Description:取样管理</p>
 * <p>Copyright: Copyright (c) 2020-06-07</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/business/order")
public class OrderController extends BaseController {

    @Reference(version = "1.0.0")
    protected IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected IOrderService orderService;

    @Reference(version = "1.0.0")
    protected ICommissionGoodsService commissionGoodsService;

    @Reference(version = "1.0.0")
    protected IBusinessService businessService;

    @Reference(version = "1.0.0")
    protected ISensationService sensationService;

    /**
     * 取样列表 -401
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/orderList")
    public ResponseMessage orderList(@RequestBody Map<String, String> map) {

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

            //当前页码
            int pageNum = 1;
            String pageNumStr = map.get("pageNum");
            if (StringUtils.isNotBlank(pageNumStr)) {
                pageNum = Integer.parseInt(pageNumStr);
            }

            //每页显示数
            int pageSize = CommonConfig.ADMIN_LIST_DEFAULT_PAGE_SIZE;
            String pageSizeStr = map.get("pageSize");
            if (StringUtils.isNotBlank(pageSizeStr)) {
                pageSize = Integer.parseInt(pageSizeStr);
            }

            //开始条数
            int beginNum = pageSize * (pageNum - 1);

            /**
             * 取样状态：1-领样等待审核、2-审核失败、3-领样待发样、4-领养已发样、5-红人已收样、6-红人寄回样中、7-领养已收样
             */
            String statusStr = map.get("status");
            Integer status = null;
            if(StringUtils.isNotBlank(statusStr)){
                status = Integer.parseInt(statusStr);
            }

            //商品标题
            String businessTitle = map.get("businessTitle");

            JSONArray ja = new JSONArray();

            //获取列表总数
            Integer totalRow = orderService.getOrderVmNumByBusinessId(businessId, status, businessTitle);

            if (totalRow > 0) {

                List<OrderVm> orderVmList = orderService.findOrderVmListByBusinessId(businessId, status, businessTitle, beginNum, pageSize);

                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                for (OrderVm orderVm : orderVmList) {

                    JSONObject obj = new JSONObject();

                    //ID
                    obj.put("orderId", orderVm.getId());
                    //红人手机号码
                    obj.put("phone", orderVm.getPhone());
                    //红人昵称
                    obj.put("nickName", orderVm.getNickName());
                    //红人头像
                    obj.put("headPic", orderVm.getHeadPic());
                    //红人粉丝数量
                    obj.put("followersNum", orderVm.getFollowersNum());
                    //取样状态：1-等待商家审核、2-商家审核失败、3-待商家发样、4-商家已发样、5-红人已收样、6-红人已寄回样品、7-商家已收样
                    obj.put("status", orderVm.getStatus());
                    //商品ID
                    obj.put("goodsId", orderVm.getGoodsId());
                    //商品标题
                    obj.put("businessTitle", orderVm.getBusinessTitle());
                    //主图
                    obj.put("mainPic", orderVm.getMainPic());
                    //券后价价格
                    obj.put("couponAfterPrice", NumUtils.doubleToScale(orderVm.getCouponAfterPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //佣金
                    obj.put("commissionPrice", NumUtils.doubleToScale(orderVm.getCommissionPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //比例
                    obj.put("proportion", NumUtils.doubleToScale(Double.parseDouble(orderVm.getProportion().toString()) / 100, CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //宝贝+券二合一推广链接
                    obj.put("couponShareUrl", orderVm.getCouponShareUrl() == null ? "" : orderVm.getCouponShareUrl());
                    //商家发样快递公司ID
                    obj.put("businessExpressId", orderVm.getBusinessExpressId() == null ? "" : orderVm.getBusinessExpressId());
                    //商家发样快递公司名称
                    obj.put("businessExpressName", orderVm.getBusinessExpressName() == null ? "" : orderVm.getBusinessExpressName());
                    //商家发样快递单号
                    obj.put("businessNum", orderVm.getBusinessNum() == null ? "" : orderVm.getBusinessNum());
                    //商家寄回样品说明
                    obj.put("businessExplain", orderVm.getBusinessExplain() == null ? "" : orderVm.getBusinessExplain());
                    //商家收样人手机号
                    obj.put("businessConsigneePhone", orderVm.getBusinessConsigneePhone() == null ? "" : orderVm.getBusinessConsigneePhone());
                    //商家收样人姓名
                    obj.put("businessConsigneeName", orderVm.getBusinessConsigneeName() == null ? "" : orderVm.getBusinessConsigneeName());
                    //商家收样的地址
                    obj.put("businessConsigneeAddress", orderVm.getBusinessConsigneeAddress() == null ? "" : orderVm.getBusinessConsigneeAddress());
                    //红人发样快递公司ID
                    obj.put("sensationExpressId", orderVm.getSensationExpressId() == null ? "" : orderVm.getSensationExpressId());
                    //红人发样快递公司名称
                    obj.put("sensationExpressName", orderVm.getSensationExpressName() == null ? "" : orderVm.getSensationExpressName());
                    //红人发样快递单号
                    obj.put("sensationNum", orderVm.getSensationNum() == null ? "" : orderVm.getSensationNum());
                    //拒绝理由
                    obj.put("refuseContent", orderVm.getRefuseContent() == null ? "" : orderVm.getRefuseContent());
                    //创建时间
                    obj.put("createDate", sf.format(orderVm.getCreateDate()));
                    //商家发样时间
                    obj.put("businessDeliverDate", orderVm.getBusinessDeliverDate() == null ? "" :  sf.format(orderVm.getBusinessDeliverDate()));
                    //红人收样时间
                    obj.put("sensationReceivingDate", orderVm.getSensationReceivingDate() == null ? "" :  sf.format(orderVm.getSensationReceivingDate()));
                    //红人寄样时间
                    obj.put("sensationSendDate", orderVm.getSensationSendDate() == null ? "" :  sf.format(orderVm.getSensationSendDate()));
                    //商家收样时间
                    obj.put("businessCollectDate", orderVm.getBusinessCollectDate() == null ? "" :  sf.format(orderVm.getBusinessCollectDate()));
                    //审核时间
                    obj.put("checkDate", orderVm.getCheckDate() == null ? "" :  sf.format(orderVm.getCheckDate()));
                    //红人收样人手机号
                    obj.put("sensationConsigneePhone", orderVm.getSensationConsigneePhone());
                    //红人收样人姓名
                    obj.put("sensationConsigneeName", orderVm.getSensationConsigneeName());
                    //红人收样地址
                    obj.put("sensationConsigneeAddress", orderVm.getSensationConsigneeAddress());

                    ja.add(obj);
                }
            }

            JSONObject jb = new JSONObject();
            jb.put("list", ja);
            jb.put("totalRow", totalRow);
            returnMsg.setDatas(jb);
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
     * 审核取样 -402
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkOrder")
    public ResponseMessage checkOrder(@RequestBody Map<String, String> map) {

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

            //取样ID
            String orderIdStr = map.get("orderId");
            if(StringUtils.isBlank(orderIdStr)){
                returnMsg.setResult("402010");
                returnMsg.setMessage(BusinessResultCode.result_402010);
                return returnMsg;
            }

            //审核状态:1-审核通过、2-审核失败
            String statusStr = map.get("status");
            if(StringUtils.isBlank(statusStr)){
                returnMsg.setResult("402020");
                returnMsg.setMessage(BusinessResultCode.result_402020);
                return returnMsg;
            }

            Integer status = Integer.parseInt(statusStr);

            //拒绝理由
            String refuseContent = null;

            if(status == 2){

                //拒绝理由
                refuseContent = map.get("refuseContent");
                if(StringUtils.isBlank(refuseContent)){
                    returnMsg.setResult("402031");
                    returnMsg.setMessage(BusinessResultCode.result_402031);
                    return returnMsg;
                }
            }

            Order order = orderService.get(Long.parseLong(orderIdStr));

            if(order.getStatus() != 1){
                returnMsg.setResult("402041");
                returnMsg.setMessage(BusinessResultCode.result_402041);
                return returnMsg;
            }

            //取样状态：1-等待商家审核、2-商家审核失败、3-取样中、4-商家发样中、5-红人已收样、6-红人寄回样中、7-商家已收样
            order.setStatus(status == 1 ? 3 : 2);
            //拒绝理由
            order.setRefuseContent(refuseContent);
            //审核时间
            order.setCheckDate(new Date());

            orderService.save(order);

            if (status == 2){
                //商品样品剩余数量加1
                commissionGoodsService.updateSurplusNum(order.getGoodsId(), 1);
            }

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
     * 商家发样 -403
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/sendOrder")
    public ResponseMessage sendOrder(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.businessCheckLogin(hrdhCacheService,map);

            //如果result为空则直接返回
            Long businessId ;
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            } else {
                businessId = returnMsg.getDatas().getLong("businessId");
                returnMsg.setDatas(null);
            }

            //取样ID
            String orderIdStr = map.get("orderId");
            if(StringUtils.isBlank(orderIdStr)){
                returnMsg.setResult("403010");
                returnMsg.setMessage(BusinessResultCode.result_403010);
                return returnMsg;
            }

            //商家发样快递公司ID
            String businessExpressId = map.get("businessExpressId");
            if(StringUtils.isBlank(businessExpressId)){
                returnMsg.setResult("403021");
                returnMsg.setMessage(BusinessResultCode.result_403021);
                return returnMsg;
            }

            //商家发样快递单号
            String businessNum = map.get("businessNum");
            if(StringUtils.isBlank(businessNum)){
                returnMsg.setResult("403031");
                returnMsg.setMessage(BusinessResultCode.result_403031);
                return returnMsg;
            }

            //商家寄回样品说明
            String businessExplain = map.get("businessExplain");
            if(StringUtils.isBlank(businessExplain)){
                returnMsg.setResult("403041");
                returnMsg.setMessage(BusinessResultCode.result_403041);
                return returnMsg;
            }

            Order order = orderService.get(Long.parseLong(orderIdStr));

            //取样状态：1-等待商家审核、2-商家审核失败、3-取样中、4-商家发样中、5-红人已收样、6-红人寄回样中、7-商家已收样
            if(order.getStatus() != 3){
                returnMsg.setResult("403051");
                returnMsg.setMessage(BusinessResultCode.result_403051);
                return returnMsg;
            }

            Business business = businessService.get(businessId);

            CommissionGoods commissionGoods = commissionGoodsService.get(order.getGoodsId());

            Sensation sensation =sensationService.get(order.getSensationId());

            //取样状态：1-等待商家审核、2-商家审核失败、3-取样中、4-商家发样中、5-红人已收样、6-红人寄回样中、7-商家已收样
            order.setStatus(4);
            //商家发样快递公司ID
            order.setBusinessExpressId(Long.parseLong(businessExpressId));
            //商家发样快递单号
            order.setBusinessNum(businessNum);
            //商家寄回样品说明
            order.setBusinessExplain(businessExplain);
            //商家收样人手机号
            order.setBusinessConsigneePhone(business.getPhone());
            //商家收样人姓名
            order.setBusinessConsigneeName(business.getConsigneeName());
            //商家收样的地址
            order.setBusinessConsigneeAddress(business.getConsigneeAddress());
            //商家发样时间
            order.setBusinessDeliverDate(new Date());
            //宝贝+券二合一推广链接
            order.setCouponShareUrl(commissionGoods.getCouponShareUrl() + "?relationId=" + sensation.getRelationId());

            orderService.save(order);

            returnMsg.setResult("1");
            returnMsg.setMessage(BusinessResultCode.result_8);
        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(BusinessResultCode.result_100000);
        }

        return returnMsg;
    }

    /**
     * 商家收样 -404
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/collectOrder")
    public ResponseMessage collectOrder(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.businessCheckLogin(hrdhCacheService,map);

            //如果result为空则直接返回
            Long businessId ;
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            } else {
                businessId = returnMsg.getDatas().getLong("businessId");
                returnMsg.setDatas(null);
            }

            //取样ID
            String orderIdStr = map.get("orderId");
            if(StringUtils.isBlank(orderIdStr)){
                returnMsg.setResult("404010");
                returnMsg.setMessage(BusinessResultCode.result_404010);
                return returnMsg;
            }

            Order order = orderService.get(Long.parseLong(orderIdStr));

            //取样状态：1-等待商家审核、2-商家审核失败、3-取样中、4-商家发样中、5-红人已收样、6-红人寄回样中、7-商家已收样
            if(order.getStatus() != 6){
                returnMsg.setResult("404021");
                returnMsg.setMessage(BusinessResultCode.result_404021);
                return returnMsg;
            }

            //取样状态：1-等待商家审核、2-商家审核失败、3-取样中、4-商家发样中、5-红人已收样、6-红人寄回样中、7-商家已收样
            order.setStatus(7);
            //商家收样时间
            order.setBusinessCollectDate(new Date());

            orderService.save(order);

            returnMsg.setResult("1");
            returnMsg.setMessage(BusinessResultCode.result_8);
        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(BusinessResultCode.result_100000);
        }

        return returnMsg;
    }
}
