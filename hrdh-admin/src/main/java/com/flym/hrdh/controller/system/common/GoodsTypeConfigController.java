package com.flym.hrdh.controller.system.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.common.GoodsTypeConfigVm;
import com.flym.hrdh.api.service.common.IGoodsTypeConfigService;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.common.GoodsTypeConfig;
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
 * <p>Description:商品分类配置管理</p>
 * <p>Copyright: Copyright (c) 2020-07-21</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/system/goodsTypeConfig")
public class GoodsTypeConfigController extends BaseController {

    @Reference(version = "1.0.0")
    IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected IGoodsTypeConfigService goodsTypeConfigService;

    /**
     * 商品分类配置 -237
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/goodsTypeConfigList")
    public ResponseMessage goodsTypeConfigList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(hrdhCacheService,map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            JSONArray ja = new JSONArray();

            List<GoodsTypeConfigVm> finGoodsNumConfigList = goodsTypeConfigService.finGoodsNumConfigList();

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for(GoodsTypeConfigVm g : finGoodsNumConfigList){

                JSONObject obj = new JSONObject();

                //ID
                obj.put("goodsTypeConfigId", g.getId());
                //分类名称
                obj.put("typeName", g.getTypeName());
                //排序号
                obj.put("sort", g.getSort());
                //修改人名称
                obj.put("modifySysUserName", g.getModifySysUserName());
                //修改时间
                obj.put("modifyDate", sf.format(g.getModifyDate()));

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
     * 新增和编辑分类配置  -238
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/addGoodsTypeConfig")
    public ResponseMessage addGoodsTypeConfig(@RequestBody Map<String, String> map) {

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

            //分类ID
            String goodsTypeConfigIdStr = map.get("goodsTypeConfigId");
            Long goodsTypeConfigId = null;
            if (StringUtils.isNotBlank(goodsTypeConfigIdStr)){
                goodsTypeConfigId = Long.parseLong(goodsTypeConfigIdStr);
            }

            //分类名称
            String typeName = map.get("typeName");
            if(StringUtils.isBlank(typeName)){
                returnMsg.setResult("238011");
                returnMsg.setMessage(ResultCode.result_238011);
                return returnMsg;
            }

            //排序号
            String sort = map.get("sort");
            if(StringUtils.isBlank(sort)){
                returnMsg.setResult("238021");
                returnMsg.setMessage(ResultCode.result_238021);
                return returnMsg;
            }

            GoodsTypeConfig goodsTypeConfig = null;

            Date date = new Date();

            if(goodsTypeConfigId == null){
                goodsTypeConfig = new GoodsTypeConfig();

                //状态：1-正常、2-删除
                goodsTypeConfig.setStatus(1);
                //创建人
                goodsTypeConfig.setCreateSysUser(userId);
                //创建时间
                goodsTypeConfig.setCreateDate(date);

            }else {
                goodsTypeConfig = goodsTypeConfigService.get(goodsTypeConfigId);
            }

            //分类名称
            goodsTypeConfig.setTypeName(typeName);
            //排序号
            goodsTypeConfig.setSort(Integer.parseInt(sort));
            //修改人
            goodsTypeConfig.setModifySysUser(userId);
            //修改时间
            goodsTypeConfig.setModifyDate(date);

            goodsTypeConfigService.save(goodsTypeConfig);

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
     * 修改分类配置状态 -239
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

            //检测分类ID不能为空
            String goodsTypeConfigId = map.get("goodsTypeConfigIds");
            if(StringUtils.isBlank(goodsTypeConfigId)){
                returnMsg.setResult("239010");
                returnMsg.setMessage(ResultCode.result_239010);
                return returnMsg;
            }

            //当前时间
            Date date = new Date();

            goodsTypeConfigService.updateStatus(goodsTypeConfigId, 2, userId, date);

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
