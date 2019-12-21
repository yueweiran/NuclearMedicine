package cn.med.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.med.dao.IncomeManDao;
import cn.med.entity.IncomeMan;


/**
 * 
 * @author xz
 *
 */
@Service
public class IncomeManService {

	@Autowired
	private IncomeManDao incomeManDao;
	
	/**
	 * 查询全部收入
	 *  
	 *   @author xz
	 */
	public List<IncomeMan> selectAllIncomeMan(){
		
		List<IncomeMan> incomeMan = incomeManDao.selectAllIncomeMan();		
		return incomeMan;
	}
	

	/**
	 * 查询单个收入
	 * 
	 * @author xz
	 */
	public IncomeMan selectByPrimaryKey(Integer inId){
		
		IncomeMan incomeMan = incomeManDao.selectByPrimaryKey(inId);		
		return incomeMan;
	}
	
	
	/**
	 * 模糊查询单个收入
	 * 
	 * @author xz
	 */
	public List<IncomeMan> selectByinName(String inName){
		
		List<IncomeMan> incomeMan = incomeManDao.selectByinName(inName);		
		return incomeMan;
	}
	
	
	/**
	 * 删除所有
	 * 
	 * writer : xz
	 */
	public int deleteByAll(@Param("datas")int[] datas){
		
		int numb = incomeManDao.deleteByAll(datas);
		return numb;
	}
	
	
	/**
	 * 删除单个收入
	 * 
	 * writer : xz
	 */
	public int deleteByPrimaryKey(Integer inId){
		
		int numb = incomeManDao.deleteByPrimaryKey(inId);		
		return numb;
	}
	
	/**
	 * 插入单个收入
	 * 
	 *  writer : xz
	 */
	public int insert(IncomeMan record){
		
		int numb = incomeManDao.insert(record);		
		return numb;
	}
	
	/**
	 * 修改单个收入
	 * 
	 *   writer : xz
	 */
	public int updateByPrimaryKey(IncomeMan record){
		
		int numb = incomeManDao.updateByPrimaryKey(record);		
		return numb;
	}
	
}
