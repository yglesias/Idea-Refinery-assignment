package com.ir.util;

public class IdCache {
	
	private static long id;

	public static synchronized long getNextId() {
		return ++id;
	}

}
