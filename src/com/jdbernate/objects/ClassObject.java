package com.jdbernate.objects;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.TabExpander;

public class ClassObject {

	private String name;
	private String tableName;
	private List<Attributes> attributes = new ArrayList<Attributes>();
	private List<TableField> tableColumns = new ArrayList<TableField>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<Attributes> getColumns() {
		return attributes;
	}

	public void setColumns(List<Attributes> columns) {
		this.attributes = columns;
	}

	public List<TableField> getTableColumns() {
		return tableColumns;
	}

	public void setTableColumns(List<TableField> tableColumns) {
		this.tableColumns = tableColumns;
	}
	
	@Override
	public String toString() {
		String retorno = name + "\n";
		for (Attributes t : attributes)
			retorno += t.getName() + " " + t.getType() + "\n";
		
		return retorno;
	}

}
