package com.pwc.assesment.utils;

import java.sql.Timestamp;
import java.util.Date;


/**
 * Temporary console Log that should be replaced with a full package log 
 * like the log4j.
 *  
 * @author Bashar
 */
public class Log {

	private final static String ERROR = "ERROR";
	private final static String WARN = "WARN";
	private final static String INFO = "INFO";

	private static final String COLON = " : ";

	
	public static void err(Object obj , String msg){
		System.out.println(getDate() + COLON + ERROR + COLON + obj.getClass().getName() + COLON + msg);
	}

	public static void warn(Object obj , String msg){
		System.out.println(getDate() + COLON + WARN + COLON + obj.getClass().getName() + COLON + msg);
	}
	
	public static void info(Object obj , String msg){
		System.out.println(getDate() + COLON + INFO + COLON + obj.getClass().getName() + COLON + msg);
	}
	
	private static String getDate(){
		return new Timestamp(new Date().getTime()).toString();
	}
	
}
