package com.desksoft.epoch.core.base.service;

import com.desksoft.epoch.core.base.model.ACL;
import com.desksoft.epoch.core.base.model.PermissionBit.Permission;

/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 9, 2014 3:54:35 PM
 */
public interface AclService {
	
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
	 * @param permission
	 * @return
	 * @throws Exception
	 */
	public boolean hasPermission(Integer roleId, Integer moduleId, Permission permission) throws Exception;
	
	/**
	 * 查询
	 * @param roleId
	 * @param moduleId
	 * @return
	 * @throws Exception
	 */
	public ACL selectPermission(Integer roleId, Integer moduleId) throws Exception;
}
