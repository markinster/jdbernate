package com.jdbernate.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
	
	public Properties getProperties () throws IOException {
		
		FileInputStream file = new FileInputStream ("jdbernate.properties");
		
		Properties properties = new Properties();
		properties.load(file);
		
		return properties;
		
	}

}
