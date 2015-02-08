package com.desksoft.epoch.core.base.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.desksoft.epoch.core.base.dao.RoleDao;
import com.desksoft.epoch.core.base.model.Role;

/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 8, 2014 3:14:44 PM
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDao roleDao;
	
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public void addRole(Role role) throws Exception {
		if (role == null){
			throw new NullPointerException("role should not be null");
		}
		
		Role dbRole = selectRoleByName(role.getName());
		if (dbRole != null){
			return;
		}
		
		this.roleDao.addRole(role);
	}

	@Override
	public void deleteRole(Integer id) throws Exception {
		if (id == null){
			throw new NullPointerException("id should not be null");
		}
		
		this.roleDao.deleteRole(id);
	}

	@Override
	public void updateRole(Role role) throws Exception {
		if (role == null){
			throw new NullPointerException("role should not be null");
		}
		this.roleDao.updateRole(role);
	}

	@Override
	public List<Role> selectAllRoles() throws Exception {
		return this.roleDao.selectAllRoles();
	}

	@Override
	public Role selectRoleById(Integer id) throws Exception {
		return this.roleDao.selectRoleById(id);
	}

	@Override
	public Role selectRoleByName(String name) throws Exception {
		return this.roleDao.selectRoleByName(name);
	}

}
