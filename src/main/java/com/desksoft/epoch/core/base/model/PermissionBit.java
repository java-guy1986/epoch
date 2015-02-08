package com.desksoft.epoch.core.base.model;

import java.io.Serializable;

/**
 * 权限位
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 9, 2014 2:32:11 PM
 */
public class PermissionBit implements Serializable{

	public enum Permission{
		ADD,
		DELETE,
		UPDATE,
		SELECT
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//添加位
	public static final int C = 1 << 0 & 0xFFFF;
	
	//查询位
	public static final int R = 1 << 1 & 0xFFFF;
	
	//更新位
	public static final int U = 1 << 2 & 0xFFFF;
	
	//删除位
	public static final int D = 1 << 3 & 0xFFFF;
}
