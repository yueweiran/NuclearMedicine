package cn.med.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.med.common.ResponseResult;
import cn.med.common.SysUtils;
import cn.med.common.TimeChange;
import cn.med.entity.AjaxJson;
import cn.med.entity.CwUser;
import cn.med.entity.User;
import cn.med.entity.YuArea;
import cn.med.service.CwUserService;
import cn.med.service.UserService;
import cn.med.service.YuAreaService;
import net.sf.json.JSONObject;
import utils.ButtUtil;
import utils.PropertiesUtil;
import utils.StatusCodeUtil;



@Controller

public class YuAreaController {

	@Autowired
	private YuAreaService areaService;
	
	@ResponseBody
	@RequestMapping(value = "/checkWork/yuArea/selAddress", method = RequestMethod.POST)
     public  AjaxJson UserOne(@RequestParam("areaId") String areaId) throws Exception{
		AjaxJson j = new AjaxJson();
		
		List<YuArea> yuareas =new ArrayList<YuArea>();
		
		try {
			
	            if(!areaId.isEmpty()){
	            	yuareas = areaService.selectByPid(Integer.parseInt(areaId));
			          if(yuareas.size()>0){
			        	  j.setData(yuareas);
			        	  j.setSuccess(true);
				  		  j.setMsg("请求成功");
				  	      j.setErrorcode(200);
			          }else{
			        	  j.setData(yuareas);
			        	  j.setSuccess(false);
			  			j.setMsg("无查询内容");
			  			j.setErrorcode(400);
			          }
		           
	            }
	            
	           
	          
	          
		
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("查询失败");
			j.setErrorcode(400);
		}
		return j;
		
	}
}
