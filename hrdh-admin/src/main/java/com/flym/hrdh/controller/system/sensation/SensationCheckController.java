package com.flym.hrdh.controller.system.sensation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.sensation.SensationCheckVm;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.api.service.sensation.ISensationCheckService;
import com.flym.hrdh.api.service.sensation.ISensationService;
import com.flym.hrdh.config.CommonConfig;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.sensation.Sensation;
import com.flym.hrdh.pojo.sensation.SensationCheck;
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
 * <p>Description:红人相关接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-26</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/system/sensationCheck")
public class SensationCheckController extends BaseController {

    @Reference(version = "1.0.0")
    protected IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected ISensationService sensationService;

    @Reference(version = "1.0.0")
    protected ISensationCheckService sensationCheckService;

    /**
     * 红人认证审核列表 -406
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/sensationCheckList")
    public ResponseMessage sensationCheckList(@RequestBody Map<String, String> map) {

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

            //审核状态：1-审核中、2-审核拒绝、3-审核通过
            String checkStatusStr = map.get("checkStatus");
            Integer checkStatus = null;
            if(StringUtils.isNotBlank(checkStatusStr)){
                checkStatus = Integer.parseInt(checkStatusStr);
            }

            JSONArray ja = new JSONArray();

            //获取列表总数
            Integer totalRow = sensationCheckService.getSensationCheckVmNum(sensationId, checkStatus);

            if (totalRow > 0) {

                List<SensationCheckVm> sensationCheckVmList = sensationCheckService.findSensationCheckVmList(sensationId, checkStatus, beginNum, pageSize);

                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                for (SensationCheckVm s : sensationCheckVmList) {

                    JSONObject obj = new JSONObject();

                    //ID
                    obj.put("sensationCheckId", s.getId());
                    //红人ID
                    obj.put("sensationId", s.getSensationId());
                    //手机号码
                    obj.put("phone", s.getPhone());
                    //昵称
                    obj.put("nickName", s.getNickName());
                    //头像
                    obj.put("headPic", s.getHeadPic());
                    //推广类目：1-抖音、2-快手、3-小红书、4-淘宝、5-今日头条、6-微博、7-B站
                    obj.put("extensionStatus", s.getExtensionStatus());
                    //认证链接
                    obj.put("authenticationLink", s.getAuthenticationLink());
                    //认证图片
                    obj.put("authenticationPic", s.getAuthenticationPic());
                    //审核状态：1-审核中、2-审核拒绝、3-审核通过
                    obj.put("checkStatus", s.getCheckStatus());
                    //内容分类：1-搞笑娱乐、2-萌宠动漫、3-情感励志、4-教育知识、5-母婴玩具、6-颜值才艺、7-健康养生、8-游戏电竞、9-汽车周边、10-科技生活、11-新闻媒体、12-美妆护肤、13-旅游攻略、14-美食攻略、15-教程技术、16-综合种草、17-服装穿搭、18-家居百货、19-剧情故事、20-音乐、21-时尚、22-创意
                    obj.put("contentType", s.getContentType());
                    //粉丝分布：1-男女平衡、2-男粉较多、3-女粉较多
                    obj.put("followersSpreadType", s.getFollowersSpreadType());
                    //推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成
                    obj.put("extensionType", s.getExtensionType());
                    //粉丝数量
                    obj.put("followersNum", s.getFollowersNum());
                    //获赞总量
                    obj.put("totalLikedNum", s.getTotalLikedNum());
                    //单条最高获赞
                    obj.put("singleHighestNum", s.getSingleHighestNum());
                    //红人概况（文字版）
                    obj.put("sensationSurvey", s.getSensationSurvey());
                    //创建时间
                    obj.put("createDate", sf.format(s.getCreateDate()));
                    //审核人名称
                    obj.put("checkSysUserName", s.getCheckSysUserName() == null ? "" : s.getCheckSysUserName());
                    //拒绝理由
                    obj.put("refuseContent", s.getRefuseContent() == null ? "" : s.getRefuseContent());
                    //审核时间
                    obj.put("checkDate", s.getCheckDate() == null ? "" : sf.format(s.getCheckDate()));

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
     * 认证审核 -407
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateSensationCheck")
    public ResponseMessage updateSensationCheck(@RequestBody Map<String, String> map) {

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

            //认证ID
            String sensationCheckId = map.get("sensationCheckId");
            if(StringUtils.isBlank(sensationCheckId)){
                returnMsg.setResult("407010");
                returnMsg.setMessage(ResultCode.result_407010);
                return returnMsg;
            }

            //审核状态：1-审核拒绝、2-审核通过
            String checkStatusStr = map.get("checkStatus");
            if(StringUtils.isBlank(checkStatusStr)){
                returnMsg.setResult("407021");
                returnMsg.setMessage(ResultCode.result_407021);
                return returnMsg;
            }

            Integer checkStatus = Integer.parseInt(checkStatusStr);

            //拒绝理由
            String refuseContent = map.get("refuseContent");

            if (checkStatus == 1){

                if(StringUtils.isBlank(refuseContent)){
                    returnMsg.setResult("407031");
                    returnMsg.setMessage(ResultCode.result_407031);
                    return returnMsg;
                }
            }

            SensationCheck sensationCheck = sensationCheckService.get(Long.parseLong(sensationCheckId));

            //审核状态：1-审核中、2-审核拒绝、3-审核通过
            if(sensationCheck.getCheckStatus() != 1){
                returnMsg.setResult("407041");
                returnMsg.setMessage(ResultCode.result_407041);
                return returnMsg;
            }

            Sensation sensation = sensationService.get(sensationCheck.getSensationId());


            if (checkStatus == 1){
                //拒绝理由
                sensationCheck.setRefuseContent(refuseContent);
            }else {

                //推广类目：1-抖音、2-快手、3-小红书、4-淘宝、5-今日头条、6-微博、7-B站
                sensation.setExtensionStatus(sensationCheck.getExtensionStatus());
                //认证链接
                sensation.setAuthenticationLink(sensationCheck.getAuthenticationLink());
                //认证图片
                sensation.setAuthenticationPic(sensationCheck.getAuthenticationPic());
                //内容分类：1-搞笑娱乐、2-萌宠动漫、3-情感励志、4-教育知识、5-母婴玩具、6-颜值才艺、7-健康养生、8-游戏电竞、9-汽车周边、10-科技生活、11-新闻媒体、12-美妆护肤、13-旅游攻略、14-美食攻略、15-教程技术、16-综合种草、17-服装穿搭、18-家居百货、19-剧情故事、20-音乐、21-时尚、22-创意
                sensation.setContentType(sensationCheck.getContentType());
                //粉丝分布：1-男女平衡、2-男粉较多、3-女粉较多
                sensation.setFollowersSpreadType(sensationCheck.getFollowersSpreadType());
                //推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成
                sensation.setExtensionType(sensationCheck.getExtensionType());
                //粉丝数量
                sensation.setFollowersNum(sensationCheck.getFollowersNum());
                //获赞总量
                sensation.setTotalLikedNum(sensationCheck.getTotalLikedNum());
                //单条最高获赞
                sensation.setSingleHighestNum(sensationCheck.getSingleHighestNum());
                //红人概况（文字版）
                sensation.setSensationSurvey(sensationCheck.getSensationSurvey());
            }

            //审核人
            sensationCheck.setCheckSysUser(userId);
            //审核时间
            Date date = new Date();
            sensationCheck.setCheckDate(date);
            //审核状态：1-审核中、2-审核拒绝、3-审核通过
            sensationCheck.setCheckStatus(checkStatus == 1 ? 2 : 3);

            sensationCheckService.save(sensationCheck);

            //修改人
            sensation.setModifySysUser(userId);
            //修改时间
            sensation.setModifyDate(date);
            //认证状态：1-未完善个人资料、2-未认证、3-认证中、4-认证拒绝、5-已认证
            sensation.setStatus(checkStatus == 1 ? 4 : 5);

            sensationService.save(sensation);

            returnMsg.setResult("1");
            returnMsg.setMessage(ResultCode.result_10);

        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(ResultCode.result_100000);
        }

        return returnMsg;
    }

}
