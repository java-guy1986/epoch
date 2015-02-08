package com.desksoft.epoch.core;

import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseTestCase extends TestCase{
	
	protected static ApplicationContext context = null;
	
	static {
		context = new ClassPathXmlApplicationContext(new String[]
				{"springbeans-datasource.xml",
				"springmvc-servlet.xml"});
	}
	
	public void testHello(){
		
	}
	
}
