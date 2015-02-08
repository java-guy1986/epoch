package com.desksoft.epoch.core.base.model;


import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 4, 2014 11:29:38 AM
 */
public class User extends BaseModel{
	
	//正常状态
	public static final int STATUS_NORMAL = 1;
	
	//锁定状态
	public static final int STATUS_LOCK = 2;
	
	//过期状态
	public static final int STATUS_PERIOD = 3;
	
	//停用状态
	public static final int STATUS_PAUSE = 4;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private String honeyName;
	
	private Integer status;
	
	private String password;
	
	private List<Role> roleList;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
	private Date registerTime;
	
	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHoneyName() {
		return honeyName;
	}

	public void setHoneyName(String honeyName) {
		this.honeyName = honeyName;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	/*public String toString() {
		return "User [username=" + username + ", honeyName=" + honeyName + ", status=" + status + ", password=" + password + ", getId()=" + getId() + ", isDelete()=" + getIsDelete()
				+ ", getCreateTime()=" + getCreateTime() + ", getLatestUpdateTime()=" + getLatestUpdateTime() + "]";
	}*/

	/*protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}*/
}
