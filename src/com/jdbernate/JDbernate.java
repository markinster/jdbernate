package com.jdbernate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbernate.connection.DataBaseConnector;
import com.jdbernate.dbproviders.DBProviderBuilder;
import com.jdbernate.filewriters.CSharpClassFW;
import com.jdbernate.filewriters.CSharpDaoFW;
import com.jdbernate.filewriters.IFileWriter;
import com.jdbernate.filewriters.JavaClassFW;
import com.jdbernate.filewriters.JavaDaoFW;
import com.jdbernate.services.ClassMaker;
import com.jdbernate.typemakers.ITypeMaker;
import com.jdbernate.typemakers.JavaTypeMakerMySQL;
import com.jdbernate.typemakers.TypeMakerHelper;

public class JDbernate {
	
	// this TypeMaker is instanced in constructor of class
	public static ITypeMaker typeMaker;

	// list of tables from data base
	private List<String> tables = new ArrayList<String>();

	// constructor
	public JDbernate() throws IOException {
		if (DataBaseConnector.getInstance().getSgbd().equals(DataBaseConnector.SGBD_MYSQL)) {
			typeMaker = new JavaTypeMakerMySQL();
		}
	}	

	/*
	 * this process receive a connection and will generate the JDBernate
	 * architecture
	 */
	public void execute() throws SQLException, IOException {
		
		if (typeMaker == null)
			typeMaker = TypeMakerHelper.getTypeMaker();
				
		tables = DBProviderBuilder.getDBProvider().getTables();

		IFileWriter classFW;
		IFileWriter daoFW;	
		
		//idefine program language
		if (DataBaseConnector.getInstance().isCSharp()) {
			classFW = new CSharpClassFW();
			daoFW = new CSharpDaoFW();	
		} else {
			classFW = new JavaClassFW();
			daoFW = new JavaDaoFW();
		}
		
		ClassMaker classMaker = new ClassMaker();
		
		System.out.println("\n[INFO] Processing \n");
		
		for (String table : tables) {
			//create entity
			classFW.write(classMaker.builder(table));		
			
			System.out.print(".");	
			
			//create DAO
			daoFW.write(classMaker.builder(table));
		}
		
		System.out.print(" \\o/");
		System.out.println("\n\n[INFO] Process finished ");
	}
}
