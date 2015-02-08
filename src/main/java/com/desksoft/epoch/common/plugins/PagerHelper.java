package com.desksoft.epoch.common.plugins;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * 
 * @author fanghc
 * @date Jun 28, 2014 11:45:18 PM
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }),
		@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }) })
public class PagerHelper implements Interceptor {

	private static final Logger logger = Logger.getLogger(PagerHelper.class);

	@SuppressWarnings("rawtypes")
	public static final ThreadLocal<Pager> localPager = new ThreadLocal<Pager>();
	
	

	/**
	 * 开始分页
	 * 
	 * @param pageNum
	 * @param pageSize
	 */
	@SuppressWarnings("rawtypes")
	public static void startPager(int pageNum, int pageSize) {
		localPager.set(new Pager(pageNum, pageSize));
	}

	/**
	 * 结束分页并返回结果，该方法必须被调用，否则localPage会一直保存下去，直到下一次startPage
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Pager endPager() {
		Pager page = localPager.get();
		localPager.remove();
		return page;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		if (localPager.get() == null) {
			return invocation.proceed();
		}
		if (invocation.getTarget() instanceof StatementHandler) {
			StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
			MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
			// 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环
			// 可以分离出最原始的的目标类)
			while (metaStatementHandler.hasGetter("h")) {
				Object object = metaStatementHandler.getValue("h");
				metaStatementHandler = SystemMetaObject.forObject(object);
			}
			// 分离最后一个代理对象的目标类
			while (metaStatementHandler.hasGetter("target")) {
				Object object = metaStatementHandler.getValue("target");
				metaStatementHandler = SystemMetaObject.forObject(object);
			}
			MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
			// 分页信息if (localPage.get() != null) {
			Pager page = localPager.get();
			BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
			// 分页参数作为参数对象parameterObject的一个属性
			String sql = boundSql.getSql();
			// 重写sql
			String pageSql = buildPageSql(sql, page);
			// 重写分页sql
			metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
			Connection connection = (Connection) invocation.getArgs()[0];
			// 重设分页参数里的总页数等
			setPageParameter(sql, connection, mappedStatement, boundSql, page);
			// 将执行权交给下一个拦截器
			return invocation.proceed();
		} else if (invocation.getTarget() instanceof ResultSetHandler) {
			Object result = invocation.proceed();
			Pager pager = localPager.get();
			pager.setResult((List)result);
			return result;
		}
		
		return null;
	}

	/**
	 * 只拦截这两种类型的 <br>
	 * StatementHandler <br>
	 * ResultSetHandler
	 * 
	 * @param target
	 * @return
	 */
	@Override
	public Object plugin(Object target) {
		if (target instanceof StatementHandler || target instanceof ResultSetHandler) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties properties) {

	}

	/**
	 * 修改原SQL为分页SQL
	 * 
	 * @param sql
	 * @param page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private String buildPageSql(String sql, Pager page) {

		return sql += " limit " + page.getOffset() + "," + page.getPageSize();
	}
	
	private String buildCountSql(String sql){
		if (sql.toLowerCase().indexOf("group") != -1){
			return "select count(1) from (" + sql + ") just_a_temp_table";
		}
		
		return "select count(1) " + sql.substring(sql.toLowerCase().indexOf("from"));
	}
	

	/**
	 * 获取总记录数
	 * 
	 * @param sql
	 * @param connection
	 * @param mappedStatement
	 * @param boundSql
	 * @param page
	 */
	@SuppressWarnings("rawtypes")
	private void setPageParameter(String sql, Connection connection, MappedStatement mappedStatement, BoundSql boundSql, Pager page) {
		// 记录总记录数
		String countSql = buildCountSql(sql);
		PreparedStatement countStmt = null;
		ResultSet rs = null;
		try {
			countStmt = connection.prepareStatement(countSql);
			BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
			setParameters(countStmt, mappedStatement, countBS, boundSql.getParameterObject());
			rs = countStmt.executeQuery();
			int totalCount = 0;
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}
			page.setTotalCounts(totalCount);
			int totalPage = totalCount / page.getPageSize() + ((totalCount % page.getPageSize() == 0) ? 0 : 1);
			page.setTotalPages(totalPage);
		} catch (SQLException e) {
			logger.error("Ignore this exception", e);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error("Ignore this exception", e);
			}
			try {
				countStmt.close();
			} catch (SQLException e) {
				logger.error("Ignore this exception", e);
			}
		}
	}

	/**
	 * 代入参数值
	 * 
	 * @param ps
	 * @param mappedStatement
	 * @param boundSql
	 * @param parameterObject
	 * @throws SQLException
	 */
	private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
		ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
		parameterHandler.setParameters(ps);
	}
}
