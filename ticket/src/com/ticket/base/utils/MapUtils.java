package com.ticket.base.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ticket.base.page.Pagination;

/**  
 * 
 *
 * @author wujunjun 
 * @date 2014-12-29 上午10:39:24
 * @since 1.0  
 * @Copyright 2014 东莞市邮政局All rights reserved.  
 */
public class MapUtils {

	public static Map<String,Object> initResultMap(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", "-1");
		map.put("error", "");
		map.put("data", null);
		return map;
	}
	
	public static Map<String,Object> initSuccessResultMap(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", "1");
		map.put("error", "");
		map.put("data", null);
		return map;
	}
	
	public static Map<String,Object> initFailResultMap(String errorMsg){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", "0");
		map.put("error", "");
		map.put("data", null);
		if(StringUtils.isNotBlank(errorMsg)){
			map.put("error", errorMsg);
		}
		return map;
	}
	
	/**
	 * 初始化返回结果分页数据
	 *
	 * @param page
	 * @return 
	 * @author wujunjun
	 */
	public static Map<String,Object> initResultDataMap(Pagination<?> page){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("lists", page.getObjLists());
		data.put("totalRows", page.getTotalRows());
		data.put("totalPage", page.getTotalPage());
		data.put("currentPage", page.getPageNo());
		data.put("pageSize", page.getPageSize());
		return data;
	}

	/**
	 * 
	 *
	 * @param hotWorks
	 * @return 
	 * @author wujunjun
	 */
	public static Map<String,Object> initResultDataMap(List<?> list) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("lists", list);
		return data;
	}
}
