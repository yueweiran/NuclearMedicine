<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>欢迎页面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="stylesheet" href="css/step.css">
      <link rel="stylesheet" href="css/report.css">
        <link rel="stylesheet" href="../layui/css/layui.css">
         <link rel="stylesheet" href="../layer/theme/default/layer.css">
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/jsp5shiv/r29/jsp5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
 <script src="../js/jquery-1.10.2.js"></script>
  <script src="../js/step.js"></script>
    <script src="../laydate/laydate.js"></script>
  <script src="../layui/layui.js"></script>
  <script type="text/javascript" src="../layer/layer.js"></script>
<style>
.layui-form-label{
	width:110px;
}
.layui-input{
	width:200px;
}
.layui-form-select dl{
	width:200px;
}
.theclear{
	clear: both;
}
.layui-form-item .layui-form-checkbox{
	margin-top:6px;
	margin-left:38px;
}
</style>
  </head>
  
<form id="formTwo" class="layui-form" action="">
					<div class="layui-collapse">
					  <div class="layui-colla-item">
					    <h2 class="layui-colla-title">设备信息<i class="layui-icon layui-colla-icon"></i></h2>
					    <div class="layui-colla-content layui-show">
						   <div class="layui-form-item">
						    <label class="layui-form-label">设备类别</label>
						    <div class="layui-input-inline">
						       <select name="shebeitype" tstitle="请选择设备类别" verification="true" class="verif">
						        <option value=""></option>
						        <option value="0">单光子成像设备</option>
						        <option value="1">发光检验设备</option>
						        <option value="2">放射免疫分析设备</option>
						        <option value="3">符合线路设备</option>
						        <option value="4">功能测定设备</option>
						        <option value="5">小动物成像设备</option>
						        <option value="6">医用回旋加速器</option>
						        <option value="7">正电子成像设备</option>
						      </select>
						      </div>
						    <label class="layui-form-label">设备类型</label>
						    <div class="layui-input-inline">
						       <select name="shebeilx" tstitle="请选择学历" verification="true" class="verif">
						        <option value=""></option>
						        <option value="0">PET</option>
						        <option value="1">PET/CT</option>
						        <option value="2">PET/MR</option>
						        <option value="3">其他</option>
						      </select>
						      </div>
						    </div>
						     <div class="layui-form-item">
						    <label class="layui-form-label">设备厂商</label>
						    <div class="layui-input-inline">
						       <select name="shebeics" tstitle="请选择设备类别" verification="true" class="verif">
						        <option value=""></option>
						        <option value="0">Siemens</option>
						        <option value="1">GE</option>
						        <option value="2">联影</option>
						        <option value="3">Philips</option>
						        <option value="4">其他</option>
						      </select>
						      </div>
						    <label class="layui-form-label">设备型号</label>
						    <div class="layui-input-inline">
						      <input type="text" name="shebeixh" verification="true" tstitle="请输入设备型号" placeholder="请输入设备型号" class="layui-input verif"> 
						    </div>
						    </div>
						   <div class="layui-form-item">
						    <label class="layui-form-label" style="width:140px;">开始使用日期</label>
						    <div class="layui-input-inline">
						      <input type="text" class="layui-input" id="startdate">
						    </div>
						     <div class="layui-input-inline"style="width:50px;">
						      	<div class="adddiv">添加</div>
						     </div>
						    </div>
						   <div class="layui-form-item">
						    	<table class="layui-table" id="test">
								  <thead>
								    <tr>
								      <th></th>
								      <th>设备类别</th>
								      <th>设备类型</th>
								      <th>设备厂商</th>
								      <th>设备型号</th>
								      <th>设备使用日期</th>
								      <th>操作</th>
								    </tr>
								    <tr>
								     <td><div class="layui-unselect  layui-form-checkbox" style="margin-top: 0px;margin-left: 16px;" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div></td>
								      <td>单光子成像设备</td>
								      <td ><div class="texttd">PEC/CT</div></td>
								      <td>Siemens</td>
								      <td>S-abc</td>
								      <td>2020-01-02</td>
								      <td><i class="layui-icon delbtn" onclick="delly(this)">&#xe640;</i></td>
								    </tr>
								    
								  </thead>
								</table>
						    </div>
						 </div>
					  </div>
					  
					 <div class="layui-colla-item">
					    <h2 class="layui-colla-title">设备使用<i class="layui-icon layui-colla-icon"></i></h2>
					    <div class="layui-colla-content">
					    	 <div class="layui-form-item" style="position: relative;height:5vw;">
						   	<label class="layui-form-label" style="width:168px;position: absolute;top:0px;left:0vw;">临床设备使用情况:</label>
						  	<label class="layui-form-label" style="width:138px;margin-left: 3vw;margin-top:2.5vw;">显像类型</label>
						  	  <div class="layui-input-inline" style="margin-top: 2.5vw;">
						       <select name="xxlx" tstitle="请选择显像类型"  verification="true" class="verif">
						        <option value=""></option>
						        <option value="0">单光子显像设备(含γ相机、SPECT和SPECT/CT)</option>
						        <option value="1">PET/CT(含PET)显像设备</option>
						        <option value="2">PET/MR显像设备</option>
						        <option value="3">符合线路SPECT</option>
						      </select>
						      </div>
						  	<label class="layui-form-label" style="margin-top:2.5vw;width:118px;">显像项目名称</label>
						  	  <div class="layui-input-inline" style="margin-top: 2.5vw;">
						       <select name="xmname" tstitle="请选项目名称"  verification="true" class="verif">
						        <option value=""></option>
						        <option value="0">内分泌系统显像</option>
						        <option value="1">神经系统显像</option>
						        <option value="2">循环系统显像</option>
						        <option value="3">骨骼系统显像</option>
						        <option value="4">呼吸系统</option>
						        <option value="5">亲肿瘤显像</option>
						        <option value="6">消化系统显像</option>
						        <option value="7">泌尿系统显像</option>
						        <option value="8">造血与淋巴系统显像</option>
						      </select>
						      </div>
						  </div> 
						   <div class="layui-form-item" style="margin-top:3vw;">
						  	<label class="layui-form-label" style="width:138px;margin-left: 3vw;">显像比例数</label>
						  	  <div class="layui-input-inline">
						       <input type="text" class="layui-input" value="0" id="xxbl">
						      </div>
						  	<label class="layui-form-label"style="width:118px;">显像阳性率：</label>
						  	  <div class="layui-input-inline" style="width:100px;">
						       <input type="text" class="layui-input" style="width:100px;"value="0" id="xxls">
						      </div>
						       <label class="layui-form-label" style="width:5px;">%</label>
						  </div> 
						   <div class="layui-form-item">
						    <label class="layui-form-label" style="width:208px;">相比上一年，显像例数：</label>
						    <div class="layui-input-inline">
						       <select name="xxls" tstitle="请选择显像例数" verification="true" class="xxls">
						        <option value=""></option>
						        <option value="0">增加</option>
						        <option value="1">减少</option>
						        <option value="2">持平</option>
						        <option value="3">不清楚</option>
						      </select>
						      </div>
						    <div class="layui-input-inline"style="margin-left:5px;width:100px;">
						      <input type="text" class="layui-input" style="width:100px;" id="lsnum">
						    </div>
							  <label class="layui-form-label" style="width:5px;">%</label>
							   <div class="layui-input-inline"style="width:50px;">
						      	<div class="adddiv">添加</div>
						     </div>
						    </div>
						    <hr>
						    <div class="layui-form-item" style="position: relative;height:5vw;">
						   	<label class="layui-form-label" style="width:175px;position: absolute;top:0px;left:0vw;">科研显像设备使用情况:</label>
						  	<label class="layui-form-label" style="width:188px;margin-left: 3vw;margin-top:2.5vw;">小动物显像设备类型</label>
						  	  <div class="layui-input-inline" style="margin-top: 2.5vw;">
						       <select name="xxsblx" tstitle="请选择设备类型"  verification="true" class="verif">
						        <option value=""></option>
						        <option value="0">PET/CT</option>
						        <option value="1">PET/MR</option>
						        <option value="2">SPECT</option>
						        <option value="3">SPECT/CT</option>
						        <option value="4">SPECT/MR</option>
						        <option value="5">SPECT/PET/CT</option>
						        <option value="6">CT</option>
						        <option value="7">光学系统</option>
						      </select>
						      </div>
						  	<label class="layui-form-label" style="margin-top:2.5vw;">显像例数</label>
						  	  <div class="layui-input-inline" style="margin-top: 2.5vw;width:100px;">
						     	 <input type="text" class="layui-input" style="width:100px;" value="0" id="xxls">
						   	  </div>	
						     <div class="layui-input-inline" style="width:50px;">
						      	<div class="adddiv" style="margin-top:3vw;width:50px;">添加</div>
						     </div>
						  </div> 
						  <div class="layui-form-item" style="margin-top:3vw">
						    	<table class="layui-table" id="test1">
								  <thead>
								    <tr>
								      <th></th>
								      <th>临床(科研)设备</th>
								      <th>显像类型</th>
								      <th>项目名称</th>
								      <th>显像例数</th>
								      <th>显像例数变化</th>
								      <th>变化百分比</th>
								      <th>阳性率</th>
								      <th>操作</th>
								    </tr>
								    <tr>
								     <td><div class="layui-unselect  layui-form-checkbox" style="margin-top: 0px;margin-left: 16px;" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div></td>
								      <td>临床</td>
								      <td>符合线路SPECT</td>
								      <td ><div class="texttd">亲肿瘤显像</div></td>
								      <td>10</td>
								      <td>不清楚</td>
								      <td>1</td>
								      <td>2</td>
								      <td><i class="layui-icon delbtn" onclick="delly(this)">&#xe640;</i></td>
								    </tr>
								    
								  </thead>
								</table>
						    </div>
						     <hr>
						    <div class="layui-form-item" style="position: relative;height:5vw;">
						   	<label class="layui-form-label" style="width:168px;position: absolute;top:0px;left:0vw;">正电子显像其他情况:</label>
						  	<label class="layui-form-label" style="width:138px;margin-left: 3vw;margin-top:2.5vw;">医保范围</label>
						  	  <div class="layui-input-inline" style="margin-top: 2.5vw;">
						       <select name="ybfw" tstitle="请选择医保范围"  verification="true" class="verif">
						        <option value=""></option>
						        <option value="0">检查费</option>
						        <option value="1">药费</option>
						        <option value="2">检查费+药费</option>
						        <option value="3">未纳入医保</option>
						      </select>
						      </div>
						  		<label class="layui-form-label" style="margin-top:2.5vw;width:150px;">病例来源：门诊</label>
						  	  <div class="layui-input-inline" style="margin-top: 2.5vw;width:50px;">
						     	 <input type="text" class="layui-input" style="width:50px;"  id="mz">
						   	  </div>
						   	   <label class="layui-form-label" style="width:5px;margin-top: 2.5vw;">%</label>
						   	   <label class="layui-form-label" style="margin-top:2.5vw;width:62px;">住院</label>
						  	  <div class="layui-input-inline" style="margin-top: 2.5vw;width:50px;">
						     	 <input type="text" class="layui-input" style="width:50px;"  id="zybfb">
						   	  </div>
						   	   <label class="layui-form-label" style="width:5px;margin-top: 2.5vw;">%</label>
						  </div> 
						   <div class="layui-form-item" style="margin-top:3vw;">
						  	<label class="layui-form-label" style="width:278px;margin-left: 3vw;">收费标准(含药费)/元：全身显像</label>
						  	  <div class="layui-input-inline" style="width:80px;">
						       <input type="text" class="layui-input" style="width:80px;"value="0" id="qsxx">
						      </div>
						  	<label class="layui-form-label"style="width:150px;">体部(双部位)显像</label>
						  	  <div class="layui-input-inline" style="width:80px;" >
						       <input type="text" class="layui-input"style="width:80px;" value="0" id="sbwxx">
						      </div>
						     
						  </div> 
						   <div class="layui-form-item">
						  	  <label class="layui-form-label"style="width:190px;">单部位显像</label>
						  	  <div class="layui-input-inline" >
						       <input type="text" class="layui-input" value="0" id="dbwxx">
						      </div>
						  </div> 
					    </div>
					  </div>
					</div>
					  
					</form> 
				<script src="../js/basis.js"></script>
</html>