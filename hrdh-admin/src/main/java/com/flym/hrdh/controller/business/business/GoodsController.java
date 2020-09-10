package com.flym.hrdh.controller.business.business;


import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.business.CommissionGoodsVm;
import com.flym.hrdh.api.model.business.TakeDeliveryGoodsVm;
import com.flym.hrdh.api.service.business.IBusinessService;
import com.flym.hrdh.api.service.business.ICommissionGoodsService;
import com.flym.hrdh.api.service.business.ITakeDeliveryGoodsService;
import com.flym.hrdh.api.service.common.IGoodsNumConfigService;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.config.*;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.business.Business;
import com.flym.hrdh.pojo.business.CommissionGoods;
import com.flym.hrdh.pojo.business.TakeDeliveryGoods;
import com.flym.hrdh.pojo.common.GoodsNumConfig;
import com.flym.hrdh.utils.NumUtils;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkDgMaterialOptionalRequest;
import com.taobao.api.response.TbkDgMaterialOptionalResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.rmi.runtime.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:商品接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-23</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/business/goods")
public class GoodsController extends BaseController {

    @Reference(version = "1.0.0")
    protected IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected ICommissionGoodsService commissionGoodsService;

    @Reference(version = "1.0.0")
    protected ITakeDeliveryGoodsService takeDeliveryGoodsService;

    @Reference(version = "1.0.0")
    protected IBusinessService businessService;

    @Reference(version = "1.0.0")
    protected IGoodsNumConfigService goodsNumConfigService;

