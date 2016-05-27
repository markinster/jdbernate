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
