package cn.med.entity;

import java.io.Serializable;

public class CwNotice implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private int id;
	private int user_id;
	private String msg_content;
	private String msg_time;
	private String longitude;
	private String latitude;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	public String getMsg_time() {
		return msg_time;
	}
	public void setMsg_time(String msg_time) {
		this.msg_time = msg_time;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	@Override
	public String toString() {
		return "CwNotice [id=" + id + ", user_id=" + user_id + ", msg_content=" + msg_content + ", msg_time=" + msg_time
				+ ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}



	

	
    
    
    
}