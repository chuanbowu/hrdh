package com.flym.hrdh.controller.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.service.common.IGoodsAdvertisementService;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.common.GoodsAdvertisement;
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
 * <p>Description:商品广告接口管理</p>
 * <p>Copyright: Copyright (c) 2020-06-22</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "goodsAdvertisement")
public class GoodsAdvertisementController extends BaseController {

    @Reference(version = "1.0.0")
    protected IGoodsAdvertisementService goodsAdvertisementService;

    /**
     * 商品广告列表 -218
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/goodsAdvertisementList")
    public ResponseMessage goodsAdvertisementList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            //类型：1-返佣、2-拿货
            String typeStr = map.get("type");
            if(StringUtils.isBlank(typeStr)){
                returnMsg.setResult("218010");
                returnMsg.setMessage(ResultCode.result_218010);
                return returnMsg;
            }

            JSONArray ja = new JSONArray();

            List<GoodsAdvertisement> goodsAdvertisementVmList = goodsAdvertisementService.findGoodsAdvertisementVmList(Integer.parseInt(typeStr));

            for(GoodsAdvertisement a : goodsAdvertisementVmList){

                JSONObject obj = new JSONObject();

                //商品ID
                obj.put("goodsId", a.getGoodsId());
                //广告图
                obj.put("advertisementPic", a.getAdvertisementPic());
                //类型：1-返佣商品、2-拿货商品
                obj.put("type", a.getType());

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
