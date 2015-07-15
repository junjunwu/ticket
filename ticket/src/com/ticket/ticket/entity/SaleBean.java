package com.ticket.ticket.entity;

import java.util.Date;

/**
 * 
 * 
 * @author wujunjun
 * @date 2015-5-26 上午10:50:30
 * @since 1.0
 * @Copyright 2015 东莞市邮政局All rights reserved.
 */
public class SaleBean {

	// 编号
	private Integer id;
	// 操作员ID
	private Integer userId;
	// 车票ID
	private Integer ticketId;
	// 销售时间
	private Date saleTime;
	//凭证号
	private String validNum;
	
	private String userName;
	
	
	

	public String getValidNum() {
		return validNum;
	}

	public void setValidNum(String validNum) {
		this.validNum = validNum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	public Date getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(Date saleTime) {
		this.saleTime = saleTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
