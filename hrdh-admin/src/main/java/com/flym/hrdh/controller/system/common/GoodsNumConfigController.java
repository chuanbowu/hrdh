package com.flym.hrdh.controller.system.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.service.common.IGoodsNumConfigService;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.common.GoodsNumConfig;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:商品数量配置管理</p>
 * <p>Copyright: Copyright (c) 2020-07-18</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/system/goodsNumConfig")
public class GoodsNumConfigController extends BaseController {

    @Reference(version = "1.0.0")
    IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected IGoodsNumConfigService goodsNumConfigService;

    /**
     * 商品数量配置 -235
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/goodsNumConfigList")
    public ResponseMessage goodsNumConfigList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(hrdhCacheService,map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            GoodsNumConfig goodsNumConfig = goodsNumConfigService.getGoodsNumConfig();

            JSONObject jb = new JSONObject();

            //初级数量
            jb.put("primaryNum", goodsNumConfig.getPrimaryNum());
            //中级数量
            jb.put("intermediateNum", goodsNumConfig.getIntermediateNum());
            //高级数量
            jb.put("seniorNum", goodsNumConfig.getSeniorNum());

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
     * 编辑商品数量配置-236
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/addGoodsNumConfig")
    public ResponseMessage addGoodsNumConfig(@RequestBody Map<String, String> map) {

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

            //初级数量
            String primaryNum = map.get("primaryNum");
            if(StringUtils.isBlank(primaryNum)){
                returnMsg.setResult("236011");
                returnMsg.setMessage(ResultCode.result_236011);
                return returnMsg;
            }

            //中级数量
            String intermediateNum = map.get("intermediateNum");
            if(StringUtils.isBlank(intermediateNum)){
                returnMsg.setResult("236021");
                returnMsg.setMessage(ResultCode.result_236021);
                return returnMsg;
            }

            //高级数量
            String seniorNum = map.get("seniorNum");
            if(StringUtils.isBlank(seniorNum)){
                returnMsg.setResult("236031");
                returnMsg.setMessage(ResultCode.result_236031);
                return returnMsg;
            }

            GoodsNumConfig goodsNumConfig = goodsNumConfigService.getGoodsNumConfig();

            //初级数量
            goodsNumConfig.setPrimaryNum(Integer.parseInt(primaryNum));
            //中级数量
            goodsNumConfig.setIntermediateNum(Integer.parseInt(intermediateNum));
            //高级数量
            goodsNumConfig.setSeniorNum(Integer.parseInt(seniorNum));
            //修改人
            goodsNumConfig.setModifySysUser(userId);
            //修改时间
            goodsNumConfig.setModifyDate(new Date());

            goodsNumConfigService.save(goodsNumConfig);

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
