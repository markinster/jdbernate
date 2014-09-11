package com.jdbernate.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.omg.PortableInterceptor.INACTIVE;

public class DataBaseConector {

	public static int SGBD_MYSQL = 1;

	private String path;
	private String dataBaseName;
	private String user;
	private String password;
	private int sgbd;
	
	private	Connection con;

	private static DataBaseConector instance;

	private DataBaseConector() {
	}

	public static DataBaseConector getInstance() {
		if (instance == null)
			instance = new DataBaseConector();

		return instance;
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

	public int getSgbd() {
		return sgbd;
	}

	public DataBaseConector setSgbd(int sgbd) {
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
			
			if (sgbd == SGBD_MYSQL) {
				String sCon = "jdbc:mysql://" + this.path + "/" + this.dataBaseName;
				con = DriverManager.getConnection(sCon, user, password);
			}
			
		}		

		return con;
	}
}
