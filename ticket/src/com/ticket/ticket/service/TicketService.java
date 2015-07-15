package com.ticket.ticket.service;

import java.io.InputStream;
import java.util.Map;

import jxl.write.WritableWorkbook;

import com.ticket.base.page.Pagination;
import com.ticket.ticket.entity.SaleBean;
import com.ticket.ticket.entity.SaleQueryBean;
import com.ticket.ticket.entity.TicketBean;
import com.ticket.ticket.entity.TicketQueryBean;
import com.ticket.ticket.entity.TicketSaveBean;

/**  
 * 
 *
 * @author wujunjun 
 * @date 2015-5-25 上午10:12:31
 * @since 1.0  
 * @Copyright 2015 东莞市邮政局All rights reserved.  
 */
public interface TicketService {

	/**
	 * 保存
	 *
	 * @param ticket
	 * @return 
	 * @author wujunjun
	 */
	boolean save(TicketSaveBean bean);

	/**
	 * 获取车票列表
	 *
	 * @param query
	 * @return 
	 * @author wujunjun
	 */
	Pagination<TicketBean> getTicketListPage(TicketQueryBean query);

	/**
	 * 售票
	 *
	 * @param ticketId
	 * @return 
	 * @author wujunjun
	 * @param userId 
	 */
	Map<String,Object> sale(Integer ticketId, Integer userId);

	/**
	 * 获取销售列表
	 *
	 * @param query
	 * @return 
	 * @author wujunjun
	 */
	Pagination<SaleBean> getSaleListPage(SaleQueryBean query);

	/**
	 * 导出报表
	 *
	 * @param query
	 * @param workbook 
	 * @author wujunjun
	 */
	void export(TicketQueryBean query, WritableWorkbook workbook);

	/**
	 * 执行导入
	 *
	 * @param inputStream
	 * @author wujunjun
	 */
	String doUpload(InputStream inputStream);

	/**
	 * 
	 *
	 * @param tId
	 * @return 
	 * @author wujunjun
	 */
	TicketBean getTicketById(Integer tId);

	/**
	 * 
	 *
	 * @param saleId
	 * @return 
	 * @author wujunjun
	 */
	SaleBean getSaleById(Integer saleId);

}
