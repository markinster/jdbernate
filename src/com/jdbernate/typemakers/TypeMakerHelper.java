package com.jdbernate.typemakers;

import com.jdbernate.connection.DataBaseConnector;

public class TypeMakerHelper {

	public static ITypeMaker getTypeMaker() {
		if (DataBaseConnector.getInstance().getSgbd().equals(DataBaseConnector.SGBD_MYSQL)) {
			
			if(DataBaseConnector.getInstance().isCSharp())
				return new CSharpTypeMakerMySQL();
			else	
				return new JavaTypeMakerMySQL();
		}
		
		return null;
	}
 }
