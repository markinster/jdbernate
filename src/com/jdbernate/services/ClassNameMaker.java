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

import java.util.ArrayList;
import java.util.List;

public class ClassNameMaker {

	/**
	 * This method return one list with names that classes , builded by list of
	 * strings
	 *
	 * @param tables
	 *            - List of String
	 * @return names that classes
	 */
	public List<String> getClassNames(List<String> tables) {
		List<String> classes = new ArrayList<String>();

		for (String tableName : tables) {

			classes.add(getClassName(tableName));

		}

		return classes;
	}

	public String getClassName(String tableName) {
		String[] tableBroken = tableName.split("_");
		String className = "";
		for (String tablePart : tableBroken) {
			className += tablePart.substring(0, 1).toUpperCase()
					+ tablePart.substring(1).toLowerCase();
		}

		return className.isEmpty() ? null : className;
	}

	public String getAttributeName(String columnName) {
		return getClassName(columnName);
	}
}
