package com.jdbernate;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import com.jdbernate.conector.DataBaseConector;
import com.jdbernate.config.JDbernateConfig;

public class Main {

	public static void main(String[] args) {		
		try {
			// connect to data base
			DataBaseConector.getInstance();	
			
			File f = new File(new JDbernateConfig().FOLDER);
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
