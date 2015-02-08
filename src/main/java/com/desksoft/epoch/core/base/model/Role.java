package com.desksoft.epoch.core.base.model;


/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 8, 2014 2:10:59 PM
 */
public class Role extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//名称
	private String name;
	
	//描述
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + ", description=" + description + ", getId()=" + getId() + ", getIsDelete()=" + getIsDelete() + ", getCreateTime()=" + getCreateTime()
				+ ", getLatestUpdateTime()=" + getLatestUpdateTime() + "]";
	}

}
