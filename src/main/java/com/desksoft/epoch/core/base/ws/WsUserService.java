package com.desksoft.epoch.core.base.ws;

import javax.annotation.Resource;

import com.desksoft.epoch.core.base.model.User;
import com.desksoft.epoch.core.base.service.UserService;

public class WsUserService {

	@Resource
	private UserService userService;
	
	public void addUser(User user){
		try {
			userService.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
