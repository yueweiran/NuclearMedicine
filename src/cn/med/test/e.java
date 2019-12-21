package cn.med.test;

import cn.med.common.DateConsist;
import cn.med.common.WebDateUtils;

public class e {

	
	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String format = "yyyy-MM-dd HH:mm:ss";
		
		 WebDateUtils wdu = new  WebDateUtils ();
		System.out.println(wdu.getWebsiteDatetime(DateConsist.WEB_URL1, format)
				+ " [bjtime]");
		System.out.println(wdu.getWebsiteDatetime(DateConsist.WEB_URL2, format)
				+ " [百度]");
		System.out.println(wdu.getWebsiteDatetime(DateConsist.WEB_URL3, format)
				+ " [淘宝]");
		System.out.println(wdu.getWebsiteDatetime(DateConsist.WEB_URL4, format)
				+ " [中国科学院国家授时中心]");
		System.out.println(wdu.getWebsiteDatetime(DateConsist.WEB_URL5, format)
				+ " [360安全卫士]");
		System.out.println(wdu.getWebsiteDatetime(DateConsist.WEB_URL6, format)
				+ " [beijing-time]");
		System.out.println(wdu.getWebsiteDatetime(DateConsist.WEB_URL7, format)
				+ " [京东-time]");
	}
}
	
