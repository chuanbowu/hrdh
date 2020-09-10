package com.flym.hrdh.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:各种ID生产策略管理</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
public class IDUtils {

	/**
	 * 根据时间生成ID
	 */
	public static String getIdName() {
		//取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		//long millis = System.nanoTime();
		//加上三位随机数
		Random random = new Random();
		int end3 = random.nextInt(999);
		//如果不足三位前面补0
		String str = millis + String.format("%03d", end3);

		return str;
	}

	/**
	 * 商品id生成
	 */
	public static long genItemId() {
		//取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		//long millis = System.nanoTime();
		//加上两位随机数
		Random random = new Random();
		int end2 = random.nextInt(99);
		//如果不足两位前面补0
		String str = millis + String.format("%02d", end2);
		long id = new Long(str);
		return id;
	}

	public static String getOrderNo(Long userId,String goodsIds) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyMMddHHmmss");
		Date now = new Date();
		String dateStr = fmt.format(now);

		String idStr = MD5Util.encoder(String.format("%d%s",userId,goodsIds));
		String idStr1 = idStr.substring(0,10);
		Long id = Long.parseLong(idStr1,16);

		return String.format("%s%d",dateStr,id).substring(0,18);
	}

	public static String getInvitationCode(Long key){
		return RC4.encry_RC4_string(String.format("%03d",key),getUuid()).toUpperCase();
	}

	//商户秘钥
	public static String getMerchantKeyCode(Long key){
		return RC4.encry_RC4_string(String.format("%016d",key),getUuid()).toUpperCase();
	}

	public static String getRandomCode(int charCount) {
		String charValue = "";
		for (int i = 0; i < charCount; i++) {
			char c = (char) (randomInt(0, 10) + '0');
			charValue += String.valueOf(c);
		}
		return charValue;
	}
	public static int randomInt(int from, int to) {
		Random r = new Random();
		return from + r.nextInt(to - from);
	}

	public static String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static void main(String[] args) {
		System.out.println(getInvitationCode(3L));
	}


}
