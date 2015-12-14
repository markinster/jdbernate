package com.jdbernate.filewriters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.jdbernate.connection.DataBaseConnector;
import com.jdbernate.objects.ClassScheme;

public class CSharpDaoFW implements IFileWriter{
	private File file;
	private BufferedWriter w;
	private String path;
	private String className;
	private ClassScheme clazz;

	@Override
	public void write(ClassScheme clazz) throws IOException {

		this.clazz = clazz;
		path = DataBaseConnector.getInstance().getFolder() + "//dao";
		file = new File(path);
		file.mkdir();
		className = clazz.getName() + "DAO";

		file = new File(path + "//" + className + ".java");
		w = new BufferedWriter(new FileWriter(file));

		definePackage();
		defineImports();

		// head of class
		w.write("public class " + className + " {");
		w.write("\n");

		defineConstants();

		w.write("\n");

		w.write("}"); // fim da classe
		
		w.write("}"); // fim do namespace

		w.close();
	}

	private void definePackage() throws IOException {
		// write package
		w.write("namespace " + DataBaseConnector.getInstance().getPACKAGE() + ".dao");
		w.write("\n{\n");
		w.write("\n");
	}

	private void defineImports() throws IOException {
		// import
		w.write("import " + DataBaseConnector.getInstance().getPACKAGE() + ".*;");
		w.write("\n");
		w.write("\n");
	}

	
	private void defineConstants() throws IOException {
		// INSERT
		String s = "    private string SQL_INSERT = \"INSERT INTO " + clazz.getTableName();
		
		s += " ( \" " + getFields() + " +\" ) VALUES ( \"" + getParamns() + "+\" );\";";
		w.write(s);
		w.write("\n\n");

		//  UPDATE
		String[] _fields = clazz.getFields().split(",");
		for (int i = 0; i < _fields.length; i++) {
			_fields[i] = _fields[i].replace(" ", ""); 
			_fields[i] = _fields[i] + " = @" + _fields[i];
		}
		
		String fields = "";
		String fecha = "";
		for (String f : _fields){
			fields += fecha;
			fields += "+\"" + f ;
			fecha = ", \" \n ";
		}
		fields += "\" \n ";
			
			
		s = "    private string SQL_UPDATE = \"UPDATE " + clazz.getTableName() + " SET \" \n";
		s += fields;
		s += " +\" WHERE ( CONDITION )  ;\";";
		w.write(s);
		w.write("\n");
		
	}
	
	
	private String getFields() {
		String fields = "\n";
		
		String f = clazz.getFields();
		f = f.replace(" ", "");
		String[] _fields = f.split(",");
		
		String fecha = "";
		for (int i = 0; i < _fields.length; i++) {
			fields += fecha + "+\"" + _fields[i];
			fecha = ", \" \n ";
		}
		fields += "\" \n ";
		
		clazz.getFields();
		
		
		return fields;
	}
	
	
	private String getParamns(){
		String paramns = "\n";
		
		String f = clazz.getFields();
		f = f.replace(" ", "");
		String[] _fields = f.split(",");

		String fecha = "";
		for (int i = 0; i < _fields.length; i++) {
			paramns += fecha + "+\"@" + _fields[i];
			fecha = ", \" \n ";
		}
		paramns += "\" \n ";
		
		clazz.getFields();
		
		
		return paramns;
	}

}
