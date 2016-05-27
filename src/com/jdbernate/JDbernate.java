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
package com.jdbernate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbernate.connection.DataBaseConnector;
import com.jdbernate.dbproviders.DBProviderBuilder;
import com.jdbernate.filewriters.CSUtilsFW;
import com.jdbernate.filewriters.CSharpClassFW;
import com.jdbernate.filewriters.CSharpDaoFW;
import com.jdbernate.filewriters.IFileWriter;
import com.jdbernate.filewriters.JavaClassFW;
import com.jdbernate.filewriters.JavaDaoFW;
import com.jdbernate.objects.ClassScheme;
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

		IFileWriter classFW = null;
		IFileWriter daoFW = null;
		IFileWriter utilFW = null;

		// idefine program language
		if (DataBaseConnector.getInstance().isCSharp()) {
			classFW = new CSharpClassFW();
			daoFW = new CSharpDaoFW();
			utilFW = new CSUtilsFW();
		} else {
			classFW = new JavaClassFW();
			daoFW = new JavaDaoFW();
		}

		ClassMaker classMaker = new ClassMaker();

		System.out.println("\n[INFO] Processing \n");

		for (String table : tables) {
			ClassScheme clazz = classMaker.builder(table);

			// create utils
			if (utilFW != null)
				utilFW.write(clazz);

			// create entity
			classFW.write(clazz);

			System.out.print(".");

			// create DAO
			daoFW.write(clazz);

		}

		System.out.print(" \\o/");
		System.out.println("\n\n[INFO] Process finished ");
	}
}
