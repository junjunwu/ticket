package com.ticket.base.page;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 分页Bean
 * 
 * @author flatychen
 * @param <T>
 */
public class Pagination<T> {

	// 总记录数
	private int totalRows = 0;
	// 查询结果集
	private List<T> objLists;
	// 页码的起始与结束
	private int begin = 1;
	private int end = 1;
	// 总页数
	private int totalPage = 1;

	// 分页页码显示个数
	private QueryPageObject pageQuery = null;

	/**
	 * 
	 * @author flatychen
	 * @date 2014-5-8
	 * @param querySql
	 * @return
	 */
	public static String countSql(String querySql) {
		return new StringBuffer("SELECT COUNT(*) as count FROM (").append(querySql).append(" ) tmp").toString();
	}

	/**
	 * 
	 * @author flatychen
	 * @date 2014-5-8
	 * @param querySql
	 * @return
	 */
	public static String pageSql(String querySql) {
		String pageSql = "SELECT *  FROM ( "
				+ querySql + " ) wrap limit ?,?  ";
		return pageSql;
	}


	/**
	 * @param pageSize
	 * @param toPage
	 */
	public Pagination(QueryPageObject pageQuery, int totalRows) {
		this.pageQuery = pageQuery;
		this.init(totalRows);
	

	}
	

	private void init(int totalRows) {
		if(pageQuery.getPageSize()!=-1){
		
			// 初始化分页数
			if (totalRows > 0) {
				this.totalRows = totalRows;
				int total = totalRows / pageQuery.getPageSize();
				if (totalRows % pageQuery.getPageSize() != 0) {
					total++;
				}
				totalPage = total;
	
			} else {
				totalPage = 1;
			}
			// 初始化请求页
			if (pageQuery.getPageNo() >  totalPage) {
				this.pageQuery.setPageNo(this.totalPage);
			}
		}
		else{
			//System.out.println(111111);
			this.totalRows = totalRows;
			this.totalPage = 1;
		}

	}

	public int getTotalRows() {
		return totalRows;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getPageSize() {
		return pageQuery.getPageSize();
	}

	public int getPageNo() {
		return pageQuery.getPageNo();
	}

	public int getBegin() {
		if(totalPage <= pageQuery.getPageNavSize()){
			begin = 1;
		} else {
			begin = Math.max(1, pageQuery.getPageNo() - pageQuery.getPageNavSize() / 2);
			begin = (totalPage - pageQuery.getPageNo() < pageQuery.getPageNavSize() / 2)?(totalPage
					- pageQuery.getPageNavSize() + 1):begin;
		}
		return begin;
	}

	public int getEnd() {
		end = Math.min(begin + pageQuery.getPageNavSize() - 1, totalPage);
		return end;
	}

	public List<T> getObjLists() {
		return objLists;
	}
	
	public int getPageNavSize2(){
		return (pageQuery.getPageNavSize()>>1) + 1;
	}

	public void setObjLists(List<T> objLists) {
		this.objLists = objLists;
	}

}
