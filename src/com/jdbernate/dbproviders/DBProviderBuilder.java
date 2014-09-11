package com.jdbernate.dbproviders;

import com.jdbernate.dao.DataBaseConector;

public class DBProviderBuilder {

	public static IDBProvider getDBProvider() {
		if (DataBaseConector.getInstance().getSgbd() == DataBaseConector.SGBD_MYSQL){
			return new MySQLProvider();
		}
		
		return null;
	}
}
