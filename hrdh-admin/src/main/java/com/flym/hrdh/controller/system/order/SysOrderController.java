package com.flym.hrdh.controller.system.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.order.OrderVm;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.api.service.order.IOrderService;
import com.flym.hrdh.config.BusinessResultCode;
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
 * <p>Description:取样管理</p>
 * <p>Copyright: Copyright (c) 2020-06-08</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/system/order")
public class SysOrderController extends BaseController {

    @Reference(version = "1.0.0")
    protected IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected IOrderService orderService;

    /**
     * 取样列表 -501
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/orderList")
    public ResponseMessage orderList(@RequestBody Map<String, String> map) {

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
            Integer totalRow = orderService.getOrderVmNum(status, businessTitle);

            if (totalRow > 0) {

                List<OrderVm> orderVmList = orderService.findOrderVmList(status, businessTitle, beginNum, pageSize);

                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                for (OrderVm orderVm : orderVmList) {

                    JSONObject obj = new JSONObject();

                    //ID
                    obj.put("orderId", orderVm.getId());
                    //商家手机号
                    obj.put("businessPhone", orderVm.getBusinessPhone());
                    //商家昵称
                    obj.put("businessNickName", orderVm.getBusinessNickName());
                    //商家头像
                    obj.put("businessHeadPic", orderVm.getBusinessHeadPic());
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

}
