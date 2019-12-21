package cn.med.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.med.common.Md5Util;
import cn.med.common.ResponseResult;
import cn.med.entity.AjaxJson;
import cn.med.entity.CwUser;
import cn.med.service.CwUserService;
import utils.EncodingTool;
import utils.PropertiesUtil;
import utils.StatusCodeUtil;
import utils.SysUtils;
import utils.TokenProccessor;



@Controller
@ResponseBody
public class CwUserController {

	@Autowired
	private CwUserService userService;
	@ResponseBody
	@RequestMapping(value = "/checkWork/user/userNameOne", method = RequestMethod.POST)
	public AjaxJson getAjaxJson(HttpServletRequest request, HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		try {
			 DiskFileItemFactory factory = new DiskFileItemFactory();   
	            ServletFileUpload upload = new ServletFileUpload(factory);   
	            upload.setHeaderEncoding("UTF-8");  
	            List items = upload.parseRequest(request);  
	            Map params = new HashMap();   
	            for(Object object:items){  
	                FileItem fileItem = (FileItem) object;   
	                if (fileItem.isFormField()) {   
	                    params.put(fileItem.getFieldName(), fileItem.getString("utf-8"));//如果你页面编码是utf-8的   
	                }  
	            }   
	            //使用params.get获取参数值
	            String userName = (String) params.get("userName");
	            String password = (String) params.get("password");
	            String UserDevice=request.getHeader("User-Device"); 
	            String token=request.getHeader("token"); 
			j.setErrorcode(200);
			CwUser c=new CwUser();
			c.setDepartmentId(3);
			c.setName("张三");
			c.setPhone("12365233663");
			c.setToken("12316548798411544651");
			c.setUserName("zhangsan");
			c.setUserType(1);  
			j.setData(c);
			j.setSuccess(true);
			j.setMsg("查询成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("查询失败");
		}
		return j;
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkWork/user/adduserName", method = RequestMethod.POST)
	public AjaxJson getAddNameAjaxJson(@RequestParam("userPhoto") MultipartFile  userPhoto,@RequestParam("userName") String  userName,@RequestParam("userPwd") String  userPwd,@RequestParam("name") String  name,@RequestParam("phone") String  phone,@RequestParam("departmentId") String  departmentId,@RequestParam("areaId") String  areaId,@RequestParam("areaStr") String  areaStr,@RequestParam("workCode") String  workCode){
		AjaxJson j = new AjaxJson();
		try {
			 DiskFileItemFactory factory = new DiskFileItemFactory();   
	            ServletFileUpload upload = new ServletFileUpload(factory);   
	            upload.setHeaderEncoding("UTF-8");  
	            factory.setSizeThreshold(1024 * 500);//设置内存的临界值为500K  
		        File linshi = new File(PropertiesUtil.getValue("LINSHI"));//当超过500K的时候，存到一个临时文件夹中  
		        factory.setRepository(linshi);
	            upload.setSizeMax(1024 * 1024*3);//这只头像最大不超过3M
	            
	            String userphotopath="";
            	String fileName = userPhoto.getOriginalFilename();
	                    InputStream in =userPhoto.getInputStream();  
	                    byte[] buffer = new byte[1024];  
	                    int len = 0;  
	                    Date d = new Date();
	                   
	                    String path=SysUtils.getRootPath()+"/upload/"+fileName;
	                    OutputStream out = new FileOutputStream(path);  
	  
	                    while ((len = in.read(buffer)) != -1) {  
	                        out.write(buffer, 0, len);  
	                    }  
	  
	                    out.close();  
	                    in.close(); 
	                    userphotopath=d.getTime()+path.substring(path.lastIndexOf("."),path.length());
	                    PropertiesUtil.ReName(path,userphotopath);
	                    System.out.println("上传完成");
	            
	            Date d1 = new Date();
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            //使用params.get获取参数值
	            String uName = EncodingTool.encodeStr(userName);
	            String md5Password = Md5Util.md5Str(userPwd);
	            String pwd =md5Password;
	            String name1 =EncodingTool.encodeStr(name) ;
	            String phone1 = phone;
	            String departmentId1 =departmentId;
	            String areaid=areaId;
	            String areastr=EncodingTool.encodeStr(areaStr);
	            String wkcode=EncodingTool.encodeStr(workCode);
	            CwUser c=new CwUser();
				c.setDepartmentId(Integer.parseInt(departmentId1));
				c.setName(name1);
				c.setPhone(phone1);
				c.setUserName(uName);
				c.setUserPwd(pwd);
				c.setUserPhoto(userphotopath);
				c.setUserType(3);  
				c.setCreattime(sdf.format(d1));
				c.setAreaId(Integer.parseInt(areaid));
				c.setAreaStr(areastr);
				c.setWorkCode(wkcode);
				int isInsert=userService.insert(c);
				if(isInsert>0){
					j.setSuccess(true);
					j.setMsg("添加成功");
					j.setErrorcode(200);
					 Map<String,String> mp=new HashMap<String,String>();
					 j.setData(mp);
				}
	            
			
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("添加失败");
			j.setErrorcode(400);
		}
		return j;
	}
	/*public AjaxJson getAjaxJson(HttpServletRequest request, HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		String url = "http://外网接口";//调用sendPost方法时传入的外网接口地址
		Map<String, String> params = new HashMap<String, String>();//调用sendPost方法时传入外网接口地址携带的参数
		params.put("access_token","xxxxxxxxxx");
		params.put("itemId","xxxxxxxx");
		try {
			String orgInfo = sendPost(url,params);
			JSONObject obj = JSONObject.fromObject(orgInfo);  
			Map<String,Object> m = new HashMap<String, Object>();
			m.put("state", obj.get("state"));//从接口的数据中取出state值并传入json中
			m.put("data", obj.get("data"));
			j.setSuccess(true);
			j.setAttributes(m);
			j.setMsg("查询成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("查询失败");
		}
		return j;
	}*/
	
	/**
	 * 查询单个用户
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	/*@ResponseBody
	@RequestMapping(value = "/checkWork/user/selUserName", method = RequestMethod.POST)
     public  AjaxJson UserOne(HttpServletRequest request,HttpServletResponse response) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			 DiskFileItemFactory factory = new DiskFileItemFactory();   
	            ServletFileUpload upload = new ServletFileUpload(factory);   
	            upload.setHeaderEncoding("UTF-8");  
	            List items = upload.parseRequest(request);  
	            Map params = new HashMap();   
	            for(Object object:items){  
	                FileItem fileItem = (FileItem) object;   
	                if (fileItem.isFormField()) {   
	                    params.put(fileItem.getFieldName(), fileItem.getString("utf-8"));//如果你页面编码是utf-8的   
	                }  
	            }   
	            //使用params.get获取参数值
	            String userName = (String) params.get("userName");
	            Map<String,String> mp=new HashMap<String,String>();
	            if(!userName.isEmpty()){
	            	CwUser user = userService.selectByPrimaryKey(userName);
		          
		            if(user !=null){
		            	  mp.put("state", "0");
		            	j.setMsg("不可用");
		            }else{
		            	  mp.put("state", "1");
		            	j.setMsg("可用");
		            }
		            j.setData(mp);
		        	j.setSuccess(true);
					j.setErrorcode(200);
	            }else{
	            	j.setData(mp);
	            	j.setSuccess(false);
	    			j.setErrorcode(400);
	    			j.setMsg(StatusCodeUtil.getConByCode("400"));
	            }
	            
	           
	          
	          
		
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("请求失败");
			j.setErrorcode(400);
		}
		return j;
		
	}*/
	@ResponseBody
	@RequestMapping(value = "/checkWork/user/selUserName", method = RequestMethod.POST)
     public  AjaxJson UserOne(@RequestParam("userName") String uName) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			
			 Map<String,String> mp=new HashMap<String,String>();
	            if(!uName.isEmpty()){
	            	CwUser user = userService.selectByPrimaryKey(uName);
		          
		            if(user !=null){
		            	  mp.put("state", "0");
		            	j.setMsg("不可用");
		            	j.setSuccess(false);
		            }else{
		            	  mp.put("state", "1");
		            	j.setMsg("可用");
		            	j.setSuccess(true);
		            }
		            j.setData(mp);
					j.setErrorcode(200);
	            }else{
	            	j.setData(mp);
	            	j.setSuccess(false);
	    			j.setErrorcode(400);
	    			j.setMsg(StatusCodeUtil.getConByCode("400"));
	            }
	            
	           
	          
	          
		
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("请求失败");
			j.setErrorcode(400);
		}
		return j;
		
	}
	
