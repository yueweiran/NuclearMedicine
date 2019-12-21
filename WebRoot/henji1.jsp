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
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="./layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <script type="text/javascript" src="./js/echarts.js"></script>
    <script type="text/javascript" src="./js/FileSaver.js"></script>
    <script type="text/javascript" src="./js/jquery.wordexport.js"></script>
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

    <div id="box" style="height:400px;width: 800px;padding: 20px"></div>
	 <div id="main" style="height:400px;width: 800px;padding: 20px"></div>
	 
	  <div class="word" style="display: none;">
        <div id="wordtext"></div>
    </div>

    </div>
<script>
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
    return "" + yy + "-" + MM + "-" + dd;
}
var timearray=new Array();
var addarray=new Array();
var delarray=new Array();
var updarray=new Array();
var selarray=new Array();
var addnum=0;
var delnum=0;
var updnum=0;
var selnum=0;
$(function(){
	
	var ins=new RegExp('insert');
	var del=new RegExp('delete');
	var upd=new RegExp('update');
	var sel=new RegExp('select');
	
	var timemap = {};
	var operDateTime="[";
	$.ajax({
		url:"<%=basePath%>/select/medicineList/henji",
		dataType:"json",
		type:"get",
		success:function(result){
			if(result.code == 1){
				var syslog = result.data;
				// 置空
				$("#tbody").empty();
				// 创建空字符串
				var str = '';
				// 循环medicine集合
				for(var i=0;i<syslog.length;i++){
				
					var operType = syslog[i].operType;
					if(ins.test(operType)){
						addnum++;
					}
					if(del.test(operType)){
						delnum++;
					}
					if(upd.test(operType)){
						updnum++;
					}
					if(sel.test(operType)){
						selnum++;
					}
					timemap[dateFormatter(syslog[i].operDateTime)]=i;
				}
				for(var prop in timemap){
					var addcount=0;
			    	var deletecount=0;
			    	var updatecount=0;
			    	var selectcount=0;
				    if(timemap.hasOwnProperty(prop)){
				    	timearray.push(prop);
				    	for(var i=0;i<syslog.length;i++){
				    	
				    		if(dateFormatter(syslog[i].operDateTime)==prop){
				    		
				    			var operType = syslog[i].operType;
				    			if(ins.test(operType)){
									addcount++;
								
								}
								if(del.test(operType)){
									deletecount++;
								}
								if(upd.test(operType)){
									updatecount++;
								}
								if(sel.test(operType)){
									selectcount++;
								}
								
				    		}
				    		
				    	
				    	
				    	}
				    	
				    }
				    addarray.push(addcount);
				    delarray.push(deletecount);
				    updarray.push(updatecount);
				    selarray.push(selectcount);
				}
				echartsLine();
				echartsCircle();
			}
			
		}

	});
	function echartsCircle(){
		 var myChart = echarts.init(document.getElementById("main"));
		var option = {
			    //提示框组件,鼠标移动上去显示的提示内容
			    tooltip: {
			       trigger: 'item',
			       formatter: "{a} <br/>{b}: {c} ({d}%)"//模板变量有 {a}、{b}、{c}、{d}，分别表示系列名，数据名，数据值，百分比。
			    },
			    //图例
			    legend: {
			        //图例垂直排列
			        orient: 'vertical',
			        x: 'left',
			        //data中的名字要与series-data中的列名对应，方可点击操控
			        //data:['增加','删除','修改','查询']
			        data:['增加','修改','查询']
			    },
			    series: [
			        {
			            name:'访问来源',
			            type:'pie',
			            //饼状图
			           radius : '55%',
           				 center: ['50%', '60%'],
			            //标签
			            label: {
			                normal: {
			                    formatter: '{b}:{d}%',//模板变量有 {a}、{b}、{c}、{d}，分别表示系列名，数据名，数据值，百分比。{d}数据会根据value值计算百分比
			                 }
			              },
			            data:[
			                {value:addnum, name:'增加'},
			                //{value:delnum, name:'删除'},
			                {value:updnum, name:'修改'},
			                {value:selnum, name:'查询'}
			            ]
			        }
			    ]
			};

		myChart.on('click', function (params) {
			$("#wordtext").html(params.name+":"+encodeURIComponent(params.value));
			 $(".word").wordExport('word文档');
		});
		myChart.setOption(option);
	}
	function echartsLine(){
		 var myChart = echarts.init(document.getElementById("box"));
		    var option = {
		        // 标题
		        title: {
		            text: '操作数据痕迹'
		           
		        },
		        tooltip: {
		            trigger: 'axis'
		        },
		        //图例名
		        legend: {
		            data:['增加','删除','修改','查询']
		        },
		        grid: {
		            left: '3%',   //图表距边框的距离
		            right: '4%',
		            bottom: '3%',
		            containLabel: true
		        },
		      
		        //x轴信息样式
		        xAxis: {
		            type: 'category',
		            boundaryGap: false,
		            data:timearray
		        },

		        yAxis : [
		            {
		                type : 'value',
		                axisLabel : {
		                    formatter: '{value}次'
		                }
		            }
		        ],
		        series: [
		            //虚线
		            {
		                name:'增加',
		                type:'line',
		                symbolSize:4,   //拐点圆的大小
		                color:['green'],  //折线条的颜色
		                data:addarray,
		                smooth:false,   //关键点，为true是不支持虚线的，实线就用true
		                itemStyle:{
		                    normal:{
		                    	  borderColor:'green'  //拐点边框颜色
		                    }
		                }
		            },
		            //实线
		            {
		                name:'删除',
		                type:'line',
		                symbolSize:4,
		                itemStyle:{
		                    normal:{
		                        color:'red',
		                        borderColor:'red'  //拐点边框颜色
		                    }
		                },
		                data:delarray
		            },
		            {
		                name:'修改',
		                type:'line',
		                symbolSize:4,
		                color:['orange'],
		                smooth:false,   //关键点，为true是不支持虚线的，实线就用true
		                itemStyle:{
		                    normal:{
		                    	 borderColor:'orange'  //拐点边框颜色
		                    }
		                },
		                data:updarray
		            },
		            {
		                name:'查询',
		                type:'line',
		                symbolSize:4,
		                color:['blue'],
		                itemStyle:{
		                    normal:{
		                    	 borderColor:'blue'  //拐点边框颜色
		                    }
		                },
		                data:selarray
		            }
		        ]
		    };

		    myChart.setOption(option);
	}
	 
});
	

</script>

  </body>

</html>