package com.ticket.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ticket.user.entity.UserBean;
import com.ticket.user.service.UserService;

/**  
 * 
 *
 * @author wujunjun 
 * @date 2015-5-20 下午3:06:30
 * @since 1.0  
 * @Copyright 2015 东莞市邮政局All rights reserved.  
 */
@Controller
public class LoginController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("admin");
		return "redirect:/login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String loginName,String password,Model model,HttpSession session) {
		//System.out.println(DigestUtils.md5Hex("dg11185"));
		String msg = "";
		if(StringUtils.isBlank(loginName)||StringUtils.isBlank(password)){
			msg = "用户名和密码不能为空";
			model.addAttribute("msg", msg);
			return "login";
		}
		UserBean user = userService.getLoginAdmin(loginName);
		if(user == null){
			msg = "用户名不存在";
			model.addAttribute("msg", msg);
			return "login";
		}
		if(DigestUtils.md5Hex(password).equals(user.getPassword())){
			session.setAttribute("user", user);
			return "redirect:/user/list";
		}
		else{
			msg = "密码不正确";
			model.addAttribute("msg", msg);
			model.addAttribute("loginName", loginName);
			return "login";
		}
	}

}
