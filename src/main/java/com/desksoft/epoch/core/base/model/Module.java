package com.desksoft.epoch.core.base.model;

import java.util.List;

/**
 * 模块实体
 * Note:hashCode() & equals()方法只会校验name和urlPath是否相同，
 * 也就是说，name和urlPath相同的两个模块会被认为是同一个模块，事实上，
 * 这种情况是不应该出现的。
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 8, 2014 5:34:51 PM
 */
public class Module extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//父模块id
	private Integer pid;
	
	//名称
	private String name;
	
	//描述
	private String description;
	
	//访问路径
	private String urlPath;
	
	//是否是叶子模块
	private Boolean isLeaf;
	
	//子模块列表
	private List<Module> children;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

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

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public List<Module> getChildren() {
		return children;
	}

	public void setChildren(List<Module> children) {
		this.children = children;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((urlPath == null) ? 0 : urlPath.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Module other = (Module) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (urlPath == null) {
			if (other.urlPath != null)
				return false;
		} else if (!urlPath.equals(other.urlPath))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Module [id= "+ getId()+", pid=" + pid + ", name=" + name + ", description=" + description + ", urlPath=" + urlPath + ", isLeaf=" + isLeaf + ", children=" + children + "]";
	}
	
}
