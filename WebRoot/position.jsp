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
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <link rel="stylesheet" href="./layer/theme/default/layer.css">
    <script type="text/javascript" src="js/jquery.js"></script>
     <script type="text/javascript" src="js/jquery.cookie.js"></script>
    <script type="text/javascript" src="./layer/layer.js"></script>
    <script type="text/javascript" src="./layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/jsp5shiv/r29/jsp5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    .layui-btn-xs{
    	line-height: 24px;
    }
    </style>
  </head>
  
  <body class="layui-anim layui-anim-up">

    <div class="x-body">

  

      <xblock>
        <button class="layui-btn" id="addPosition" ><i class="layui-icon"></i>添加</button>
        <span class="x-right" style="line-height:40px" id="dataNumSpan"></span>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
      </xblock>
 <div style="overflow:auto;width:60%">
	<table class="layui-table" width="auto" style="white-space:nowrap">
        <thead>

          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>          
            <th>编号</th>
            <th>职位名称</th>
            <th>操作</th></tr>
        </thead>
        <tbody id="tbody">
				
				<!--  利用Ajax技术循环生成页面	-->
				
		</tbody>
      </table>
     </div>
     

    </div>

    <script>
    
	 var v = parseUrl();//解析所有参数
	    var departId = v['mId'] ;//就是你要的结果
    
    
      layui.use('laydate', function(){
        var laydate = layui.laydate;
        
        //执行一个laydate实例
        laydate.render({
          elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
          elem: '#end' //指定元素
        });
      });


