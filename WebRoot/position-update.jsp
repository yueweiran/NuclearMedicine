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
    <title>修改部门信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />

    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
        <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="./layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  
  <body>
    <div class="x-body layui-anim layui-anim-up">

<form class="layui-form">

        <div class="layui-form-item">
              <label for="L_username" class="layui-form-label">
                  <span class="x-red">*</span>部门名称
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="positionName" name="positionName" required="" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>

          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
               <button  class="layui-btn" lay-submit="" lay-filter="update" >
                修改
              </button>
          </div>
</form>  
    </div>
    <script>
    var v = parseUrl();//解析所有参数
    var code = v['mId'] ;//就是你要的结果	
    var wktype=v['wktype'];
	$(function(){
	     $.ajax({
				url:"<%=basePath%>/checkWork/position/getPositionByCode",
				data: {'positionCode':code},
				dataType:"json",
				type:"post",
				success:function(result){
					if(result.success ==true){
						var position = result.data;
						var work_name = position.work_name;
						// 设置值
						$("#positionName").val(work_name);
						
					}
				}
			});
	});

    function parseUrl(){
                    var url=location.href;
                    var i=url.indexOf('?');
                    if(i==-1)return;
                    var querystr=url.substr(i+1);
                    var arr1=querystr.split('&');
                    var arr2=new Object();
                    for  (i in arr1){
                        var ta=arr1[i].split('=');
                        arr2[ta[0]]=ta[1];
                    }
                    return arr2;
                }
	function dateFormatter(value) {
	    date = new Date(value);
	    var yy = date.getFullYear();
	    var MM = date.getMonth() + 1;
	    var dd = date.getDate();
	    var hh = date.getHours();
	    var mm = date.getMinutes();
	    var ss = date.getSeconds();
	    if (MM < 10)
	        MM = "0" + MM;
	    if (dd < 10)
	        dd = "0" + dd;
	    if (hh < 10)
	        hh = "0" + hh;
	    if (mm < 10)
	        mm = "0" + mm;
	    if (ss < 10)
	        ss = "0" + ss;					 
	    return "" + yy + "-" + MM + "-" + dd + " " + hh + ":" + mm + ":" + ss;
	}

    layui.use('form', function(){
		var form = layui.form;

	
		
    	form.on('submit(update)', function(data){
		var positionName = $("#positionName").val();
		if(positionName!=""){
			$.ajax({
				url:"<%=basePath%>/checkWork/position/updatePosition",
				data: {'positionCode':code,
						'positionName':positionName,
						'wktype':wktype
					   },
				dataType:"json",
				type:"post",
				success:function(result){
					if(result.success ==true){					
						var index=parent.layer.getFrameIndex(window.name); //获取当前窗口的name
						parent.layer.close(index);
						parent.tools1.func1();
					}else{
						layer.msg("修改失败!");
					}
				}
			});
		}else{
			layer.msg("请填写岗位信息！");
		}
    	return false;
	});
});
    </script>

	<script>
	layui.use('laydate', function(){
		  var laydate = layui.laydate;
	
		  //生产日期
		  laydate.render({
		    elem: '#prouductDate'
		    ,type: 'datetime'
		  });
		  
		  //保质期
		  laydate.render({
		    elem: '#saveDate'
		    ,type: 'datetime'
		  });
	});
	</script>


  </body>

</html>