package com.desksoft.epoch.core.base.service;

import java.util.List;

import com.desksoft.epoch.core.BaseTestCase;
import com.desksoft.epoch.core.base.model.Module;


public class ModuleServiceTest extends BaseTestCase {

	private static ModuleService moduleService = null;
	
	static {
		if (moduleService == null){
			moduleService = (ModuleService)context.getBean("moduleService");
		}
	}
	
	public void testAddModule() throws Exception {
		//添加顶级模块
		final Integer pid = -1;
		for (int i = 0; i < 5; i++){
			Module module = new Module();
			module.setName("模块_" + i);
			module.setDescription("模块描述_" + i);
			module.setUrlPath("url_" +i+ ".do?");
			Integer moduleId = moduleService.addModule(pid, module);
			for (int j = 0; j < 5; j++){
				//添加子模块
				Module childModule = new Module();
				childModule.setName(module.getName() + "_" + "子模块_" + j);
				childModule.setDescription(module.getDescription() + "_" + "子模块_" + j);
				childModule.setUrlPath(module.getUrlPath() + "childurl");
				moduleService.addModule(moduleId, childModule);
			}
		}
	}

	public void testDeleteModule() throws Exception {
		moduleService.deleteModule(23);
	}

	public void testUpdateModule() throws Exception {
		Module module = new Module();
		module.setId(19);
		module.setName("测试模块DEOM");
		moduleService.updateModule(module);
	}

	public void testSelectModule() throws Exception {
		Module module = moduleService.selectModule(30);
		System.out.println(module);
	}

	public void testSelectModules() throws Exception{
		List<Module> children = moduleService.selectModules(30);
		for (Module child: children){
			System.out.println(child);
		}
	}

}
