package com.ticket.ticket.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ticket.base.dao.JdbcTemplateWrapper;
import com.ticket.base.page.Pagination;
import com.ticket.ticket.entity.TicketBean;
import com.ticket.ticket.entity.TicketQueryBean;

/**  
 * 
 *
 * @author wujunjun 
 * @date 2015-5-25 上午10:13:51
 * @since 1.0  
 * @Copyright 2015 东莞市邮政局All rights reserved.  
 */
@Repository
public class TicketDAO {
	
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;

	/**
	 * 新增
	 *
	 * @param ticket 
	 * @author wujunjun
	 */
	public int add(TicketBean ticket) {
		String sql = "insert into TICKET(COACHNUM, TERMINUS, DEPARTURETIME, PRICE, TOTALNUM, SALENUM, COACHTYPE, CREATETIME) values(?, ?, ?, ?, ?, ?, ?, ?)";
		return jdbcTemplateWrapper.saveORUpdate(sql, new Object[]{ticket.getCoachNum(), ticket.getTerminus(),ticket.getDepartureTime(),ticket.getPrice(),ticket.getTotalNum(),ticket.getSaleNum(),ticket.getCoachType(),ticket.getCreateTime()});
	}

	/**
	 * 
	 *
	 * @param query
	 * @return 
	 * @author wujunjun
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Pagination<TicketBean> getTicketListPage(TicketQueryBean query) {
		StringBuffer sql = new StringBuffer();
		List args = new ArrayList();
		sql.append("select * from ticket where 1=1 ");
		if(query.getTicket()!= null){
			//班次
			if(StringUtils.isNotBlank(query.getTicket().getCoachNum())){
				sql.append(" and instr(coachNum,?)>0");
				args.add(query.getTicket().getCoachNum());
			}
			//车型
			if(StringUtils.isNotBlank(query.getTicket().getCoachType())){
				sql.append(" and instr(coachType,?)>0");
				args.add(query.getTicket().getCoachType());
			}
			//终点站
			if(StringUtils.isNotBlank(query.getTicket().getTerminus())){
				sql.append(" and instr(terminus,?)>0");
				args.add(query.getTicket().getTerminus());
			}
		}
		//发车日期
		if(StringUtils.isNotBlank(query.getBeginDate())){
			sql.append(" and date_format(departureTime,'%Y-%m-%d')>=?");
			args.add(query.getBeginDate());
		}
		if(StringUtils.isNotBlank(query.getEndDate())){
			sql.append(" and date_format(departureTime,'%Y-%m-%d')<=?");
			args.add(query.getEndDate());
		}
		
		sql.append(" order by createTime desc");
		return jdbcTemplateWrapper.queryForPage(query,sql.toString(), TicketBean.class, args.toArray());
	}

	/**
	 * 根据住建获取票信息
	 *
	 * @param ticketId
	 * @return 
	 * @author wujunjun
	 */
	public TicketBean get(Integer ticketId) {
		String sql = "select * from ticket where id=?";
		return jdbcTemplateWrapper.queryForBean(sql, TicketBean.class, new Object[]{ticketId});
	}

	/**
	 * 增加售票记录
	 *
	 * @param ticketId
	 * @param userId 
	 * @author wujunjun
	 */
	public int addSaleRecord(Integer ticketId, Integer userId) {
		String sql = "insert into SALERECORD(USERID, TICKETID, SALETIME) values(?, ?, ?)";
		return jdbcTemplateWrapper.saveORUpdate(sql, new Object[]{userId,ticketId,new Date()});
	}

	/**
	 * 票信息已售数量加一
	 *
	 * @param ticketId 
	 * @author wujunjun
	 */
	public int addSaleNum(Integer ticketId) {
		String sql = "update ticket set salenum=saleNum+1 where id=?";
		return jdbcTemplateWrapper.saveORUpdate(sql, new Object[]{ticketId});
	}

}
