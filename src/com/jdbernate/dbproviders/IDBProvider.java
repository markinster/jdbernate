package com.jdbernate.dbproviders;

import java.io.IOException;
import java.util.List;

import com.jdbernate.objects.Column;

public interface IDBProvider {

	public List<String> getTables() throws IOException;
	public List<Column> getFields(String tableName) throws IOException;
}
