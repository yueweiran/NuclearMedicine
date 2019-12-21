package cn.med.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
/**
 * 考勤设置
 * @author Administrator
 *
 */
public class CwWorkTime implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private int id;
	private String work_code;
	private String amstarttime;
	private String amendtime;
	private String pmstarttime;
	private String pmendtime;
	private String remark;
	private int work_type;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getWork_code() {
		return work_code;
	}
	public void setWork_code(String work_code) {
		this.work_code = work_code;
	}
	public String getAmstarttime() {
		return amstarttime;
	}
	public void setAmstarttime(String amstarttime) {
		this.amstarttime = amstarttime;
	}
	public String getAmendtime() {
		return amendtime;
	}
	public void setAmendtime(String amendtime) {
		this.amendtime = amendtime;
	}
	public String getPmstarttime() {
		return pmstarttime;
	}
	public void setPmstarttime(String pmstarttime) {
		this.pmstarttime = pmstarttime;
	}
	public String getPmendtime() {
		return pmendtime;
	}
	public void setPmendtime(String pmendtime) {
		this.pmendtime = pmendtime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	


	public int getWork_type() {
		return work_type;
	}
	public void setWork_type(int work_type) {
		this.work_type = work_type;
	}
	public CwWorkTime() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CwWorkTime(int id,String amstarttime,String amendtime,String pmstarttime,String pmendtime,int work_type) {
		super();
		this.id=id;
		this.amstarttime=amstarttime;
		this.amendtime=amendtime;
		this.pmstarttime=pmstarttime;
		this.pmendtime=pmendtime;
		this.work_type=work_type;
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CwWorkTime [id=" + id + ", work_code=" + work_code + ", amstarttime=" + amstarttime + ", amendtime="
				+ amendtime + ", pmstarttime=" + pmstarttime + ", pmendtime=" + pmendtime + ", remark=" + remark
				+ ", work_type=" + work_type + "]";
	}
	

	
	
    
    
    
}