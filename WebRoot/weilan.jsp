<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";font-size:14px;}
		#l-map{height:95%;width:100%;}
		#r-result{width:100%;}
		.mapclass{
			margin-top:5px;
		}
	</style>
	    <link rel="stylesheet" href="./css/font.css">
	<link rel="stylesheet" href="./css/xadmin.css">
        <script type="text/javascript" src="js/jquery.js"></script>
            <script type="text/javascript" src="js/jquery.cookie.js"></script>
    <script src="./layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=UFnmcEfD90UAWSvT2EqMes563Rvb2T2j"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
	<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
	<!--加载检索信息窗口-->
	<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.4/src/SearchInfoWindow_min.js"></script>
	<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.4/src/SearchInfoWindow_min.css" />
	<title>关键字输入提示词条</title>
</head>
<body>
	
	
	<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
	<div id="r-result" style="margin-top:5px;margin-bottom:5px;">
	<input id="departId" type="hidden" value=""/>
	<input id="iswl" type="hidden" value=""/>
	<input id="zuobiaostr" type="hidden" value=""/>
	<input class="layui-input" placeholder="请输入地区" type="text" id="suggestId" size="20" value="百度" style="width:150px;float:left;margin-left:40px;" />&nbsp;&nbsp;
	<button class="layui-btn" onclick="clearAll()" >清除所有坐标</button>
	&nbsp;&nbsp;<button class="layui-btn" id="eid" onclick="subZuoBiao()"/>确定</button>
	<div class="mapclass" id="l-map"></div>
