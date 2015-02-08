package com.desksoft.epoch.core.base.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.desksoft.epoch.core.base.model.ACL;
import com.desksoft.epoch.core.base.model.PermissionBit;
import com.desksoft.epoch.core.base.model.PermissionBit.Permission;

@Repository("aclDao")
public class AclDaoImpl extends BaseDaoImpl implements AclDao {

	private static final String NAME_SPACE = "com.desksoft.epoch.core.base.dao";
	
	private static final String ADD_PERMISSION = NAME_SPACE + ".addPermission";
	
	private static final String UPDATE_PERMISSION = NAME_SPACE + ".updatePermission";
	
	private static final String SELECT_PERMISSION = NAME_SPACE + ".selectPermission";
	
	/**
	 * 获取授予权限位的值
	 * @param permission
	 * @return
	 */
	private int getPermissionBitValue(Permission permission){
		if (permission == Permission.ADD){
			return PermissionBit.C;
		} 
		if (permission == Permission.DELETE){
			return  PermissionBit.D;
		} 
		if (permission == Permission.UPDATE){
			return PermissionBit.U;
		}
		
		return PermissionBit.R;
	}
	
	@Override
	public void addPermission(Integer roleId, Integer moduleId, Permission permission) throws Exception {
		if (roleId == null){
			throw new NullPointerException("RoleId should not be null!");
		}
		if (moduleId == null){
			throw new NullPointerException("ModuleId should not be null!");
		}
		Map<String, Object> params = new HashMap<String, Object>(10);
		params.put("roleId", roleId);
		params.put("moduleId", moduleId);
		//查询已有权限
		ACL acl = selectPermission(roleId, moduleId);
		//记录不存在，插入一条新纪录
		if (acl == null){
			params.put("permission", getPermissionBitValue(permission));
			this.insert(ADD_PERMISSION, params);
		} else {
			//记录已存在，修改权限字段值(位或操作：其它位保持不变，授权位置1)
			Integer destPermission = acl.getPermission() | getPermissionBitValue(permission);
			params.put("permission", destPermission);
			this.update(UPDATE_PERMISSION, params);
		}
	}

	@Override
	public void deletePermission(Integer roleId, Integer moduleId, Permission permission) throws Exception {
		if (roleId == null){
			throw new NullPointerException("RoleId should not be null!");
		}
		if (moduleId == null){
			throw new NullPointerException("moduleId should not be null!");
		}

		Map<String, Object> params = new HashMap<String, Object>(10);
		params.put("roleId", roleId);
		params.put("moduleId", moduleId);
		//查询已有权限
		ACL acl = selectPermission(roleId, moduleId);
		//记录不存在，不做任何处理
		if (acl == null){
			return;
		}
		//删除权限: 其它位不变，欲删除位置0(按位取反，再与数据库字段"与"操作)
		Integer destPermission = acl.getPermission() & (~getPermissionBitValue(permission));
		params.put("permission", destPermission);
		this.update(UPDATE_PERMISSION, params);
	}

	@Override
	public boolean hasPermission(Integer roleId, Integer moduleId, Permission permission) throws Exception {
		ACL acl = selectPermission(roleId, moduleId);
		//记录不存在，无权限
		if (acl == null){
			return false;
		}
		Integer permissionBitValue = getPermissionBitValue(permission);
		//"与"操作，permissionBitValue会将其它位清零，权限位结果大于零表示有权限，否则表示没有
		return (permissionBitValue & acl.getPermission()) > 0 ? true : false;
	}

	@Override
	public ACL selectPermission(Integer roleId, Integer moduleId) throws Exception {
		if (roleId == null){
			throw new NullPointerException("RoleId should not be null!");
		}
		if (moduleId == null){
			throw new NullPointerException("moduleId should not be null!");
		}
		
		Map<String, Object> params = new HashMap<String, Object>(10);
		params.put("roleId", roleId);
		params.put("moduleId", moduleId);
		
		return (ACL)this.selectOne(SELECT_PERMISSION, params);
	}

}
