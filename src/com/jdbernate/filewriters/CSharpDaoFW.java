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
import com.jdbernate.objects.AttributeScheme;
import com.jdbernate.objects.ClassScheme;
import com.jdbernate.utils.StringUtils;

public class CSharpDaoFW implements IFileWriter{
	private File file;
	private BufferedWriter w;
	private String path;
	private String className;
	private ClassScheme clazz;
	
	private String TAB = "    ";
	
	private String ___newLine = System.getProperty("line.separator"); 

	@Override
	public void write(ClassScheme clazz) throws IOException {

		this.clazz = clazz;
		path = DataBaseConnector.getInstance().getFolder() + "//dao";
		file = new File(path);
		
		//create dirs
		file.mkdir();
		
		//class name
		className = clazz.getName() + "DAO";

		//created class file
		file = new File(path + "//" + className + ".cs");
		w = new BufferedWriter(new FileWriter(file));

		writeUsings();
		writeNamespace();

		// head of class
		w.write(TAB + "public class " + className + ___newLine+ TAB +"{");
		w.write(___newLine);
		
		w.write(TAB+TAB+"private MySqlConnection conn;");
		w.write(___newLine);
		w.write(___newLine);
		
		defineConstants();
		
		w.write(___newLine);
		
		//CONSTRUCTOR
		w.write(TAB + TAB + "public " + className + "(MySqlConnection conn)");
		w.write(___newLine);
		w.write(TAB + TAB +"{");
		w.write(TAB+TAB+TAB+"this.conn = conn; ");
		w.write(___newLine);
		w.write(TAB+TAB+"}");
		w.write(___newLine);
		w.write(___newLine);
		
		
		
		//METHODS
		
		//Insert
		w.write(TAB + TAB + "public string Insert(" +clazz.getName()+ " entity)" );
		w.write(___newLine);
		w.write(TAB+TAB+"{");
		w.write(___newLine);
		w.write(TAB+TAB+TAB+"return Save(entity, SQL_INSERT);");
		w.write(___newLine);
		w.write(TAB+TAB+"}");
		w.write(___newLine);
		w.write(___newLine);
		
		//update
		w.write(TAB + TAB + "public string Update(" +clazz.getName()+ " entity)" );
		w.write(___newLine);
		w.write(TAB+TAB+"{");
		w.write(___newLine);
		w.write(TAB+TAB+TAB+"return Save(entity, SQL_UPDATE);");
		w.write(___newLine);
		w.write(TAB+TAB+"}");
		w.write(___newLine);
		w.write(___newLine);		
		
		//save
		w.write(TAB + TAB + "private string Save(" +clazz.getName()+ " entity, String sql)" + ___newLine );
		w.write(TAB+TAB+"{" + ___newLine);
		w.write(TAB+TAB+TAB+"try" + ___newLine);
		w.write(TAB+TAB+TAB+"{" + ___newLine);
		
		w.write(TAB+TAB+TAB+TAB+"MySqlCommand command = new MySqlCommand(sql, conn);" + ___newLine);
		w.write(TAB+TAB+TAB+TAB+"command = SetParameters(entity, command);" + ___newLine);
		w.write(TAB+TAB+TAB+TAB+"command.ExecuteNonQuery();" + ___newLine);
		w.write(TAB+TAB+TAB+TAB+"return null;" + ___newLine);
		
		w.write(TAB+TAB+TAB+"}" + ___newLine);
		w.write(TAB+TAB+TAB+"catch (Exception ex)" + ___newLine);
		w.write(TAB+TAB+TAB+"{" + ___newLine);
		w.write(TAB+TAB+TAB+TAB+"return ex.ToString();" + ___newLine);
		w.write(TAB+TAB+TAB+"}" + ___newLine);
		w.write(TAB+TAB+"}" + ___newLine);
		w.write(___newLine);
		
		//Set parameters
		w.write(TAB + TAB + "private MySqlCommand SetParameters(" +clazz.getName()+ " entity, MySqlCommand command)" );
		w.write(___newLine);
		w.write(TAB+TAB+"{");
		w.write(___newLine);
		
		for (AttributeScheme at : clazz.getAttributes()){
			String string = TAB+TAB+TAB+"command.Parameters.Add(new MySqlParameter(\"";
			string += at.getTableOriginalName()+"\", entity."+at.getName()+"));"+ ___newLine;
			w.write(string);
		}
		
		
		w.write(TAB+TAB+TAB+"return command;");
		w.write(___newLine);
		w.write(TAB+TAB+"}");
		w.write(___newLine);
		w.write(___newLine);
		
		//------------------------
		
		
		w.write(TAB+"}"); // end of classs
		w.write(___newLine);
		w.write("}"); // end of namespace

		w.close();
	}

