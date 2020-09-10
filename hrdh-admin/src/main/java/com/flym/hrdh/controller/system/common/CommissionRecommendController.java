package com.flym.hrdh.controller.system.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.common.CommissionRecommendVm;
import com.flym.hrdh.api.service.common.ICommissionRecommendService;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.config.CommonConfig;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.common.CommissionRecommend;
import com.flym.hrdh.pojo.common.Problem;
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
 * <p>Description:返佣推荐商品接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-24</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/system/commissionRecommend")
public class CommissionRecommendController extends BaseController {

    @Reference(version = "1.0.0")
    IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected ICommissionRecommendService commissionRecommendService;

    /**
     * 返佣推荐商品列表 -220
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/commissionRecommendList")
    public ResponseMessage commissionRecommendList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(hrdhCacheService,map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            JSONArray ja = new JSONArray();

            List<CommissionRecommendVm> commissionRecommendVmList = commissionRecommendService.findCommissionRecommendVmTotalList();

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for(CommissionRecommendVm c : commissionRecommendVmList){

                JSONObject obj = new JSONObject();

                //返佣推荐商品ID
                obj.put("commissionRecommendId", c.getId());
                //商品ID
                obj.put("goodsId", c.getGoodsId());
                //商品标题
                obj.put("businessTitle", c.getBusinessTitle());
                //主图
                obj.put("mainPic", c.getMainPic());
                //排序号
                obj.put("sort", c.getSort());
                //状态：1-正常、2-禁用、3-删除
                obj.put("status", c.getStatus());
                //操作人名称
                obj.put("modifySysUserName", c.getModifySysUserName());
                //操作时间
                obj.put("modifyDate", sf.format(c.getModifyDate()));

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
     * 新增和编辑返佣推荐商品 -221
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/addCommissionRecommend")
    public ResponseMessage addCommissionRecommend(@RequestBody Map<String, String> map) {

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

            //返佣推荐商品ID
            String commissionRecommendIdStr = map.get("commissionRecommendId");
            Long commissionRecommendId = null;
            if (StringUtils.isNotBlank(commissionRecommendIdStr)){
                commissionRecommendId = Long.parseLong(commissionRecommendIdStr);
            }

            //商品ID
            String goodsId = map.get("goodsId");
            if(StringUtils.isBlank(goodsId)){
                returnMsg.setResult("221011");
                returnMsg.setMessage(ResultCode.result_221011);
                return returnMsg;
            }

            //排序号
            String sort = map.get("sort");
            if(StringUtils.isBlank(sort)){
                returnMsg.setResult("221021");
                returnMsg.setMessage(ResultCode.result_221021);
                return returnMsg;
            }

            CommissionRecommend commissionRecommend = null;

            Date date = new Date();

            if(commissionRecommendId == null){
                commissionRecommend = new CommissionRecommend();

                //状态：1-正常、2-禁用、3-删除
                commissionRecommend.setStatus(1);
                //创建人
                commissionRecommend.setCreateSysUser(userId);
                //创建时间
                commissionRecommend.setCreateDate(date);

            }else {
                commissionRecommend = commissionRecommendService.get(commissionRecommendId);
            }

            //商品ID
            commissionRecommend.setGoodsId(Long.parseLong(goodsId));
            //排序号
            commissionRecommend.setSort(Integer.parseInt(sort));
            //修改人
            commissionRecommend.setModifySysUser(userId);
            //修改时间
            commissionRecommend.setModifyDate(date);

            commissionRecommendService.save(commissionRecommend);

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
     * 修改返佣推荐商品状态 -222
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

            //检测返佣推荐商品ID不能为空
            String commissionRecommendIds = map.get("commissionRecommendIds");
            if(StringUtils.isBlank(commissionRecommendIds)){
                returnMsg.setResult("222010");
                returnMsg.setMessage(ResultCode.result_222010);
                return returnMsg;
            }

            //检测操作类型不能为空：1-正常、2-禁用、3-删除
            String statusStr= map.get("status");
            if(StringUtils.isBlank(statusStr)){
                returnMsg.setResult("222020");
                returnMsg.setMessage(ResultCode.result_222020);
                return returnMsg;
            }

            int status = Integer.parseInt(statusStr);

            //当前时间
            Date date = new Date();

            commissionRecommendService.updateStatus(commissionRecommendIds, status, userId, date);

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
