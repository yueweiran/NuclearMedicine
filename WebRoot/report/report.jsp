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
    <title>全国核医学普查信息填报系统</title>
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
	width:116px;
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
 .layui-table td, .layui-table th{
    	text-align: center;
    }

</style>
  </head>
  
  <body class="layui-anim layui-anim-up">
  
    <div class="x-body">
	   <div class="box">
		    <div class="eis-stepContents">
		        <div class="eis-stepContent ">
		        	<div class="fmcontent">
		        		
					<form id="formOne" class="layui-form" action="" >
					<div class="layui-collapse">
					  <div class="layui-colla-item">
					    <h2 class="layui-colla-title">单位信息</h2>
					    <div class="layui-colla-content layui-show">
							<div class="layui-form-item">
						    <label class="layui-form-label">医院名称</label>
						    <div class="layui-input-inline">
						      <input type="text" name="hospital" verification="true"  tstitle="请输入医院名称"  placeholder="请输入医院名称"  class="layui-input verif">
						    </div>
						
						    <label class="layui-form-label">所属省市</label>
						    <div class="layui-input-inline">
						      <select name="city" tstitle="请选择省市" verification="true" class="verif" >
						        <option value=""></option>
						        <option value="0">安徽省</option>
						        <option value="1">北京市</option>
						        <option value="2">福建省</option>
						        <option value="3">广西壮族自治区</option>
						        <option value="4">贵州省</option>
						        <option value="5">海南省</option>
						        <option value="6">河北省</option>
						        <option value="7">河南省</option>
						        <option value="8">黑龙江省</option>
						        <option value="9">湖北省</option>
						        <option value="10">湖南省</option>
						        <option value="11">吉林省</option>
						        <option value="12">江西省</option>
						        <option value="13">辽宁省</option>
						        <option value="14">内蒙古自治区</option>
						        <option value="15">宁夏回族自治区</option>
						        <option value="16">山东省</option>
						        <option value="17">山西省</option>
						        <option value="18">上海市</option>
						        <option value="19">新疆维吾尔自治区</option>
						        <option value="20">云南省</option>
						        <option value="21">浙江省</option>
						        <option value="22">重庆市</option>
						        <option value="23">甘肃省</option>
						        <option value="24">四川省</option>
						        <option value="25">广东省</option>
						        <option value="26">天津市</option>
						        <option value="27">江苏省</option>
						        <option value="28">陕西省</option>
						        <option value="29">青海省</option>
						        <option value="30">西藏藏族自治区</option>
						      </select>
						    </div>
						 
						  </div>
							<div class="layui-form-item">
						   
						      <label class="layui-form-label">医院等级</label>
						    <div class="layui-input-inline">
						       <select name="levelfilter" tstitle="请选择医院等级" verification="true" class="verif"   lay-filter="levelfilter">
						        <option value=""></option>
						        <option value="0">二级</option>
						        <option value="1">三级</option>
						      </select>
						    </div>
						     <div class="layui-input-inline"  style="margin-left:5px;">
						       <select id="levelcon" tstitle="请选择等级" verification="true" class="verif"   name="levelconfilter" lay-filter="levelconfilter"> </select>
						    </div>
						     
						  </div>
						  	<div class="layui-form-item">
						   
						    <label class="layui-form-label">单位性质</label>
						    <div class="layui-input-inline">
						       <select name="unitproperty" tstitle="请选择单位性质" verification="true" class="verif">
						        <option value=""></option>
						        <option value="0">地方</option>
						        <option value="1">军队</option>
						      </select>
						    </div>
						    <div class="layui-input-inline">
						        <input type="checkbox"  name="hospitalized" style="margin-left: 10px;margin-top:1px" title="是否住院医师规范化培训基地" value="是否住院医师规范化培训基地">
						    </div>
						     
						  </div>
						  <div class="layui-form-item">
						   
						    <label class="layui-form-label">科室名称</label>
						    <div class="layui-input-inline">
						       <select name="department" tstitle="请选择科室名称" verification="true" class="verif">
						        <option value=""></option>
						        <option value="0">核医学科</option>
						        <option value="1">独立的PET/CT中心</option>
						        <option value="2">ECT室</option>
						        <option value="3">甲亢专科</option>
						        <option value="4">同位素室</option>
						        <option value="5">医学影像科</option>
						        <option value="6">放疗中心</option>
						        <option value="7">放射科</option>
						        <option value="8">放免室</option>
						        <option value="9">其他</option>
						      </select>
						    </div>
						    <label class="layui-form-label" style="width:150px;">建科时间（年份）</label>
						    <div class="layui-input-inline">
						      <input type="text" name="year" verification="true" tstitle="请输入建科时间"   placeholder="请输入建科时间"  class="layui-input verif"> 
						    </div>
						     <label class="layui-form-label" style="width:5px;">年</label>
						  </div>
						    <div class="layui-form-item">
						   
						    <label class="layui-form-label">行政隶属</label>
						    <div class="layui-input-inline">
						       <select name="administrative"  tstitle="请选择行政隶属" verification="true" class="verif">
						        <option value=""></option>
						        <option value="0">核医学科</option>
						        <option value="1">医学影像科</option>
						        <option value="2">放射科</option>
						        <option value="3">独立的PET/CT中心</option>
						        <option value="4">甲亢专科</option>
						        <option value="5">检验科</option>
						        <option value="6">内分泌科</option>
						        <option value="7">ECT室</option>
						        <option value="8">同位素室</option>
						        <option value="9">放疗中心</option>
						        <option value="10">放免中心</option>
						        <option value="10">其他</option>
						      </select>
						    </div>
						    <label class="layui-form-label" >负责人</label>
						    <div class="layui-input-inline">
						      <input type="text" name="director" verification="true" tstitle="请输入负责人"   placeholder="请输入负责人"  class="layui-input verif"> 
						    </div>
						  </div>
						   <div class="layui-form-item">
						   
						    <label class="layui-form-label">职称</label>
						    <div class="layui-input-inline">
						       <select name="titles" tstitle="请选择职称" verification="true" class="verif">
						        <option value=""></option>
						        <option value="0">主任医师</option>
						        <option value="1">副主任医师</option>
						        <option value="2">主治医师</option>
						        <option value="3">住院医师</option>
						        <option value="4">主任技师</option>
						        <option value="5">副主任技师</option>
						        <option value="6">主管技师</option>
						        <option value="7">技师</option>
						        <option value="8">高级工程师</option>
						        <option value="9">工程师</option>
						        <option value="10">教授</option>
						        <option value="10">副教授</option>
						        <option value="11">其他</option>
						      </select>
						    </div>
						    <label class="layui-form-label" >学历</label>
						    <div class="layui-input-inline">
						       <select name="education" tstitle="请选择学历" verification="true" class="verif">
						        <option value=""></option>
						        <option value="0">博士</option>
						        <option value="1">硕士</option>
						        <option value="2">本科</option>
						        <option value="3">专科</option>
						      </select>
						    </div>
						  </div>
						  <div class="layui-form-item">
						   
						    <label class="layui-form-label">座机号码</label>
						    <div class="layui-input-inline">
						      <input type="text" name="phonenumber" verification="true" tstitle="请输入座机号码"  placeholder="请输入座机号码"  class="layui-input verif"> 
						    </div>
						    <label class="layui-form-label" >手机号码</label>
						      <div class="layui-input-inline">
						      <input type="text" name="mobilphonenumber" verification="true" tstitle="请输入手机号码"   placeholder="请输入手机号码"  class="layui-input verif"> 
						    </div>
						  </div>
						   <div class="layui-form-item">
						     <label class="layui-form-label" >电子邮箱</label>
						      <div class="layui-input-inline">
						      <input type="text" name="email" verification="true" tstitle="请输入电子邮箱"   placeholder="请输入电子邮箱"  class="layui-input verif"> 
						    </div>
						      <label class="layui-form-label">通信地址</label>
						    <div class="layui-input-inline">
						      <input type="text" name="address" verification="true" tstitle="请输入通信地址"  placeholder="请输入通信地址"  class="layui-input verif"> 
						    </div>
						  
						  </div>
						    <label class="layui-form-label">邮编</label>
						    <div class="layui-input-inline">
						      <input type="text" name="postcode" verification="true"  tstitle="请输入邮编" placeholder="请输入邮编"  class="layui-input verif"> 
						    </div>					
						   </div>
					  </div>
					  <div class="layui-colla-item">
					    <h2 class="layui-colla-title">请选择要开展的业务</h2>
					    <div class="layui-colla-content layui-show">
					     <div class="layui-form-item">
						    <div  class="layui-input-block" style="margin-left: 50px;">
						      <input type="checkbox" name="business"  title="单光子显像" value="单光子显像" lay-filter="ywfilter">
						      <input type="checkbox" name="business"  title="正电子显像" value="正电子显像" lay-filter="ywfilter">
						      <input type="checkbox" name="business"  title="符合线路显像" value="符合线路显像" lay-filter="ywfilter">
						      <input type="checkbox" name="business"  title="脏器功能测定" value="脏器功能测定" lay-filter="ywfilter">
						      <input type="checkbox" name="business"  title="放射免疫分析" value="放射免疫分析" lay-filter="ywfilter">
						      <input type="checkbox" name="business"  title="化学发光分析" value="化学发光分析" lay-filter="ywfilter">
						      <input type="checkbox" name="business"  title="门诊" value="门诊" lay-filter="ywfilter">
						      <input type="checkbox" name="business"  title="核素治疗" value="核素治疗"lay-filter="ywfilter">
						      <input type="checkbox" name="business"  title="病房" value="病房" lay-filter="ywfilter">
						      <input type="checkbox" name="business"  title="科研实验室" value="科研实验室" lay-filter="ywfilter">
						      <input type="checkbox" name="business"  title="其他" value="其他" lay-filter="ywfilter">
						    </div>
						  </div>
					    </div>
					  </div>
					
					</div>
					  
					</form>
		        	</div>
		        </div>
		        <div class="eis-stepContent">
		        <div class="layui-row">
			    <div class="layui-col-md2">
			     <div class="step-content">
			     <ul class="step-ul"></ul>
			     </div>
			    </div>
			    <div class="layui-col-md10">
			      <div class="fmcontent1">
		        		<iframe id="iframebody"  frameborder="0" style="width: 100%;height:100%" src="" scrolling="yes" class="x-iframe"></iframe>
					
		        	</div>
			    </div>
			  </div>
		        </div>
		        <div class="eis-stepContent"><h2>人员信息</h2></div>
		        <div class="eis-stepContent"><h2>教学开展</h2></div>
		        <div class="eis-stepContent"><h2>科研开展</h2></div>
		        <div class="eis-stepContent"><h2>意见与建议</h2></div>
		    </div>
		</div>
    </div>

  </body>
  

<script src="../js/report.js"></script>


</html>