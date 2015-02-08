package com.desksoft.epoch.core.base.controller;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.desksoft.epoch.core.base.anotation.JsonArg;
import com.desksoft.epoch.core.base.dto.UserForm;
import com.desksoft.epoch.core.base.model.User;

@Controller
@Scope("prototype")
public class ArrayTestController extends BaseController{
	
	@RequestMapping(value="test/jsonMutiArguments", method = RequestMethod.POST)
	public String passJsonMutiArguments(@RequestBody List<User> userList){
		for (User user : userList) {
			System.out.println(user);
		}
		return "test/index";
	}
	

	@RequestMapping(value="test/getArrayData", method = RequestMethod.POST)
	public String getArrayData(@RequestParam(value="ids[]") String[] ids){
		System.out.println(ids);
		return "test/index";
	}
	
	@RequestMapping(value="test/indexArray")
	public String index(){
		System.out.println("controller:" + this);
		return "test/index";
	}
	
	@RequestMapping(value="test/getObjectArrayData", method = RequestMethod.POST)
	public String getObjectArrayData(@RequestBody List<User> userList){
		for (User user:userList){
			System.out.println(user.getUsername());
		}
		return "test/index";
	}
	
	@RequestMapping(value="test/getMutiObjectArrayData", method = RequestMethod.POST)
	public String getMutiObjectArrayData(@RequestBody UserForm userForm){
		System.out.println(userForm);
		
		return "test/index";
	}
	
	@RequestMapping(value="test/getMutiTypeMapData", method = RequestMethod.POST)
	public String getMutiTypeMapData(@RequestBody Map<String, Object> hashmap){
		System.out.println(hashmap);
		return "test/index";
	}
	
	@RequestMapping(value="test/getSimpleTypeMapData", method = RequestMethod.POST)
	public String getSimpleTypeMapData(@RequestBody Map<String, Object> hashmap,@RequestBody String username){
		System.out.println(hashmap);
		return "test/index";
	}
	
	@RequestMapping(value="test/passSimpleDateParameters", method = RequestMethod.POST)
	public String passSimpleDateParameters(@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")Date beginTime){
		System.out.println(beginTime);
		return null;
	}
	
	@RequestMapping(value="test/passJsonString", method = RequestMethod.POST)
	public String passJsonString(@JsonArg String jsonString){
		System.out.println(jsonString);
		return "test/index";
	}
	
	
	@RequestMapping(value="test/getApplicationContext", method = RequestMethod.POST)
	public String getApplicationContext(){
		
		/*ApplicationContext applicationContext = SpringContextHolder.getApplicationContext();
		
		System.out.println(applicationContext);
		
		SqlSessionTemplate sqlSessionTemplate = SpringContextHolder.getBean("sqlSessionTemplate", SqlSessionTemplate.class);
		
		System.out.println(sqlSessionTemplate);*/
		
		return "test/index";
	}
	
	
}
