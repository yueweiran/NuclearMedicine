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
    <style>
    .layui-form-label{
    	width:94px;
    }
    .time1{
    	display: none;
    }
    .time2{
    	display: none;
    }
    .isshow{
    	display: block;
    }
    </style>
  </head>
  
  <body>
    <div class="x-body layui-anim layui-anim-up">

<form class="layui-form" style="width:70%;margin-left:auto;margin-right:auto;">

		<div class="layui-form-item">
              <label for="L_username" class="layui-form-label">
                 	打卡次数
              </label>
              <div class="layui-input-inline" >
                  <select id="worktype" style="height:38px;">
                 	<option >请选择</option>
					<option value="1">2</option>
					<option value="2">4</option>
				  </select>
              </div>
          </div>
		<div class="time1">
		
		
		<div class="layui-form-item">
              <label for="L_username" class="layui-form-label">
                  <span class="x-red">*</span>当日上班时间
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="todaystarttime" name="todaystarttime" 
                  autocomplete="off" class="layui-input">
              </div>
          </div>
 		<div class="layui-form-item">
              <label for="L_username" class="layui-form-label">
                  <span class="x-red">*</span>次日下班时间
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="nextendtime" name="nextendtime" required="" 
                  autocomplete="off" class="layui-input">
              </div>
          </div>
		</div>
		<div class="time2">
			<div class="layui-form-item">
              <label for="L_username" class="layui-form-label">
                  <span class="x-red">*</span>上午上班时间
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="amstarttime" name="amstarttime" required="" 
                  autocomplete="off" class="layui-input">
              </div>
          </div>
 		<div class="layui-form-item">
              <label for="L_username" class="layui-form-label">
                  <span class="x-red">*</span>上午下班时间
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="amendtime" name="amendtime" required="" 
                  autocomplete="off" class="layui-input">
              </div>
          </div>
 		<div class="layui-form-item">
              <label for="L_username" class="layui-form-label">
                  <span class="x-red">*</span>下午上班时间
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="pmstarttime" name="pmstarttime" required="" 
                  autocomplete="off" class="layui-input">
              </div>
          </div>
 		<div class="layui-form-item">
              <label for="L_username" class="layui-form-label">
                  <span class="x-red">*</span>下午下班时间
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="pmendtime" name="pmendtime" required="" 
                  autocomplete="off" class="layui-input">
              </div>
          </div>
		</div>
          <div class="layui-form-item">
          	  <input type="hidden" id="dakanum"/>
              <label for="L_repass" class="layui-form-label">
              </label>
               <button  class="layui-btn" lay-submit="" lay-filter="addorupdate" >
               <input type="hidden" id="btnval" cwid="" doset="" value=""/>
                确定
              </button>
          </div>
