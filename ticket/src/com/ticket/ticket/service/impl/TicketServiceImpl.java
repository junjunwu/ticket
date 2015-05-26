package com.ticket.ticket.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ticket.base.page.Pagination;
import com.ticket.base.utils.MessageConstants;
import com.ticket.base.utils.MyDateUtils;
import com.ticket.ticket.dao.TicketDAO;
import com.ticket.ticket.entity.SaleBean;
import com.ticket.ticket.entity.SaleQueryBean;
import com.ticket.ticket.entity.TicketBean;
import com.ticket.ticket.entity.TicketQueryBean;
import com.ticket.ticket.entity.TicketSaveBean;
import com.ticket.ticket.service.TicketService;

/**  
 * 售票管理
 *
 * @author wujunjun 
 * @date 2015-5-25 上午10:12:53
 * @since 1.0  
 * @Copyright 2015 东莞市邮政局All rights reserved.  
 */
@Service
public class TicketServiceImpl implements TicketService {
	
	@Resource
	private TicketDAO ticketDAO;

	/* (non-Javadoc)
	 * @see com.ticket.ticket.service.TicketService#save(com.ticket.ticket.entity.TicketBean)
	 */
	@Override
	public boolean save(TicketSaveBean bean) {
		TicketBean ticket = new TicketBean();
		BeanUtils.copyProperties(bean, ticket);
		if(ticket.getId() == null){
			ticket.setDepartureTime(MyDateUtils.strToDate(bean.getDepartureDate()+" "+bean.getDepartureTime(), "yyyy/MM/dd HH:mm"));
			ticket.setCreateTime(new Date());
			ticket.setSaleNum(0);
			ticketDAO.add(ticket);
			return true;
		}
		else{
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.ticket.ticket.service.TicketService#getTicketListPage(com.ticket.ticket.entity.TicketQueryBean)
	 */
	@Override
	public Pagination<TicketBean> getTicketListPage(TicketQueryBean query) {
		return ticketDAO.getTicketListPage(query);
	}

	
	/* (non-Javadoc)
	 * @see com.ticket.ticket.service.TicketService#sale(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public String sale(Integer ticketId, Integer userId) {
		if(ticketId == null){
			return MessageConstants.PARAM_ERROR;
		}
		//加锁，防止一张票被多人购买
		synchronized (this) {
			TicketBean ticket = ticketDAO.get(ticketId);
			if(ticket==null){
				return "ticket_not_exist";
			}
			//已卖光
			if(ticket.getSaleNum() >= ticket.getTotalNum()){
				return "ticket_sold_out";
			}
			ticketDAO.addSaleRecord(ticketId,userId);
			ticketDAO.addSaleNum(ticketId);
			return MessageConstants.SUCCESS;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ticket.ticket.service.TicketService#getSaleListPage(com.ticket.ticket.entity.SaleQueryBean)
	 */
	@Override
	public Pagination<SaleBean> getSaleListPage(SaleQueryBean query){
		return ticketDAO.getSaleListPage(query);
	}
	

}
