package cn.med.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.med.dao.IntoManDao;
import cn.med.entity.IntoMan;



@Service
public class IntoManService {

	@Autowired
	private IntoManDao intoManDao;
	
	/**
	 * 查询全部进货
	 *  
	 *   @author xz
	 */
	public List<IntoMan> selectAllIntoMan(){
		
		List<IntoMan> intoMan = intoManDao.selectAllIntoMan();		
		return intoMan;
	}
	

	/**
	 * 查询单个进货
	 * 
	 * @author xz
	 */
	public IntoMan selectByPrimaryKey(Integer intoId){
		
		IntoMan intoMan = intoManDao.selectByPrimaryKey(intoId);		
		return intoMan;
	}
	
	
	/**
	 * 模糊查询单个进货
	 * 
	 * @author xz
	 */
	public List<IntoMan> selectByintoName(String intoName){
		
		List<IntoMan> intoMan = intoManDao.selectByintoName(intoName);		
		return intoMan;
	}
	
	
	/**
	 * 删除所有
	 * 
	 * writer : xz
	 */
	public int deleteByAll(@Param("datas")int[] datas){
		
		int numb = intoManDao.deleteByAll(datas);
		return numb;
	}
	
	
	
	/**
	 * 删除单个进货
	 * 
	 * writer : xz
	 */
	public int deleteByPrimaryKey(Integer intoId){
		
		int numb = intoManDao.deleteByPrimaryKey(intoId);		
		return numb;
	}
	
	/**
	 * 插入单个进货
	 * 
	 *  writer : xz
	 */
	public int insert(IntoMan record){
		
		int numb = intoManDao.insert(record);		
		return numb;
	}
	
	/**
	 * 修改单个进货
	 * 
	 *   writer : xz
	 */
	public int updateByPrimaryKey(IntoMan record){
		
		int numb = intoManDao.updateByPrimaryKey(record);		
		return numb;
	}
	
}
