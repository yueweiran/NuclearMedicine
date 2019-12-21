package cn.med.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.med.dao.StaffDao;
import cn.med.entity.Staff;



@Service
public class StaffService {

	@Autowired
	private StaffDao staffDao;
	
	/**
	 * 查询全部员工
	 *  
	 *   @author xz
	 */
	public List<Staff> selectAllCharge(){
		
		List<Staff> staff = staffDao.selectAllStaff();		
		return staff;
	}
	

	/**
	 * 查询单个员工
	 * 
	 * @author xz
	 */
	public Staff selectByPrimaryKey(int stId){
		
		Staff staff = staffDao.selectByPrimaryKey(stId);		
		return staff;
	}
	
	
	public Staff selectByNameAndPassword(String stName,String stPassword){
		
		Staff staff = staffDao.selectByNameAndPassword(stName, stPassword);		
		return staff;
	}
	
	
	/**
	 * 模糊查询单个员工信息根据员工姓名
	 * 
	 * @author xz
	 */
	public List<Staff> retrieveUserByIdAndName(String names){
		
		List<Staff> staffList = staffDao.retrieveUserByIdAndName(names);		
		return staffList;
	}
	
	/**
	 * 模糊查询单个员工信息根据员工编号
	 * 
	 * @author xz
	 */
	public List<Staff> retrieveUserBystOrdernum(Long stOrdernum){	
		List<Staff> staff = staffDao.retrieveUserBystOrdernum(stOrdernum);		
		return staff;
	}
	

	
	/**
	 * 权限查询  
	 * 
	 * @author xz
	 */
	public List<Staff> selectStaffGrade(int stGrade){
		
		List<Staff> staff = staffDao.selectStaffGrade(stGrade);		
		return staff;
	}
	
	
	
	/**
	 * 删除单个员工
	 * 
	 * writer : xz
	 */
	public int deleteByPrimaryKey(Integer stId){
		
		int numb = staffDao.deleteByPrimaryKey(stId);		
		return numb;
	}
	
	
	/**
	 * 删除所有
	 * 
	 * writer : xz
	 */
	public int deleteByAll(@Param("datas")int[] datas){
		
		int numb = staffDao.deleteByAll(datas);	
		return numb;
	}
	
	/**
	 * 插入单个员工
	 * 
	 *  writer : xz
	 */
	public int insert(Staff record){
		
		int numb = staffDao.insert(record);		
		return numb;
	}
	
	/**
	 * 修改单个员工
	 * 
	 *   writer : xz
	 */
	public int updateByPrimaryKey(Staff record){
		
		int numb = staffDao.updateByPrimaryKey(record);		
		return numb;
	}
	
}
