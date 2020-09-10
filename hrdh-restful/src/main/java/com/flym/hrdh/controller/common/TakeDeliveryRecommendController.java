package com.flym.hrdh.controller.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.common.TakeDeliveryRecommendVm;
import com.flym.hrdh.api.service.common.ITakeDeliveryRecommendService;
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
 * <p>Description:拿货推荐商口接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-13</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "takeDeliveryRecommend")
public class TakeDeliveryRecommendController extends BaseController {

    @Reference(version = "1.0.0")
    protected ITakeDeliveryRecommendService takeDeliveryRecommendService;

    /**
     * 拿货推荐商品列表 -215
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/takeDeliveryRecommendList")
    public ResponseMessage takeDeliveryRecommendList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            //商品分类ID
            String typeIdStr = map.get("typeId");
            Long typeId = null;
            if(StringUtils.isNotBlank(typeIdStr)){
                typeId = Long.parseLong(typeIdStr);
            }

            JSONArray ja = new JSONArray();

            List<TakeDeliveryRecommendVm> takeDeliveryRecommendVmList = takeDeliveryRecommendService.findTakeDeliveryRecommendVmList(typeId);

            for(TakeDeliveryRecommendVm t : takeDeliveryRecommendVmList){

                JSONObject obj = new JSONObject();

                //商品ID
                obj.put("goodsId", t.getGoodsId());
                //商品标题
                obj.put("businessTitle", t.getBusinessTitle());
                //主图
                obj.put("mainPic", t.getMainPic());
                //商品价格
                obj.put("goodsPrice", NumUtils.doubleToScale(t.getGoodsPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));

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
