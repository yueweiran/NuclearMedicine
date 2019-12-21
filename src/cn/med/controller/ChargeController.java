package cn.med.controller;

import java.sql.Timestamp;
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
import cn.med.entity.Charge;
import cn.med.service.ChargeService;



@Controller
@ResponseBody
public class ChargeController {

	@Autowired
	private ChargeService chargeService;
	
	/**
	 * 查询全部退单集合
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/select/chargeList/charge", method = RequestMethod.GET)
     public  ResponseResult medicineeList() throws Exception{
  	       
    	 ResponseResult result = null;
 		try {
 			List<Charge> charge = chargeService.selectAllCharge();
 			if (charge != null){
 				result = new ResponseResult();
 	 			result.setCode(1);
 	 			result.setMsg("查询成功");
 	 			result.setData(charge);
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
	 * 查询单个退单
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/select/charge/chargeId", method = RequestMethod.GET)
     public  ResponseResult medicineOne(@Param("chargeId")int chargeId) throws Exception{
  	       
    	 ResponseResult result = null;
 		try {
 			Charge charge = chargeService.selectByPrimaryKey(chargeId);
 			if (charge != null){
 				result = new ResponseResult();
 	 			result.setCode(1);
 	 			result.setMsg("查询成功");
 	 			result.setData(charge);
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
	 * 模糊查询单个退单
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/select/charge/solderName", method = RequestMethod.POST)
     public  ResponseResult selectBysolderName(@Param("solderName")String solderName) throws Exception{
  	       
		String solderName2 = solderName+"%";
		
    	 ResponseResult result = null;
 		try {
 			List<Charge> charge = chargeService.selectBysolderName(solderName2);
 			if (charge != null){
 				result = new ResponseResult();
 	 			result.setCode(1);
 	 			result.setMsg("查询成功");
 	 			result.setData(charge);
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
	@RequestMapping(value = "/delete/charge/charges", method = RequestMethod.POST)
	public  ResponseResult deleteByAll(@RequestParam(value = "datas[]") int[] datas) throws Exception{

		ResponseResult result = null;
		try {
			int numb = chargeService.deleteByAll(datas);
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
	 * 删除单个退单
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/delete/charge/chargeId", method = RequestMethod.POST)
     public  ResponseResult deleteByPrimaryKey(@Param("chargeId")int chargeId) throws Exception{
  	       
    	 ResponseResult result = null;
 		try {
 			int numb = chargeService.deleteByPrimaryKey(chargeId);
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
	 * 插入单个退单
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/insert/charge/charge", method = RequestMethod.POST)
     public  ResponseResult insert(@Param("solderName")String solderName,
    		 @Param("chargeTime")String chargeTime,@Param("chargeReason")String chargeReason,
    		 @Param("chargeNumber")Integer chargeNumber) throws Exception{
  	       		
		Timestamp chargeTime_ts= new TimeChange().dateChange(chargeTime);	
        Charge charge = new Charge(solderName,chargeTime_ts,chargeReason,chargeNumber);     
    	ResponseResult result = null;
 		try {
 			int numb = chargeService.insert(charge);
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
	 * 修改单个药品
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/update/charge/charge", method = RequestMethod.POST)
     public  ResponseResult updateByPrimaryKey(@Param("chargeId")Integer chargeId,@Param("solderName")String solderName,
    		 @Param("chargeTime")String chargeTime,@Param("chargeReason")String chargeReason,
    		 @Param("chargeNumber")Integer chargeNumber) throws Exception{
		
		Timestamp chargeTime_ts= new TimeChange().dateChange(chargeTime);	
        Charge charge = new Charge(chargeId,solderName,chargeTime_ts,chargeReason,chargeNumber);        
    	ResponseResult result = null;
 		try {
 			int numb = chargeService.updateByPrimaryKey(charge);
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
