package com.ticket.user.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ticket.base.dao.JdbcTemplateWrapper;
import com.ticket.base.page.Pagination;
import com.ticket.user.entity.UserBean;
import com.ticket.user.entity.UserQueryBean;

/**  
 * 用户DAO
 *
 * @author wujunjun 
 * @date 2015-5-20 上午10:26:23
 * @since 1.0  
 * @Copyright 2015 东莞市邮政局All rights reserved.  
 */
@Repository
public class UserDAO {
	
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;

	/**
	 * 分页获取用户列表
	 *
	 * @param query
	 * @return 
	 * @author wujunjun
	 */
	public Pagination<UserBean> getUserListPage(UserQueryBean query) {
		StringBuffer sql = new StringBuffer();
		List args = new ArrayList();
		sql.append("select * from user where userType=2 ");
		if(query.getUser()!= null){
			if(StringUtils.isNotBlank(query.getUser().getRealName())){
				sql.append(" and instr(realname,?)>0");
				args.add(query.getUser().getRealName());
			}
			
		}
		sql.append(" order by createTime desc");
		return jdbcTemplateWrapper.queryForPage(query,sql.toString(), UserBean.class, args.toArray());
	}

	/**
	 * 获取登录管理员信息
	 *
	 * @param loginName
	 * @return 
	 * @author wujunjun
	 */
	public UserBean getLoginAdmin(String loginName) {
		String sql = "select * from user where loginName=?";
		return jdbcTemplateWrapper.queryForBean(sql, UserBean.class, new Object[]{loginName});
	}

	/**
	 * 新增用户
	 *
	 * @param user
	 * @return 
	 * @author wujunjun
	 */
	public int add(UserBean user) {
		String sql = "insert into USER( LOGINNAME, REALNAME, PASSWORD, SEX, CREATETIME, USERTYPE) values(?, ?, ?, ?, ?, ?)";
		return jdbcTemplateWrapper.saveORUpdate(sql, new Object[]{user.getLoginName(),user.getRealName(),user.getPassword(),user.getSex(),user.getCreateTime(),user.getUserType()});
	}

	/**
	 * 判断登录名是否已存在
	 *
	 * @param loginName
	 * @return 
	 * @author wujunjun
	 */
	public int checkLoginName(String loginName) {
		String sql = "select count(1) from user where loginName=?";
		return jdbcTemplateWrapper.queryForInt(sql, new Object[]{loginName});
	}

	/**
	 * 修改密码
	 *
	 * @param userId
	 * @param password
	 * @return 
	 * @author wujunjun
	 */
	public int updatePwd(Integer userId, String password) {
		String sql = "update user set password=? where id=?";
		return jdbcTemplateWrapper.saveORUpdate(sql, new Object[]{password, userId});
	}

	/**
	 * 
	 *
	 * @param userId
	 * @return 
	 * @author wujunjun
	 */
	public UserBean getUserById(Integer userId) {
		String sql = "select * from user where id=?";
		return jdbcTemplateWrapper.queryForBean(sql, UserBean.class, new Object[]{userId});
	}

//	/**
//	 * 
//	 *
//	 * @param user
//	 * @return 
//	 * @author wujunjun
//	 */
//	public int edit(UserBean user) {
//		String sql = "update user set ";
//		return false;
//	}

}
