package com.desksoft.epoch.core.base.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class JSONPController {

	@RequestMapping(value="/jsonp/getJsonpData", method=RequestMethod.GET)
	public void getData(String callback, HttpServletResponse response) throws JsonProcessingException{
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("key1", "value1");
		hashMap.put("key2", "value2");
		ActionResult actionResult = new ActionResult();
		actionResult.setSuccess(true);
		actionResult.setData(hashMap);
		
		ObjectMapper oMapper = new ObjectMapper();
		String jsonStr = oMapper.writeValueAsString(actionResult);
		String result = callback + "(" + jsonStr + ")";
		PrintWriter writer = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			writer = response.getWriter();
			writer.write(result);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/jsonp/index", method=RequestMethod.GET)
	public String index(){
		return "/jsonp/index";
	}
}

class ActionResult {
	private boolean success;
	private Object data;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
