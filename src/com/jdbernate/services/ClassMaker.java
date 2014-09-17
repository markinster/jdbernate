package com.jdbernate.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbernate.dbproviders.DBProviderBuilder;
import com.jdbernate.objects.JAttribute;
import com.jdbernate.objects.ClassObject;
import com.jdbernate.objects.TableField;

public class ClassMaker {

	public ClassObject builder(String table, boolean usePrefix) throws SQLException {

		ClassObject clazz = new ClassObject();
		clazz.setTableName(table);

		// build class Name from table Name
		String className = new ClassNameMaker().getClassName(table);
		clazz.setName(className);

		// build a list of fields
		List<TableField> fields = DBProviderBuilder.getDBProvider().getFields(table);
		
		List<JAttribute> attributes = new ArrayList<JAttribute>();
		for (TableField field : fields)
			attributes.add(new ClassColumnMaker().getClassColumn(field, usePrefix));

		clazz.setAttributes(attributes);

		System.out.println(clazz);
		
		return clazz;
	}

}
