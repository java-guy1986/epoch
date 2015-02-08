package com.desksoft.epoch.core.role.service;

import java.util.List;

import com.desksoft.epoch.core.BaseTestCase;
import com.desksoft.epoch.core.base.model.Role;
import com.desksoft.epoch.core.base.service.RoleService;

public class RoleServiceTest extends BaseTestCase {

	
	private static RoleService roleService = null;
	
	static {
		if (roleService == null){
			roleService = (RoleService)context.getBean("roleService");
		}
	}
	
	protected void setUp() throws Exception {
	}

	protected void tearDown() throws Exception {
	}

	public void testAddRole() throws Exception{
		for (int i = 0; i < 5; i++){
			Role role = new Role();
			role.setName("系统管理员_" + i);
			role.setDescription("系统管理员管理系统所有用户_" + i);
			roleService.addRole(role);	
		}
	}

	public void testDeleteRole() throws Exception {
		roleService.deleteRole(7);
	}

	public void testUpdateRole() throws Exception {
		
		Role role = new Role();
		role.setId(7);
		role.setName("更改角色名");
		role.setIsDelete(false);
		
		roleService.updateRole(role);
	}

	public void testSelectAllRoles() throws Exception {
		
		List<Role> roleList = roleService.selectAllRoles();
		for (Role role : roleList){
			System.out.println(role);
		}
	}

}
