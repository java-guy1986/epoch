package com.desksoft.epoch.core.base.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.desksoft.epoch.core.base.model.User;
import com.desksoft.epoch.core.base.service.UserService;

/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 5, 2014 2:23:21 PM
 */
@Controller
public class UserController {
	
	@Resource
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value="/front/user/addUser")
	@ResponseBody
	public User addUser(User user) throws Exception{
		System.out.println(user);
		return user;
	}
}
