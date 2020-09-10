package com.flym.hrdh.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:数字处理工具类</p>
 * <p>Copyright: Copyright (c) 2020-05-06</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class NumUtils {

    /**
     * double四舍五入得到int
     * @param num       double数字
     * @return int数字
     */
    public static int doubleToIntByUp(double num) {
        return new BigDecimal(Double.toString(num)).setScale(0, RoundingMode.HALF_UP).intValue();
    }

    /**
     * double四舍五入得到String
     * @param num  double数字
     * @param scale 小数位数
     * @return double数字
     */
    public static String doubleToScale(double num, int scale) {
        return new BigDecimal(Double.toString(num)).setScale(scale, RoundingMode.HALF_UP).toString();
    }

    /**
     * double四舍五入得到double
     * @param num  double数字
     * @param scale 小数位数
     * @return double数字
     */
    public static double doubleScale(double num, int scale) {
        return new BigDecimal(Double.toString(num)).setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * double四舍五入得到String
     * @param num  double数字
     * @param scale 小数位数
     * @return double数字
     */
    public static String doubleScaleToString(double num, int scale) {

        String str = new BigDecimal(Double.toString(num)).setScale(scale, RoundingMode.HALF_UP).toString();
        num = Double.parseDouble(str);

        //如果小数位都为0，则不显示小数
        if(num - (int)num == 0){
            str = str.substring(0, str.indexOf("."));
        }else {
            for (int i = 2; i <= scale; i++) {
                if (num - Double.parseDouble(str.substring(0, str.indexOf(".") + i)) == 0) {
                    str = str.substring(0, str.indexOf(".") + i);
                    break;
                }
            }
        }

        return str;
    }

    public static void main(String[] args) {

//        BeeCloud.registerApp("43c0f6ee-b855-490e-98dc-8caa4064faa9", "00db4043-eb12-4e65-8a3b-fa4d5fa9e54a", null, null);
//        BeeCloud.setSandbox(true);
//
//        System.out.println(MD5Util.encoder("mch_id=17190&service=BINDCARD09a3a9220c06c8c2b60858d7a5c1594b"));
//
//        String result = "\"success\"|\"201712040001375301\"|\"3\"|\"09a3a9220c06c8c2b60858d7a5c1594b\"";
//        result = result.substring(result.indexOf("|") + 2);
//        System.out.println(result);
//        result = result.substring(0, result.indexOf("|")-1);
//        System.out.println(result);

//          double dd = doubleScale(0.00123, 2);
//            System.out.println("==="+dd);
//
//        String dd1 =doubleToScale(0.5, 2);
//        System.out.println("==="+dd1);

    }
}
