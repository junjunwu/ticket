package com.ticket.ticket.service;

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
	String sale(Integer ticketId, Integer userId);

	/**
	 * 获取销售列表
	 *
	 * @param query
	 * @return 
	 * @author wujunjun
	 */
	Pagination<SaleBean> getSaleListPage(SaleQueryBean query);

}
