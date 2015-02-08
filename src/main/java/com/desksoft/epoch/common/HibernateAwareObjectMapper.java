package com.desksoft.epoch.common;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate3.Hibernate3Module;

public class HibernateAwareObjectMapper extends ObjectMapper {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1900403184682024308L;

	public HibernateAwareObjectMapper() {
        registerModule(new Hibernate3Module());
    }
}