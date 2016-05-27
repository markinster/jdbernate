/**************************************************************************************
 *                                                                                    
 *   This program is free software: you can redistribute it and/or modify              
 *   it under the terms of the GNU General Public License as published by             
 *   the Free Software Foundation, either version 3 of the License, or                
 *   (at your option) any later version.                                              
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   See the license terms in <http://www.gnu.org/licenses/>.
 * 
 ************************************************************************************ 
 * 
 *   Este programa é um software livre; você pode redistribuí-lo e/ou 
 *   modificá-lo dentro dos termos da Licença Pública Geral GNU como 
 *   publicada pela Fundação do Software Livre (FSF); na versão 3 da 
 *   Licença, ou (na sua opinião) qualquer versão.
 *
 *   Este programa é distribuído na esperança de que possa ser útil, 
 *   mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO
 *   a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a
 *   Licença Pública Geral GNU para maiores detalhes.
 *
 *   veja os termos da licença <http://www.gnu.org/licenses/>.
 *   
 *   
 *   Developers:
 *   	Marcos Vinicius Soares ( Markinster ) - e-mail: markinster@gmail.com
 *
 *************************************************************************************
 */
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
	private String modelSubdir = "";
	
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
		instance.modelSubdir = properties.getProperty("database.models.subdir").trim();
		
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
	
	public String getModelSubdir() {
		return modelSubdir;
	}
	
	public void setModelSubdir(String modelSubdir) {
		this.modelSubdir = modelSubdir;
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
