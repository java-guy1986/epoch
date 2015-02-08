package com.desksoft.epoch.core.base.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.desksoft.epoch.core.base.model.Role;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl implements RoleDao {

	private static final String NAME_SPACE = "com.desksoft.epoch.core.base.dao";
	
	private static final String ADD_ROLE = NAME_SPACE + ".addRole";
	
	private static final String DELETE_ROLE =  NAME_SPACE + ".deleteRole";
	
	private static final String SELECT_ROLES = NAME_SPACE + ".selectRoles";
	
	private static final String UPDATE_ROLE = NAME_SPACE + ".updateRole";
	
	private static final String SELECT_ROLE_BY_ID = NAME_SPACE + ".selectRoleById";

	private static final String SELECT_ROLE_BY_NAME = NAME_SPACE + ".selectRoleByName";
	
	@Override
	public void addRole(Role role) throws Exception {
		if (role == null){
			throw new NullPointerException("Add role: role object should not be null!");
		}
		
		insert(ADD_ROLE, role);
	}

	@Override
	public void deleteRole(Integer id) throws Exception {
		if (id == null){
			throw new NullPointerException("Delete role: id should not be null!");
		}
		
		delete(DELETE_ROLE, id);
	}

	@Override
	public void updateRole(Role role) throws Exception {
		if (role == null){
			throw new NullPointerException("Update role: role should not be null!");
		}
		if (role.getId() == null){
			throw new NullPointerException("Update role: role id should not be null!");
		}
		
		update(UPDATE_ROLE, role);
	}

	@Override
	public List<Role> selectAllRoles() throws Exception {
		return selectList(SELECT_ROLES, null);
	}

	@Override
	public Role selectRoleById(Integer id) throws Exception {
		if (id == null){
			throw new NullPointerException("Id should not be null!");
		}
		
		return (Role)this.selectOne(SELECT_ROLE_BY_ID, id);
	}

	@Override
	public Role selectRoleByName(String name) throws Exception {
		if (StringUtils.isEmpty(name)){
			return null;
		}
		
		return (Role)this.selectOne(SELECT_ROLE_BY_NAME, name);
	}

}
