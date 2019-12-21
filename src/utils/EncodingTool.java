package utils;

import java.io.UnsupportedEncodingException;

/**
 * 字符串的处理类
 * @author submail
 *
 */
public class EncodingTool {

	public static String  encodeStr(String str){
		try{
			return new String(str.getBytes("ISO-8859-1"),"UTF-8");}
		catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return null;
		}
	
	public static void main(String[] args) {
			System.out.println(EncodingTool.encodeStr("coco11"));
	}
}
