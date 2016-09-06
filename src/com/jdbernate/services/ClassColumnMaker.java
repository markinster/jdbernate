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

import com.jdbernate.objects.AttributeScheme;
import com.jdbernate.objects.Column;
import com.jdbernate.typemakers.TypeMakerHelper;

public class ClassColumnMaker {

	//created a attribute from table fields	
	public AttributeScheme getClassColumn(Column tableColumn) {
		AttributeScheme cCol = new AttributeScheme();
		String name = new ClassNameMaker().getAttributeName(tableColumn.getName());		
		
		name = name.substring(0, 1).toLowerCase() + name.substring(1);
		
		cCol.setName(name);
		cCol.setType(TypeMakerHelper.getTypeMaker().getType(tableColumn.getType()));
		cCol.setDbFieldName(tableColumn.getName());
		cCol.setTableOriginalName(tableColumn.getName());

		return cCol;
	}
}
