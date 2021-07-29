package com.rvhaley.dbunit_sqlite_datatype_factory;

import java.sql.Types;
import java.util.Arrays;
import java.util.Collection;

import org.dbunit.dataset.datatype.DataType;
import org.dbunit.dataset.datatype.DataTypeException;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a DataType factory implementation for using DbUnit with a local
 * SQLite database.
 * 
 * @author Richard Haley III
 *
 */
public class SQLiteDataTypeFactory extends DefaultDataTypeFactory {

	private static final Logger logger = LoggerFactory.getLogger(SQLiteDataTypeFactory.class);

	private static final Collection<String> DATABASE_PRODUCTS = Arrays.asList(new String[] { "sqlite" });

	@Override
	public Collection<String> getValidDbProducts() {
		return DATABASE_PRODUCTS;
	}

	@Override
	public DataType createDataType(int sqlType, String sqlTypeName) throws DataTypeException {
		if (logger.isDebugEnabled())
			logger.debug("createDataType(sqlType={}, sqlTypeName={}) - start", String.valueOf(sqlType), sqlTypeName);

		if (sqlType != Types.OTHER && "text".equalsIgnoreCase(sqlTypeName)) {
			return DataType.VARCHAR;
		}

		return super.createDataType(sqlType, sqlTypeName);
	}

}