/*部门-修改 */
	function updateMed(code){
		 var _uptools = {
				    func1: function(){
				     reloadPage();
				    }
				  };
		  window.tools1 = _uptools;
		var url = 'position-update.jsp?mId='+code;
		x_admin_show('编辑',url,600,400);
	}
	
	/*部门-设置考勤 */
  	function setWorkTime(mId){
  		var _timetools = {
			    func: function(){
			    	setsuccess();
			    }
			  };
	  window.timtools = _timetools;
  		var url = 'worktime.jsp?mId='+mId;
		x_admin_show('编辑',url,600,500);
  	}

      /*部门-删除*/
      function member_del(obj,code){
          layer.confirm('确认要删除吗？',function(index){
              	//发异步删除数据
				$.ajax({
					url:"<%=basePath%>/checkWork/position/delPositionByCode",
					data:{'positionCode':code},
					dataType:"json",
					async:false,
					type:"post",
					success:function(result){
						if (result.errorcode == 200) {	
							$(obj).parents("tr").remove();
				            layer.msg('已删除!',{icon:1,time:1000});
				            $("#dataNumSpan").text('共有数据：'+$("#tbody tr").length+' 条');
						}
					}
				});	
			
          });
      }


	// 批量删除
  	function delAll (argument) {

	        	var datas = tableCheck.getData();
				if (datas.length != 0) {
					layer.confirm('确认要删除吗？',function(index){
		
		              	//发异步删除数据
						$.ajax({
							url:"<%=basePath%>/checkWork/position/delPositions",
							data:{'datas':datas},
							dataType:"json",
							async:false,
							type:"post",
							success:function(result){
								if (result.errorcode == 200) {	
									layer.closeAll('dialog');
									layer.msg('删除成功', {icon: 1});
									$(".layui-form-checked").not('.header').parents('tr').remove();
									$("#dataNumSpan").text('共有数据：'+$("#tbody tr").length+' 条');
								}
							}
						});	
						
						
					});
				} else {
					layer.confirm('请勾选要删除的选项！');
				}
		
	}
	
  	//工作内容设置
    
  	function setWork(mId){
  		var _wktools = {
			    func: function(){
			    	setsuccess();
			    }
			  };
	  window.wktools = _wktools;
  		var url = 'question.jsp?mId='+departId+'&postionCode='+mId;
		x_admin_show('工作类型设置',url);
  	}
  	
  	
  	function setsuccess(){
		layer.msg("设置成功！");
	}
	
	
	$("#addPosition").click(function(){
	
			 var _tools = {
					    func1: function(){
					    	if(departId!=""){
								$.ajax({
									url:"<%=basePath%>/checkWork/position/getAllPositionByDepartId",
									data: {'departId':departId},
									dataType:"json",
									type:"post",
									success:function(result){
										if(result.success ==true){
											var medicine = result.data;
											// 置空
											$("#tbody").empty();
											// 创建空字符串
											var str = '';
											// 循环medicine集合
											for(var i=0;i<medicine.length;i++){
												var mId = medicine[i].work_code;
												var medName = medicine[i].work_name;
												var dataNum = 0;
												str += '  <tr>';
												str += ' 	<td>';
												str += ' 		<div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='+mId+'><i class="layui-icon">&#xe605;</i></div>';
												str += ' 	</td>';
												str += '	<td>'+mId+'</td>';
												str += '	<td>'+medName+'</td>';
												str += '	<td class="td-manage">';
												str += '     <button class="layui-btn layui-btn layui-btn-xs"  onclick="setWorkTime('+mId+')" >';
												str += '		<i class="layui-icon">&#xe60e;</i>设置考勤时间';
												str += '	  </button>';
												str += '     <button class="layui-btn layui-btn layui-btn-xs"  onclick="setWork('+mId+')" >';
												str += '		<i class="layui-icon">&#xe63c;</i>工作类型设置';
												str += '	  </button>';
												str += '     <button class="layui-btn layui-btn layui-btn-xs"  onclick="updateMed('+mId+')" >';
												str += '		<i class="layui-icon">&#xe642;</i>修改';
												str += '	  </button>';
												str += '    <button class="layui-btn-danger layui-btn layui-btn-xs"  onclick="member_del(this,' +mId+ ')" href="javascript:;" >';
												str += '	<i class="layui-icon">&#xe640;</i>删除';
												str += '  </button>';
												str += '  </td>';
												str += '  </tr>';
												dataNum = i+1;
											}
											$("#dataNumSpan").text('共有数据：'+dataNum+' 条');
											$("#tbody").append(str);
										}
									}
								});
							}
					    }
					  };
			  window.tools = _tools;
			x_admin_show('添加职位','./position-add.jsp?mId='+departId,600,400);
		
		
	});
	 function reloadPage(){

			if(departId!=""){
				$.ajax({
					url:"<%=basePath%>/checkWork/position/getAllPositionByDepartId",
					data: {'departId':departId},
					dataType:"json",
					type:"post",
					success:function(result){
						if(result.success ==true){
							var medicine = result.data;
							// 置空
							$("#tbody").empty();
							// 创建空字符串
							var str = '';
							// 循环medicine集合
							for(var i=0;i<medicine.length;i++){
								var mId = medicine[i].work_code;
								var medName = medicine[i].work_name;
								var wktype=medicine[i].work_type;
								var dataNum = 0;
								str += '  <tr>';
								str += ' 	<td>';
								str += ' 		<div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='+mId+'><i class="layui-icon">&#xe605;</i></div>';
								str += ' 	</td>';
								str += '	<td>'+mId+'</td>';
								str += '	<td>'+medName+'</td>';
								str += '	<td class="td-manage">';
								str += '     <button class="layui-btn layui-btn layui-btn-xs"  onclick="setWorkTime('+mId+')" >';
								str += '		<i class="layui-icon">&#xe60e;</i>设置考勤时间';
								str += '	  </button>';
								str += '     <button class="layui-btn layui-btn layui-btn-xs"  onclick="setWork('+mId+')" >';
								str += '		<i class="layui-icon">&#xe63c;</i>工作类型设置';
								str += '	  </button>';
								str += '     <button class="layui-btn layui-btn layui-btn-xs"  onclick="updateMed('+mId+')" >';
								str += '		<i class="layui-icon">&#xe642;</i>修改';
								str += '	  </button>';
								str += '    <button class="layui-btn-danger layui-btn layui-btn-xs"  onclick="member_del(this,' +mId+ ')" href="javascript:;" >';
								str += '	<i class="layui-icon">&#xe640;</i>删除';
								str += '  </button>';
								str += '  </td>';
								str += '  </tr>';
								dataNum = i+1;
							}
							$("#dataNumSpan").text('共有数据：'+dataNum+' 条');
							$("#tbody").append(str);
						}
						
						 tableCheck = {
						            init:function  () {
						                $(".layui-form-checkbox").click(function(event) {
						                    if($(this).hasClass('layui-form-checked')){
						                        $(this).removeClass('layui-form-checked');
						                        $(".header").removeClass('layui-form-checked');
						                        if($(this).hasClass('header')){
						                            $(".layui-form-checkbox").removeClass('layui-form-checked');
						                        }
						                    }else{
						                    	
						                        $(this).addClass('layui-form-checked');
						                        if($(this).hasClass('header')){
						                            $(".layui-form-checkbox").addClass('layui-form-checked');
						                        }
						                    }
						                    
						                });
						            },
						            getData:function  () {
						                var obj = $(".layui-form-checked").not('.header');
						                var arr=[];
						                obj.each(function(index, el) {
						                    arr.push(obj.eq(index).attr('data-id'));
						                });
						                return arr;
						            }
						        }
						        //开启表格多选
						        tableCheck.init();
					}
				});
			}
	 }

 // 当页面接在完毕 执行函数 
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
	$(function(){
		reloadPage();
		 
		
		
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
	
    </script>
  </body>

</html>