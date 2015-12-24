
package com.jdbernate.typemakers;

import java.util.Map;

public class JavaTypeMakerMySQL implements ITypeMaker {

	private Map<String, String> types;

	private static final String INT = "int";
	private static final String INTEGER = "integer";
	private static final String CHAR = "char";
	private static final String VARCHAR = "varchar";
	private static final String TINYTEXT = "tinytext";
	private static final String SMALLTEXT = "smalltext";
	private static final String MEDIUMTEXT = "mediumtext";
	private static final String BIGTEXT = "bigtext";
	private static final String SERIAL = "serial";
	private static final String CHARACTER = "character";
	private static final String DECIMAL = "decimal";
	private static final String DOUBLE = "double";
	private static final String TIME = "time";
	private static final String TINYINT = "tinyint";
	private static final String SMALLINT = "smallint";
	private static final String MEDIUMINT = "mediumint";
	private static final String BIGINT = "bigint";
	private static final String FLOAT = "float";
	private static final String REAL = "real";
	private static final String NUMERIC = "numeric";
	private static final String DATE = "date";
	private static final String YEAR = "year";

	@Override
	public String getType(String typeColumn) {
		String[] typeBroken = typeColumn.trim().split("\\(");
		String type = typeBroken[0];

		if (typeBroken.length > 1) {
			typeBroken = typeBroken[1].split("\\)");
			String size = typeBroken[0];
			return returnType(type, size);
		} else {
			return returnType(type);
		}
	}

	private void createMapTypes() {
		types.put(CHARACTER, "String");
		types.put(VARCHAR, "String");
		types.put(CHAR, "String");
		types.put(BIGTEXT, "String");
		types.put(MEDIUMTEXT, "String");
		types.put(SMALLTEXT, "String");
		types.put(TINYTEXT, "String");
		types.put(INT, "Integer");
		types.put(INTEGER, "Integer");
		types.put(SERIAL, "Integer");
		types.put(BIGINT, "Integer");
		types.put(MEDIUMINT, "Integer");
		types.put(SMALLINT, "Integer");
		types.put(TINYINT, "Integer");
		types.put(DECIMAL, "BigDecimal");
		types.put(DOUBLE, "BigDecimal");
		types.put(FLOAT, "BigDecimal");
		types.put(REAL, "BigDecimal");
		types.put(NUMERIC, "BigDecimal");
		types.put(YEAR, "Short");
		types.put(TIME, "Calendar");
		types.put(DATE, "Calendar");
	}

	private String returnType(String type) {
		type = type.toLowerCase();

		if (type == null)
			createMapTypes();

		String ret;
		try {
			ret = types.get(type);
		} catch (Exception e) {
			ret = "String";
		}
		
		if (ret == null || ret.isEmpty())
			ret = "String";
		
		return ret;
	}

	private String returnType(String type, String size) {
		return returnType(type);
	}
}
