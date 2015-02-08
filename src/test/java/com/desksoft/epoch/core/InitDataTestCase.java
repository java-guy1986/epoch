package com.desksoft.epoch.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import com.desksoft.epoch.core.base.model.Module;
import com.desksoft.epoch.core.base.model.Role;
import com.desksoft.epoch.core.base.model.User;
import com.desksoft.epoch.core.base.model.PermissionBit.Permission;
import com.desksoft.epoch.core.base.service.AclService;
import com.desksoft.epoch.core.base.service.ModuleService;
import com.desksoft.epoch.core.base.service.RoleService;
import com.desksoft.epoch.core.base.service.UserService;


/**
 * Init system data
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 10, 2014 9:44:42 AM
 */
public class InitDataTestCase extends BaseTestCase{

	
	private static UserService userService = null;
	
	private static RoleService roleService = null;
	
	private static ModuleService moduleService = null;
	
	private static AclService aclService = null;
	
	private static  String FILE_PATH = null;
	
	static {
		FILE_PATH = System.getProperty("user.dir") + java.io.File.separator + "init-data.xml";
		userService = (UserService)context.getBean("userService");
		roleService = (RoleService)context.getBean("roleService");
		moduleService = (ModuleService)context.getBean("moduleService");
		aclService = (AclService)context.getBean("aclService");
	}
	
	
	private static List<User> readUsers(Element rootElement){
		List<Element> elements = rootElement.getChild("users").getChildren();
		List<User> userList = new ArrayList<User>();
		for (Element element : elements){
			User user = new User();
			user.setUsername(element.getAttributeValue("name"));
			user.setPassword(element.getAttributeValue("password"));
			user.setHoneyName(element.getAttributeValue("honeyName"));
			userList.add(user);
		}
		
		return userList;
	}
	
	private static List<Role> readRoles(Element rootElement){
		List<Element> elements = rootElement.getChild("roles").getChildren();
		List<Role> roleList = new ArrayList<Role>();
		for (Element element:elements){
			Role role = new Role();
			role.setName(element.getAttributeValue("name"));
			role.setDescription(element.getAttributeValue("description"));
			roleList.add(role);
		}
		
		return roleList;
	}
	
	private static List<WrapperModule> readModules(Element rootElement){
		List<Element> elements = rootElement.getChild("modules").getChildren();
		List<WrapperModule> configModuleConfig = new ArrayList<WrapperModule>();
		for (Element element:elements){
			WrapperModule wm = new WrapperModule();
			wm.setParentModuleName(element.getAttributeValue("parentModuleName"));
			Module module = new Module();
			module.setName(element.getAttributeValue("name"));
			module.setUrlPath(element.getAttributeValue("urlPath"));
			wm.setModule(module);
			configModuleConfig.add(wm);
		}
		
		return configModuleConfig;
	}
	
	private static void saveAcls(Element rootElement) throws Exception{
		List<Element> elements = rootElement.getChild("acls").getChildren();
		for (Element element : elements){
			Role role = roleService.selectRoleByName(element.getAttributeValue("roleName"));
			Module module = moduleService.selectModuleByName(element.getAttributeValue("moduleName"));
			if ("true".equalsIgnoreCase(element.getAttributeValue("create"))){
				aclService.addPermission(role.getId(), module.getId(), Permission.ADD);
			}
			if ("true".equalsIgnoreCase(element.getAttributeValue("read"))){
				aclService.addPermission(role.getId(), module.getId(), Permission.SELECT);
			}
			if ("true".equalsIgnoreCase(element.getAttributeValue("delete"))){
				aclService.addPermission(role.getId(), module.getId(), Permission.DELETE);
			}
			if ("true".equalsIgnoreCase(element.getAttributeValue("update"))){
				aclService.addPermission(role.getId(), module.getId(), Permission.UPDATE);
			}
		}
	}
	
	
	private static List<WrapperUserRole> readUserRoles(Element rootElement){
		List<Element> elements = rootElement.getChild("user-roles").getChildren();
		List<WrapperUserRole> list = new ArrayList<WrapperUserRole>();
		for (Element element: elements){
			WrapperUserRole userRole = new WrapperUserRole();
			userRole.setUsername(element.getAttributeValue("username"));
			userRole.setRoleName(element.getAttributeValue("roleName"));
			list.add(userRole);
		}
		
		return list;
	}
	
	
	public void testInitData()throws Exception{
		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = saxBuilder.build(new File(FILE_PATH));
		Element rootElement = document.getRootElement();
		
		//获取用户列表，保存用户
		List<User> userList = readUsers(rootElement);
		for (User user:userList){
			userService.addUser(user);
		}
		
		//获取角色列表，保存角色
		List<Role> roleList = readRoles(rootElement);
		for (Role role:roleList){
			roleService.addRole(role);
		}
		//读取模块，保存模块
		List<WrapperModule> wmList = readModules(rootElement);
		for (WrapperModule wm : wmList){
			Module module = new Module();
			if (StringUtils.isEmpty(wm.getParentModuleName())){
				module.setPid(-1);
			} else {
				Module parentModule = moduleService.selectModuleByName(wm.getParentModuleName());
				module.setPid(parentModule.getId());
			}
			module.setName(wm.getModule().getName());
			module.setUrlPath(wm.getModule().getUrlPath());
			moduleService.addModule(module.getPid(), module);
		}
		
		//读取用户角色关联，保存关联
		List<WrapperUserRole> wuRoleList = readUserRoles(rootElement);
		for (WrapperUserRole wur : wuRoleList){
			User user = userService.selectUserByName(wur.getUsername());
			Role role = roleService.selectRoleByName(wur.getRoleName());
			userService.addRole(user.getId(), role.getId());
		}
		
		//读取ACL配置，保存信息
		saveAcls(rootElement);
	}
}

class WrapperModule{
	private Module module;
	
	private String parentModuleName;

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public String getParentModuleName() {
		return parentModuleName;
	}

	public void setParentModuleName(String parentModuleName) {
		this.parentModuleName = parentModuleName;
	}
}

class WrapperUserRole{
	
	private String username;
	
	private String roleName;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
