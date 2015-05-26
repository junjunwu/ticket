package com.ticket.ticket.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * 
 * @author wujunjun
 * @date 2015-5-25 上午10:08:03
 * @since 1.0
 * @Copyright 2015 东莞市邮政局All rights reserved.
 */
public class TicketBean {

	// 编号
	private Integer id;
	// 班次
	private String coachNum;
	// 终点站
	private String terminus;
	// 发车时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date departureTime;
	// 票价
	private float price;
	// 可售
	private Integer totalNum;
	// 已售
	private Integer saleNum;
	// 车型
	private String coachType;
	// 创建时间
	private Date createTime;

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

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
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

	public Integer getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	public String getCoachType() {
		return coachType;
	}

	public void setCoachType(String coachType) {
		this.coachType = coachType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
