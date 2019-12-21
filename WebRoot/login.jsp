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
	<title>登录</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="./css/font.css">
	<link rel="stylesheet" href="./css/xadmin.css">
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.cookie.js"></script>
    <script src="./layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>

</head>
<body class="login-bg">
    
    <div class="login layui-anim layui-anim-up">
        <div class="message">管理登录</div>
        <div id="darkbannerwrap"></div>
        
        <form method="post" class="layui-form" >
            <input name="username" placeholder="请输入账号" id="userName"  type="text" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input name="password" lay-verify="required" id="userPwd"  placeholder="请输入密码"  type="password" class="layui-input">
            <hr class="hr15">
            <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
            <hr class="hr20" >
        </form>
    </div>

    <script>

    layui.use('form', function(){
		var form = layui.form;
    	form.on('submit(login)', function(data){


    	var userName = $("#userName").val();
    	var userPwd = $("#userPwd").val();
        	
		$.ajax({
			url:"<%=basePath%>checkWork/server/doLogin",
			data: {"userName":userName,"userPwd":userPwd},
			dataType:"json",
			type:"post",
			success:function(result){
				if (result.code == 1) {
					var mapList = result.data;
					var stDepartment = mapList.stDepartment;	
					var stGrade = mapList.stGrade;	
					var stId = mapList.stId;	
					
					var stName = mapList.stName;	
					var stAreaId=mapList.stAreaId;
					var stAreaStr=mapList.stAreaStr;


					$.cookie('stDepartment', stDepartment, { expires: 1 });
					$.cookie('stGrade', stGrade, { expires: 1 });
					$.cookie('stId', stId, { expires: 1 });
					$.cookie('stName', stName, { expires: 1 });
					$.cookie('stAreaId', stName, { expires: 1 });
					$.cookie('stAreaStr', stAreaStr, { expires: 1});

					window.location.href="<%=basePath%>/index.jsp";
				} else if (result.code == 2) {
					alert("账号或密码错误,请重新登录 ");
					window.location.href="login.jsp";
				} else if (result.code == 3) {
					alert("对不起,服务器发生异常,请重新登录");
					window.location.href="login.jsp";
				}
			}
		});
		return false;
	});
});

        
    </script>

    
</body>
</html>