package com.desksoft.epoch.core.base.dao;

import java.util.List;
import java.util.Map;

import com.desksoft.epoch.common.plugins.Pager;
import com.desksoft.epoch.core.base.model.Role;
import com.desksoft.epoch.core.base.model.User;

/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 1, 2014 11:11:47 PM
 */
public interface UserDao {

	/**
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void addUser(User user) throws Exception;

	/**
	 * 给用户添加角色
	 * @param userId
	 * @param roleId
	 * @throws Exception
	 */
	public void addRole(Integer userId, Integer roleId) throws Exception;
	
	/**
	 * 给用户删除角色
	 * @param userId
	 * @param roleId
	 * @throws Exception
	 */
	public void deleteRole(Integer userId, Integer roleId) throws Exception;
	
	/**
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void updateUser(User user) throws Exception;
	
	/**
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteUserById(String id) throws Exception;
	
	/**
	 * 
	 * @param idList
	 * @throws Exception
	 */
	public void deleteUsersByIdList(List<String> idList) throws Exception;
	
	/**
	 * 
	 * @param id
	 * @throws Exception
	 */
	public User selectUserById(String id) throws Exception;
	
	
	/**
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public User selectUser(Map<String, Object> params) throws Exception;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<User> selectAllUsers() throws Exception;
	
	/**
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager<User> selectPagerUsers(Map<String, Object> params) throws Exception;
	
	/**
	 * 查询用户的角色列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<Role> selectRoleList(Integer userId) throws Exception;
	
}
