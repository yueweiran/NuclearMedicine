package cn.med.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import cn.med.common.SystemTimeTypeTimestamp;
import cn.med.common.TimeChange;
import cn.med.entity.Medicine;
import cn.med.service.MedicineService;



@Controller
@ResponseBody
public class MedicineController {

	@Autowired
	private MedicineService medicineService;
	

	
	/**
	 * 查询全部药品集合
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/select/medicineList/medicine", method = RequestMethod.GET)
     public  ResponseResult medicineeList(HttpServletRequest request) throws Exception{
    	 ResponseResult result = null;
 		try {
 			List<Medicine> medicine = medicineService.selectAllMedicine();
 			if (medicine != null){
 				result = new ResponseResult();
 	 			result.setCode(1);
 	 			result.setMsg("查询成功");
 	 			result.setData(medicine);
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
	 * 查询单个药品
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/select/medicine/medicineId", method = RequestMethod.GET)
     public  ResponseResult medicineOne(@Param("mId")int mId,HttpServletRequest request) throws Exception{
  	       
    	 ResponseResult result = null;
 		try {
 			Medicine medicine = medicineService.selectByPrimaryKey(mId);
 			if (medicine != null){
 				result = new ResponseResult();
 	 			result.setCode(1);
 	 			result.setMsg("查询成功");
 	 			result.setData(medicine);
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
	 * 查询单个药品根据药品名
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/select/medicine/medName", method = RequestMethod.POST)
     public  ResponseResult selectBymedName(@Param("medName")String medName,HttpServletRequest request) throws Exception{
		String medName2 = medName+"%";
    	 ResponseResult result = null;
 		try {
 			List<Medicine> medicine = medicineService.selectBymedName(medName2);
 			if (medicine != null){
 				result = new ResponseResult();
 	 			result.setCode(1);
 	 			result.setMsg("查询成功");
 	 			result.setData(medicine);
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
	@RequestMapping(value = "/delete/medicine/medicines", method = RequestMethod.POST)
	public  ResponseResult deleteByAll(@RequestParam(value = "datas[]") int[] datas,HttpServletRequest request) throws Exception{

		ResponseResult result = null;
		try {
			int numb = medicineService.deleteByAll(datas);
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
	 * 删除单个药品
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/delete/medicine/medicineId", method = RequestMethod.POST)
     public  ResponseResult deleteByPrimaryKey(@Param("mId")int mId,HttpServletRequest request) throws Exception{
  	       
    	 ResponseResult result = null;
 		try {
 			int numb = medicineService.deleteByPrimaryKey(mId);	
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
	 * 插入单个药品
	 * @return
	 * @throws Exception
	 * Writer : xz
	 */
	@RequestMapping(value = "/insert/medicine/medicine", method = RequestMethod.POST)
     public  ResponseResult insert(@Param("medName")String medName,HttpServletRequest request) throws Exception{

		TimeChange timeChange = new TimeChange();
		SystemTimeTypeTimestamp sttt = new SystemTimeTypeTimestamp();
		System.out.println(sttt.getSystemTimeTypeTimestap());
		Medicine medicine = new Medicine(medName,sttt.getSystemTimeTypeTimestap());
	
    	 ResponseResult result = null;
 		try {
 			int numb = medicineService.insert(medicine);
 			if (numb == 1){
 				String proNum_Str = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
 		 		Long proNum  = null;
 		 		if(proNum_Str!=null){
 		 			proNum = Long.parseLong(proNum_Str.toString().trim());
 		 		}
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
	@RequestMapping(value = "/update/medicine/medicine", method = RequestMethod.POST)
     public  ResponseResult updateByPrimaryKey(@Param("mId")Integer mId,
		    		@Param("medName")String medName,HttpServletRequest request) throws Exception{


		TimeChange timeChange = new TimeChange();
		SystemTimeTypeTimestamp sttt = new SystemTimeTypeTimestamp();
		System.out.println(sttt.getSystemTimeTypeTimestap());
		Medicine medicine = new Medicine(mId,medName);
		
    	 ResponseResult result = null;
 		try {
 			
 			int numb = medicineService.updateByPrimaryKey(medicine);
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
