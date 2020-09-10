package com.flym.hrdh.controller.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.service.common.ISamplingPropagandaService;

import com.flym.hrdh.config.*;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.common.SamplingPropaganda;

import com.flym.hrdh.utils.IDUtils;
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
 * <p>Description:领样宣传接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-11</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "samplingPropaganda")
public class SamplingPropagandaController extends BaseController {

    @Reference(version = "1.0.0")
    protected ISamplingPropagandaService samplingPropagandaService;

    /**
     * 领样宣传列表 -203
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/samplingPropagandaList")
    public ResponseMessage samplingPropagandaList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            JSONArray ja = new JSONArray();

            List<SamplingPropaganda> samplingPropagandaList = samplingPropagandaService.findSamplingPropagandaRandList(HrdhRestfulConfig.sampling_propaganda_num);

            for(SamplingPropaganda s : samplingPropagandaList){

                JSONObject obj = new JSONObject();

                String code = IDUtils.getRandomCode(4);

                //领样内容
                obj.put("content", s.getContent().replace("####", code));

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
