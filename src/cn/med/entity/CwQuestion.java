package cn.med.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class CwQuestion implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private int id;
	private int departmentId;
	private String question;
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
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getWorkCode() {
		return workCode;
	}
	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}
	
	public CwQuestion(){
		super();
	}
	public CwQuestion(int id,String question){
		super();
		this.id=id;
		this.question=question;
	}
	
	@Override
	public String toString() {
		return "CwQuestion [id=" + id + ", departmentId=" + departmentId + ", question=" + question + ", workCode="
				+ workCode + "]";
	}

	

	
	

	
    
    
    
}