package com.flym.hrdh.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:HTTP请求工具类</p>
 * <p>Copyright: Copyright (c) 2018-11-27</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class HttpRequestUtil {

    /**
     * post请求
     * @param content 请求内容
     * @param url 请求链接
     */
    public static String post(String content,String url){
        BufferedReader reader =null;
        BufferedWriter writer =null;
        StringBuffer data = new StringBuffer();
        try {
            URL connect = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)connect.openConnection();
            connection.setRequestMethod("POST");
            //设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
            //http正文内，因此需要设为true, 默认情况下是false;
            connection.setDoOutput(true);
            //设置是否从httpUrlConnection读入，默认情况下是true;
            connection.setDoInput(true);
            //Post 请求不能使用缓存
            connection.setUseCaches(false);

            writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(),"UTF-8"));
            writer.write(content);
            writer.flush();

            //<===注意，实际发送请求的代码段就在这里
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            try {
                if(writer!=null){
                    writer.close();
                }
                if(reader!=null){
                    reader.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return data.toString();
    }

    /**
     * get请求
     * @param param 请求内容
     * @param url 请求链接
     */
    public static String getURL(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {// 使用finally块来关闭输入流
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * get请求
     * @param url 请求链接
     */
    public static String getURL(String url) {
        String result = "";
        BufferedReader in = null;
        try {

            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {// 使用finally块来关闭输入流
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * post请求-json
     * @param content 请求内容
     * @param url 请求链接
     */
    public static String postJson(String content,String url){
        BufferedReader reader =null;
        BufferedWriter writer =null;
        StringBuffer data = new StringBuffer();
        try {
            URL connect = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)connect.openConnection();
            connection.setRequestMethod("POST");
            //设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
            //http正文内，因此需要设为true, 默认情况下是false;
            connection.setDoOutput(true);
            //设置是否从httpUrlConnection读入，默认情况下是true;
            connection.setDoInput(true);
            //Post 请求不能使用缓存
            connection.setUseCaches(false);

            connection.setRequestProperty("Content-Type","application/json");

            writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(),"UTF-8"));
            writer.write(content);
            writer.flush();

            //<===注意，实际发送请求的代码段就在这里
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            try {
                if(writer!=null){
                    writer.close();
                }
                if(reader!=null){
                    reader.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return data.toString();
    }



    /**
     * post请求-json
     * @param str 请求内容
     * @param ur 请求链接
     */
    public static String URLPost(String ur, String str) throws IOException {

        URL url = new URL(ur);
        URLConnection rulConnection = url.openConnection();
        // urlConnection对象实际上是根据URL的请求协议(此处是http)生成的URLConnection类的子类HttpURLConnection,故此处最好将其转化为HttpURLConnection类型的对象,以便用到
        HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;
        // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true,
        // 默认情况下是false;
        httpUrlConnection.setDoOutput(true);
        // 设置是否从httpUrlConnection读入，默认情况下是true;
        httpUrlConnection.setDoInput(true);
        // Post 请求不能使用缓存
        httpUrlConnection.setUseCaches(false);
        // 设定传送的内容类型是可序列化的java对象
        // (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
//			httpUrlConnection.setRequestProperty("Content-Type", "application/soap+xml; charset=UTF-8");
        httpUrlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        // 设置连接主机超时（单位：毫秒） ,读取数据连接
        httpUrlConnection.setConnectTimeout(8000);
        httpUrlConnection.setReadTimeout(100000);
        // 设定请求的方法为"POST"，默认是GET
        httpUrlConnection.setRequestMethod("POST");
        BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(httpUrlConnection.getOutputStream(), "UTF-8"));
        // 开始发送字符串
        // String s = new String(str.getBytes(),"UTF-8");
        String s = str;
        bout.write(s);
        // 刷新对象输出流，将任何字节都写入潜在的流中（些处为ObjectOutputStream）
        bout.flush();
        // 关闭流对象。此时，不能再向对象输出流写入任何数据，先前写入的数据存在于内存缓冲区中,
        // 在调用下边的getInputStream()函数时才把准备好的http请求正式发送到服务器
        bout.close();

        int response = httpUrlConnection.getResponseCode();

        StringBuffer result = new StringBuffer();
        // 返回200说明已经请求成功
        switch (response) {
            case 200:
                try {
                    InputStream inStrm = httpUrlConnection.getInputStream();
                    Reader reader = null;
                    BufferedReader bufferedReader = null;
                    try {
                        reader = new InputStreamReader(inStrm, "UTF-8");
                        bufferedReader = new BufferedReader(reader);
                        String line = null;
                        while ((line = bufferedReader.readLine()) != null) {
                            result.append(line);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                }
        }
        return result.toString();
    }

    /**
     *
     * @param requestMethod POST、DELETE
     * @param headerName
     * @param headerValue
     * @param url
     * @return
     */
    public static String httpPostHeaderParam(String requestMethod, String headerName, String headerValue, String url){
        BufferedReader reader =null;
        BufferedWriter writer =null;
        StringBuffer data = new StringBuffer();
        try {
            URL connect = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)connect.openConnection();
            connection.setRequestMethod(requestMethod);
            //设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
            //http正文内，因此需要设为true, 默认情况下是false;
            connection.setDoOutput(true);
            //设置是否从httpUrlConnection读入，默认情况下是true;
            connection.setDoInput(true);
            //Post 请求不能使用缓存
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestProperty(headerName, headerValue);
            writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(),"UTF-8"));
            writer.write("");
            writer.flush();

            //<===注意，实际发送请求的代码段就在这里
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            try {
                if(writer!=null){
                    writer.close();
                }
                if(reader!=null){
                    reader.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return data.toString();
    }

    /**
     * POST请求
     * @param headerName
     * @param headerValue
     * @param url
     * @return
     */
    public static String httpGetHeaderParam(String headerName, String headerValue, String url){
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            connection.setRequestProperty(headerName, headerValue);
            // 建立实际的连接
            connection.connect();

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {// 使用finally块来关闭输入流
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * GET请求
     * @param urlAll:请求接口
     * @param charset:字符编码
     * @return 返回json结果
     */
    public static String get_award(String urlAll, String charset) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        // 模拟浏览器
        String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
        try {
            URL url = new URL(urlAll);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(30000);
            connection.setConnectTimeout(30000);
            connection.setRequestProperty("User-agent", userAgent);
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, charset));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
