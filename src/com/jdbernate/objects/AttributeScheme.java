package com.jdbernate.objects;

public class AttributeScheme extends ColumnBase {	

	private String dbFieldName;
	
	public String getDbFieldName() {
		return dbFieldName;
	}
	
	public void setDbFieldName(String dbFieldName) {
		this.dbFieldName = dbFieldName;
	}
}
