package com.ticket.base.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**  
 * 
 *
 * @author wujunjun 
 * @date 2015-1-4 下午3:30:36
 * @since 1.0  
 * @Copyright 2014 东莞市邮政局All rights reserved.  
 */
public class PropertiesUtil {

	//根据属性读取配置文件
		public static String getProperty(String key,String fileName){
			Properties prop = new Properties();// 属性集合对象   
			try {
				InputStream inputStream = PropertiesUtil.class
						.getResourceAsStream(fileName);
				prop.load(inputStream);
				inputStream.close();// 关闭流
				return prop.getProperty(key);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// 属性文件输入流   
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
}
