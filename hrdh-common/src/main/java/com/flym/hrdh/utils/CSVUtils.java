package com.flym.hrdh.utils;

import com.csvreader.CsvWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: 红人带货系统</p>
 * <p>Description:CSV表单操作工具类</p>
 * <p>Copyright: Copyright (c) 2019-03-11</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: Mr.Yuan$
 * @version $Revision: 1.0.0 $
 */
public class CSVUtils {

    /**
     * 生成excel
     *@param fileName  文件名称
     * @param headers  表头
     * @param content  内容
     * @throws IOException
     */
    public static void createNewCSV(String fileName, String[] headers, List<String[]> content, HttpServletResponse response) throws IOException {
        CsvWriter csvWriter = null;
        try{
            //设置文字格式
            response.setContentType("application/x-download;charset=GBK");

            //生成时间
            SimpleDateFormat dtf = new SimpleDateFormat("yyyyMMddHHmmSS");
            String curDateStr = dtf.format(new Date());
            //导出文件名 以时间做文件名
            fileName = fileName + "-" + curDateStr + ".csv";
            fileName = URLEncoder.encode(fileName, "utf-8");

            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

            //操作csv表单
            csvWriter = new CsvWriter(response.getOutputStream(),',', Charset.forName("GBK"));

            //设置表单
            //填写表头
            csvWriter.writeRecord(headers);
            if(content != null && content.size() > 0){
                //循环遍历加载数据
                for(String[] contents : content){
                    csvWriter.writeRecord(contents);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭操作csv表格
            if(csvWriter != null){
                csvWriter.close();
            }
        }
    }
}
