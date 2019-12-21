package cn.med.test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import cn.med.service.MedicineService;
import config.AppConfig;
import lib.MessageSend;
import utils.ConfigLoader;
public class TomcatListener implements ServletContextListener{
	 
	private static Properties pros = null;
	/**
	 * 加载文件时，类载入，静态块内部的操作将被运行一次
	 * */
	static {
		pros = new Properties();
		try {
			pros.load(TomcatListener.class
					.getResourceAsStream("/sqlConnection.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
public void checkDataCount(){
	
	Boolean re = true;
	while(re) {
		try {
			Thread.sleep(5* 1000); //设置暂停的时间 5 秒
			TomcatListener t=new TomcatListener();
			boolean a=t.getSelect();
			if(a){
				 String sql = "select * from medicine"; 
			        Connection conn = getCon();
			        PreparedStatement pst = null;
			        // 定义一个list用于接受数据库查询到的内容
			        List<String> list = new ArrayList<String>();
			        try {
			            pst = (PreparedStatement) conn.prepareStatement(sql);
			            ResultSet rs = pst.executeQuery();
			            while (rs.next()) {
			                // 将查询出的内容添加到list中，其中userName为数据库中的字段名称
			                list.add(rs.getString("med_name"));
			            }
			        } catch (Exception e) {
			        }
				AppConfig config = ConfigLoader.load(ConfigLoader.ConfigType.Message);
				if(list.size()>0){
					for (int i = 0; i < list.size(); i++) {
						MessageSend submail = new MessageSend(config);
						submail.addTo(""+list.get(i)+"");
						submail.addContent("【提现消息】你好，你的验证码是3373");
						submail.send();
					}
				}
				
			}
	        
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
}
 private String entityToString(HttpEntity entity) throws IOException {
         String result = null;
         if(entity != null)
         {
             long lenth = entity.getContentLength();
             if(lenth != -1 && lenth < 2048)
             {
                 result = EntityUtils.toString(entity,"UTF-8");
             }else {
                 InputStreamReader reader1 = new InputStreamReader(entity.getContent(), "UTF-8");
                 CharArrayBuffer buffer = new CharArrayBuffer(2048);
                 char[] tmp = new char[1024];
                 int l;
                 while((l = reader1.read(tmp)) != -1) {
                     buffer.append(tmp, 0, l);
                 }
                 result = buffer.toString();
             }
         }
         return result;
     }
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		final TomcatListener listener=new TomcatListener();
		Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				listener.checkDataCount();
			}
		},"t1");
		
		t1.start();
	}
	
	   public Connection getCon() {
	        //数据库连接名称
	        String username=pros.getProperty("username");
	        //数据库连接密码
	        String password=pros.getProperty("pwd");
	        String driver="com.mysql.jdbc.Driver";
	        //其中test为数据库名称
	        String url="jdbc:mysql://localhost:3306/"+pros.getProperty("dataname");
	        Connection conn=null;
	        try{
	            Class.forName(driver);
	            conn=(Connection) DriverManager.getConnection(url,username,password);
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        return conn;
	    } 
	   public Boolean getSelect() { 
		   Boolean re=false;
		        String sql = "select * from medicine"; 
		        Connection conn = getCon();
		        PreparedStatement pst = null;
		        // 定义一个list用于接受数据库查询到的内容
		        List<String> list = new ArrayList<String>();
		        try {
		            pst = (PreparedStatement) conn.prepareStatement(sql);
		            ResultSet rs = pst.executeQuery();
		            while (rs.next()) {
		                // 将查询出的内容添加到list中，其中userName为数据库中的字段名称
		                list.add(rs.getString("med_name"));
		            }
		        } catch (Exception e) {
		        }
		        if(list.size()>0){
		        	String meid="";
		        	String menum="";
		        	 List<String> list1 = new ArrayList<String>();
		        	 String sql1 = "select * from medicine_num LIMIT 1"; 
		        	 try {
				            pst = (PreparedStatement) conn.prepareStatement(sql1);
				            ResultSet rs = pst.executeQuery();
				            while (rs.next()) {
				            	meid=rs.getString("id");
				            	menum=rs.getString("me_count");
				                list1.add(rs.getString("me_count"));
				            }
				        } catch (Exception e) {
				        }
		        	 if(list1.size()<=0){
		        		 String sql2 = "insert into medicine_num values(null,?)";
				    		PreparedStatement pstmt;
							try {
								pstmt = (PreparedStatement) conn.prepareStatement(sql2);
								pstmt.setInt(1,list.size());
					    		int result = pstmt.executeUpdate();
					    		if(result>0){
					    			re=true;
					    		}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		        	 }else{
		        		 if(list.size()!=Integer.parseInt(menum)){
		        			 String sql2 = "update medicine_num set me_count=? where id ="+meid;
					    		PreparedStatement pstmt;
								try {
									pstmt = (PreparedStatement) conn.prepareStatement(sql2);
									pstmt.setInt(1,list.size());
						    		int result = pstmt.executeUpdate();
						    		if(result>0){
						    			re=true;
						    		}
						    		System.out.println(result);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		        		 }
		        		
		        	 }
		    		
		    	

		        }
		        return re;
		    }

}
