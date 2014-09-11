package com.jdbernate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbernate.dao.DataBaseConector;
import com.jdbernate.dbproviders.DBProviderBuilder;
import com.jdbernate.filewriters.ClassFileWriter;
import com.jdbernate.services.ClassMaker;
import com.jdbernate.typemakers.IJavaTypeMaker;
import com.jdbernate.typemakers.JavaTypeMakerMySQL;

public class JDbernate {

	// this TypeMaker is instanced in constructor of class
	public static IJavaTypeMaker javaTypeMaker;

	// list of tables from data base
	private List<String> tables = new ArrayList<String>();

	// constructor
	public JDbernate() {
		if (DataBaseConector.getInstance().getSgbd() == DataBaseConector.SGBD_MYSQL) {
			javaTypeMaker = new JavaTypeMakerMySQL();
		}
	}

	/*
	 * this process receive a connection and will generate the JDBernate
	 * architecture
	 */
	public void process() throws SQLException, IOException {
		
		if (javaTypeMaker == null){
			if (DataBaseConector.getInstance().getSgbd() == DataBaseConector.SGBD_MYSQL) {
				javaTypeMaker = new JavaTypeMakerMySQL();
			}
		}
		
		tables = DBProviderBuilder.getDBProvider().getTables();

		ClassMaker classMaker = new ClassMaker();
		for (String table : tables) {
			new ClassFileWriter().write(classMaker.builder(table));
		}
	}
}
