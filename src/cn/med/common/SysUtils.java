package cn.med.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SysUtils {
	
	/**
	 * yyyy-MM-dd
	 * **/
	public static String getShortNowDate(Date date) { 
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String dateString = formatter.format(date);  
	    return dateString; 
	}
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 * **/
	public static String getLongNowDate(Date date) { 
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    String dateString = formatter.format(date);  
	    return dateString; 
	}
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 * **/
	public static String getLongNowDate(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    String dateString = formatter.format(new Date());  
	    return dateString; 
	}
}
