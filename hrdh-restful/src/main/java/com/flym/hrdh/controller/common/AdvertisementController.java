package com.flym.hrdh.controller.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.common.AdvertisementVm;
import com.flym.hrdh.api.service.common.IAdvertisementService;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
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
 * <p>Description:广告接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-12</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "advertisement")
public class AdvertisementController extends BaseController {

    @Reference(version = "1.0.0")
    protected IAdvertisementService advertisementService;

    /**
     * 广告列表 -208
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/advertisementList")
    public ResponseMessage advertisementList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            JSONArray ja = new JSONArray();

            List<AdvertisementVm> advertisementVmList = advertisementService.findAdvertisementVmList();

            for(AdvertisementVm a : advertisementVmList){

                JSONObject obj = new JSONObject();

                //商品ID
                obj.put("goodsId", a.getGoodsId());
                //商品标题
                obj.put("businessTitle", a.getBusinessTitle());
                //商品主图
                obj.put("mainPic", a.getMainPic());
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
