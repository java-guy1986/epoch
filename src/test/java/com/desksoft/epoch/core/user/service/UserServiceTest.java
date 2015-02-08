package com.desksoft.epoch.core.user.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.desksoft.epoch.common.plugins.Pager;
import com.desksoft.epoch.core.BaseTestCase;
import com.desksoft.epoch.core.base.model.Module;
import com.desksoft.epoch.core.base.model.Role;
import com.desksoft.epoch.core.base.model.User;
import com.desksoft.epoch.core.base.service.ServiceException;
import com.desksoft.epoch.core.base.service.UserService;

public class UserServiceTest extends BaseTestCase {
	
	private static UserService userService = null;
	
	static {
		if (userService == null){
			userService = (UserService)context.getBean("userService");
		}
	}
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	
	public void testAddRole() throws Exception{
		final Integer userId = 99;
		
		userService.addRole(userId, 7);
		userService.addRole(userId, 8);
		userService.addRole(userId, 9);
	}
	
	public void testSelectRoleList()throws Exception{
		final Integer userId = 98;
		List<Role> roleList = userService.selectRoleList(userId);
		for (Role role : roleList){
			System.out.println(role);
		}
	}
	
	public void testDeleteRole()throws Exception{
		final Integer userId = 98;
		final Integer roleId = 7;
		userService.deleteRole(userId, roleId);
	}
	
	public void testAddUser() throws Exception {
		for (int i = 0; i < 10; i++){
			User user = new User();
			user.setUsername("zhangsan_" + i);
			user.setPassword("password_" + i);
			user.setHoneyName("honeyName_" + i);
			userService.addUser(user);
		}
		try {
			User user = userService.selectUserByName("zhangsan_0");
			assertNotNull("user should not be null", user);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void testUpdateUser() throws Exception {
		
		User user = new User();
		user.setId(21);
		user.setHoneyName("更改的honeyName");
		user.setIsDelete(false);
		userService.updateUser(user);
	}

	public void testUpdateUserStatus() {
		
		try {
			userService.updateUserStatus("14", User.STATUS_LOCK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void testDeleteUser() {
		
		try {
			userService.deleteUser("14");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testDeleteUsers() {
		String[] idArray = new String[]{"87", "15", "16"};
		List<String> idList = Arrays.asList(idArray);
		try {
			userService.deleteUsers(idList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testSelectAllUsers() {
		
		List<User> userList = null;
		try {
			userList = userService.selectAllUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (User user: userList){
			System.out.println(user);
		}
		
	}

	public void testSelectUsersPager() throws Exception {
		
		try {
			Pager<User> userPager = userService.selectUsersPager(null);
			System.out.println(userPager);
			if (userPager != null){
				List<User> userList = userPager.getResult();
				for (User user: userList){
					System.out.println(user);
				}
			}
			
			System.out.println("--------------------------------------------");
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Pager.PAGE_NUM_KEY, 1);
			params.put(Pager.PAGE_SIZE_KEY, 2);
			params.put("username", "zhangsan");
			Pager<User> userPager2 = userService.selectUsersPager(params);
			System.out.println(userPager2);
			
			if (userPager2 != null){
				List<User> userList = userPager2.getResult();
				for (User user: userList){
					System.out.println(user);
				}
			}
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void testSelectUsersByStatus() throws Exception {
		try {
			Pager<User> userPager = userService.selectUsersByStatus(User.STATUS_LOCK);
			System.out.println(userPager);
			if (userPager != null){
				List<User> userList = userPager.getResult();
				for (User user: userList){
					System.out.println(user);
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}

	public void testSelectUserById() throws Exception {
		User user = null;
		try {
			user = userService.selectUserById("14");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		System.out.println(user);
		
	}

	public void testSelectUserByName() throws Exception {
		User user = null;
		try {
			user = userService.selectUserByName("zhangsan_2");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		System.out.println(user);
	}

	public void testSelectUserByNamePassword() throws Exception {
		User user = null;
		try {
			user = userService.selectUserByNamePassword("zhangsan_2", "password_2");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		System.out.println(user);
	}
	
	public void testSelectEnableReadModuleList() throws Exception{
		final Integer userId = 98;
		Set<Module> moduleSet = userService.selectEnableReadModuleList(userId);
		for (Module module:moduleSet){
			System.out.println(module);
		}
		
	}
}
