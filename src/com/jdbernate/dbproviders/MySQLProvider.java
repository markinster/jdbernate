package com.jdbernate.dbproviders;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbernate.conector.DataBaseConector;
import com.jdbernate.objects.Column;

public class MySQLProvider implements IDBProvider {

	@Override
	public List<String> getTables() throws IOException {
		List<String> tables = new ArrayList<String>();

		Connection con;
		try {
			con = DataBaseConector.getInstance().getConnection();

			String sql = "show tables";
			PreparedStatement prepareStatement = con.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();

			while (rs.next()) {
				tables.add(rs.getString(1));
			}

			return tables;

		} catch (SQLException e) {
			return null;
		}

	}

	@Override
	public List<Column> getFields(String tableName) throws IOException {
		Connection con;
		try {
			con = DataBaseConector.getInstance().getConnection();

			List<Column> fields = new ArrayList<Column>();

			// fint all fields from table
			String sql = "show fields from " + tableName + " ; ";

			ResultSet rs = con.prepareStatement(sql).executeQuery();

			while (rs.next()) {
				Column field = new Column();
				field.setName(rs.getString("Field"));
				field.setType(rs.getString("Type"));

				fields.add(field);
			}
			
			return fields;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

}
