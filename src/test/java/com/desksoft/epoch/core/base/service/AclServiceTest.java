package com.desksoft.epoch.core.base.service;

import com.desksoft.epoch.core.BaseTestCase;
import com.desksoft.epoch.core.base.model.PermissionBit.Permission;

public class AclServiceTest extends BaseTestCase {

	private static AclService aclService = null;
	
	static {
		if (aclService == null){
			aclService = (AclService)context.getBean("aclService");
		}
	}
	
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testAddPermission() throws Exception{
		
		final Integer roleId = 8;
		final Integer moduleId = 18;
		
		aclService.addPermission(roleId, moduleId, Permission.ADD);
		aclService.addPermission(roleId, moduleId, Permission.UPDATE);
		aclService.addPermission(roleId, moduleId, Permission.SELECT);
		aclService.addPermission(roleId, moduleId, Permission.DELETE);
	}

	public void testDeletePermission() throws Exception{
		final Integer roleId = 8;
		final Integer moduleId = 18;
		aclService.deletePermission(roleId, moduleId, Permission.ADD);
		aclService.deletePermission(roleId, moduleId, Permission.SELECT);
		aclService.deletePermission(roleId, moduleId, Permission.UPDATE);
		aclService.deletePermission(roleId, moduleId, Permission.DELETE);
	}

	public void testHasPermission() throws Exception{
		final Integer roleId = 8;
		final Integer moduleId = 18;
		Boolean hasAddPermission = aclService.hasPermission(roleId, moduleId, Permission.ADD);
		Boolean hasSelectPermission = aclService.hasPermission(roleId, moduleId, Permission.SELECT);
		Boolean hasUpdatePermission = aclService.hasPermission(roleId, moduleId, Permission.UPDATE);
		Boolean hasDeletePermission = aclService.hasPermission(roleId, moduleId, Permission.DELETE);
		System.out.println(hasAddPermission + ", " + hasSelectPermission + ", " + hasUpdatePermission + ", " + hasDeletePermission);
	}

	public void testSelectPermission() {
		
	}

}
