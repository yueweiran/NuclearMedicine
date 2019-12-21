package cn.med.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.med.entity.AjaxJson;
import cn.med.entity.CwDepartment;
import cn.med.entity.CwEnclosure;
import cn.med.entity.CwWorkTime;
import cn.med.service.CwDepartmentService;
import cn.med.service.CwEnclosureService;
import cn.med.service.CwWorkTimeService;
import utils.StatusCodeUtil;



@Controller

public class CwEnclosureController {

	@Autowired
	private CwEnclosureService enclosureService;
	

	
	@ResponseBody
	@RequestMapping(value = "/checkWork/enclosure/getdepartById", method = RequestMethod.POST)
     public  AjaxJson getDepartById(@RequestParam("departId") String departId) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			
	            if(!departId.isEmpty()){
	            	CwEnclosure enclosure = enclosureService.selectDepartById(departId);
		          
		            if(enclosure!=null){
		            	 j.setData(enclosure);
		            	 j.setMsg("查询成功");
	  		             j.setSuccess(true);
	  		             j.setErrorcode(200);
		            }else{
		            	 j.setData("");
  		            	 j.setMsg("该部门没有设置围栏");
  		            	 j.setSuccess(false);
  		            	 j.setErrorcode(200);
		            }
		           
					
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
	 * 添加围栏
	 * **/
	@ResponseBody
	@RequestMapping(value = "/checkWork/enclosure/addEnclosure", method = RequestMethod.POST)
     public  AjaxJson AddEnclosure(@RequestParam("departId") String departId,@RequestParam("enclosure") String enclosure) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			
	            if(!departId.isEmpty()&&!enclosure.isEmpty()){
	            	CwEnclosure ce =new CwEnclosure();
	            	ce.setDepartid(Integer.parseInt(departId));
	            	ce.setEnclosure(enclosure);
	            	int isok=enclosureService.insert(ce);
		            if(isok>0){
		            	 j.setData("");
		            	 j.setMsg("添加成功");
	  		             j.setSuccess(true);
	  		             j.setErrorcode(200);
		            }else{
		            	 j.setData("");
  		            	 j.setMsg("添加失败");
  		            	 j.setSuccess(false);
  		            	 j.setErrorcode(200);
		            }
		           
					
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
	 * 修改考勤时间
	 * **/
	@ResponseBody
	@RequestMapping(value = "/checkWork/enclosure/upEnclosure", method = RequestMethod.POST)
     public  AjaxJson UpdateEnclosure(@RequestParam("eid") String eid,@RequestParam("enclosure") String enclosure) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			
			if(!eid.isEmpty()&&!enclosure.isEmpty()){
				CwEnclosure cd=new CwEnclosure(Integer.parseInt(eid),enclosure);
	            	int isok=enclosureService.updateEnclosure(cd);
		            if(isok>0){
		            	 j.setData("");
		            	 j.setMsg("修改成功");
	  		             j.setSuccess(true);
	  		             j.setErrorcode(200);
		            }else{
		            	 j.setData("");
  		            	 j.setMsg("修改失败");
  		            	 j.setSuccess(false);
  		            	 j.setErrorcode(200);
		            }
		           
					
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
	 * 删除指定围栏
	 * **/
	@ResponseBody
	@RequestMapping(value = "/checkWork/enclosure/delEnclosureById", method = RequestMethod.POST)
     public  AjaxJson DelEnclosure(@RequestParam("eid") String eid) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			
	            if(!eid.isEmpty()){
	            	int numb = enclosureService.deleteEnclosure(Integer.parseInt(eid));
		            if(numb>0){
		            	 j.setData("");
		            	 j.setMsg("删除成功");
	  		             j.setSuccess(true);
	  		             j.setErrorcode(200);
		            }else{
		            	 j.setData("");
  		            	 j.setMsg("删除失败");
  		            	 j.setSuccess(false);
  		            	 j.setErrorcode(200);
		            }
		           
					
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
	
}
