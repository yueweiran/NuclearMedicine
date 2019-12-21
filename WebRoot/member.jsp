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
    <title>退单管理</title>
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
      <script src="https://cdn.staticfile.org/jsp5shiv/r29/jsp5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
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
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
    <div class="x-body">
      <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
          <input type="text" name="medName" id="sousuokuang" placeholder="请输入积分等级" autocomplete="off" class="layui-input" lay-verify="number" oninput="value=value.replace(/[^\d]/g,'')">
          <button class="layui-btn"  lay-submit="" lay-filter="selectName"><i class="layui-icon">&#xe615;</i></button>
        </form>
      </div>
      <xblock>
        <button class="layui-btn" onclick="x_admin_show('添加','./member-add.jsp',600,400)"><i class="layui-icon"></i>添加</button>
        <span class="x-right" style="line-height:40px" id="dataNumSpan"></span>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
      </xblock>
<div style="overflow:auto;width:100%">
	<table class="layui-table" width="auto" style="white-space:nowrap">
        <thead>

          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>ID</th>
            <th>积分等级</th>
            <th>礼品</th>
            <th>结束时间</th>
            <th>操作</th></tr>
        </thead>
        <tbody id="tbody">
				
				<!--  利用Ajax技术循环生成页面	-->
				
		</tbody>
      </table>
   </div>
      <div class="page">
        <div>
          <a class="prev" href="">&lt;&lt;</a>
          <a class="num" href="">1</a>
          <span class="current">2</span>
          <a class="num" href="">3</a>
          <a class="num" href="">489</a>
          <a class="next" href="">&gt;&gt;</a>
        </div>
      </div>

    </div>
    <script>
layui.use('form', function(){
	var form = layui.form;	
	form.on('submit(selectName)', function(data){   	
		var sousuokuang = $("#sousuokuang").val();
		$.ajax({
			url:"<%=basePath%>/select/member/memberGarde",
			data: {'memberGarde':sousuokuang},
			dataType:"json",
			type:"post",
			success:function(result){
				if(result.code == 1){
					var memberList = result.data;
					// 置空
					$("#tbody").empty();
					// 创建空字符串
					var str = '';
					// 循环medicine集合
					for(var i=0;i<memberList.length;i++){

						var memberId = memberList[i].memberId;
						var memberGarde = memberList[i].memberGarde;
						var memberLasttime = dateFormatter(memberList[i].memberLasttime);
						var memberLipin = memberList[i].memberLipin;
						var dataNum = 0;
						str += '  <tr>';
						str += ' 	<td>';
						str += ' 		<div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='+memberId+'><i class="layui-icon">&#xe605;</i></div>';
						str += ' 	</td>';
						str += '	<td>'+memberId+'</td>';
						str += '    <td>'+memberGarde+'</td>';
						str += '    <td>'+memberLipin+'</td>';
						str += '	<td>'+memberLasttime+'</td>';
						str += '	<td class="td-manage">';
						str += '     <button class="layui-btn layui-btn layui-btn-xs"  onclick="updateMed('+memberId+')" >';
						str += '		<i class="layui-icon">&#xe642;</i>修改';
						str += '	  </button>';
						str += '    <button class="layui-btn-danger layui-btn layui-btn-xs"  onclick="member_del(this,' +memberId+ ')" href="javascript:;" >';
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

       /*用户-停用*/
      function member_stop(obj,id){
          layer.confirm('确认要停用吗？',function(index){

              if($(obj).attr('title')=='启用'){

                //发异步把用户状态进行更改
                $(obj).attr('title','停用')
                $(obj).find('i').jsp('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').jsp('已停用');
                layer.msg('已停用!',{icon: 5,time:1000});

              }else{
                $(obj).attr('title','启用')
                $(obj).find('i').jsp('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').jsp('已启用');
                layer.msg('已启用!',{icon: 5,time:1000});
              }
              
          });
      }

    /*用户-修改*/
	function updateMed(memberId){
		var url = 'member-update.jsp?memberId='+memberId;
		x_admin_show('编辑',url);

	}
     
      /*用户-删除*/
      function member_del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              	//发异步删除数据
				$.ajax({
					url:"<%=basePath%>/delete/member/memberId",
					data:{'memberId':id},
					dataType:"json",
					async:false,
					type:"post",
					success:function(result){
						if (result == 1) {							
						}
					}
				});	
				$(obj).parents("tr").remove();
	            layer.msg('已删除!',{icon:1,time:1000});
          });
      }




  	function delAll (argument) {
  		// 获取全部复选ID
          var datas = tableCheck.getData();
  		if (datas.length != 0) {
  			layer.confirm('确认要删除吗？',function(index){

                	//发异步删除数据
  				$.ajax({
  					url:"<%=basePath%>/delete/member/members",
  					data:{'datas':datas},
  					dataType:"json",
  					async:false,
  					type:"post",
  					success:function(result){
  						if (result == 1) {							
  						}
  					}
  				});	
  				
  				//捉到所有被选中的，发异步进行删除
  				layer.msg('删除成功', {icon: 1});
  				$(".layui-form-checked").not('.header').parents('tr').remove();
  			});
  		} else {
  			layer.confirm('请勾选要删除的选项！');
  		}
  	}
    </script>
    <script>
 // 当页面加载完毕 执行函数 
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
		
		$.ajax({
			url:"<%=basePath%>/select/memberList/member",
			dataType:"json",
			type:"get",
			success:function(result){
				if(result.code == 1){
					var memberList = result.data;
					// 置空
					$("#tbody").empty();
					// 创建空字符串
					var str = '';
					// 循环medicine集合
					for(var i=0;i<memberList.length;i++){

						var memberId = memberList[i].memberId;
						var memberGarde = memberList[i].memberGarde;
						var memberLasttime = dateFormatter(memberList[i].memberLasttime);
						var memberLipin = memberList[i].memberLipin;
						var dataNum = 0;
						str += '  <tr>';
						str += ' 	<td>';
						str += ' 		<div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='+memberId+'><i class="layui-icon">&#xe605;</i></div>';
						str += ' 	</td>';
						str += '	<td>'+memberId+'</td>';
						str += '    <td>'+memberGarde+'</td>';
						str += '    <td>'+memberLipin+'</td>';
						str += '	<td>'+memberLasttime+'</td>';
						str += '	<td class="td-manage">';
						str += '     <button class="layui-btn layui-btn layui-btn-xs"  onclick="updateMed('+memberId+')" >';
						str += '		<i class="layui-icon">&#xe642;</i>修改';
						str += '	  </button>';
						str += '    <button class="layui-btn-danger layui-btn layui-btn-xs"  onclick="member_del(this,' +memberId+ ')" href="javascript:;" >';
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
});
    </script>
  </body>

</html>