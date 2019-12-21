package cn.med.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.med.dao.CwDepartmentDao;
import cn.med.entity.CwDepartment;
import cn.med.entity.CwUser;



@Service
public class CwDepartmentService {

	@Autowired
	private CwDepartmentDao departDao;
	

	
	/**
	 * 根据地址id查询部门
	 * 
	 * @author xz
	 */
	public List<CwDepartment> selectDepart(String areaId){
		
		List<CwDepartment> departs = departDao.selectDepart(areaId);		
		return departs;
	}
	/**
	 * 根据部门id查询部门
	 * 
	 * @author xz
	 */
	public CwDepartment selectDepartById(String departId){
		
		CwDepartment departs = departDao.selectDepartById(departId);		
		return departs;
	}
	/**
	 * 根据地址id和部门名称
	 * 
	 * @author xz
	 */
	public  List<CwDepartment> selDepartByName(Integer areaId,String deptName){
		
		List<CwDepartment> departs = departDao.selDepartByName(areaId,deptName);		
		return departs;
	}
	
	
	/**
	 * 插入单个部门
	 * 
	 *  writer : xz
	 */
	public int insert(CwDepartment depart){
		
		int numb = departDao.insert(depart);		
		return numb;
	}
	
	
	
	
	/**
	 * 修改单个部门
	 * 
	 *   writer : xz
	 */
	public int updateDepart(CwDepartment depart){
		
		int numb = departDao.updateDepart(depart);		
		return numb;
	}
	
	
	/**
	 * 删除所有
	 * 
	 * writer : xz
	 */
	public int deleteDeparts(@Param("datas")int[] datas){
		
		int numb = departDao.deleteDeparts(datas);	
		return numb;
	}
	/**
	 * 删除指定部门
	 * 
	 * writer : xz
	 */
	public int deleteDepart(Integer areaId){
		
		int numb = departDao.deleteDepart(areaId);	
		return numb;
	}
}
