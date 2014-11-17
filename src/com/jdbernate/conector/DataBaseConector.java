package com.jdbernate.conector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConector {

	public static String SGBD_MYSQL = "mysql";

	private String sgbd;
	private String path;
	private String port;
	private String dataBaseName;
	private String user;
	private String password;
	
	
	private	Connection con;

	private static DataBaseConector instance;

	private DataBaseConector()  {
		
	}

	public static DataBaseConector getInstance() {
		if (instance == null)
			instance = new DataBaseConector();	

		return instance;
	}
	
	public void load() throws IOException { 
		
		Properties properties = new PropertiesLoader().getProperties();
		instance.sgbd = properties.getProperty("sgbd").trim().toLowerCase();
		instance.path = properties.getProperty("database.path").trim();
		instance.dataBaseName = properties.getProperty("database.name").trim();
		instance.port = properties.getProperty("database.port").trim();
		instance.user = properties.getProperty("database.user").trim();
		instance.password = properties.getProperty("database.password").trim();
	}

	public String getPath() {
		return path;
	}

	public DataBaseConector setPath(String path) {
		this.path = path;
		return this;
	}

	public String getDataBaseName() {
		return dataBaseName;
	}

	public DataBaseConector setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
		return this;
	}

	public String getSgbd() {
		return sgbd;
	}

	public DataBaseConector setSgbd(String sgbd) {
		this.sgbd = sgbd;
		return this;
	}

	public String getUser() {
		return user;
	}

	public DataBaseConector setUser(String user) {
		this.user = user;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public DataBaseConector setPassword(String password) {
		this.password = password;
		return this;
	}

	public Connection getConnection() throws SQLException {
		if (con == null || con.isClosed()){
			
			if (sgbd.equals(SGBD_MYSQL)) {
				String sCon = "jdbc:mysql://" + this.path + "/" + this.dataBaseName;
				con = DriverManager.getConnection(sCon, user, password);
			}
			
		}		

		return con;
	}
}
