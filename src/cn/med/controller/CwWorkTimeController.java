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

public class CwWorkTimeController {

	@Autowired
	private CwWorkTimeService workTimeService;
	@Autowired
	private CwUserService cwUserService;
	
	@Autowired
	private CwPositionService positionService;
	
/*	@ResponseBody
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
	*/
	
	@ResponseBody
	@RequestMapping(value = "/checkWork/worktime/getWtByCode", method = RequestMethod.POST)
     public  AjaxJson getWtByCode(@RequestParam("workcode") String workcode) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			
	            if(!workcode.isEmpty()){
	            	CwWorkTime workTime = workTimeService.selectWtByCode(workcode);
	            	
		            if(workTime!=null){
		            	
		            	 j.setData(workTime);
		            	 j.setMsg("查询成功");
	  		             j.setSuccess(true);
	  		             j.setErrorcode(200);
		            }else{
		            	 j.setData("");
  		            	 j.setMsg("该部门没有设置考勤时间");
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
	/*@ResponseBody
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
		
	}*/
	/**
	 * 添加考勤时间
	 * **/
	@ResponseBody
	@RequestMapping(value = "/checkWork/worktime/addWt", method = RequestMethod.POST)
     public  AjaxJson AddDepart(@RequestParam("wkcode") String wkcode,@RequestParam("amstarttime") String amstarttime,@RequestParam("amendtime") String amendtime,@RequestParam("pmstarttime") String pmstarttime,@RequestParam("pmendtime") String pmendtime,@RequestParam("worktype")  String worktype) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			
	            if(!wkcode.isEmpty()&&!amstarttime.isEmpty()&&!pmendtime.isEmpty()&&!worktype.isEmpty()){
	            	CwWorkTime ct=new CwWorkTime();
	            	ct.setWork_code(wkcode);
	            	ct.setAmstarttime(amstarttime);
	            	ct.setAmendtime(amendtime);
	            	ct.setPmstarttime(pmstarttime);
	            	ct.setPmendtime(pmendtime);
	            	ct.setRemark("");
	            	ct.setWork_type(Integer.parseInt(worktype));
	            	int isok=workTimeService.insert(ct);
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
	@RequestMapping(value = "/checkWork/worktime/updateWt", method = RequestMethod.POST)
     public  AjaxJson UpdateDepart(@RequestParam("wtid") String wtid,@RequestParam("wkcode") String wkcode,@RequestParam("amstarttime") String amstarttime,@RequestParam("amendtime") String amendtime,@RequestParam("pmstarttime") String pmstarttime,@RequestParam("pmendtime") String pmendtime,@RequestParam("worktype")  String worktype) throws Exception{
		 //时间间隔
	   //  final long PERIOD_DAY = 24 * 60 * 60 * 1000;
		AjaxJson j = new AjaxJson();
		try {
			
			if(!wtid.isEmpty()&&!wkcode.isEmpty()&&!worktype.isEmpty()){
				
			     
					//String[] timeattr=new String[]{amstarttime,amendtime,pmstarttime,pmendtime};
	            	CwWorkTime cd=new CwWorkTime(Integer.parseInt(wtid),amstarttime,amendtime,pmstarttime,pmendtime,Integer.parseInt(worktype));
	            	int isok=workTimeService.updateDepart(cd);
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



/*	private void setAMFunction(final long PERIOD_DAY, final String departId, String amstarttime, String amendtime, String pmstarttime, String pmendtime) {
		if(amstarttime!=""){
			String[] amstarstr=amstarttime.split(":");
			Integer shi= Integer.parseInt(amstarstr[0]);
			Integer fen= Integer.parseInt(amstarstr[1]);
			
			*//*** 定制每日指定时间执行方法 ***//*
			 Calendar calendar = Calendar.getInstance();
			  calendar.set(Calendar.HOUR_OF_DAY,shi);
			  calendar.set(Calendar.MINUTE,fen+1);
			  calendar.set(Calendar.SECOND, 0);
			  Date date=calendar.getTime(); //第一次执行定时任务的时间
			  //如果第一次执行定时任务的时间 小于 当前的时间
			  //此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
			  if (date.before(new Date())) {
			      date = this.addDay(date, 1);
			  }
			   
			  Timer timer = new Timer();
			   
			  TimerTask task = new TimerTask() {
				
				@Override
				public void run() {
						setALLUserAmStartState(departId);	
				}

			
				
			};
			  //安排指定的任务在指定的时间开始进行重复的固定延迟执行。
			  timer.schedule(task,date,PERIOD_DAY);
		}
			
		
		 
	}*/
	  // 增加或减少天数
    public Date addDay(Date date, int num) {
     Calendar startDT = Calendar.getInstance();
     startDT.setTime(date);
     startDT.add(Calendar.DAY_OF_MONTH, num);
     return startDT.getTime();
    }
	public void setALLUserAmStartState(String departId) {
		// TODO Auto-generated method stub
		List<CwUser> userlist=cwUserService.selDepartById(departId);
		if(userlist.size()>0){
			
		}
	}

}
