package com.desksoft.epoch.core.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.desksoft.epoch.core.base.interceptor.RequestResponseContext;

/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 5, 2014 3:13:06 PM
 */
@Controller
public class BaseController {

	protected HttpServletRequest getServletRequest(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	protected HttpServletResponse getServletResponse(){
		return RequestResponseContext.getResponse();
	}
	protected HttpSession getHttpSession(HttpServletRequest request){
		return request.getSession();
	}
}
