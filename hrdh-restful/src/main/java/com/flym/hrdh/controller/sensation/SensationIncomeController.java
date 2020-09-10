package com.flym.hrdh.controller.sensation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.sensation.SensationIncomeVm;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.api.service.sensation.ISensationIncomeService;
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

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:红人收入明细接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-30</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "sensationIncome")
public class SensationIncomeController extends BaseController {

    @Reference(version = "1.0.0")
    protected IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected ISensationIncomeService sensationIncomeService;

    /**
     * 红人收入明细列表 -116
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/sensationIncomeList")
    public ResponseMessage sensationIncomeList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkLogin(hrdhCacheService,map);
            Long sensationId ;
            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }else{
                sensationId = Long.parseLong(returnMsg.getDatas().toString());
                returnMsg.setDatas(null);
            }

            //当前页码
            int pageNum = 1;
            String pageNumStr = map.get("pageNum");
            if (StringUtils.isNotBlank(pageNumStr)) {
                pageNum = Integer.parseInt(pageNumStr);
            }

            //每页显示数
            int pageSize = CommonConfig.RESTFUL_LIST_DEFAULT_PAGE_SIZE;
            String pageSizeStr = map.get("pageSize");
            if (StringUtils.isNotBlank(pageSizeStr)) {
                pageSize = Integer.parseInt(pageSizeStr);
            }

            //开始条数
            int beginNum = pageSize * (pageNum - 1);

            JSONArray ja = new JSONArray();

            //获取列表总数
            Integer totalRow = sensationIncomeService.getSensationIncomeVmNumBySensationId(sensationId);

            if (totalRow > 0) {

                List<SensationIncomeVm> sensationIncomeVmList = sensationIncomeService.findSensationIncomeVmListBySensationId(sensationId, beginNum, pageSize);

                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                for (SensationIncomeVm s : sensationIncomeVmList) {

                    JSONObject obj = new JSONObject();

                    //商品标题
                    obj.put("businessTitle", s.getBusinessTitle());
                    //佣金
                    obj.put("commissionPrice", NumUtils.doubleToScale(s.getCommissionPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //类型：1-预估收入、2-已入账、3-违规处罚
                    obj.put("type", s.getType());
                    //收入时间
                    obj.put("incomeDate", sf.format(s.getIncomeDate()));

                    ja.add(obj);
                }
            }

            JSONObject jb = new JSONObject();
            jb.put("list", ja);
            jb.put("totalRow", totalRow);
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
