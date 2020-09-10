package com.flym.hrdh.controller.system.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.common.SensationRecommendVm;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.api.service.common.ISensationRecommendService;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.common.Problem;
import com.flym.hrdh.pojo.common.SensationRecommend;
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
 * <p>Description:红人推荐接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-24</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/system/sensationRecommend")
public class SensationRecommendController extends BaseController {

    @Reference(version = "1.0.0")
    IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected ISensationRecommendService sensationRecommendService;

    /**
     * 红人推荐列表 -217
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/sensationRecommendList")
    public ResponseMessage sensationRecommendList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(hrdhCacheService,map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            JSONArray ja = new JSONArray();

            List<SensationRecommendVm> sensationRecommendVmList = sensationRecommendService.findSensationRecommendVmList();

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for(SensationRecommendVm s : sensationRecommendVmList){

                JSONObject obj = new JSONObject();

                //红人推荐ID
                obj.put("sensationRecommendId", s.getId());
                //红人ID
                obj.put("sensationId", s.getSensationId());
                //手机号码
                obj.put("phone", s.getPhone());
                //昵称
                obj.put("nickName", s.getNickName());
                //头像
                obj.put("headPic", s.getHeadPic());
                //操作人名称
                obj.put("modifySysUserName", s.getModifySysUserName());
                //操作时间
                obj.put("modifyDate", sf.format(s.getModifyDate()));

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

    /**
     * 新增和编辑红人推荐 -218
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/addSensationRecommend")
    public ResponseMessage addSensationRecommend(@RequestBody Map<String, String> map) {

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

            //红人推荐ID
            String sensationRecommendIdStr = map.get("sensationRecommendId");
            Long sensationRecommendId = null;
            if (StringUtils.isNotBlank(sensationRecommendIdStr)){
                sensationRecommendId = Long.parseLong(sensationRecommendIdStr);
            }

            //红人ID
            String sensationId = map.get("sensationId");
            if(StringUtils.isBlank(sensationId)){
                returnMsg.setResult("218011");
                returnMsg.setMessage(ResultCode.result_218011);
                return returnMsg;
            }

            SensationRecommend sensationRecommend = null;

            Date date = new Date();

            if(sensationRecommendId == null){
                sensationRecommend = new SensationRecommend();

                //状态：1-正常、2-删除
                sensationRecommend.setStatus(1);
                //创建人
                sensationRecommend.setCreateSysUser(userId);
                //创建时间
                sensationRecommend.setCreateDate(date);

            }else {
                sensationRecommend = sensationRecommendService.get(sensationRecommendId);
            }

            //红人ID
            sensationRecommend.setSensationId(Long.parseLong(sensationId));
            //修改人
            sensationRecommend.setModifySysUser(userId);
            //修改时间
            sensationRecommend.setModifyDate(date);

            sensationRecommendService.save(sensationRecommend);

            returnMsg.setResult("1");
            returnMsg.setMessage(ResultCode.result_10);

        } catch (Exception e) {

            e.printStackTrace();
            returnMsg.setResult("100000");
            returnMsg.setMessage(ResultCode.result_100000);
        }

        return returnMsg;
    }

    /**
     * 删除红人推荐 -219
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateStatus")
    public ResponseMessage updateStatus(@RequestBody Map<String, String> map) {

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

            //检测配置ID不能为空
            String sensationRecommendIds = map.get("sensationRecommendIds");
            if(StringUtils.isBlank(sensationRecommendIds)){
                returnMsg.setResult("219010");
                returnMsg.setMessage(ResultCode.result_219010);
                return returnMsg;
            }

            //当前时间
            Date date = new Date();

            sensationRecommendService.updateStatus(sensationRecommendIds, 2, userId, date);

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
