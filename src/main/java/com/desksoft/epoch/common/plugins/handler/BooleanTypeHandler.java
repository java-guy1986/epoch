package com.desksoft.epoch.common.plugins.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class BooleanTypeHandler implements TypeHandler<Boolean> {

	private static final String TRUE_INT_VALUE = "1";
	private static final String FALSE_INT_VALUE = "0";
	
	@Override
	public void setParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {
		if (parameter == Boolean.TRUE){
			ps.setString(i, TRUE_INT_VALUE);
		} else {
			ps.setString(i, FALSE_INT_VALUE);
		}
	}

	@Override
	public Boolean getResult(ResultSet rs, String columnName) throws SQLException {
		String value = rs.getString(columnName);
		if (value.equals(TRUE_INT_VALUE)){
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}

	@Override
	public Boolean getResult(ResultSet rs, int columnIndex) throws SQLException {
		String value = rs.getString(columnIndex);
		if (value.equals(TRUE_INT_VALUE)){
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}

	@Override
	public Boolean getResult(CallableStatement cs, int columnIndex) throws SQLException {
		String value = cs.getString(columnIndex);
		if (value.equals(TRUE_INT_VALUE)){
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}
}
