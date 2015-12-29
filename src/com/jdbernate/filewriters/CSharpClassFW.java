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
	private BufferedWriter w;
	
	//usado para fazer espaçamentos
	private String TAB = "    ";
	
	@Override
	public void write(ClassScheme clazz) throws IOException{
		
		//cria o arquivo da classe
		file = new File(DataBaseConnector.getInstance().getFolder() +"//" + clazz.getName() + ".java");
	    w = new BufferedWriter(new FileWriter (file));
	    
	    //grava os Usings
	    writeUsings();
	    
	    //grava o namespace
	    writeNamespace();
	    
	    w.write("\n");
	    
	    //public class NomeDaClasse
	    w.write(TAB+"public class " + clazz.getName() + " { ");
	    w.write("\n\n");
	    
	    //grava todos os atributos da classe
	    for (AttributeScheme at : clazz.getAttributes()){
	    	//public tipo atributo { get; set; }
	    	w.write(TAB+TAB+"public " + at.getType() + " " + at.getName() + " { get; set; }");
	    	w.write("\n");
	    }
	    
	    w.write("\n");
	    w.write(TAB+"} ");
	    w.write(" } ");
	    
	    w.close();
	}
	
	private void writeNamespace() throws IOException {
		// write package
		w.write("namespace " + DataBaseConnector.getInstance().getPACKAGE());
		w.write("\n{");
		w.write("\n");
	}
	
	
	private void writeUsings() throws IOException {
		// import
		//w.write("using " + DataBaseConnector.getInstance().getPACKAGE() + ".*;");
		//w.write("\n");
		//w.write("\n");
	}


}
