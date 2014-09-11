package com.jdbernate.filewriters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.jdbernate.objects.ClassObject;
import com.jdbernate.objects.JAttribute;

public class ClassFileWriter {
	File file;
	private BufferedWriter w;
	
	public void write(ClassObject clazz) throws IOException{
		file = new File(clazz.getName() + ".java");
	    w = new BufferedWriter(new FileWriter (file));
	    
	    w.write("public class " + clazz.getName() + " { ");
	    w.write("\n");
	    for (JAttribute at : clazz.getAttributes()){
	    	w.write("    private " + at.getType() + " " + at.getName() + ";");
	    	w.write("\n");
	    }
	    
	    w.write("\n");
	    for (JAttribute at : clazz.getAttributes()){
	    	w.write("\n");
	    	w.write("    // " + at.getName() + ": Set and Get \n");
	    	writeGetMethod(at);
	    	writeSetMethod(at);
	    }
	    w.write(" } ");
	    
	    w.close();
	}
	
	private void writeGetMethod(JAttribute at) throws IOException{
		String _get = "    public " + at.getType() + " get"
		+ at.getName().substring(0,1).toUpperCase() + at.getName().substring(1)
		+ "() {";
		
		w.write(_get);
		w.write("\n");
		
		_get = "        return " + at.getName() + ";";
		
		w.write(_get);
		w.write("\n");
		
		w.write("    }");
		w.write("\n");
		w.write("\n");		
	}
	
	private void writeSetMethod(JAttribute at) throws IOException{
		String _set = "    public void set"
		+ at.getName().substring(0,1).toUpperCase() + at.getName().substring(1)
		+ "("+ at.getType() +" newValue ) {";
		
		w.write(_set);
		w.write("\n");		
		
		_set = "        this." + at.getName() + " = newValue;";
		
		w.write(_set);
		w.write("\n");
		
		w.write("    }");
		w.write("\n");
		w.write("\n");		
	}

}
