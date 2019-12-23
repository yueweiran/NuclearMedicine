<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>管理系统</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="./css/font.css">
	<link rel="stylesheet" href="./css/xadmin.css">
    <link rel="stylesheet" href="./layer/theme/default/layer.css">
        <script type="text/javascript" src="js/jquery.js"></script>
            <script type="text/javascript" src="js/jquery.cookie.js"></script>
 <script type="text/javascript" src="./layer/layer.js"></script>
    <script type="text/javascript" src="./layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
 
</head>
<body>
    <!-- 顶部开始 -->
    <div class="container">
        <div class="logo"><a href="./index.jsp">管理系统</a></div>
        <div class="left_open">
            <i title="展开左侧栏" class="iconfont">&#xe699;</i>
        </div>

        <ul class="layui-nav right" lay-filter="">
          <li class="layui-nav-item">
            <a href="javascript:;" ><span id="stName"></span></a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
              <dd><a onclick="tuichu()">退出</a></dd>
            </dl>
          </li>
         
        </ul>
        
    </div>
    <!-- 顶部结束 -->
    <!-- 中部开始 -->
     <!-- 左侧菜单开始 -->
    <div class="left-nav">
      <div id="side-nav">
        <ul id="nav">

			    <!-- 一级菜单(1) -->
			 <li id="li1">
                <a _href="report/report.jsp">
                    <i class="layui-icon">&#xe770;</i>
                    <cite>填报</cite>

                </a>
            </li>
            <li id="li1">
                <a _href="department.jsp">
                    <i class="iconfont">&#xe723;</i>
                    <cite>部门管理</cite>

                </a>
            </li>
			
        </ul>
      </div>
    </div>
    <!-- <div class="x-slide_left"></div> -->
    <!-- 左侧菜单结束 -->
    <!-- 右侧主体开始 -->
    <div class="page-content">
        <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
          <ul class="layui-tab-title">
            <li class="home"><i class="layui-icon">&#xe68e;</i>我的桌面</li>
          </ul>
          <div class="layui-tab-content">
            <div class="layui-tab-item layui-show" id="divWel">
                
            </div>
          </div>
        </div>
    </div>
    <div class="page-content-bg"></div>
    <!-- 右侧主体结束 -->
    <!-- 中部结束 -->
    <!-- 底部开始 -->

    <!-- 底部结束 -->
    <script>


    function tuichu(){

    //	$.cookie('stId', null);
    //	$.cookie('stName', null);
    //	$.cookie('stDepartment', null);
    //	$.cookie('stGrade', null);
    	
    	window.location.href="login.jsp";
    }

		$(function(){
			var stId = $.cookie('stId');
			var stName = $.cookie('stName');
			var stDepartment = $.cookie('stDepartment');
			var stGrade = $.cookie('stGrade');
			var stAreaId = $.cookie('stAreaId');
			var stAreaStr = $.cookie('stAreaStr');
			if (stId == null) {
				window.location.href="login.jsp";				
			} else {
				$("#stName").text(stName);
				$("#div5").empty();
				$("#divWel").empty();
				var str2 ='<iframe src="./welcome2.jsp" frameborder="0" scrolling="yes" class="x-iframe"></iframe>';
			
				$("#divWel").append(str2);

			
				
			}

			
			
		});


		
    </script>
</body>
</html>