    /**
     * 返佣商品列表 -301
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/commissionGoodsList")
    public ResponseMessage commissionGoodsList(@RequestBody Map<String, String> map) {

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

            //商品标题
            String businessTitle = map.get("businessTitle");

            //商品分类ID
            String typeIdStr = map.get("typeId");
            Long typeId = null;
            if(StringUtils.isNotBlank(typeIdStr)){
                typeId = Long.parseLong(typeIdStr);
            }

            JSONArray ja = new JSONArray();

            //获取列表总数
            Integer totalRow = commissionGoodsService.getCommissionGoodsListByBusinessId(businessId, businessTitle, typeId);

            if(totalRow > 0) {

                List<CommissionGoodsVm> commissionGoodsList = commissionGoodsService.findCommissionGoodsListByBusinessId(businessId, businessTitle, typeId, beginNum, pageSize);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                for (CommissionGoodsVm c : commissionGoodsList) {

                    JSONObject obj = new JSONObject();

                    //商品ID
                    obj.put("goodsId", c.getId());
                    //商品标题
                    obj.put("businessTitle", c.getBusinessTitle());
                    //主图
                    obj.put("mainPic", c.getMainPic());
                    //商品详细
                    obj.put("details", c.getDetails());
                    //商品分类ID
                    obj.put("typeId", c.getTypeId());
                    //商品分类名称
                    obj.put("typeName", c.getTypeName());
                    //券后价价格
                    obj.put("couponAfterPrice", NumUtils.doubleToScale(c.getCouponAfterPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //优惠券金额
                    obj.put("couponPrice", NumUtils.doubleToScale(c.getCouponPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //佣金
                    obj.put("commissionPrice", NumUtils.doubleToScale(c.getCommissionPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //比例
                    obj.put("proportion", NumUtils.doubleToScale(Double.parseDouble(c.getProportion().toString()) / 100, CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //样品剩余数量
                    obj.put("surplusNum", c.getSurplusNum());
                    //取样条件数量
                    obj.put("conditionNum", c.getConditionNum());
                    //押金金额
                    obj.put("depositPrice", NumUtils.doubleToScale(c.getDepositPric(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //活动开始时间
                    obj.put("activityStartDate", c.getActivityStartDate() == null ? "" :  sdf.format(c.getActivityStartDate()));
                    //活动结束时间
                    obj.put("activityEndDate", c.getActivityEndDate() == null ? "" : sdf.format(c.getActivityEndDate()));
                    //拍摄要求
                    obj.put("shotRequirement", c.getShotRequirement());
                    //规则说明
                    obj.put("ruleDescription", c.getRuleDescription());
                    //宝贝ID
                    obj.put("itemId", c.getItemId());
                    //宝贝链接
                    obj.put("itemUrl", c.getItemUrl());
                    //推广链接
                    obj.put("promotionLink", c.getPromotionLink());
                    //宝贝+券二合一推广链接
                    obj.put("couponShareUrl", c.getCouponShareUrl() == null ? "" : c.getCouponShareUrl());
                    //状态：1-正常、2-禁用、3-删除
                    obj.put("status", c.getStatus());
                    //创建时间
                    obj.put("createDate", sf.format(c.getCreateDate()));

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
            returnMsg.setMessage(BusinessResultCode.result_100000);
        }

        return returnMsg;
    }

    /**
     * 新增返佣商品 -302
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/addCommissionGoods")
    public ResponseMessage addCommissionGoods(@RequestBody Map<String, String> map) {

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

            //商品标题
            String businessTitle = map.get("businessTitle");
            if(StringUtils.isBlank(businessTitle)){
                returnMsg.setResult("302011");
                returnMsg.setMessage(BusinessResultCode.result_302011);
                return returnMsg;
            }

            //主图
            String mainPic = map.get("mainPic");
            if(StringUtils.isBlank(mainPic)){
                returnMsg.setResult("302021");
                returnMsg.setMessage(BusinessResultCode.result_302021);
                return returnMsg;
            }

            //商品分类ID
            String typeId = map.get("typeId");
            if(StringUtils.isBlank(typeId)){
                returnMsg.setResult("302031");
                returnMsg.setMessage(BusinessResultCode.result_302031);
                return returnMsg;
            }

            //商品详细
            String details = map.get("details");
            if(StringUtils.isBlank(details)){
                returnMsg.setResult("302041");
                returnMsg.setMessage(BusinessResultCode.result_302041);
                return returnMsg;
            }

            //券后价价格
            String couponAfterPrice = map.get("couponAfterPrice");
            if(StringUtils.isBlank(couponAfterPrice)){
                returnMsg.setResult("302051");
                returnMsg.setMessage(BusinessResultCode.result_302051);
                return returnMsg;
            }

            //优惠券金额
            String couponPrice = map.get("couponPrice");
            if(StringUtils.isBlank(couponPrice)){
                returnMsg.setResult("302061");
                returnMsg.setMessage(BusinessResultCode.result_302061);
                return returnMsg;
            }

            //佣金
            String commissionPrice = map.get("commissionPrice");
            if(StringUtils.isBlank(commissionPrice)){
                returnMsg.setResult("302071");
                returnMsg.setMessage(BusinessResultCode.result_302071);
                return returnMsg;
            }

            //比例
            String proportion = map.get("proportion");
            if(StringUtils.isBlank(proportion)){
                returnMsg.setResult("302081");
                returnMsg.setMessage(BusinessResultCode.result_302081);
                return returnMsg;
            }

            //样品剩余数量
            String surplusNum = map.get("surplusNum");
            if(StringUtils.isBlank(surplusNum)){
                returnMsg.setResult("302091");
                returnMsg.setMessage(BusinessResultCode.result_302091);
                return returnMsg;
            }

            //拍摄要求
            String shotRequirement = map.get("shotRequirement");
            if(StringUtils.isBlank(shotRequirement)){
                returnMsg.setResult("302101");
                returnMsg.setMessage(BusinessResultCode.result_302101);
                return returnMsg;
            }

            //规则说明
            String ruleDescription = map.get("ruleDescription");
            if(StringUtils.isBlank(ruleDescription)){
                returnMsg.setResult("302111");
                returnMsg.setMessage(BusinessResultCode.result_302111);
                return returnMsg;
            }

            //取样条件数量
            String conditionNum = map.get("conditionNum");
            if(StringUtils.isBlank(conditionNum)){
                returnMsg.setResult("302121");
                returnMsg.setMessage(BusinessResultCode.result_302121);
                return returnMsg;
            }

            //押金金额
            String depositPric = map.get("depositPric");
            if(StringUtils.isBlank(depositPric)){
                returnMsg.setResult("302131");
                returnMsg.setMessage(BusinessResultCode.result_302131);
                return returnMsg;
            }

            //活动开始时间
            String activityStartDate = map.get("activityStartDate");
            /*if(StringUtils.isBlank(activityStartDate)){
                returnMsg.setResult("302141");
                returnMsg.setMessage(BusinessResultCode.result_302141);
                return returnMsg;
            }*/

