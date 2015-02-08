package com.desksoft.epoch.core.base.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.mybatis.spring.SqlSessionTemplate;


/**
 * 
 * 系统日志切面
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 7, 2014 9:26:48 AM
 */
@Aspect
public class SysLogAspect {
	
	private Log log = LogFactory.getLog(this.getClass());	
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	private String getModuleName(String className){
		String moduleName = null;
		if (className.toLowerCase().startsWith("user")){
			//用户模块
			moduleName = "用户管理";
		} else if (className.toLowerCase().startsWith("goods")){
			//商品模块
			moduleName = "商品管理";
		}
		
		return moduleName;
	}
	
	/**
	 * 操作类型
	 * Copyright © 2014 desksoft. All rights reserved
	 * @author fanghc
	 * @date Jul 7, 2014 5:39:07 PM
	 */
	enum OperationType{
		ADD,
		UPDATE,
		DELETE,
		SELECT
	}
	
	/**
	 * 获取操作数据库的类型
	 * @param methodName
	 * @return
	 */
	private OperationType getOperationType(String methodName){
		if (methodName.startsWith("add") 
				|| methodName.startsWith("insert")){
			return OperationType.ADD;
		}
		
		if (methodName.startsWith("update") ||
				methodName.startsWith("modify")){
			return OperationType.UPDATE;
		}
		
		if (methodName.startsWith("del")){
			return OperationType.DELETE;
		}
		
		return OperationType.SELECT;
	}

	@SuppressWarnings("unused")
	@After(value = "execution(public * com.desksoft.epoch.core.*.service..*.*(..))" )
	public void afterOperation(JoinPoint jp){
		/*HttpServletRequest request = ApplicationContext.getRequest();
		String className = jp.getThis().toString();
		String methodName = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		if (StringUtils.isBlank(className)){
			log.warn("JoinPoint getClassName is empty !");
			return;
		}
		if (StringUtils.isBlank(methodName)){
			log.warn("JoinPoint getMethodName is empty!");
			return;
		}
		className = className.substring(className.lastIndexOf(".") + 1);
		String moduleName = getModuleName(className);
		OperationType operationType = getOperationType(methodName);
		if (OperationType.ADD == operationType){
			//记录数据库 添加日志
		} else if (OperationType.UPDATE == operationType){
			//记录更新日志
		} else if (OperationType.DELETE == operationType){
			//记录删除日志
		}*/
	}
}
