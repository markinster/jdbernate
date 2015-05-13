//resposability this class is create the class for table 
package com.jdbernate.filewriters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.jdbernate.config.JDbernateConfig;
import com.jdbernate.objects.AttributeScheme;
import com.jdbernate.objects.ClassScheme;

public class ClassFileWriter { 
	
	private File file;
	private BufferedWriter w;
	
	public void write(ClassScheme clazz) throws IOException{
		
		JDbernateConfig config = new JDbernateConfig();
		
		file = new File(config.FOLDER +"//" + clazz.getName() + ".java");
	    w = new BufferedWriter(new FileWriter (file));
	    
	    w.write("package " + config.PACKAGE + ";");
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
	    	w.write(String.format("    @DataBaseField(name = \"%s\")\n", at.getTableOriginalName()));
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
