package com.ticket.base.page;


/**  
 * 分页查询条件基础类
 *
 * @author wujunjun 
 * @date 2014-9-29 下午4:27:22
 * @since 1.0  
 * @Copyright 2014 东莞市邮政局All rights reserved.  
 */ 
public class PageQueryBean {

	public static final int DEFAULT_PAGE_SIZE = 15;


	public static final int DEFAULT_PAGE_NO = 1;

	/**
	 * 页面大小
	 */
	private Integer rows = DEFAULT_PAGE_SIZE;

	/**
	 * 请求页
	 */
	private Integer page = DEFAULT_PAGE_NO;

	public PageQueryBean(Integer page, Integer rows) {
		super();
		this.setPage(page);
		this.setRows(rows);
	}

	public PageQueryBean() {
		super();
	}

	public Integer getRows() {
		
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = initPageNumberValid(rows);
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = initPageNumberValid(page);
	}

	private int initPageNumberValid(Integer number) {
		return (number == null || number <= 0) ? 1 : number;
	}

}
