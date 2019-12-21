package cn.med.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.med.dao.CwDepartmentDao;
import cn.med.dao.CwEnclosureDao;
import cn.med.dao.CwWorkTimeDao;
import cn.med.entity.CwDepartment;
import cn.med.entity.CwEnclosure;
import cn.med.entity.CwUser;
import cn.med.entity.CwWorkTime;



@Service
public class CwEnclosureService {

	@Autowired
	private CwEnclosureDao enclosureDao;
	

	

	/**
	 * 根据部门id查询部门
	 * 
	 * @author xz
	 */
	public CwEnclosure selectDepartById(String departId){
		
		CwEnclosure enclosure = enclosureDao.selectDepartById(departId);		
		return enclosure;
	}
	
	/**
	 * 插入单条数据
	 * 
	 *  writer : xz
	 */
	public int insert(CwEnclosure enclosure){
		
		int numb = enclosureDao.insert(enclosure);		
		return numb;
	}
	
	
	
	
	/**
	 * 修改单个数据
	 * 
	 *   writer : xz
	 */
	public int updateEnclosure(CwEnclosure enclosure){
		
		int numb = enclosureDao.updateEnclosure(enclosure);		
		return numb;
	}
	

	public  int deleteEnclosure(Integer eid){
		int numb = enclosureDao.deleteEnclosure(eid);		
		return numb;
	}
}
