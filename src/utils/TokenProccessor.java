package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import sun.misc.BASE64Encoder;

public class TokenProccessor {
	
	
	 
	/**
	 * 20位末尾的数字id
	 */
	public static volatile int Guid=100;

 
	/**
	 * 生成Token
	 * @return
	 */
	public static String makeToken() {
		//随机数发生器
		
			/*String token = System.currentTimeMillis()+"";
			//获得毫秒数加随机数
			String tokenMd5="";
			try{
				MessageDigest md = MessageDigest.getInstance("md5");
				byte[] md5 = md.digest(token.getBytes());
				BASE64Encoder base =new BASE64Encoder();
				tokenMd5 = base.encode(md5);
			}catch(NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			return tokenMd5;*/
			
		TokenProccessor.Guid+=1;
 
			long now = System.currentTimeMillis();  
			//获取4位年份数字  
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy");  
			//获取时间戳  
			String time=dateFormat.format(now);
			String info=now+"";
			//获取三位随机数  
			//int ran=(int) ((Math.random()*9+1)*100); 
			//要是一段时间内的数据连过大会有重复的情况，所以做以下修改
			int ran=0;
			if(TokenProccessor.Guid>999){
				TokenProccessor.Guid=100;    	
			}
			ran=TokenProccessor.Guid;
					
			return time+new Date().getTime()+ran;  
		}
		


}