            //活动结束时间
            String activityEndDate = map.get("activityEndDate");
           /* if(StringUtils.isBlank(activityEndDate)){
                returnMsg.setResult("302151");
                returnMsg.setMessage(BusinessResultCode.result_302151);
                return returnMsg;
            }*/

            //宝贝ID
            String itemId = map.get("itemId");
            if(StringUtils.isBlank(itemId)){
                returnMsg.setResult("302180");
                returnMsg.setMessage(BusinessResultCode.result_302180);
                return returnMsg;
            }

            //宝贝链接
            String itemUrl = map.get("itemUrl");
            if(StringUtils.isBlank(itemUrl)){
                returnMsg.setResult("302191");
                returnMsg.setMessage(BusinessResultCode.result_302191);
                return returnMsg;
            }

            //推广链接
            String promotionLink = map.get("promotionLink");
            if(StringUtils.isBlank(promotionLink)){
                returnMsg.setResult("302160");
                returnMsg.setMessage(BusinessResultCode.result_302160);
                return returnMsg;
            }

            //宝贝+券二合一推广链接
            String couponShareUrl = map.get("couponShareUrl");

            Business b = businessService.get(businessId);

            //商家等级：1-初级、2-中级、3-高级
            Integer gradeType = b.getGradeType();

            Date date = new Date();

            //获取这个月上新数量
            int num = commissionGoodsService.getNewCommissionGoodsNum(businessId, date);

            GoodsNumConfig goodsNumConfig = goodsNumConfigService.getGoodsNumConfig();

            if(gradeType == 1 && num >= goodsNumConfig.getPrimaryNum()){
                returnMsg.setResult("302171");
                returnMsg.setMessage(BusinessResultCode.result_302171);
                return returnMsg;
            }else if(gradeType == 2 && num >= goodsNumConfig.getIntermediateNum()){
                returnMsg.setResult("302171");
                returnMsg.setMessage(BusinessResultCode.result_302171);
                return returnMsg;
            }else if(gradeType == 3 && num >= goodsNumConfig.getSeniorNum()){
                returnMsg.setResult("302171");
                returnMsg.setMessage(BusinessResultCode.result_302171);
                return returnMsg;
            }

            //判断一下宝贝ID是否被用
            Long itemIds = commissionGoodsService.getIdByItemId(Long.parseLong(itemId));
            if(itemIds != null){
                returnMsg.setResult("302201");
                returnMsg.setMessage(BusinessResultCode.result_302201);
                return returnMsg;
            }

            CommissionGoods c = new CommissionGoods();

            //商家ID
            c.setBusinessId(businessId);
            //商品标题
            c.setBusinessTitle(businessTitle);
            //主图
            c.setMainPic(mainPic);
            //商品分类ID
            c.setTypeId(Long.parseLong(typeId));
            //商品详细
            c.setDetails(details);
            //券后价价格
            c.setCouponAfterPrice(Double.parseDouble(couponAfterPrice));
            //优惠券金额
            c.setCouponPrice(Double.parseDouble(couponPrice));
            //佣金
            c.setCommissionPrice(Double.parseDouble(commissionPrice));
            //比例
            c.setProportion((int) (Double.parseDouble(proportion) * 100));
            //样品剩余数量
            c.setSurplusNum(Integer.parseInt(surplusNum));
            //拍摄要求
            c.setShotRequirement(shotRequirement);
            //规则说明
            c.setRuleDescription(ruleDescription);
            //取样条件数量
            c.setConditionNum(Integer.parseInt(conditionNum));
            //押金金额
            c.setDepositPric(Double.parseDouble(depositPric));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //活动开始时间
            c.setActivityStartDate(activityStartDate == "" ? null : sdf.parse(activityStartDate));
            //活动结束时间
            c.setActivityEndDate(activityStartDate == "" ? null : sdf.parse(activityEndDate));
            //推广链接
            c.setPromotionLink(TaobaoApi.getTbkSpreadGetRequest(promotionLink));
            //状态：1-正常、2-禁用、3-删除
            c.setStatus(1);
            //创建时间
            c.setCreateDate(date);
            //宝贝ID
            c.setItemId(Long.parseLong(itemId));
            //宝贝地址
            c.setItemUrl(itemUrl);
            //宝贝+券二合一推广链接
            c.setCouponShareUrl(couponShareUrl == null ? couponShareUrl : TaobaoApi.getTbkSpreadGetRequest(couponShareUrl));

