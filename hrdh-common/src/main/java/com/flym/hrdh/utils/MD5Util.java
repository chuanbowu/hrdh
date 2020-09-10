package com.flym.hrdh.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:MD5加密管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class MD5Util {

    public static final String KEY_MD5 = "MD5";

    public static String encoder(String str) {
        BigInteger bigInteger=null;
        try {
            MessageDigest md = MessageDigest.getInstance(KEY_MD5);
            byte[] inputData = str.getBytes();
            md.update(inputData);
            bigInteger = new BigInteger(md.digest()).abs();
        } catch (Exception e) {e.printStackTrace();}
        return bigInteger.toString(16);
    }

    public static String md5(String value){
        String result = null;
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
            md5.update((value).getBytes("UTF-8"));
        }catch (NoSuchAlgorithmException error){
            error.printStackTrace();
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        byte b[] = md5.digest();
        int i;
        StringBuffer buf = new StringBuffer("");

        for(int offset=0; offset<b.length; offset++){
            i = b[offset];
            if(i<0){
                i+=256;
            }
            if(i<16){
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }

        result = buf.toString();
        return result;
    }

    public static void main(String[] args) throws Exception {
        String password1=md5("merchantName=apple&merchantCode=222224&merchantOrderNo=test5151298148211420251&payPrice=30000&payType=1&B63F66BE6481E43FCFAF0282FDCB6EAF");

        //String sign = MD5Util.md5("merchantName=apple&merchantOrderNo=222224&noticeType=1&payPrice=30000&payType=1&B63F66BE6481E43FCFAF0282FDCB6EAF");
        String sign2 = MD5Util.md5("merchantName=玖玖数码&merchantCode=222223&merchantOrderNo=201901291117260000012&payPrice=150000&payType=1&9499A1E7CD7E5E58B2AFBEDDEE374A80");


        String keyStr = "merchantName=membershop&merchantCode=222259&merchantOrderNo=190528144553000002"
                + "&payPrice=" + 30000 + "&payType=" + 1 + "&53772C267D1261365C1A47836D2D6B81";

        //异步通知地址
        //String callbackUrl = order.getCallbackUrl();
        //商户订单号
        String merchantOrderNo = "2019061010051245";
        //商户备注
        String merchantRemark = "商户测试";
        //付款类型：1-支付宝、2-微信
        Integer payType = 1;
        //付款金额
        String orderPrice = String.valueOf((int)(300.00 * 100));
        //商户名称
        String merchantName = "玖玖数码";
        //通知类型：1-订单成功、2-订单失败
        Integer noticeType = 1;


        merchantName = URLEncoder.encode(merchantName, "UTF-8");
        merchantOrderNo = URLEncoder.encode(merchantOrderNo, "UTF-8");
        if(StringUtils.isNotBlank(merchantRemark)){
            merchantRemark = URLEncoder.encode(merchantRemark, "UTF-8");
        }

        String sign = MD5Util.md5("merchantName=" + merchantName + "&merchantOrderNo=" + merchantOrderNo +
                "&noticeType=" + noticeType + "&payPrice=" + orderPrice + "&payType=" + payType + "&B63F66BE6481E43FCFAF0282FDCB6EAF");

        String gxzf = MD5Util.md5("merchantName="+merchantName+"&merchantOrderNo="+merchantOrderNo+"&merchantRemark="+merchantRemark +
                "&noticeType="+noticeType+"&payPrice="+orderPrice+"&payType="+payType+"&B63F66BE6481E43FCFAF0282FDCB6EAF");

        String gxzf2 = MD5Util.md5("merchantName=apple&merchantOrderNo=2019061010051245&merchantRemark=123&noticeType=1&payPrice=30000&payType=1&B63F66BE6481E43FCFAF0282FDCB6EAF");

        String str = "merchantOrderNo=" + merchantOrderNo +"&merchantRemark=" + merchantRemark + "&payType=" + payType +
                "&payPrice=" + orderPrice + "&merchantName=" + merchantName + "&noticeType=" + noticeType + "&sign=" + sign;

        System.out.println(gxzf);
        System.out.println(gxzf2);
        System.out.println(merchantRemark);
        System.out.println(merchantName);

    }

}
