package com.ticket.base.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**  
 * 
 *
 * @author wujunjun 
 * @date 2015-1-4 下午3:33:08
 * @since 1.0  
 * @Copyright 2014 东莞市邮政局All rights reserved.  
 */
public class MyFileUtils {

	/**
	 * 上传文件
	 *
	 * @param file 
	 * @author wujunjun
	 * @return 
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public static Map<String, Object> doUpload(MultipartFile file,String dir,String path) {
		//图片后缀
		String suffixName = getSuffixName(file.getOriginalFilename());
//		System.out.println(file.getContentType());
//		if(!IsImgContentType(file.getContentType())){
//			//不是图片类型
//			return MapUtils.initFailResultMap("not_image");
//		}
		if(!IsImgSuffix(suffixName)){
			//不是图片类型
			return MapUtils.initFailResultMap("not_image");
		}
		Long maxImgSize = Long.parseLong(PropertiesUtil.getProperty("maxImgSize", "/sysConfig.properties"));
		if(file.getSize()>maxImgSize.longValue()){
			//文件过大
			return MapUtils.initFailResultMap("out_size");
		}
		String imgUploadPath = PropertiesUtil.getProperty("imgUploadPath", "/sysConfig.properties");
		String imgPath = PropertiesUtil.getProperty("imgPath", "/sysConfig.properties");
		String fullPath = "";
		if(StringUtils.isBlank(path)){
			Calendar cal = Calendar.getInstance();
			//文件保存的路径
			imgUploadPath = imgUploadPath + File.separator+dir+
		           File.separator+cal.get(Calendar.YEAR)+File.separator+(cal.get(Calendar.MONTH)+1)+File.separator+cal.get(Calendar.DAY_OF_MONTH);
			//访问路径
			fullPath = dir+
				"/"+cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DAY_OF_MONTH);
		}
		else{
			//文件保存的路径
			imgUploadPath = imgUploadPath + File.separator+dir+
		           File.separator+path;
			//访问路径
			fullPath = dir+
				"/"+path;
		}
		//如果基本路径不存在
		File fileDir = new File(imgUploadPath);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		
		//图片上传到服务器上的名字
		String imgName = getUUIDStr()+"."+suffixName; //服务器中存储文件名
		File imageFile = new File(imgUploadPath+File.separator+imgName);  
		//上传...  
		try {
			file.transferTo(imageFile);
		} catch (IOException e) {
			//异常
			return MapUtils.initFailResultMap(MessageConstants.EXCEPTION);
		}  
		
		Map<String,Object> map = MapUtils.initSuccessResultMap();
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("imgUrl", fullPath+"/"+imgName);
		data.put("imgPath", imgPath);
		map.put("data", data);
		return map;
	}

	/**
	 * 获取随机文件名
	 *
	 * @return 
	 * @author wujunjun
	 */
	private static String getUUIDStr() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSS");
		return format.format(new Date());
		//UUID uuid = UUID.randomUUID();
		//return uuid.toString().replace("-", "");
	}
	
	/**
	 * 
	 *
	 * @return 
	 * @author wujunjun
	 */
	public static String getImgPath() {
		return PropertiesUtil.getProperty("imgPath", "/sysConfig.properties");
	}


	/**
	 * 获取文件后缀
	 *
	 * @param originalFileName
	 * @return 
	 * @author wujunjun
	 */
	private static String getSuffixName(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".")+1);
	}
	
	/**
	 * 根据contentType判断是否为图片
	 *
	 * @param contentType
	 * @return 
	 * @author wujunjun
	 */
	public static boolean IsImgContentType(String contentType){
		if(!filterFileContentType(PropertiesUtil.getProperty("imgContentType", "/sysConfig.properties").split(";"), contentType)) {
			return false;
		}
		else{
			return true;
		}
	}
	
	/**
	 * 根据文件后缀判断是否为图片
	 *
	 * @param contentType
	 * @return 
	 * @author wujunjun
	 */
	public static boolean IsImgSuffix(String suffix){
		if(!filterFileContentType(PropertiesUtil.getProperty("imgSuffix", "/sysConfig.properties").split(";"), suffix)) {
			return false;
		}
		else{
			return true;
		}
	}
	
	/**
	 * 过虑文件mime类型。<br/>
	 * 
	 * @param filters :符合的文件类型。
	 * @param contentType :要被过滤的文件类型。
	 * @return bolean :被过滤的文件类型如果匹配filters，返回true, false otherwise.
	 */
	public static boolean filterFileContentType(String[] filters, String contentType) {
		for(String filter : filters) {
			if(filter.equalsIgnoreCase(contentType)) {
				return true;
			}
		}
		return false;
	}
	
	

}
