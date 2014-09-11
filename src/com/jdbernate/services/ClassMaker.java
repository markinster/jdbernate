package com.jdbernate.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbernate.objects.Attributes;
import com.jdbernate.objects.ClassObject;
import com.jdbernate.objects.TableField;

public class ClassMaker {

	public void builder(String table, Connection con) throws SQLException {
		
		ClassObject clazz = new ClassObject();
		clazz.setTableName(table);		
		
		//build class Name from table Name 
		String className = new ClassNameMaker().getClassName(table);
		clazz.setName(className);
		
		//fint all fields from table
		String sql = "show fields from " + table + " ; ";
		ResultSet rs = con.prepareStatement(sql).executeQuery();
		
		//build a list of fields
		List<TableField> fields = new ArrayList<TableField>();
		List<Attributes> columns = new ArrayList<Attributes>();
		
		while (rs.next()){
			TableField field = new TableField();
			field.setName(rs.getString("Field"));
			field.setType(rs.getString("Type"));
			
			fields.add(field);
			
			columns.add(new ClassColumnMaker().getClassColumn(field));
		}
		clazz.setTableColumns(fields);
		clazz.setColumns(columns);		
		

		System.out.println(clazz);
	}

}
