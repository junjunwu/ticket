package com.ticket.base.page;

/**
 * 
 * @author flatychen
 * @date 2014-5-8
 */
public class QueryPageObject {

	public static final int DEFAULT_PAGE_SIZE = 10;

	public static final int DEFAULT_PAGE_NAV_SIZE = 5;

	public static final int DEFAULT_PAGE_NO = 1;

	/**
	 * 页面大小
	 */
	private Integer pageSize = DEFAULT_PAGE_SIZE;

	/**
	 * 请求页
	 */
	private Integer pageNo = DEFAULT_PAGE_NO;

	/**
	 * 分页数字导航显示
	 */
	private Integer pageNavSize = DEFAULT_PAGE_NAV_SIZE;

	public QueryPageObject(Integer pageSize, Integer pageNo, Integer pageNavSize) {
		super();
		this.setPageNavSize(pageNavSize);
		this.setPageNo(pageNo);
		this.setPageSize(pageSize);
	}

	public QueryPageObject(Integer pageNo, Integer pageSize) {
		super();
		this.setPageNo(pageNo);
		this.setPageSize(pageSize);
	}

	public QueryPageObject() {
		super();
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public QueryPageObject setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public QueryPageObject setPageNo(Integer pageNo) {
		this.pageNo = initPageNumberValid(pageNo);
		return this;
	}

	public int getPageNavSize() {
		return pageNavSize;
	}

	public QueryPageObject setPageNavSize(Integer pageNavSize) {
		this.pageNavSize = initPageNumberValid(pageNavSize);
		return this;
	}

	private int initPageNumberValid(Integer number) {
		return (number == null || number <= 0) ? 1 : number;
	}

}
