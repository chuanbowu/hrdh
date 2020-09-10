package com.flym.hrdh.controller.system.sensation;

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
import com.flym.hrdh.pojo.business.Business;
import com.flym.hrdh.pojo.sensation.Sensation;
import com.flym.hrdh.pojo.sensation.SensationCheck;
import com.flym.hrdh.utils.IpAddressUtil;
import com.flym.hrdh.utils.MD5Util;
import com.flym.hrdh.utils.NumUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping(value = "/system/sensation")
public class SensationController extends BaseController {

    @Reference(version = "1.0.0")
    protected IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected ISensationService sensationService;

    /**
     * 红人列表 -401
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/sensationList")
    public ResponseMessage sensationList(@RequestBody Map<String, String> map) {

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

            //红人类型：1-红人、2-找红人
            String sensationTypeStr = map.get("sensationType");
            Integer sensationType = null;
            if(StringUtils.isNotBlank(sensationTypeStr)){
                sensationType = Integer.parseInt(sensationTypeStr);
            }

            //昵称
            String nickName = map.get("nickName");

            //认证状态：1-待认证
            String statusStr = map.get("status");
            Integer status = null;
            if(StringUtils.isNotBlank(statusStr)){
                status = Integer.parseInt(statusStr);
            }

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
            String extensionTypeStr = map.get("extensionType");
            Integer extensionType = null;
            if(StringUtils.isNotBlank(extensionTypeStr)){
                extensionType = Integer.parseInt(extensionTypeStr);
            }

            JSONArray ja = new JSONArray();

            //获取列表总数
            Integer totalRow = sensationService.getSensationVmNum(sensationType, nickName, status, contentType, followersType, followersSpreadType, totalLikedType, provinceId, extensionType);

            if (totalRow > 0) {

                List<SensationVm> sensationList = sensationService.findSensationVmList(sensationType, nickName, status, contentType, followersType, followersSpreadType, totalLikedType, provinceId, extensionType, beginNum, pageSize);

                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                for (SensationVm s : sensationList) {

                    JSONObject obj = new JSONObject();

                    //ID
                    obj.put("sensationId", s.getId());
                    //手机号码
                    obj.put("phone", s.getPhone());
                    //头像
                    obj.put("headPic", s.getHeadPic() == null ? "" : s.getHeadPic());
                    //昵称
                    obj.put("nickName", s.getNickName() == null ? "" : s.getNickName());
                    //性别：1-男、2-女、3-无
                    obj.put("sex", s.getSex());
                    //QQ
                    obj.put("qq", s.getQq() == null ? "" : s.getQq());
                    //微信
                    obj.put("weChat", s.getWeChat() == null ? "" : s.getWeChat());
                    //省ID
                    obj.put("provinceId", s.getProvinceId() == null ? "" : s.getProvinceId());
                    //省名称
                    obj.put("provinceName", s.getProvinceName() == null ? "" : s.getProvinceName());
                    //市ID
                    obj.put("cityId", s.getCityId() == null ? "" : s.getCityId());
                    //市名称
                    obj.put("cityName", s.getCityName() == null ? "" : s.getCityName());
                    //区ID
                    obj.put("areaId", s.getAreaId() == null ? "" : s.getAreaId());
                    //区名称
                    obj.put("areaName", s.getAreaName() == null ? "" : s.getAreaName());
                    //推广类目：1-抖音、2-快手、3-小红书、4-淘宝、5-今日头条、6-微博、7-B站
                    obj.put("extensionStatus", s.getExtensionStatus() == null ? "" : s.getExtensionStatus());
                    //余额
                    obj.put("balancePrice", NumUtils.doubleToScale(s.getBalancePrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //预估收入余额
                    obj.put("estimatedRevenuePrice", NumUtils.doubleToScale(s.getEstimatedRevenuePrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
                    //认证状态：1-未完善个人资料、2-未认证、3-认证中、4-认证拒绝、5-已认证
                    obj.put("status", s.getStatus());
                    //收样人姓名
                    obj.put("consigneeName", s.getConsigneeName() == null ? "" : s.getConsigneeName());
                    //收样地址
                    obj.put("consigneeAddress", s.getConsigneeAddress() == null ? "" : s.getConsigneeAddress());
                    //认证链接
                    obj.put("authenticationLink", s.getAuthenticationLink() == null ? "" : s.getAuthenticationLink());
                    //认证图片
                    obj.put("authenticationPic", s.getAuthenticationPic() == null ? "" : s.getAuthenticationPic());
                    //粉丝数量
                    obj.put("followersNum", s.getFollowersNum());
                    //获赞总量
                    obj.put("totalLikedNum", s.getTotalLikedNum());
                    //单条最高获赞
                    obj.put("singleHighestNum", s.getSingleHighestNum());
                    //内容分类：1-搞笑娱乐、2-萌宠动漫、3-情感励志、4-教育知识、5-母婴玩具、6-颜值才艺、7-健康养生、8-游戏电竞、9-汽车周边、10-科技生活、11-新闻媒体、12-美妆护肤、13-旅游攻略、14-美食攻略、15-教程技术、16-综合种草、17-服装穿搭、18-家居百货、19-剧情故事、20-音乐、21-时尚、22-创意
                    obj.put("contentType", s.getContentType() == null ? "" : s.getContentType());
                    //粉丝分布：1-男女平衡、2-男粉较多、3-女粉较多
                    obj.put("followersSpreadType", s.getFollowersSpreadType() == null ? "" : s.getFollowersSpreadType());
                    //推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成、4-直播带货
                    obj.put("extensionType", s.getExtensionType() == null ? "" : s.getExtensionType());
                    //红人概况（文字版）
                    obj.put("sensationSurvey", s.getSensationSurvey() == null ? "" : s.getSensationSurvey());
                    //状态：1-正常、2-禁用、3-删除
                    obj.put("sensationStatus", s.getSensationStatus());
                    //创建时间
                    obj.put("createDate", sf.format(s.getCreateDate()));

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
     * 红人详情 -402
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/sensationInfo")
    public ResponseMessage sensationInfo(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(hrdhCacheService, map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            //检测红人ID不能为空
            String sensationId = map.get("sensationId");
            if(StringUtils.isBlank(sensationId)){
                returnMsg.setResult("402010");
                returnMsg.setMessage(ResultCode.result_402010);
                return returnMsg;
            }

            SensationVm s = sensationService.getSensationVmById(Long.parseLong(sensationId));

            JSONObject obj = new JSONObject();

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            //ID
            obj.put("sensationId", s.getId());
            //手机号码
            obj.put("phone", s.getPhone());
            //头像
            obj.put("headPic", s.getHeadPic() == null ? "" : s.getHeadPic());
            //昵称
            obj.put("nickName", s.getNickName() == null ? "" : s.getNickName());
            //性别：1-男、2-女、3-无
            obj.put("sex", s.getSex());
            //QQ
            obj.put("qq", s.getQq() == null ? "" : s.getQq());
            //微信
            obj.put("weChat", s.getWeChat() == null ? "" : s.getWeChat());
            //省ID
            obj.put("provinceId", s.getProvinceId() == null ? "" : s.getProvinceId());
            //省名称
            obj.put("provinceName", s.getProvinceName() == null ? "" : s.getProvinceName());
            //市ID
            obj.put("cityId", s.getCityId() == null ? "" : s.getCityId());
            //市名称
            obj.put("cityName", s.getCityName() == null ? "" : s.getCityName());
            //区ID
            obj.put("areaId", s.getAreaId() == null ? "" : s.getAreaId());
            //区名称
            obj.put("areaName", s.getAreaName() == null ? "" : s.getAreaName());
            //推广类目：1-抖音、2-快手、3-小红书、4-淘宝、5-今日头条、6-微博、7-B站
            obj.put("extensionStatus", s.getExtensionStatus() == null ? "" : s.getExtensionStatus());
            //余额
            obj.put("balancePrice", NumUtils.doubleToScale(s.getBalancePrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
            //预估收入余额
            obj.put("estimatedRevenuePrice", NumUtils.doubleToScale(s.getEstimatedRevenuePrice(), CommonConfig.INTEGRAL_KEEP_DECIMAL_PLACES));
            //认证状态：1-未完善个人资料、2-未认证、3-认证中、4-认证拒绝、5-已认证
            obj.put("status", s.getStatus());
            //收样人姓名
            obj.put("consigneeName", s.getConsigneeName() == null ? "" : s.getConsigneeName());
            //收样地址
            obj.put("consigneeAddress", s.getConsigneeAddress() == null ? "" : s.getConsigneeAddress());
            //认证链接
            obj.put("authenticationLink", s.getAuthenticationLink() == null ? "" : s.getAuthenticationLink());
            //认证图片
            obj.put("authenticationPic", s.getAuthenticationPic() == null ? "" : s.getAuthenticationPic());
            //粉丝数量
            obj.put("followersNum", s.getFollowersNum());
            //获赞总量
            obj.put("totalLikedNum", s.getTotalLikedNum());
            //单条最高获赞
            obj.put("singleHighestNum", s.getSingleHighestNum());
            //内容分类：1-搞笑娱乐、2-萌宠动漫、3-情感励志、4-教育知识、5-母婴玩具、6-颜值才艺、7-健康养生、8-游戏电竞、9-汽车周边、10-科技生活、11-新闻媒体、12-美妆护肤、13-旅游攻略、14-美食攻略、15-教程技术、16-综合种草、17-服装穿搭、18-家居百货、19-剧情故事、20-音乐、21-时尚、22-创意
            obj.put("contentType", s.getContentType() == null ? "" : s.getContentType());
            //粉丝分布：1-男女平衡、2-男粉较多、3-女粉较多
            obj.put("followersSpreadType", s.getFollowersSpreadType() == null ? "" : s.getFollowersSpreadType());
            //推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成、4-直播带货
            obj.put("extensionType", s.getExtensionType() == null ? "" : s.getExtensionType());
            //红人概况（文字版）
            obj.put("sensationSurvey", s.getSensationSurvey() == null ? "" : s.getSensationSurvey());
            //状态：1-正常、2-禁用、3-删除
            obj.put("sensationStatus", s.getSensationStatus());
            //创建时间
            obj.put("createDate", sf.format(s.getCreateDate()));

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
     * 新增找红人 -403
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/addSensation")
    public ResponseMessage addSensation(@RequestBody Map<String, String> map, HttpServletRequest request) {

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

            //检测手机号不能为空
            String phone = map.get("phone");
            if(StringUtils.isBlank(phone)){
                returnMsg.setResult("403011");
                returnMsg.setMessage(ResultCode.result_403011);
                return returnMsg;
            }

            //检测昵称不能为空
            String nickName = map.get("nickName");
            if(StringUtils.isBlank(nickName)){
                returnMsg.setResult("403021");
                returnMsg.setMessage(ResultCode.result_403021);
                return returnMsg;
            }

            //头像
            String headPic = map.get("headPic");
            if(StringUtils.isBlank(headPic)){
                returnMsg.setResult("403031");
                returnMsg.setMessage(ResultCode.result_403031);
                return returnMsg;
            }

            //性别：1-男、2-女
            String sex = map.get("sex");
            if(StringUtils.isBlank(sex)){
                returnMsg.setResult("403041");
                returnMsg.setMessage(ResultCode.result_403041);
                return returnMsg;
            }

            //省ID
            String provinceId = map.get("provinceId");
            if(StringUtils.isBlank(provinceId)){
                returnMsg.setResult("403051");
                returnMsg.setMessage(ResultCode.result_403051);
                return returnMsg;
            }

            //市ID
            String cityId = map.get("cityId");
            if(StringUtils.isBlank(cityId)){
                returnMsg.setResult("403061");
                returnMsg.setMessage(ResultCode.result_403061);
                return returnMsg;
            }

            //区ID
            String areaId = map.get("areaId");
            if(StringUtils.isBlank(areaId)){
                returnMsg.setResult("40307");
                returnMsg.setMessage(ResultCode.result_403071);
                return returnMsg;
            }

            //推广类目：1-抖音、2-快手、3-小红书、4-淘宝、5-今日头条、6-微博、7-B站
            String extensionStatus = map.get("extensionStatus");
            if(StringUtils.isBlank(extensionStatus)){
                returnMsg.setResult("403081");
                returnMsg.setMessage(ResultCode.result_403081);
                return returnMsg;
            }

            //粉丝数量
            String followersNum = map.get("followersNum");
            if(StringUtils.isBlank(followersNum)){
                returnMsg.setResult("403091");
                returnMsg.setMessage(ResultCode.result_403091);
                return returnMsg;
            }

            //获赞总量
            String totalLikedNum = map.get("totalLikedNum");
            if(StringUtils.isBlank(totalLikedNum)){
                returnMsg.setResult("403101");
                returnMsg.setMessage(ResultCode.result_403101);
                return returnMsg;
            }

            //单条最高获赞
            String singleHighestNum = map.get("singleHighestNum");
            if(StringUtils.isBlank(singleHighestNum)){
                returnMsg.setResult("403111");
                returnMsg.setMessage(ResultCode.result_403111);
                return returnMsg;
            }

            //内容分类：1-搞笑娱乐、2-萌宠动漫、3-情感励志、4-教育知识、5-母婴玩具、6-颜值才艺、7-健康养生、8-游戏电竞、
            // 9-汽车周边、10-科技生活、11-新闻媒体、12-美妆护肤、13-旅游攻略、14-美食攻略、15-教程技术、16-综合种草、
            // 17-服装穿搭、18-家居百货、19-剧情故事、20-音乐、21-时尚、22-创意
            String contentType = map.get("contentType");
            if(StringUtils.isBlank(contentType)){
                returnMsg.setResult("403121");
                returnMsg.setMessage(ResultCode.result_403121);
                return returnMsg;
            }

            //粉丝分布：1-男女平衡、2-男粉较多、3-女粉较多
            String followersSpreadType = map.get("followersSpreadType");
            if(StringUtils.isBlank(followersSpreadType)){
                returnMsg.setResult("403131");
                returnMsg.setMessage(ResultCode.result_403131);
                return returnMsg;
            }

            //推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成、4-直播带货
            String extensionType = map.get("extensionType");
            if(StringUtils.isBlank(extensionType)){
                returnMsg.setResult("403141");
                returnMsg.setMessage(ResultCode.result_403141);
                return returnMsg;
            }

            //红人概况
            String sensationSurvey = map.get("sensationSurvey");
            if(StringUtils.isBlank(sensationSurvey)){
                returnMsg.setResult("403151");
                returnMsg.setMessage(ResultCode.result_403151);
                return returnMsg;
            }

            int num = sensationService.getSensationPhoneNum(phone);

            //判断是否手机号已经注册
            if(num > 0){
                returnMsg.setResult("403161");
                returnMsg.setMessage(ResultCode.result_403161);
                return returnMsg;
            }

            Sensation sensation = new Sensation();

            //手机号码
            sensation.setPhone(phone);
            //昵称
            sensation.setNickName(nickName);
            //头像
            sensation.setHeadPic(headPic);
            //密码
            sensation.setPassword(MD5Util.encoder("123456"));
            //性别：1-男、2-女、3-无
            sensation.setSex(Integer.parseInt(sex));
            //省ID
            sensation.setProvinceId(Long.parseLong(provinceId));
            //市ID
            sensation.setCityId(Long.parseLong(cityId));
            //区ID
            sensation.setAreaId(Long.parseLong(areaId));
            //推广类目：1-抖音、2-快手、3-小红书、4-淘宝、5-今日头条、6-微博、7-B站
            sensation.setExtensionStatus(Integer.parseInt(extensionStatus));
            //余额
            sensation.setBalancePrice(0.00);
            //累计余额
            sensation.setTotalBalancePrice(0.00);
            //预估收入余额
            sensation.setEstimatedRevenuePrice(0.00);
            //认证状态：1-未完善个人资料、2-未认证、3-认证中、4-认证拒绝、5-已认证
            sensation.setStatus(5);
            //粉丝数量
            sensation.setFollowersNum(Integer.parseInt(followersNum));
            //获赞总量
            sensation.setTotalLikedNum(Integer.parseInt(totalLikedNum));
            //单条最高获赞
            sensation.setSingleHighestNum(Integer.parseInt(singleHighestNum));
            //内容分类：1-搞笑娱乐、2-萌宠动漫、3-情感励志、4-教育知识、5-母婴玩具、6-颜值才艺、7-健康养生、8-游戏电竞、9-汽车周边、10-科技生活、11-新闻媒体、12-美妆护肤、13-旅游攻略、14-美食攻略、15-教程技术、16-综合种草、17-服装穿搭、18-家居百货、19-剧情故事、20-音乐、21-时尚、22-创意
            sensation.setContentType(Integer.parseInt(contentType));
            //粉丝分布：1-男女平衡、2-男粉较多、3-女粉较多
            sensation.setFollowersSpreadType(Integer.parseInt(followersSpreadType));
            //推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成、4-直播带货
            sensation.setExtensionType(extensionType);
            //红人概况（文字版）
            sensation.setSensationSurvey(sensationSurvey);
            //红人类型：1-红人、2-找红人
            sensation.setSensationType(2);
            //状态：1-正常、2-禁用、3-删除
            sensation.setSensationStatus(1);
            //注册时间
            Date date = new Date();
            sensation.setRegisterDate(date);
            //注册IP
            sensation.setRegisterIp(IpAddressUtil.getIpAddr(request));
            //登录次数
            sensation.setLoginNum(0);
            //创建人
            sensation.setCreateSysUser(userId);
            //创建时间
            sensation.setCreateDate(date);
            //修改人
            sensation.setModifySysUser(userId);
            //修改时间
            sensation.setModifyDate(date);

            sensationService.save(sensation);

            returnMsg.setResult("1");
            returnMsg.setMessage(ResultCode.result_9);

        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(ResultCode.result_100000);
        }
        return returnMsg;
    }

    /**
     * 编辑找红人 -404
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateSensation")
    public ResponseMessage updateSensation(@RequestBody Map<String, String> map, HttpServletRequest request) {

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

            //检测红人ID不能为空
            String sensationId = map.get("sensationId");
            if(StringUtils.isBlank(sensationId)){
                returnMsg.setResult("404010");
                returnMsg.setMessage(ResultCode.result_404010);
                return returnMsg;
            }

            //检测昵称不能为空
            String nickName = map.get("nickName");
            if(StringUtils.isBlank(nickName)){
                returnMsg.setResult("404021");
                returnMsg.setMessage(ResultCode.result_404021);
                return returnMsg;
            }

            //头像
            String headPic = map.get("headPic");
            if(StringUtils.isBlank(headPic)){
                returnMsg.setResult("404031");
                returnMsg.setMessage(ResultCode.result_404031);
                return returnMsg;
            }

            //性别：1-男、2-女
            String sex = map.get("sex");
            if(StringUtils.isBlank(sex)){
                returnMsg.setResult("404041");
                returnMsg.setMessage(ResultCode.result_404041);
                return returnMsg;
            }

            //省ID
            String provinceId = map.get("provinceId");
            if(StringUtils.isBlank(provinceId)){
                returnMsg.setResult("404051");
                returnMsg.setMessage(ResultCode.result_404051);
                return returnMsg;
            }

            //市ID
            String cityId = map.get("cityId");
            if(StringUtils.isBlank(cityId)){
                returnMsg.setResult("404061");
                returnMsg.setMessage(ResultCode.result_404061);
                return returnMsg;
            }

            //区ID
            String areaId = map.get("areaId");
            if(StringUtils.isBlank(areaId)){
                returnMsg.setResult("404071");
                returnMsg.setMessage(ResultCode.result_404071);
                return returnMsg;
            }

            //推广类目：1-抖音、2-快手、3-小红书、4-淘宝、5-今日头条、6-微博、7-B站
            String extensionStatus = map.get("extensionStatus");
            if(StringUtils.isBlank(extensionStatus)){
                returnMsg.setResult("404081");
                returnMsg.setMessage(ResultCode.result_404081);
                return returnMsg;
            }

            //粉丝数量
            String followersNum = map.get("followersNum");
            if(StringUtils.isBlank(followersNum)){
                returnMsg.setResult("404091");
                returnMsg.setMessage(ResultCode.result_404091);
                return returnMsg;
            }

            //获赞总量
            String totalLikedNum = map.get("totalLikedNum");
            if(StringUtils.isBlank(totalLikedNum)){
                returnMsg.setResult("404101");
                returnMsg.setMessage(ResultCode.result_404101);
                return returnMsg;
            }

            //单条最高获赞
            String singleHighestNum = map.get("singleHighestNum");
            if(StringUtils.isBlank(singleHighestNum)){
                returnMsg.setResult("404111");
                returnMsg.setMessage(ResultCode.result_404111);
                return returnMsg;
            }

            //内容分类：1-搞笑娱乐、2-萌宠动漫、3-情感励志、4-教育知识、5-母婴玩具、6-颜值才艺、7-健康养生、8-游戏电竞、
            // 9-汽车周边、10-科技生活、11-新闻媒体、12-美妆护肤、13-旅游攻略、14-美食攻略、15-教程技术、16-综合种草、
            // 17-服装穿搭、18-家居百货、19-剧情故事、20-音乐、21-时尚、22-创意
            String contentType = map.get("contentType");
            if(StringUtils.isBlank(contentType)){
                returnMsg.setResult("404121");
                returnMsg.setMessage(ResultCode.result_404121);
                return returnMsg;
            }

            //粉丝分布：1-男女平衡、2-男粉较多、3-女粉较多
            String followersSpreadType = map.get("followersSpreadType");
            if(StringUtils.isBlank(followersSpreadType)){
                returnMsg.setResult("404131");
                returnMsg.setMessage(ResultCode.result_404131);
                return returnMsg;
            }

            //推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成、4-直播带货
            String extensionType = map.get("extensionType");
            if(StringUtils.isBlank(extensionType)){
                returnMsg.setResult("404141");
                returnMsg.setMessage(ResultCode.result_404141);
                return returnMsg;
            }

            //红人概况
            String sensationSurvey = map.get("sensationSurvey");
            if(StringUtils.isBlank(sensationSurvey)){
                returnMsg.setResult("404151");
                returnMsg.setMessage(ResultCode.result_404151);
                return returnMsg;
            }

            Sensation sensation = sensationService.get(Long.parseLong(sensationId));

            //红人类型：1-红人、2-找红人
            if(sensation.getSensationType() != 2){
                returnMsg.setResult("404161");
                returnMsg.setMessage(ResultCode.result_404161);
                return returnMsg;
            }

            //昵称
            sensation.setNickName(nickName);
            //头像
            sensation.setHeadPic(headPic);
            //性别：1-男、2-女、3-无
            sensation.setSex(Integer.parseInt(sex));
            //省ID
            sensation.setProvinceId(Long.parseLong(provinceId));
            //市ID
            sensation.setCityId(Long.parseLong(cityId));
            //区ID
            sensation.setAreaId(Long.parseLong(areaId));
            //推广类目：1-抖音、2-快手、3-小红书、4-淘宝、5-今日头条、6-微博、7-B站
            sensation.setExtensionStatus(Integer.parseInt(extensionStatus));
            //粉丝数量
            sensation.setFollowersNum(Integer.parseInt(followersNum));
            //获赞总量
            sensation.setTotalLikedNum(Integer.parseInt(totalLikedNum));
            //单条最高获赞
            sensation.setSingleHighestNum(Integer.parseInt(singleHighestNum));
            //内容分类：1-搞笑娱乐、2-萌宠动漫、3-情感励志、4-教育知识、5-母婴玩具、6-颜值才艺、7-健康养生、8-游戏电竞、9-汽车周边、10-科技生活、11-新闻媒体、12-美妆护肤、13-旅游攻略、14-美食攻略、15-教程技术、16-综合种草、17-服装穿搭、18-家居百货、19-剧情故事、20-音乐、21-时尚、22-创意
            sensation.setContentType(Integer.parseInt(contentType));
            //粉丝分布：1-男女平衡、2-男粉较多、3-女粉较多
            sensation.setFollowersSpreadType(Integer.parseInt(followersSpreadType));
            //推广方式：1-橱窗广告、2-原创视频+发布、3-CPS分成、4-直播带货
            sensation.setExtensionType(extensionType);
            //红人概况（文字版）
            sensation.setSensationSurvey(sensationSurvey);

            Date date = new Date();
            //修改人
            sensation.setModifySysUser(userId);
            //修改时间
            sensation.setModifyDate(date);

            sensationService.save(sensation);

            returnMsg.setResult("1");
            returnMsg.setMessage(ResultCode.result_6);

        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(ResultCode.result_100000);
        }
        return returnMsg;
    }

    /**
     * 修改红人状态 -405
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateStatus")
    public ResponseMessage editStatus(@RequestBody Map<String, String> map){

        ResponseMessage respMsg = null;

        try {

            respMsg = super.checkLogin(hrdhCacheService, map);

            //如果result为空则直接返回
            Long userId ;
            if (StringUtils.isNotBlank(respMsg.result)) {
                return respMsg;
            } else {
                userId = respMsg.getDatas().getLong("userId");
                respMsg.setDatas(null);
            }

            //检测红人ID不能为空
            String sensationIds = map.get("sensationIds");
            if(StringUtils.isBlank(sensationIds)){
                respMsg.setResult("405010");
                respMsg.setMessage(ResultCode.result_405010);
                return respMsg;
            }

            //检测操作类型不能为空:状态：1-正常、2-禁用、3-删除
            String statusStr = map.get("status");
            if(StringUtils.isBlank(statusStr)){
                respMsg.setResult("405020");
                respMsg.setMessage(ResultCode.result_405020);
                return respMsg;
            }
            int status = Integer.parseInt(statusStr);

            String[] idArr = sensationIds.split(",");
            for (int i = 0; i < idArr.length; i++){

                //删除用户令牌信息
                if(status != 1){
                    //用户登陆令牌
                    String userToken = idArr[i].toString() + CacheKeyConfig.REDIS_MEMBER_LOGIN_TOKEN;
                    //获取用户令牌
                    String oldToken = hrdhCacheService.getCache(userToken);
                    if(StringUtils.isNotBlank(oldToken)){
                        hrdhCacheService.delCache(oldToken);
                    }
                    hrdhCacheService.delCache(userToken);
                }
            }

            //当前时间
            Date date = new Date();

            sensationService.updateStatus(sensationIds, status , userId, date);

            respMsg.setResult("1");
            respMsg.setMessage(ResultCode.result_10);
        }catch (Exception e){
            e.printStackTrace();
            respMsg.setResult("100000");
            respMsg.setMessage(ResultCode.result_100000);
        }
        return respMsg;
    }
}
