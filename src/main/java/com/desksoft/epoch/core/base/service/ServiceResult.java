package com.desksoft.epoch.core.base.service;

import java.io.Serializable;

/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 5, 2014 6:20:21 PM
 */
public class ServiceResult implements Serializable{

	
	public static final String CODE_SUCCESS = "0";
	
	public static final String CODE_USER_EXIST = "-2501";
	
	public static final String CODE_MODULE_EXIST = "-2502";

	public static final String MESSAGE_SUCCESS = "OK";
	
	public static final String MESSAGE_USER_EXIST = "user already exists";
	
	public static final String MESSAGE_MODULE_EXIST = "module already exists";
	
	private static final long serialVersionUID = 1L;
	
	public ServiceResult(){
		
	}
	
	public ServiceResult(String code, String message){
		this.code = code;
		this.message = message;
	}

	private String code;
	
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
