package com.desksoft.epoch.core.base.dao;


import java.util.List;

import com.desksoft.epoch.core.base.model.Role;

/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 8, 2014 2:50:33 PM
 */
public interface RoleDao {

	public void addRole(Role role) throws Exception;
	
	public void deleteRole(Integer id) throws Exception;
	
	public void updateRole(Role role) throws Exception;
	
	public List<Role> selectAllRoles() throws Exception;

	public Role selectRoleById(Integer id) throws Exception;

	public Role selectRoleByName(String name) throws Exception;
}
