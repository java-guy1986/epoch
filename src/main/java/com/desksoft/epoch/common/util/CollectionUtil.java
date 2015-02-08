package com.desksoft.epoch.common.util;

import java.util.Collection;

/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 2, 2014 9:54:22 AM
 */
public class CollectionUtil {

	public static boolean isEmpty(Collection<?> c){
		if (c == null || c.isEmpty()){
			return true;
		}
		
		return false;
	}
	
	public static boolean isNotEmpty(Collection<?> c){
		return !isEmpty(c);
	}
}
