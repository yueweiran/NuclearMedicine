package cn.med.entity;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 考勤设置
 * @author Administrator
 *
 */
public class CwPosition implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private int id;
	private int departid;
	private String work_code;
	private String work_name;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDepartid() {
		return departid;
	}
	public void setDepartid(int departid) {
		this.departid = departid;
	}
	public String getWork_code() {
		return work_code;
	}
	public void setWork_code(String work_code) {
		this.work_code = work_code;
	}
	public String getWork_name() {
		return work_name;
	}
	public void setWork_name(String work_name) {
		this.work_name = work_name;
	}

	public CwPosition() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CwPosition(String work_code,String work_name) {
		super();
		this.work_code=work_code;
		this.work_name=work_name;
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CwPosition [id=" + id + ", departid=" + departid + ", work_code=" + work_code + ", work_name="
				+ work_name + "]";
	}
	
	
	
    
    
    
}