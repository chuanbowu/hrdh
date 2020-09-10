package com.flym.hrdh.controller.system.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.common.AgentVm;
import com.flym.hrdh.api.service.common.IAgentService;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.common.Agent;
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
 * <p>Description:经纪人接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-23</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/system/agent")
public class AgentController extends BaseController {

    @Reference(version = "1.0.0")
    IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected IAgentService agentService;

    /**
     * 经纪人列表 -208
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/agentList")
    public ResponseMessage agentList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(hrdhCacheService,map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            JSONArray ja = new JSONArray();

            List<AgentVm> advertList = agentService.findAgentVmList();

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for(AgentVm a : advertList){

                JSONObject obj = new JSONObject();

                //经纪人ID
                obj.put("agentId", a.getId());
                //经纪人姓名
                obj.put("agentName", a.getAgentName());
                //经纪人图片
                obj.put("agentPic", a.getAgentPic());
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
     * 新增和编辑经纪人 -209
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/addAgent")
    public ResponseMessage addAgent(@RequestBody Map<String, String> map) {

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

            //经纪人ID
            String agentIdStr = map.get("agentId");
            Long agentId = null;
            if (StringUtils.isNotBlank(agentIdStr)){
                agentId = Long.parseLong(agentIdStr);
            }

            //经纪人姓名
            String agentName = map.get("agentName");
            if(StringUtils.isBlank(agentName)){
                returnMsg.setResult("20901");
                returnMsg.setMessage(ResultCode.result_209011);
                return returnMsg;
            }

            //经纪人图片
            String agentPic = map.get("agentPic");
            if(StringUtils.isBlank(agentPic)){
                returnMsg.setResult("209021");
                returnMsg.setMessage(ResultCode.result_209021);
                return returnMsg;
            }

            Agent agent = null;

            Date date = new Date();

            if(agentId == null){
                agent = new Agent();

                //状态：1-正常、2-禁用、3-删除
                agent.setStatus(1);
                //创建人
                agent.setCreateSysUser(userId);
                //创建时间
                agent.setCreateDate(date);

            }else {
                agent = agentService.get(agentId);
            }

            //经纪人姓名
            agent.setAgentName(agentName);
            //经纪人图片
            agent.setAgentPic(agentPic);
            //修改人
            agent.setModifySysUser(userId);
            //修改时间
            agent.setModifyDate(date);

            agentService.save(agent);

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
     * 修改经纪人状态 -210
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

            //检测经纪人ID不能为空
            String agentIds = map.get("agentIds");
            if(StringUtils.isBlank(agentIds)){
                returnMsg.setResult("210010");
                returnMsg.setMessage(ResultCode.result_210010);
                return returnMsg;
            }

            //检测操作类型不能为空：1-正常、2-禁用、3-删除
            String statusStr= map.get("status");
            if(StringUtils.isBlank(statusStr)){
                returnMsg.setResult("210020");
                returnMsg.setMessage(ResultCode.result_210020);
                return returnMsg;
            }

            int status = Integer.parseInt(statusStr);

            //当前时间
            Date date = new Date();

            agentService.updateStatus(agentIds, status, userId, date);

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
