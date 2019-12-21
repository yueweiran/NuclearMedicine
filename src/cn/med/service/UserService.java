package cn.med.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.med.dao.UserDao;
import cn.med.entity.User;



@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	/**
	 * 查询全部用户
	 *  
	 *   @author xz
	 */
	public List<User> selectAllCharge(){
		
		List<User> user = userDao.selectAllUser();		
		return user;
	}
	

	/**
	 * 查询单个用户
	 * 
	 * @author xz
	 */
	public User selectByPrimaryKey(int chargeId){
		
		User user = userDao.selectByPrimaryKey(chargeId);		
		return user;
	}
	
	
	/**
	 * 模糊查询单个用户
	 * 
	 * @author xz
	 */
	public List<User> selectByuserName(String userName){
		
		List<User> user = userDao.selectByuserName(userName);		
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
	public int insert(User record){
		
		int numb = userDao.insert(record);		
		return numb;
	}
	
	/**
	 * 修改单个用户
	 * 
	 *   writer : xz
	 */
	public int updateByPrimaryKey(User record){
		
		int numb = userDao.updateByPrimaryKey(record);		
		return numb;
	}
	
}
