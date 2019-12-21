package cn.med.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class CwUser implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private int id;
	private int departmentId;
	private String userPhoto;
	private String userName;
	private String userPwd;
	private int userType;
	private String name;
	private String phone;
	private String creattime;
	private String token;
	private String remark;
	private int areaId;
	private String areaStr;
	private String workCode;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCreattime() {
		return creattime;
	}
	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	

	public String getWorkCode() {
		return workCode;
	}
	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	
	
	
	public String getAreaStr() {
		return areaStr;
	}
	public void setAreaStr(String areaStr) {
		this.areaStr = areaStr;
	}
	@Override
	public String toString() {
		return "CwUser [id=" + id + ", departmentId=" + departmentId + ", userPhoto=" + userPhoto + ", userName="
				+ userName + ", userPwd=" + userPwd + ", userType=" + userType + ", name=" + name + ", phone=" + phone
				+ ", creattime=" + creattime + ", token=" + token + ", remark=" + remark + ", areaId=" + areaId
				+ ", areaStr=" + areaStr + ", workCode=" + workCode + "]";
	}
	

    
    
    
}