package com.ticket.base.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;

/**
 *	simplejdbc 工厂
 * 
 * @author flatychen
 *
 */
public class SimpleJdbcCallFactory  {
	
	private DataSource dataSource;

	public SimpleJdbcCall getObject() {
		return new SimpleJdbcCall(dataSource);
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


}
