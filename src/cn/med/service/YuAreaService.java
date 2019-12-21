package cn.med.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.med.dao.UserDao;
import cn.med.dao.YuAreaDao;
import cn.med.entity.User;
import cn.med.entity.YuArea;



@Service
public class YuAreaService {

	@Autowired
	private YuAreaDao areaDao;
	

	/**
	 * 模糊查询单个用户
	 * 
	 * @author xz
	 */
	public List<YuArea> selectByPid(int areaId){
		
		List<YuArea> areas = areaDao.selectByPid(areaId);		
		return areas;
	}
	
	

	

}
