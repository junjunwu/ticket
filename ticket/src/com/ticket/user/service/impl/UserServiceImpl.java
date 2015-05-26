package com.ticket.user.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.ticket.base.page.Pagination;
import com.ticket.user.dao.UserDAO;
import com.ticket.user.entity.UserBean;
import com.ticket.user.entity.UserQueryBean;
import com.ticket.user.service.UserService;

/**  
 * 用户管理
 *
 * @author wujunjun 
 * @date 2015-5-20 上午10:28:23
 * @since 1.0  
 * @Copyright 2015 东莞市邮政局All rights reserved.  
 */
@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserDAO userDAO;

	/* (non-Javadoc)
	 * @see com.ticket.user.service.UserService#getUserListPage(com.ticket.user.entity.UserQueryBean)
	 */
	@Override
	public Pagination<UserBean> getUserListPage(UserQueryBean query) {
		return userDAO.getUserListPage(query);
	}

	/* (non-Javadoc)
	 * @see com.ticket.user.service.UserService#getLoginAdmin(java.lang.String)
	 */
	@Override
	public UserBean getLoginAdmin(String loginName) {
		return userDAO.getLoginAdmin(loginName);
	}

	/* (non-Javadoc)
	 * @see com.ticket.user.service.UserService#save(com.ticket.user.entity.UserBean)
	 */
	@Override
	public boolean save(UserBean user) {
		if(user.getId()==null){
			user.setCreateTime(new Date());
			user.setPassword(DigestUtils.md5Hex(user.getPassword()));
			user.setUserType(2);
			return userDAO.add(user) > 0;
		}
		else{
			//user.setPassword(DigestUtils.md5Hex(user.getPassword()));
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.ticket.user.service.UserService#checkLoginName(java.lang.String)
	 */
	@Override
	public boolean checkLoginName(String loginName) {
		return userDAO.checkLoginName(loginName)== 0;
	}

	/* (non-Javadoc)
	 * @see com.ticket.user.service.UserService#resetPwd(java.lang.Integer)
	 */
	@Override
	public boolean resetPwd(Integer userId) {
		return userDAO.updatePwd(userId,DigestUtils.md5Hex("123456")) > 0;
	}
}
