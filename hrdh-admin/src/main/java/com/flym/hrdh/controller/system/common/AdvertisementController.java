package com.flym.hrdh.controller.system.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.common.AdvertisementVm;
import com.flym.hrdh.api.service.common.IAdvertisementService;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.common.Advert;
import com.flym.hrdh.pojo.common.Advertisement;
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
 * <p>Description:广告接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-23</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/system/advertisement")
public class AdvertisementController extends BaseController {

    @Reference(version = "1.0.0")
    IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected IAdvertisementService advertisementService;

    /**
     * 广告列表 -211
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/advertisementList")
    public ResponseMessage advertisementList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(hrdhCacheService,map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            JSONArray ja = new JSONArray();

            List<AdvertisementVm> advertisementVmList = advertisementService.findAdvertisementVmTotalList();

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for(AdvertisementVm a : advertisementVmList){

                JSONObject obj = new JSONObject();

                //ID
                obj.put("advertisementId", a.getId());
                //商品ID
                obj.put("goodsId", a.getGoodsId());
                //商品标题
                obj.put("businessTitle", a.getBusinessTitle());
                //商品主图
                obj.put("mainPic", a.getMainPic());
                //类型：1-返佣商品、2-拿货商品
                obj.put("type", a.getType());
                //状态：1-正常、2-禁用、3-删除
                obj.put("status", a.getStatus());
                //操作人名称
                obj.put("modifySysUserName", a.getModifySysUserName());
                //操作时间
                obj.put("modifyDate", sf.format(a.getModifyDate()));

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
     * 新增和编辑广告 -212
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/addAdvertisement")
    public ResponseMessage addAdvertisement(@RequestBody Map<String, String> map) {

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

            //广告ID
            String advertisementIdStr = map.get("advertisementId");
            Long advertisementId = null;
            if (StringUtils.isNotBlank(advertisementIdStr)){
                advertisementId = Long.parseLong(advertisementIdStr);
            }

            //商品ID
            String goodsId = map.get("goodsId");
            if(StringUtils.isBlank(goodsId)){
                returnMsg.setResult("212011");
                returnMsg.setMessage(ResultCode.result_212011);
                return returnMsg;
            }

            //类型：1-返佣商品、2-拿货商品
            String type = map.get("type");
            if(StringUtils.isBlank(type)){
                returnMsg.setResult("212021");
                returnMsg.setMessage(ResultCode.result_212021);
                return returnMsg;
            }

            Advertisement advertisement = null;

            Date date = new Date();

            if(advertisementId == null){
                advertisement = new Advertisement();

                //状态：1-正常、2-禁用、3-删除
                advertisement.setStatus(1);
                //创建人
                advertisement.setCreateSysUser(userId);
                //创建时间
                advertisement.setCreateDate(date);

            }else {
                advertisement = advertisementService.get(advertisementId);
            }

            //商品ID
            advertisement.setGoodsId(Long.parseLong(goodsId));
            //类型：1-返佣商品、2-拿货商品
            advertisement.setType(Integer.parseInt(type));
            //修改人
            advertisement.setModifySysUser(userId);
            //修改时间
            advertisement.setModifyDate(date);

            advertisementService.save(advertisement);

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
     * 修改广告状态 -213
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

            //检测广告ID不能为空
            String advertisementIds = map.get("advertisementIds");
            if(StringUtils.isBlank(advertisementIds)){
                returnMsg.setResult("213010");
                returnMsg.setMessage(ResultCode.result_213010);
                return returnMsg;
            }

            //检测操作类型不能为空：1-正常、2-禁用、3-删除
            String statusStr= map.get("status");
            if(StringUtils.isBlank(statusStr)){
                returnMsg.setResult("213020");
                returnMsg.setMessage(ResultCode.result_213020);
                return returnMsg;
            }

            int status = Integer.parseInt(statusStr);

            //当前时间
            Date date = new Date();

            advertisementService.updateStatus(advertisementIds, status, userId, date);

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
