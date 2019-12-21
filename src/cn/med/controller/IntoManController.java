package cn.med.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.med.common.ResponseResult;
import cn.med.common.TimeChange;
import cn.med.entity.IntoMan;
import cn.med.service.IntoManService;



@Controller
@ResponseBody
public class IntoManController {

	@Autowired
	private IntoManService intoManService;
	
	/**
	 * 查询全部进货集合
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/select/intoManList/intoMan", method = RequestMethod.GET)
     public  ResponseResult medicineeList() throws Exception{
  	       
    	 ResponseResult result = null;
 		try {
 			List<IntoMan> intoMan = intoManService.selectAllIntoMan();
 			if (intoMan != null){
 				result = new ResponseResult();
 	 			result.setCode(1);
 	 			result.setMsg("查询成功");
 	 			result.setData(intoMan);
 	 	 		return result;
 			} else {
 				result = new ResponseResult();
 	 			result.setCode(2);
 	 			result.setMsg("查询失败");
 	 	 		return result;
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 			// 返回结果
 			result = new ResponseResult();
 			result.setCode(3);
 			result.setMsg("查询失败");
 		}
 		return result;
 	}
 	
	/**
	 * 查询单个进货
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/select/intoMan/intoManId", method = RequestMethod.GET)
     public  ResponseResult medicineOne(@Param("intoId")int intoId) throws Exception{
  	       
    	 ResponseResult result = null;
 		try {
 			IntoMan intoMan = intoManService.selectByPrimaryKey(intoId);
 			if (intoMan != null){
 				result = new ResponseResult();
 	 			result.setCode(1);
 	 			result.setMsg("查询成功");
 	 			result.setData(intoMan);
 	 	 		return result;
 			} else {
 				result = new ResponseResult();
 	 			result.setCode(2);
 	 			result.setMsg("查询失败");
 	 	 		return result;
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 			// 返回结果
 			result = new ResponseResult();
 			result.setCode(3);
 			result.setMsg("查询失败");
 		}
 		return result;
 	}
	
	
	
	/**
	 * 查询单个进货
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/select/intoMan/intoName", method = RequestMethod.POST)
     public  ResponseResult selectByintoName(@Param("intoName")String intoName) throws Exception{
  	       
		String intoName2 = intoName + "%";
		
    	 ResponseResult result = null;
 		try {
 			List<IntoMan> intoMan = intoManService.selectByintoName(intoName2);
 			if (intoMan != null){
 				result = new ResponseResult();
 	 			result.setCode(1);
 	 			result.setMsg("查询成功");
 	 			result.setData(intoMan);
 	 	 		return result;
 			} else {
 				result = new ResponseResult();
 	 			result.setCode(2);
 	 			result.setMsg("查询失败");
 	 	 		return result;
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 			// 返回结果
 			result = new ResponseResult();
 			result.setCode(3);
 			result.setMsg("查询失败");
 		}
 		return result;
 	}



	/**
	 * 删除多个
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/delete/intoMan/intoManIds", method = RequestMethod.POST)
	public  ResponseResult deleteByAll(@RequestParam(value = "datas[]") int[] datas) throws Exception{

		ResponseResult result = null;
		try {
			int numb = intoManService.deleteByAll(datas);
			if (numb == 1){
				result = new ResponseResult();
				result.setCode(1);
				result.setMsg("删除成功");
				return result;
			} else {
				result = new ResponseResult();
				result.setCode(2);
				result.setMsg("删除失败");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 返回结果
			result = new ResponseResult();
			result.setCode(3);
			result.setMsg("删除失败");
		}
		return result;
	}
	

	
	/**
	 * 删除单个进货
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/delete/intoMan/intoManId", method = RequestMethod.POST)
     public  ResponseResult deleteByPrimaryKey(@Param("intoId")int intoId) throws Exception{
  	       
    	 ResponseResult result = null;
 		try {
 			int numb = intoManService.deleteByPrimaryKey(intoId);
 			if (numb == 1){
 				result = new ResponseResult();
 	 			result.setCode(1);
 	 			result.setMsg("删除成功");
 	 	 		return result;
 			} else {
 				result = new ResponseResult();
 	 			result.setCode(2);
 	 			result.setMsg("删除失败");
 	 	 		return result;
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 			// 返回结果
 			result = new ResponseResult();
 			result.setCode(3);
 			result.setMsg("删除失败");
 		}
 		return result;
 	}
	

	/**
	 * 插入单个进货
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/insert/intoMan/intoMan", method = RequestMethod.POST)
     public  ResponseResult insert(@Param("intoName")String intoName,
    		 @Param("intoType")String intoType,
    		 @Param("intoNumber")Integer intoNumber,
    		 @Param("intoPrice")Long intoPrice,
    		 @Param("intoCreateTime")String intoCreateTime) throws Exception{
		
		TimeChange timeChange = new TimeChange();   
		IntoMan intoMan = new IntoMan(intoName,intoType,intoNumber,intoPrice,timeChange.dateChange(intoCreateTime));
		
    	 ResponseResult result = null;
 		try {
 			int numb = intoManService.insert(intoMan);
 			if (numb == 1){
 				result = new ResponseResult();
 	 			result.setCode(1);
 	 			result.setMsg("插入成功");
 	 	 		return result;
 			} else {
 				result = new ResponseResult();
 	 			result.setCode(2);
 	 			result.setMsg("插入失败");
 	 	 		return result;
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 			// 返回结果
 			result = new ResponseResult();
 			result.setCode(3);
 			result.setMsg("插入失败");
 		}
 		return result;
 	}
	
	/**
	 * 修改单个进货
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/update/intoMan/intoMan", method = RequestMethod.POST)
     public  ResponseResult updateByPrimaryKey(@Param("intoId")Integer intoId,
    		 @Param("intoName")String intoName,
    		 @Param("intoType")String intoType,
    		 @Param("intoNumber")Integer intoNumber,
    		 @Param("intoPrice")Long intoPrice,
    		 @Param("intoCreateTime")String intoCreateTime) throws Exception{
  	       
		TimeChange timeChange = new TimeChange();   
		IntoMan intoMan = new IntoMan(intoId,intoName,intoType,intoNumber,intoPrice,timeChange.dateChange(intoCreateTime));
    	 ResponseResult result = null;
 		try {
 			int numb = intoManService.updateByPrimaryKey(intoMan);
 			if (numb == 1){
 				result = new ResponseResult();
 	 			result.setCode(1);
 	 			result.setMsg("修改成功");
 	 	 		return result;
 			} else {
 				result = new ResponseResult();
 	 			result.setCode(2);
 	 			result.setMsg("修改失败");
 	 	 		return result;
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 			// 返回结果
 			result = new ResponseResult();
 			result.setCode(3);
 			result.setMsg("修改失败");
 		}
 		return result;
 	}
}
