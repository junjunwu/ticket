package com.ticket.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;


/*******************************************************************************
 * 日期工具类
 * 
 * <pre>
 * 日期操作工具类
 * </pre>
 * 
 * @author zengxiangtao
 * @version 2013-07-01
 ******************************************************************************/
public class MyDateUtils {
	

	/**
	 * 格式化日日期成字符串
	 * 
	 * @author zengxiangtao
	 * **/
	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 字符串转换成日期格式
	 * 
	 * @author zengxiangtao
	 * **/
	public static Date strToDate(String date, String pattern) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 订制日期
	 * 
	 * @param date
	 *            --日期
	 * @param field
	 *            --位移单位
	 * @prama offset--位移数值
	 */
	public static Date getDesignatedDate(Date date, int field, int offset) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(field, offset);
		return calendar.getTime();
	}
}
