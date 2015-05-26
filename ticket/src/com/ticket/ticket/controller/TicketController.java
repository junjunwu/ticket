package com.ticket.ticket.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ticket.base.utils.MessageConstants;
import com.ticket.base.utils.MyDateUtils;
import com.ticket.ticket.entity.TicketQueryBean;
import com.ticket.ticket.entity.TicketSaveBean;
import com.ticket.ticket.service.TicketService;
import com.ticket.user.entity.UserBean;

/**  
 * 
 *
 * @author wujunjun 
 * @date 2015-5-25 上午10:09:18
 * @since 1.0  
 * @Copyright 2015 东莞市邮政局All rights reserved.  
 */
@Controller
@RequestMapping(value="/ticket/")
public class TicketController {
	
	@Resource
	private TicketService ticketService;
	
	@RequestMapping(value="add", method = RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("today",MyDateUtils.formatDate(new Date(), "yyyy/MM/dd"));
		return "ticket/add";
	}
	
	
	@RequestMapping(value="save", method = RequestMethod.POST)
	@ResponseBody
	public String save(TicketSaveBean ticket){
		if(ticketService.save(ticket)){
			return MessageConstants.SUCCESS;
		}
		else{
			return MessageConstants.FAIL;
		}
	}
	
	@RequestMapping(value="list", method = {RequestMethod.GET,RequestMethod.POST})
	public String list(TicketQueryBean query, Model model){
		String today = MyDateUtils.formatDate(new Date(), "yyyy-MM-dd");
		if(StringUtils.isBlank(query.getBeginDate())){
			query.setBeginDate(today);
		}
		if(StringUtils.isBlank(query.getEndDate())){
			query.setEndDate(today);
		}
		model.addAttribute("pagination",ticketService.getTicketListPage(query));
		model.addAttribute("query", query);
		//model.addAttribute("today",MyDateUtils.formatDate(new Date(), "yyyy-MM-dd"));
		return "ticket/list";
	}
	
	@RequestMapping(value="sale", method = RequestMethod.POST)
	@ResponseBody
	public String sale(Integer ticketId,HttpSession session){
		UserBean user = (UserBean) session.getAttribute("user");
		return ticketService.sale(ticketId,user.getId());
	}

}
