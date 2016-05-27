package com.jdbernate.utils;

public class StringUtils {

	public static boolean isEmpty(String value) {
		if (value == null)
			return true;
		
		if (value.isEmpty() || value.trim().equals(""))
			return true;
		
		return false;
		
	}
}
