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
import cn.med.entity.CwPosition;
import cn.med.entity.CwQuestion;
import cn.med.service.CwDepartmentService;
import cn.med.service.CwQuestionService;
import utils.StatusCodeUtil;



@Controller

public class CwQuestionController {

	@Autowired
	private CwQuestionService questionService;
	
	@ResponseBody
	@RequestMapping(value = "/checkWork/question/getquestion", method = RequestMethod.POST)
     public  AjaxJson getDepart(@RequestParam("deptId") String deptId,@RequestParam("postionCode") String postionCode) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			
			List<CwQuestion> ques=new ArrayList<CwQuestion>();
	            if(!deptId.isEmpty()&&!postionCode.isEmpty()){
	            	ques = questionService.selQueByDepartId(deptId,postionCode);
		          
		            if(ques.size()>0){
		            	 j.setData(ques);
		            	 j.setMsg("查询成功");
	  		             j.setSuccess(true);
	  		             j.setErrorcode(200);
		            }else{
		            	 j.setData(ques);
  		            	 j.setMsg("该部门暂无设置工作内容");
  		            	 j.setSuccess(false);
  		            	 j.setErrorcode(200);
		            }
		           
					
	            }else{
	            	j.setData(ques);
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
	@RequestMapping(value = "/checkWork/question/getQuestionById", method = RequestMethod.POST)
     public  AjaxJson getPositionByCode(@RequestParam("questionId") String questionId) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			
	            if(!questionId.isEmpty()){
	            	CwQuestion question = questionService.selectQuestionById(questionId);
		          
		            if(question!=null){
		            	 j.setData(question);
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
	
	
	/**
	 * 修改工作类型
	 * **/
	@ResponseBody
	@RequestMapping(value = "/checkWork/question/updateQuestion", method = RequestMethod.POST)
     public  AjaxJson updatePosition(@RequestParam("questionId") String questionId,@RequestParam("questionName") String questionName) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			
	            if(!questionId.isEmpty()&&!questionName.isEmpty()){
	            	CwQuestion cd=new CwQuestion(Integer.parseInt(questionId),questionName);
	            	int isok=questionService.updateQuestion(cd);
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
	 * 添加岗位
	 * **/
	@ResponseBody
	@RequestMapping(value = "/checkWork/question/addQuestion", method = RequestMethod.POST)
     public  AjaxJson addPosition(@RequestParam("departId") String departId,@RequestParam("questionName") String questionName,@RequestParam("workCode") String workCode) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
				
	            if(!departId.isEmpty()&&!questionName.isEmpty()&&!workCode.isEmpty()){
	            	CwQuestion cq=new CwQuestion();
	            	cq.setDepartmentId(Integer.parseInt(departId));
	            	cq.setWorkCode(workCode);
	            	cq.setQuestion(questionName);
	            	int isok=questionService.insert(cq);
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
	
}
