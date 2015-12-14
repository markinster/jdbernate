package com.jdbernate.filewriters;

import java.io.IOException;

import com.jdbernate.objects.ClassScheme;

public interface IFileWriter {
	
	public void write(ClassScheme clazz) throws IOException;

}
