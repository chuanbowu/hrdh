package com.flym.hrdh.controller.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.business.CommissionGoodsVm;
import com.flym.hrdh.api.model.order.OrderVm;
import com.flym.hrdh.api.service.business.IBusinessService;
import com.flym.hrdh.api.service.business.ICommissionGoodsService;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.api.service.order.IOrderService;
import com.flym.hrdh.api.service.sensation.ISensationService;
import com.flym.hrdh.config.CommonConfig;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.business.Business;
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
 * <p>Copyright: Copyright (c) 2020-06-04</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "order")
public class OrderController extends BaseController {

    @Reference(version = "1.0.0")
    protected IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected IOrderService orderService;

    @Reference(version = "1.0.0")
    protected ICommissionGoodsService commissionGoodsService;

    @Reference(version = "1.0.0")
    protected ISensationService sensationService;

    @Reference(version = "1.0.0")
    protected IBusinessService businessService;

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

            returnMsg = super.checkLogin(hrdhCacheService,map);
            Long sensationId ;
            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }else{
                sensationId = Long.parseLong(returnMsg.getDatas().toString());
                returnMsg.setDatas(null);
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

            JSONArray ja = new JSONArray();

            //获取列表总数
            Integer totalRow = orderService.getOrderVmNumBySensationId(sensationId);

            if (totalRow > 0) {

                List<OrderVm> orderVmList = orderService.findOrderVmListBySensationId(sensationId, beginNum, pageSize);

                for (OrderVm o : orderVmList) {

                    JSONObject obj = new JSONObject();

                    //ID
                    obj.put("orderId", o.getId());
                    //商品标题
                    obj.put("businessTitle", o.getBusinessTitle());
                    //主图
                    obj.put("mainPic", o.getMainPic());
                    //券后价价格
                    obj.put("couponAfterPrice", NumUtils.doubleToScale(o.getCouponAfterPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //佣金
                    obj.put("commissionPrice", NumUtils.doubleToScale(o.getCommissionPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //比例
                    obj.put("proportion", NumUtils.doubleToScale(Double.parseDouble(o.getProportion().toString()) / 100, CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //押金金额
                    obj.put("depositPrice", NumUtils.doubleToScale(o.getDepositPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));

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
     * 取样详情 -402
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/orderInfo")
    public ResponseMessage orderInfo(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkLogin(hrdhCacheService,map);
            Long sensationId ;
            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }else{
                sensationId = Long.parseLong(returnMsg.getDatas().toString());
                returnMsg.setDatas(null);
            }

            //取样ID
            String orderIdStr = map.get("orderId");
            if(StringUtils.isBlank(orderIdStr)){
                returnMsg.setResult("401010");
                returnMsg.setMessage(ResultCode.result_401010);
                return returnMsg;
            }

            Long orderId = Long.parseLong(orderIdStr);

            OrderVm orderVm = orderService.getOrderInfo(orderId);

            JSONObject obj = new JSONObject();

            //ID
            obj.put("orderId", orderVm.getId());
            //取样状态：1-等待商家审核、2-商家审核失败、3-取样中、4-商家发样中、5-红人已收样、6-红人寄回样中、7-商家已收样
            //取样状态：1-等待商家审核、2-商家审核失败、3-待商家发样、4-商家已发样、5-红人已收样、6-红人已寄回样品、7-商家已收样
            obj.put("status", orderVm.getStatus());
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
            //押金金额
            obj.put("depositPrice", NumUtils.doubleToScale(orderVm.getDepositPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
            //宝贝+券二合一推广链接
            obj.put("couponShareUrl", orderVm.getCouponShareUrl() == null ? "" : orderVm.getCouponShareUrl());
            //已推广（件）
            obj.put("extensionNum", orderVm.getExtensionNum());
            //收益
            obj.put("profitPrice", orderVm.getProfitPrice() == null ? 0.00 : NumUtils.doubleToScale(orderVm.getProfitPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
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

            returnMsg.setDatas(obj);
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
     * 获取取样信息 -403
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/getOrderInfo")
    public ResponseMessage getOrderInfo(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkLogin(hrdhCacheService,map);
            Long sensationId ;
            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }else{
                sensationId = Long.parseLong(returnMsg.getDatas().toString());
                returnMsg.setDatas(null);
            }

            //检测商品ID不能为空
            String goodsIdStr = map.get("goodsId");
            if(StringUtils.isBlank(goodsIdStr)){
                returnMsg.setResult("403010");
                returnMsg.setMessage(ResultCode.result_403010);
                return returnMsg;
            }

            Sensation sensation = sensationService.get(sensationId);

            //认证状态：1-未完善个人资料、2-未认证、3-认证中、4-认证拒绝、5-已认证
            if (sensation.getStatus() != 5){
                returnMsg.setResult("403021");
                returnMsg.setMessage(ResultCode.result_403021);
                return returnMsg;
            }

            //判断是否授权
            if(sensation.getRelationId() == null){
                returnMsg.setResult("403031");
                returnMsg.setMessage(ResultCode.result_403031);
                return returnMsg;
            }

            Long goodsId = Long.parseLong(goodsIdStr);

            int num = orderService.getOrderNumSensationIdOrGoodsId(sensationId, goodsId);

            if(num > 0){
                returnMsg.setResult("403051");
                returnMsg.setMessage(ResultCode.result_403051);
                return returnMsg;
            }

            JSONObject jb = new JSONObject();

            CommissionGoodsVm c = commissionGoodsService.getCommissionGoodsVm(goodsId);

            //样品剩余数量
            if (c.getSurplusNum() <= 0){
                returnMsg.setResult("403041");
                returnMsg.setMessage(ResultCode.result_403041);
                return returnMsg;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            //商品ID
            jb.put("goodsId", c.getId());
            //商品标题
            jb.put("businessTitle", c.getBusinessTitle());
            //主图
            jb.put("mainPic", c.getMainPic());
            //券后价价格
            jb.put("couponAfterPrice", NumUtils.doubleToScale(c.getCouponAfterPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
            //优惠券金额
            jb.put("couponPrice", NumUtils.doubleToScale(c.getCouponPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
            //佣金
            jb.put("commissionPrice", NumUtils.doubleToScale(c.getCommissionPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
            //比例
            jb.put("proportion", NumUtils.doubleToScale(Double.parseDouble(c.getProportion().toString()) / 100, CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
            //样品剩余数量
            jb.put("surplusNum", c.getSurplusNum());
            //押金金额
            jb.put("depositPrice", NumUtils.doubleToScale(c.getDepositPric(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
            //活动开始时间
            jb.put("activityStartDate", c.getActivityStartDate() ==  null ? "" : sdf.format(c.getActivityStartDate()));
            //活动结束时间
            jb.put("activityEndDate", c.getActivityEndDate() == null ? "" : sdf.format(c.getActivityEndDate()));
            //商家头像
            jb.put("headPic", c.getHeadPic());
            //店铺名称
            jb.put("shopName", c.getShopName());
            //宝贝描述
            jb.put("babyDescription", NumUtils.doubleToScale(c.getBabyDescription(), 1));
            //卖家服务
            jb.put("sellerServices", NumUtils.doubleToScale(c.getSellerServices(), 1));
            //物流服务
            jb.put("logisticsService", NumUtils.doubleToScale(c.getLogisticsService(), 1));
            //淘宝信誉等级：1-20
            jb.put("reputationType", c.getReputationType());
            //宝贝链接
            jb.put("itemUrl", c.getItemUrl());

            //收样人手机号码
            jb.put("phone", sensation.getPhone());
            //收样人姓名
            jb.put("consigneeName", sensation.getConsigneeName());
            //收样地址
            jb.put("consigneeAddress", sensation.getConsigneeAddress());

            //支付押金：1-是、2-否
            Integer paymentDeposit = 1;
            //取样条件数量 <= 粉丝数量
            if(c.getConditionNum() <= sensation.getFollowersNum()){
                paymentDeposit = 2;
            }
            jb.put("paymentDeposit", paymentDeposit);

            Business business = businessService.get(c.getBusinessId());

            //商家客服电话
            jb.put("customerPhone", business.getCustomerPhone());
            //商家客服微信二维码
            jb.put("customerWeChatPic", business.getCustomerWeChatPic());

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
     * 确认取样 -404
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/addOrder")
    public ResponseMessage addOrder(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkLogin(hrdhCacheService,map);
            Long sensationId ;
            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }else{
                sensationId = Long.parseLong(returnMsg.getDatas().toString());
                returnMsg.setDatas(null);
            }

            //检测商品ID不能为空
            String goodsIdStr = map.get("goodsId");
            if(StringUtils.isBlank(goodsIdStr)){
                returnMsg.setResult("404010");
                returnMsg.setMessage(ResultCode.result_404010);
                return returnMsg;
            }

            Long goodsId = Long.parseLong(goodsIdStr);

            //支付押金：1-是、2-否
            String paymentDepositStr = map.get("paymentDeposit");
            if(StringUtils.isBlank(paymentDepositStr)){
                returnMsg.setResult("404020");
                returnMsg.setMessage(ResultCode.result_404020);
                return returnMsg;
            }

            int num = orderService.getOrderNumSensationIdOrGoodsId(sensationId, goodsId);

            if(num > 0){
                returnMsg.setResult("404031");
                returnMsg.setMessage(ResultCode.result_404031);
                return returnMsg;
            }

            Integer paymentDeposit = Integer.parseInt(paymentDepositStr);

            Sensation sensation = sensationService.get(sensationId);

            Order order = new Order();

            //红人ID
            order.setSensationId(sensationId);
            //商品ID
            order.setGoodsId(goodsId);
            //红人收样人手机号
            order.setSensationConsigneePhone(sensation.getPhone());
            //红人收样人姓名
            order.setSensationConsigneeName(sensation.getConsigneeName());
            //红人收样地址
            order.setSensationConsigneeAddress(sensation.getConsigneeAddress());
            //支付方式：1-支付宝、2-微信、3-不需要
            order.setPaymentType(3);
            //支付押金：1-是、2-否
            order.setPaymentDeposit(paymentDeposit);
            //押金金额
            order.setDepositPrice(0.00);
            //取样状态：1-等待商家审核、2-商家审核失败、3-取样中、4-商家发样中、5-红人已收样、6-红人寄回样中、7-商家已收样
            order.setStatus(paymentDeposit == 1 ? 1 : 3);
            //创建时间
            order.setCreateDate(new Date());

            orderService.save(order);

            //商品样品剩余数量减一
            commissionGoodsService.updateSurplusNum(goodsId, 2);

            returnMsg.setResult("1");
            returnMsg.setMessage(paymentDeposit == 1 ? ResultCode.result_9 + ",请等待审核" : ResultCode.result_9 );
        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(ResultCode.result_100000);
        }

        return returnMsg;
    }

    /**
     * 红人确认收样 -405
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/receivingOrder")
    public ResponseMessage receivingOrder(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkLogin(hrdhCacheService,map);
            Long sensationId ;
            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }else{
                sensationId = Long.parseLong(returnMsg.getDatas().toString());
                returnMsg.setDatas(null);
            }

            //取样ID
            String orderIdStr = map.get("orderId");
            if(StringUtils.isBlank(orderIdStr)){
                returnMsg.setResult("405010");
                returnMsg.setMessage(ResultCode.result_405010);
                return returnMsg;
            }

            Order order = orderService.get(Long.parseLong(orderIdStr));

            //取样状态：1-等待商家审核、2-商家审核失败、3-取样中、4-商家发样中、5-红人已收样、6-红人寄回样中、7-商家已收样
            if(order.getStatus() != 4){
                returnMsg.setResult("405021");
                returnMsg.setMessage(ResultCode.result_405021);
                return returnMsg;
            }

            //取样状态：1-等待商家审核、2-商家审核失败、3-取样中、4-商家发样中、5-红人已收样、6-红人寄回样中、7-商家已收样
            order.setStatus(5);
            //红人收样时间
            order.setSensationReceivingDate(new Date());

            orderService.save(order);

            returnMsg.setResult("1");
            returnMsg.setMessage(ResultCode.result_10);
        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(ResultCode.result_100000);
        }

        return returnMsg;
    }

    /**
     * 红人寄回样 -406
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/sendOrder")
    public ResponseMessage sendOrder(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkLogin(hrdhCacheService,map);
            Long sensationId ;
            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }else{
                sensationId = Long.parseLong(returnMsg.getDatas().toString());
                returnMsg.setDatas(null);
            }

            //取样ID
            String orderIdStr = map.get("orderId");
            if(StringUtils.isBlank(orderIdStr)){
                returnMsg.setResult("406010");
                returnMsg.setMessage(ResultCode.result_406010);
                return returnMsg;
            }

            //红人发样快递公司ID
            String sensationExpressId = map.get("sensationExpressId");
            if(StringUtils.isBlank(sensationExpressId)){
                returnMsg.setResult("406021");
                returnMsg.setMessage(ResultCode.result_406021);
                return returnMsg;
            }

            //红人发样快递单号
            String sensationNum = map.get("sensationNum");
            if(StringUtils.isBlank(sensationNum)){
                returnMsg.setResult("406031");
                returnMsg.setMessage(ResultCode.result_406031);
                return returnMsg;
            }

            Order order = orderService.get(Long.parseLong(orderIdStr));

            //取样状态：1-等待商家审核、2-商家审核失败、3-取样中、4-商家发样中、5-红人已收样、6-红人寄回样中、7-商家已收样
            if(order.getStatus() != 5){
                returnMsg.setResult("406041");
                returnMsg.setMessage(ResultCode.result_406041);
                return returnMsg;
            }

            //取样状态：1-等待商家审核、2-商家审核失败、3-取样中、4-商家发样中、5-红人已收样、6-红人寄回样中、7-商家已收样
            order.setStatus(6);
            //红人寄样时间
            order.setSensationSendDate(new Date());
            //红人发样快递公司ID
            order.setSensationExpressId(Long.parseLong(sensationExpressId));
            //红人发样快递单号
            order.setSensationNum(sensationNum);

            orderService.save(order);

            returnMsg.setResult("1");
            returnMsg.setMessage(ResultCode.result_7);
        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(ResultCode.result_100000);
        }

        return returnMsg;
    }
}
