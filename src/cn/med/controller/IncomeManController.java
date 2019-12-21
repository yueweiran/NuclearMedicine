package cn.med.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.med.common.ResponseResult;
import cn.med.common.SysUtils;
import cn.med.common.TimeChange;
import cn.med.entity.IncomeMan;
import cn.med.service.IncomeManService;



@Controller
@ResponseBody
public class IncomeManController {

	@Autowired
	private IncomeManService incomeManService;
	

	
	/**
	 * 查询全部收入集合
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/select/incomeManList/incomeMan", method = RequestMethod.GET)
     public  ResponseResult medicineeList(HttpServletRequest request) throws Exception{
  	       
    	 ResponseResult result = null;
 		try {
 			
 			
 			List<IncomeMan> incomeMan = incomeManService.selectAllIncomeMan();
 			if (incomeMan != null){
 				result = new ResponseResult();
 	 			result.setCode(1);
 	 			result.setMsg("查询成功");
 	 			result.setData(incomeMan);
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
	 * 查询单个收入
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/select/incomeMan/incomeManId", method = RequestMethod.GET)
     public  ResponseResult medicineOne(@Param("inId")int inId,HttpServletRequest request) throws Exception{
  	       
    	 ResponseResult result = null;
 		try {
 			
 			
 			IncomeMan incomeMan = incomeManService.selectByPrimaryKey(inId);
 			if (incomeMan != null){
 				result = new ResponseResult();
 	 			result.setCode(1);
 	 			result.setMsg("查询成功");
 	 			result.setData(incomeMan);
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
	 * 模糊查询单个收入
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/select/incomeMan/inName", method = RequestMethod.POST)
     public  ResponseResult selectByinName(@Param("inName")String inName,HttpServletRequest request) throws Exception{
  	       
		String inName2 = inName + "%";
		
    	 ResponseResult result = null;
 		try {
 			
 			
 			List<IncomeMan> incomeMan = incomeManService.selectByinName(inName2);
 			if (incomeMan != null){
 				result = new ResponseResult();
 	 			result.setCode(1);
 	 			result.setMsg("查询成功");
 	 			result.setData(incomeMan);
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
	@RequestMapping(value = "/delete/incomeMan/incomeMans", method = RequestMethod.POST)
	public  ResponseResult deleteByAll(@RequestParam(value = "datas[]") int[] datas,HttpServletRequest request) throws Exception{

		ResponseResult result = null;
		try {
 		
			int numb = incomeManService.deleteByAll(datas);
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
	 * 删除单个收入
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/delete/incomeMan/incomeManId", method = RequestMethod.POST)
     public  ResponseResult deleteByPrimaryKey(@Param("inId")int inId,HttpServletRequest request) throws Exception{
  	       
    	 ResponseResult result = null;
 		try {
 			
 			
 			int numb = incomeManService.deleteByPrimaryKey(inId);
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
	 * 插入单个收入
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/insert/incomeMan/incomeMan", method = RequestMethod.POST)
     public  ResponseResult insert(@Param("inName")String inName,
    		 @Param("inNumber")Integer inNumber,@Param("inOrdernum")Integer inOrdernum,
    		 @Param("inPay")String inPay,@Param("inCard")Integer inCard,
    		 @Param("inCashier")String inCashier,
    		 @Param("inCreateTime")String inCreateTime,HttpServletRequest request) throws Exception{
		TimeChange timeChange = new TimeChange();   
		IncomeMan incomeMan = new IncomeMan(inName,inNumber,inOrdernum,inPay,inCard,inCashier,timeChange.dateChange(inCreateTime));
		
    	 ResponseResult result = null;
 		try {
 			
 			
 			int numb = incomeManService.insert(incomeMan);
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
	 * 修改单个收入
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/update/incomeMan/incomeMan", method = RequestMethod.POST)
     public  ResponseResult updateByPrimaryKey(@Param("inId")Integer inId,@Param("inName")String inName,
    		 @Param("inNumber")Integer inNumber,@Param("inOrdernum")Integer inOrdernum,
    		 @Param("inPay")String inPay,@Param("inCard")Integer inCard,
    		 @Param("inCashier")String inCashier,
    		 @Param("inCreateTime")String inCreateTime,HttpServletRequest request) throws Exception{
		
		TimeChange timeChange = new TimeChange();   
		IncomeMan incomeMan = new IncomeMan(inId,inName,inNumber,inOrdernum,inPay,inCard,inCashier,timeChange.dateChange(inCreateTime));
    	ResponseResult result = null;
 		try {
 			
 			int numb = incomeManService.updateByPrimaryKey(incomeMan);
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
