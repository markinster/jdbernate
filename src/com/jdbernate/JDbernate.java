package com.jdbernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbernate.dao.DataBaseConector;
import com.jdbernate.services.ClassMaker;
import com.jdbernate.typemakers.ITypeMaker;
import com.jdbernate.typemakers.TypeMakerMySQL;

public class JDbernate {

	// this TypeMaker is instanced in constructor of class
	public static ITypeMaker typeMaker;

	// list of tables from data base
	private List<String> tables = new ArrayList<String>();

	// constructor
	public JDbernate() {
		if (DataBaseConector.getInstance().getSgbd() == DataBaseConector.SGBD_MYSQL) {
			typeMaker = new TypeMakerMySQL();
		}
	}

	/*
	 * this process receive a connection and will generate the JDBernate
	 * architecture
	 */
	public void process(Connection con) throws SQLException {

		String sql = "show tables";

		PreparedStatement prepareStatement = con.prepareStatement(sql);
		ResultSet rs = prepareStatement.executeQuery();

		while (rs.next()) {
			tables.add(rs.getString(1));
		}

		ClassMaker classBuilder = new ClassMaker();
		for (String table : tables) {
			classBuilder.builder(table, con);
		}
	}
}
