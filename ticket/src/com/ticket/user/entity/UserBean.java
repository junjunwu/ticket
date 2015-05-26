package com.ticket.user.entity;

import java.util.Date;

/**
 * 
 * 
 * @author wujunjun
 * @date 2015-5-20 下午3:09:25
 * @since 1.0
 * @Copyright 2015 东莞市邮政局All rights reserved.
 */
public class UserBean {

	// 编号
	private Integer id;
	// 登录名
	private String loginName;
	// 真实姓名
	private String realName;
	// 密码
	private String password;
	// 性别
	private Integer sex;
	// 创建时间
	private Date createTime;
	// 类型 1为管理员，2为操作员
	private Integer userType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

}
