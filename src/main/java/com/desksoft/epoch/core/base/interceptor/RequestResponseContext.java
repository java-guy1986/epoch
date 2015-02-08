package com.desksoft.epoch.core.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestResponseContext {

	private static ThreadLocal<HttpServletRequest> REQUEST_CHREADLOCAL = new ThreadLocal<HttpServletRequest>();
	 
    private static ThreadLocal<HttpServletResponse> REPONSE_THREADLOCAL = new ThreadLocal<HttpServletResponse>();
 
    public static void setRequest(HttpServletRequest request) {
    	REQUEST_CHREADLOCAL.set(request);
    }
 
    public static HttpServletRequest getRequest() {
        return REQUEST_CHREADLOCAL.get();
    }
 
    public static void removeRequest() {
    	REQUEST_CHREADLOCAL.remove();
    }
 
    public static void setResponse(HttpServletResponse response) {
    	REPONSE_THREADLOCAL.set(response);
    }
 
    public static HttpServletResponse getResponse() {
        return REPONSE_THREADLOCAL.get();
    }
 
    public static void removeResponse() {
    	REPONSE_THREADLOCAL.remove();
    }
}
