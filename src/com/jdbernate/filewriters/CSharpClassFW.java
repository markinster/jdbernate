//resposability this class is create the class for table 
package com.jdbernate.filewriters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.jdbernate.connection.DataBaseConnector;
import com.jdbernate.objects.AttributeScheme;
import com.jdbernate.objects.ClassScheme;

public class CSharpClassFW implements IFileWriter { 
	
	private File file;
	private BufferedWriter bw;
	
	//usado para fazer espaçamentos
	private String TAB = "    ";
	
	@Override
	public void write(ClassScheme clazz) throws IOException{
		
		//cria o arquivo da classe
		file = new File(DataBaseConnector.getInstance().getFolder() +"//" + clazz.getName() + ".cs");
	    bw = new BufferedWriter(new FileWriter (file));
	    
	    //grava os Usings
	    writeUsings();
	    
	    //grava o namespace
	    writeNamespace();
	    
	    bw.write("\n");
	    
	    bw.write("using DataBaseLib.utils;");
	    bw.write("\n");
	    bw.write("using MySql.Data.MySqlClient;");
	    bw.write("using System;");
	    bw.write("\n");
	    bw.write("using System.Collections.Generic;");
	    bw.write("\n");
	    bw.write("using System.Linq;");
	    bw.write("\n");
	    bw.write("using System.Text;");
	    bw.write("\n");
	    bw.write("using System.Threading.Tasks;");	
	    bw.write("\n");
	    
	    //public class NomeDaClasse
	    bw.write(TAB+"public class " + clazz.getName() + " { ");
	    bw.write("\n\n");
	    
	    
	    //construtor padrao
	    bw.write(TAB+TAB+"public " + clazz.getName() + "()\n");
	    bw.write(TAB+TAB+"{\n");
	    bw.write(TAB+TAB+"}");
	    bw.write("\n\n");
	    
	    //construtor com MysqlReader
	    bw.write(TAB+TAB+"public " + clazz.getName() + "(MySqlDataReader reader)\n");
	    bw.write(TAB+TAB+"{\n");
	    
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
	    	
	    	bw.write("\n");
	    }
	    bw.write(TAB+TAB+"}\n");
	    bw.write("\n\n");
	    
	    
	    //grava todos os atributos da classe
	    for (AttributeScheme at : clazz.getAttributes()){
	    	//public tipo atributo { get; set; }
	    	bw.write(TAB+TAB+"public " + at.getType() + " " + at.getName() + " { get; set; }");
	    	bw.write("\n");
	    }
	    
	    bw.write("\n");
	    bw.write(TAB+"} ");
	    bw.write("\n} ");
	    
	    bw.close();
	}
	
	private void writeNamespace() throws IOException {
		// write package
		bw.write("namespace " + DataBaseConnector.getInstance().getPACKAGE());
		bw.write("\n{");
		bw.write("\n");
	}
	
	
	private void writeUsings() throws IOException {
		// import
		//w.write("using " + DataBaseConnector.getInstance().getPACKAGE() + ".*;");
		//w.write("\n");
		//w.write("\n");
	}


}
