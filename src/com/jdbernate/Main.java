package com.jdbernate;

import java.io.IOException;
import java.sql.SQLException;

import com.jdbernate.dao.DataBaseConector;

public class Main {

	public static void main(String[] args) {
		DataBaseConector data = DataBaseConector.getInstance();
		data.setDataBaseName("mercurio").setUser("root").setPassword("123456")
				.setPath("localhost").setSgbd(DataBaseConector.SGBD_MYSQL);
		
		try {
			new JDbernate().process();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
