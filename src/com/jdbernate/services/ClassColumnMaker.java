package com.jdbernate.services;

import com.jdbernate.JDbernate;
import com.jdbernate.objects.AttributeScheme;
import com.jdbernate.objects.Column;
import com.jdbernate.typemakers.JavaTypeMakerMySQL;

public class ClassColumnMaker {

	//created a attribute from table fields	
	public AttributeScheme getClassColumn(Column tableColumn) {
		AttributeScheme cCol = new AttributeScheme();
		String name = new ClassNameMaker().getAttributeName(tableColumn.getName());		
		
		name = name.substring(0, 1).toLowerCase() + name.substring(1);
		
		cCol.setName(name);
		cCol.setType(JDbernate.typeMaker.getType(tableColumn.getType()));
		cCol.setDbFieldName(tableColumn.getName());
		cCol.setTableOriginalName(tableColumn.getName());

		return cCol;
	}
}
