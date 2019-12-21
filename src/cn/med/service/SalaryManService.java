package cn.med.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.med.dao.SalaryManDao;
import cn.med.entity.SalaryMan;



@Service
public class SalaryManService {

	@Autowired
	private SalaryManDao salaryManDao;
	
	/**
	 * 查询全部薪资
	 *  
	 *   @author xz
	 */
	public List<SalaryMan> selectAllCharge(){
		
		List<SalaryMan> salaryMan = salaryManDao.selectAllSalaryMan();		
		return salaryMan;
	}
	

	/**
	 * 查询单个薪资
	 * 
	 * @author xz
	 */
	public SalaryMan selectByPrimaryKey(Integer saId){
		
		SalaryMan salaryMan = salaryManDao.selectByPrimaryKey(saId);		
		return salaryMan;
	}
	
	
	/**
	 * 查询单个  根据员工ID
	 * 
	 * @author xz
	 */
	public SalaryMan selectByStId(Integer stId){
		
		SalaryMan salaryMan = salaryManDao.selectByStId(stId);		
		return salaryMan;
	}
	
	
	/**
	 * 模糊查询单个  根据stName
	 * 
	 * @author xz
	 */
	public List<SalaryMan> selectAllSalaryManByLikeStName(String stName){
		
		List<SalaryMan> salaryManList = salaryManDao.selectAllSalaryManByLikeStName(stName);	
		return salaryManList;
	}
	
	
	/**
	 * 删除单个薪资
	 * 
	 * writer : xz
	 */
	public int deleteByPrimaryKey(Integer saId){
		
		int numb = salaryManDao.deleteByPrimaryKey(saId);		
		return numb;
	}
	
	
	/**
	 * 删除所以ByStId
	 * 
	 * writer : xz
	 */
	public int deleteByAllByStIds(@Param("datas")int[] datas){
		
		int numb = salaryManDao.deleteByAllByStIds(datas);	
		return numb;
	}
	
	/**
	 * 删除单个薪资根据yuangongId
	 * 
	 * writer : xz
	 */
	public int deleteByStId(Integer stId){
		
		int numb = salaryManDao.deleteByStId(stId);	
		return numb;
	}
	
	/**
	 * 插入单个薪资
	 * 
	 *  writer : xz
	 */
	public int insert(SalaryMan record){
		
		int numb = salaryManDao.insert(record);		
		return numb;
	}
	
	/**
	 * 修改单个薪资
	 * 
	 *   writer : xz
	 */
	public int updateByPrimaryKey(SalaryMan record){
		
		int numb = salaryManDao.updateByPrimaryKey(record);		
		return numb;
	}
	
}
