package com.desksoft.epoch.core.base.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.desksoft.epoch.core.base.dao.AclDao;
import com.desksoft.epoch.core.base.model.ACL;
import com.desksoft.epoch.core.base.model.PermissionBit.Permission;

/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 9, 2014 3:57:06 PM
 */
@Service("aclService")
public class AclServiceImpl extends BaseServiceImpl implements AclService {

	@Resource
	private AclDao aclDao;
	
	public void setAclDao(AclDao aclDao) {
		this.aclDao = aclDao;
	}

	@Override
	public void addPermission(Integer roleId, Integer moduleId, Permission permission) throws Exception {
		this.aclDao.addPermission(roleId, moduleId, permission);
	}

	@Override
	public void deletePermission(Integer roleId, Integer moduleId, Permission permission) throws Exception {
		this.aclDao.deletePermission(roleId, moduleId, permission);
	}

	@Override
	public boolean hasPermission(Integer roleId, Integer moduleId, Permission permission) throws Exception {
		return this.aclDao.hasPermission(roleId, moduleId, permission);
	}

	@Override
	public ACL selectPermission(Integer roleId, Integer moduleId) throws Exception {
		return this.aclDao.selectPermission(roleId, moduleId);
	}

}
