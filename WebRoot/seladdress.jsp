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
    <title>欢迎页面-X-admin2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <link rel="stylesheet" href="./layer/theme/default/layer.css">
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="./layer/layer.js"></script>
    <script type="text/javascript" src="./layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/jsp5shiv/r29/jsp5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
    .layui-btn{
    	float:left;
    }
    .layui-btn+.layui-btn{
    	margin-left:5px;
    }
    .layui-btn{
    	background-color: transparent;
    	color:#428bca;
    }
    #addressdiv a:hover{
    	color:red;
    }
    .addtext{
    	margin-right:8px;
    	color:#428bca;
    	cursor:printer;
    	cursor: pointer;
    }
    .noCon{
	width: 25%;
    height: 48%;
    background-image: url(./images/noCon.png);
    background-size: 100% 100%;
    position: absolute;
    top: 20px;
    left: 38%;
    }
    </style>
  </head>
  
  <body class="layui-anim layui-anim-up">
    <div class="x-body">

       <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
        <input type="hidden" id="areaval" value=""/>
        	<i id="area">已选地区：</i>
        </form>
      </div> 

      <xblock>
       
      </xblock>
 	<div id="addressdiv" style="overflow:auto;width:100%;height:100%;position: relative;">
			
    </div>
    </div>
    <script>
 
	$(function(){
		var areavalstr="";
		var parentaddId=parent.$('#addrtext').val();
		
		 if(parentaddId!=""){
			 var endaddrId,endaddrStr;
			 var endaddcon="";
			 if(parentaddId.indexOf(";")>0){
				 $("#areaval").val(parentaddId+";");
				 parent.$('#address').data('addressId',$("#areaval").val());
				 var addstr=parentaddId.split(";");
				 for(var i=0;i<addstr.length;i++){
					 var adstr=addstr[i].split(":");
					 $("#area").append('<i class="addtext" addr="'+adstr[0]+'" >'+adstr[1]+'</i>');
				 }
				 endaddrStr=addstr[addstr.length-1].split(":");
				 endaddrId=endaddrStr[0];
				 endaddcon=endaddrStr[1];
			 }else{
				 endaddrStr=parentaddId.split(":");
				 endaddrId=endaddrStr[0];
				 endaddcon=endaddrStr[1];
				 $("#area").append('<i class="addtext" addr="'+endaddrId+'" >'+endaddcon+'</i>');
				 $("#areaval").val(parentaddId+";");
				 parent.$('#address').data('addressId',$("#areaval").val());
			 }
			getAddress(endaddrId);
			$(".addtext").click(function(){
				areavalstr="";
				var thisadd=$(this).index();
				var adds=$(".addtext");
				for(var i=0;i<adds.length;i++){
					if(i>thisadd){
						$(adds[i]).remove();
					}else{
						areavalstr+=$(adds[i]).attr("addr")+":"+$(adds[i]).text()+";";
					}
				}
				var addid=$(this).attr("addr");
				var areaCon=$(this).text();
				$("#areaval").val(areavalstr);
				parent.$('#address').data('addressId',$("#areaval").val());
				getAddress(addid);
			});
		}else{
			//默认加载一级地区
			var departid=0;
			getAddress(departid);
		} 
	
		
		
		function getAddress(departid){
			$.ajax({
				url:"<%=basePath%>/checkWork/yuArea/selAddress",
				data:{"areaId":departid},
				dataType:"json",
				type:"post",
				success:function(result){
						var medicine = result.data;
						var obj = eval(medicine); 
						var str="";
					
						if(!$.isEmptyObject(obj)){
							for(var i=0;i<obj.length;i++){
								str+='<a id="'+obj[i].id+'" class="addressclass layui-btn" >'+obj[i].name+'</a>';
							}
							$("#addressdiv").html("");
							$("#addressdiv").append(str);
							
							$(".addressclass").click(function(){
								areavalstr=$("#areaval").val();
								var areaId=$(this).attr("id");
								var areaCon=$(this).text();
								/* var isnull=false;
								var adds=$(".addtext");
								for(var i=0;i<adds.length;i++){
									if($(adds[i]).attr("addr")===areaId){
										isnull=true;
										break;
									}
								}
								if(isnull){
									return;
								} */
								$("#area").append('<i class="addtext" addr="'+areaId+'" >'+areaCon+'</i>');
								areavalstr+=areaId+":"+areaCon+";";
								$("#areaval").val(areavalstr);
								parent.$('#address').data('addressId',$("#areaval").val());
								getAddress(areaId);
							});
							$(".addtext").click(function(){
								areavalstr="";
								var thisadd=$(this).index();
								var adds=$(".addtext");
								for(var i=0;i<adds.length;i++){
									if(i>thisadd){
										$(adds[i]).remove();
									}else{
										areavalstr+=$(adds[i]).attr("addr")+":"+$(adds[i]).text()+";";
									}
								}
								var addid=$(this).attr("addr");
								var areaCon=$(this).text();
								$("#areaval").val(areavalstr);
								parent.$('#address').data('addressId',$("#areaval").val());
								getAddress(addid);
							});
						
						}else{
							var str='<div class="noCon"></div>';
							$("#addressdiv").html(str);
						}
				}
			});
		}
	});
    </script>
    
  </body>

</html>