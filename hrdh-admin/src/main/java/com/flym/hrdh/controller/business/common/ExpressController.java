package com.flym.hrdh.controller.business.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.service.common.IExpressService;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.config.BusinessResultCode;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.common.Express;
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
 * <p>Description:快递公司管理</p>
 * <p>Copyright: Copyright (c) 2020-06-07</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/business/express")
public class ExpressController extends BaseController {

    @Reference(version = "1.0.0")
    protected IExpressService expressService;


    @Reference(version = "1.0.0")
    protected IHrdhCacheService hrdhCacheService;

    /**
     * 快递公司列表 -203
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/expressList")
    public ResponseMessage expressList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.businessCheckNotLogin(hrdhCacheService, map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            JSONArray ja = new JSONArray();

            List<Express> expressList = expressService.findExpressList();

            for(Express e : expressList){

                JSONObject obj = new JSONObject();

                //ID
                obj.put("expressId", e.getId());
                //快递名称
                obj.put("expressName", e.getExpressName());

                ja.add(obj);
            }

            JSONObject jb = new JSONObject();
            jb.put("list", ja);
            returnMsg.setDatas(jb);
            returnMsg.setResult("0");
            returnMsg.setMessage(BusinessResultCode.result_0);

        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(BusinessResultCode.result_100000);
        }

        return returnMsg;
    }


}
