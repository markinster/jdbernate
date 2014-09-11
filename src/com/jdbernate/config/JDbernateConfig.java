package com.jdbernate.config;

import java.io.Serializable;

public class JDbernateConfig implements Serializable {

	private String pathBase = "src//com//jdbernate//classes";
	private String packageBase = "com.jdbernate.classes";

	public String getPathBase() {
		return pathBase;
	}

	public void setPathBase(String pathBase) {
		this.pathBase = pathBase;
	}

	public String getPackageBase() {
		return packageBase;
	}

	public void setPackageBase(String packageBase) {
		this.packageBase = packageBase;
	}

}