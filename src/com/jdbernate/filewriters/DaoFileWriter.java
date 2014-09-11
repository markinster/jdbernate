package com.jdbernate.filewriters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.jdbernate.config.JDbernateConfig;
import com.jdbernate.objects.ClassObject;

public class DaoFileWriter {
	private File file;
	private BufferedWriter w;
	
	public void write(ClassObject clazz) throws IOException {
		JDbernateConfig config = new JDbernateConfig();
		
		String path = config.getPathBase() + "//dao";
		
		file = new File(path);
		file.mkdir();
		
		String className = clazz.getName() + "DAO"; 
		
		file = new File(path +"//" + className + ".java");
	    w = new BufferedWriter(new FileWriter (file));
	    
	    // write package
	    w.write("package " + config.getPackageBase() + ".dao;");
	    w.write("\n");
	    w.write("\n");
	    
	    // import
	    w.write("import " + config.getPackageBase() + ".*;");
	    w.write("\n");
	    w.write("\n");
	    
	    // head of class
	    w.write("public class " + className + " {");
	    w.write("\n");
	    
	    //final INSERT	    	    
	    String s = "    private final String INSERT = \"insert into " + clazz.getTableName();
	    s += "("+ clazz.getFields() + ") values ( " + clazz.getParamns() + " );\";";	    
	    w.write(s); 
	    w.write("\n");
	    
	    //final UPDATE	    	    
	    s = "    private final String UPDATE = \"update " + clazz.getTableName();
	    s += " SET "+ clazz.getFields() + ";\";";	    
	    w.write(s); 
	    w.write("\n");
	    
	    //	 
	    w.write("\n");
	    s = "    private " + clazz.getName() + " selectedObject;";    
	    w.write(s); 
	    w.write("\n");   
	    w.write("\n");
	    
	    
	    // METHODS
	    s = "    public void insert(){ ";	    
	    w.write(s); 
	    w.write("\n");
	    
	    w.write("    }"); 
	    w.write("\n");
	    
	    
	    s = "    public void update(){ ";	    
	    w.write(s); 
	    w.write("\n");
	    
	    w.write("    }"); 
	    w.write("\n");
	    
	    
	    s = "    public void delete(){ ";	    
	    w.write(s); 
	    w.write("\n");
	    
	    w.write("    }"); 
	    w.write("\n");
	    
	    
	    w.write("}");
	    
	    w.close();
	} 
}
