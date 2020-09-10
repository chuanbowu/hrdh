package com.flym.hrdh.controller.system.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flym.hrdh.api.model.common.CustomerVm;
import com.flym.hrdh.api.service.common.ICustomerService;
import com.flym.hrdh.api.service.common.IHrdhCacheService;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import com.flym.hrdh.pojo.common.Customer;
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
 * <p>Description:客服接口管理</p>
 * <p>Copyright: Copyright (c) 2020-05-23</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/system/customer")
public class CustomerController extends BaseController {

    @Reference(version = "1.0.0")
    IHrdhCacheService hrdhCacheService;

    @Reference(version = "1.0.0")
    protected ICustomerService customerService;

    /**
     * 客服列表 -202
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/customerList")
    public ResponseMessage customerList(@RequestBody Map<String, String> map) {

        ResponseMessage returnMsg = null;

        try {

            returnMsg = super.checkNotLogin(hrdhCacheService,map);

            //如果result为空则直接返回
            if (StringUtils.isNotBlank(returnMsg.result)) {
                return returnMsg;
            }

            JSONArray ja = new JSONArray();

            List<CustomerVm> customerVmList = customerService.findCustomerVmList();

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for(CustomerVm c : customerVmList){

                JSONObject obj = new JSONObject();

                //ID
                obj.put("customerId", c.getId());
                //客服昵称
                obj.put("customerName", c.getCustomerName());
                //客服图片
                obj.put("customerPic", c.getCustomerPic());
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
     * 新增和编辑客服 -203
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/addCustomer")
    public ResponseMessage addCustomer(@RequestBody Map<String, String> map) {

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

            //客服ID
            String customerIdStr = map.get("customerId");
            Long customerId = null;
            if (StringUtils.isNotBlank(customerIdStr)){
                customerId = Long.parseLong(customerIdStr);
            }

            //客服昵称
            String customerName = map.get("customerName");
            if(StringUtils.isBlank(customerName)){
                returnMsg.setResult("203011");
                returnMsg.setMessage(ResultCode.result_203011);
                return returnMsg;
            }

            //客服图片
            String customerPic = map.get("customerPic");
            if(StringUtils.isBlank(customerPic)){
                returnMsg.setResult("203021");
                returnMsg.setMessage(ResultCode.result_203021);
                return returnMsg;
            }

            Customer customer = null;

            Date date = new Date();

            if(customerId == null){
                customer = new Customer();

                //状态：1-正常、2-禁用、3-删除
                customer.setStatus(1);
                //创建人
                customer.setCreateSysUser(userId);
                //创建时间
                customer.setCreateDate(date);

            }else {
                customer = customerService.get(customerId);
            }

            //客服昵称
            customer.setCustomerName(customerName);
            //客服图片
            customer.setCustomerPic(customerPic);
            //修改人
            customer.setModifySysUser(userId);
            //修改时间
            customer.setModifyDate(date);

            customerService.save(customer);

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
     * 修改客服状态 -204
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

            //检测客服ID不能为空
            String customerIds = map.get("customerIds");
            if(StringUtils.isBlank(customerIds)){
                returnMsg.setResult("204010");
                returnMsg.setMessage(ResultCode.result_204010);
                return returnMsg;
            }

            //检测操作类型不能为空：1-正常、2-禁用、3-删除
            String statusStr= map.get("status");
            if(StringUtils.isBlank(statusStr)){
                returnMsg.setResult("204020");
                returnMsg.setMessage(ResultCode.result_204020);
                return returnMsg;
            }

            int status = Integer.parseInt(statusStr);

            //当前时间
            Date date = new Date();

            customerService.updateStatus(customerIds, status, userId, date);

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
