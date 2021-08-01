package com.rvhaley.dbunit_sqlite_datatype_factory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.dbunit.dataset.ITable;
import org.dbunit.dataset.datatype.AbstractDataType;
import org.dbunit.dataset.datatype.TypeCastException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextDataType extends AbstractDataType {

	private static final Logger logger = LoggerFactory.getLogger(TextDataType.class);

	private static final String SQL_TYPE_NAME = "TEXT";

	public TextDataType() {
		super(SQL_TYPE_NAME, Types.VARCHAR, String.class, false);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected int compareNonNulls(Object value1, Object value2) throws TypeCastException {
		logger.debug("compareNonNulls(value1={}, value2={}) - start", value1, value2);

		Comparable<String> value1comp = (Comparable<String>) value1;
		Comparable<String> value2comp = (Comparable<String>) value2;

		return value1comp.compareTo((String) value2comp);
	}

	@Override
	public int compare(Object o1, Object o2) throws TypeCastException {
		logger.debug("compare(o1={}, o2={}) - start", o1, o2);

		return super.compare(o1, o2);
	}

	@Override
	public Object typeCast(Object value) throws TypeCastException {
		logger.debug("typeCast(value={}) - start", value);

		if (value == null || value == ITable.NO_VALUE) {
			return null;
		}

		if (value instanceof String) {
			return value;
		}

		String stringValue = value.toString().trim();
		if (stringValue.length() <= 0) {
			return null;
		}

		return (String) value;
	}

	@Override
	public Object getSqlValue(int column, ResultSet resultSet) throws SQLException, TypeCastException {
		if (logger.isDebugEnabled())
			logger.debug("getSqlValue(column={}, resultSet={}) - start", new Integer(column), resultSet);

		String value = resultSet.getString(column);
		if (resultSet.wasNull()) {
			return null;
		}

		return value;
	}

	@Override
	public void setSqlValue(Object value, int column, PreparedStatement statement)
			throws SQLException, TypeCastException {
		if (logger.isDebugEnabled())
			logger.debug("setSqlValue(value={}, column={}, statement={}) - start",
					new Object[] { value, (String) value, statement });

		statement.setString(column, ((String) typeCast(value)));
	}

	@Override
	public String getSqlTypeName() {
		return SQL_TYPE_NAME;
	}
	
	

}
