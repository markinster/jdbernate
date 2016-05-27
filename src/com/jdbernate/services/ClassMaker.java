/**
 * This class return a ClassScheme, for table
 * passed by parametter <table> 
 */
package com.jdbernate.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbernate.dbproviders.DBProviderBuilder;
import com.jdbernate.dbproviders.IDBProvider;
import com.jdbernate.objects.AttributeScheme;
import com.jdbernate.objects.ClassScheme;
import com.jdbernate.objects.Column;

/**
 * 
 * @author marcos
 *
 */
public class ClassMaker {

	public ClassScheme builder(String table) throws SQLException {

		IDBProvider dbProvider = DBProviderBuilder.getDBProvider();

		ClassScheme clazz = new ClassScheme();
		clazz.setTableName(table);

		// build class Name from table Name
		String className = new ClassNameMaker().getClassName(table);
		clazz.setName(className);

		// build a list of fields
		List<Column> fields = dbProvider.getFields(table);
		
		List<AttributeScheme> attributes = new ArrayList<AttributeScheme>();
		for (Column field : fields)
			attributes.add(new ClassColumnMaker().getClassColumn(field));

		clazz.setAttributes(attributes);
		
		List<String> primaryKeys = dbProvider.getPrimaryKeys(table);
		clazz.setPrivareKeys(primaryKeys == null ? new ArrayList<String>() : primaryKeys);
		
		return clazz;
	}

}
