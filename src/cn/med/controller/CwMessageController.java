package cn.med.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.med.entity.AjaxJson;
import cn.med.entity.CwDepartment;
import cn.med.entity.CwMessage;
import cn.med.entity.CwNotice;
import cn.med.entity.CwUser;
import cn.med.service.CwDepartmentService;
import cn.med.service.CwMessageService;
import cn.med.service.CwNoticeService;
import cn.med.service.CwUserService;
import utils.JiPushUtil;
import utils.StatusCodeUtil;



@Controller

public class CwMessageController {

	@Autowired
	private CwUserService userService;
	@Autowired
	private CwMessageService messageService;
	@Autowired
	private CwNoticeService cwNoticeService;
	
	
	
	@ResponseBody
	@RequestMapping(value = "/checkWork/msg/sendMsgforAdmin", method = RequestMethod.POST)
     public  AjaxJson sendMsgforAdmin(HttpServletRequest request,@RequestParam("longitude") String longitude,@RequestParam("latitude") String latitude) throws Exception{
		AjaxJson j = new AjaxJson();
		boolean isSaveMsg=false;
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			 String token=request.getHeader("token"); 
			 if(token!=""&&token!=null){
				 CwUser user=userService.selectByToken(token);
					
					if(user !=null){
						//获取部门管理员
						CwUser	adminuser=userService.selectByUserByDepartId(user.getDepartmentId());
						if(adminuser!=null){
							if(adminuser.getToken()==null||adminuser.getToken().equals("")){
								isSaveMsg=true;
							}
							if(isSaveMsg){
								//将消息保存到消息表
								CwMessage msg=new CwMessage();
								msg.setLatitude(latitude);
								msg.setLongitude(longitude);
								msg.setMsg_content(user.getName()+"："+sf.format(new Date())+"  离开工作范围！");
								msg.setMsg_time(new Date().getTime()+"");
								msg.setUser_id(adminuser.getId());
								msg.setFrom_user_id(user.getId());
								int num=messageService.insert(msg);
								
								//将消息保存到通知表
								CwNotice cwNotice=new CwNotice();
								cwNotice.setLatitude(latitude);
								cwNotice.setLongitude(longitude);
								cwNotice.setMsg_content(user.getName()+"："+sf.format(new Date())+"  离开工作范围！");
								cwNotice.setMsg_time(new Date().getTime()+"");
								cwNotice.setUser_id(adminuser.getId());
								int cwNoticenum=cwNoticeService.insert(cwNotice);
								if(num>0&&cwNoticenum>0){
									j.setMsg("发送成功");
									j.setSuccess(true);
									j.setErrorcode(200);
								}else{
									j.setMsg("发送失败");
									j.setSuccess(false);
									j.setErrorcode(400);
								}
							}else{
								//将消息保存到消息表
								CwMessage msg=new CwMessage();
								msg.setLatitude(latitude);
								msg.setLongitude(longitude);
								msg.setMsg_content(user.getName()+"："+sf.format(new Date())+"  离开工作范围！");
								msg.setMsg_time(new Date().getTime()+"");
								msg.setUser_id(adminuser.getId());
								msg.setFrom_user_id(user.getId());
								int msgnum=messageService.insert(msg);
								//直接推送消息
								List<String> aliasList=new ArrayList<String>();
						    	aliasList.add(adminuser.getToken());
								int num=JiPushUtil.sendToAliasList(aliasList, "考勤", "消息提醒",user.getName()+"："+sf.format(new Date())+"  离开工作范围！","");
								if(num>0&&msgnum>0){
									j.setMsg("发送成功");
									j.setSuccess(true);
									j.setErrorcode(200);
								}else{
									j.setMsg("发送失败");
									j.setSuccess(false);
									j.setErrorcode(400);
								}
							}
							j.setData("");
						}else{
							j.setMsg("部门管理员不存在");
							j.setSuccess(false);
							j.setErrorcode(400);
						}
					  
					}else{
						j.setMsg("用户不存在");
						j.setSuccess(false);
						j.setErrorcode(400);
					}
			 }else{
				 	j.setData("");
				 	j.setSuccess(false);
					j.setMsg("请求失败");
					j.setErrorcode(400);
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
