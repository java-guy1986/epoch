package com.desksoft.epoch.core.json;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ObjectToJsonMain {

	public static void main(String[] args) throws JsonProcessingException {
		User user = new User();
		/*user.setId(1);
		user.setName("zhangsan");*/
		ObjectMapper oMapper = new ObjectMapper();
		//oMapper.setSerializationInclusion(Include.NON_EMPTY);
		oMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		ObjectWriter oWriter = oMapper.writer();
		String json = oWriter.writeValueAsString(user);
		System.out.println(json);
	}

}

class User{
	private Integer id;
	
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
