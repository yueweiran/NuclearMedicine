package cn.med.common;

import java.sql.Timestamp;

public class TimeChange {

	public Timestamp dateChange(String time){
		
		Timestamp ts = new Timestamp(System.currentTimeMillis());   
        try {   
            ts = Timestamp.valueOf(time);   
 
        } catch (Exception e) {   
            e.printStackTrace();   
        } 
        return ts;
	}
}
