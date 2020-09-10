package com.flym.hrdh.controller.system.business;


import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.business.CommissionGoodsVm;
import com.flym.hrdh.api.model.business.TakeDeliveryGoodsVm;
import com.flym.hrdh.api.service.business.ICommissionGoodsService;
import com.flym.hrdh.api.service.business.ITakeDeliveryGoodsService;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.config.CommonConfig;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
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
 * <p>Copyright: Copyright (c) 2020-05-26</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/system/goods")
public class SysGoodsController extends BaseController {

    @Reference(version = "1.0.0")
    protected IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected ICommissionGoodsService commissionGoodsService;

    @Reference(version = "1.0.0")
    protected ITakeDeliveryGoodsService takeDeliveryGoodsService;

    /**
     * 返佣商品列表 -305
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/commissionGoodsList")
    public ResponseMessage commissionGoodsList(@RequestBody Map<String, String> map) {

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
            int pageSize = CommonConfig.ADMIN_LIST_DEFAULT_PAGE_SIZE;
            String pageSizeStr = map.get("pageSize");
            if (StringUtils.isNotBlank(pageSizeStr)) {
                pageSize = Integer.parseInt(pageSizeStr);
            }

            //开始条数
            int beginNum = pageSize * (pageNum - 1);

            //商家ID
            String businessIdStr = map.get("businessId");
            Long businessId = null;
            if (StringUtils.isNotBlank(businessIdStr)){
                businessId = Long.parseLong(businessIdStr);
            }

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
            Integer totalRow = commissionGoodsService.getCommissionGoodsVmNum(businessId, businessTitle, typeId);

            if(totalRow > 0) {

                List<CommissionGoodsVm> commissionGoodsList = commissionGoodsService.findCommissionGoodsVmList(businessId, businessTitle, typeId, beginNum, pageSize);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                for (CommissionGoodsVm c : commissionGoodsList) {

                    JSONObject obj = new JSONObject();

                    //商品ID
                    obj.put("goodsId", c.getId());
                    //商家ID
                    obj.put("businessId", c.getBusinessId());
                    //商家手机号
                    obj.put("phone", c.getPhone());
                    //商家头像
                    obj.put("headPic", c.getHeadPic());
                    //店铺名称
                    obj.put("shopName", c.getShopName());
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
            returnMsg.setMessage(ResultCode.result_0);

        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(ResultCode.result_100000);
        }

        return returnMsg;
    }

    /**
     * 返佣商品详情 -306
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/commissionGoodsInfo")
    public ResponseMessage commissionGoodsInfo(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(hrdhCacheService,map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            //检测商品ID不能为空
            String goodsId = map.get("goodsId");
            if(StringUtils.isBlank(goodsId)){
                returnMsg.setResult("306010");
                returnMsg.setMessage(ResultCode.result_306010);
                return returnMsg;
            }

            JSONObject jb = new JSONObject();

            CommissionGoodsVm c = commissionGoodsService.getCommissionGoodsVm(Long.parseLong(goodsId));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            //商品ID
            jb.put("goodsId", c.getId());
            //商家ID
            jb.put("businessId", c.getBusinessId());
            //商家手机号
            jb.put("phone", c.getPhone());
            //商家头像
            jb.put("headPic", c.getHeadPic());
            //店铺名称
            jb.put("shopName", c.getShopName());
            //商品标题
            jb.put("businessTitle", c.getBusinessTitle());
            //主图
            jb.put("mainPic", c.getMainPic());
            //商品详细
            jb.put("details", c.getDetails());
            //商品分类ID
            jb.put("typeId", c.getTypeId());
            //商品分类名称
            jb.put("typeName", c.getTypeName());
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
            jb.put("activityStartDate", sdf.format(c.getActivityStartDate()));
            //活动结束时间
            jb.put("activityEndDate", sdf.format(c.getActivityEndDate()));
            //拍摄要求
            jb.put("shotRequirement", c.getShotRequirement());
            //规则说明
            jb.put("ruleDescription", c.getRuleDescription());
            //宝贝链接
            jb.put("itemUrl", c.getItemUrl());
            //推广链接
            jb.put("promotionLink", c.getPromotionLink());
            //宝贝+券二合一推广链接
            jb.put("couponShareUrl", c.getCouponShareUrl() == null ? "" : c.getCouponShareUrl());
            //状态：1-正常、2-禁用、3-删除
            jb.put("status", c.getStatus());
            //创建时间
            jb.put("createDate", sf.format(c.getCreateDate()));

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
     * 拿货商品列表 -307
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/takeDeliveryGoodsList")
    public ResponseMessage takeDeliveryGoodsList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(hrdhCacheService, map);

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

            //商家ID
            String businessIdStr = map.get("businessId");
            Long businessId = null;
            if (StringUtils.isNotBlank(businessIdStr)){
                businessId = Long.parseLong(businessIdStr);
            }

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
            Integer totalRow = takeDeliveryGoodsService.getTakeDeliveryGoodsVmNum(businessId, businessTitle, typeId);

            if(totalRow > 0) {

                List<TakeDeliveryGoodsVm> takeDeliveryGoodsList = takeDeliveryGoodsService.findTakeDeliveryGoodsVmList(businessId, businessTitle, typeId, beginNum, pageSize);

                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                for (TakeDeliveryGoodsVm t : takeDeliveryGoodsList) {

                    JSONObject obj = new JSONObject();

                    //商品ID
                    obj.put("goodsId", t.getId());
                    //商家ID
                    obj.put("businessId", t.getBusinessId());
                    //商家手机号
                    obj.put("phone", t.getPhone());
                    //商家头像
                    obj.put("headPic", t.getHeadPic());
                    //店铺名称
                    obj.put("shopName", t.getShopName());
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
     * 拿货商品详情 -308
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/takeDeliveryGoodsInfo")
    public ResponseMessage takeDeliveryGoodsInfo(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(hrdhCacheService, map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            //检测商品ID不能为空
            String goodsId = map.get("goodsId");
            if(StringUtils.isBlank(goodsId)){
                returnMsg.setResult("308010");
                returnMsg.setMessage(ResultCode.result_308010);
                return returnMsg;
            }

            JSONObject jb = new JSONObject();

            TakeDeliveryGoodsVm t = takeDeliveryGoodsService.getTakeDeliveryGoodsVm(Long.parseLong(goodsId));

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            //商品ID
            jb.put("goodsId", t.getId());
            //商家ID
            jb.put("businessId", t.getBusinessId());
            //商家手机号
            jb.put("phone", t.getPhone());
            //商家头像
            jb.put("headPic", t.getHeadPic());
            //店铺名称
            jb.put("shopName", t.getShopName());
            //商品标题
            jb.put("businessTitle", t.getBusinessTitle());
            //主图
            jb.put("mainPic", t.getMainPic());
            //商品价格
            jb.put("goodsPrice", NumUtils.doubleToScale(t.getGoodsPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
            //商品分类ID
            jb.put("typeId", t.getTypeId());
            //商品分类名称
            jb.put("typeName", t.getTypeName());
            //商品详细
            jb.put("details", t.getDetails());
            //状态：1-正常、2-禁用、3-删除
            jb.put("status", t.getStatus());
            //创建时间
            jb.put("createDate", sf.format(t.getCreateDate()));

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
