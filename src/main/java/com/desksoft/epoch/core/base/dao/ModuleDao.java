package com.desksoft.epoch.core.base.dao;

import java.util.List;

import com.desksoft.epoch.core.base.model.Module;

/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 8, 2014 5:34:07 PM
 */
public interface ModuleDao {
	
	/**
	 * 添加模块
	 * @param pid
	 * @param module
	 * @throws Exception
	 */
	public Integer addModule(Integer pid, Module module) throws Exception;
	
	/**
	 * 删除模块
	 * @param id
	 * @throws Exception
	 */
	public void deleteModule(Integer id) throws Exception;
	
	/**
	 * 更新模块
	 * @param module
	 * @throws Exception
	 */
	public void updateModule(Module module) throws Exception;
	
	/**
	 * 查询父模块下的所有子模块
	 * @param pid 
	 *   -1 表示查询顶级模块
	 * @return
	 * @throws Exception
	 */
	public List<Module> selectModules(Integer pid) throws Exception;
	
	/**
	 * 根据id查询模块
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Module selectModuleById(Integer id) throws Exception;
	
	/**
	 * 根据名称查询模块
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Module selectModuleByName(String name) throws Exception;
	
}
