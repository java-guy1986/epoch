package com.desksoft.epoch.core.base.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.desksoft.epoch.common.plugins.Pager;
import com.desksoft.epoch.common.util.CollectionUtil;
import com.desksoft.epoch.core.base.dao.AclDao;
import com.desksoft.epoch.core.base.dao.ModuleDao;
import com.desksoft.epoch.core.base.dao.UserDao;
import com.desksoft.epoch.core.base.model.Module;
import com.desksoft.epoch.core.base.model.PermissionBit.Permission;
import com.desksoft.epoch.core.base.model.Role;
import com.desksoft.epoch.core.base.model.User;
/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 2, 2014 12:43:30 PM
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	@Resource
	private AclDao aclDao;
	
	@Resource
	private ModuleDao moduleDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setAclDao(AclDao aclDao) {
		this.aclDao = aclDao;
	}
	
	public void setModuleDao(ModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}

	private boolean checkStatus(int status){
		if (status == User.STATUS_NORMAL || status == User.STATUS_LOCK 
				|| status == User.STATUS_PAUSE || status == User.STATUS_PERIOD){
			return true;
		}
		
		return false;
	}
	
	@Override
	public ServiceResult addUser(User user) throws Exception {
		if (user == null){
			throw new NullPointerException("User should not be null!");
		}
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("username", user.getUsername());
		if (userDao.selectUser(params) != null){
			return new ServiceResult(ServiceResult.CODE_USER_EXIST, ServiceResult.MESSAGE_USER_EXIST);
		}
		userDao.addUser(user);
		
		return new ServiceResult(ServiceResult.CODE_SUCCESS, ServiceResult.MESSAGE_SUCCESS);
	}

	@Override
	public void updateUser(User user) throws Exception {
		if (user == null){
			throw new NullPointerException("User should not be null");
		}
		if (user.getId() == null){
			throw new IllegalArgumentException("User id should not be null");
		}
		userDao.updateUser(user);
	}

	@Override
	public void updateUserStatus(String id, int status) throws Exception {
		if (StringUtils.isEmpty(id)){
			throw new NullPointerException("Id should not be null!");
		}
		if (!checkStatus(status)){
			throw new IllegalArgumentException("User status[" + status +" ] is not correct!");
		}
		User user = new User();
		user.setId(Integer.parseInt(id));
		user.setStatus(status);
		userDao.updateUser(user);
	}

	@Override
	public void deleteUser(String id) throws Exception {
		if (StringUtils.isEmpty(id)){
			throw new  NullPointerException("Id should not be null");
		}
		userDao.deleteUserById(id);
	}

	@Override
	public void deleteUsers(List<String> idList) throws Exception {
		if (CollectionUtil.isEmpty(idList)){
			log.warn("Delete users id list is empty!");
			return;
		}
		userDao.deleteUsersByIdList(idList);
	}

	@Override
	public List<User> selectAllUsers() throws Exception {
		return userDao.selectAllUsers();
	}

	@Override
	public Pager<User> selectUsersPager(Map<String, Object> params) throws Exception {
		return userDao.selectPagerUsers(params);
	}

	@Override
	public Pager<User> selectUsersByStatus(int status) throws Exception {
		if (!checkStatus(status)){
			throw new IllegalArgumentException("status [" + status +"] is not allowed!");
		}
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("status", status);
		
		return userDao.selectPagerUsers(params);
	}

	@Override
	public User selectUserById(String id) throws Exception {
		if (StringUtils.isEmpty(id)){
			throw new NullPointerException("Id should not be null!");
		}
		return userDao.selectUserById(id);
	}

	@Override
	public User selectUserByName(String username) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("username", username);
		return userDao.selectUser(params);
	}

	@Override
	public User selectUserByNamePassword(String username, String password) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("username", username);
		params.put("password", password);
		return userDao.selectUser(params);
	}

	@Override
	public void addRole(Integer userId, Integer roleId) throws Exception {
		List<Role> roleList = userDao.selectRoleList(userId);
		if (CollectionUtil.isEmpty(roleList)){
			userDao.addRole(userId, roleId);
			return;
		}
		boolean roleExists = false;
		for (Role role:roleList){
			if (role.getId() == roleId){
				roleExists = true;
				break;
			}
		}
		if (!roleExists){
			userDao.addRole(userId, roleId);
		}
	}

	@Override
	public List<Role> selectRoleList(Integer userId) throws Exception {
		return userDao.selectRoleList(userId);
	}

	@Override
	public void deleteRole(Integer userId, Integer roleId) throws Exception {
		userDao.deleteRole(userId, roleId);
	}

	@Override
	public Set<Module> selectEnableReadModuleList(Integer userId) throws Exception {
		//查询到按优先级排序的用户角色列表
		List<Role> roleList = userDao.selectRoleList(userId);
		//用户没有关联角色，返回空
		if (CollectionUtil.isEmpty(roleList)){
			return null;
		}
		//查询顶级模块
		List<Module> topModuleList = moduleDao.selectModules(-1);
		List<Module> moduleList = new ArrayList<Module>();
		if (CollectionUtil.isNotEmpty(topModuleList)){
			for (Module parent : topModuleList){
				moduleList.add(parent);
				//查询顶级模块下的二级模块，目前不支持 三级模块
				List<Module> childrenModules = moduleDao.selectModules(parent.getId());
				for (Module childModule : childrenModules){
					moduleList.add(childModule);
				}
			}
		}
		
		Set<Module> resultSet = new HashSet<Module>();
		//判断权限
		for (Role role : roleList){
			for (Module module : moduleList){
				boolean hasSelectPermission = aclDao.hasPermission(role.getId(), module.getId(), Permission.SELECT);
				if (hasSelectPermission){
					resultSet.add(module);
				}
			}
		}
		
		return resultSet;
	}
}
