package com.ticket.user.entity;

import com.ticket.base.page.QueryPageObject;

/**  
 * 
 *
 * @author wujunjun 
 * @date 2015-5-20 上午10:38:35
 * @since 1.0  
 * @Copyright 2015 东莞市邮政局All rights reserved.  
 */
public class UserQueryBean extends QueryPageObject{
	
	private UserBean user;
	private Integer userType = 0;

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public Integer getUserType() {
		if(userType==null){
			return 0;
		}
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	
	

}
