package cn.med.entity;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 考勤设置
 * @author Administrator
 *
 */
public class CwEnclosure implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private int id;
	private int departid;
	private String enclosure;
	private String remark;
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
	public String getEnclosure() {
		return enclosure;
	}
	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public CwEnclosure() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CwEnclosure(int id,String enclosure) {
		super();
		this.id=id;
		this.enclosure=enclosure;
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CwEnclosure [id=" + id + ", departid=" + departid + ", enclosure=" + enclosure + ", remark=" + remark
				+ "]";
	}
	
	
    
    
    
}