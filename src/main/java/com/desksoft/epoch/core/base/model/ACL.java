package com.desksoft.epoch.core.base.model;

import java.io.Serializable;

/**
 * 访问控制列表
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 8, 2014 6:16:52 PM
 */
public class ACL implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer roleId;
	
	private Integer moduleId;
	
	private Integer permission;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "ACL [id=" + id + ", roleId=" + roleId + ", moduleId=" + moduleId + ", permission=" + permission + "]";
	}
}
