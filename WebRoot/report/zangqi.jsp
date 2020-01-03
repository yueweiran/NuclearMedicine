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
  
<form id="formThree" class="layui-form" action="">
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
						    <label class="layui-form-label"style="width:140px;">开始使用日期</label>
						    <div class="layui-input-inline">
						      <input type="text" class="layui-input" id="startdate">
						    </div>
						     <div class="layui-input-inline">
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
					</div>
					  
					</form>
				<script src="../js/basis.js"></script> 
</html>