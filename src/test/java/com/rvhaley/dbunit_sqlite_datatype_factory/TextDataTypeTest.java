package com.rvhaley.dbunit_sqlite_datatype_factory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.sql.Types;

import org.dbunit.dataset.datatype.TypeCastException;
import org.junit.Test;

public class TextDataTypeTest {

	@Test
	public void getSqlTypeNameReturnsCorrectValue() {
		TextDataType tdt = new TextDataType();
		assertThat(tdt.getSqlTypeName(), equalToIgnoringCase("TEXT"));
	}

	@Test
	public void getSqlTypeNameReturnsNonNullValue() {
		TextDataType tdt = new TextDataType();
		assertThat(tdt.getSqlTypeName(), is(notNullValue()));
	}

	@Test
	public void getSqlTypeReturnsCorrectValue() {
		TextDataType tdt = new TextDataType();
		assertThat(tdt.getSqlType(), is(Types.VARCHAR));
	}

	@Test
	public void getTypeClassReturnsStringClass() {
		TextDataType tdt = new TextDataType();
		assertThat(tdt.getTypeClass(), is(String.class));
	}

	@Test
	public void isNumberReturnsFalse() {
		TextDataType tdt = new TextDataType();
		assertThat(tdt.isNumber(), is(false));
	}

	@Test
	public void isDateTime() {
		TextDataType tdt = new TextDataType();
		assertThat(tdt.isDateTime(), is(false));
	}

	@Test
	public void typeCastReturnsInstanceOfString() throws TypeCastException {
		final String testString = "testing";
		TextDataType tdt = new TextDataType();
		Object typeCast = tdt.typeCast((Object) testString);
		assertThat(typeCast, is(instanceOf(String.class)));
	}

	@Test
	public void typeCastReturnsCorrectString() throws TypeCastException {
		final String testString = "testing";
		TextDataType tdt = new TextDataType();
		String typeCastStr = (String) tdt.typeCast((Object) testString);
		assertThat(typeCastStr, equalToIgnoringCase(testString));
	}

}
