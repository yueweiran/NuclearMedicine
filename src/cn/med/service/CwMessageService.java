package cn.med.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.med.dao.CwDepartmentDao;
import cn.med.dao.CwMessageDao;
import cn.med.entity.CwDepartment;
import cn.med.entity.CwMessage;
import cn.med.entity.CwUser;



@Service
public class CwMessageService {

	@Autowired
	private CwMessageDao messageDao;
	


	/**
	 * 添加消息
	 * 
	 *  writer : xz
	 */
	public int insert(CwMessage message){
		
		int numb = messageDao.insert(message);		
		return numb;
	}
	
	

}
