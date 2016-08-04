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
//resposability this class is create the class for table 
package com.jdbernate.filewriters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.jdbernate.connection.DataBaseConnector;
import com.jdbernate.objects.AttributeScheme;
import com.jdbernate.objects.ClassScheme;
import com.jdbernate.utils.StringUtils;


public class CSharpClassFW implements IFileWriter { 
	
	private File file;
	private BufferedWriter bw;
	
	//usado para fazer espaçamentos
	private String TAB = "    ";
	
	private String ___newLine = System.getProperty("line.separator"); 
	
	@Override
	public void write(ClassScheme clazz) throws IOException{
		
		//cria o arquivo da classe
		
		String modelpackage = StringUtils.isEmpty(DataBaseConnector.getInstance().getModelSubdir()) ? "" :
				"//" + DataBaseConnector.getInstance().getModelSubdir();
		
		String path = DataBaseConnector.getInstance().getFolder() + "//models" + modelpackage;		
		file = new File(path);
		
		//create dirs
		file.mkdirs();
		
		file = new File(path + "//" + clazz.getName() +   ".cs");
	    bw = new BufferedWriter(new FileWriter (file));
	    
	    //grava os Usings
	    writeUsings();
	    
	    //grava o namespace
	    writeNamespace();
	    
	    bw.write(___newLine);
	    
	    //public class NomeDaClasse
	    bw.write(TAB+"public class " + clazz.getName() + " { ");
	    bw.write(___newLine);
	    bw.write(___newLine);
	    
	    
	    //construtor padrao
	    bw.write(TAB+TAB+"public " + clazz.getName() + "()" + ___newLine);
	    bw.write(TAB+TAB+"{"+___newLine);
	    bw.write(TAB+TAB+"}");
	    bw.write(___newLine);
	    bw.write(___newLine);
	    
	    //construtor com MysqlReader
	    bw.write(TAB+TAB+"public " + clazz.getName() + "(MySqlDataReader reader)"+___newLine);
	    bw.write(TAB+TAB+"{"+___newLine);
	    
	    for (AttributeScheme at : clazz.getAttributes()){
	    	
	    	String string = TAB+TAB+TAB+ at.getName() + " = ";
	    	
	    	String type = at.getType().toLowerCase();
	    	if (!type.equals("string")) {
	    		string += "JdbConvert.";
	    		
	    		if (type.equals("double"))
	    			string += "ToDouble";
	    		else if (type.equals("int"))
	    			string += "ToInt32";
	    		else
	    			string += "ToDouble";
	    		
	    		string += "(";	    		
	    	}
	    	
	    	string += "reader[\"" + at.getTableOriginalName() + "\"].ToString()";
	    	
	    	if (!type.equals("string")) {
	    		string += ")";
	    	}
	    	
	    	string += ";";
	    	
	    	bw.write(string);
	    	
	    	bw.write(___newLine);
	    }
	    bw.write(TAB+TAB+"}");
	    bw.write(___newLine);
	    bw.write(___newLine);
	    bw.write(___newLine);
	    
	    
	    //grava todos os atributos da classe
	    for (AttributeScheme at : clazz.getAttributes()){
	    	//public tipo atributo { get; set; }
	    	bw.write(TAB+TAB+"public " + at.getType() + " " + at.getName() + " { get; set; }");
	    	bw.write(___newLine);
	    }
	    
	    bw.write(___newLine);
	    bw.write(TAB+"} ");
	    bw.write(___newLine+"} ");
	    
	    bw.close();
	}
	
	private void writeNamespace() throws IOException {
		// write package
		String pkt = DataBaseConnector.getInstance().getPACKAGE();

		String models = pkt + ".models";
		if (!StringUtils.isEmpty(DataBaseConnector.getInstance().getModelSubdir()))
			models += "." + DataBaseConnector.getInstance().getModelSubdir();
		models += ";";
		
		bw.write("namespace " + models);
		
		bw.write(___newLine+"{");
		bw.write(___newLine);
	}
	
	
	private void writeUsings() throws IOException {
	    
	    bw.write("using DataBaseLib.utils;");
	    bw.write(___newLine);
	    bw.write("using MySql.Data.MySqlClient;");
	    bw.write(___newLine);
	    bw.write("using System;");
	    bw.write(___newLine);
	    bw.write("using System.Collections.Generic;");
	    bw.write(___newLine);
	    bw.write("using System.Linq;");
	    bw.write(___newLine);
	    bw.write("using System.Text;");
	    bw.write(___newLine);
	    bw.write("using System.Threading.Tasks;");	
	    bw.write(___newLine);
	    bw.write(___newLine);
	}


}
