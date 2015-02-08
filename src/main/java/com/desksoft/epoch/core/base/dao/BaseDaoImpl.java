package com.desksoft.epoch.core.base.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;

import com.desksoft.epoch.common.plugins.Pager;
import com.desksoft.epoch.common.plugins.PagerHelper;

/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jul 1, 2014 11:29:09 PM
 */
public class BaseDaoImpl {

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	
	/**
	 * 分页查询
	 * @param statementId
	 * @param params
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	protected <T> Pager<T> selectPager(String statement, Map<String, Object> params) throws Exception{
		if (StringUtils.isEmpty(statement)){
			throw new NullPointerException("Select pager statement should not be null!");
		}
		int pageNum = Pager.PAGE_NUM_DEFAULT_VALUE;
		int pageSize = Pager.PAGE_SIZE_DEFAULT_VALUE;
		if (params != null){
			if (params.get(Pager.PAGE_NUM_KEY) != null 
					&& (Integer)params.get(Pager.PAGE_NUM_KEY) > 0){
				pageNum = (Integer)params.get(Pager.PAGE_NUM_KEY);
			}
			if (params.get(Pager.PAGE_SIZE_KEY) != null && 
					(Integer)params.get(Pager.PAGE_SIZE_KEY) > 0){
				pageSize = (Integer)params.get(Pager.PAGE_SIZE_KEY);
			}
		}
		PagerHelper.startPager(pageNum, pageSize);
		try {
			sqlSessionTemplate.selectList(statement, params);
		} catch (Exception e) {
			throw new DaoException("分页查询失败", e);
		}
		
		
		return PagerHelper.endPager();
	}

	/**
	 * 查询对象列表
	 * @param statement
	 * @param params
	 * @return
	 * @throws DaoException
	 */
	protected <T> List<T> selectList(String statement, Map<String, Object> params) throws Exception{
		if (StringUtils.isEmpty(statement)){
			throw new NullPointerException("Select list statement should not be null!");
		}
		try {
			return this.sqlSessionTemplate.selectList(statement, params);
		} catch (Exception e) {
			throw new DaoException("查询列表失败", e);
		}
	}
	
	/**
	 * 插入对象
	 * @param statement
	 * @param parameter
	 * @throws DaoException
	 */
	protected void insert(String statement, Object object) throws Exception{
		if (StringUtils.isEmpty(statement)){
			throw new NullPointerException("Insert statement should not be null!");
		}
		if (object == null) {
			throw new NullPointerException("Insert object should not be null!");
		}
		
		try {
			this.sqlSessionTemplate.insert(statement, object);
		} catch (Exception e) {
			throw new DaoException("插入失败", e);
		}
	}
	
	/**
	 * 更新对象
	 * @param statement
	 * @param object
	 * @throws DaoException
	 */
	protected void update(String statement, Object object) throws Exception{
		if (StringUtils.isEmpty(statement)){
			throw new NullPointerException("Update statement should not be null!");
		}
		if (object == null) {
			throw new NullPointerException("Update object should not be null!");
		}
		try {
			this.sqlSessionTemplate.update(statement, object);
		} catch (Exception e) {
			throw new DaoException("更新数据失败", e);
		}
	}
	
	
	protected void delete(String statement, Object parameter) throws Exception{
		if (StringUtils.isEmpty(statement)){
			throw new NullPointerException("Delete statement should not be null!");
		}
		if (parameter == null){
			throw new NullPointerException("Delete parameter should not be null!");
		}
		try {
			this.sqlSessionTemplate.delete(statement, parameter);
		} catch (Exception e) {
			throw new DaoException("删除数据失败", e);
		}
	}
	
	/**
	 * 查询对象
	 * @param statement
	 * @param parameter
	 * @return
	 * @throws DaoException
	 */
	protected Object selectOne(String statement, Object parameter) throws Exception{
		if (StringUtils.isEmpty(statement)){
			throw new NullPointerException("Select statement should not be null!");
		}
		if (parameter == null) {
			throw new NullPointerException("Select object parameter should not be null!");
		}
		try {
			return this.sqlSessionTemplate.selectOne(statement, parameter);
		} catch (Exception e) {
			throw new DaoException("查询数据失败", e);
		}
		
	}
	
}
