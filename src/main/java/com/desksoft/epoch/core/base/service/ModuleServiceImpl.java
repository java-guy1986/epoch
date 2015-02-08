package com.desksoft.epoch.core.base.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.desksoft.epoch.common.util.CollectionUtil;
import com.desksoft.epoch.core.base.dao.ModuleDao;
import com.desksoft.epoch.core.base.model.Module;

/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 9, 2014 12:52:21 PM
 */
@Service("moduleService")
public class ModuleServiceImpl extends BaseServiceImpl implements ModuleService {

	@Resource
	private ModuleDao moduleDao;
	
	public void setModuleDao(ModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}

	@Override
	public Integer addModule(Integer pid, Module module) throws Exception {
		if (module == null){
			throw new NullPointerException("Module should not be null!");
		}
		
		Module dbModule = moduleDao.selectModuleByName(module.getName());
		if (dbModule != null){
			return dbModule.getId();
			//throw new ServiceException("Module[" + module.getName() + "] already exists!");
		}
		
		//添加模块
		Integer moduleId = this.moduleDao.addModule(pid, module);
		
		//修改父模块
		Module parentModule = selectModule(pid);
		if (parentModule != null 
				&& parentModule.getIsLeaf() == true){
			parentModule.setIsLeaf(false);
			this.updateModule(parentModule);
		}
		
		return moduleId;
	}

	@Override
	public void deleteModule(Integer id) throws Exception {
		//查询模块
		Module module = this.moduleDao.selectModuleById(id);
		//删除模块
		this.moduleDao.deleteModule(id);
		//父模块下没有子模块，修改父模块的isLeaf字段为true
		List<Module> children = this.moduleDao.selectModules(module.getPid());
		if (CollectionUtil.isEmpty(children)){
			Module parentModule = new Module();
			parentModule.setId(module.getPid());
			parentModule.setIsLeaf(true);
			this.moduleDao.updateModule(parentModule);
		}
	}

	@Override
	public void updateModule(Module module) throws Exception {
		this.moduleDao.updateModule(module);
	}

	@Override
	public Module selectModule(Integer id) throws Exception {
		Module module = this.moduleDao.selectModuleById(id);
		if (module != null){
			List<Module> children = this.moduleDao.selectModules(id);
			module.setChildren(children);
		}
		return module;
	}

	@Override
	public List<Module> selectModules(Integer pid) throws Exception {
		return this.moduleDao.selectModules(pid);
	}

	@Override
	public Module selectModuleByName(String name) throws Exception {
		return this.moduleDao.selectModuleByName(name);
	}
}
