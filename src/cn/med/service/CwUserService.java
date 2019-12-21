package cn.med.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.med.dao.CwUserDao;
import cn.med.dao.UserDao;
import cn.med.entity.CwUser;
import cn.med.entity.User;



@Service
public class CwUserService {

	@Autowired
	private CwUserDao userDao;
	


	/**
	 * 查询单个用户
	 * 
	 * @author xz
	 */
	public CwUser selectByPrimaryKey(String userName){
		
		CwUser user = userDao.selectByUserName(userName);		
		return user;
	}
	
	/**
	 * 根据token查询
	 * 
	 * @author xz
	 */
	public CwUser selectByToken(String token){
		
		CwUser user = userDao.selectByToken(token);		
		return user;
	}
	
	/**
	 * 查询单个用户用于判断
	 * 
	 * @author xz
	 */
	public CwUser selectUserOne(String userName,String userPwd){
		
		CwUser user = userDao.selectByUserOne(userName,userPwd);		
		return user;
	}
	
	
	/**
	 * 查询单个用户用于判断
	 * 
	 * @author xz
	 */
	public CwUser selectByUserByDepartId(int departId){
		
		CwUser user = userDao.selectByUserByDepartId(departId);		
		return user;
	}
	
	

	
	/**
	 * 删除单个用户
	 * 
	 * writer : xz
	 */
	public int deleteByPrimaryKey(Integer chargeId){
		
		int numb = userDao.deleteByPrimaryKey(chargeId);		
		return numb;
	}
	
	
	/**
	 * 删除所有
	 * 
	 * writer : xz
	 */
	public int deleteByAll(@Param("datas")int[] datas){
		
		int numb = userDao.deleteByAll(datas);	
		return numb;
	}
	
	
	/**
	 * 插入单个用户
	 * 
	 *  writer : xz
	 */
	public int insert(CwUser user){
		
		int numb = userDao.insert(user);		
		return numb;
	}
	
	/**
	 * 修改单个用户
	 * 
	 *   writer : xz
	 */
	public int updateByPrimaryKey(CwUser user){
		
		int numb = userDao.updateByPrimaryKey(user);		
		return numb;
	}


	public List<CwUser> selDepartById(String departId) {
		// TODO Auto-generated method stub
		List<CwUser> list=userDao.selDepartById(departId);
		return list;
	}
	
}
