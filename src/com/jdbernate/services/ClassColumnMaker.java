package com.jdbernate.services;

import com.jdbernate.JDbernate;
import com.jdbernate.objects.AttributeScheme;
import com.jdbernate.objects.Column;
import com.jdbernate.typemakers.JavaTypeMakerMySQL;

public class ClassColumnMaker {

	//created a attribute from table fields	
	public AttributeScheme getClassColumn(Column tableColumn, boolean usePrefix) {
		AttributeScheme cCol = new AttributeScheme();
		String name = new ClassNameMaker().getAttributeName(tableColumn.getName(), usePrefix);		
		
		name = name.substring(0, 1).toLowerCase() + name.substring(1);
		
		cCol.setName(name);
		cCol.setType(JDbernate.javaTypeMaker.getType(tableColumn.getType()));
		cCol.setDbFieldName(tableColumn.getName());

		return cCol;
	}
}
