package com.flym.hrdh.controller.business;


import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.business.CommissionGoodsVm;
import com.flym.hrdh.api.model.business.TakeDeliveryGoodsVm;
import com.flym.hrdh.api.service.business.ICommissionGoodsService;
import com.flym.hrdh.api.service.business.ITakeDeliveryGoodsService;
import com.flym.hrdh.config.CommonConfig;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.business.CommissionGoods;
import com.flym.hrdh.pojo.business.TakeDeliveryGoods;
import com.flym.hrdh.utils.NumUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:商品接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-14</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "goods")
public class GoodsController extends BaseController {

    @Reference(version = "1.0.0")
    protected ICommissionGoodsService commissionGoodsService;

    @Reference(version = "1.0.0")
    protected ITakeDeliveryGoodsService takeDeliveryGoodsService;

    /**
     * 首页搜索商品  -301
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/goodsList")
    public ResponseMessage goodsList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            //检测商品名称不能为空
            String goodsName = map.get("goodsName");
            if(StringUtils.isBlank(goodsName)){
                returnMsg.setResult("301011");
                returnMsg.setMessage(ResultCode.result_301011);
                return returnMsg;
            }

            JSONArray ja = new JSONArray();

            /*List<CommissionGoods> commissionGoodsList = commissionGoodsService.findCommissionGoodsListByGoodsName(goodsName);

            for(CommissionGoods c : commissionGoodsList){

                JSONObject obj = new JSONObject();

                //商品ID
                obj.put("goodsId", c.getId());
                //商品标题
                obj.put("businessTitle", c.getBusinessTitle());
                //主图
                obj.put("mainPic", c.getMainPic());
                //券后价价格
                obj.put("couponAfterPrice", NumUtils.doubleToScale(c.getCouponAfterPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                //优惠券金额
                obj.put("couponPrice", NumUtils.doubleToScale(c.getCouponPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                //佣金
                obj.put("commissionPrice", NumUtils.doubleToScale(c.getCommissionPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                //比例
                obj.put("proportion", c.getProportion());


                ja.add(obj);
            }

            JSONArray jat = new JSONArray();

            List<TakeDeliveryGoods> takeDeliveryGoodsList = takeDeliveryGoodsService.findTakeDeliveryGoodsListByGoodsName(goodsName);

            for(TakeDeliveryGoods t : takeDeliveryGoodsList){

                JSONObject obj = new JSONObject();

                //商品ID
                obj.put("goodsId", t.getId());
                //商品标题
                obj.put("businessTitle", t.getBusinessTitle());
                //主图
                obj.put("mainPic", t.getMainPic());
                //商品价格
                obj.put("goodsPrice", NumUtils.doubleToScale(t.getGoodsPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));

                jat.add(obj);
            }*/

            JSONObject jb = new JSONObject();
            jb.put("commissionList", ja);
            // jb.put("takeDeliveryList", jat);
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
     * 返佣商品列表 -302
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/commissionGoodsList")
    public ResponseMessage commissionGoodsList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(map);

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

            //商品分类ID
            String typeIdStr = map.get("typeId");
            Long typeId = null;
            if(StringUtils.isNotBlank(typeIdStr)){
                typeId = Long.parseLong(typeIdStr);
            }

            JSONArray ja = new JSONArray();

            //获取列表总数
            Integer totalRow = commissionGoodsService.getCommissionGoodsListByTypeNum(typeId);

