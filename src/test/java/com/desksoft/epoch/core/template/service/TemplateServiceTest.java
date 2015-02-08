package com.desksoft.epoch.core.template.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 11, 2014 4:20:38 PM
 */
public class TemplateServiceTest extends TestCase {

	static Configuration cfg = null;
	
	static File templdate_dir = new File("D:\\workspace\\desk-soft\\epoch\\src\\main\\webapp\\template");
	
	static String output_dir = "D:\\workspace\\desk-soft\\epoch\\src\\main\\webapp\\output";
	
	static {
		cfg = new Configuration();
		try {
			cfg.setDirectoryForTemplateLoading(templdate_dir);
			cfg.setTemplateExceptionHandler(new EpochTemplateExceptionHandler());
			cfg.setDefaultEncoding("UTF-8");
			cfg.setObjectWrapper(new DefaultObjectWrapper());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void testOutput() throws Exception{
		Map<String, Object> root = new HashMap<String, Object>();
		List<Animal> animalList = new ArrayList<Animal>();
		for (int i = 0; i < 3; i++){
			Animal animal = new Animal("animal_" + i, 1.28);
			animalList.add(animal);
		}
		root.put("animalList", animalList);
		Template temp = cfg.getTemplate("test.ftl");
		FileOutputStream fos = new FileOutputStream(new File(output_dir + File.separator + "test.html"));
		//输出
		Writer writer = new OutputStreamWriter(fos);
		temp.process(root, writer);
		writer.close();
		fos.close();
	}
}


class EpochTemplateExceptionHandler implements TemplateExceptionHandler{

	@Override
	public void handleTemplateException(TemplateException te, 
			Environment env, Writer out) throws TemplateException {
		try {
			out.write("[ERROR: " + te.getMessage() + "]");
		} catch (IOException e) {
			throw new TemplateException("Failed to print errormessage. Cause: " + e, env);
		}
	}
	
}
