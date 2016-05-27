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

public class JavaClassFW implements IFileWriter { 
	
	private File file;
	private BufferedWriter w;
	
	@Override
	public void write(ClassScheme clazz) throws IOException{
		
		file = new File(DataBaseConnector.getInstance().getFolder() +"//" + clazz.getName() + ".java");
	    w = new BufferedWriter(new FileWriter (file));
	    
	    w.write("package " + DataBaseConnector.getInstance().getPACKAGE() + ";");
	    w.write("\n");
	    w.write("\n");
	    
	    w.write("import com.jdbernate.annotations.*;\n");
	    
	    //verify a need to imports
	    for (AttributeScheme at : clazz.getAttributes()){
	    	if ("bigdecimal".equals(at.getType().toLowerCase())){
	    		w.write("import java.math.BigDecimal;");
	    		w.write("\n");
	    	} else if  ("calendar".equals(at.getType().toLowerCase())){
	    		w.write("import java.util.Calendar;");
	    		w.write("\n");
	    	}
	    }
	    w.write("\n");
	    
	    w.write("public class " + clazz.getName() + " { ");
	    w.write("\n");
	    for (AttributeScheme at : clazz.getAttributes()){
	    	w.write(String.format("    @Column(name = \"%s\")\n", at.getTableOriginalName()));
	    	w.write("    private " + at.getType() + " " + at.getName() + ";");
	    	w.write("\n\n");
	    }
	    
	    w.write("\n");
	    for (AttributeScheme at : clazz.getAttributes()){
	    	w.write("    // " + at.getName() + ": Set and Get \n");
	    	writeGetMethod(at);
	    	writeSetMethod(at);
	    }
	    w.write(" } ");
	    
	    w.close();
	}
	
	//create the method GET for attribute
	private void writeGetMethod(AttributeScheme at) throws IOException{
		String string = "    public " + at.getType() + " get"
		+ at.getName().substring(0,1).toUpperCase() + at.getName().substring(1)
		+ "() {";
		
		w.write(string);
		w.write("\n");
		
		string = "        return " + at.getName() + ";";
		
		w.write(string);
		w.write("\n");
		
		w.write("    }");
		w.write("\n");
		w.write("\n");		
	}
	
	//create the method SET for attribute
	private void writeSetMethod(AttributeScheme at) throws IOException{
		String string = "    public void set"
		+ at.getName().substring(0,1).toUpperCase() + at.getName().substring(1)
		+ "("+ at.getType() +" newValue ) {";
		
		w.write(string);
		w.write("\n");		
		
		string = "        this." + at.getName() + " = newValue;";
		
		w.write(string);
		w.write("\n");
		
		w.write("    }");
		w.write("\n");
		w.write("\n");		
	}

}
