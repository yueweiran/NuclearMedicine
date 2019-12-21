package cn.med.entity;

import java.util.Map;

import net.sf.json.JSONObject;

public class AjaxJson {
	private Integer errorcode;
	private boolean success = true;// 是否成功
	private String msg = "操作成功";// 提示信息
	private Object data = null;// 其他信息
 
	public String getMsg() {
		return msg;
	}
 
 
	public void setMsg(String msg) {
		this.msg = msg;
	}
 
 

 
	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}


	public boolean isSuccess() {
		return success;
	}
 
 
	public void setSuccess(boolean success) {
		this.success = success;
	}


	public Integer getErrorcode() {
		return errorcode;
	}


	public void setErrorcode(Integer errorcode) {
		this.errorcode = errorcode;
	}
	
	


/*
	public String getJsonStr(){
		JSONObject obj = new JSONObject();
		obj.put("code", this.getErrorcode());
		obj.put("success", this.isSuccess());
		obj.put("msg", this.getMsg());
		obj.put("data", this.data);
		return obj.toString();
	}
*/
}
