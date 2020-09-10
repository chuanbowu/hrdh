package com.flym.hrdh.controller.system.sensation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.sensation.SensationWithdrawInfoVm;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.api.service.sensation.ISensationWithdrawInfoService;
import com.flym.hrdh.config.CommonConfig;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.sensation.Sensation;
import com.flym.hrdh.pojo.sensation.SensationCheck;
import com.flym.hrdh.pojo.sensation.SensationWithdrawInfo;
import com.flym.hrdh.utils.NumUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:红人提现明细接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-30</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/system/sensationWithdrawInfo")
public class SensationWithdrawInfoController extends BaseController {

    @Reference(version = "1.0.0")
    protected IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected ISensationWithdrawInfoService sensationWithdrawInfoService;

    /**
     * 红人提现明细列表 -408
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/sensationWithdrawInfoList")
    public ResponseMessage sensationWithdrawInfoList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(hrdhCacheService, map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
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

            //红人ID
            String sensationIdStr = map.get("sensationId");
            Long sensationId = null;
            if(StringUtils.isNotBlank(sensationIdStr)){
                sensationId = Long.parseLong(sensationIdStr);
            }

            //状态：1-提现中、2-已提现、3-提现失败
            String statusStr = map.get("status");

            Integer status = null;
            if(StringUtils.isNotBlank(statusStr)){
                status = Integer.parseInt(statusStr);
            }

            JSONArray ja = new JSONArray();

            //获取列表总数
            Integer totalRow = sensationWithdrawInfoService.getSensationWithdrawInfoVmNum(sensationId, status);

            if (totalRow > 0) {

                List<SensationWithdrawInfoVm> sensationWithdrawInfoVmList = sensationWithdrawInfoService.findSensationWithdrawInfoVmList(sensationId, status, beginNum, pageSize);

                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                for (SensationWithdrawInfoVm s : sensationWithdrawInfoVmList) {

                    JSONObject obj = new JSONObject();

                    //ID
                    obj.put("sensationWithdrawInfoId", s.getId());
                    //红人ID
                    obj.put("sensationId", s.getSensationId());
                    //手机号码
                    obj.put("phone", s.getPhone());
                    //昵称
                    obj.put("nickName", s.getNickName());
                    //头像
                    obj.put("headPic", s.getHeadPic());
                    //余额
                    obj.put("moneyPrice", NumUtils.doubleToScale(s.getMoneyPrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //状态：1-提现中、2-已提现、3-提现失败
                    obj.put("status", s.getStatus());
                    //提现时间
                    obj.put("withdrawDate", sf.format(s.getWithdrawDate()));
                    //审核人名称
                    obj.put("checkSysUserName", s.getCheckSysUserName() == null ? "" : s.getCheckSysUserName());
                    //拒绝理由
                    obj.put("refuseContent", s.getRefuseContent() == null ? "" : s.getRefuseContent());
                    //审核时间
                    obj.put("checkDate", s.getCheckDate() == null ? "" : sf.format(s.getCheckDate()));
                    //账号
                    obj.put("account", s.getAccount());
                    //真实姓名
                    obj.put("trueName", s.getTrueName());

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

    /**
     * 提现审核 -410
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkWithdrawInfo")
    public ResponseMessage checkWithdrawInfo(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkLogin(hrdhCacheService, map);

            //如果result为空则直接返回
            Long userId ;
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            } else {
                userId = returnMsg.getDatas().getLong("userId");
                returnMsg.setDatas(null);
            }

            //提现ID
            String sensationWithdrawInfoId = map.get("sensationWithdrawInfoId");
            if(StringUtils.isBlank(sensationWithdrawInfoId)){
                returnMsg.setResult("410010");
                returnMsg.setMessage(ResultCode.result_410010);
                return returnMsg;
            }

            SensationWithdrawInfo sensationWithdrawInfo = sensationWithdrawInfoService.get(Long.parseLong(sensationWithdrawInfoId));

            if(sensationWithdrawInfo.getStatus() != 1){
                returnMsg.setResult("410021");
                returnMsg.setMessage(ResultCode.result_410021);
                return returnMsg;
            }

            //状态：1-提现中、2-已提现、3-提现失败
            sensationWithdrawInfo.setStatus(2);
            //审核人
            sensationWithdrawInfo.setCheckSysUser(userId);
            //审核时间
            sensationWithdrawInfo.setCheckDate(new Date());

            sensationWithdrawInfoService.save(sensationWithdrawInfo);

            returnMsg.setResult("1");
            returnMsg.setMessage(ResultCode.result_12);

        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(ResultCode.result_100000);
        }

        return returnMsg;
    }
}
