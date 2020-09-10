package com.flym.hrdh.controller.system.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.common.BottomCommissionRecommendVm;
import com.flym.hrdh.api.service.common.IBottomCommissionRecommendService;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.common.BottomCommissionRecommend;
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
 * <p>Description:底部返佣推荐商口接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-24</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/system/bottomCommissionRecommend")
public class BottomCommissionRecommendController extends BaseController {

    @Reference(version = "1.0.0")
    IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected IBottomCommissionRecommendService bottomCommissionRecommendService;

    /**
     * 底部返佣推荐商品列表 -226
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/bottomCommissionRecommendList")
    public ResponseMessage bottomCommissionRecommendList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(hrdhCacheService, map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            JSONArray ja = new JSONArray();

            List<BottomCommissionRecommendVm> bottomCommissionRecommendVmList = bottomCommissionRecommendService.findBottomCommissionRecommendVmTotalList();

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for(BottomCommissionRecommendVm b : bottomCommissionRecommendVmList){

                JSONObject obj = new JSONObject();

                //底部返佣推荐商品ID
                obj.put("bottomCommissionRecommendId", b.getId());
                //商品ID
                obj.put("goodsId", b.getGoodsId());
                //商品标题
                obj.put("businessTitle", b.getBusinessTitle());
                //主图
                obj.put("mainPic", b.getMainPic());
                //排序号
                obj.put("sort", b.getSort());
                //状态：1-正常、2-禁用、3-删除
                obj.put("status", b.getStatus());
                //操作人名称
                obj.put("modifySysUserName", b.getModifySysUserName());
                //操作时间
                obj.put("modifyDate", sf.format(b.getModifyDate()));

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
     * 新增和编辑底部返佣推荐商品 -227
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/addBottomCommissionRecommend")
    public ResponseMessage addBottomCommissionRecommend(@RequestBody Map<String, String> map) {

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

            //底部返佣推荐商品ID
            String bottomCommissionRecommendIdStr = map.get("bottomCommissionRecommendId");
            Long bottomCommissionRecommendId = null;
            if (StringUtils.isNotBlank(bottomCommissionRecommendIdStr)){
                bottomCommissionRecommendId = Long.parseLong(bottomCommissionRecommendIdStr);
            }

            //商品ID
            String goodsId = map.get("goodsId");
            if(StringUtils.isBlank(goodsId)){
                returnMsg.setResult("227011");
                returnMsg.setMessage(ResultCode.result_227011);
                return returnMsg;
            }

            //排序号
            String sort = map.get("sort");
            if(StringUtils.isBlank(sort)){
                returnMsg.setResult("227021");
                returnMsg.setMessage(ResultCode.result_227021);
                return returnMsg;
            }

            BottomCommissionRecommend bottomCommissionRecommend = null;

            Date date = new Date();

            if(bottomCommissionRecommendId == null){
                bottomCommissionRecommend = new BottomCommissionRecommend();

                //状态：1-正常、2-禁用、3-删除
                bottomCommissionRecommend.setStatus(1);
                //创建人
                bottomCommissionRecommend.setCreateSysUser(userId);
                //创建时间
                bottomCommissionRecommend.setCreateDate(date);

            }else {
                bottomCommissionRecommend = bottomCommissionRecommendService.get(bottomCommissionRecommendId);
            }

            //商品ID
            bottomCommissionRecommend.setGoodsId(Long.parseLong(goodsId));
            //排序号
            bottomCommissionRecommend.setSort(Integer.parseInt(sort));
            //修改人
            bottomCommissionRecommend.setModifySysUser(userId);
            //修改时间
            bottomCommissionRecommend.setModifyDate(date);

            bottomCommissionRecommendService.save(bottomCommissionRecommend);

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
     * 修改底部返佣推荐商品状态 -228
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

            //检测底部返佣推荐商品ID不能为空
            String bottomCommissionRecommendIds = map.get("bottomCommissionRecommendIds");
            if(StringUtils.isBlank(bottomCommissionRecommendIds)){
                returnMsg.setResult("228010");
                returnMsg.setMessage(ResultCode.result_228010);
                return returnMsg;
            }

            //检测操作类型不能为空：1-正常、2-禁用、3-删除
            String statusStr= map.get("status");
            if(StringUtils.isBlank(statusStr)){
                returnMsg.setResult("228020");
                returnMsg.setMessage(ResultCode.result_228020);
                return returnMsg;
            }

            int status = Integer.parseInt(statusStr);

            //当前时间
            Date date = new Date();

            bottomCommissionRecommendService.updateStatus(bottomCommissionRecommendIds, status, userId, date);

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
