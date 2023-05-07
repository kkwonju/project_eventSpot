package com.kkwo.demo.util;

public class Ut {
	
	/** 해당 객체가 비었는지 체크 */
	public static boolean isEmpty(Object obj) {
		
		if(obj == null) {
			return true;
		}
		
		if(obj instanceof Integer) {
			return (int) obj == 0;
		}
		
		if(!(obj instanceof String)) {
			return true;
		}
		
		String str = (String) obj;
		
		try {
			return str.trim().length() == 0;
		} catch (NullPointerException e) {
			return true;
		}	
	}

}
