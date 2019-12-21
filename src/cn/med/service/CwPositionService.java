package cn.med.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.med.dao.CwDepartmentDao;
import cn.med.dao.CwPositionDao;
import cn.med.entity.CwDepartment;
import cn.med.entity.CwPosition;
import cn.med.entity.CwUser;



@Service
public class CwPositionService {

	@Autowired
	private CwPositionDao positionDao;
	
	/**
	 * 根据部门id查询部门
	 * 
	 * @author xz
	 */
	public CwPosition selectPositionByCode(String code){
		
		CwPosition departs = positionDao.selectPositionByCode(code);		
		return departs;
	}
	
	/**
	 * 根据地址id查询部门
	 * 
	 * @author xz
	 */
	public List<CwPosition> selectDepartById(String departId){
		
		List<CwPosition> positions = positionDao.selectDepartById(departId);		
		return positions;
	}


	/**
	 * 插入单个岗位
	 * 
	 *  writer : xz
	 */
	public int insert(CwPosition position){
		
		int numb = positionDao.insert(position);		
		return numb;
	}
	
	
	
	
	/**
	 * 修改单个岗位
	 * 
	 *   writer : xz
	 */
	public int updatePosition(CwPosition position){
		
		int numb = positionDao.updatePosition(position);		
		return numb;
	}
	
	
	
    /**
	 * 删除所有
	 * 
	 * writer : xz
	 */
	public int deletePositions(@Param("datas")int[] datas){
		
		int numb = positionDao.deletePositions(datas);	
		return numb;
	}
	/**
	 * 删除指定岗位
	 * 
	 * writer : xz
	 */
	public int deletePosition(String code){
		
		int numb = positionDao.deletePosition(code);	
		return numb;
	}
}
