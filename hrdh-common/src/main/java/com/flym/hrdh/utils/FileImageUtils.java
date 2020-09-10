package com.flym.hrdh.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>Title:红人带货系统</p>
 * <p>Description:图片上传工具类</p>
 * <p>Copyright: Copyright (c) 2020-05-06</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class FileImageUtils {

    //打印日志
    private static Logger logger = LoggerFactory.getLogger(FileImageUtils.class);

    /**
     * 保存图片
     * @param fileInputStream
     * @param fileName
     * @param imageUrl
     * @return
     */
    public static  Map<String, Object> fileImage(InputStream fileInputStream, String fileName, String imageUrl){

        logger.debug("开始上传名称为"+fileName+"的图片");

        Map<String, Object> resultMap = new HashMap<String, Object>();

        //根据月份和日期生成文件夹
        Date date = new Date();
        int year =DateTimeUtils.getYear(date);
        int month = DateTimeUtils.getMonth(date);
        int day = DateTimeUtils.getDay(date);

        //拼装图片名称  生成随机六位数再加上时间戳
        fileName =RandomUtils.generateString(6)+System.currentTimeMillis()+fileName;


        //拼接文件夹保存地址
        String imageUrlNew = imageUrl+"/" + year +"/"+month+"/"+day+"/";

        logger.info("文件夹保存地址"+imageUrlNew);
        File file = new File(imageUrlNew);
        //绝对路径
        String absoluteUrl =  imageUrlNew+fileName;

        //返回绝对路径
        resultMap.put("absoluteUrl",absoluteUrl);

        //判断是否存在这个文件夹，如果不存在则创建
        if(!file.exists()){
            file.mkdirs();
        }

        BufferedOutputStream bufferedOutputStream = null;

        try {

            bufferedOutputStream  = new BufferedOutputStream(new BufferedOutputStream(new FileOutputStream(absoluteUrl)));
            byte[] bytes = new byte[1024];

            int len;
            logger.info("正在上传----------");
            while ((len = fileInputStream.read(bytes)) != -1){
                bufferedOutputStream.write(bytes,0,len);
            }

            bufferedOutputStream.flush();
            logger.info("上传成功----------");

            resultMap.put("status", true);
            resultMap.put("msg", absoluteUrl.split(imageUrl)[1]);

        }catch (IOException e){
            e.printStackTrace();
            resultMap.put("status", false);
            resultMap.put("msg", "上传失败!");
        }finally {

            //关流
            try {
                if(null != bufferedOutputStream){
                    bufferedOutputStream.close();
                }
                if(null !=  fileInputStream){
                    fileInputStream.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return resultMap;
    }

}
