package com.ticket.user.service;

import com.ticket.base.page.Pagination;
import com.ticket.user.entity.UserBean;
import com.ticket.user.entity.UserQueryBean;

/**  
 * 
 *
 * @author wujunjun 
 * @date 2015-5-20 上午10:28:03
 * @since 1.0  
 * @Copyright 2015 东莞市邮政局All rights reserved.  
 */
public interface UserService {

	/**
	 * 分页获取用户列表
	 *
	 * @param query
	 * @return 
	 * @author wujunjun
	 */
	Pagination<UserBean> getUserListPage(UserQueryBean query);

	/**
	 * 获取登录管理员信息
	 *
	 * @param loginName
	 * @return 
	 * @author wujunjun
	 */
	UserBean getLoginAdmin(String loginName);

	/**
	 * 新增操作员
	 *
	 * @param user
	 * @return 
	 * @author wujunjun
	 */
	boolean save(UserBean user);

	/**
	 * 判断登录名是否可用
	 *
	 * @param loginName
	 * @return 
	 * @author wujunjun
	 */
	boolean checkLoginName(String loginName);

	/**
	 * 充值密码
	 *
	 * @param userId
	 * @return 
	 * @author wujunjun
	 */
	boolean resetPwd(Integer userId);

	/**
	 * 修改密码
	 *
	 * @param prePwd
	 * @param newPwd
	 * @return 
	 * @author wujunjun
	 * @param userId 
	 */
	String updatePwd(Integer userId, String prePwd, String newPwd);

}
