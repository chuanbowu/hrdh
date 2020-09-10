package com.flym.hrdh.controller.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.service.common.IAdvertService;
import com.flym.hrdh.api.service.common.IProblemService;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.common.Advert;
import com.flym.hrdh.pojo.common.Problem;
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
 * <p>Description:常见问题配置接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-20</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "problem")
public class ProblemController extends BaseController {

    @Reference(version = "1.0.0")
    protected IProblemService problemService;

    /**
     * 常见问题配置列表 -216
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/problemList")
    public ResponseMessage problemList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            JSONArray ja = new JSONArray();

            List<Problem> problemList = problemService.findProblemList();

            for(Problem p : problemList){

                JSONObject obj = new JSONObject();

                //问内容
                obj.put("askContent", p.getAskContent());
                //答内容
                obj.put("answerContent", p.getAnswerContent());

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
