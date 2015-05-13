package com.jdbernate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbernate.connection.DataBaseConnector;
import com.jdbernate.dbproviders.DBProviderBuilder;
import com.jdbernate.filewriters.ClassFileWriter;
import com.jdbernate.filewriters.DaoFileWriter;
import com.jdbernate.services.ClassMaker;
import com.jdbernate.typemakers.IJavaTypeMaker;
import com.jdbernate.typemakers.JavaTypeMakerMySQL;

public class JDbernate {
	
	// this TypeMaker is instanced in constructor of class
	public static IJavaTypeMaker javaTypeMaker;

	// list of tables from data base
	private List<String> tables = new ArrayList<String>();

	// constructor
	public JDbernate() throws IOException {
		if (DataBaseConnector.getInstance().getSgbd().equals(DataBaseConnector.SGBD_MYSQL)) {
			javaTypeMaker = new JavaTypeMakerMySQL();
		}
	}	

	/*
	 * this process receive a connection and will generate the JDBernate
	 * architecture
	 */
	public void execute() throws SQLException, IOException {
		
		if (javaTypeMaker == null){
			if (DataBaseConnector.getInstance().getSgbd().equals(DataBaseConnector.SGBD_MYSQL)) {
				javaTypeMaker = new JavaTypeMakerMySQL();
			}
		}
		
		tables = DBProviderBuilder.getDBProvider().getTables();

		ClassMaker classMaker = new ClassMaker();
		System.out.println("\n[INFO] Processing \n");
		for (String table : tables) {
			new ClassFileWriter().write(classMaker.builder(table));
			System.out.print(".");
			new DaoFileWriter().write(classMaker.builder(table));
		}
		System.out.print(" \\o/");
		System.out.println("\n\n[INFO] Process finished ");
	}
}