</body>
</html>
<script type="text/javascript">

	$(function(){
		 var v = parseUrl();//解析所有参数
	     var departId = v['mId'] ;//部门id
	    $("#departId").val(departId);
	     $.ajax({
				url:"<%=basePath%> /checkWork/enclosure/getdepartById",
				data: {'departId':departId},
				dataType:"json",
				type:"post",
				success:function(result){
					if(result.errorcode == 200 && result.success==true){
						var weilan= result.data;
							$("#eid").val(weilan.id);
							$("#zuobiaostr").val(weilan.enclosure);
							$("#iswl").val("y");
							resetTrueMap();
							showWeiLan();
					}else if(result.errorcode == 200 && result.success==false){
						$("#iswl").val("n");
						resetFalseMap();
					}
				}
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
	
	});

    function subZuoBiao(){
    	
    	var departmentid=$("#departId").val();
   	 var zbstr=$("#zuobiaostr").val();
   
   	 if(zbstr!=""){
   		 	var iswla=$("#iswl").val();
   		 	if(iswla==="n"){
   		 		 $.ajax({
						url:"<%=basePath%> /checkWork/enclosure/addEnclosure",
						data: {'departId':departmentid,'enclosure':zbstr},
						dataType:"json",
						type:"post",
						success:function(result){
							if(result.errorcode == 200 && result.success==true){
								var index=parent.layer.getFrameIndex(window.name); //获取当前窗口的name
								parent.layer.close(index);
								parent.wltools.func();
							}else if(result.errorcode == 200 && result.success==false){
								layer.msg("设置失败！");
							}
						}
					});
   		 	}else{
   		 		var eid=$("#eid").val();
   		 	 $.ajax({
					url:"<%=basePath%> /checkWork/enclosure/upEnclosure",
					data: {'eid':eid,'enclosure':zbstr},
					dataType:"json",
					type:"post",
					success:function(result){
						if(result.errorcode == 200 && result.success==true){
							var index=parent.layer.getFrameIndex(window.name); //获取当前窗口的name
							parent.layer.close(index);
							parent.wltools.func();
						}else if(result.errorcode == 200 && result.success==false){
							layer.msg("设置失败！");
						}
					}
				});
   		 	}
   	
   	 }else{
   		 layer.alert("请先设置围栏！");
   	 }
   	
    }
    
	// 百度地图API功能
	function G(id) {
		return document.getElementById(id);
	}

	var map = new BMap.Map("l-map");
	
/* 	map.addEventListener("click",function(e){
		console.log(e.point.lng + "," + e.point.lat);
	});	 */

	function resetTrueMap(){
		var zb;
		var zuobiaostr=$("#zuobiaostr").val();
		 if(zuobiaostr!=""){
			 map.clearOverlays(); 
			if(zuobiaostr.indexOf(";")>0){
				var zbstr=zuobiaostr.split(";");
				if(zbstr.length>0){
					zb=zbstr[0];
					
				}
			}else{
				zb=zuobiaostr;
			}
			zb='new BMap.Point('+zb+')';
			map.centerAndZoom(eval(zb),18);
			map.enableScrollWheelZoom(true);
		}
		
		
	}
	function resetFalseMap(){
		map.centerAndZoom("临漳县",14);                   // 初始化地图,设置城市和地图级别。
		map.enableScrollWheelZoom(); 
		
	}

	var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
		{"input" : "suggestId"
		,"location" : map
	});

	ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
	var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
		
		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
		G("searchResultPanel").innerHTML = str;
	});

	var myValue;
	ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
	var _value = e.item.value;
		myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
		
		setPlace();
	});

	function setPlace(){
		//map.clearOverlays();    //清除地图上所有覆盖物
		function myFun(){
			var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			map.centerAndZoom(pp, 20);
			map.addOverlay(new BMap.Marker(pp));    //添加标注
		}
		var local = new BMap.LocalSearch(map, { //智能搜索
		  onSearchComplete: myFun
		});
		local.search(myValue);
	}
	
	
	
	 var overlays = [];
		var overlaycomplete = function(e){
			var zb=$("#zuobiaostr").val();
			        overlays.push(e.overlay);
			    	if(zb!=""){
						theclear();
						layer.alert("请先清除之前的坐标！");
					}else{
						
						
						var a=e.overlay.ff;
						var zbstr="";
						var num=0;
						for(var i=0;i<a.length;i++){
							if(a[i]!=null){
								for(var j=0;j<a[i].length;j++){
									zbstr+=(a[i][j].lng)+","+(a[i][j].lat)+";";
								}
								
								
							}
						}
						zbstr=zbstr.substring(0,zbstr.length-1);
						$("#zuobiaostr").val(zbstr);
					}
			
		
	    };
	
	    var styleOptions = {
        strokeColor:"red",    //边线颜色。
        fillColor:"red",      //填充颜色。当参数为空时，圆形将没有填充效果。
        strokeWeight: 3,       //边线的宽度，以像素为单位。
        strokeOpacity: 0.8,	   //边线透明度，取值范围0 - 1。
        fillOpacity: 0.6,      //填充的透明度，取值范围0 - 1。
        strokeStyle: 'solid' //边线的样式，solid或dashed。
    }
    //实例化鼠标绘制工具
    var drawingManager = new BMapLib.DrawingManager(map, {
        isOpen: false, //是否开启绘制模式
        enableDrawingTool: true, //是否显示工具栏
        drawingToolOptions: {
            anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
            offset: new BMap.Size(5, 5), //偏离值
        },
        circleOptions: styleOptions, //圆的样式
        polylineOptions: styleOptions, //线的样式
        polygonOptions: styleOptions, //多边形的样式
        rectangleOptions: styleOptions //矩形的样式
    });  
	 //添加鼠标绘制工具监听事件，用于获取绘制结果
    drawingManager.addEventListener('overlaycomplete', overlaycomplete);
	
	var polyline;
	var thezb='[';
	function showWeiLan(){
		var zuobiaostr=$("#zuobiaostr").val();
		 if(zuobiaostr!=""){
			if(zuobiaostr.indexOf(";")>0){
				var zbstr=zuobiaostr.split(";");
				if(zbstr.length>0){
					for(var i=0;i<zbstr.length;i++){
						thezb+='new BMap.Point('+zbstr[i]+'),';
					}
					thezb=thezb.substring(0,thezb.length-1)+']';
					
				}
			}else{
				thezb+='new BMap.Point('+zuobiaostr+')';
			}
		} 
		 polyline = new BMap.Polyline(eval(thezb), {strokeColor:"red", strokeWeight:3, strokeOpacity:0.8});   //创建折线
		                          	map.addOverlay(polyline); 
	}
		
	
    function clearAll() {
    	layer.confirm('清除将删除所有坐标，是否删除？',function(index){
    		var wl=$("#iswl").val();
    		
    		for(var i = 0; i < overlays.length; i++){
                map.removeOverlay(overlays[i]);
            }
            overlays.length = 0   
            map.removeOverlay(polyline);
            $("#zuobiaostr").val("");
            layer.closeAll('dialog');
            var zbstr=$("#zuobiaostr").val();
            if(wl==="y"){
            	var eid=$("#eid").val();
      		 	 $.ajax({
   					url:"<%=basePath%> /checkWork/enclosure/upEnclosure",
   					data: {'eid':eid,'enclosure':zbstr},
   					dataType:"json",
   					type:"post",
   					success:function(result){
   						if(result.errorcode == 200 && result.success==true){
   							layer.msg("删除成功！");
   						}else if(result.errorcode == 200 && result.success==false){
   							layer.msg("设置失败！");
   						}
   					}
   				});
            	
            }
		});
		
    }
    function theclear() {
    		
    	for(var i = 0; i < overlays.length; i++){
            map.removeOverlay(overlays[i]);
        }
        overlays.length = 0   
        layer.closeAll('dialog'); 
		
    }
</script>
