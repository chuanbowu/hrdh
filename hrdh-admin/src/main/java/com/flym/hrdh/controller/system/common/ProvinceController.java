package com.flym.hrdh.controller.system.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.service.common.IAreaService;
import com.flym.hrdh.api.service.common.ICityService;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.api.service.common.IProvinceService;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.common.Area;
import com.flym.hrdh.pojo.common.City;
import com.flym.hrdh.pojo.common.Province;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:地址管理</p>
 * <p>Copyright: Copyright (c) 2020-05-26</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/system/address")
public class ProvinceController extends BaseController {

    @Reference(version = "1.0.0")
    protected IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected IProvinceService provinceService;

    @Reference(version = "1.0.0")
    protected ICityService cityService;

    @Reference(version = "1.0.0")
    protected IAreaService areaService;

    /**
     * 全部省份 -229
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/provinceList")
    public ResponseMessage provinceList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(hrdhCacheService, map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            JSONArray ja = new JSONArray();

            List<Province> provinceList = provinceService.findProvinceList();

            for(Province p : provinceList){

                JSONObject obj = new JSONObject();

                //省份
                obj.put("name", p.getName());
                //省份ID
                obj.put("cid", p.getCid());

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
     * 根据省份ID获取城市 -230
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/cityList")
    public ResponseMessage cityList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(hrdhCacheService, map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            //检测省份ID不能为空
            String cid = map.get("cid");
            if(StringUtils.isBlank(cid)){
                returnMsg.setResult("230010");
                returnMsg.setMessage(ResultCode.result_230010);
                return returnMsg;
            }

            JSONArray ja = new JSONArray();

            List<City> cityList = cityService.findCityList(cid);

            for(City c : cityList){

                JSONObject obj = new JSONObject();

                //城市
                obj.put("name", c.getName());
                //城市ID
                obj.put("cid", c.getCid());

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
     * 根据城市ID获取区 -231
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/areaList")
    public ResponseMessage areaList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(hrdhCacheService, map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            //检测城市ID不能为空
            String cid = map.get("cid");
            if(StringUtils.isBlank(cid)){
                returnMsg.setResult("231010");
                returnMsg.setMessage(ResultCode.result_231010);
                return returnMsg;
            }

            JSONArray ja = new JSONArray();

            List<Area> areaList = areaService.findAreaList(cid);

            for(Area a : areaList){

                JSONObject obj = new JSONObject();

                //区
                obj.put("name", a.getName());
                //区ID
                obj.put("cid", a.getCid());

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

}
