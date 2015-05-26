package com.ticket.ticket.entity;

import com.ticket.base.page.QueryPageObject;

/**  
 * 
 *
 * @author wujunjun 
 * @date 2015-5-25 上午10:30:30
 * @since 1.0  
 * @Copyright 2015 东莞市邮政局All rights reserved.  
 */
public class TicketQueryBean extends QueryPageObject{
	
	private TicketBean ticket;
	private String beginDate;
	private String endDate;

	public TicketBean getTicket() {
		return ticket;
	}

	public void setTicket(TicketBean ticket) {
		this.ticket = ticket;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
	

}
