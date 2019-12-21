package cn.med.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.med.dao.MedicineDao;
import cn.med.entity.Medicine;



@Service
public class MedicineService {

	@Autowired
	private MedicineDao medicineDao;
	
	/**
	 * 查询全部药品
	 *  * writer : xz
	 */
	public List<Medicine> selectAllMedicine(){
		
		List<Medicine> medicine = medicineDao.selectAllMedicine();		
		return medicine;
	}
	
	
	/**
	 * 查询单个药品
	 *  * writer : xz
	 */
	public Medicine selectByPrimaryKey(Integer mId){
		
		Medicine medicine = medicineDao.selectByPrimaryKey(mId);		
		return medicine;
	}
	

	
	/**
	 * 查询单个药品根据药品名
	 *  * writer : xz
	 */
	public List<Medicine>  selectBymedName(String medName){
		
		List<Medicine> medicine = medicineDao.selectBymedName(medName);		
		return medicine;
	}

	
	/**
	 * 删除所有
	 * 
	 * writer : xz
	 */
	public int deleteByAll(@Param("datas")int[] datas){
		
		int numb = medicineDao.deleteByAll(datas);	
		return numb;
	}

	
	/**
	 * 删除单个药品
	 * 
	 * writer : xz
	 */
	public int deleteByPrimaryKey(Integer mId){
		
		int numb = medicineDao.deleteByPrimaryKey(mId);		
		return numb;
	}
	
	/**
	 * 插入单个药品
	 * 
	 *  * writer : xz
	 */
	public int insert(Medicine record){
		
		int numb = medicineDao.insert(record);		
		return numb;
	}
	
	/**
	 * 修改单个药品
	 * 
	 *   writer : xz
	 */
	public int updateByPrimaryKey(Medicine record){
		
		int numb = medicineDao.updateByPrimaryKey(record);		
		return numb;
	}
	
	
	
}
