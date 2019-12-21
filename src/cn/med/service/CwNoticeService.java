package cn.med.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.med.dao.CwDepartmentDao;
import cn.med.dao.CwMessageDao;
import cn.med.dao.CwNoticeDao;
import cn.med.entity.CwDepartment;
import cn.med.entity.CwMessage;
import cn.med.entity.CwNotice;
import cn.med.entity.CwUser;



@Service
public class CwNoticeService {

	@Autowired
	private CwNoticeDao cwNoticeDao;
	


	/**
	 * 添加消息
	 * 
	 *  writer : xz
	 */
	public int insert(CwNotice cwNotice){
		
		int numb = cwNoticeDao.insert(cwNotice);		
		return numb;
	}
	
	

}
