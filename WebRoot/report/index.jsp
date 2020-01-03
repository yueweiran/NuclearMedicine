<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>全国核医学普查信息填报系统</title>

   <link rel="stylesheet" href="./layui/css/layui.css">
    <script type="text/javascript" src="../js/jquery-1.10.2.js"></script>
       <script src="./layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
       <script type="text/javascript" src="../js/xadmin.js"></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo" style="width:260px;">全国核医学普查信息填报系统</div>
   
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <!-- <img src="http://t.cn/RCzsdCq" class="layui-nav-img"> -->
         <span id="stName"></span>
        </a>
        <dl class="layui-nav-child">
          <dd><a onclick="tuichu()">退出</a></dd>
        </dl>
      </li>
      <!-- <li class="layui-nav-item"><a onclick="tuichu()">退出</a></li> -->
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black ">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul id="leftmenu" class="layui-nav layui-nav-tree"  lay-filter="test">
       
       <!--  <li class="layui-nav-item">
          <a href="javascript:;">解决方案</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">列表一</a></dd>
            <dd><a href="javascript:;">列表二</a></dd>
            <dd><a href="">超链接</a></dd>
          </dl>
        </li> -->
        <li class="layui-nav-item layui-this">
        <a _href="report.jsp">  
	        <i class="layui-icon">&#xe63c;</i>
	        <cite>信息填报</cite>
        </a>
        </li>
         <li class="layui-nav-item">
        <a _href="">  
	        <i class="layui-icon">&#xe770;</i>
	        <cite>人员管理</cite>
        </a>
        </li>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="width: 100%;height:100%">
		<iframe id="iframebody"  frameborder="0" style="width: 100%;height:100%" src="" scrolling="yes" class="x-iframe"></iframe>
	</div>
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © layui.com - 底部固定区域
  </div>
</div>

<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
  
});
function tuichu(){
	$.cookie('stId',null);
	$.cookie('stName',null);
    	
    	window.location.href="login.jsp";
    }
$(function(){
	var stId = $.cookie('stId');
	var stName = $.cookie('stName');
	
	if (stId == null) {
		window.location.href="login.jsp";				
	}else{
		$("#stName").text(stName);
		 var url = $('#leftmenu li:first').children('a').attr('_href');
		$("#iframebody").attr("src",url);
	}

	 $('#leftmenu li').click(function (event) {
		  var url = $(this).children('a').attr('_href');
		  $("#iframebody").attr("src",url);
	 });
	
});
</script>
</body>

</html>