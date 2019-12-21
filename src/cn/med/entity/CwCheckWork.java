package cn.med.entity;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 考勤设置
 * @author Administrator
 *
 */
public class CwCheckWork implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private int id;
	private int userid;
	private String ambegin_time;
	private String ambegin_state;
	private String amend_time;
	private String amend_state;
	private String pmbegin_time;
	private String pmbegin_state;
	private String pmend_time;
	private String pmend_state;
	private String coordinate;
	private String creat_time;
	private String remark;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getAmbegin_time() {
		return ambegin_time;
	}
	public void setAmbegin_time(String ambegin_time) {
		this.ambegin_time = ambegin_time;
	}
	public String getAmbegin_state() {
		return ambegin_state;
	}
	public void setAmbegin_state(String ambegin_state) {
		this.ambegin_state = ambegin_state;
	}
	public String getAmend_time() {
		return amend_time;
	}
	public void setAmend_time(String amend_time) {
		this.amend_time = amend_time;
	}
	public String getAmend_state() {
		return amend_state;
	}
	public void setAmend_state(String amend_state) {
		this.amend_state = amend_state;
	}
	public String getPmbegin_time() {
		return pmbegin_time;
	}
	public void setPmbegin_time(String pmbegin_time) {
		this.pmbegin_time = pmbegin_time;
	}
	public String getPmbegin_state() {
		return pmbegin_state;
	}
	public void setPmbegin_state(String pmbegin_state) {
		this.pmbegin_state = pmbegin_state;
	}
	public String getPmend_time() {
		return pmend_time;
	}
	public void setPmend_time(String pmend_time) {
		this.pmend_time = pmend_time;
	}
	public String getPmend_state() {
		return pmend_state;
	}
	public void setPmend_state(String pmend_state) {
		this.pmend_state = pmend_state;
	}
	public String getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getCreat_time() {
		return creat_time;
	}
	public void setCreat_time(String creat_time) {
		this.creat_time = creat_time;
	}
	
	
	@Override
	public String toString() {
		return "CwCheckWork [id=" + id + ", userid=" + userid + ", ambegin_time=" + ambegin_time + ", ambegin_state="
				+ ambegin_state + ", amend_time=" + amend_time + ", amend_state=" + amend_state + ", pmbegin_time="
				+ pmbegin_time + ", pmbegin_state=" + pmbegin_state + ", pmend_time=" + pmend_time + ", pmend_state="
				+ pmend_state + ", coordinate=" + coordinate + ", creat_time=" + creat_time + ", remark=" + remark
				+ "]";
	}

	

	


	
	
    
    
    
}