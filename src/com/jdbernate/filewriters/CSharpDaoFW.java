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
	
	private String TAB = "    ";

	@Override
	public void write(ClassScheme clazz) throws IOException {

		this.clazz = clazz;
		path = DataBaseConnector.getInstance().getFolder() + "//dao";
		file = new File(path);
		
		//criando diretorios
		file.mkdir();
		
		//monta o nome da class
		className = clazz.getName() + "DAO";

		//cria o arquivo da classe
		file = new File(path + "//" + className + ".java");
		w = new BufferedWriter(new FileWriter(file));

		writeUsings();
		writeNamespace();

		// head of class
		w.write(TAB + "public class " + className + "\n"+ TAB +"{");
		w.write("\n");

		defineConstants();

		w.write("\n");

		w.write(TAB+"}"); // fim da classe
		
		w.write("\n}"); // fim do namespace

		w.close();
	}

	private void writeNamespace() throws IOException {
		// write package
		w.write("namespace " + DataBaseConnector.getInstance().getPACKAGE() + ".daos");
		w.write("\n{");
		w.write("\n");
	}

	private void writeUsings() throws IOException {
		// import
		//w.write("using " + DataBaseConnector.getInstance().getPACKAGE() + ".*;");
		//w.write("\n");
		//w.write("\n");
	}

	
	private void defineConstants() throws IOException {
		// INSERT
		String s = TAB+TAB+"private string SQL_INSERT = \"INSERT INTO " + clazz.getTableName();		
		s += " ( \" " + getFields()  + TAB+TAB+ " +\" ) VALUES ( \"" + getParamns()  + TAB+TAB+ "+\" );\";";
		w.write(s);
		w.write("\n\n");

		//  UPDATE
		String[] _fields = clazz.getFields().split(",");
		for (int i = 0; i < _fields.length; i++) {
			_fields[i] = _fields[i].replace(" ", ""); //eliminando espacos
			_fields[i] = _fields[i] + " = @" + _fields[i];
		}
		
		String fields = "";
		String fecha = "";
		for (String f : _fields){
			fields += fecha + TAB+TAB+TAB+ "+\"" + f ;
			fecha = ", \" \n ";
		}
		fields += "\" \n ";
			
			
		s = TAB+TAB+"private string SQL_UPDATE = \"UPDATE " + clazz.getTableName() + " SET \" \n";
		s += fields;
		s +=  TAB+TAB+" +\" WHERE ( CONDITION )  ;\";";
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
			fields += fecha + TAB+TAB+TAB+ "+\"" + _fields[i];
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
			paramns += fecha  + TAB+TAB+TAB+ "+\"@" + _fields[i];
			fecha = ", \" \n ";
		}
		paramns += "\" \n ";
		
		clazz.getFields();
		
		
		return paramns;
	}

}
