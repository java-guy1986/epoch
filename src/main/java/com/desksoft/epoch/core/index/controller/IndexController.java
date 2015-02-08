package com.desksoft.epoch.core.index.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 5, 2014 3:14:28 PM
 */
@Controller
public class IndexController {
	
	@RequestMapping(value="index", method = RequestMethod.GET)
	public String openIndex(HttpServletRequest request){
		return "index";
	}
}
