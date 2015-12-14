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

		defineAttributes();
		w.write("\n");
		defineMehods();
		w.write("\n");

		w.write("}");

		w.close();
	}

	private void definePackage() throws IOException {
		// write package
		w.write("package " + DataBaseConnector.getInstance().getPACKAGE() + ".dao;");
		w.write("\n");
		w.write("\n");
	}

	private void defineImports() throws IOException {
		// import
		w.write("import " + DataBaseConnector.getInstance().getPACKAGE() + ".*;");
		w.write("\n");
		w.write("\n");
	}

	
	private String getFields() {
		String fields = "\n";
		
		String[] _fields = clazz.getFields().split(",");
		for (int i = 0; i < _fields.length; i++)
			fields += "+\"" + _fields[i] + ", \" \n";
		
		clazz.getFields();
		
		
		return fields;
	}
	
	
	private String getParamns(){
		String paramns = "";
		
		return paramns;
	}
	
	private void defineConstants() throws IOException {
		// final INSERT
		String s = "    private string SQL_INSERT = \"INSERT INTO " + clazz.getTableName();
		
		s += " ( \" \n " + getFields() + " \" ) VALUES ( " + getParamns() + " );\";";
		w.write(s);
		w.write("\n");

		// final UPDATE
		String[] _fields = clazz.getFields().split(",");
		for (int i = 0; i < _fields.length; i++)
			_fields[i] = _fields[i] + " = ?";
		
		String fields = "";
		String comma = "";
		for (String f : _fields){
			fields += comma + f;
			comma = ",";
		}
			
			
		s = "    private final String SQL_UPDATE = \"UPDATE "
				+ clazz.getTableName();
		s += " SET " + fields + " WHERE ( CONDITION )  ;\";";
		w.write(s);
		w.write("\n");
		
	}

	private void defineAttributes() throws IOException {
		String s;
		//
		w.write("\n");
		s = "    private " + clazz.getName() + " itemSelected;";
		w.write(s);
		w.write("\n");
	}

	private void defineMehods() throws IOException {
		String s;
		// METHODS
		s = "    public void insert() { ";
		w.write(s);
		w.write("\n");

		w.write("    }");
		w.write("\n");
		w.write("\n");

		s = "    public void update() { ";
		w.write(s);
		w.write("\n");

		w.write("    }");
		w.write("\n");
		w.write("\n");

		s = "    public void delete() { ";
		w.write(s);
		w.write("\n");

		w.write("    }");
		w.write("\n");
		w.write("\n");
		
		s = "    public " + clazz.getName() + " getItemSelected() { ";
		w.write(s);
		w.write("\n");
		s = "        return itemSelected;";
		w.write(s);
		w.write("\n");
		
		w.write("    }");
		w.write("\n");
		w.write("\n");
		
		s = "    public void setItemSelected("+ clazz.getName() + " newValue) { ";
		w.write(s);
		w.write("\n");
		s = "        this.itemSelected = newValue;";
		w.write(s);
		w.write("\n");
		
		w.write("    }");
		w.write("\n");
	}

}
