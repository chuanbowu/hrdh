package com.flym.hrdh.controller.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.common.GoodsTypeConfigVm;
import com.flym.hrdh.api.service.common.IGoodsTypeConfigService;
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
 * <p>Description:商品分类配置管理</p>
 * <p>Copyright: Copyright (c) 2020-07-21</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "goodsTypeConfig")
public class GoodsTypeConfigController extends BaseController {

    @Reference(version = "1.0.0")
    protected IGoodsTypeConfigService goodsTypeConfigService;

    /**
     * 商品分类配置 -219
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/goodsTypeConfigList")
    public ResponseMessage goodsTypeConfigList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            JSONArray ja = new JSONArray();

            List<GoodsTypeConfigVm> finGoodsNumConfigList = goodsTypeConfigService.finGoodsNumConfigList();

            for(GoodsTypeConfigVm g : finGoodsNumConfigList){

                JSONObject obj = new JSONObject();

                //ID
                obj.put("goodsTypeConfigId", g.getId());
                //分类名称
                obj.put("typeName", g.getTypeName());

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