	private void writeNamespace() throws IOException {
		// write package
		w.write("namespace " + DataBaseConnector.getInstance().getPACKAGE() + ".daos");
		w.write(___newLine);
		w.write("{");
		w.write(___newLine);
	}

	private void writeUsings() throws IOException {
		
		String pkt = DataBaseConnector.getInstance().getPACKAGE();
		
		String models = pkt + ".models";
		if (!StringUtils.isEmpty(DataBaseConnector.getInstance().getModelSubdir()))
			models += "." + DataBaseConnector.getInstance().getModelSubdir();
		
		models += ";";
		
	    w.write("using " + pkt + ".utils;");
	    w.write(___newLine);
	    w.write("using " + models);
	    w.write(___newLine);
	    w.write("using MySql.Data.MySqlClient;");
	    w.write(___newLine);
	    w.write("using System;");
	    w.write(___newLine);
	    w.write("using System.Collections.Generic;");
	    w.write(___newLine);
	    w.write("using System.Linq;");
	    w.write(___newLine);
	    w.write("using System.Text;");
	    w.write(___newLine);
	    w.write("using System.Threading.Tasks;");	
	    w.write(___newLine);	   	    		
	    w.write(___newLine);
	}

	
	private void defineConstants() throws IOException {
		// INSERT
		String s = TAB+TAB+"private string SQL_INSERT = \"INSERT INTO " + clazz.getTableName();		
		s += " ( \" " + getFields()  + TAB+TAB+ " +\" ) VALUES ( \"" + getParamns()  + TAB+TAB+ "+\" );\";";
		w.write(s);
		w.write(___newLine);
		w.write(___newLine);

		//  UPDATE
		String[] _fields = clazz.getFields().split(",");
		for (int i = 0; i < _fields.length; i++) {
			_fields[i] = _fields[i].replace(" ", ""); //Trim
			_fields[i] = _fields[i] + " = @" + _fields[i];
		}
		
		String fields = "";
		String fecha = "";
		for (String f : _fields){
			fields += fecha + TAB+TAB+TAB+ "+\"" + f ;
			fecha = ", \"  " + ___newLine;
		}
		fields += "\" " + ___newLine;
		
		String CONDITION = "";
		String AND = "";
		for (String pk : clazz.getPrivareKeys()) {
			CONDITION += AND + pk + " = @" + pk;
			AND = " and ";
		}
			
		s = TAB+TAB+"private string SQL_UPDATE = \"UPDATE " + clazz.getTableName() + " SET \" " + ___newLine;
		s += fields;
		s +=  TAB+TAB+" +\" WHERE ( " + CONDITION + " )  ;\";";
		w.write(s);
		w.write(___newLine);
		
	}
	
	
	private String getFields() {
		String fields = ___newLine;
		
		String f = clazz.getFields();
		f = f.replace(" ", "");
		String[] _fields = f.split(",");
		
		String fecha = "";
		for (int i = 0; i < _fields.length; i++) {
			fields += fecha + TAB+TAB+TAB+ "+\"" + _fields[i];
			fecha = ", \" " + ___newLine;
		}
		fields += "\" " + ___newLine;
		
		clazz.getFields();
		
		
		return fields;
	}
	
	
	private String getParamns(){
		String paramns = ___newLine;
		
		String f = clazz.getFields();
		f = f.replace(" ", "");
		String[] _fields = f.split(",");

		String fecha = "";
		for (int i = 0; i < _fields.length; i++) {
			paramns += fecha  + TAB+TAB+TAB+ "+\"@" + _fields[i];
			fecha = ", \" " + ___newLine;
		}
		paramns += "\" " + ___newLine;
		
		clazz.getFields();
		
		
		return paramns;
	}

}
