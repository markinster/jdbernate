package com.jdbernate.filewriters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.jdbernate.connection.DataBaseConnector;
import com.jdbernate.objects.AttributeScheme;
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
		
		w.write(TAB+TAB+"private MySqlConnection conn; \n");
		w.write("\n");
		
		defineConstants();
		
		w.write("\n");
		
		//CONSTRUTOR
		w.write(TAB + TAB + "public " + className + "(MySqlConnection conn)\n"+ TAB + TAB +"{\n");
		w.write(TAB+TAB+TAB+"this.conn = conn; \n");
		w.write(TAB+TAB+"}\n");

		w.write("\n");
		
		
		
		//METODOS
		
		//Inserir
		w.write(TAB + TAB + "public string Inserir(" +clazz.getName()+ " entity)\n" );
		w.write(TAB+TAB+"{\n");
		w.write(TAB+TAB+TAB+"return Salvar(entiry, SQL_INSERT);\n");
		w.write(TAB+TAB+"}\n");
		w.write("\n");
		
		//Alterar
		w.write(TAB + TAB + "public string Alterar(" +clazz.getName()+ " entity)\n" );
		w.write(TAB+TAB+"{\n");
		w.write(TAB+TAB+TAB+"return Salvar(entiry, SQL_UPDATE);\n");
		w.write(TAB+TAB+"}\n");
		w.write("\n");
		
		
		//Salvar
		w.write(TAB + TAB + "public string Salvar(" +clazz.getName()+ " entity, String sql)\n" );
		w.write(TAB+TAB+"{\n");
		w.write(TAB+TAB+TAB+"try\n");
		w.write(TAB+TAB+TAB+"{\n");
		
		w.write(TAB+TAB+TAB+TAB+"MySqlCommand command = new MySqlCommand(sql, conn);\n");
		w.write(TAB+TAB+TAB+TAB+"command = SetaAtributos(tabela, command);\n");
		w.write(TAB+TAB+TAB+TAB+"command.ExecuteNonQuery();\n");
		w.write(TAB+TAB+TAB+TAB+"return null;\n");
		
		w.write(TAB+TAB+TAB+"}\n");
		w.write(TAB+TAB+TAB+"catch (Exception ex)\n");
		w.write(TAB+TAB+TAB+"{\n");
		w.write(TAB+TAB+TAB+TAB+"return ex.ToString();\n");
		w.write(TAB+TAB+TAB+"}\n");
		w.write(TAB+TAB+"}\n");
		w.write("\n");
		
		//Seta Atributos
		w.write(TAB + TAB + "public string SetaAtributos(" +clazz.getName()+ " entity, MySqlCommand command)\n" );
		w.write(TAB+TAB+"{\n");
		
		for (AttributeScheme at : clazz.getAttributes()){
			String string = TAB+TAB+TAB+"command.Parameters.Add(new MySqlParameter(\"";
			string += at.getTableOriginalName()+"\", entity."+at.getName()+"));\n";
			w.write(string);
		}
		
		
		w.write(TAB+TAB+TAB+"return command;\n");
		w.write(TAB+TAB+"}\n");
		w.write("\n");
		
		//------------------------
		
		
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
