package com.ticket.ticket.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ticket.base.dao.JdbcTemplateWrapper;
import com.ticket.base.page.Pagination;
import com.ticket.base.utils.MyDateUtils;
import com.ticket.ticket.entity.SaleBean;
import com.ticket.ticket.entity.SaleQueryBean;
import com.ticket.ticket.entity.TicketBean;
import com.ticket.ticket.entity.TicketQueryBean;
import com.ticket.ticket.entity.TicketSaveBean;

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
		if(query.getAvailableOnly()!=null && query.getAvailableOnly()==1){
			sql.append(" and departureTime>now() ");
		}
		
		sql.append(" order by coachNum asc,departureTime asc");
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
	public int addSaleRecord(Integer ticketId, Integer userId,String validNum) {
		String sql = "insert into SALERECORD(USERID, TICKETID, SALETIME, VALIDNUM) values(?, ?, ?,?)";
		jdbcTemplateWrapper.saveORUpdate(sql, new Object[]{userId,ticketId,new Date(),validNum});
		String slq2 = "SELECT LAST_INSERT_ID()"; 
		return jdbcTemplateWrapper.queryForInt(slq2, null);
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

	/**
	 * 
	 *
	 * @param query
	 * @return 
	 * @author wujunjun
	 */
	public Pagination<SaleBean> getSaleListPage(SaleQueryBean query) {
		StringBuffer sql = new StringBuffer();
		List args = new ArrayList();
		sql.append("select s.*,u.realName as userName from saleRecord s,user u where s.userId=u.id ");
		if(query.getSale()!= null){
			if(query.getSale().getTicketId()!=null){
				sql.append(" and s.ticketId=?");
				args.add(query.getSale().getTicketId());
			}
		}
	
		sql.append(" order by saleTime desc");
		return jdbcTemplateWrapper.queryForPage(query,sql.toString(), SaleBean.class, args.toArray());
	}

	/**
	 * 批量添加
	 *
	 * @param list 
	 * @author wujunjun
	 */
	public int batchAdd(List<TicketBean> list) {
		String sql = "insert into TICKET(COACHNUM, TERMINUS, DEPARTURETIME, PRICE, TOTALNUM, SALENUM, COACHTYPE, CREATETIME) values(?, ?, ?, ?, ?, ?, ?, ?)";
		List<Object[]> listArgs = new ArrayList<Object[]>();
		for(TicketBean bean:list){
			listArgs.add(new Object[]{bean.getCoachNum(),bean.getTerminus(),bean.getDepartureTime(),bean.getPrice(),bean.getTotalNum(),bean.getSaleNum(),bean.getCoachType(),bean.getCreateTime()});
		}
		return jdbcTemplateWrapper.batchUpdate(sql, listArgs);
		
	}

	/**
	 * 
	 *
	 * @param tId
	 * @return 
	 * @author wujunjun
	 */
	public TicketBean getTicketById(Integer tId) {
		String sql = "select * from ticket where id=?";
		return jdbcTemplateWrapper.queryForBean(sql, TicketBean.class, new Object[]{tId});
	}

	/**
	 * 
	 *
	 * @param saleId
	 * @return 
	 * @author wujunjun
	 */
	public SaleBean getSaleById(Integer saleId) {
		String sql = "select * from saleRecord where id=?";
		return jdbcTemplateWrapper.queryForBean(sql, SaleBean.class, new Object[]{saleId});
	}

	/**
	 * 修改车票信息
	 *
	 * @param bean 
	 * @author wujunjun
	 */
	public int save(TicketBean bean) {
		String sql = "update ticket set coachNum=?,terminus=?,departureTime=?,price=?,totalNum=?,coachType=? where id=?";
		return jdbcTemplateWrapper.saveORUpdate(sql, new Object[]{bean.getCoachNum(),bean.getTerminus(),bean.getDepartureTime(),bean.getPrice(),bean.getTotalNum(),bean.getCoachType(),bean.getId()});
	}

}
