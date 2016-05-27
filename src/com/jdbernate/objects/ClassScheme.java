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
package com.jdbernate.objects;

import java.util.ArrayList;
import java.util.List;

public class ClassScheme {

	private String name;
	private String tableName;
	private List<AttributeScheme> attributes = new ArrayList<AttributeScheme>();
	private List<String> privareKeys = new ArrayList<>();

	private String fields = "";
	private String paramns = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<AttributeScheme> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeScheme> columns) {
		this.attributes = columns;
	}

	public List<String> getPrivareKeys() {
		return privareKeys;
	}

	public void setPrivareKeys(List<String> privareKeys) {
		this.privareKeys = privareKeys;
	}

	public String getFields() {
		if (fields.isEmpty()) {
			String comma = "";
			for (AttributeScheme at : attributes) {
				fields += comma + at.getDbFieldName();
				paramns += comma + "?";
				comma = ", ";
			}
		}
		return fields;
	}

	public String getParamns() {
		if (paramns.isEmpty())
			getFields();

		return paramns;
	}

	@Override
	public String toString() {
		String retorno = name + "\n";
		for (AttributeScheme t : attributes)
			retorno += t.getName() + " " + t.getType() + "\n";

		return retorno;
	}

}
