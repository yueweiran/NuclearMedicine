package cn.med.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.med.dao.CwDepartmentDao;
import cn.med.dao.CwWorkTimeDao;
import cn.med.entity.CwDepartment;
import cn.med.entity.CwUser;
import cn.med.entity.CwWorkTime;



@Service
public class CwWorkTimeService {

	@Autowired
	private CwWorkTimeDao workTimeDao;
	

	
	/**
	 * 根据地址id查询部门
	 * 
	 * @author xz
	 */
	/*public List<CwDepartment> selectDepart(String areaId){
		
		List<CwDepartment> departs = departDao.selectDepart(areaId);		
		return departs;
	}*/
	/**
	 * 根据部门id查询部门
	 * 
	 * @author xz
	 */
	public CwWorkTime selectWtByCode(String workcode){
		
		CwWorkTime workTime = workTimeDao.selectWtByCode(workcode);		
		return workTime;
	}
	/**
	 * 根据地址id和部门名称
	 * 
	 * @author xz
	 */
	/*public  List<CwDepartment> selDepartByName(Integer areaId,String deptName){
		
		List<CwDepartment> departs = departDao.selDepartByName(areaId,deptName);		
		return departs;
	}
	*/
	
	/**
	 * 插入单条数据
	 * 
	 *  writer : xz
	 */
	public int insert(CwWorkTime workTime){
		
		int numb = workTimeDao.insert(workTime);		
		return numb;
	}
	
	
	
	
	/**
	 * 修改单个数据
	 * 
	 *   writer : xz
	 */
	public int updateDepart(CwWorkTime workTime){
		
		int numb = workTimeDao.updateDepart(workTime);		
		return numb;
	}
	
	
	/**
	 * 删除所有
	 * 
	 * writer : xz
	 */
/*	public int deleteDeparts(@Param("datas")int[] datas){
		
		int numb = departDao.deleteDeparts(datas);	
		return numb;
	}*/
	/**
	 * 删除指定部门
	 * 
	 * writer : xz
	 */
	public int delWorkTime(String code){
		
		int numb = workTimeDao.delWorkTime(code);	
		return numb;
	}
}
