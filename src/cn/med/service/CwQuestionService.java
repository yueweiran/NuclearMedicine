package cn.med.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.med.dao.CwDepartmentDao;
import cn.med.dao.CwQuestionDao;
import cn.med.entity.CwDepartment;
import cn.med.entity.CwPosition;
import cn.med.entity.CwQuestion;
import cn.med.entity.CwUser;



@Service
public class CwQuestionService {

	@Autowired
	private CwQuestionDao questionDao;
	

	
	/**
	 * 根据部门id查询对应的工作内容
	 * 
	 * @author xz
	 */
	public List<CwQuestion> selQueByDepartId(String departmentId,String workCode){
		
		List<CwQuestion> questions = questionDao.selQueByDepartId(departmentId,workCode);		
		return questions;
	}

	
	/**
	 * 根据id查询工作类型
	 * 
	 * @author xz
	 */


	public CwQuestion selectQuestionById(String questionId) {
		// TODO Auto-generated method stub
		CwQuestion question = questionDao.selectQuestionById(questionId);		
		return question;
	}

/**
 * 修改
 * @param cd
 * @return
 */
	public int updateQuestion(CwQuestion cd) {
		int numb = questionDao.updateQuestion(cd);		
		return numb;
	}


public int insert(CwQuestion cq) {
	int numb = questionDao.insert(cq);		
	return numb;
}
	
	
	/**
	 * 根据地址id和部门名称
	 * 
	 * @author xz
	 *//*
	public  List<CwDepartment> selDepartByName(Integer areaId,String deptName){
		
		List<CwDepartment> departs = departDao.selDepartByName(areaId,deptName);		
		return departs;
	}
	
	

	
	

	
	*//**
	 * 删除所有
	 * 
	 * writer : xz
	 *//*
	public int deleteDeparts(@Param("datas")int[] datas){
		
		int numb = departDao.deleteDeparts(datas);	
		return numb;
	}
	*//**
	 * 删除指定部门
	 * 
	 * writer : xz
	 *//*
	public int deleteDepart(Integer areaId){
		
		int numb = departDao.deleteDepart(areaId);	
		return numb;
	}*/


}
