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
package com.jdbernate.filewriters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.jdbernate.connection.DataBaseConnector;
import com.jdbernate.objects.ClassScheme;

public class JavaDaoFW implements IFileWriter{
	private File file;
	private BufferedWriter w;
	private String path;
	private String className;
	private ClassScheme clazz;

	@Override
	public void write(ClassScheme clazz) throws IOException {

		this.clazz = clazz;
		path = DataBaseConnector.getInstance().getFolder() + "//dao";
		file = new File(path);
		file.mkdir();
		className = clazz.getName() + "DAO";

		file = new File(path + "//" + className + ".java");
		w = new BufferedWriter(new FileWriter(file));

		definePackage();
		defineImports();

		// head of class
		w.write("public class " + className + " {");
		w.write("\n");

		defineConstants();

		defineAttributes();
		w.write("\n");
		defineMehods();
		w.write("\n");

		w.write("}");

		w.close();
	}

	private void definePackage() throws IOException {
		// write package
		w.write("package " + DataBaseConnector.getInstance().getPACKAGE() + ".dao;");
		w.write("\n");
		w.write("\n");
	}

	private void defineImports() throws IOException {
		// import
		w.write("import " + DataBaseConnector.getInstance().getPACKAGE() + ".*;");
		w.write("\n");
		w.write("\n");
	}

	private void defineConstants() throws IOException {
		// final INSERT
		String s = "    private final String SQL_INSERT = \"INSERT INTO "
				+ clazz.getTableName();
		s += " (" + clazz.getFields() + " ) VALUES ( " + clazz.getParamns()
				+ " );\";";
		w.write(s);
		w.write("\n");

		// final UPDATE
		String[] _fields = clazz.getFields().split(",");
		for (int i = 0; i < _fields.length; i++)
			_fields[i] = _fields[i] + " = ?";
		
		String fields = "";
		String comma = "";
		for (String f : _fields){
			fields += comma + f;
			comma = ",";
		}
			
			
		s = "    private final String SQL_UPDATE = \"UPDATE "
				+ clazz.getTableName();
		s += " SET " + fields + " WHERE ( CONDITION )  ;\";";
		w.write(s);
		w.write("\n");
		
		
		// final DELETE
		s = "    private final String SQL_DELETE = \"DELETE FROM "
				+ clazz.getTableName();
		s += " WHERE ( CONDITION ) ;\";";

		w.write(s);
		w.write("\n");
	}

	private void defineAttributes() throws IOException {
		String s;
		//
		w.write("\n");
		s = "    private " + clazz.getName() + " itemSelected;";
		w.write(s);
		w.write("\n");
	}

	private void defineMehods() throws IOException {
		String s;
		// METHODS
		s = "    public void insert() { ";
		w.write(s);
		w.write("\n");

		w.write("    }");
		w.write("\n");
		w.write("\n");

		s = "    public void update() { ";
		w.write(s);
		w.write("\n");

		w.write("    }");
		w.write("\n");
		w.write("\n");

		s = "    public void delete() { ";
		w.write(s);
		w.write("\n");

		w.write("    }");
		w.write("\n");
		w.write("\n");
		
		s = "    public " + clazz.getName() + " getItemSelected() { ";
		w.write(s);
		w.write("\n");
		s = "        return itemSelected;";
		w.write(s);
		w.write("\n");
		
		w.write("    }");
		w.write("\n");
		w.write("\n");
		
		s = "    public void setItemSelected("+ clazz.getName() + " newValue) { ";
		w.write(s);
		w.write("\n");
		s = "        this.itemSelected = newValue;";
		w.write(s);
		w.write("\n");
		
		w.write("    }");
		w.write("\n");
	}

}
