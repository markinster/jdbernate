package com.jdbernate.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnector {

	public static String SGBD_MYSQL = "mysql";

	private String sgbd;
	private String path;
	private String port;
	private String dataBaseName;
	private String user;
	private String password;
	private String language;
	
	private String folder = "src//com//jdbernate//entities";
	private String PACKAGE = "com.jdbernate.entities";	
	
	private	Connection con;

	private static DataBaseConnector instance;

	private DataBaseConnector()  {
		
	}

	public static DataBaseConnector getInstance() {
		if (instance == null)
			instance = new DataBaseConnector();	

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
		instance.language = properties.getProperty("database.language").trim();
		
		String pak = properties.getProperty("database.package").trim();
		if (pak != null && !pak.trim().isEmpty()) {
			PACKAGE = pak;
			folder = "src";
			String[] split = pak.split("\\.");
			for (String folders : split) 
				folder += "//" + folders;			
		}
	}

	public String getPath() {
		return path;
	}

	public DataBaseConnector setPath(String path) {
		this.path = path;
		return this;
	}

	public String getDataBaseName() {
		return dataBaseName;
	}

	public DataBaseConnector setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
		return this;
	}

	public String getSgbd() {
		return sgbd;
	}

	public DataBaseConnector setSgbd(String sgbd) {
		this.sgbd = sgbd;
		return this;
	}

	public String getUser() {
		return user;
	}

	public DataBaseConnector setUser(String user) {
		this.user = user;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public DataBaseConnector setPassword(String password) {
		this.password = password;
		return this;
	}
	
	
	public boolean isCSharp() {
		return language != null && language.toLowerCase().equals("c#");
	}
	
	public String getFolder() {
		return folder;
	}
	
	public String getPACKAGE() {
		return PACKAGE;
	}
	
	public String getPort() {
		return port;
	}

	public Connection getConnection() throws SQLException {
		if (con == null || con.isClosed()){
			
			if (SGBD_MYSQL.equals(sgbd)) {
				String sCon = "jdbc:mysql://" + this.path + "/" + this.dataBaseName;
				con = DriverManager.getConnection(sCon, user, password);
			}
			
		}		

		return con;
	}
}
