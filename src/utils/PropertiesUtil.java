package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	public static String getValue(String key){
		Properties prop = new Properties();
		InputStream in = new PropertiesUtil().getClass().getResourceAsStream("/default.properties");
		try {
			prop.load(in);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}
	
	
	   public static boolean ReName(String path,String newname) {//文件重命名
	       //Scanner scanner=new Scanner(System.in);
	       File file=new File(path);
	       if(file.exists()) {
		       File newfile=new File(file.getParent()+File.separator+newname);//创建新名字的抽象文件
		       if(file.renameTo(newfile)) {
		          
		           return true;
		       }
		       else {
		          
		           return false;
		       }
	       }
	       else {
	           
	           return false;
	       }
	   
	   }
}
