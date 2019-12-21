package cn.med.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.med.dao.CwCheckWorkDao;
import cn.med.dao.CwDepartmentDao;
import cn.med.dao.CwWorkTimeDao;
import cn.med.entity.CwCheckWork;
import cn.med.entity.CwDepartment;
import cn.med.entity.CwUser;
import cn.med.entity.CwWorkTime;



@Service
public class CwCheckWorkService {

	@Autowired
	private CwCheckWorkDao checkWorkDao;
	

	
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
	 * 根据用户id查询当天考勤记录
	 * 
	 * @author xz
	 */
	public CwCheckWork selectCkByUserId(int userId){
		
		CwCheckWork checkWork = checkWorkDao.selectCkByUserId(userId);		
		return checkWork;
	}
	/**
	 * 根据用户id和指定日期查询考勤记录
	 * 
	 * @author xz
	 */
	public CwCheckWork selectCkByUserAndDate(String date,int userId){
		CwCheckWork checkWork = checkWorkDao.selectCkByUserAndDate(date,userId);		
		return checkWork;
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
	public int insert(CwCheckWork checkWork){
		
		int numb = checkWorkDao.insert(checkWork);		
		return numb;
	}
	
	
	
	
	/**
	 * 修改单个数据
	 * 
	 *   writer : xz
	 */
	public int updateCheckWork(CwCheckWork checkWork){
		
		int numb = checkWorkDao.updateCheckWork(checkWork);		
		return numb;
	}
	

}
