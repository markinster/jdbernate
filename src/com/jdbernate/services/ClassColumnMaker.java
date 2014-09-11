package com.jdbernate.services;

import com.jdbernate.JDbernate;
import com.jdbernate.objects.Attributes;
import com.jdbernate.objects.TableField;
import com.jdbernate.typemakers.TypeMakerMySQL;

public class ClassColumnMaker {

	//created a attribute from table fields	
	public Attributes getClassColumn(TableField tableColumn) {
		Attributes cCol = new Attributes();
		String name = new ClassNameMaker().getClassName(tableColumn.getName());		
		name = name.substring(0, 1).toLowerCase() + name.substring(1);
		
		cCol.setName(name);
		cCol.setType(JDbernate.typeMaker.getType(tableColumn.getType()));

		return cCol;
	}
}
