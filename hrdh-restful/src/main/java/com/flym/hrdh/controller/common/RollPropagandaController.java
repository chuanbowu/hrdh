package com.flym.hrdh.controller.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.service.common.IRollPropagandaService;
import com.flym.hrdh.config.HrdhRestfulConfig;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.common.RollPropaganda;
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
 * <p>Description:滚动宣传接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-11</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "rollPropaganda")
public class RollPropagandaController extends BaseController {

    @Reference(version = "1.0.0")
    protected IRollPropagandaService rollPropagandaService;

    /**
     * 滚动宣传列表 -204
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/rollPropagandaList")
    public ResponseMessage rollPropagandaList(@RequestBody Map<String, String> map) {

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
                returnMsg.setResult("204010");
                returnMsg.setMessage(ResultCode.result_204010);
                return returnMsg;
            }

            Integer type = Integer.parseInt(typeStr);

            String content = "";

            if(type == 1){
                content = "返佣";
            }else if(type == 2){
                content = "拿货";
            }

            JSONArray ja = new JSONArray();

            List<RollPropaganda> rollPropagandaList = rollPropagandaService.findRollPropagandaRandList(HrdhRestfulConfig.sampling_propaganda_num);

            for(RollPropaganda r : rollPropagandaList){

                JSONObject obj = new JSONObject();

                String code = IDUtils.getRandomCode(4);

                //滚动内容
                obj.put("content", r.getContent().replace("####", code).replace("++", content));

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
