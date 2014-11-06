/*
*      REMINDER: Refactor this class for use MAP <06-11-2014>
*/


package com.jdbernate.typemakers;

public class JavaTypeMakerMySQL implements IJavaTypeMaker {
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

	private String returnType(String type) {
		type = type.toLowerCase();

		if (type.startsWith(CHARACTER)) {
			return "String";
		} else if (type.equals(VARCHAR)) {
			return "String";
		} else if (type.equals(INT)) {
			return "Integer";
		} else if (type.equals(INTEGER)) {
			return "Integer";
		} else if (type.equals(SERIAL)) {
			return "Integer";
		} else if (type.equals(DECIMAL)) {
			return "BigDecimal";
		} else if (type.equals(DOUBLE)) {
			return "BigDecimal";
		} else if (type.startsWith(TIME)) {
			return "Calendar";
		} else if (type.startsWith(CHAR)) {
			return "String";
		} else if (type.startsWith(BIGTEXT)) {
			return "String";
		} else if (type.startsWith(MEDIUMTEXT)) {
			return "String";
		} else if (type.startsWith(SMALLTEXT)) {
			return "String";
		} else if (type.startsWith(TINYTEXT)) {
			return "String";
		} else if (type.startsWith(BIGINT)) {
			return "Integer";
		} else if (type.startsWith(MEDIUMINT)) {
			return "Integer";
		} else if (type.startsWith(SMALLINT)) {
			return "Integer";
		} else if (type.startsWith(TINYINT)) {
			return "Integer";
		} else if (type.startsWith(FLOAT)) {
			return "BigDecimal";
		} else if (type.startsWith(REAL)) {
			return "BigDecimal";
		} else if (type.startsWith(NUMERIC)) {
			return "BigDecimal";
		} else if (type.startsWith(YEAR)) {
			return "Short";
		} else if (type.startsWith(DATE)) {
			return "Calendar";
		}

		return "String";
	}

	private String returnType(String type, String size) {
		return returnType(type);
	}
}
