package com.rvhaley.dbunit_sqlite_datatype_factory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.empty;

import java.sql.Types;
import java.util.Collection;
import java.util.List;

import org.dbunit.dataset.datatype.DataType;
import org.dbunit.dataset.datatype.DataTypeException;
import org.junit.Test;

public class SQLiteDataTypeFactoryTest {

	@Test
	public void createDataTypeReturnsNonNullValue() throws DataTypeException {
		SQLiteDataTypeFactory sqlite = new SQLiteDataTypeFactory();
		DataType dt = sqlite.createDataType(Types.VARCHAR, "TEXT");
		assertThat(dt, is(notNullValue()));
	}

	@Test
	public void createDataTypeReturnsInstanceOfDataType() throws DataTypeException {
		SQLiteDataTypeFactory sqlite = new SQLiteDataTypeFactory();
		DataType dt = sqlite.createDataType(Types.VARCHAR, "TEXT");
		assertThat(dt, is(instanceOf(TextDataType.class)));
	}

	@Test
	public void createDataTypeReturnsCorrectSqlType() throws DataTypeException {
		SQLiteDataTypeFactory sqlite = new SQLiteDataTypeFactory();
		DataType dt = sqlite.createDataType(Types.VARCHAR, "TEXT");
		assertThat(dt.getSqlType(), is(Types.VARCHAR));
	}

	@Test
	public void createDataTypeReturnsCorrectSqlTypeName() throws DataTypeException {
		SQLiteDataTypeFactory sqlite = new SQLiteDataTypeFactory();
		DataType dt = sqlite.createDataType(Types.VARCHAR, "TEXT");
		assertThat(dt.getSqlTypeName(), equalToIgnoringCase("TEXT"));
	}

	@Test
	public void getValidDbProductsReturnsSingleElement() {
		SQLiteDataTypeFactory sqlite = new SQLiteDataTypeFactory();
		Collection<String> dbProducts = sqlite.getValidDbProducts();
		assertThat(dbProducts, hasSize(1));
	}

	@Test
	public void getValidDbProductsReturnsNonEmptyCollection() {
		SQLiteDataTypeFactory sqlite = new SQLiteDataTypeFactory();
		Collection<String> dbProducts = sqlite.getValidDbProducts();
		assertThat(dbProducts, not(empty()));
	}

	@Test
	public void getValidDbProductsReturnsCollectionTypeList() throws DataTypeException {
		SQLiteDataTypeFactory sqlite = new SQLiteDataTypeFactory();
		Collection<String> dbProducts = sqlite.getValidDbProducts();
		assertThat(dbProducts, is(instanceOf(List.class)));
	}

	@Test
	public void getValidDbProductsReturnsCorrectStringInCollection() throws DataTypeException {
		SQLiteDataTypeFactory sqlite = new SQLiteDataTypeFactory();
		Collection<String> dbProducts = sqlite.getValidDbProducts();
		assertThat(dbProducts, hasItem("sqlite"));
	}

}
