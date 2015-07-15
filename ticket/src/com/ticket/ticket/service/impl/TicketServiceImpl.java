package com.ticket.ticket.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jxl.Cell;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
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
		ticket.setDepartureTime(MyDateUtils.strToDate(bean.getDepartureDate()+" "+bean.getDepartureTime(), "yyyy/MM/dd HH:mm"));
		if(ticket.getId() == null){
			ticket.setCreateTime(new Date());
			ticket.setSaleNum(0);
			ticketDAO.add(ticket);
			return true;
		}
		else{
			ticketDAO.save(ticket);
			return true;
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
	public Map<String,Object> sale(Integer ticketId, Integer userId) {
		Map<String,Object> map = new HashMap<String, Object>();
		if(ticketId == null){
			map.put("status", MessageConstants.PARAM_ERROR);
			return map;
		}
		//加锁，防止一张票被多人购买
		synchronized (this) {
			TicketBean ticket = ticketDAO.get(ticketId);
			if(ticket==null){
				map.put("status", "ticket_not_exist");
				return map;
			}
			//已卖光
			if(ticket.getSaleNum() >= ticket.getTotalNum()){
				map.put("status", "ticket_sold_out");
				return map;
			}
			String validNum = MyDateUtils.formatDate(new Date(), "yyMMddHHmm");
			Integer saleId = ticketDAO.addSaleRecord(ticketId,userId,validNum);
			ticketDAO.addSaleNum(ticketId);
			map.put("status", MessageConstants.SUCCESS);
			map.put("saleId", saleId);
			return map;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ticket.ticket.service.TicketService#getSaleListPage(com.ticket.ticket.entity.SaleQueryBean)
	 */
	@Override
	public Pagination<SaleBean> getSaleListPage(SaleQueryBean query){
		return ticketDAO.getSaleListPage(query);
	}

	/* (non-Javadoc)
	 * @see com.ticket.ticket.service.TicketService#export(com.ticket.ticket.entity.TicketQueryBean, jxl.write.WritableWorkbook)
	 */
	@Override
	public void export(TicketQueryBean query, WritableWorkbook workbook) {
		
		try {
			WritableSheet ws = workbook.createSheet("黄江长途车站销售明细表", 0);
			query.setPageSize(-1);
			List<TicketBean> list =ticketDAO.getTicketListPage(query).getObjLists();
			
			//标题样式
			WritableFont titleFont= new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD); 
			WritableCellFormat titleStyle = new WritableCellFormat(titleFont); 
			//把水平对齐方式指定为居中
			titleStyle.setAlignment(jxl.format.Alignment.CENTRE);
			//把垂直对齐方式指定为居中
			titleStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        
			ws.addCell(new Label(0, 0, "黄江长途车站销售明细表",titleStyle));
			//设置行高
			ws.setRowView(0, 500);
			//合并单元格
			ws.mergeCells(0, 0, 6, 0);
			
			ws.setColumnView(0, 15); // 设置列的宽度  
			ws.setColumnView(1, 15); // 设置列的宽度  
			ws.setColumnView(2, 25); // 设置列的宽度  
			ws.setColumnView(3, 15); // 设置列的宽度  
			ws.setColumnView(4, 15); // 设置列的宽度  
			ws.setColumnView(5, 15); // 设置列的宽度
			ws.setColumnView(6, 15); // 设置列的宽度
			
			WritableFont commonFont= new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD);
			jxl.write.NumberFormat nf = new jxl.write.NumberFormat("0.00"); 
			//单元格样式
			WritableCellFormat commonStyle = new WritableCellFormat(commonFont,nf); 
	        // 设置居中 
			commonStyle.setAlignment(Alignment.CENTRE); 
	        // 设置边框线 
			commonStyle.setBorder(Border.ALL, BorderLineStyle.THIN); 
	
			//添加表头信息，Label对象三个参数意思：【列，行，值，样式】  
			ws.addCell(new Label(0, 1, "班次",commonStyle));
			ws.addCell(new Label(1, 1, "终点站",commonStyle));
			ws.addCell(new Label(2, 1, "发车时间",commonStyle));
			ws.addCell(new Label(3, 1, "票价",commonStyle));
			ws.addCell(new Label(4, 1, "可售",commonStyle));
			ws.addCell(new Label(5, 1, "已售",commonStyle));
			ws.addCell(new Label(6, 1, "车型",commonStyle));
			
			if (null != list && list.size() > 0) {
				for (int i = 1; i <= list.size(); i++) {
					ws.addCell(new Label(0, i+1, list.get(i - 1).getCoachNum(),commonStyle));
					ws.addCell(new Label(1, i+1, list.get(i - 1).getTerminus(),commonStyle));
					ws.addCell(new Label(2, i+1, MyDateUtils.formatDate(list.get(i - 1).getDepartureTime(),"yyyy/MM/dd HH:mm"),commonStyle));
					ws.addCell(new jxl.write.Number(3, i+1, list.get(i - 1).getPrice(),commonStyle));
					ws.addCell(new Label(4, i+1, list.get(i - 1).getTotalNum().toString(),commonStyle));
					ws.addCell(new Label(5, i+1, list.get(i - 1).getSaleNum().toString(),commonStyle));
					ws.addCell(new Label(6, i+1, list.get(i - 1).getCoachType().equals("1")?"大型卧铺":"大型座席",commonStyle));
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		
	}

	/* (non-Javadoc)
	 * @see com.ticket.ticket.service.TicketService#doUpload(java.io.InputStream, java.lang.Integer)
	 */
	@Override
	public String doUpload(InputStream inputStream) {
		String msg = "";
		Workbook book = null;
		try {
			book = Workbook.getWorkbook(inputStream);
			if (book == null) {
				throw new Exception("获取文件的workbook对象失败！");
			}
			Sheet sheet = book.getSheet(0);
			List<TicketBean> list = new ArrayList<TicketBean>();
			for (int i = 2; i < sheet.getRows(); i++) {
				if(StringUtils.isNotBlank(sheet.getCell(0, i).getContents())){
					TicketBean ticket = new TicketBean();
//					Cell[] row = sheet.getRow(i);
					ticket.setCoachNum(sheet.getCell(0, i).getContents());
					ticket.setTerminus(sheet.getCell(1, i).getContents());
					ticket.setDepartureTime(MyDateUtils.strToDate(getDateStr(sheet.getCell(2, i),"yyyy/MM/dd")+" " +getDateStr(sheet.getCell(3, i),"HH:mm"), "yyyy/MM/dd HH:mm"));
					ticket.setPrice(Float.parseFloat(sheet.getCell(4, i).getContents()));
					ticket.setTotalNum(Integer.parseInt(sheet.getCell(5, i).getContents()));
					ticket.setSaleNum(0);
//					ticket.setSaleNum(Integer.parseInt(sheet.getCell(6, i).getContents()));
					ticket.setCoachType(sheet.getCell(6, i).getContents());
					ticket.setCreateTime(new Date());
					list.add(ticket);
//					for (int j = 0; j < row.length; j++) {
//						Cell cell = sheet.getCell(j, i);
//						if(cell.getType() == CellType.DATE){
//							
//							System.out.print(getDateStr(cell,"yyyy/MM/dd") + "  ");
//						}
//					}
//					System.out.println();
				}
			}
//			for(TicketBean bean:list){
//				System.out.println(bean.toString());
//				System.out.println(bean.getCoachNum());
//			}
			int result  = ticketDAO.batchAdd(list);
			msg = "成功导入" + result + "条记录";
			//msg = "成功添加" + 0 + "条记录";
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg = "文件有错误";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg = "文件有错误";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg = "文件有错误";
		}
		finally{
			if(book!=null){
				book.close();
			}
		}
		return msg;
	}

	/**
	 * 
	 *
	 * @param cell 
	 * @author wujunjun
	 */
	private String getDateStr(Cell cell,String pattern) {
		return MyDateUtils.formatDate(((DateCell) cell).getDate(), pattern);
		
	}

	/* (non-Javadoc)
	 * @see com.ticket.ticket.service.TicketService#getTicketById(java.lang.Integer)
	 */
	@Override
	public TicketBean getTicketById(Integer tId) {
		return ticketDAO.getTicketById(tId);
	}

	/* (non-Javadoc)
	 * @see com.ticket.ticket.service.TicketService#getSaleById(java.lang.Integer)
	 */
	@Override
	public SaleBean getSaleById(Integer saleId) {
		return ticketDAO.getSaleById(saleId);
	}

}
