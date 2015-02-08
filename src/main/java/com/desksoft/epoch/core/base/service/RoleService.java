package com.desksoft.epoch.core.base.service;

import java.util.List;

import com.desksoft.epoch.core.base.model.Role;

public interface RoleService {
	
	public void addRole(Role role) throws Exception;
	
	public void deleteRole(Integer id) throws Exception;
	
	public void updateRole(Role role) throws Exception;
	
	public List<Role> selectAllRoles() throws Exception;
	
	public Role selectRoleById(Integer id) throws Exception;
	
	public Role selectRoleByName(String name) throws Exception;
}
