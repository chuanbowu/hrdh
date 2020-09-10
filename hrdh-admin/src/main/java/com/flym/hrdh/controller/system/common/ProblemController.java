package com.flym.hrdh.controller.system.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.common.ProblemVm;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.api.service.common.IProblemService;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.common.Problem;
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
 * <p>Description:常见问题配置接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-23</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/system/problem")
public class ProblemController extends BaseController {

    @Reference(version = "1.0.0")
    IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected IProblemService problemService;

    /**
     * 常见问题配置列表 -214
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/problemList")
    public ResponseMessage problemList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(hrdhCacheService,map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            JSONArray ja = new JSONArray();

            List<ProblemVm> problemList = problemService.findProblemVmList();

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for(ProblemVm p : problemList){

                JSONObject obj = new JSONObject();


                //ID
                obj.put("problemId", p.getId());
                //问内容
                obj.put("askContent", p.getAskContent());
                //答内容
                obj.put("answerContent", p.getAnswerContent());
                //排序号
                obj.put("sort", p.getSort());
                //状态：1-正常、2-禁用、3-删除
                obj.put("status", p.getStatus());
                //操作人名称
                obj.put("modifySysUserName", p.getModifySysUserName());
                //操作时间
                obj.put("modifyDate", sf.format(p.getModifyDate()));

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
     * 新增和编辑轮常见问题配置 -215
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/addProblem")
    public ResponseMessage addProblem(@RequestBody Map<String, String> map) {

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

            //配置ID
            String problemIdStr = map.get("problemId");
            Long problemId = null;
            if (StringUtils.isNotBlank(problemIdStr)){
                problemId = Long.parseLong(problemIdStr);
            }

            //问内容
            String askContent = map.get("askContent");
            if(StringUtils.isBlank(askContent)){
                returnMsg.setResult("21501");
                returnMsg.setMessage(ResultCode.result_215011);
                return returnMsg;
            }

            //答内容
            String answerContent = map.get("answerContent");
            if(StringUtils.isBlank(answerContent)){
                returnMsg.setResult("215021");
                returnMsg.setMessage(ResultCode.result_215021);
                return returnMsg;
            }

            //排序号
            String sort = map.get("sort");
            if(StringUtils.isBlank(sort)){
                returnMsg.setResult("215031");
                returnMsg.setMessage(ResultCode.result_215031);
                return returnMsg;
            }

            Problem problem = null;

            Date date = new Date();

            if(problemId == null){
                problem = new Problem();

                //状态：1-正常、2-禁用、3-删除
                problem.setStatus(1);
                //创建人
                problem.setCreateSysUser(userId);
                //创建时间
                problem.setCreateDate(date);

            }else {
                problem = problemService.get(problemId);
            }

            //问内容
            problem.setAskContent(askContent);
            //答内容
            problem.setAnswerContent(answerContent);
            //排序号
            problem.setSort(Integer.parseInt(sort));
            //修改人
            problem.setModifySysUser(userId);
            //修改时间
            problem.setModifyDate(date);

            problemService.save(problem);

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
     * 修改常见问题配置状态 -216
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
            String problemIds = map.get("problemIds");
            if(StringUtils.isBlank(problemIds)){
                returnMsg.setResult("216010");
                returnMsg.setMessage(ResultCode.result_216010);
                return returnMsg;
            }

            //检测操作类型不能为空：1-正常、2-禁用、3-删除
            String statusStr= map.get("status");
            if(StringUtils.isBlank(statusStr)){
                returnMsg.setResult("216020");
                returnMsg.setMessage(ResultCode.result_216020);
                return returnMsg;
            }

            int status = Integer.parseInt(statusStr);

            //当前时间
            Date date = new Date();

            problemService.updateStatus(problemIds, status, userId, date);

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
