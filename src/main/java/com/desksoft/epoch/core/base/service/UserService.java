package com.desksoft.epoch.core.base.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.desksoft.epoch.common.plugins.Pager;
import com.desksoft.epoch.core.base.model.Module;
import com.desksoft.epoch.core.base.model.Role;
import com.desksoft.epoch.core.base.model.User;

/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 2, 2014 11:13:16 AM
 */
public interface UserService {
	
	public ServiceResult addUser(User user) throws Exception;
	
	public void updateUser(User user) throws Exception;
	
	public void updateUserStatus(String id, int status) throws Exception;
	
	public void deleteUser(String id) throws Exception;
	
	public void deleteUsers(List<String> idList) throws Exception;
	
	public List<User> selectAllUsers() throws Exception;
	
	public Pager<User> selectUsersPager(Map<String, Object> params) throws Exception;
	
	public Pager<User> selectUsersByStatus(int status) throws Exception;
	
	public User selectUserById(String id) throws Exception;
	
	public User selectUserByName(String name) throws Exception;
	
	public User selectUserByNamePassword(String username, String password) throws Exception;
	
	public void addRole(Integer userId, Integer roleId) throws Exception;
	
	public void deleteRole(Integer userId, Integer roleId) throws Exception;
	
	public List<Role> selectRoleList(Integer userId) throws Exception;
	
	/**
	 * 查询拥有读取权限的模块列表
	 * 角色冲突的解决方案：合并多角色的权限。 假设给A用户授予角色A和角色B，
	 * 角色A和角色B对模块A的读取权限是有冲突的，例如，角色A有模块A的读取权限，
	 * 角色B没有模块A的读取权限，那么，最终的结果是角色A拥有模块A的读取权限，反之亦然。
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Set<Module> selectEnableReadModuleList(Integer userId) throws Exception;
}
