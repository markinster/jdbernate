package com.jdbernate.dbproviders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbernate.connection.DataBaseConnector;
import com.jdbernate.objects.Column;

public class MySQLProvider implements IDBProvider {

	@Override
	public List<String> getTables() {
		List<String> tables = new ArrayList<String>();

		Connection con;
		try {
			con = DataBaseConnector.getInstance().getConnection();

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
	public List<Column> getFields(String tableName) {
		Connection con;
		try {
			con = DataBaseConnector.getInstance().getConnection();

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

	@Override
	public List<String> getPrimaryKeys(String tableName) {

		List<String> asReturn = new ArrayList<>();

		String schema = DataBaseConnector.getInstance().getDataBaseName();

		String sql = "SELECT information_schema.KEY_COLUMN_USAGE.COLUMN_NAME as COLUMN_NAME "
				+ "FROM information_schema.KEY_COLUMN_USAGE "
				+ "WHERE information_schema.KEY_COLUMN_USAGE.CONSTRAINT_NAME LIKE \"PRIMARY\" AND "
				+ "information_schema.KEY_COLUMN_USAGE.TABLE_SCHEMA LIKE '" + schema + "' AND "
				+ "information_schema.KEY_COLUMN_USAGE.TABLE_NAME LIKE '" + tableName +"'";

		Connection con;

		try {
			con = DataBaseConnector.getInstance().getConnection();

			PreparedStatement prepareStatement = con.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();

			while (rs.next())
				asReturn.add(rs.getString("COLUMN_NAME"));
			

		} catch (Exception e) {
			return null;
		}

		return asReturn;
	}

}
