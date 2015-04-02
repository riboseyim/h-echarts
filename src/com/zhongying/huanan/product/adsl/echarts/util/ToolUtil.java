/*
 * 功能描述:ID738(Deploy-E8业务开通-20080416),新增历史数据处理程序:工具类
 * 创建日期:2008-02-25
 * 创建人:李书亮
 * 文档编号：Deploy-E8业务开通-20080416
 * 任务号/bug号：ID738
 */

package com.zhongying.huanan.product.adsl.echarts.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class ToolUtil {

	/**
	 * 基本功能：获取系统当前时间信息 方法名称: getAccurateTime()
	 * <p>
	 * 
	 * <pre>
	 * 类    型：公共函数
	 * 数据库表： 无
	 * 修    改：李书亮
	 * 修改日期： 2008-04-22
	 * 修改内容： ID738(Deploy-E8业务开通-20080416),新增历史数据处理程序:工具类-获取系统当前时间信息
	 * </pre>
	 * 
	 * @return String
	 */
	public static String getAccurateTime() throws Exception {
		// 根据时区适应 覃华云
		String[] ids = TimeZone.getAvailableIDs(-8 * 60 * 60 * 1000);
		// if no ids were returned, something is wrong. get out.
		if (ids.length == 0)
			ids[0] = "EAT-8";

		// create a Pacific Standard Time time zone
		SimpleTimeZone pdt = new SimpleTimeZone(8 * 60 * 60 * 1000, ids[0]);

		GregorianCalendar sdate = new GregorianCalendar(pdt, new Locale("CHINESE ", "CHINA"));
		int year = sdate.get(Calendar.YEAR);
		int month = sdate.get(Calendar.MONTH) + 1;
		int date = sdate.get(Calendar.DATE);
		int hour = sdate.get(Calendar.HOUR_OF_DAY);
		int minute = sdate.get(Calendar.MINUTE);
		int second = sdate.get(Calendar.SECOND);
		StringBuffer sResult = new StringBuffer();
		sResult.append(year);
		sResult.append("-");
		if (month < 10) {
			sResult.append(0);
			sResult.append(month);
			sResult.append("-");
		} else {
			sResult.append(month);
			sResult.append("-");
		}
		if (date < 10) {
			sResult.append(0);
			sResult.append(date);
			sResult.append(" ");
		} else {
			sResult.append(date);
			sResult.append(" ");
		}
		if (hour < 10) {
			sResult.append(0);
			sResult.append(hour);
			sResult.append(":");
		} else {
			sResult.append(hour);
			sResult.append(":");
		}
		if (minute < 10) {
			sResult.append(0);
			sResult.append(minute);
			sResult.append(":");
		} else {
			sResult.append(minute);
			sResult.append(":");
		}
		if (second < 10) {
			sResult.append(0);
			sResult.append(second);
		} else {
			sResult.append(second);
		}
		return sResult.toString();
	}

	public static String getYYYYMMDD() {
		// 根据时区适应 覃华云
		String[] ids = TimeZone.getAvailableIDs(-8 * 60 * 60 * 1000);
		// if no ids were returned, something is wrong. get out.
		if (ids.length == 0)
			ids[0] = "Asia/Shanghai";
		// System.out.println("time zone="+TimeZone.getDefault());
		// create a Pacific Standard Time time zone
		SimpleTimeZone pdt = new SimpleTimeZone(8 * 60 * 60 * 1000, ids[0]);

		GregorianCalendar sdate = new GregorianCalendar(pdt, new Locale("CHINESE ", "CHINA"));

		int year = sdate.get(Calendar.YEAR);
		int month = sdate.get(Calendar.MONTH) + 1;
		int date = sdate.get(Calendar.DATE);
		String Result = new String();
		Result += year;
		if (month < 10) {
			Result += 0;
			Result += month;
		} else {
			Result += month;
		}
		if (date < 10) {
			Result += 0;
			Result += date;
		} else {
			Result += date;
		}
		return Result;
	}


}
