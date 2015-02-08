package com.desksoft.epoch.core.base.model;

import java.util.Date;

/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 4, 2014 11:29:26 AM
 */
public abstract class BaseModel {

	//删除标识:已删除
	protected static int DELETE_FLAG_YES = 1;
	
	//删除标识：未删除
	protected static int DELETE_FLAG_NO = 0;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	
	private String modelId;
	
	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	//是否删除
	private Boolean isDelete;
	
	//创建时间
	private Date createTime;
	
	//最近更新时间
	private Date latestUpdateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLatestUpdateTime() {
		return latestUpdateTime;
	}

	public void setLatestUpdateTime(Date latestUpdateTime) {
		this.latestUpdateTime = latestUpdateTime;
	}

}
