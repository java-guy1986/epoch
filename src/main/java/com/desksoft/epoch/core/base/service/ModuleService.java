package com.desksoft.epoch.core.base.service;

import java.util.List;

import com.desksoft.epoch.core.base.model.Module;

/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 9, 2014 12:47:13 PM
 */
public interface ModuleService {
	
	/**
	 * 添加模块
	 * @param pid
	 * @param module
	 * @return 模块id
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
	 * 查询模块
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Module selectModule(Integer id) throws Exception;
	
	/**
	 * 查询子模块列表
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public List<Module> selectModules(Integer pid) throws Exception;
	
	/**
	 * 根据名称查询模块
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Module selectModuleByName(String name) throws Exception;
}
