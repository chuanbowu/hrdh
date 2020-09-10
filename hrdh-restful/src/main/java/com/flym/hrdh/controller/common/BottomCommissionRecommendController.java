package com.flym.hrdh.controller.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.common.BottomCommissionRecommendVm;
import com.flym.hrdh.api.service.common.IBottomCommissionRecommendService;
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

import java.util.List;
import java.util.Map;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:底部返佣推荐商口接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-13</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "bottomCommissionRecommend")
public class BottomCommissionRecommendController extends BaseController {

    @Reference(version = "1.0.0")
    protected IBottomCommissionRecommendService bottomCommissionRecommendService;

    /**
     * 底部返佣推荐商品列表 -214
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/bottomCommissionRecommendList")
    public ResponseMessage bottomCommissionRecommendList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            JSONArray ja = new JSONArray();

            List<BottomCommissionRecommendVm> bottomCommissionRecommendVmList = bottomCommissionRecommendService.findBottomCommissionRecommendVmList();

            for(BottomCommissionRecommendVm b : bottomCommissionRecommendVmList){

                JSONObject obj = new JSONObject();

                //商品ID
                obj.put("goodsId", b.getGoodsId());
                //商品标题
                obj.put("businessTitle", b.getBusinessTitle());
                //主图
                obj.put("mainPic", b.getMainPic());
                //券后价价格
                obj.put("couponAfterPrice", NumUtils.doubleToScale(b.getCouponAfterPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                //优惠券金额
                obj.put("couponPrice", NumUtils.doubleToScale(b.getCouponPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                //佣金
                obj.put("commissionPrice", NumUtils.doubleToScale(b.getCommissionPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                //比例
                obj.put("proportion", NumUtils.doubleToScale(Double.parseDouble(b.getProportion().toString()) / 100, CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));


                ja.add(obj);
            }

            JSONObject jb = new JSONObject();
            jb.put("list", ja);
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
