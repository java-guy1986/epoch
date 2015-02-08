package com.desksoft.epoch.core.base.dao;


import com.desksoft.epoch.core.base.model.ACL;
import com.desksoft.epoch.core.base.model.PermissionBit.Permission;

/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 9, 2014 2:24:22 PM
 */
public interface AclDao {
	
	
	/**
	 * 授权
	 * @param roleId
	 * @param moduleId
	 * @param permission
	 * @throws Exception
	 */
	public void addPermission(Integer roleId, Integer moduleId, Permission permission) throws Exception;
	
	/**
	 * 删除权限
	 * @param roleId
	 * @param moduleId
	 * @param permission
	 * @throws Exception
	 */
	public void deletePermission(Integer roleId, Integer moduleId, Permission permission) throws Exception;
	
	/**
	 * 认证
	 * @param roleId
	 * @param moduleId
	 * @return
	 */
	public boolean hasPermission(Integer roleId, Integer moduleId, Permission permission) throws Exception;
	
	/**
	 * 查询权限
	 * @param roleId
	 * @param moduleId
	 * @return null 表示 记录不存在
	 * @throws Exception
	 */
	public ACL selectPermission(Integer roleId, Integer moduleId) throws Exception;
}
