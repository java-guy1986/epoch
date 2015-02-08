package com.desksoft.epoch.core.base.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.desksoft.epoch.common.plugins.Pager;
import com.desksoft.epoch.common.util.CollectionUtil;
import com.desksoft.epoch.core.base.model.Role;
import com.desksoft.epoch.core.base.model.User;
/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 2, 2014 9:15:41 AM
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	public static final String NAME_SPACE = "com.desksoft.epoch.core.base.dao";
	
	public static final String INSERT_USER = NAME_SPACE + ".insertUser";
	
	public static final String UPDATE_USER = NAME_SPACE + ".updateUser";
	
	public static final String DELETE_USER_BY_ID = NAME_SPACE + ".deleteUserById";
	
	public static final String DELETE_USER_BY_ID_LIST = NAME_SPACE + ".deleteUserByIdList";
	
	public static final String SELECT_USER_BY_ID = NAME_SPACE + ".selectUserById";
	
	public static final String SELECT_USER = NAME_SPACE + ".selectUser";
	
	public static final String SELECT_USERS = NAME_SPACE + ".selectUsers";
	
	public static final String ADD_ROLE = NAME_SPACE + ".addUserRole";
	
	public static final String SELECT_ROLES = NAME_SPACE + ".selectUserRoles";
	
	public static final String DELETE_ROLE = NAME_SPACE + ".deleteUserRole";
	
	@Override
	public void addUser(User user) throws Exception {
		if (user == null){
			throw new NullPointerException("Add user: user object should not be null!");
		}
		
		this.insert(INSERT_USER, user);
	}

	@Override
	public void updateUser(User user) throws Exception {
		if (user == null){
			throw new NullPointerException("Update user: user object should not be null!");
		}
		if (user.getId() == null){
			throw new IllegalArgumentException("Update user: user id should not be empty!");
		}
		
		this.update(UPDATE_USER, user);
	}

	@Override
	public void deleteUserById(String id) throws Exception {
		if (StringUtils.isEmpty(id)){
			throw new NullPointerException("Delete user: id should not be null!");
		}
		
		this.update(DELETE_USER_BY_ID, id);
	}

	@Override
	public void deleteUsersByIdList(List<String> idList) throws Exception {
		if (CollectionUtil.isEmpty(idList)){
			throw new NullPointerException("Delete users: id list should not be null!");
		}
		
		this.update(DELETE_USER_BY_ID_LIST, idList);
	}

	@Override
	public User selectUserById(String id) throws Exception {
		if (StringUtils.isEmpty(id)){
			throw new NullPointerException("Select user: id should not be null!");
		}
		
		return (User)selectOne(SELECT_USER_BY_ID, id);
	}

	@Override
	public List<User> selectAllUsers() throws Exception {
		return selectList(SELECT_USERS, null);
	}

	@Override
	public Pager<User> selectPagerUsers(Map<String, Object> params) throws Exception {
		return this.selectPager(SELECT_USERS, params);
	}

	@Override
	public User selectUser(Map<String, Object> params) throws Exception {
		return (User)this.selectOne(SELECT_USER, params);
	}

	@Override
	public void addRole(Integer userId, Integer roleId) throws Exception {
		if (userId == null){
			throw new NullPointerException("UserId should not be null!");
		}
		if (roleId == null){
			throw new NullPointerException("RoleId should not be null!");
		}
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("userId", userId);
		params.put("roleId", roleId);
		this.insert(ADD_ROLE, params);
	}

	@Override
	public List<Role> selectRoleList(Integer userId) throws Exception {
		if (userId == null){
			throw new NullPointerException("UserId should not be null!");
		}
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("userId", userId);
		
		return selectList(SELECT_ROLES, params);
	}

	@Override
	public void deleteRole(Integer userId, Integer roleId) throws Exception {
		if (userId == null){
			throw new NullPointerException("userId should not be null !");
		}
		if (roleId == null){
			throw new NullPointerException("roleId should not be null !");
		}
		
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("userId", userId);
		params.put("roleId", roleId);
		
		this.delete(DELETE_ROLE, params);
	}
}
