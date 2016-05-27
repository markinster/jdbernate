package com.jdbernate.dbproviders;

import java.util.List;

import com.jdbernate.objects.Column;

public interface IDBProvider {

	public List<String> getTables();
	public List<Column> getFields(String tableName);
	public List<String> getPrimaryKeys(String tableName);
}
