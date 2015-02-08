package com.desksoft.epoch.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 7, 2014 10:57:27 AM
 */
public class ApplicationContextFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*ApplicationContext.setRequest((HttpServletRequest)request);
		ApplicationContext.setResponse((HttpServletResponse)response);
		try {
			chain.doFilter(request, response);
		} finally{
			ApplicationContext.removeRequest();
			ApplicationContext.removeResponse();
		}*/
	}

	@Override
	public void destroy() {
		
	}

}
