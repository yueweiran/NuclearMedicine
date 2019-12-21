package cn.med.test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import cn.med.dao.MedicineDao;
import cn.med.entity.Medicine;

@Controller 
public class Listener extends HttpServlet{
	  private static final Logger logger = LoggerFactory.getLogger(Listener.class);  
      
	    private final int sleepTime=5*60*1000;  
	      
	    @Resource(name = "medicineDao")  
	    private MedicineDao medicineDao;  
	  
	      
	    // Servlet的init方法会在Tomcat启动的时候执行  
	    @Override  
	    public void init() throws ServletException {  
	    		System.out.println("123");
	    }  
	    public void taskstart(){  
	        while (true) {  
	            try {  
	                System.out.println("init");  
	                System.out.println("==============================");  
	                Thread.sleep(5*1000);  
	              List<Medicine> list = medicineDao.selectAllMedicine();  
	              System.out.println("=============================="+list.size());  
	                logger.info("tomcat 测试方法");  
	            } catch (InterruptedException e) {  
	                // TODO Auto-generated catch block  
	                e.printStackTrace();  
	            }  
	              
	        }  
	    }  
	  
}
