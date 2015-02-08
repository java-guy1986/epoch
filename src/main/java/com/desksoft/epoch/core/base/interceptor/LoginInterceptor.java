package com.desksoft.epoch.core.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.desksoft.epoch.core.base.model.User;

/**
 * 登录拦截器
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 8, 2014 10:19:06 AM
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String servletPath = request.getServletPath();
		if (StringUtils.isNotBlank(servletPath)
				&& servletPath.startsWith("/admin")){
			if ((User)request.getSession().getAttribute("user") == null){
				response.sendRedirect(request.getContextPath() + "/login.html");
				return false;
			}
		}
		
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

}
