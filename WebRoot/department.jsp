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
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">演示</a>
        <a>
          <cite>导航元素</cite></a>
      </span>
      <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:38px">ဂ</i></a>
    </div>
    <div class="x-body">

       <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
        <input type="text" name="address" id="address" placeholder="请选择地区" autocomplete="off" class="layui-input" lay-verify="required">&nbsp;&nbsp;&nbsp;&nbsp;
        <input id="addrtext" addid="" type="hidden" value=""/>
          <input type="text" name="medName" id="deptName" placeholder="请输入部门名称" autocomplete="off" class="layui-input" >
          <button class="layui-btn"  lay-submit="" lay-filter="selectName"><i class="layui-icon">&#xe615;</i></button>
        </form>
      </div> 

      <xblock>
        <button class="layui-btn" id="addDepartment" ><i class="layui-icon"></i>添加</button>
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
            <th>ID</th>
            <th>部门名称</th>
            <th>操作</th></tr>
        </thead>
        <tbody id="tbody">
				
				<!--  利用Ajax技术循环生成页面	-->
				
		</tbody>
      </table>
     </div>
     

    </div>
<script>
layui.use('form', function(){
	var form = layui.form;	
	form.on('submit(selectName)', function(data){ 
		
		var addtessvalue=$("#address").val();
		if(addtessvalue!=""){
			var areaId=$("#addrtext").attr("addid");
			var deptName = $("#deptName").val();
			$.ajax({
				url:"<%=basePath%>/checkWork/departement/getDepartByName",
				data: {'areaId':areaId,'deptName':deptName},
				dataType:"json",
				type:"post",
				success:function(result){
					if(result.errorcode == 200){
						var medicine = result.data;
						// 置空
						$("#tbody").empty();
						// 创建空字符串
						var str = '';
						// 循环medicine集合
						for(var i=0;i<medicine.length;i++){
							var mId = medicine[i].id;
							var medName = medicine[i].departmentName;
							var dataNum = 0;
							str += '  <tr>';
							str += ' 	<td>';
							str += ' 		<div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='+mId+'><i class="layui-icon">&#xe605;</i></div>';
							str += ' 	</td>';
							str += '	<td>'+mId+'</td>';
							str += '	<td>'+medName+'</td>';
							str += '	<td class="td-manage">';
							str += '     <button class="layui-btn layui-btn layui-btn-xs"  onclick="setPosition('+mId+')" >';
							str += '		<i class="layui-icon">&#xe66f;</i>设置职位';
							str += '	  </button>';
							str += '     <button class="layui-btn layui-btn layui-btn-xs"  onclick="setWeiLan('+mId+')" >';
							str += '		<i class="layui-icon">&#xe715;</i>设置围栏';
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
		}else{
			layer.alert("请先选择地点！");
			return false;
		}
		
	return false;
	});
});
</script>
    <script>
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

      
  
  
  	/*部门-设置围栏 */
  	function setWeiLan(mId){
  		var _wltools = {
			    func: function(){
			    	setsuccess();
			    }
			  };
	  window.wltools = _wltools;
  		var url = 'weilan.jsp?mId='+mId;
		x_admin_show('围栏设置',url);
  	}
  	
  	//岗位设置
  	function setPosition(mId){
  		var _gwtools = {
			    func: function(){
			    	setsuccess();
			    }
			  };
	  window.gwtools = _gwtools;
  		var url = 'position.jsp?mId='+mId;
		x_admin_show('职位设置',url);
  	}
  	
  	

  	
  	function setsuccess(){
		layer.msg("设置成功！");
	}
      
/*部门-修改 */

	function updateMed(mId){
		 var _uptools = {
				    func1: function(){
				     reloadPage();
				    }
				  };
		  window.tools1 = _uptools;
		var url = 'department-update.jsp?mId='+mId;
		x_admin_show('编辑',url,600,400);
	}
	
	

      /*部门-删除*/
      function member_del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              	//发异步删除数据
				$.ajax({
					url:"<%=basePath%>/checkWork/departement/delDepartById",
					data:{'areaId':id},
					dataType:"json",
					async:false,
					type:"post",
					success:function(result){
						if (result.errorcode == 200) {	
							$(obj).parents("tr").remove();
				            layer.msg('已删除!',{icon:1,time:1000});
						}
					}
				});	
			
          });
      }


	// 批量删除
  	function delAll (argument) {
  		var addtessvalue=$("#address").val();
			if(addtessvalue!=""){
	        	var datas = tableCheck.getData();
				if (datas.length != 0) {
					layer.confirm('确认要删除吗？',function(index){
		
		              	//发异步删除数据
						$.ajax({
							url:"<%=basePath%>/checkWork/departement/delDeparts",
							data:{'datas':datas},
							dataType:"json",
							async:false,
							type:"post",
							success:function(result){
								if (result.errorcode == 200) {	
									layer.closeAll('dialog');
									layer.msg('删除成功', {icon: 1});
									$(".layui-form-checked").not('.header').parents('tr').remove();
								}
							}
						});	
						
						
					});
				} else {
					layer.confirm('请勾选要删除的选项！');
				}
		}else{
			layer.alert("请先选择地区");
		}
	}
	
	
	
	$("#addDepartment").click(function(){
		var departName=$("#addrtext").attr("addid");
		if(departName!=""){
			 var _tools = {
					    func1: function(){
					     reloadPage();
					    }
					  };
			  window.tools = _tools;
			x_admin_show('添加部门','./department-add.jsp',600,400);
		}else{
			layer.alert("请先选择地区");
		}
		
	});
	 function reloadPage(){
		 var addrId=$("#addrtext").attr("addid");
		 $.ajax({
				url:"<%=basePath%>/checkWork/departement/getdepart",
				data:{"areaId":addrId},
				dataType:"json",
				type:"post",
				success:function(result){
					if(result.errorcode == 200){
						var medicine = result.data;
						
						// 置空
						$("#tbody").empty();
						// 创建空字符串
						var str = '';
						// 循环medicine集合
						var dataNum = 0;
						for(var i=0;i<medicine.length;i++){
							var mId = medicine[i].id;
							var medName = medicine[i].departmentName;
						
							
							str += '  <tr>';
							str += ' 	<td>';
							str += ' 		<div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='+mId+'><i class="layui-icon">&#xe605;</i></div>';
							str += ' 	</td>';
							str += '	<td>'+mId+'</td>';
							str += '	<td>'+medName+'</td>';
							str += '	<td class="td-manage">';
							str += '     <button class="layui-btn layui-btn layui-btn-xs"  onclick="setPosition('+mId+')" >';
							str += '		<i class="layui-icon">&#xe66f;</i>设置职位';
							str += '	  </button>';
							str += '     <button class="layui-btn layui-btn layui-btn-xs"  onclick="setWeiLan('+mId+')" >';
							str += '		<i class="layui-icon">&#xe715;</i>设置围栏';
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
    </script>
    <script>
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
		var stAreaStr = $.cookie('stAreaStr');
		startPlan(stAreaStr);
		$("#address").click(function(){
			var ar=$("#addrtext").val();
			var opDom=$(this);
			var param = {
				addressId:ar
			};
			showPop(param,opDom);
		});
		/*layer弹出页面层*/
		function showPop(param,opDom){
			layer.open({
					//页面层默认选择2，content指向父层某个div时type设置为1
					type:2,
					//头部标题
					title:'选择地区',
					//子层容器宽度和高度
					area:['800px','500px'],
					//子层页面资源
					content:["./seladdress.jsp",'no'],
					//是否显示右上角关闭图标，默认为1，显示
					closeBtn:0,
					//右下角按钮文本和数目控制
					btn: ['确定', '取消'],
					//按钮对应点击事件回调通知
					yes:function(index, layero){
						//异常捕获，避免页面因js错误而终止运行 
						try{
							//成功回调，从当前界面dom缓存中获取子层保存的数据
							if(opDom.data('addressId')==null){
								layer.alert("请选择地点！");
								return false;
							}
							startPlan(opDom.data('addressId'));
							opDom.data('addressId',-1);
						}catch(e){
							console.log(e);
							layer.alert("参数错误！");
							opDom.removeData("addressId");
							return false;
						}	
						layer.close(index);
					},
					btn2:function(index, layero){
						layer.close(index);
						opDom.data('addressId',-1);
					}
				});	
		}

		/*业务逻辑*/
		function startPlan(address){
			var endaddrId,endaddcon,endaddrStr;
			if(address.length>0){
			var bd=address.substring(address.length-1,address.length);
			var addressstr;
			if(bd===";"){
				addressstr=address.substring(0,address.length-1);
			}else{
				addressstr=address;
			}
			
			$("#addrtext").val(addressstr);
			if(addressstr.indexOf(";")>0){
				var addstr=addressstr.split(";");
				 endaddrStr=addstr[addstr.length-1].split(":");
				 endaddrId=endaddrStr[0];
				 endaddcon=endaddrStr[1];
			}else{
				 endaddrStr=addressstr.split(":");
				 endaddrId=endaddrStr[0];
				 endaddcon=endaddrStr[1];
			}
			
			$("#address").val(endaddcon);
			$("#addrtext").attr("addid",endaddrId);
			 $.ajax({
				url:"<%=basePath%>/checkWork/departement/getdepart",
				data:{"areaId":endaddrId},
				dataType:"json",
				type:"post",
				success:function(result){
					if(result.errorcode == 200){
						var medicine = result.data;
						
						// 置空
						$("#tbody").empty();
						// 创建空字符串
						var str = '';
						// 循环medicine集合
						var dataNum = 0;
						for(var i=0;i<medicine.length;i++){
							var mId = medicine[i].id;
							var medName = medicine[i].departmentName;
						
							
							str += '  <tr>';
							str += ' 	<td>';
							str += ' 		<div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='+mId+'><i class="layui-icon">&#xe605;</i></div>';
							str += ' 	</td>';
							str += '	<td>'+mId+'</td>';
							str += '	<td>'+medName+'</td>';
							str += '	<td class="td-manage">';
							str += '     <button class="layui-btn layui-btn layui-btn-xs"  onclick="setPosition('+mId+')" >';
							str += '		<i class="layui-icon">&#xe66f;</i>设置职位';
							str += '	  </button>';
							str += '     <button class="layui-btn layui-btn layui-btn-xs"  onclick="setWeiLan('+mId+')" >';
							str += '		<i class="layui-icon">&#xe715;</i>设置围栏';
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
		 
		
		
		
	});
    </script>
  </body>

</html>