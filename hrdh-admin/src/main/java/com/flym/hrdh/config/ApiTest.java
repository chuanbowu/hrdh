package com.flym.hrdh.config;

import com.flym.hrdh.utils.HttpRequestUtil;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.*;
import com.taobao.api.response.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class ApiTest {

    private static final String SIGN_METHOD_MD5 = "md5";
    private static final String SIGN_METHOD_HMAC = "hmac";
    private static final String CHARSET_UTF8 = "utf-8";
    private static final String CONTENT_ENCODING_GZIP = "gzip";

    // TOP服务地址，正式环境需要设置为http://gw.api.taobao.com/router/rest
    private static final String serverUrl = "https://eco.taobao.com/router/rest";
    private static final String appKey = "29490486"; // 可替换为您的沙箱环境应用的appKey
    private static final String appSecret = "3bcf458714127cc985980193a7a27313"; // 可替换为您的沙箱环境应用的appSecret
    private static final String sessionKey = "610002264c390262bb88ff8aeea065147348d4a5375b2b91800187999"; // 必须替换为沙箱账号授权得到的真实有效sessionKey

    public static void main(String[] args) throws Exception {
        //System.out.println(getSellerItem());

        //taobao.tbk.dg.material.optional( 淘宝客-推广者-物料搜索 )
        /*TaobaoClient client = new DefaultTaobaoClient(serverUrl, appKey, appSecret);
        TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
        //页大小，默认20，1~100
        req.setPageSize(20L);
        //第几页，默认：１
        req.setPageNo(1L);
        //链接形式：1：PC，2：无线，默认：１
        req.setPlatform(1L);
        //商品筛选-所在地
        req.setItemloc("") ;
        //商品筛选-查询词
        req.setQ("垃圾袋家用手提式加厚抽绳收口大号实惠装背心式黑厨房拉圾塑料袋");
        //mm_xxx_xxx_12345678三段式的最后一段数字
        req.setAdzoneId(110453250004L);

        TbkDgMaterialOptionalResponse rsp = client.execute(req);

        JSONObject js = JSONObject.fromObject(rsp.getBody());

        if(js.has("tbk_dg_material_optional_response")){

            JSONObject jss = JSONObject.fromObject(js.getString("tbk_dg_material_optional_response"));
            String total_results = jss.getString("total_results");
            System.out.println("结果总数=" + total_results);
            JSONObject jsss = JSONObject.fromObject(jss.get("result_list"));
            JSONArray jssss = JSONArray.fromObject(jsss.get("map_data"));

            for (int x=0; x < jssss.size(); x++){


                JSONObject j = JSONObject.fromObject(jssss.get(x));

                String item_id = j.getString("item_id");



                    System.out.println(j);

                    //商品信息-商品标题
                    System.out.println("商品标题=" + j.getString("title"));
                    //链接-宝贝地址
                    System.out.println("宝贝地址=" + j.getString("item_url"));
                    //商品信息-商品主图
                    System.out.println("商品主图=" + j.getString("pict_url"));
                    System.out.println("商品信息-宝贝id=" + j.getString("item_id"));
                    //店铺信息-店铺名称
                    System.out.println("店铺名称=" + j.getString("shop_title"));
                    // 链接-宝贝+券二合一页面链接
                    if (j.has("coupon_share_url")) {
                        System.out.println("二合一页面链接=" + j.getString("coupon_share_url"));
                    }
                    //宝贝推广链接
                    System.out.println("链接-宝贝推广链接=" + j.getString("url"));

                    Double coupon_amount = 0.00;
                    if (j.has("coupon_amount")) {
                        coupon_amount = j.getDouble("coupon_amount");
                        System.out.println("优惠券（元)=" + coupon_amount);
                    }
                    Double reserve_price = j.getDouble("reserve_price");
                    //商品信息-商品一口价格
                    System.out.println("商品一口价格=" + reserve_price);

                    Double zk_final_price = j.getDouble("zk_final_price");
                    System.out.println("折扣价（元）=" + zk_final_price);

                    if (j.has("commission_rate")) {
                        Double commission_rate = j.getDouble("commission_rate");
                        System.out.println("佣金金额=" + (zk_final_price - coupon_amount) * Double.parseDouble(String.valueOf((commission_rate / 10000))));
                        System.out.println("佣金比例=" + commission_rate / 100);
                        System.out.println("券后=" + (zk_final_price - coupon_amount));
                    }
                //优惠券信息-优惠券开始时间
                System.out.println("优惠券信息-优惠券开始时间=" + j.getString("coupon_start_time"));
                //优惠券信息-优惠券开始时间
                //System.out.println("优惠券信息-优惠券结束时间=" + j.getString("coupon_end_time"));

            }
        }else {
            System.out.println(rsp.getSubMsg());
        }*/

        //System.out.println(rsp.getBody());
        //System.out.println(rsp.getSubCode()+rsp.getSubMsg());

/*        TbkSpreadGetRequest req = new TbkSpreadGetRequest();
        List<TbkSpreadGetRequest.TbkSpreadRequest> list2 = new ArrayList<TbkSpreadGetRequest.TbkSpreadRequest>();
        TbkSpreadGetRequest.TbkSpreadRequest obj3 = new TbkSpreadGetRequest.TbkSpreadRequest();
        list2.add(obj3);
        obj3.setUrl("https://uland.taobao.com/coupon/edetail?e=5qNd5IFUzl0NfLV8niU3RxrSI%2FOabn6qNg4Gqf8CT4AKuDLwELihnYGxCw25f49jR5RbQ%2FaMAzjzg9ez7b%2B0xDgcNGWRQUyfZUWLdTnHV8qP0kqNEDOwpSWXf2W1CWH6fKMOtJeQVfs492GMRdRUGnfFjNjJPe2WRV3mMeYnpDqjvwqG%2F7bFyyDrhnJcCiPk59WqflzEvOXNa4fuEICbKUwNBUbTsArs&&app_pvid=59590_11.90.50.202_2084_1591692788052&ptl=floorId:2836;app_pvid:59590_11.90.50.202_2084_1591692788052;tpp_pvid:100_11.139.250.89_118676_9051591692788054370&xId=3cVntwnKKodOfunoCRpvm31NxNjP6HJqZUGY3UVqPPcuX5GFVgB7oyZ8Ck1dYPmfEVfzgQHqbL6yW366cdzEmlgCGoPcchGTCwGV5Sye5fuD&union_lens=lensId%3AMAPI%401591692788%400b5a32ca_0e8e_172984881a4_b5d6%4001\n");
        req.setRequests(list2);
        TbkSpreadGetResponse rsp= client.execute(req);
        System.out.println(rsp.getBody());

        JSONObject js = JSONObject.fromObject(rsp.getBody());

        if(js.has("tbk_spread_get_response")){
            JSONObject jss = JSONObject.fromObject(js.getString("tbk_spread_get_response"));
            String total_results = jss.getString("total_results");
            System.out.println("结果总数=" + total_results);
            JSONObject results = JSONObject.fromObject(jss.get("results"));
            JSONArray tbkSpread = JSONArray.fromObject(results.get("tbk_spread"));
            JSONObject j = JSONObject.fromObject(tbkSpread.get(0));
            if(j.getString("err_msg").equalsIgnoreCase("ok")){

                System.out.println("商品标题=" + j.getString("content"));
            }

        }*/

        //TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
/*        TbkScInvitecodeGetRequest req = new TbkScInvitecodeGetRequest();
        req.setRelationId(11L);
        req.setRelationApp("common");
        req.setCodeType(1L);
        TbkScInvitecodeGetResponse rsp = client.execute(req, sessionKey);
        System.out.println(rsp.getBody());*/


/*        TbkScPublisherInfoSaveRequest req = new TbkScPublisherInfoSaveRequest();
        req.setInviterCode("7SDPDU");
        req.setInfoType(1L);
        TbkScPublisherInfoSaveResponse rsp = client.execute(req, sessionKey);
        System.out.println(rsp.getBody());*/

       // System.out.println(HttpRequestUtil.getURL("http://container.open.taobao.com/container?appkey=29490486"));

        //TaobaoClient client = new DefaultTaobaoClient(HrdhRestfulConfig.server_url, HrdhRestfulConfig.app_key, HrdhRestfulConfig.app_secret);

/*        TopAuthTokenCreateRequest req = new TopAuthTokenCreateRequest();
        req.setCode("CDo1tXToPlrS50I63zAxnIYy6260114");

        TopAuthTokenCreateResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());*/

/*        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        TaobaoClient client = new DefaultTaobaoClient(serverUrl, appKey, appSecret);

        TbkRelationRefundRequest req = new TbkRelationRefundRequest();
        TbkRelationRefundRequest.TopApiRefundRptOption obj1 = new TbkRelationRefundRequest.TopApiRefundRptOption();
        obj1.setPageSize(100L);
        obj1.setSearchType(1L);
        obj1.setRefundType(0L);
        obj1.setStartTime(sf.parse("2020-06-13 00:00:00"));
        obj1.setPageNo(1L);
        obj1.setBizType(1L);
        req.setSearchOption(obj1);
        TbkRelationRefundResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());*/

        int count = 0;//总条数

        int pageSize=100;//每页显示10条

        ////总页数
        int pageCount=count % pageSize == 0 ? (count / pageSize) : (count / pageSize + 1);

        System.out.println(pageCount);



    }

    private static String getSellerItem() throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        // 公共参数
        params.put("method", "taobao.tbk.item.click.extract");
        params.put("app_key", appKey);
        //params.put("session", null);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        params.put("timestamp", df.format(new Date()));
        params.put("format", "json");
        params.put("v", "2.0");
        params.put("sign_method", "hmac");
        // 业务参数
        params.put("click_url", "https://s.click.taobao.com/QAXcUhv");
        // 签名参数
        params.put("sign", signTopRequest(params, appSecret, SIGN_METHOD_HMAC));
        // 请用API
        return callApi(new URL(serverUrl), params);
    }

    /**
     * 对TOP请求进行签名。
     */
    private static String signTopRequest(Map<String, String> params, String secret, String signMethod) throws IOException {
        // 第一步：检查参数是否已经排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        // 第二步：把所有参数名和参数值串在一起
        StringBuilder query = new StringBuilder();
        if (SIGN_METHOD_MD5.equals(signMethod)) {
            query.append(secret);
        }
        for (String key : keys) {
            String value = params.get(key);
            if (isNotEmpty(key) && isNotEmpty(value)) {
                query.append(key).append(value);
            }
        }

        // 第三步：使用MD5/HMAC加密
        byte[] bytes;
        if (SIGN_METHOD_HMAC.equals(signMethod)) {
            bytes = encryptHMAC(query.toString(), secret);
        } else {
            query.append(secret);
            bytes = encryptMD5(query.toString());
        }

        // 第四步：把二进制转化为大写的十六进制
        return byte2hex(bytes);
    }

    /**
     * 对字节流进行HMAC_MD5摘要。
     */
    private static byte[] encryptHMAC(String data, String secret) throws IOException {
        byte[] bytes = null;
        try {
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(CHARSET_UTF8), "HmacMD5");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            bytes = mac.doFinal(data.getBytes(CHARSET_UTF8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.toString());
        }
        return bytes;
    }

    /**
     * 对字符串采用UTF-8编码后，用MD5进行摘要。
     */
    private static byte[] encryptMD5(String data) throws IOException {
        return encryptMD5(data.getBytes(CHARSET_UTF8));
    }

    /**
     * 对字节流进行MD5摘要。
     */
    private static byte[] encryptMD5(byte[] data) throws IOException {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            bytes = md.digest(data);
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.toString());
        }
        return bytes;
    }

    /**
     * 把字节流转换为十六进制表示方式。
     */
    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    private static String callApi(URL url, Map<String, String> params) throws IOException {
        String query = buildQuery(params, CHARSET_UTF8);
        byte[] content = {};
        if (query != null) {
            content = query.getBytes(CHARSET_UTF8);
        }

        HttpURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Host", url.getHost());
            conn.setRequestProperty("Accept", "text/xml,text/javascript");
            conn.setRequestProperty("User-Agent", "top-sdk-java");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHARSET_UTF8);
            out = conn.getOutputStream();
            out.write(content);
            rsp = getResponseAsString(conn);
        } finally {
            if (out != null) {
                out.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

        return rsp;
    }

    private static String buildQuery(Map<String, String> params, String charset) throws IOException {
        if (params == null || params.isEmpty()) {
            return null;
        }

        StringBuilder query = new StringBuilder();
        Set<Entry<String, String>> entries = params.entrySet();
        boolean hasParam = false;

        for (Entry<String, String> entry : entries) {
            String name = entry.getKey();
            String value = entry.getValue();
            // 忽略参数名或参数值为空的参数
            if (isNotEmpty(name) && isNotEmpty(value)) {
                if (hasParam) {
                    query.append("&");
                } else {
                    hasParam = true;
                }

                query.append(name).append("=").append(URLEncoder.encode(value, charset));
            }
        }

        return query.toString();
    }

    private static String getResponseAsString(HttpURLConnection conn) throws IOException {
        String charset = getResponseCharset(conn.getContentType());
        if (conn.getResponseCode() < 400) {
            String contentEncoding = conn.getContentEncoding();
            if (CONTENT_ENCODING_GZIP.equalsIgnoreCase(contentEncoding)) {
                return getStreamAsString(new GZIPInputStream(conn.getInputStream()), charset);
            } else {
                return getStreamAsString(conn.getInputStream(), charset);
            }
        } else {// Client Error 4xx and Server Error 5xx
            throw new IOException(conn.getResponseCode() + " " + conn.getResponseMessage());
        }
    }

    private static String getStreamAsString(InputStream stream, String charset) throws IOException {
        try {
            Reader reader = new InputStreamReader(stream, charset);
            StringBuilder response = new StringBuilder();

            final char[] buff = new char[1024];
            int read = 0;
            while ((read = reader.read(buff)) > 0) {
                response.append(buff, 0, read);
            }

            return response.toString();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    private static String getResponseCharset(String ctype) {
        String charset = CHARSET_UTF8;

        if (isNotEmpty(ctype)) {
            String[] params = ctype.split(";");
            for (String param : params) {
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if (pair.length == 2) {
                        if (isNotEmpty(pair[1])) {
                            charset = pair[1].trim();
                        }
                    }
                    break;
                }
            }
        }

        return charset;
    }

    private static boolean isNotEmpty(String value) {
        int strLen;
        if (value == null || (strLen = value.length()) == 0) {
            return false;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(value.charAt(i)) == false)) {
                return true;
            }
        }
        return false;
    }

}