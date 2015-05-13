package com.jdbernate.objects;

public class AttributeScheme extends ColumnBase {	

	private String dbFieldName;
	private String tableOriginalName;
	
	public String getDbFieldName() {
		return dbFieldName;
	}
	
	public void setDbFieldName(String dbFieldName) {
		this.dbFieldName = dbFieldName;
	}
	
	public String getTableOriginalName() {
		return tableOriginalName;
	}
	
	public void setTableOriginalName(String tableOriginalName) {
		this.tableOriginalName = tableOriginalName;
	}
}
