package cn.med.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.med.dao.ChargeDao;
import cn.med.entity.Charge;



@Service
public class ChargeService {

	@Autowired
	private ChargeDao chargeDao;
	
	/**
	 * 查询全部退单
	 *  
	 *   @author xz
	 */
	public List<Charge> selectAllCharge(){
		
		List<Charge> charge = chargeDao.selectAllCharge();		
		return charge;
	}
	

	/**
	 * 查询单个退单
	 * 
	 * @author xz
	 */
	public Charge selectByPrimaryKey(int chargeId){
		
		Charge charge = chargeDao.selectByPrimaryKey(chargeId);		
		return charge;
	}
	
	
	
	/**
	 * 模糊查询单个退单
	 * 
	 * @author xz
	 */
	public List<Charge> selectBysolderName(String solderName){
		
		List<Charge> charge = chargeDao.selectBysolderName(solderName);		
		return charge;
	}
	
	
	/**
	 * 删除所有
	 * 
	 * writer : xz
	 */
	public int deleteByAll(@Param("datas")int[] datas){
		
		int numb = chargeDao.deleteByAll(datas);
		return numb;
	}
	
	
	/**
	 * 删除单个退单
	 * 
	 * writer : xz
	 */
	public int deleteByPrimaryKey(Integer chargeId){
		
		int numb = chargeDao.deleteByPrimaryKey(chargeId);		
		return numb;
	}
	
	/**
	 * 插入单个退单
	 * 
	 *  writer : xz
	 */
	public int insert(Charge record){
		
		int numb = chargeDao.insert(record);		
		return numb;
	}
	
	/**
	 * 修改单个退单
	 * 
	 *   writer : xz
	 */
	public int updateByPrimaryKey(Charge record){
		
		int numb = chargeDao.updateByPrimaryKey(record);		
		return numb;
	}
	
}
