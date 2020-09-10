package com.flym.hrdh.controller.sensation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.sensation.SensationVm;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.api.service.sensation.ISensationCheckService;
import com.flym.hrdh.api.service.sensation.ISensationService;
import com.flym.hrdh.config.CacheKeyConfig;
import com.flym.hrdh.config.CommonConfig;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.business.TakeDeliveryGoods;
import com.flym.hrdh.pojo.sensation.Sensation;
import com.flym.hrdh.pojo.sensation.SensationCheck;
import com.flym.hrdh.utils.MD5Util;
import com.flym.hrdh.utils.NumUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:红人相关接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-06</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "sensation")
public class SensationController extends BaseController {

    @Reference(version = "1.0.0")
    protected IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected ISensationService sensationService;

    @Reference(version = "1.0.0")
    protected ISensationCheckService sensationCheckService;

    /**
     * 找回密码 -105
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updatePassword")
    public ResponseMessage updatePassword(@RequestBody Map<String, String> map){

        ResponseMessage respMsg = null;
        try {

            respMsg = super.checkNotLogin(map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(respMsg.result)) {
                return respMsg;
            }

            //检测手机号码不能为空
            String phone = map.get("phone");
            if(StringUtils.isBlank(phone)){
                respMsg.setResult("105011");
                respMsg.setMessage(ResultCode.result_105011);
                return respMsg;
            }

            //检测验证码不能为空
            String code = map.get("code");
            if(StringUtils.isBlank(code)){
                respMsg.setResult("105021");
                respMsg.setMessage(ResultCode.result_105021);
                return respMsg;
            }

            //检测密码不能为空
            String password = map.get("password");
            if(StringUtils.isBlank(password)){
                respMsg.setResult("105031");
                respMsg.setMessage(ResultCode.result_105031);
                return respMsg;
            }

            //检测确认密码不能为空
            String confirmPassword = map.get("confirmPassword");
            if(StringUtils.isBlank(confirmPassword)){
                respMsg.setResult("105041");
                respMsg.setMessage(ResultCode.result_105041);
                return respMsg;
            }

            //检测新密码和确认密码是否一至
            if(!password.equals(confirmPassword)){
                respMsg.setResult("105051");
                respMsg.setMessage(ResultCode.result_105051);
                return respMsg;
            }

            //校验验证码
            String redisCodeKey = 1 + CacheKeyConfig.REDIS_SMS_CODE + phone;

            String redisCode = hrdhCacheService.getCache(redisCodeKey);
            if (!code.equalsIgnoreCase(redisCode)) {
                respMsg.setResult("105061");
                respMsg.setMessage(ResultCode.result_105061);
                return respMsg;
            }

            //根据账号和密码获取会员信息
            Sensation sensation = sensationService.findSensationByPhoneNoPassword(phone, null);

            if(sensation == null){
                respMsg.setResult("105071");
                respMsg.setMessage(ResultCode.result_105071);
                return respMsg;
            }

            //密码
            sensation.setPassword(MD5Util.encoder(password));
            //修改时间
            sensation.setModifyDate(new Date());

            sensationService.save(sensation);

            //获取token
            String oldToken =  hrdhCacheService.getCache(sensation.getId() + CacheKeyConfig.REDIS_MEMBER_LOGIN_TOKEN);
            //检查旧token是否存在
            if(StringUtils.isNotBlank(oldToken)){
                // 删除token
                hrdhCacheService.delCache(oldToken);
            }

            hrdhCacheService.delCache(sensation.getId() + CacheKeyConfig.REDIS_MEMBER_LOGIN_TOKEN);

            respMsg.setResult("1");
            respMsg.setMessage(ResultCode.result_1);
        }catch (Exception e){
            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(ResultCode.result_100000);
        }
        return respMsg;
    }

    /**
     * 获取红人资料 -106
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/sensationInfo")
    public ResponseMessage sensationInfo(@RequestBody Map<String, String> map){

        ResponseMessage respMsg = null;
        try {

            respMsg = super.checkLogin(hrdhCacheService,map);
            Long sensationId ;
            //如果result为空则直接返回
            if (StringUtils.isNotBlank(respMsg.result)) {
                return respMsg;
            }else{
                sensationId = Long.parseLong(respMsg.getDatas().toString());
                respMsg.setDatas(null);
            }

            //红人信息
            Sensation sensation = sensationService.get(sensationId);

            //状态：1-正常、2-冻结、3-删除
            if(sensation.getSensationStatus() == 2){
                respMsg.setResult("106011");
                respMsg.setMessage(ResultCode.result_106011);
                return respMsg;
            }

            // 玩家信息
            JSONObject job = new JSONObject();

            //手机号码
            job.put("phone", sensation.getPhone());
            //昵称
            job.put("nickName", sensation.getNickName() == null ? "" : sensation.getNickName());
            //头像
            job.put("headPic", sensation.getHeadPic() == null ? "" : sensation.getHeadPic());
            //性别：1-男、2-女、3-无
            job.put("sex", sensation.getSex());
            //QQ
            job.put("qq", sensation.getQq() == null ? "" : sensation.getQq());
            //微信
            job.put("weChat", sensation.getWeChat() == null ? "" : sensation.getWeChat());
            //省ID
            job.put("provinceId", sensation.getProvinceId() == null ? "" : sensation.getProvinceId());
            //市ID
            job.put("cityId", sensation.getCityId() == null ? "" : sensation.getCityId());
            //区ID
            job.put("areaId", sensation.getAreaId() == null ? "" : sensation.getAreaId());
            //余额
            job.put("balancePrice", NumUtils.doubleToScale(sensation.getBalancePrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
            //认证状态：1-未完善个人资料、2-未认证、3-认证中、4-认证拒绝、5-已认证
            job.put("status", sensation.getStatus());
            //收样人姓名
            job.put("consigneeName", sensation.getConsigneeName() == null ? "" : sensation.getConsigneeName());
            //收样地址
            job.put("consigneeAddress", sensation.getConsigneeAddress() == null ? "" : sensation.getConsigneeAddress());

            //淘宝客授权状态：1-未授权、2-已授权
            Integer relationStatus = 1;
            if(sensation.getRelationId() != null){
                relationStatus = 2;
            }
            job.put("relationStatus", relationStatus);

            respMsg.setDatas(job);
            respMsg.setResult("0");
            respMsg.setMessage(ResultCode.result_0);
        }catch (Exception e){
            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(ResultCode.result_100000);
        }
        return respMsg;
    }

    /**
     * 完善和修改红人个人资料 -107
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateSensation")
    public ResponseMessage updateSensation(@RequestBody Map<String, String> map){

        ResponseMessage respMsg = null;
        try {

            respMsg = super.checkLogin(hrdhCacheService,map);
            Long sensationId ;
            //如果result为空则直接返回
            if (StringUtils.isNotBlank(respMsg.result)) {
                return respMsg;
            }else{
                sensationId = Long.parseLong(respMsg.getDatas().toString());
                respMsg.setDatas(null);
            }

            //检测昵称不能为空
            String nickName = map.get("nickName");
            if(StringUtils.isBlank(nickName)){
                respMsg.setResult("107011");
                respMsg.setMessage(ResultCode.result_107011);
                return respMsg;
            }

            //检测头像不能为空
            String headPic = map.get("headPic");
            if(StringUtils.isBlank(headPic)){
                respMsg.setResult("107021");
                respMsg.setMessage(ResultCode.result_107021);
                return respMsg;
            }

            //检测性别：1-男、2-女、3-无不能为空
            String sex = map.get("sex");
            if(StringUtils.isBlank(sex)){
                respMsg.setResult("107031");
                respMsg.setMessage(ResultCode.result_107031);
                return respMsg;
            }

            //检测QQ不能为空
            String qq = map.get("qq");
            if(StringUtils.isBlank(sex)){
                respMsg.setResult("107041");
                respMsg.setMessage(ResultCode.result_107041);
                return respMsg;
            }

            //检测微信不能为空
            String weChat = map.get("weChat");
            if(StringUtils.isBlank(weChat)){
                respMsg.setResult("107051");
                respMsg.setMessage(ResultCode.result_107051);
                return respMsg;
            }

            //检测省ID不能为空
            String provinceId = map.get("provinceId");
            if(StringUtils.isBlank(provinceId)){
                respMsg.setResult("107061");
                respMsg.setMessage(ResultCode.result_107061);
                return respMsg;
            }

            //检测市ID不能为空
            String cityId = map.get("cityId");
            if(StringUtils.isBlank(cityId)){
                respMsg.setResult("107071");
                respMsg.setMessage(ResultCode.result_107071);
                return respMsg;
            }

            //检测区ID不能为空
            String areaId = map.get("areaId");
            if(StringUtils.isBlank(areaId)){
                respMsg.setResult("107081");
                respMsg.setMessage(ResultCode.result_107081);
                return respMsg;
            }

            //检测收样人姓名不能为空
            String consigneeName = map.get("consigneeName");
            if(StringUtils.isBlank(consigneeName)){
                respMsg.setResult("107091");
                respMsg.setMessage(ResultCode.result_107091);
                return respMsg;
            }

            //检测收样地址不能为空
            String consigneeAddress = map.get("consigneeAddress");
            if(StringUtils.isBlank(consigneeAddress)){
                respMsg.setResult("107101");
                respMsg.setMessage(ResultCode.result_107101);
                return respMsg;
            }

            //红人信息
            Sensation sensation = sensationService.get(sensationId);

            if(sensation.getStatus() == 1){

                //认证状态：1-未完善个人资料、2-未认证、3-认证中、4-认证拒绝、5-已认证
                sensation.setStatus(2);
            }

            //昵称
            sensation.setNickName(nickName);
            //头像
            sensation.setHeadPic(headPic);
            //性别：1-男、2-女、3-无
            sensation.setSex(Integer.parseInt(sex));
            //QQ
            sensation.setQq(qq);
            //微信
            sensation.setWeChat(weChat);
            //省ID
            sensation.setProvinceId(Long.parseLong(provinceId));
            //市ID
            sensation.setCityId(Long.parseLong(cityId));
            //区ID
            sensation.setAreaId(Long.parseLong(areaId));
            //收样人姓名
            sensation.setConsigneeName(consigneeName);
            //收样地址
            sensation.setConsigneeAddress(consigneeAddress);

            sensationService.save(sensation);

            respMsg.setResult("1");
            respMsg.setMessage(ResultCode.result_1);
        }catch (Exception e){
            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(ResultCode.result_100000);
        }
        return respMsg;
    }

    /**
     * 获取红人认证详情 -108
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/sensationAttestationInfo")
    public ResponseMessage sensationAttestationInfo(@RequestBody Map<String, String> map){

        ResponseMessage respMsg = null;
        try {

            respMsg = super.checkLogin(hrdhCacheService,map);
            Long sensationId ;
            //如果result为空则直接返回
            if (StringUtils.isNotBlank(respMsg.result)) {
                return respMsg;
            }else{
                sensationId = Long.parseLong(respMsg.getDatas().toString());
                respMsg.setDatas(null);
            }

            //红人信息
            Sensation sensation = sensationService.get(sensationId);

            // 玩家信息
            JSONObject job = new JSONObject();

            Integer status = sensation.getStatus();

            //认证状态：1-未完善个人资料、2-未认证、3-认证中、4-认证拒绝、5-已认证
            job.put("status", sensation.getStatus());

            if(status == 1 || status == 2 || status == 5) {

                //推广类目：1-抖音、2-快手、3-小红书、4-淘宝、5-今日头条、6-微博、7-B站
                job.put("extensionStatus", sensation.getExtensionStatus() == null ? "" : sensation.getExtensionStatus());
                //认证链接
                job.put("authenticationLink", sensation.getAuthenticationLink() == null ? "" : sensation.getAuthenticationLink());
                //认证图片
                job.put("authenticationPic", sensation.getAuthenticationPic() == null ? "" : sensation.getAuthenticationPic());
                //粉丝数量
                job.put("followersNum", sensation.getFollowersNum());
                //获赞总量
                job.put("totalLikedNum", sensation.getTotalLikedNum());
                //单条最高获赞
                job.put("singleHighestNum", sensation.getSingleHighestNum());
                //内容分类：1-搞笑娱乐、2-萌宠动漫、3-情感励志、4-教育知识、5-母婴玩具、6-颜值才艺、7-健康养生、8-游戏电竞、9-汽车周边、10-科技生活、11-新闻媒体、12-美妆护肤、13-旅游攻略、14-美食攻略、15-教程技术、16-综合种草、17-服装穿搭、18-家居百货、19-剧情故事、20-音乐、21-时尚、22-创意
                job.put("contentType", sensation.getContentType() == null ? "" : sensation.getContentType());
                //粉丝分布：1-男女平衡、2-男粉较多、3-女粉较多
                job.put("followersSpreadType", sensation.getFollowersSpreadType() == null ? "" : sensation.getFollowersSpreadType());
                //推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成、4-直播带货
                job.put("extensionType", sensation.getExtensionType() == null ? "" : sensation.getExtensionType());
                //红人概况（文字版）
                job.put("sensationSurvey", sensation.getSensationSurvey() == null ? "" : sensation.getSensationSurvey());

            }else {

                SensationCheck sensationCheck = sensationCheckService.getSensationCheckBySensation(sensationId);

                //推广类目：1-抖音、2-快手、3-小红书、4-淘宝、5-今日头条、6-微博、7-B站
                job.put("extensionStatus", sensationCheck.getExtensionStatus());
                //认证链接
                job.put("authenticationLink", sensationCheck.getAuthenticationLink());
                //认证图片
                job.put("authenticationPic", sensationCheck.getAuthenticationPic());
                //粉丝数量
                job.put("followersNum", sensationCheck.getFollowersNum());
                //获赞总量
                job.put("totalLikedNum", sensationCheck.getTotalLikedNum());
                //单条最高获赞
                job.put("singleHighestNum", sensationCheck.getSingleHighestNum());
                //内容分类：1-搞笑娱乐、2-萌宠动漫、3-情感励志、4-教育知识、5-母婴玩具、6-颜值才艺、7-健康养生、8-游戏电竞、9-汽车周边、10-科技生活、11-新闻媒体、12-美妆护肤、13-旅游攻略、14-美食攻略、15-教程技术、16-综合种草、17-服装穿搭、18-家居百货、19-剧情故事、20-音乐、21-时尚、22-创意
                job.put("contentType", sensationCheck.getContentType());
                //粉丝分布：1-男女平衡、2-男粉较多、3-女粉较多
                job.put("followersSpreadType", sensationCheck.getFollowersSpreadType());
                //推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成、4-直播带货
                job.put("extensionType", sensationCheck.getExtensionType());
                //红人概况（文字版）
                job.put("sensationSurvey", sensationCheck.getSensationSurvey());
                //拒绝理由
                job.put("refuseContent", sensationCheck.getRefuseContent() == null ? "" : sensationCheck.getRefuseContent());

            }

            respMsg.setDatas(job);
            respMsg.setResult("0");
            respMsg.setMessage(ResultCode.result_0);
        }catch (Exception e){
            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(ResultCode.result_100000);
        }
        return respMsg;
    }

    /**
     * 红人认证 -109
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateSensationAttestation")
    public ResponseMessage updateSensationAttestation(@RequestBody Map<String, String> map){

        ResponseMessage respMsg = null;
        try {

            respMsg = super.checkLogin(hrdhCacheService,map);
            Long sensationId ;
            //如果result为空则直接返回
            if (StringUtils.isNotBlank(respMsg.result)) {
                return respMsg;
            }else{
                sensationId = Long.parseLong(respMsg.getDatas().toString());
                respMsg.setDatas(null);
            }

            //红人信息
            Sensation sensation = sensationService.get(sensationId);

            Integer status = sensation.getStatus();

            if(status == 1){
                respMsg.setResult("109011");
                respMsg.setMessage(ResultCode.result_109011);
                return respMsg;

            }else if(status == 3){
                respMsg.setResult("109021");
                respMsg.setMessage(ResultCode.result_109021);
                return respMsg;

            }else if(status == 5){
                respMsg.setResult("109031");
                respMsg.setMessage(ResultCode.result_109031);
                return respMsg;
            }

            //推广类目：1-抖音、2-快手、3-小红书、4-淘宝、5-今日头条、6-微博、7-B站
            String extensionStatus = map.get("extensionStatus");
            if(StringUtils.isBlank(extensionStatus)){
                respMsg.setResult("109041");
                respMsg.setMessage(ResultCode.result_109041);
                return respMsg;
            }

            //认证链接
            String authenticationLink = map.get("authenticationLink");
            if(StringUtils.isBlank(authenticationLink)){
                respMsg.setResult("109051");
                respMsg.setMessage(ResultCode.result_109051);
                return respMsg;
            }

            //认证图片
            String authenticationPic = map.get("authenticationPic");
            if(StringUtils.isBlank(authenticationPic)){
                respMsg.setResult("109061");
                respMsg.setMessage(ResultCode.result_109061);
                return respMsg;
            }

            //粉丝数量
            String followersNum = map.get("followersNum");
            if(StringUtils.isBlank(followersNum)){
                respMsg.setResult("109071");
                respMsg.setMessage(ResultCode.result_109071);
                return respMsg;
            }

            //获赞总量
            String totalLikedNum = map.get("totalLikedNum");
            if(StringUtils.isBlank(totalLikedNum)){
                respMsg.setResult("109081");
                respMsg.setMessage(ResultCode.result_109081);
                return respMsg;
            }

            //单条最高获赞
            String singleHighestNum = map.get("singleHighestNum");
            if(StringUtils.isBlank(singleHighestNum)){
                respMsg.setResult("109091");
                respMsg.setMessage(ResultCode.result_109091);
                return respMsg;
            }

            //内容分类：1-搞笑娱乐、2-萌宠动漫、3-情感励志、4-教育知识、5-母婴玩具、6-颜值才艺、
            // 7-健康养生、8-游戏电竞、9-汽车周边、10-科技生活、11-新闻媒体、12-美妆护肤、13-旅游攻略、
            // 14-美食攻略、15-教程技术、16-综合种草、17-服装穿搭、18-家居百货、19-剧情故事、20-音乐、21-时尚、22-创意
            String contentType = map.get("contentType");
            if(StringUtils.isBlank(contentType)){
                respMsg.setResult("109101");
                respMsg.setMessage(ResultCode.result_109101);
                return respMsg;
            }

            //粉丝分布：1-男女平衡、2-男粉较多、3-女粉较多
            String followersSpreadType = map.get("followersSpreadType");
            if(StringUtils.isBlank(followersSpreadType)){
                respMsg.setResult("109111");
                respMsg.setMessage(ResultCode.result_109111);
                return respMsg;
            }

            //推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成、4-直播带货
            String extensionType = map.get("extensionType");
            if(StringUtils.isBlank(extensionType)){
                respMsg.setResult("109121");
                respMsg.setMessage(ResultCode.result_109121);
                return respMsg;
            }

            //红人概况（文字版）
            String sensationSurvey = map.get("sensationSurvey");

            SensationCheck sensationCheck = new SensationCheck();

            //红人ID
            sensationCheck.setSensationId(sensationId);
            //推广类目：1-抖音、2-快手、3-小红书、4-淘宝、5-今日头条、6-微博、7-B站
            sensationCheck.setExtensionStatus(Integer.parseInt(extensionStatus));
            //认证链接
            sensationCheck.setAuthenticationLink(authenticationLink);
            //认证图片
            sensationCheck.setAuthenticationPic(authenticationPic);
            //审核状态：1-审核中、2-审核拒绝、3-审核通过
            sensationCheck.setCheckStatus(1);
            //内容分类：1-搞笑娱乐、2-萌宠动漫、3-情感励志、4-教育知识、5-母婴玩具、6-颜值才艺、7-健康养生、8-游戏电竞、9-汽车周边、10-科技生活、11-新闻媒体、12-美妆护肤、13-旅游攻略、14-美食攻略、15-教程技术、16-综合种草、17-服装穿搭、18-家居百货、19-剧情故事、20-音乐、21-时尚、22-创意
            sensationCheck.setContentType(Integer.parseInt(contentType));
            //粉丝分布：1-男女平衡、2-男粉较多、3-女粉较多
            sensationCheck.setFollowersSpreadType(Integer.parseInt(followersSpreadType));
            //推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成、4-直播带货
            sensationCheck.setExtensionType(extensionType);
            //粉丝数量
            sensationCheck.setFollowersNum(Integer.parseInt(followersNum));
            //获赞总量
            sensationCheck.setTotalLikedNum(Integer.parseInt(totalLikedNum));
            //单条最高获赞
            sensationCheck.setSingleHighestNum(Integer.parseInt(singleHighestNum));
            //红人概况（文字版）
            sensationCheck.setSensationSurvey(sensationSurvey);
            //创建时间
            sensationCheck.setCreateDate(new Date());

            sensationCheckService.save(sensationCheck);

            //认证状态：1-未完善个人资料、2-未认证、3-认证中、4-认证拒绝、5-已认证
            sensation.setStatus(3);

            sensationService.save(sensation);

            respMsg.setResult("1");
            respMsg.setMessage(ResultCode.result_7);
        }catch (Exception e){
            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(ResultCode.result_100000);
        }
        return respMsg;
    }

    /**
     * 红人列表 -110
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/sensationList")
    public ResponseMessage sensationList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(map);

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

            //内容分类：1-搞笑娱乐、2-萌宠动漫、3-情感励志、4-教育知识、5-母婴玩具、6-颜值才艺、7-健康养生、
            //8-游戏电竞、9-汽车周边、10-科技生活、11-新闻媒体、12-美妆护肤、13-旅游攻略、14-美食攻略、
            //15-教程技术、16-综合种草、17-服装穿搭、18-家居百货、19-剧情故事、20-音乐、21-时尚、22-创意
            String contentTypeStr = map.get("contentType");
            Integer contentType = null;
            if(StringUtils.isNotBlank(contentTypeStr)){
                contentType = Integer.parseInt(contentTypeStr);
            }

            //粉丝数量:1、10W以内、2-10W-30W、3-30W-100W、4-100W-300W、5-300W-500W、6-500W以上
            String followersTypeStr = map.get("followersType");
            Integer followersType = null;
            if(StringUtils.isNotBlank(followersTypeStr)){
                followersType = Integer.parseInt(followersTypeStr);
            }

            //粉丝分布：1-男女平衡、2-男粉较多、3-女粉较多
            String followersSpreadTypeStr = map.get("followersSpreadType");
            Integer followersSpreadType = null;
            if(StringUtils.isNotBlank(followersSpreadTypeStr)){
                followersSpreadType = Integer.parseInt(followersSpreadTypeStr);
            }

            //获赞总量：1、10W以内、2-10W-30W、3-30W-100W、4-100W-300W、5-300W-500W、6-500W以上
            String totalLikedTypeStr = map.get("totalLikedType");
            Integer totalLikedType = null;
            if(StringUtils.isNotBlank(totalLikedTypeStr)){
                totalLikedType = Integer.parseInt(totalLikedTypeStr);
            }

            //省份：110000-北京市、120000-天津市、130000-河北省、140000-山西省、210000-辽宁省、220000-吉林省、
            // 230000-黑龙江省、310000-上海市、320000-江苏省、330000-浙江省、340000-安徽省、350000-福建省、
            // 360000-江西省、370000-山东省、410000-河南省、420000-湖北省、430000-湖南省、440000-广东省、
            // 450000-广西壮族自治区、460000-海南省、500000-重庆市、510000-四川省、520000-贵州省、530000-云南省、
            // 540000-西藏自治区、610000-陕西省、630000-青海省、630000-宁夏回族自治区、650000-新疆维吾尔自治区、
            // 710000-台湾省、810000-香港特别行政区、620000-甘肃省、150000-内蒙古自治区、820000、澳门特别行政区
            String provinceIdStr = map.get("provinceType");
            Long provinceId = null;
            if(StringUtils.isNotBlank(provinceIdStr)){
                provinceId = Long.parseLong(provinceIdStr);
            }

            //推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成、4-直播带货
            String extensionType = map.get("extensionType");

            String[] extensionTypes = extensionType.split(",");

            JSONArray ja = new JSONArray();

            //获取列表总数
            Integer totalRow = sensationService.getSensationVmListByTypeNum(contentType, followersType, followersSpreadType, totalLikedType, provinceId, extensionTypes);

            if (totalRow > 0) {

                List<SensationVm> sensationList = sensationService.findSensationVmListByType(contentType, followersType, followersSpreadType, totalLikedType, provinceId, extensionTypes, beginNum, pageSize);

                for (SensationVm s : sensationList) {

                    JSONObject obj = new JSONObject();

                    //ID
                    obj.put("sensationId", s.getId());
                    //头像
                    obj.put("headPic", s.getHeadPic());
                    //昵称
                    obj.put("nickName", s.getNickName());
                    //粉丝数量
                    obj.put("followersNum", s.getFollowersNum());
                    //获赞总量
                    obj.put("totalLikedNum", s.getTotalLikedNum());
                    //单条最高获赞
                    obj.put("singleHighestNum", s.getSingleHighestNum());
                    //粉丝分布：1-男女平衡、2-男粉较多、3-女粉较多
                    obj.put("followersSpreadType", s.getFollowersSpreadType());
                    //省名称
                    obj.put("provinceName", s.getProvinceName());
                    //推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成、4-直播带货
                    obj.put("extensionType", s.getExtensionType());
                    //红人概况（文字版）
                    obj.put("sensationSurvey", s.getSensationSurvey());

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
     * 红人搜索列表 -111
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/sensationSearchList")
    public ResponseMessage sensationSearchList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(map);

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

            //检测红人昵称不能为空
            String nickName = map.get("nickName");
            if(StringUtils.isBlank(nickName)){
                returnMsg.setResult("111011");
                returnMsg.setMessage(ResultCode.result_111011);
                return returnMsg;
            }

            JSONArray ja = new JSONArray();

            //获取列表总数
            Integer totalRow = sensationService.getSensationVmListByNickNameNum(nickName);

            if(totalRow > 0) {

                List<SensationVm> sensationList = sensationService.findSensationVmListByNickName(nickName, beginNum, pageSize);

                for (SensationVm s : sensationList) {

                    JSONObject obj = new JSONObject();

                    //ID
                    obj.put("sensationId", s.getId());
                    //头像
                    obj.put("headPic", s.getHeadPic());
                    //昵称
                    obj.put("nickName", s.getNickName());
                    //粉丝数量
                    obj.put("followersNum", s.getFollowersNum());
                    //获赞总量
                    obj.put("totalLikedNum", s.getTotalLikedNum());
                    //单条最高获赞
                    obj.put("singleHighestNum", s.getSingleHighestNum());
                    //粉丝分布：1-男女平衡、2-男粉较多、3-女粉较多
                    obj.put("followersSpreadType", s.getFollowersSpreadType());
                    //省名称
                    obj.put("provinceName", s.getProvinceName());
                    //推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成、4-直播带货
                    obj.put("extensionType", s.getExtensionType());
                    //红人概况（文字版）
                    obj.put("sensationSurvey", s.getSensationSurvey());

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
     * 红人详情 -112
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/sensationDetails")
    public ResponseMessage sensationDetails(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            //检测红人ID不能为空
            String sensationId = map.get("sensationId");
            if(StringUtils.isBlank(sensationId)){
                returnMsg.setResult("112010");
                returnMsg.setMessage(ResultCode.result_112010);
                return returnMsg;
            }

            SensationVm s = sensationService.getSensationVmById(Long.parseLong(sensationId));

            JSONObject obj = new JSONObject();

            //ID
            obj.put("sensationId", s.getId());
            //头像
            obj.put("headPic", s.getHeadPic());
            //昵称
            obj.put("nickName", s.getNickName());
            //粉丝数量
            obj.put("followersNum", s.getFollowersNum());
            //获赞总量
            obj.put("totalLikedNum", s.getTotalLikedNum());
            //单条最高获赞
            obj.put("singleHighestNum", s.getSingleHighestNum());
            //粉丝分布：1-男女平衡、2-男粉较多、3-女粉较多
            obj.put("followersSpreadType", s.getFollowersSpreadType());
            //省名称
            obj.put("provinceName", s.getProvinceName());
            //推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成、4-直播带货
            obj.put("extensionType", s.getExtensionType());
            //红人概况（文字版）
            obj.put("sensationSurvey", s.getSensationSurvey());

            returnMsg.setDatas(obj);
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
     * 红人金额 -113
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/sensationPrice")
    public ResponseMessage sensationPrice(@RequestBody Map<String, String> map) {

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

            //红人信息
            Sensation sensation = sensationService.get(sensationId);

            JSONObject obj = new JSONObject();

            //余额
            obj.put("balancePrice", NumUtils.doubleToScale(sensation.getBalancePrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
            //预估收入余额
            obj.put("estimatedRevenuePrice", NumUtils.doubleToScale(sensation.getEstimatedRevenuePrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));

            returnMsg.setDatas(obj);
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
