package cn.med.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.med.entity.AjaxJson;
import cn.med.entity.CwDepartment;
import cn.med.entity.CwPosition;
import cn.med.entity.CwUser;
import cn.med.entity.CwWorkTime;
import cn.med.service.CwDepartmentService;
import cn.med.service.CwPositionService;
import cn.med.service.CwUserService;
import cn.med.service.CwWorkTimeService;
import utils.StatusCodeUtil;



@Controller

public class CwPositionController {

	@Autowired
	private CwPositionService positionService;
	@Autowired
	private CwWorkTimeService workTimeService;
	
	@ResponseBody
	@RequestMapping(value = "/checkWork/position/getPositionByCode", method = RequestMethod.POST)
     public  AjaxJson getPositionByCode(@RequestParam("positionCode") String positionCode) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			
	            if(!positionCode.isEmpty()){
	            	CwPosition position = positionService.selectPositionByCode(positionCode);
		          
		            if(position!=null){
		            	 j.setData(position);
		            	 j.setMsg("查询成功");
	  		             j.setSuccess(true);
	  		             j.setErrorcode(200);
		            }else{
		            	 j.setData("");
  		            	 j.setMsg("暂无数据");
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
	
	
	@ResponseBody
	@RequestMapping(value = "/checkWork/position/getAllPositionByDepartId", method = RequestMethod.POST)
     public  AjaxJson getAllPositionByDepartId(@RequestParam("departId") String departId) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			List<CwPosition> pos=new ArrayList<CwPosition>();
	            if(!departId.isEmpty()){
	            	pos= positionService.selectDepartById(departId);
		          
		            if(pos.size()>0){
		            	 j.setData(pos);
		            	 j.setMsg("查询成功");
	  		             j.setSuccess(true);
	  		             j.setErrorcode(200);
		            }else{
		            	 j.setData("");
  		            	 j.setMsg("该部门暂无岗位数据");
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
	 * 添加岗位
	 * **/
	@ResponseBody
	@RequestMapping(value = "/checkWork/position/addPosition", method = RequestMethod.POST)
     public  AjaxJson addPosition(@RequestParam("departId") String departId,@RequestParam("positionName") String positionName) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
				
	            if(!departId.isEmpty()&&!positionName.isEmpty()){
	            	CwPosition cp=new CwPosition();
	            	cp.setWork_code(new Date().getTime()+"");
	            	cp.setDepartid(Integer.parseInt(departId));
	            	cp.setWork_name(positionName);
	            	int isok=positionService.insert(cp);
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
	 * 修改岗位
	 * **/
	@ResponseBody
	@RequestMapping(value = "/checkWork/position/updatePosition", method = RequestMethod.POST)
     public  AjaxJson updatePosition(@RequestParam("positionCode") String positionCode,@RequestParam("positionName") String positionName,@RequestParam("wktype") String wktype) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			
	            if(!positionCode.isEmpty()&&!positionName.isEmpty()){
	            	CwPosition cd=new CwPosition(positionCode,positionName);
	            	int isok=positionService.updatePosition(cd);
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
	 * 删除单个岗位
	 * **/
	@ResponseBody
	@RequestMapping(value = "/checkWork/position/delPositionByCode", method = RequestMethod.POST)
     public  AjaxJson delPositionByCode(@RequestParam("positionCode") String positionCode) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			
	            if(!positionCode.isEmpty()){
	            	int wknumb = workTimeService.delWorkTime(positionCode);
	            	if(wknumb>0){
	            		int numb = positionService.deletePosition(positionCode);
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
	 * 删除多个岗位
	 * **/
	@ResponseBody
	@RequestMapping(value = "/checkWork/position/delPositions", method = RequestMethod.POST)
     public  AjaxJson DelDeparts(@RequestParam(value = "datas[]") int[] datas) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			
	            if(datas.length>0){
	            	int numb = positionService.deletePositions(datas);
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
