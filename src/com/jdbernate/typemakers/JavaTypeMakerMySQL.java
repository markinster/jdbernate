
package com.jdbernate.typemakers;

import java.util.HashMap;
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
		types = new HashMap<String, String>();
		types.put(CHARACTER, "string");
		types.put(VARCHAR, "string");
		types.put(CHAR, "string");
		types.put(BIGTEXT, "string");
		types.put(MEDIUMTEXT, "string");
		types.put(SMALLTEXT, "string");
		types.put(TINYTEXT, "string");
		types.put(INT, "int");
		types.put(INTEGER, "int");
		types.put(SERIAL, "int");
		types.put(BIGINT, "int");
		types.put(MEDIUMINT, "int");
		types.put(SMALLINT, "int");
		types.put(TINYINT, "int");
		types.put(DECIMAL, "double");
		types.put(DOUBLE, "double");
		types.put(FLOAT, "double");
		types.put(REAL, "double");
		types.put(NUMERIC, "double");
		types.put(YEAR, "int32");
		types.put(TIME, "string");
		types.put(DATE, "string");
	}

	private String returnType(String type) {
		type = type.toLowerCase();

		if (types == null)
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
