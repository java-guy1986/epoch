package com.desksoft.epoch.core;

import org.springframework.context.ApplicationContext;

import com.desksoft.epoch.common.SpringContextHolder;
import com.desksoft.epoch.core.base.service.UserService;

public class SimpleTest extends BaseTestCase {

	public void testGetApplicationContext(){
		
		ApplicationContext applicationContext = SpringContextHolder.getApplicationContext();
		
		UserService userService = (UserService)applicationContext.getBean("userService");
		
		
		System.out.println("userService:" + userService);
	}
}
