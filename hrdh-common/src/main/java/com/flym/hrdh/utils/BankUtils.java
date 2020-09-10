package com.flym.hrdh.utils;


/**
 * <p>Title:红人带货系统</p>
 * <p>Description: 银行信息处理工具</p>
 * <p>Copyright: Copyright (c) 2019-01-07</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: Mr.Yuan $
 * @version $Revision: 1.0.0 $
 */
public class BankUtils {

    public static  String  getBankCardType(String bankType){
        /**
         *   DC: "储蓄卡",
         *     CC: "信用卡",
         *     SCC: "准贷记卡",
         *     PC: "预付费卡
         */
        if("DC".equalsIgnoreCase(bankType)){
            return "储蓄卡";
        }else if("CC".equalsIgnoreCase(bankType)){
            return "信用卡";
        }else if("SCC:".equalsIgnoreCase(bankType)){
            return "准贷记卡";
        }else if("PC".equalsIgnoreCase(bankType)){
            return "预付费卡";
        }
        return "";
    }
}
