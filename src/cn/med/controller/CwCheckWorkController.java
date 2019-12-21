package cn.med.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.med.entity.AjaxJson;
import cn.med.entity.CwCheckWork;
import cn.med.entity.CwDepartment;
import cn.med.entity.CwEnclosure;
import cn.med.entity.CwUser;
import cn.med.entity.CwWorkTime;
import cn.med.entity.TimesObj;
import cn.med.entity.WeiLan;
import cn.med.service.CwCheckWorkService;
import cn.med.service.CwDepartmentService;
import cn.med.service.CwEnclosureService;
import cn.med.service.CwUserService;
import cn.med.service.CwWorkTimeService;
import utils.StatusCodeUtil;
import utils.SysUtils;



@Controller

public class CwCheckWorkController {

	public static final int TWONUM=1;
	public static final int FOURNUM=2;
	
	@Autowired
	private CwUserService userService;
	@Autowired
	private CwWorkTimeService workTimeService;
	@Autowired
	private CwDepartmentService departmentService;
	@Autowired
	private CwCheckWorkService checkWorkService;
	@Autowired
	private CwEnclosureService cwEnclosureService;
	

	
	
	/**
	 * 查询考勤数据
	 * **/
	@ResponseBody
	@RequestMapping(value = "/checkWork/ck/selCheckWork", method = RequestMethod.POST)
     public  AjaxJson selCheckWork(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String path = request.getContextPath();  
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		AjaxJson j = new AjaxJson();
		CwUser cwuser=null;
		CwWorkTime ct=null;
		CwDepartment cd=null;
		CwEnclosure ce=null;
		 Map<String,Object> mp=new HashMap<String,Object>();
		try {
			String token=request.getHeader("token"); 
	            if(!token.isEmpty()){
	            	 cwuser=userService.selectByToken(token);
	            	if(cwuser!=null){
	            		cwuser.setUserPhoto(basePath+"upload/"+cwuser.getUserPhoto());
	            		Integer departid=cwuser.getDepartmentId();
	            		 CwCheckWork cw=checkWorkService.selectCkByUserId(cwuser.getId());
	            		 ct=workTimeService.selectWtByCode(cwuser.getWorkCode());
	            		 cd=departmentService.selectDepartById(departid.toString());
	            		 ce=cwEnclosureService.selectDepartById(departid.toString());
	            		 List<WeiLan> wllist=new ArrayList<WeiLan>();
	            		 List<TimesObj> timelist=new ArrayList<TimesObj>();
	            		 if(ce!=null){
	            			 String[] enclosure=ce.getEnclosure().split(";");
	            			 if(enclosure.length>0){
	            				 for (int i = 0; i < enclosure.length; i++) {
									String[] str=enclosure[i].split(",");
									WeiLan weiLan=new WeiLan();
									weiLan.setLongitude(str[0]);
									weiLan.setLatitude(str[1]);
									wllist.add(weiLan);
								}
	            			 }
	            		 }
	            		 if(ct!=null){
	            			 if(ct.getWork_type()==CwCheckWorkController.TWONUM){
	            				 
	            			 }
	            			 if(ct.getWork_type()==CwCheckWorkController.FOURNUM){
	            				 if(cw!=null){
		            				 TimesObj t1=new TimesObj();
		            				 t1.setWorktime(ct.getAmstarttime());
		            				 t1.setDakatime(cw.getAmbegin_time());
		            				 t1.setState(cw.getAmbegin_state());
		            				 timelist.add(t1);
		            				 
		            				 TimesObj t2=new TimesObj();
		            				 t2.setWorktime(ct.getAmendtime());
		            				 t2.setDakatime(cw.getAmend_time());
		            				 t2.setState(cw.getAmend_state());
		            				 timelist.add(t2);
		            				 
		            				 TimesObj t3=new TimesObj();
		            				 t3.setWorktime(ct.getPmstarttime());
		            				 t3.setDakatime(cw.getPmbegin_time());
		            				 t3.setState(cw.getPmbegin_state());
		            				 timelist.add(t3);
		            				 
		            				 TimesObj t4=new TimesObj();
		            				 t4.setWorktime(ct.getPmendtime());
		            				 t4.setDakatime(cw.getPmend_time());
		            				 t4.setState(cw.getPmend_state());
		            				 timelist.add(t4);
		            			 }else{
		            				 TimesObj t1=new TimesObj();
		            				 t1.setWorktime(ct.getAmstarttime());
		            				 t1.setDakatime("");
		            				 t1.setState("");
		            				 timelist.add(t1);
		            				 
		            				 TimesObj t2=new TimesObj();
		            				 t2.setWorktime(ct.getAmendtime());
		            				 t2.setDakatime("");
		            				 t2.setState("");
		            				 timelist.add(t2);
		            				 
		            				 TimesObj t3=new TimesObj();
		            				 t3.setWorktime(ct.getPmstarttime());
		            				 t3.setDakatime("");
		            				 t3.setState("");
		            				 timelist.add(t3);
		            				 
		            				 TimesObj t4=new TimesObj();
		            				 t4.setWorktime(ct.getPmendtime());
		            				 t4.setDakatime("");
		            				 t4.setState("");
		            				 timelist.add(t4);
		            			 }
	            			 }
	            			
	            				
	            				 
	            		 }
	            		 if(cd!=null){
	            			 mp.put("CwUser", cwuser);
	            			 mp.put("CwWorkTime", ct);
	            			 mp.put("CwDepartment", cd);
	            			 mp.put("nowTime", new Date().getTime());
	            			 mp.put("wlList", wllist);
	            			 mp.put("timelist", timelist);
	            			 j.setData(mp);
			            	 j.setMsg("获取成功");
		  		             j.setSuccess(true);
		  		             j.setErrorcode(200);
	            		 }else{
	            			 j.setData("");
	  		            	 j.setMsg("该部门没有设置考勤时间，请联系管理员");
	  		            	 j.setSuccess(false);
	  		            	 j.setErrorcode(200);
	            		 }
	            	}else{
		            	j.setData("");
		            	j.setSuccess(false);
		    			j.setErrorcode(400);
		    			j.setMsg("用户失效，检查token");
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
	@RequestMapping(value = "/checkWork/ck/getCKbyDate", method = RequestMethod.POST)
	public  AjaxJson getCKbyDate(HttpServletRequest request,@RequestParam("dateStr") String dateStr) throws Exception{
		AjaxJson j = new AjaxJson();
		try {
			CwUser cwuser=null;
			CwWorkTime ct=null;
			 List<TimesObj> timelist=new ArrayList<TimesObj>();
			if(!dateStr.isEmpty()){
				String token=request.getHeader("token"); 
	            if(!token.isEmpty()){
	            	 cwuser=userService.selectByToken(token);
	            	if(cwuser!=null){
	            		CwCheckWork cw=checkWorkService.selectCkByUserAndDate(dateStr,cwuser.getId());
	            		 ct=workTimeService.selectWtByCode(cwuser.getWorkCode());
	            		if(ct!=null){
	            			 if(ct.getWork_type()==CwCheckWorkController.TWONUM){
			    				 
			    			 }
			    			 if(ct.getWork_type()==CwCheckWorkController.FOURNUM){
								 if(cw!=null){
				    				 TimesObj t1=new TimesObj();
				    				 t1.setWorktime(ct.getAmstarttime());
				    				 t1.setDakatime(cw.getAmbegin_time());
				    				 t1.setState(cw.getAmbegin_state());
				    				 timelist.add(t1);
				    				 
				    				 TimesObj t2=new TimesObj();
				    				 t2.setWorktime(ct.getAmendtime());
				    				 t2.setDakatime(cw.getAmend_time());
				    				 t2.setState(cw.getAmend_state());
				    				 timelist.add(t2);
				    				 
				    				 TimesObj t3=new TimesObj();
				    				 t3.setWorktime(ct.getPmstarttime());
				    				 t3.setDakatime(cw.getPmbegin_time());
				    				 t3.setState(cw.getPmbegin_state());
				    				 timelist.add(t3);
				    				 
				    				 TimesObj t4=new TimesObj();
				    				 t4.setWorktime(ct.getPmendtime());
				    				 t4.setDakatime(cw.getPmend_time());
				    				 t4.setState(cw.getPmend_state());
				    				 timelist.add(t4);
				    			 }else{
				    				 TimesObj t1=new TimesObj();
				    				 t1.setWorktime(ct.getAmstarttime());
				    				 t1.setDakatime("");
				    				 t1.setState("");
				    				 timelist.add(t1);
				    				 
				    				 TimesObj t2=new TimesObj();
				    				 t2.setWorktime(ct.getAmendtime());
				    				 t2.setDakatime("");
				    				 t2.setState("");
				    				 timelist.add(t2);
				    				 
				    				 TimesObj t3=new TimesObj();
				    				 t3.setWorktime(ct.getPmstarttime());
				    				 t3.setDakatime("");
				    				 t3.setState("");
				    				 timelist.add(t3);
				    				 
				    				 TimesObj t4=new TimesObj();
				    				 t4.setWorktime(ct.getPmendtime());
				    				 t4.setDakatime("");
				    				 t4.setState("");
				    				 timelist.add(t4);
				    			 }
								 if(timelist.size()>0){
										j.setData(timelist);
										j.setMsg("查询成功");
										j.setSuccess(true);
										j.setErrorcode(200);
									}else{
										j.setData("");
										j.setMsg("指定日期没有考勤记录");
										j.setSuccess(false);
										j.setErrorcode(200);
									}
			    			 }
						
		    			 }else{
		    				 j.setData("");
								j.setMsg("该职位没有设置考勤规则");
								j.setSuccess(false);
								j.setErrorcode(200);
		    			 }
		            }else{
	    				 j.setData("");
							j.setMsg("用户失效");
							j.setSuccess(false);
							j.setErrorcode(200);
	    			 }
	            }
				
				
				
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
	 * 打卡
	 */
	@ResponseBody
	@RequestMapping(value = "/checkWork/ck/doCheckWork", method = RequestMethod.POST)
     public  AjaxJson doCheckWork(HttpServletRequest request) throws Exception{
		String path = request.getContextPath();  
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		AjaxJson j = new AjaxJson();
		CwUser cwuser=null;
		CwWorkTime ct=null;
		SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 List<TimesObj> timelist=new ArrayList<TimesObj>();
		 Map<String,Object> mp=new HashMap<String,Object>();
		try {
			String token=request.getHeader("token"); 
	            if(!token.isEmpty()){
	            	 cwuser=userService.selectByToken(token);
	            	if(cwuser!=null){
	            		cwuser.setUserPhoto(basePath+"upload/"+cwuser.getUserPhoto());
	            		 ct=workTimeService.selectWtByCode(cwuser.getWorkCode());
	            		 CwCheckWork cw=checkWorkService.selectCkByUserId(cwuser.getId());
	            		 if(cw==null){
	            			 cw=new CwCheckWork();
	            			 cw.setUserid(cwuser.getId());
	            			 cw.setCreat_time(sm.format(new Date()));
	            			 if(ct!=null){
		            			 if(ct.getWork_type()==CwCheckWorkController.TWONUM){
		            				 
		            			 }
		            			 if(ct.getWork_type()==CwCheckWorkController.FOURNUM){
		            				 //获取考勤各时间段
		            				 SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		            				 String today=sf.format(new Date());
		            				 long ambegin=dateToStamp(today+" "+ct.getAmstarttime());
		            				 long amend=dateToStamp(today+" "+ct.getAmendtime());
		            				 long pmbegin=dateToStamp(today+" "+ct.getPmstarttime());
		            				 long pmend=dateToStamp(today+" "+ct.getPmendtime());
		            				 //获取当前日期时间戳
		            				 long todaystr=new Date().getTime();
		            				 
		            				 if(todaystr<ambegin){
		            						 cw.setAmbegin_state("正常");
			            					 cw.setAmbegin_time(String.valueOf(todaystr));
		            					
		            				 }else if(todaystr>ambegin&&todaystr<amend){
		            					 if(cw.getAmbegin_state()!=null){
		            						 if(cw.getAmend_state()==null){
		            							 cw.setAmend_state("早退");
			            						 cw.setAmend_time(String.valueOf(todaystr));
		            						 }
		            						
		            					 }else{
		            						 cw.setAmbegin_state("迟到");
		            						 cw.setAmbegin_time(String.valueOf(todaystr));
		            					 }
		            				 }else if(todaystr>amend&&todaystr<pmbegin){
		            					 if(cw.getAmbegin_state()==null&&cw.getAmend_state()==null){
		            						 cw.setAmbegin_state("迟到");
		            						 cw.setAmend_state("正常");
		            						 cw.setAmend_time(String.valueOf(todaystr));
		            					 }else if(cw.getAmend_state()!=null){
		            						 cw.setPmbegin_state("正常");
		            						 cw.setPmbegin_time(String.valueOf(todaystr));
		            					 }
		            				 }else if(todaystr>pmbegin&&todaystr<pmend){
		            					 if(cw.getAmbegin_state()==null&&cw.getAmend_state()==null){
		            						 cw.setAmbegin_state("迟到");
		            						 cw.setAmend_state("缺勤");
		            					 }
		            					 if(cw.getPmbegin_state()==null){
		            						 cw.setPmbegin_state("迟到");
		            						 cw.setPmbegin_time(String.valueOf(todaystr));
		            					 }else{
		            						 cw.setPmend_state("早退");
		            						 cw.setPmend_time(String.valueOf(todaystr));
		            					 }
		            					 
		            				 }else if(todaystr>pmend){
		            					 if(cw.getAmbegin_state()==null&&cw.getAmend_state()==null&&cw.getPmbegin_state()==null){
		            						 cw.setAmbegin_state("迟到");
		            						 cw.setAmend_state("缺勤");
		            						 cw.setPmbegin_state("迟到");
		            						 cw.setPmend_state("旷工");
		            					 }else if(cw.getAmbegin_state()==null&&cw.getAmend_state()==null){
		            						 cw.setAmbegin_state("迟到");
		            						 cw.setAmend_state("缺勤");
		            					 }else{
		            						 if(cw.getPmend_state()==null){
		            							 cw.setPmend_state("正常");
			            						 cw.setPmend_time(String.valueOf(todaystr)); 
		            						 }
		            						
		            					 }
		            				 }
		            		
		            			 }
		            			 int isok=checkWorkService.insert(cw);
		            			 if(isok>0){
		            				 cw=checkWorkService.selectCkByUserId(cwuser.getId());
		            				 if(cw!=null){
		            					
		            						 TimesObj t1=new TimesObj();
				            				 t1.setWorktime(ct.getAmstarttime());
				            				 t1.setDakatime(cw.getAmbegin_time());
				            				 t1.setState(cw.getAmbegin_state());
				            				 timelist.add(t1);
				            				 
				            				 TimesObj t2=new TimesObj();
				            				 t2.setWorktime(ct.getAmendtime());
				            				 t2.setDakatime(cw.getAmend_time());
				            				 t2.setState(cw.getAmend_state());
				            				 timelist.add(t2);
				            				 
				            				 TimesObj t3=new TimesObj();
				            				 t3.setWorktime(ct.getPmstarttime());
				            				 t3.setDakatime(cw.getPmbegin_time());
				            				 t3.setState(cw.getPmbegin_state());
				            				 timelist.add(t3);
				            				 
				            				 TimesObj t4=new TimesObj();
				            				 t4.setWorktime(ct.getPmendtime());
				            				 t4.setDakatime(cw.getPmend_time());
				            				 t4.setState(cw.getPmend_state());
				            				 timelist.add(t4);
				            				 j.setData(timelist);
				    		            	 j.setMsg("操作成功");
				    	  		             j.setSuccess(true);
				    	  		             j.setErrorcode(200);
		            					 }
		    		            }else{
		    		            	 j.setData("");
		      		            	 j.setMsg("操作失败");
		      		            	 j.setSuccess(false);
		      		            	 j.setErrorcode(200);
		    		            	
		    		            }
		            		 }else{
		            			 j.setData("");
		  		            	 j.setMsg("该部门没有设置考勤时间，请联系管理员");
		  		            	 j.setSuccess(false);
		  		            	 j.setErrorcode(200);
		            		 }
	            		 }else{
	            			 boolean isdaka=true;
	            			 if(ct!=null){
		            			 if(ct.getWork_type()==CwCheckWorkController.TWONUM){
		            				 
		            			 }
		            			 if(ct.getWork_type()==CwCheckWorkController.FOURNUM){
		            				 //获取考勤各时间段
		            				 SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		            				 String today=sf.format(new Date());
		            				 long ambegin=dateToStamp(today+" "+ct.getAmstarttime());
		            				 long amend=dateToStamp(today+" "+ct.getAmendtime());
		            				 long pmbegin=dateToStamp(today+" "+ct.getPmstarttime());
		            				 long pmend=dateToStamp(today+" "+ct.getPmendtime());
		            				 //获取当前日期时间戳
		            				 long todaystr=new Date().getTime();
		            				 
		            				 if(todaystr<ambegin){
		            					 if(cw.getAmbegin_state()!=null){
		            						 isdaka=false;
		            					 }else{
		            						 cw.setAmbegin_state("正常");
			            					 cw.setAmbegin_time(String.valueOf(todaystr));
		            					 }
		            						
		            					
		            				 }else if(todaystr>ambegin&&todaystr<amend){
		            					 if(cw.getAmbegin_state()!=null){
		            						 if(cw.getAmend_state()==null){
		            							 cw.setAmend_state("早退");
			            						 cw.setAmend_time(String.valueOf(todaystr));
		            						 }else{
		            							 isdaka=false;
		            						 }
		            						
		            					 }else{
		            						 cw.setAmbegin_state("迟到");
		            						 cw.setAmbegin_time(String.valueOf(todaystr));
		            					 }
		            				 }else if(todaystr>amend&&todaystr<pmbegin){
		            					 if(cw.getAmbegin_state()==null&&cw.getAmend_state()==null){
		            						 cw.setAmbegin_state("迟到");
		            						 cw.setAmend_state("正常");
		            						 cw.setAmend_time(String.valueOf(todaystr));
		            					 }else if(cw.getAmend_state()!=null){
		            						 if(cw.getPmbegin_state()==null){
		            							 cw.setPmbegin_state("正常");
		            							 cw.setPmbegin_time(String.valueOf(todaystr));
		            						 }else{
		            							 isdaka=false;
		            						 }
		            					 }
		            				 }else if(todaystr>pmbegin&&todaystr<pmend){
		            					 if(cw.getAmbegin_state()==null&&cw.getAmend_state()==null){
		            						 cw.setAmbegin_state("迟到");
		            						 cw.setAmend_state("缺勤");
		            					 }
		            					 if(cw.getPmbegin_state()==null){
		            						 cw.setPmbegin_state("迟到");
		            						 cw.setPmbegin_time(String.valueOf(todaystr));
		            					 }else{
		            						 if(cw.getPmend_state()==null){
		            							 cw.setPmend_state("早退");
			            						 cw.setPmend_time(String.valueOf(todaystr));
		            						 }else{
		            							 isdaka=false;
		            						 }
		            						 
		            					 }
		            					 
		            				 }else if(todaystr>pmend){
		            					 if(cw.getAmbegin_state()==null&&cw.getAmend_state()==null&&cw.getPmbegin_state()==null){
		            						 cw.setAmbegin_state("迟到");
		            						 cw.setAmend_state("缺勤");
		            						 cw.setPmbegin_state("迟到");
		            						 cw.setPmend_state("旷工");
		            						 cw.setPmend_time(String.valueOf(todaystr)); 
		            					 }else if(cw.getAmbegin_state()==null&&cw.getAmend_state()==null){
		            						 cw.setAmbegin_state("迟到");
		            						 cw.setAmend_state("缺勤");
		            					 }else if(cw.getPmbegin_state()==null){
		            						 cw.setPmbegin_state("迟到");
		            					 }else{
		            						 if(cw.getPmend_state()==null){
		            							 cw.setPmend_state("正常");
			            						 cw.setPmend_time(String.valueOf(todaystr)); 
		            						 }else{
		            							 isdaka=false;
		            						 }
		            						
		            					 }
		            				 }
		            		
		            			 }
		            			 if(isdaka){
		            				 int isok= checkWorkService.updateCheckWork(cw);
			            			 if(isok>0){
			            				 cw=checkWorkService.selectCkByUserId(cwuser.getId());
			            				 if(cw!=null){
			            					
			            						 TimesObj t1=new TimesObj();
					            				 t1.setWorktime(ct.getAmstarttime());
					            				 t1.setDakatime(cw.getAmbegin_time());
					            				 t1.setState(cw.getAmbegin_state());
					            				 timelist.add(t1);
					            				 
					            				 TimesObj t2=new TimesObj();
					            				 t2.setWorktime(ct.getAmendtime());
					            				 t2.setDakatime(cw.getAmend_time());
					            				 t2.setState(cw.getAmend_state());
					            				 timelist.add(t2);
					            				 
					            				 TimesObj t3=new TimesObj();
					            				 t3.setWorktime(ct.getPmstarttime());
					            				 t3.setDakatime(cw.getPmbegin_time());
					            				 t3.setState(cw.getPmbegin_state());
					            				 timelist.add(t3);
					            				 
					            				 TimesObj t4=new TimesObj();
					            				 t4.setWorktime(ct.getPmendtime());
					            				 t4.setDakatime(cw.getPmend_time());
					            				 t4.setState(cw.getPmend_state());
					            				 timelist.add(t4);
					            				 j.setData(timelist);
					    		            	 j.setMsg("操作成功");
					    	  		             j.setSuccess(true);
					    	  		             j.setErrorcode(200);
			            					 }
			    		            }else{
			    		            	 j.setData("");
			      		            	 j.setMsg("操作失败");
			      		            	 j.setSuccess(false);
			      		            	 j.setErrorcode(-1);
			    		            	
			    		            }
		            			 }else{
		            				 j.setData("");
		      		            	 j.setMsg("您已打卡");
		      		            	 j.setSuccess(false);
		      		            	 j.setErrorcode(-1);
		            			 }
		            			
		            		 }else{
		            			 j.setData("");
		  		            	 j.setMsg("该部门没有设置考勤时间，请联系管理员");
		  		            	 j.setSuccess(false);
		  		            	 j.setErrorcode(-1);
		            		 }
	            		 }
	            		
	            	}else{
		            	j.setData("");
		            	j.setSuccess(false);
		    			j.setErrorcode(-1);
		    			j.setMsg("用户失效，检查token");
		            }
					
	            }else{
	            	j.setData("");
	            	j.setSuccess(false);
	    			j.setErrorcode(-1);
	    			j.setMsg(StatusCodeUtil.getConByCode("400"));
	            }
	            
		
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("请求失败");
			j.setErrorcode(-1);
		}
		return j;
		
	}

	
	
	
	/**
	 * 计算时间差
	 */
	public String timediff(long begin_Time,long end_Time){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(format.format(new Date()));
		long starttime;
		long endtime;
		if(begin_Time<end_Time){
			starttime=begin_Time;
			endtime=end_Time;
		}else{
			starttime=end_Time;
			endtime=begin_Time;
		}
		
		//计算天数
		long timediff=endtime-starttime;
		long days = timediff / (1000 * 60 * 60 * 24);
		
		long hours = (timediff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
		
		
		 long minutes = (timediff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
		
		//计算秒数
		 long secs=minutes%60;
		
		String res="天数"+days+", 小时数"+hours+", 分钟数"+minutes+", 秒数"+secs;
		
		return res;
	}
	
	  /* 
     * 将时间转换为时间戳
     */    
    public static long dateToStamp(String s) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        return ts;
    }
	public static void main(String[] args) {
		try {
			new CwCheckWorkController().timediff(dateToStamp("2019-04-30 08:00:00"), dateToStamp("2019-05-01 18:00:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
