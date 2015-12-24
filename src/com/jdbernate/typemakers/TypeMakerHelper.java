package com.jdbernate.typemakers;

import com.jdbernate.connection.DataBaseConnector;

public class TypeMakerHelper {

	public static ITypeMaker getTypeMaker() {
		if (DataBaseConnector.getInstance().getSgbd().equals(DataBaseConnector.SGBD_MYSQL)) {
			return new JavaTypeMakerMySQL();
		}
		
		return null;
	}
 }
