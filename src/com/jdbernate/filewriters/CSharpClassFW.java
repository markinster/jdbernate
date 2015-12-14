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
	
	@Override
	public void write(ClassScheme clazz) throws IOException{
		
		file = new File(DataBaseConnector.getInstance().getFolder() +"//" + clazz.getName() + ".java");
	    w = new BufferedWriter(new FileWriter (file));
	    
	    w.write("package " + DataBaseConnector.getInstance().getPACKAGE() + ";");
	    w.write("\n");
	    w.write("\n");   

	    
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
	    w.write("\n\n");
	    for (AttributeScheme at : clazz.getAttributes()){
	    	String typp = at.getType();
	    	typp = typp.equals("String") ? "string" : typp;
	    	w.write("    public " + typp + " " + at.getName() + " { get; set; }");
	    	w.write("\n");
	    }
	    
	    w.write("\n");
	    w.write(" } ");
	    
	    w.close();
	}
	


}
