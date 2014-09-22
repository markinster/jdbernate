package com.jdbernate.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbernate.dbproviders.DBProviderBuilder;
import com.jdbernate.objects.AttributeScheme;
import com.jdbernate.objects.ClassScheme;
import com.jdbernate.objects.Column;

public class ClassMaker {

	public ClassScheme builder(String table, boolean usePrefix) throws SQLException {

		ClassScheme clazz = new ClassScheme();
		clazz.setTableName(table);

		// build class Name from table Name
		String className = new ClassNameMaker().getClassName(table);
		clazz.setName(className);

		// build a list of fields
		List<Column> fields = DBProviderBuilder.getDBProvider().getFields(table);
		
		List<AttributeScheme> attributes = new ArrayList<AttributeScheme>();
		for (Column field : fields)
			attributes.add(new ClassColumnMaker().getClassColumn(field, usePrefix));

		clazz.setAttributes(attributes);

		System.out.println(clazz);
		
		return clazz;
	}

}
