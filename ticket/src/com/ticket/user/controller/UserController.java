package com.ticket.user.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ticket.base.page.Pagination;
import com.ticket.base.utils.MessageConstants;
import com.ticket.ticket.entity.TicketBean;
import com.ticket.user.entity.UserBean;
import com.ticket.user.entity.UserQueryBean;
import com.ticket.user.service.UserService;

/**  
 * 
 *
 * @author wujunjun 
 * @date 2015-5-19 下午4:58:27
 * @since 1.0  
 * @Copyright 2015 东莞市邮政局All rights reserved.  
 */
@Controller
@RequestMapping(value="/user/")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value="list")
	public String list(Model model,UserQueryBean query){
		//Pagination<UserBean> userList = userService.getUserListPage(query);
		model.addAttribute("pagination",userService.getUserListPage(query));
		model.addAttribute("query", query);
		model.addAttribute("menuName", "用户列表");
		return "user/list";
	}

	@RequestMapping(value="add", method = RequestMethod.GET)
	public String add(){
		return "user/add";
	}
	
	@RequestMapping(value="save", method = RequestMethod.POST)
	@ResponseBody
	public String save(UserBean user){
		if(userService.save(user)){
			return MessageConstants.SUCCESS;
		}
		else{
			return MessageConstants.FAIL;
		}
	}
	
	@RequestMapping(value="resetPwd", method = RequestMethod.POST)
	@ResponseBody
	public String resetPwd(Integer userId){
		if(userService.resetPwd(userId)){
			return MessageConstants.SUCCESS;
		}
		else{
			return MessageConstants.FAIL;
		}
	}
	
	@RequestMapping(value="checkLoginName", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkLoginName(String loginName){
		if(userService.checkLoginName(loginName)){
			return true;
		}
		else{
			return false;
		}
	}
	
	@RequestMapping(value="updatePwd", method = RequestMethod.GET)
	public String updatePwd(){
		return "user/updatePwd";
	}
	
	@RequestMapping(value="updatePwd", method = RequestMethod.POST)
	@ResponseBody
	public String updatePwd(Integer userId,String prePwd,String newPwd){
		
		return userService.updatePwd(userId, prePwd,newPwd);
	}
}