</form>  
    </div>
    <script>

	layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  
	  //执行一个laydate实例
	  laydate.render({ 
		  elem: '#amstarttime'
		  ,type: 'time'
		});
	  laydate.render({ 
		  elem: '#amendtime'
		  ,type: 'time'
		});
	  laydate.render({ 
		  elem: '#pmstarttime'
		  ,type: 'time'
		});
	  laydate.render({ 
		  elem: '#pmendtime'
		  ,type: 'time'
		});
	  laydate.render({ 
		  elem: '#todaystarttime'
		  ,type: 'time'
		});
	  laydate.render({ 
		  elem: '#nextendtime'
		  ,type: 'time'
		});
	});
	
	$(function(){
		 var form = layui.form;
		
	     var v = parseUrl();//解析所有参数
	     var mId = v['mId'] ;//就是你要的结果	 
	     $.ajax({
				url:"<%=basePath%>/checkWork/worktime/getWtByCode",
				data: {'workcode':mId},
				dataType:"json",
				type:"post",
				success:function(result){
					console.log(result);
					if(result.errorcode == 200 && result.success==true){
						var wt = result.data;

						// 获取值
						var wtid = wt.id;
						var amstarttime = wt.amstarttime;
						var worktype=wt.work_type;
						
						if(worktype==1){
							
							
							$(".time1").addClass("isshow");
			            	$(".time2").removeClass("isshow");
							// 设置值
							$("#todaystarttime").val(amstarttime);
					
							var pmendtime = wt.pmendtime;
							// 设置值
							$("#nextendtime").val(pmendtime);
							$("#worktype").val(1);
						}else if(worktype==2){
							$(".time2").addClass("isshow");
			            	$(".time1").removeClass("isshow");
							// 设置值
							$("#amstarttime").val(amstarttime);
							
							var amendtime = wt.amendtime;
							// 设置值
							$("#amendtime").val(amendtime);
							var pmstarttime = wt.pmstarttime;
							// 设置值
							$("#pmstarttime").val(pmstarttime);
							
							var pmendtime = wt.pmendtime;
							// 设置值
							$("#pmendtime").val(pmendtime);
							$("#worktype").val(2);
							
						}else{
							$(".time1").removeClass("isshow");
			            	$(".time2").removeClass("isshow");
						}
						$("#btnval").attr("doset","updateWt");
						$("#btnval").attr("cwid",wtid);
					}else if(result.errorcode == 200 && result.success==false){
						$("#btnval").attr("doset","addWt");
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
		var form = layui.form, layer = layui.layer;
		// 监听
		$(document).ready(function() {       
		    // select下拉框选中触发事件
		    form.on("select", function(data){
		            var seltypeval=data.value; // 获取选中的值
		            $("#dakanum").val(seltypeval);
		            if(seltypeval==1){
		            	$(".time1").addClass("isshow");
		            	$(".time2").removeClass("isshow");
		            }else if(seltypeval==2){
		            	$(".time2").addClass("isshow");
		            	$(".time1").removeClass("isshow");
		            }else{
		            	$(".time1").removeClass("isshow");
		            	$(".time2").removeClass("isshow");
		            }
		            
		    });
		 
		});
	
		
    	form.on('submit(addorupdate)', function(data){
	    var v = parseUrl();//解析所有参数
	    var wkcode = v['mId'] ;//就是你要的结果
		var amstarttime = $("#amstarttime").val();
		var amendtime = $("#amendtime").val();
		var pmstarttime = $("#pmstarttime").val();
		var pmendtime = $("#pmendtime").val();
		var adorup=$("#btnval").attr("doset");
		var wtid=$("#btnval").attr("cwid");
		
		var dakanum=$("#dakanum").val();
		var turl='<%=basePath%>checkWork/worktime/';
		if(dakanum!=""){
			if(dakanum==1){
				amstarttime=$("#todaystarttime").val();
				pmendtime=$("#nextendtime").val();
				$.ajax({
					url:turl+adorup,
					data: {'wtid':wtid,'wkcode':wkcode,
							'amstarttime':amstarttime,
							'amendtime':"",
							'pmstarttime':"",
							'pmendtime':pmendtime,
							'worktype':dakanum
						   },
					dataType:"json",
					type:"post",
					success:function(result){
						if(result.errorcode == 200 && result.success==true){					
							var index=parent.layer.getFrameIndex(window.name); //获取当前窗口的name
							parent.layer.close(index);
							parent.timtools.func();
						} 
					}
				});
		    	return false;
			}
			if(dakanum==2){
				
				$.ajax({
					url:turl+adorup,
					data: {'wtid':wtid,'wkcode':wkcode,
							'amstarttime':amstarttime,
							'amendtime':amendtime,
							'pmstarttime':pmstarttime,
							'pmendtime':pmendtime,
							'worktype':dakanum
						   },
					dataType:"json",
					type:"post",
					success:function(result){
						if(result.errorcode == 200 && result.success==true){					
							var index=parent.layer.getFrameIndex(window.name); //获取当前窗口的name
							parent.layer.close(index);
							parent.timtools.func();
						} 
					}
				});
		    	return false;
			
			}

		}
		
		
		
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