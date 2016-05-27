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
package com.jdbernate.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbernate.dbproviders.DBProviderBuilder;
import com.jdbernate.dbproviders.IDBProvider;
import com.jdbernate.objects.AttributeScheme;
import com.jdbernate.objects.ClassScheme;
import com.jdbernate.objects.Column;

/**
 * 
 * @author marcos
 *
 */
public class ClassMaker {

	public ClassScheme builder(String table) throws SQLException {

		IDBProvider dbProvider = DBProviderBuilder.getDBProvider();

		ClassScheme clazz = new ClassScheme();
		clazz.setTableName(table);

		// build class Name from table Name
		String className = new ClassNameMaker().getClassName(table);
		clazz.setName(className);

		// build a list of fields
		List<Column> fields = dbProvider.getFields(table);
		
		List<AttributeScheme> attributes = new ArrayList<AttributeScheme>();
		for (Column field : fields)
			attributes.add(new ClassColumnMaker().getClassColumn(field));

		clazz.setAttributes(attributes);
		
		List<String> primaryKeys = dbProvider.getPrimaryKeys(table);
		clazz.setPrivareKeys(primaryKeys == null ? new ArrayList<String>() : primaryKeys);
		
		return clazz;
	}

}
