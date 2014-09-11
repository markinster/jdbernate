package com.jdbernate.objects;

public class JAttribute extends Column {	

	private String dbFieldName;
	
	public String getDbFieldName() {
		return dbFieldName;
	}
	
	public void setDbFieldName(String dbFieldName) {
		this.dbFieldName = dbFieldName;
	}
}
