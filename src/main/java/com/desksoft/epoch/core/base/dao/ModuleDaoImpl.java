package com.desksoft.epoch.core.base.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.desksoft.epoch.common.util.CollectionUtil;
import com.desksoft.epoch.core.base.model.Module;

/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 9, 2014 12:14:36 PM
 */
@Repository("moduleDao")
public class ModuleDaoImpl extends BaseDaoImpl implements ModuleDao {
	
	private static final String NAME_SPACE = "com.desksoft.epoch.core.base.dao";
	
	private static final String ADD_MODULE = NAME_SPACE + ".addModule";
	
	private static final String DELETE_MODULE = NAME_SPACE + ".deleteModule";
	
	private static final String SELECT_MODULES = NAME_SPACE + ".selectModules";
	
	private static final String SELECT_MODULE_BY_ID = NAME_SPACE + ".selectModuleById";
	
	private static final String SELECT_MODULE_BY_NAME = NAME_SPACE + ".selectModuleByName";
	
	private static final String UPDATE_MODULE = NAME_SPACE + ".updateModule";
	
	@Override
	public Integer addModule(Integer pid, Module module) throws Exception {
		if (module == null){
			throw new NullPointerException("module should not be null!");
		}
		if (pid == null){
			pid = -1;
		}
		module.setPid(pid);
		//添加模块
		this.insert(ADD_MODULE, module);
		
		return module.getId();
	}

	@Override
	public void deleteModule(Integer id) throws Exception {
		if (id == null){
			throw new NullPointerException("Module id should not be null!");
		}
		List<Module> children = selectModules(id);
		if (CollectionUtil.isNotEmpty(children)){
			throw new DaoException("非叶子节点模块不允许删除");
		}
		this.update(DELETE_MODULE, id);
	}

	@Override
	public void updateModule(Module module) throws Exception {
		if (module == null){
			throw new NullPointerException("Module should not be null!");
		}
		if (module.getId() == null){
			throw new IllegalArgumentException("Module id should not be null!");
		}
		
		this.update(UPDATE_MODULE, module);
	}

	@Override
	public List<Module> selectModules(Integer pid) throws Exception {
		if(pid == null){
			throw new NullPointerException("Pid should not be null!");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", pid);
		
		return this.selectList(SELECT_MODULES, params);
	}

	@Override
	public Module selectModuleById(Integer id) throws Exception {
		if (id == null){
			throw new NullPointerException("Id should not be null!");
		}
		
		return (Module)this.selectOne(SELECT_MODULE_BY_ID, id);
	}

	@Override
	public Module selectModuleByName(String name) throws Exception {
		return (Module)this.selectOne(SELECT_MODULE_BY_NAME, name);
	}

}