            commissionGoodsService.save(c);

            returnMsg.setResult("1");
            returnMsg.setMessage(BusinessResultCode.result_7);

        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(BusinessResultCode.result_100000);
        }

        return returnMsg;
    }

    /**
     * 修改返佣商品状态 -303
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateCommissionGoodsStatus")
    public ResponseMessage updateCommissionGoodsStatus(@RequestBody Map<String, String> map) {

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

            //检测商品ID不能为空
            String goodsIds = map.get("goodsIds");
            if(StringUtils.isBlank(goodsIds)){
                returnMsg.setResult("303010");
                returnMsg.setMessage(BusinessResultCode.result_303010);
                return returnMsg;
            }

            //检测操作类型不能为空：1-正常、2-禁用、3-删除
            String statusStr= map.get("status");
            if(StringUtils.isBlank(statusStr)){
                returnMsg.setResult("303020");
                returnMsg.setMessage(BusinessResultCode.result_303020);
                return returnMsg;
            }

            int status = Integer.parseInt(statusStr);

            //当前时间
            Date date = new Date();

            commissionGoodsService.updateStatus(goodsIds, status, date);

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
     * 拿货商品列表 -304
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/takeDeliveryGoodsList")
    public ResponseMessage takeDeliveryGoodsList(@RequestBody Map<String, String> map) {

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
            int pageSize = CommonConfig.RESTFUL_LIST_DEFAULT_PAGE_SIZE;
            String pageSizeStr = map.get("pageSize");
            if (StringUtils.isNotBlank(pageSizeStr)) {
                pageSize = Integer.parseInt(pageSizeStr);
            }

            //开始条数
            int beginNum = pageSize * (pageNum - 1);

            //商品标题
            String businessTitle = map.get("businessTitle");

            //商品分类ID
            String typeIdStr = map.get("typeId");
            Long typeId = null;
            if(StringUtils.isNotBlank(typeIdStr)){
                typeId = Long.parseLong(typeIdStr);
            }

            JSONArray ja = new JSONArray();

            //获取列表总数
            Integer totalRow = takeDeliveryGoodsService.getTakeDeliveryGoodsListByBusinessIdNum(businessId, businessTitle, typeId);

            if(totalRow > 0) {

                List<TakeDeliveryGoodsVm> takeDeliveryGoodsList = takeDeliveryGoodsService.findTakeDeliveryGoodsListByBusinessId(businessId, businessTitle, typeId, beginNum, pageSize);

                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                for (TakeDeliveryGoodsVm t : takeDeliveryGoodsList) {

                    JSONObject obj = new JSONObject();

                    //商品ID
                    obj.put("goodsId", t.getId());
                    //商品标题
                    obj.put("businessTitle", t.getBusinessTitle());
                    //主图
                    obj.put("mainPic", t.getMainPic());
                    //商品价格
                    obj.put("goodsPrice", NumUtils.doubleToScale(t.getGoodsPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //商品分类ID
                    obj.put("typeId", t.getTypeId());
                    //商品分类名称
                    obj.put("typeName", t.getTypeName());
                    //商品详细
                    obj.put("details", t.getDetails());
                    //状态：1-正常、2-禁用、3-删除
                    obj.put("status", t.getStatus());
                    //创建时间
                    obj.put("createDate", sf.format(t.getCreateDate()));

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
     * 新增拿货商品 -305
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/addTakeDeliveryGoods")
    public ResponseMessage addTakeDeliveryGoods(@RequestBody Map<String, String> map) {

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

            //商家等级：1-初级、2-中级、3-高级
            Integer gradeType = b.getGradeType();

            //商家等级：1-初级、2-中级、3-高级
            /*if (gradeType == 1){
                returnMsg.setResult("305011");
                returnMsg.setMessage(BusinessResultCode.result_305011);
                return returnMsg;
            }*/

            //商品标题
            String businessTitle = map.get("businessTitle");
            if(StringUtils.isBlank(businessTitle)){
                returnMsg.setResult("305021");
                returnMsg.setMessage(BusinessResultCode.result_305021);
                return returnMsg;
            }

            //主图
            String mainPic = map.get("mainPic");
            if(StringUtils.isBlank(mainPic)){
                returnMsg.setResult("305031");
                returnMsg.setMessage(BusinessResultCode.result_305031);
                return returnMsg;
            }

            //商品价格
            String goodsPrice = map.get("goodsPrice");
            if(StringUtils.isBlank(goodsPrice)){
                returnMsg.setResult("305041");
                returnMsg.setMessage(BusinessResultCode.result_305041);
                return returnMsg;
            }

            //商品分类ID
            String typeId = map.get("typeId");
            if(StringUtils.isBlank(typeId)){
                returnMsg.setResult("305051");
                returnMsg.setMessage(BusinessResultCode.result_305051);
                return returnMsg;
            }

            //商品详细
            String details = map.get("details");
            if(StringUtils.isBlank(details)){
                returnMsg.setResult("305061");
                returnMsg.setMessage(BusinessResultCode.result_305061);
                return returnMsg;
            }

            Date date = new Date();

            //获取这个月上新数量
            int num = takeDeliveryGoodsService.getNewTakeDeliveryGoodsNum(businessId, date);

            GoodsNumConfig goodsNumConfig = goodsNumConfigService.getGoodsNumConfig();

            if(gradeType == 1 && num >= goodsNumConfig.getPrimaryNum()){
                returnMsg.setResult("305071");
                returnMsg.setMessage(BusinessResultCode.result_305071);
                return returnMsg;
            } else if(gradeType == 2 && num >= goodsNumConfig.getIntermediateNum()){
                returnMsg.setResult("305071");
                returnMsg.setMessage(BusinessResultCode.result_305071);
                return returnMsg;
            }else if(gradeType == 3 && num >= goodsNumConfig.getSeniorNum()){
                returnMsg.setResult("305071");
                returnMsg.setMessage(BusinessResultCode.result_305071);
                return returnMsg;
            }

            TakeDeliveryGoods t = new TakeDeliveryGoods();

            //商家ID
            t.setBusinessId(businessId);
            //商品标题
            t.setBusinessTitle(businessTitle);
            //主图
            t.setMainPic(mainPic);
            //商品价格
            t.setGoodsPrice(Double.parseDouble(goodsPrice));
            //商品分类ID
            t.setTypeId(Long.parseLong(typeId));
            //商品详细
            t.setDetails(details);
            //状态：1-正常、2-禁用、3-删除
            t.setStatus(1);
            //创建时间
            t.setCreateDate(new Date());

            takeDeliveryGoodsService.save(t);

            returnMsg.setResult("1");
            returnMsg.setMessage(BusinessResultCode.result_7);

        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(BusinessResultCode.result_100000);
        }

        return returnMsg;
    }

    /**
     * 修改拿货商品状态 -306
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateTakeDeliveryGoodsStatus")
    public ResponseMessage updateTakeDeliveryGoodsStatus(@RequestBody Map<String, String> map) {

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

            //检测商品ID不能为空
            String goodsIds = map.get("goodsIds");
            if(StringUtils.isBlank(goodsIds)){
                returnMsg.setResult("306010");
                returnMsg.setMessage(BusinessResultCode.result_306010);
                return returnMsg;
            }

            //检测操作类型不能为空：1-正常、2-禁用、3-删除
            String statusStr= map.get("status");
            if(StringUtils.isBlank(statusStr)){
                returnMsg.setResult("306020");
                returnMsg.setMessage(BusinessResultCode.result_306020);
                return returnMsg;
            }

            int status = Integer.parseInt(statusStr);

            //当前时间
            Date date = new Date();

            takeDeliveryGoodsService.updateStatus(goodsIds, status, date);

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
     * 查询返佣商品列表 -307
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryCommissionGoodsList")
    public ResponseMessage queryCommissionGoodsList(@RequestBody Map<String, String> map) {

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

            //商品标题
            String businessTitle = map.get("businessTitle");
            if(StringUtils.isBlank(businessTitle)){
                returnMsg.setResult("302011");
                returnMsg.setMessage(BusinessResultCode.result_302011);
                return returnMsg;
            }

            //商品筛选-所在地
            String itemloc = map.get("itemloc");

            //taobao.tbk.dg.material.optional( 淘宝客-推广者-物料搜索 )
            TaobaoClient client = new DefaultTaobaoClient(HrdhAdminConfig.server_url, HrdhAdminConfig.app_key, HrdhAdminConfig.app_secret);
            TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
            //页大小，默认20，1~100
            req.setPageSize(Long.parseLong(String.valueOf(pageSize)));
            //第几页，默认：１
            req.setPageNo(Long.parseLong(String.valueOf(pageNum)));
            //链接形式：1：PC，2：无线，默认：１
            req.setPlatform(1L);
            //商品筛选-所在地
            req.setItemloc(itemloc) ;
            //商品筛选-查询词
            req.setQ(businessTitle);
            //mm_xxx_xxx_12345678三段式的最后一段数字
            req.setAdzoneId(HrdhAdminConfig.adzone_id);

            TbkDgMaterialOptionalResponse rsp = client.execute(req);

            JSONObject js = JSONObject.fromObject(rsp.getBody());

            JSONArray ja = new JSONArray();
            Integer totalRow = 0;

            if(js.has("tbk_dg_material_optional_response")){

                JSONObject jsResponse = JSONObject.fromObject(js.getString("tbk_dg_material_optional_response"));

                //结果总数
                Integer  total_results = jsResponse.getInt("total_results");
                totalRow = total_results;

                JSONObject jsResultList = JSONObject.fromObject(jsResponse.get("result_list"));
                JSONArray jsMapData = JSONArray.fromObject(jsResultList.get("map_data"));

                for (int x=0; x < jsMapData.size(); x++){

                    JSONObject obj = new JSONObject();

                    JSONObject j = JSONObject.fromObject(jsMapData.get(x));

                    //商品信息-宝贝ID
                    obj.put("itemId", j.getString("item_id"));
                    //商品信息-商品标题
                    obj.put("businessTitle", j.getString("title"));
                    //链接-宝贝地址
                    obj.put("itemUrl", j.getString("item_url"));
                    //商品信息-商品主图
                    obj.put("mainPic", j.getString("pict_url"));
                    //店铺信息-店铺名称
                    obj.put("shopTitle", j.getString("shop_title"));
                    //宝贝推广链接
                    obj.put("promotionLink", "https:" + j.getString("url"));
                    //宝贝+券二合一推广链接
                    obj.put("couponShareUrl", "https:" + j.getString("url"));
                    if (j.has("coupon_share_url")) {
                        obj.put("couponShareUrl", "https:" + j.getString("coupon_share_url"));
                    }

                    //优惠券
                    Double couponAmount = 0.00;
                    if (j.has("coupon_amount")) {
                        couponAmount = j.getDouble("coupon_amount");
                    }
                    obj.put("couponPrice", NumUtils.doubleScale(couponAmount, CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));

                    //折扣价（元）
                    Double zkFinalPrice = j.getDouble("zk_final_price");
                    //券后价价格
                    obj.put("couponAfterPrice", NumUtils.doubleScale(zkFinalPrice - couponAmount, CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //商品信息-佣金比率。1550表示15.5%
                    Double commissionRate = j.getDouble("commission_rate");
                    obj.put("proportion", NumUtils.doubleScale(commissionRate / 100, CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //佣金金额
                    obj.put("commissionPrice", NumUtils.doubleScale((zkFinalPrice - couponAmount) * Double.parseDouble(String.valueOf((commissionRate / 10000))), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));

                    //优惠券信息-优惠券开始时间
                    String activityStartDate = "";
                    if (j.has("coupon_start_time")) {
                        activityStartDate = j.getString("coupon_start_time");
                    }
                    obj.put("activityStartDate", activityStartDate);

                    //优惠券信息-优惠券结束时间
                    String activityEndDate = "";
                    if (j.has("coupon_end_time")) {
                        activityEndDate = j.getString("coupon_end_time");
                    }
                    obj.put("activityEndDate", activityEndDate);

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
            returnMsg.setMessage(BusinessResultCode.result_100000);
        }

        return returnMsg;
    }

    /**
     * 编辑返佣商品 -308
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateCommissionGoods")
    public ResponseMessage updateCommissionGoods(@RequestBody Map<String, String> map) {

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

            //商品ID
            String goodsId = map.get("goodsId");
            if(StringUtils.isBlank(goodsId)){
                returnMsg.setResult("308200");
                returnMsg.setMessage(BusinessResultCode.result_308200);
                return returnMsg;
            }

            //商品标题
            String businessTitle = map.get("businessTitle");
            if(StringUtils.isBlank(businessTitle)){
                returnMsg.setResult("308011");
                returnMsg.setMessage(BusinessResultCode.result_308011);
                return returnMsg;
            }

            //主图
            String mainPic = map.get("mainPic");
            if(StringUtils.isBlank(mainPic)){
                returnMsg.setResult("308021");
                returnMsg.setMessage(BusinessResultCode.result_308021);
                return returnMsg;
            }


            //商品分类ID
            String typeId = map.get("typeId");
            if(StringUtils.isBlank(typeId)){
                returnMsg.setResult("308031");
                returnMsg.setMessage(BusinessResultCode.result_308031);
                return returnMsg;
            }

            //商品详细
            String details = map.get("details");
            if(StringUtils.isBlank(details)){
                returnMsg.setResult("308041");
                returnMsg.setMessage(BusinessResultCode.result_308041);
                return returnMsg;
            }

            //券后价价格
            String couponAfterPrice = map.get("couponAfterPrice");
            if(StringUtils.isBlank(couponAfterPrice)){
                returnMsg.setResult("308051");
                returnMsg.setMessage(BusinessResultCode.result_308051);
                return returnMsg;
            }

            //优惠券金额
            String couponPrice = map.get("couponPrice");
            if(StringUtils.isBlank(couponPrice)){
                returnMsg.setResult("308061");
                returnMsg.setMessage(BusinessResultCode.result_308061);
                return returnMsg;
            }

            //佣金
            String commissionPrice = map.get("commissionPrice");
            if(StringUtils.isBlank(commissionPrice)){
                returnMsg.setResult("308071");
                returnMsg.setMessage(BusinessResultCode.result_308071);
                return returnMsg;
            }

            //比例
            String proportion = map.get("proportion");
            if(StringUtils.isBlank(proportion)){
                returnMsg.setResult("308081");
                returnMsg.setMessage(BusinessResultCode.result_308081);
                return returnMsg;
            }

            //样品剩余数量
            String surplusNum = map.get("surplusNum");
            if(StringUtils.isBlank(surplusNum)){
                returnMsg.setResult("308091");
                returnMsg.setMessage(BusinessResultCode.result_308091);
                return returnMsg;
            }

            //拍摄要求
            String shotRequirement = map.get("shotRequirement");
            if(StringUtils.isBlank(shotRequirement)){
                returnMsg.setResult("308101");
                returnMsg.setMessage(BusinessResultCode.result_308101);
                return returnMsg;
            }

            //规则说明
            String ruleDescription = map.get("ruleDescription");
            if(StringUtils.isBlank(ruleDescription)){
                returnMsg.setResult("308111");
                returnMsg.setMessage(BusinessResultCode.result_308111);
                return returnMsg;
            }

            //取样条件数量
            String conditionNum = map.get("conditionNum");
            if(StringUtils.isBlank(conditionNum)){
                returnMsg.setResult("308121");
                returnMsg.setMessage(BusinessResultCode.result_308121);
                return returnMsg;
            }

            //押金金额
            String depositPric = map.get("depositPric");
            if(StringUtils.isBlank(depositPric)){
                returnMsg.setResult("308131");
                returnMsg.setMessage(BusinessResultCode.result_308131);
                return returnMsg;
            }

            //活动开始时间
            String activityStartDate = map.get("activityStartDate");
            /*if(StringUtils.isBlank(activityStartDate)){
                returnMsg.setResult("308141");
                returnMsg.setMessage(BusinessResultCode.result_308141);
                return returnMsg;
            }*/

            //活动结束时间
            String activityEndDate = map.get("activityEndDate");
           /* if(StringUtils.isBlank(activityEndDate)){
                returnMsg.setResult("308151");
                returnMsg.setMessage(BusinessResultCode.result_308151);
                return returnMsg;
            }*/

            //宝贝ID
            String itemId = map.get("itemId");
            if(StringUtils.isBlank(itemId)){
                returnMsg.setResult("308180");
                returnMsg.setMessage(BusinessResultCode.result_308180);
                return returnMsg;
            }

            //宝贝链接
            String itemUrl = map.get("itemUrl");
            if(StringUtils.isBlank(itemUrl)){
                returnMsg.setResult("308191");
                returnMsg.setMessage(BusinessResultCode.result_308191);
                return returnMsg;
            }

            //推广链接
            String promotionLink = map.get("promotionLink");
            if(StringUtils.isBlank(promotionLink)){
                returnMsg.setResult("308160");
                returnMsg.setMessage(BusinessResultCode.result_308160);
                return returnMsg;
            }

            //宝贝+券二合一推广链接
            String couponShareUrl = map.get("couponShareUrl");

            Date date = new Date();

            CommissionGoods c = commissionGoodsService.get(Long.parseLong(goodsId));

            //判断是否原商品
            if(c.getItemId() != Long.parseLong(itemId)){
                returnMsg.setResult("308211");
                returnMsg.setMessage(BusinessResultCode.result_308211);
                return returnMsg;
            }

            //商品标题
            c.setBusinessTitle(businessTitle);
            //主图
            c.setMainPic(mainPic);
            //商品分类ID
            c.setTypeId(Long.parseLong(typeId));
            //商品详细
            c.setDetails(details);
            //券后价价格
            c.setCouponAfterPrice(Double.parseDouble(couponAfterPrice));
            //优惠券金额
            c.setCouponPrice(Double.parseDouble(couponPrice));
            //佣金
            c.setCommissionPrice(Double.parseDouble(commissionPrice));
            //比例
            c.setProportion((int) (Double.parseDouble(proportion) * 100));
            //样品剩余数量
            c.setSurplusNum(Integer.parseInt(surplusNum));
            //拍摄要求
            c.setShotRequirement(shotRequirement);
            //规则说明
            c.setRuleDescription(ruleDescription);
            //取样条件数量
            c.setConditionNum(Integer.parseInt(conditionNum));
            //押金金额
            c.setDepositPric(Double.parseDouble(depositPric));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //活动开始时间
            c.setActivityStartDate(activityStartDate == "" ? null : sdf.parse(activityStartDate));
            //活动结束时间
            c.setActivityEndDate(activityStartDate == "" ? null : sdf.parse(activityEndDate));
            //推广链接
            c.setPromotionLink(TaobaoApi.getTbkSpreadGetRequest(promotionLink));
            //宝贝地址
            c.setItemUrl(itemUrl);
            //宝贝+券二合一推广链接
            c.setCouponShareUrl(couponShareUrl == null ? couponShareUrl : TaobaoApi.getTbkSpreadGetRequest(couponShareUrl));
            //修改时间
            c.setModifyDate(date);

            commissionGoodsService.save(c);

            returnMsg.setResult("1");
            returnMsg.setMessage(BusinessResultCode.result_4);

        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(BusinessResultCode.result_100000);
        }

        return returnMsg;
    }
}
