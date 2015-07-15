package com.ticket.ticket.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ticket.base.utils.MessageConstants;
import com.ticket.base.utils.MyDateUtils;
import com.ticket.ticket.entity.SaleBean;
import com.ticket.ticket.entity.SaleQueryBean;
import com.ticket.ticket.entity.TicketBean;
import com.ticket.ticket.entity.TicketQueryBean;
import com.ticket.ticket.entity.TicketSaveBean;
import com.ticket.ticket.service.TicketService;
import com.ticket.user.entity.UserBean;

/**  
 * 售票管理
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
	public String add(Integer ticketId,Model model){
		if(ticketId!=null){
			model.addAttribute("ticket",ticketService.getTicketById(ticketId));
		}
		model.addAttribute("today",MyDateUtils.formatDate(new Date(), "yyyy/MM/dd"));
		return "ticket/add";
	}
	
	@RequestMapping(value="print", method = RequestMethod.GET)
	public String print(Integer saleId, Model model){
		SaleBean sale = ticketService.getSaleById(saleId);
		if(sale!=null){
			
			TicketBean ticket = ticketService.getTicketById(sale.getTicketId());
			model.addAttribute("ticket",ticket);
		}
		model.addAttribute("sale",sale);
		return "ticket/print";
	}
	
	@RequestMapping(value="upload", method = RequestMethod.GET)
	public String upload(Model model){
		return "ticket/upload";
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
		//System.out.println("==========="  +query.getAvailableOnly());
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
	public Map<String,Object> sale(Integer ticketId,HttpSession session){
		UserBean user = (UserBean) session.getAttribute("user");
		return ticketService.sale(ticketId,user.getId());
	}
	
	@RequestMapping(value="saleList", method = {RequestMethod.GET,RequestMethod.POST})
	public String saleList(SaleQueryBean query, Model model){
		query.setPageSize(-1);
		model.addAttribute("pagination",ticketService.getSaleListPage(query));
		model.addAttribute("query", query);
		return "ticket/saleList";
	}
	
	@RequestMapping(value = "/upload", method =  RequestMethod.POST)
    public String upload(MultipartFile file,Model model) throws Exception{
		String msg = "";
		//System.out.println(file.getOriginalFilename());
		if(file.isEmpty()){
			msg = "文件不能为空";
		}
		msg = ticketService.doUpload(file.getInputStream());
		model.addAttribute("msg", msg);	
		return "ticket/upload";
		
	}
	
	// 导出excel文件
	@RequestMapping("export")
	public void exportDigitCardExcel(TicketQueryBean query,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		request.setCharacterEncoding("UTF-8");
		try {
			response.setContentType("application/x-excel");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ new String(("黄江长途车站销售明细表").getBytes(), "ISO8859-1") + 
					MyDateUtils.formatDate(new Date(), "yyyyMMddHHmmss")+ ".xls");
			WritableWorkbook workbook = Workbook.createWorkbook(out);
			ticketService.export(query,workbook);
			
			// 把工作簿写入文件
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

}
