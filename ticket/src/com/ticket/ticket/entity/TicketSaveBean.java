package com.ticket.ticket.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**  
 * 
 *
 * @author wujunjun 
 * @date 2015-5-25 下午6:45:11
 * @since 1.0  
 * @Copyright 2015 东莞市邮政局All rights reserved.  
 */
public class TicketSaveBean {

	// 编号
	private Integer id;
	// 班次
	private String coachNum;
	// 终点站
	private String terminus;
	// 发车日期
	private String departureDate;
	// 发车时间
	private String departureTime;
	// 票价
	private float price;
	// 可售
	private Integer totalNum;
	// 车型
	private String coachType;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCoachNum() {
		return coachNum;
	}
	public void setCoachNum(String coachNum) {
		this.coachNum = coachNum;
	}
	public String getTerminus() {
		return terminus;
	}
	public void setTerminus(String terminus) {
		this.terminus = terminus;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public String getCoachType() {
		return coachType;
	}
	public void setCoachType(String coachType) {
		this.coachType = coachType;
	}
	
	
}
