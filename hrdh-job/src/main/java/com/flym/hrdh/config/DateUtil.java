package com.flym.hrdh.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>Title: 红人带货系统</p>
 * <p>Description: 时间计算工具类口</p>
 * <p>Copyright: Copyright (c) 2020-06-13</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: .jh.x $
 * @version $Revision: 1.0 $
 */
public class DateUtil {

	/**
	 * 加秒
	 * @return
	 */
	public static Date getFetureDateSecond(Date date,int num){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, num );
		return calendar.getTime();
	}

	/**
	 * 加分钟
	 * @return
	 */
	public static Date getFetureDateMinute(Date date,int num){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, num );
		return calendar.getTime();
	}

	/**
	 * 加小时
	 * @return
	 */
	public static Date getFetureDateHour(Date date,int num){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, num );
		return calendar.getTime();
	}

	/**
	 * 添加天数
	 * @return
	 */
	public static Date getFetureDateHasDate(Date date,int num){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, num);
		return calendar.getTime();
	}

	/**
	 * 减周数
	 * @return
	 */
	public static Date getFetureDateWeek(Date date,int num){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, num * 7);
		return calendar.getTime();
	}

	/**
	 * 添加月数
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date getFetureMonth(Date date,int num) {
	   Calendar calendar = Calendar.getInstance();  
	   calendar.setTime(date);
	   calendar.add(Calendar.MONTH, num);
	   return calendar.getTime(); 
   }

	/**
	 * 添加年数
	 * @param date
	 * @param num
	 * @return
	 */
   public static Date getFetureYear(Date date,int num) {
	   Calendar calendar = Calendar.getInstance();  
	   calendar.setTime(date);
	   calendar.add(Calendar.YEAR, num);
	   return calendar.getTime();  
   }


   public static Date getFetureHour(Date date,int num){
	   Calendar calendar = Calendar.getInstance();
	   calendar.setTime(date);
	   calendar.add(Calendar.HOUR, num);
	   return calendar.getTime();
   }

   public static void main(String[] args) throws ParseException {
   		Date date = new Date();

	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	   SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	   System.out.println(sdf.format(DateUtil.getMonthLastDay(date)) + " 23:59:59");
	   System.out.println(sf.format(DateUtil.getFetureDateMinute(sf.parse("2020-05-31 23:39:59"), 20)));
   }

	/**
	 * 减秒
	 * @return
	 */
	public static Date getReduceDateSecond(Date date,int num){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayNum = calendar.get(Calendar.SECOND);
		calendar.set(Calendar.SECOND, dayNum - num );
		return calendar.getTime();
	}

	/**
	 * 减分钟
	 * @return
	 */
	public static Date getReduceDateMinute(Date date,int num){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayNum = calendar.get(Calendar.MINUTE);
		calendar.set(Calendar.MINUTE, dayNum - num );
		return calendar.getTime();
	}

	/**
	 * 减小时
	 * @return
	 */
	public static Date getReduceDateHour(Date date,int num){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayNum = calendar.get(Calendar.HOUR_OF_DAY);
		calendar.set(Calendar.HOUR_OF_DAY, dayNum - num );
		return calendar.getTime();
	}

	/**
	 * 减天数
	 * @return
	 */
	public static Date getReduceDateHasDate(Date date,int num){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayNum = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, dayNum - num );
		return calendar.getTime();
	}

	/**
	 * 减周数
	 * @return
	 */
	public static Date getReduceDateWeek(Date date,int num){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayNum = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, dayNum - (num * 7));
		return calendar.getTime();
	}

	/**
	 * 减月数
	 * @return
	 */
	public static Date getReduceDateMonth(Date date,int num){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayNum = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, dayNum - num );
		return calendar.getTime();
	}

	/**
	 * 减年数
	 * @return
	 */
	public static Date getReduceDateYEAR(Date date,int num){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayNum = calendar.get(Calendar.YEAR);
		calendar.set(Calendar.YEAR, dayNum - num );
		return calendar.getTime();
	}

	/***
	 * 加小时和分钟
	 * @param date
	 * @param hour
	 * @param minute
	 * @return
	 */
	public static Date getFetureHourMinute(Date date,int hour,int minute){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hour);
		calendar.add(Calendar.MINUTE, minute );
		return calendar.getTime();
	}

	/**
	 * 获取上个月最后一天
	 * @param date
	 * @return
	 */
	public static Date getMonthLastDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH,0);
		calendar.add(Calendar.MONTH,0);
		return calendar.getTime();
	}

}
