/**
 * @author Marcos Soares, Markinster
 * markinster@gmail.com
 * 
 * 
 */
package com.jdbernate.services;

import java.util.ArrayList;
import java.util.List;

public class ClassNameMaker {

	/**
	 * This method return one list with names that classes , builded by list of
	 * strings
	 *
	 * @param tables
	 *            - List of String
	 * @return names that classes
	 */
	public List<String> getClassNames(List<String> tables) {
		List<String> classes = new ArrayList<String>();

		for (String tableName : tables) {

			classes.add(getClassName(tableName));

		}

		return classes;
	}

	public String getClassName(String tableName) {
		String[] tableBroken = tableName.split("_");
		String className = "";
		for (String tablePart : tableBroken) {
			className += tablePart.substring(0, 1).toUpperCase()
					+ tablePart.substring(1).toLowerCase();
		}

		return className.isEmpty() ? null : className;
	}

	public String getAttributeName(String columnName) {
		return getClassName(columnName);
	}
}
