package com.jdbernate.dbproviders;

import java.io.IOException;

import com.jdbernate.conector.DataBaseConector;

public class DBProviderBuilder {

	public static IDBProvider getDBProvider() throws IOException {
		if (DataBaseConector.getInstance().getSgbd().equals(DataBaseConector.SGBD_MYSQL)){
			return new MySQLProvider();
		}
		
		return null;
	}
}
