package com.flym.hrdh.controller.system.sensation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.sensation.SensationAccountInfoVm;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.api.service.sensation.ISensationAccountInfoService;
import com.flym.hrdh.config.CommonConfig;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.utils.NumUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:首页统计管理</p>
 * <p>Copyright: Copyright (c) 2020-06-14</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/system/statistics")
public class SensationAccountInfoController extends BaseController {

    @Reference(version = "1.0.0")
    protected IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected ISensationAccountInfoService sensationAccountInfoService;

    /**
     * 首页统计 -232
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/statisticsInfo")
    public ResponseMessage statisticsInfo(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(hrdhCacheService, map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            Date date = new Date();

            JSONObject jb = new JSONObject();

            SensationAccountInfoVm s = sensationAccountInfoService.getStatistics(date);

            //今日流水数量
            jb.put("dayPrice", NumUtils.doubleToScale(s.getDayPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
            //今日预估流水数量
            jb.put("dayEstimatedPrice", NumUtils.doubleToScale(s.getDayEstimatedPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
            //本月流水数量
            jb.put("monthPrice", NumUtils.doubleToScale(s.getMonthPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
            //流水预估流水数量
            jb.put("monthEstimatedPrice", NumUtils.doubleToScale(s.getMonthEstimatedPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
            //总流水数量
            jb.put("wholePrice", NumUtils.doubleToScale(s.getWholePrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
            //总预估流水数量
            jb.put("wholeEstimatedPrice", NumUtils.doubleToScale(s.getWholeEstimatedPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));

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
