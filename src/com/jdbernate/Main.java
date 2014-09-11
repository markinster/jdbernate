package com.jdbernate;

import java.sql.Connection;
import java.sql.SQLException;

import com.jdbernate.dao.DataBaseConector;

public class Main {

	public static void main(String[] args) {
		DataBaseConector data = DataBaseConector.getInstance();
		data.setDataBaseName("loja").setUser("root").setPassword("123456")
				.setPath("localhost").setSgbd(DataBaseConector.SGBD_MYSQL);
		
		try {
			Connection connection = data.getConnection();
			new JDbernate().process(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
