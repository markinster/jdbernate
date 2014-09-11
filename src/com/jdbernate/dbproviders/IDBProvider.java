package com.jdbernate.dbproviders;

import java.util.List;

import com.jdbernate.objects.TableField;

public interface IDBProvider {

	public List<String> getTables();
	public List<TableField> getFields(String tableName);
}
