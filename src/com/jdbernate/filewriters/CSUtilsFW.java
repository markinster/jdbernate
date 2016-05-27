/**************************************************************************************
 *                                                                                    
 *   This program is free software: you can redistribute it and/or modify              
 *   it under the terms of the GNU General Public License as published by             
 *   the Free Software Foundation, either version 3 of the License, or                
 *   (at your option) any later version.                                              
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   See the license terms in <http://www.gnu.org/licenses/>.
 * 
 ************************************************************************************ 
 * 
 *   Este programa é um software livre; você pode redistribuí-lo e/ou 
 *   modificá-lo dentro dos termos da Licença Pública Geral GNU como 
 *   publicada pela Fundação do Software Livre (FSF); na versão 3 da 
 *   Licença, ou (na sua opinião) qualquer versão.
 *
 *   Este programa é distribuído na esperança de que possa ser útil, 
 *   mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO
 *   a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a
 *   Licença Pública Geral GNU para maiores detalhes.
 *
 *   veja os termos da licença <http://www.gnu.org/licenses/>.
 *   
 *   
 *   Developers:
 *   	Marcos Vinicius Soares ( Markinster ) - e-mail: markinster@gmail.com
 *
 *************************************************************************************
 */
package com.jdbernate.filewriters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.jdbernate.connection.DataBaseConnector;
import com.jdbernate.objects.ClassScheme;

public class CSUtilsFW implements IFileWriter {

	private static final String TAB = "    ";
	private BufferedWriter writer;

	@Override
	public void write(ClassScheme clazz) throws IOException {

		try {
			
			String path = DataBaseConnector.getInstance().getFolder() + "//utils";
			File file = new File(path);
			
			//create dirs
			file.mkdir();

			//created class file
			file = new File(path + "//JdbConverter.cs");

			writer = new BufferedWriter(new FileWriter(file));
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Nao foi possivel criar a classe JdbConverter");
			return;
		}

		writeln("using System;");
		writeln("using System.Collections.Generic;");
		writeln("using System.Linq;");
		writeln("using System.Text;");
		writeln("using System.Threading.Tasks;");
		writeln("");
		writeln("namespace " + DataBaseConnector.getInstance().getPACKAGE() + ".utils");
		writeln("{");
		writeln(TAB + "public class JdbConvert");
		writeln(TAB + "{");
		writeln("");
		writeln(TAB + TAB + "public static Int16 ToInt16(string value)");
		writeln(TAB + TAB + "{");
		writeln(TAB + TAB + TAB + "try");
		writeln(TAB + TAB + TAB + "{");
		writeln(TAB + TAB + TAB + TAB + "Int16 newValue = Convert.ToInt16(value);");
		writeln(TAB + TAB + TAB + TAB + "return newValue;");
		writeln(TAB + TAB + TAB + "}");
		writeln(TAB + TAB + TAB + "catch");
		writeln(TAB + TAB + TAB + "{");
		writeln(TAB + TAB + TAB + TAB + "return 0;");
		writeln(TAB + TAB + TAB + "}");
		writeln(TAB + TAB + "}");
		writeln("");
		writeln(TAB + TAB + "public static Int32 ToInt32(string value)");
		writeln(TAB + TAB + "{");
		writeln(TAB + TAB + TAB + "try");
		writeln(TAB + TAB + TAB + "{");
		writeln(TAB + TAB + TAB + TAB + "Int32 newValue = Convert.ToInt32(value);");
		writeln(TAB + TAB + TAB + TAB + "return newValue;");
		writeln(TAB + TAB + TAB + "}");
		writeln(TAB + TAB + TAB + "catch");
		writeln(TAB + TAB + TAB + "{");
		writeln(TAB + TAB + TAB + TAB + "return 0;");
		writeln(TAB + TAB + TAB + "}");
		writeln(TAB + TAB + "}");
		writeln("");
		writeln(TAB + TAB + "public static bool ToBoolean(string value)");
		writeln(TAB + TAB + "{");
		writeln(TAB + TAB + TAB + "if (value == null)");
		writeln(TAB + TAB + TAB + TAB + "return false;");
		writeln("");
		writeln(TAB + TAB + TAB + "value = value.Trim().ToUpper();");
		writeln("");
		writeln(TAB + TAB + TAB + "if (value.Equals(\"N\") ");
		writeln(TAB + TAB + TAB + TAB + "|| value.Equals(\"0\")");
		writeln(TAB + TAB + TAB + TAB + "|| value.Equals(\"F\")");
		writeln(TAB + TAB + TAB + TAB + "|| value.Equals(\"FALSE\"))");
		writeln(TAB + TAB + TAB + TAB + TAB + "return false;");
		writeln("");
		writeln("");
		writeln(TAB + TAB + TAB + "return true;");
		writeln(TAB + TAB + "}");
		writeln("");
		writeln(TAB + TAB + "public static double ToDouble(string v)");
		writeln(TAB + TAB + "{");
		writeln(TAB + TAB + TAB + "try");
		writeln(TAB + TAB + TAB + "{");
		writeln(TAB + TAB + TAB + TAB + "double vdoub = Convert.ToDouble(v);");
		writeln(TAB + TAB + TAB + TAB + "return vdoub;");
		writeln(TAB + TAB + TAB + "}");
		writeln(TAB + TAB + TAB + "catch");
		writeln(TAB + TAB + TAB + "{");
		writeln(TAB + TAB + TAB + TAB + "return 0;");
		writeln(TAB + TAB + TAB + "}");
		writeln(TAB + TAB + "}");
		writeln("");
		writeln(TAB + TAB + "public static double ToDoubleMoeda(string v)");
		writeln(TAB + TAB + "{");
		writeln(TAB + TAB + TAB + "v = v.Replace(\"R$\", \"\");");
		writeln(TAB + TAB + TAB + "v = v.Replace(\"$ \", \"\");");
		writeln("");
		writeln(TAB + TAB + TAB + "try");
		writeln(TAB + TAB + TAB + "{");
		writeln(TAB + TAB + TAB + TAB + "double retorno = Convert.ToDouble(v);");
		writeln(TAB + TAB + TAB + TAB + "return retorno;");
		writeln(TAB + TAB + TAB + "}");
		writeln(TAB + TAB + TAB + "catch");
		writeln(TAB + TAB + TAB + "{");
		writeln(TAB + TAB + TAB + TAB + "return 0;");
		writeln(TAB + TAB + TAB + "}");
		writeln(TAB + TAB + "}");
		writeln("");
		writeln(TAB + TAB + "public static string toTime (string v)");
		writeln(TAB + TAB + "{");
		writeln(TAB + TAB + TAB + "v = v.Replace(\":\", \"\");");
		writeln(TAB + TAB + TAB + "return v; // hhmmss");
		writeln(TAB + TAB + "}");
		writeln(TAB + "}");
		writeln("}");
		
		writer.close();

	}

	private void writeln(String line) {

		try {
			writer.write(line);
			writer.write("\n");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
}