            if(totalRow > 0) {

                List<CommissionGoods> commissionGoodsList = commissionGoodsService.findCommissionGoodsListByType(typeId, beginNum, pageSize);

                for (CommissionGoods c : commissionGoodsList) {

                    JSONObject obj = new JSONObject();

                    //商品ID
                    obj.put("goodsId", c.getId());
                    //商品标题
                    obj.put("businessTitle", c.getBusinessTitle());
                    //主图
                    obj.put("mainPic", c.getMainPic());
                    //券后价价格
                    obj.put("couponAfterPrice", NumUtils.doubleToScale(c.getCouponAfterPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //优惠券金额
                    obj.put("couponPrice", NumUtils.doubleToScale(c.getCouponPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //佣金
                    obj.put("commissionPrice", NumUtils.doubleToScale(c.getCommissionPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //比例
                    obj.put("proportion", NumUtils.doubleToScale(Double.parseDouble(c.getProportion().toString()) / 100, CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));

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
     * 返佣商品搜索列表 -303
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/commissionGoodsSearchList")
    public ResponseMessage commissionGoodsSearchList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(map);

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

            //检测商品名称不能为空
            String goodsName = map.get("goodsName");
            if(StringUtils.isBlank(goodsName)){
                returnMsg.setResult("303011");
                returnMsg.setMessage(ResultCode.result_303011);
                return returnMsg;
            }

            JSONArray ja = new JSONArray();

            //获取列表总数
            Integer totalRow = commissionGoodsService.getCommissionGoodsListByGoodsNameNum(goodsName);

            if(totalRow > 0) {

                List<CommissionGoods> commissionGoodsList = commissionGoodsService.findCommissionGoodsListByGoodsName(goodsName, beginNum, pageSize);

                for (CommissionGoods c : commissionGoodsList) {

                    JSONObject obj = new JSONObject();

                    //商品ID
                    obj.put("goodsId", c.getId());
                    //商品标题
                    obj.put("businessTitle", c.getBusinessTitle());
                    //主图
                    obj.put("mainPic", c.getMainPic());
                    //券后价价格
                    obj.put("couponAfterPrice", NumUtils.doubleToScale(c.getCouponAfterPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //优惠券金额
                    obj.put("couponPrice", NumUtils.doubleToScale(c.getCouponPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //佣金
                    obj.put("commissionPrice", NumUtils.doubleToScale(c.getCommissionPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //比例
                    obj.put("proportion", NumUtils.doubleToScale(Double.parseDouble(c.getProportion().toString()) / 100, CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));

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
     * 返佣商品详情 -304
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/commissionGoodsInfo")
    public ResponseMessage commissionGoodsInfo(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            //检测商品ID不能为空
            String goodsId = map.get("goodsId");
            if(StringUtils.isBlank(goodsId)){
                returnMsg.setResult("304010");
                returnMsg.setMessage(ResultCode.result_304010);
                return returnMsg;
            }

            JSONObject jb = new JSONObject();

            CommissionGoodsVm c = commissionGoodsService.getCommissionGoodsVm(Long.parseLong(goodsId));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            //商品ID
            jb.put("goodsId", c.getId());
            //商品标题
            jb.put("businessTitle", c.getBusinessTitle());
            //主图
            jb.put("mainPic", c.getMainPic());
            //商品详细
            jb.put("details", c.getDetails());
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
            //取样条件数量
            jb.put("conditionNum", c.getConditionNum());
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
            //商家等级：1-初级、2-中级、3-高级
            jb.put("gradeType", c.getGradeType());
            //淘宝信誉等级：1-20
            jb.put("reputationType", c.getReputationType());
            //拍摄要求
            jb.put("shotRequirement", c.getShotRequirement());
            //规则说明
            jb.put("ruleDescription", c.getRuleDescription());
            //宝贝链接
            jb.put("itemUrl", c.getItemUrl());

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
     * 拿货商品列表 -305
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/takeDeliveryGoodsList")
    public ResponseMessage takeDeliveryGoodsList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(map);

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

            //商品分类ID
            String typeIdStr = map.get("typeId");
            Long typeId = null;
            if(StringUtils.isNotBlank(typeIdStr)){
                typeId = Long.parseLong(typeIdStr);
            }

            JSONArray ja = new JSONArray();

            //获取列表总数
            Integer totalRow = takeDeliveryGoodsService.getTakeDeliveryGoodsListByTypeNum(typeId);

            if(totalRow > 0) {

                List<TakeDeliveryGoods> takeDeliveryGoodsList = takeDeliveryGoodsService.findTakeDeliveryGoodsListByType(typeId, beginNum, pageSize);

                for (TakeDeliveryGoods t : takeDeliveryGoodsList) {

                    JSONObject obj = new JSONObject();

                    //商品ID
                    obj.put("goodsId", t.getId());
                    //商品标题
                    obj.put("businessTitle", t.getBusinessTitle());
                    //主图
                    obj.put("mainPic", t.getMainPic());
                    //商品价格
                    obj.put("goodsPrice", NumUtils.doubleToScale(t.getGoodsPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));

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
     * 拿货商品搜索列表 -306
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/takeDeliveryGoodsSearchList")
    public ResponseMessage takeDeliveryGoodsSearchList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(map);

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

            //检测商品名称不能为空
            String goodsName = map.get("goodsName");
            if(StringUtils.isBlank(goodsName)){
                returnMsg.setResult("306011");
                returnMsg.setMessage(ResultCode.result_306011);
                return returnMsg;
            }

            JSONArray ja = new JSONArray();

            //获取列表总数
            Integer totalRow = takeDeliveryGoodsService.getTakeDeliveryGoodsListByGoodsNameNum(goodsName);

            if(totalRow > 0) {

                List<TakeDeliveryGoods> takeDeliveryGoodsList = takeDeliveryGoodsService.findTakeDeliveryGoodsListByGoodsName(goodsName, beginNum, pageSize);

                for (TakeDeliveryGoods t : takeDeliveryGoodsList) {

                    JSONObject obj = new JSONObject();

                    //商品ID
                    obj.put("goodsId", t.getId());
                    //商品标题
                    obj.put("businessTitle", t.getBusinessTitle());
                    //主图
                    obj.put("mainPic", t.getMainPic());
                    //商品价格
                    obj.put("goodsPrice", NumUtils.doubleToScale(t.getGoodsPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));

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
     * 拿货商品详情 -307
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/takeDeliveryGoodsInfo")
    public ResponseMessage takeDeliveryGoodsInfo(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            //检测商品ID不能为空
            String goodsId = map.get("goodsId");
            if(StringUtils.isBlank(goodsId)){
                returnMsg.setResult("307010");
                returnMsg.setMessage(ResultCode.result_307010);
                return returnMsg;
            }

            JSONObject jb = new JSONObject();

            TakeDeliveryGoodsVm t = takeDeliveryGoodsService.getTakeDeliveryGoodsVm(Long.parseLong(goodsId));

            //商品ID
            jb.put("goodsId", t.getId());
            //商品标题
            jb.put("businessTitle", t.getBusinessTitle());
            //主图
            jb.put("mainPic", t.getMainPic());
            //商品价格
            jb.put("goodsPrice", NumUtils.doubleToScale(t.getGoodsPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
            //商家头像
            jb.put("headPic", t.getHeadPic());
            //店铺名称
            jb.put("shopName", t.getShopName());
            //宝贝描述
            jb.put("babyDescription", NumUtils.doubleToScale(t.getBabyDescription(), 1));
            //卖家服务
            jb.put("sellerServices", NumUtils.doubleToScale(t.getSellerServices(), 1));
            //物流服务
            jb.put("logisticsService", NumUtils.doubleToScale(t.getLogisticsService(), 1));
            //商家等级：1-初级、2-中级、3-高级
            jb.put("gradeType", t.getGradeType());
            //商家客服电话
            jb.put("customerPhone", t.getCustomerPhone());
            //商家客服微信二维码
            jb.put("customerWeChatPic", t.getCustomerWeChatPic());
            //淘宝信誉等级：1-20
            jb.put("reputationType", t.getReputationType());
            //商品详细
            jb.put("details", t.getDetails());

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
     * 品牌馆返佣商品列表 -308
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/brandCommissionGoodsList")
    public ResponseMessage brandCommissionGoodsList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(map);

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

            //检测商品名称不能为空
            String goodsName = map.get("goodsName");

            //商品分类ID
            String typeIdStr = map.get("typeId");
            Long typeId = null;
            if(StringUtils.isNotBlank(typeIdStr)){
                typeId = Long.parseLong(typeIdStr);
            }

            JSONArray ja = new JSONArray();

            //获取列表总数
            Integer totalRow = commissionGoodsService.getCommissionGoodsListByGradeNum(goodsName, typeId);

            if(totalRow > 0) {

                List<CommissionGoods> commissionGoodsList = commissionGoodsService.findCommissionGoodsListByGrade(goodsName, typeId, beginNum, pageSize);

                for (CommissionGoods c : commissionGoodsList) {

                    JSONObject obj = new JSONObject();

                    //商品ID
                    obj.put("goodsId", c.getId());
                    //商品标题
                    obj.put("businessTitle", c.getBusinessTitle());
                    //主图
                    obj.put("mainPic", c.getMainPic());
                    //券后价价格
                    obj.put("couponAfterPrice", NumUtils.doubleToScale(c.getCouponAfterPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //优惠券金额
                    obj.put("couponPrice", NumUtils.doubleToScale(c.getCouponPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //佣金
                    obj.put("commissionPrice", NumUtils.doubleToScale(c.getCommissionPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //比例
                    obj.put("proportion", NumUtils.doubleToScale(Double.parseDouble(c.getProportion().toString()) / 100, CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));

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
     * 品牌馆拿货商品列表 -309
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/brandTakeDeliveryGoodsList")
    public ResponseMessage brandTakeDeliveryGoodsList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(map);

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

            //检测商品名称不能为空
            String goodsName = map.get("goodsName");

            //商品分类ID
            String typeIdStr = map.get("typeId");
            Long typeId = null;
            if(StringUtils.isNotBlank(typeIdStr)){
                typeId = Long.parseLong(typeIdStr);
            }

            JSONArray ja = new JSONArray();

            //获取列表总数
            Integer totalRow = takeDeliveryGoodsService.getTakeDeliveryGoodsListByGradeNum(goodsName, typeId);

            if(totalRow > 0) {

                List<TakeDeliveryGoods> takeDeliveryGoodsList = takeDeliveryGoodsService.findTakeDeliveryGoodsListByGrade(goodsName, typeId, beginNum, pageSize);

                for (TakeDeliveryGoods t : takeDeliveryGoodsList) {

                    JSONObject obj = new JSONObject();

                    //商品ID
                    obj.put("goodsId", t.getId());
                    //商品标题
                    obj.put("businessTitle", t.getBusinessTitle());
                    //主图
                    obj.put("mainPic", t.getMainPic());
                    //商品价格
                    obj.put("goodsPrice", NumUtils.doubleToScale(t.getGoodsPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));

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

}
