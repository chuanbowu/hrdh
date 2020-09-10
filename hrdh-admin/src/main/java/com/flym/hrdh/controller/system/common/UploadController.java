package com.flym.hrdh.controller.system.common;

import com.flym.hrdh.config.HrdhAdminConfig;
import com.flym.hrdh.config.ResponseMessage;
import com.flym.hrdh.config.ResultCode;
import com.flym.hrdh.controller.BaseController;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.UUID;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:上传附件管理</p>
 * <p>Copyright: Copyright (c) 2019-01-27</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@Controller
@RequestMapping(value = "/system/currency")
public class UploadController extends BaseController {

    /**
     * 图片上传接口 -201
     * @param multipartFile
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/uploadImage")
    public ResponseMessage uploadImage(@RequestParam("file")MultipartFile multipartFile){

        ResponseMessage respMsg= new ResponseMessage();
        try{
            String allFName = "";
            JSONObject respJson = new JSONObject();
            if (multipartFile== null) {
                return null;
            }
            String originalFilename =multipartFile.getOriginalFilename();
            String contentType = multipartFile.getContentType().toLowerCase();

            String uploadPath = HrdhAdminConfig.file_local_url;

            if (!contentType.startsWith("image")) {
                respMsg.setResult("201011");
                respMsg.setMessage(ResultCode.result_201011);
                return respMsg;
            }
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            /*if(!HrdhAdminConfig.upload_image_limit_format.contains(suffix)){
                respMsg.setResult("201011");
                respMsg.setMessage(ResultCode.result_201011);
                return respMsg;
            }*/

            Calendar c = Calendar.getInstance();
            uploadPath += "/images/" + c.get(Calendar.YEAR) + "/" + c.get(Calendar.MONTH) + "/"+ c.get(Calendar.DAY_OF_MONTH);

            //文件保存目录
            String filePath =  uploadPath;

            File file = new File(filePath);
            //检查文件对应目录是否存在
            if (!file.exists()){
                file.mkdirs();
            }

            // 创建一个随机的文件ID
            String uuid = UUID.randomUUID().toString();

            String fileName = originalFilename;
            //文件后缀名
            String extendName = fileName.substring(fileName.lastIndexOf(".") + 1);

            allFName = filePath + "/" + uuid + "." + extendName;// 完整的文件名

            BufferedOutputStream bout = new BufferedOutputStream( new FileOutputStream(allFName));

            BufferedInputStream bin = new BufferedInputStream(multipartFile.getInputStream());

            FileUtils.copyInputStreamToFile(bin, new File(allFName));

            //文件路径
            respJson.put("fileUrl", allFName.replaceAll(HrdhAdminConfig.file_base_replace_url, ""));

            respMsg.setDatas(respJson);
            respMsg.setResult("1");
            respMsg.setMessage(ResultCode.result_7);
        }catch (Exception e) {
            e.printStackTrace();
            respMsg.setMessage(ResultCode.result_100000);
            respMsg.setResult("100000");
        }
        return respMsg;
    }
}
