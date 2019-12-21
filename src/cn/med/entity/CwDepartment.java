package cn.med.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class CwDepartment implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private int id;
	private String departmentName;
	private int areaId;
	private int creatureId;
	private long creattime;
	private String remark;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public long getCreattime() {
		return creattime;
	}
	public void setCreattime(long creattime) {
		this.creattime = creattime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public int getCreatureId() {
		return creatureId;
	}
	public void setCreatureId(int creatureId) {
		this.creatureId = creatureId;
	}
	public CwDepartment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CwDepartment(int id,String departmentName) {
		// TODO Auto-generated constructor stub
		super();
		this.id=id;
		this.departmentName=departmentName;
	}
	@Override
	public String toString() {
		return "CwDepartment [id=" + id + ", departmentName=" + departmentName + ", areaId=" + areaId + ", creatureId="
				+ creatureId + ", creattime=" + creattime + ", remark=" + remark + "]";
	}
	
	

	
    
    
    
}