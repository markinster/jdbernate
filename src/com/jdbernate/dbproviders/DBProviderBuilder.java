package com.jdbernate.dbproviders;

import com.jdbernate.connection.DataBaseConnector;

public class DBProviderBuilder {

	public static IDBProvider getDBProvider()  {
		if (DataBaseConnector.getInstance().getSgbd().equals(DataBaseConnector.SGBD_MYSQL)){
			return new MySQLProvider();
		}
		
		return null;
	}
}
