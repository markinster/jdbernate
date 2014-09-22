package com.jdbernate.objects;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.TabExpander;

public class ClassScheme {

	private String name;
	private String tableName;
	private List<AttributeScheme> attributes = new ArrayList<AttributeScheme>();
	
	private String fields = "";
	private String paramns = "";
	
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

	public List<AttributeScheme> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeScheme> columns) {
		this.attributes = columns;
	}
	
	
	public String getFields() {
		if (fields.isEmpty()){
			String comma = "";
			for(AttributeScheme at: attributes){
				fields += comma + at.getDbFieldName();
				paramns+= comma + "?";
				comma = ", ";
			}
		}
		return fields;
	}
	
	public String getParamns() {
		if (paramns.isEmpty())
			getFields();
		
		return paramns;
	}

	@Override
	public String toString() {
		String retorno = name + "\n";
		for (AttributeScheme t : attributes)
			retorno += t.getName() + " " + t.getType() + "\n";
		
		return retorno;
	}

}
