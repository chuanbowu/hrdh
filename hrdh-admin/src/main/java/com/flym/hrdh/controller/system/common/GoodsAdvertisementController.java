package com.flym.hrdh.controller.system.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.common.GoodsAdvertisementVm;
import com.flym.hrdh.api.service.common.IGoodsAdvertisementService;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.common.GoodsAdvertisement;
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
 * <p>Description:商品广告接口管理</p>
 * <p>Copyright: Copyright (c) 2020-06-22</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/system/goodsAdvertisement")
public class GoodsAdvertisementController extends BaseController {

    @Reference(version = "1.0.0")
    IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected IGoodsAdvertisementService goodsAdvertisementService;

    /**
     * 商品广告列表 -233
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/goodsAdvertisementList")
    public ResponseMessage goodsAdvertisementList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(hrdhCacheService,map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            JSONArray ja = new JSONArray();

            List<GoodsAdvertisementVm> advertisementVmList = goodsAdvertisementService.findGoodsAdvertisementVmTotalList();

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for(GoodsAdvertisementVm a : advertisementVmList){

                JSONObject obj = new JSONObject();

                //ID
                obj.put("goodsAdvertisementId", a.getId());
                //广告图
                obj.put("advertisementPic", a.getAdvertisementPic());
                //商品ID
                obj.put("goodsId", a.getGoodsId());
                //商品标题
                obj.put("businessTitle", a.getBusinessTitle());
                //商品主图
                obj.put("mainPic", a.getMainPic());
                //类型：1-返佣商品、2-拿货商品
                obj.put("type", a.getType());
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
     * 编辑商品广告 -234
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateGoodsAdvertisement")
    public ResponseMessage updateGoodsAdvertisement(@RequestBody Map<String, String> map) {

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
            String goodsAdvertisementIdStr = map.get("goodsAdvertisementId");
            if (StringUtils.isBlank(goodsAdvertisementIdStr)){
                returnMsg.setResult("234010");
                returnMsg.setMessage(ResultCode.result_234010);
                return returnMsg;
            }

            Long advertisementId =  Long.parseLong(goodsAdvertisementIdStr);

            //广告图
            String advertisementPic = map.get("advertisementPic");
            if (StringUtils.isBlank(advertisementPic)){
                returnMsg.setResult("234021");
                returnMsg.setMessage(ResultCode.result_234021);
                return returnMsg;
            }

            //商品ID
            String goodsId = map.get("goodsId");
            if(StringUtils.isBlank(goodsId)){
                returnMsg.setResult("234031");
                returnMsg.setMessage(ResultCode.result_234031);
                return returnMsg;
            }

            Date date = new Date();

            GoodsAdvertisement goodsAdvertisement = goodsAdvertisementService.get(advertisementId);

            //广告图
            goodsAdvertisement.setAdvertisementPic(advertisementPic);
            //商品ID
            goodsAdvertisement.setGoodsId(Long.parseLong(goodsId));
            //修改人
            goodsAdvertisement.setModifySysUser(userId);
            //修改时间
            goodsAdvertisement.setModifyDate(date);

            goodsAdvertisementService.save(goodsAdvertisement);

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
