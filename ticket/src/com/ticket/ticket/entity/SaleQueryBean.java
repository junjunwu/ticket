package com.ticket.ticket.entity;

import com.ticket.base.page.QueryPageObject;

/**  
 * 
 *
 * @author wujunjun 
 * @date 2015-5-26 上午10:58:37
 * @since 1.0  
 * @Copyright 2015 东莞市邮政局All rights reserved.  
 */
public class SaleQueryBean extends QueryPageObject{

	private SaleBean sale;

	public SaleBean getSale() {
		return sale;
	}

	public void setSale(SaleBean sale) {
		this.sale = sale;
	}
	
	
}
