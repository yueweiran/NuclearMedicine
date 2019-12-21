package cn.med.test;


import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DataBasicTest {

	@Test
	public void test(){
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		DataSource source = ac.getBean("dbcp",DataSource.class);
		System.out.println(source);
	}
}
