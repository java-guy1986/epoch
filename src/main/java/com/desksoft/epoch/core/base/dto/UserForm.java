package com.desksoft.epoch.core.base.dto;

import java.util.Date;
import java.util.List;
import com.desksoft.epoch.core.base.model.Role;
import com.desksoft.epoch.core.base.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;

public class UserForm extends PagerDTO{

	private String id;
	
	private String name;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	private Date createTime;
	
	private List<User> userList;
	
	private List<Role> roleList;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	

}
