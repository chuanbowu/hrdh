package com.flym.hrdh.controller.system.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.common.TakeDeliveryRecommendVm;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.api.service.common.ITakeDeliveryRecommendService;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.common.TakeDeliveryRecommend;
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
 * <p>Description:拿货推荐商口接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-24</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/system/takeDeliveryRecommend")
public class TakeDeliveryRecommendController extends BaseController {

    @Reference(version = "1.0.0")
    IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected ITakeDeliveryRecommendService takeDeliveryRecommendService;

    /**
     * 拿货推荐商品列表 -223
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/takeDeliveryRecommendList")
    public ResponseMessage takeDeliveryRecommendList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(hrdhCacheService,map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            JSONArray ja = new JSONArray();

            List<TakeDeliveryRecommendVm> takeDeliveryRecommendVmList = takeDeliveryRecommendService.findTakeDeliveryRecommendVmTotalList();

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for(TakeDeliveryRecommendVm t : takeDeliveryRecommendVmList){

                JSONObject obj = new JSONObject();

                //拿货推荐商品ID
                obj.put("takeDeliveryRecommendId", t.getId());
                //商品ID
                obj.put("goodsId", t.getGoodsId());
                //商品标题
                obj.put("businessTitle", t.getBusinessTitle());
                //主图
                obj.put("mainPic", t.getMainPic());
                //排序号
                obj.put("sort", t.getSort());
                //状态：1-正常、2-禁用、3-删除
                obj.put("status", t.getStatus());
                //操作人名称
                obj.put("modifySysUserName", t.getModifySysUserName());
                //操作时间
                obj.put("modifyDate", sf.format(t.getModifyDate()));

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
     * 新增和编辑拿货推荐商品 -224
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/addTakeDeliveryRecommend")
    public ResponseMessage addTakeDeliveryRecommend(@RequestBody Map<String, String> map) {

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

            //拿货推荐商品ID
            String takeDeliveryRecommendIdStr = map.get("takeDeliveryRecommendId");
            Long takeDeliveryRecommendId = null;
            if (StringUtils.isNotBlank(takeDeliveryRecommendIdStr)){
                takeDeliveryRecommendId = Long.parseLong(takeDeliveryRecommendIdStr);
            }

            //商品ID
            String goodsId = map.get("goodsId");
            if(StringUtils.isBlank(goodsId)){
                returnMsg.setResult("224011");
                returnMsg.setMessage(ResultCode.result_224011);
                return returnMsg;
            }

            //排序号
            String sort = map.get("sort");
            if(StringUtils.isBlank(sort)){
                returnMsg.setResult("224021");
                returnMsg.setMessage(ResultCode.result_224021);
                return returnMsg;
            }

            TakeDeliveryRecommend takeDeliveryRecommend = null;

            Date date = new Date();

            if(takeDeliveryRecommendId == null){
                takeDeliveryRecommend = new TakeDeliveryRecommend();

                //状态：1-正常、2-禁用、3-删除
                takeDeliveryRecommend.setStatus(1);
                //创建人
                takeDeliveryRecommend.setCreateSysUser(userId);
                //创建时间
                takeDeliveryRecommend.setCreateDate(date);

            }else {
                takeDeliveryRecommend = takeDeliveryRecommendService.get(takeDeliveryRecommendId);
            }

            //商品ID
            takeDeliveryRecommend.setGoodsId(Long.parseLong(goodsId));
            //排序号
            takeDeliveryRecommend.setSort(Integer.parseInt(sort));
            //修改人
            takeDeliveryRecommend.setModifySysUser(userId);
            //修改时间
            takeDeliveryRecommend.setModifyDate(date);

            takeDeliveryRecommendService.save(takeDeliveryRecommend);

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
     * 修改拿货推荐商品状态 -225
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

            //检测拿货推荐商品ID不能为空
            String takeDeliveryRecommendIds = map.get("takeDeliveryRecommendIds");
            if(StringUtils.isBlank(takeDeliveryRecommendIds)){
                returnMsg.setResult("225010");
                returnMsg.setMessage(ResultCode.result_225010);
                return returnMsg;
            }

            //检测操作类型不能为空：1-正常、2-禁用、3-删除
            String statusStr= map.get("status");
            if(StringUtils.isBlank(statusStr)){
                returnMsg.setResult("225020");
                returnMsg.setMessage(ResultCode.result_225020);
                return returnMsg;
            }

            int status = Integer.parseInt(statusStr);

            //当前时间
            Date date = new Date();

            takeDeliveryRecommendService.updateStatus(takeDeliveryRecommendIds, status, userId, date);

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
