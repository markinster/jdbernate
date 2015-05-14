package com.jdbernate;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import com.jdbernate.connection.DataBaseConnector;

public class Main {

	public static void main(String[] args) {		
		try {
			// connect to data base
			DataBaseConnector.getInstance().load();	
			
			File f = new File(DataBaseConnector.getInstance().getFolder());
			f.mkdirs();
			
			JDbernate jDbernate = new JDbernate();
			jDbernate.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
