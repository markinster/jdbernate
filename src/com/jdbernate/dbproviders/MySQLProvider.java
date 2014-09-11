package com.jdbernate.dbproviders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbernate.dao.DataBaseConector;
import com.jdbernate.objects.TableField;

public class MySQLProvider implements IDBProvider {

	@Override
	public List<String> getTables() {
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
	public List<TableField> getFields(String tableName) {
		Connection con;
		try {
			con = DataBaseConector.getInstance().getConnection();

			List<TableField> fields = new ArrayList<TableField>();

			// fint all fields from table
			String sql = "show fields from " + tableName + " ; ";

			ResultSet rs = con.prepareStatement(sql).executeQuery();

			while (rs.next()) {
				TableField field = new TableField();
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
