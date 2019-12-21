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
import cn.med.service.CwDepartmentService;
import utils.StatusCodeUtil;



@Controller

public class CwDepartmentController {

	@Autowired
	private CwDepartmentService departService;
	
	@ResponseBody
	@RequestMapping(value = "/checkWork/departement/getdepart", method = RequestMethod.POST)
     public  AjaxJson getDepart(@RequestParam("areaId") String areaId) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			
			List<CwDepartment> departs=new ArrayList<CwDepartment>();
	            if(!areaId.isEmpty()){
	            	departs = departService.selectDepart(areaId);
		          
		            if(departs.size()>0){
		            	 j.setData(departs);
		            	 j.setMsg("查询成功");
	  		             j.setSuccess(true);
	  		             j.setErrorcode(200);
		            }else{
		            	 j.setData(departs);
  		            	 j.setMsg("该地区下没有部门");
  		            	 j.setSuccess(false);
  		            	 j.setErrorcode(200);
		            }
		           
					
	            }else{
	            	j.setData(departs);
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
	
	
	@ResponseBody
	@RequestMapping(value = "/checkWork/departement/getdepartById", method = RequestMethod.POST)
     public  AjaxJson getDepartById(@RequestParam("departId") String departId) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			
	            if(!departId.isEmpty()){
	            	CwDepartment department = departService.selectDepartById(departId);
		          
		            if(department!=null){
		            	 j.setData(department);
		            	 j.setMsg("查询成功");
	  		             j.setSuccess(true);
	  		             j.setErrorcode(200);
		            }else{
		            	 j.setData("");
  		            	 j.setMsg("该部门不存在");
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
	 * 根据部门名称查找
	 * @param areaId
	 * @param deptName
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/checkWork/departement/getDepartByName", method = RequestMethod.POST)
	public  AjaxJson getDepartbyname(@RequestParam("areaId") String areaId,@RequestParam("deptName") String deptName) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			
			List<CwDepartment> departs=new ArrayList<CwDepartment>();
			if(!areaId.isEmpty()&&!deptName.isEmpty()){
				departs = departService.selDepartByName(Integer.parseInt(areaId),deptName);
				
				if(departs.size()>0){
					j.setData(departs);
					j.setMsg("查询成功");
					j.setSuccess(true);
					j.setErrorcode(200);
				}else{
					j.setData(departs);
					j.setMsg("该地区下没有部门");
					j.setSuccess(false);
					j.setErrorcode(200);
				}
				
				
			}else if(!areaId.isEmpty()&&deptName.isEmpty()){
				departs = departService.selectDepart(areaId);
				
				if(departs.size()>0){
					j.setData(departs);
					j.setMsg("查询成功");
					j.setSuccess(true);
					j.setErrorcode(200);
				}else{
					j.setData(departs);
					j.setMsg("该地区下没有部门");
					j.setSuccess(false);
					j.setErrorcode(200);
				}
			}else{
				j.setData(departs);
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
	 * 添加部门
	 * **/
	@ResponseBody
	@RequestMapping(value = "/checkWork/departement/addDepart", method = RequestMethod.POST)
     public  AjaxJson AddDepart(@RequestParam("departName") String departName,@RequestParam("areaId") String areaId) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			
	            if(!departName.isEmpty()&&!areaId.isEmpty()){
	            	CwDepartment cd=new CwDepartment();
	            	cd.setAreaId(Integer.parseInt(areaId));
	            	cd.setCreattime(new Date().getTime());
	            	cd.setDepartmentName(departName);
	            	cd.setRemark("");
	            	int isok=departService.insert(cd);
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
	 * 修改部门
	 * **/
	@ResponseBody
	@RequestMapping(value = "/checkWork/departement/updateDepart", method = RequestMethod.POST)
     public  AjaxJson UpdateDepart(@RequestParam("departId") String departId,@RequestParam("departName") String departName) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			
	            if(!departId.isEmpty()&&!departName.isEmpty()){
	            	CwDepartment cd=new CwDepartment(Integer.parseInt(departId),departName);
	            	int isok=departService.updateDepart(cd);
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
	 * 删除多个部门
	 * **/
	@ResponseBody
	@RequestMapping(value = "/checkWork/departement/delDeparts", method = RequestMethod.POST)
     public  AjaxJson DelDeparts(@RequestParam(value = "datas[]") int[] datas) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			
	            if(datas.length>0){
	            	int numb = departService.deleteDeparts(datas);
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
	
	/**
	 * 删除单个部门
	 * **/
	@ResponseBody
	@RequestMapping(value = "/checkWork/departement/delDepartById", method = RequestMethod.POST)
     public  AjaxJson DelDepart(@RequestParam("areaId") String areaId) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			
	            if(!areaId.isEmpty()){
	            	int numb = departService.deleteDepart(Integer.parseInt(areaId));
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
