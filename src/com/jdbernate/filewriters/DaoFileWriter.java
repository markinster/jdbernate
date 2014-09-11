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
		String path = new JDbernateConfig().getPathBase() + "//dao";
		file = new File(path);
		file.mkdir();
		
		file = new File(path +"//" + clazz.getName() + "DAO.java");
	    w = new BufferedWriter(new FileWriter (file));
	    w.close();
	} 
}
