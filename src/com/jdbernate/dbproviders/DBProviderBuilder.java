package com.jdbernate.dbproviders;

import com.jdbernate.conector.DataBaseConector;

public class DBProviderBuilder {

	public static IDBProvider getDBProvider()  {
		if (DataBaseConector.getInstance().getSgbd().equals(DataBaseConector.SGBD_MYSQL)){
			return new MySQLProvider();
		}
		
		return null;
	}
}
