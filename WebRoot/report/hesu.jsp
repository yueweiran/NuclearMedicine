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
					    <h2 class="layui-colla-title">核素治疗<i class="layui-icon layui-colla-icon"></i></h2>
					    <div class="layui-colla-content layui-show">
						  
						     <div class="layui-form-item">
							    <label class="layui-form-label" style="width:158px;">全年科室门诊总数：</label>
							    <div class="layui-input-inline">
							      <input type="text" name="ksnum" verification="true" class="layui-input verif"> 
							    </div>
							    <label class="layui-form-label"style="width:158px;">全年核素治疗总人数</label>
							    <div class="layui-input-inline">
							      <input type="text" name="hsnum" verification="true"  class="layui-input verif"> 
							    </div>
						    </div>
						     <div class="layui-form-item">
							    <label class="layui-form-label"style="width:158px;">核素治疗留观床位数：</label>
							    <div class="layui-input-inline">
							      <input type="text" name="lgcwnum" verification="true"  class="layui-input verif"> 
							    </div>
							    <label class="layui-form-label"style="width:158px;">核素治疗专用病床数</label>
							    <div class="layui-input-inline">
							      <input type="text" name="lszlnum" verification="true"  class="layui-input verif"> 
							    </div>
						    </div>
						   <hr>
						    <div class="layui-form-item" style="position: relative;height:5vw;">
						   	<label class="layui-form-label" style="width:150px;position: absolute;top:0px;left:0vw;">核素治疗开展情况:</label>
						  	<label class="layui-form-label" style="width:138px;margin-left: 3vw;margin-top:3vw;">项目名称</label>
						  	  <div class="layui-input-inline" style="margin-top: 3vw;">
						       <select name="xmname" tstitle="请选择项目名称"  verification="true" class="verif">
						        <option value=""></option>
						        <option value="0">131I治疗格雷夫斯甲亢</option>
						        <option value="1">131I治疗自主功能性甲状腺结节</option>
						        <option value="2">131I治疗非毒性甲状腺肿</option>
						        <option value="3">131I治疗分化型甲状腺癌</option>
						        <option value="4">32P治疗真性红细胞增多症</option>
						        <option value="5">32P治疗原发性血小板增多症</option>
						        <option value="6">89Sr治疗骨肿瘤</option>
						        <option value="7">153Sm治疗骨肿瘤</option>
						        <option value="8">188Re治疗骨肿瘤</option>
						        <option value="9">其他核素治疗骨肿瘤</option>
						        <option value="10">32P胶体腔内介入放射性核素治疗</option>
						        <option value="11">动脉内介入放射性核素治疗</option>
						        <option value="12">99Tc-MDP治疗类风湿性关节炎(云克)</option>
						        <option value="13">131I-MIBG治疗</option>
						        <option value="14">90Sr/90Y敷贴器治疗</option>
						        <option value="15">32P敷贴治疗</option>
						        <option value="16">放射性药物注入发组织间治疗</option>
						        <option value="17">放射性粒子植入发组织间治疗</option>
						        <option value="18">90Sr/90Y治疗前列腺增生症</option>
						      </select>
						      </div>
						  	<label class="layui-form-label" style="margin-top:3vw;">治疗人数</label>
						  	 <div class="layui-input-inline"style="margin-top:3vw;">
							      <input type="text" name="zlnum" verification="true"  class="layui-input verif"> 
							  </div>
							  <div class="layui-input-inline" style="width:50px;margin-top:3.2vw;">
						      	<div class="adddiv">添加</div>
						     </div>
						  </div> 
						   <div class="layui-form-item"style="margin-top:3.5vw;">
						    	<table class="layui-table" id="test">
								  <thead>
								    <tr>
								      <th></th>
								      <th>核素治疗项目名称</th>
								      <th>治疗人数</th>
								      <th>操作</th>
								    </tr>
								    <tr>
								     <td><div class="layui-unselect  layui-form-checkbox" style="margin-top: 0px;margin-left: 16px;" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div></td>
								      <td>131I治疗分化型甲状腺癌</td>
								      <td >5</td>
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