	/**
	 * 客户端登录
	 * @param uName
	 * @param userPwd
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/checkWork/user/doLogin", method = RequestMethod.POST)
     public  AjaxJson UserOne(@RequestParam("userName") String uName,@RequestParam("userPwd") String userPwd) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			
			 Map<String,Object> mp=new HashMap<String,Object>();
	            if(!uName.isEmpty()&&!userPwd.isEmpty()){
	            	String md5Password = Md5Util.md5Str(userPwd);
	            	CwUser user = userService.selectUserOne(uName,md5Password);
		          
		            if(user !=null){
		            	String token=TokenProccessor.makeToken();
		            	if(token!=""&&token!=null){
		            		user.setToken(token);
		            		int numb = userService.updateByPrimaryKey(user);
		            		if(numb>0){
		            			  mp.put("token",token);
		            			  mp.put("UserType",user.getUserType());
		  		            	j.setMsg("登录成功");
		  		            	j.setSuccess(true);
		  		            	j.setErrorcode(200);
		            		}else{
		            			 mp.put("token","");
		            			 mp.put("UserType","");
		  		            	j.setMsg("登录失败");
		  		            	j.setSuccess(false);
		  		            	j.setErrorcode(400);
		            		}
		            		
		            	}
		            	
		            	
		            	
		            }else{
		            	 mp.put("token","");
            			 mp.put("UserType","");
  		            	j.setMsg("登录失败");
  		            	j.setSuccess(false);
  		            	j.setErrorcode(400);
		            }
		            j.setData(mp);
					
	            }else{
	            	 mp.put("token","");
        			 mp.put("UserType","");
	            	j.setData(mp);
	            	j.setSuccess(false);
	    			j.setErrorcode(400);
	    			j.setMsg(StatusCodeUtil.getConByCode("400"));
	            }
	            
	           
	          
	          
		
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("请求失败");
			j.setErrorcode(400);
		}
		return j;
		
	}
	/**
	 * 客户端退出登录
	 * @param uName
	 * @param userPwd
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/checkWork/user/quitLogin", method = RequestMethod.POST)
	public  AjaxJson quitLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			
			 String token=request.getHeader("token"); 
			if(token!=""&&token!=null){
				CwUser user=userService.selectByToken(token);
				
				if(user !=null){
					
						user.setToken("");
						int numb = userService.updateByPrimaryKey(user);
						if(numb>0){
							j.setMsg("退出成功");
							j.setSuccess(true);
							j.setErrorcode(200);
						}else{
							j.setMsg("退出失败");
							j.setSuccess(false);
							j.setErrorcode(400);
						}
						
					
					
					
				}else{
					j.setMsg("用户不存在");
					j.setSuccess(false);
					j.setErrorcode(400);
				}
				j.setData("");
			}else{
				
				j.setData("");
				j.setSuccess(false);
				j.setErrorcode(400);
				j.setMsg(StatusCodeUtil.getConByCode("400"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("请求失败");
			j.setErrorcode(400);
		}
		return j;
		
	}
	
	/**
	 * 服务端登陆
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/checkWork/server/doLogin", method = RequestMethod.POST)
     public ResponseResult loginStaff(@RequestParam("userName") String uName,@RequestParam("userPwd") String userPwd,HttpServletRequest request) throws Exception{

    	ResponseResult result = null;
    	if(!uName.isEmpty()&&!userPwd.isEmpty()){
    		String md5Password = Md5Util.md5Str(userPwd);
    		Map<String,Object> map = new HashMap<String,Object>();
     		try {
     			
     			CwUser user = userService.selectUserOne(uName,md5Password);
     			
     			if (user != null ) {
     				if(user.getUserType()==1||user.getUserType()==2){
     					request.getSession().setAttribute("stId", user.getId());
         				// 登录成功
         				map.put("stId",user.getId());
         				map.put("stGrade",user.getUserType() );
         				map.put("stDepartment", user.getDepartmentId());
         				map.put("stName", user.getUserName());
         				map.put("stAreaId", user.getAreaId());
         				map.put("stAreaStr",user.getAreaStr());
         				
         				result = new ResponseResult();
         	 			result.setCode(1);
         	 			result.setMsg("登陆成功");
         	 			result.setData(map);
         	 	 		return result;
     				}else{
     				// 登录失败
         				result = new ResponseResult();
         	 			result.setCode(2);
         	 			result.setMsg("登陆失败");
         	 	 		return result;
     				}
     				
     			} else {
     				// 登录失败
     				result = new ResponseResult();
     	 			result.setCode(2);
     	 			result.setMsg("登陆失败");
     	 	 		return result;
     			}
     
     		} catch (Exception e) {
     			e.printStackTrace();
     			// 返回结果
     			result = new ResponseResult();
     			result.setCode(3);
     			result.setMsg("服务器异常");
     		}
    	}
    	return result;
	}
     		
     	
    	
    	
	
	
}
