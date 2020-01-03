layui.use('form', function(){
	  var form = layui.form;
	  
	  //各种基于事件的操作，下面会有进一步介绍
	  form.on('checkbox(ywfilter)', function(data){
		  if(data.value=="其他" && data.elem.checked){
			  $(data.othis).after(' <input type="text" name="other" style="width: 120px;display: inline-table;vertical-align: bottom;height: 30px;"  placeholder="请输入其他信息"  class="layui-input">');
		  }
		  if(data.value=="其他" && !data.elem.checked){
			  $("input[name='other']").remove();
		  }
		  
		});    
	  
	   layui.form.on('select(levelfilter)', function (data) {
		  if (data.value == "") {
		  $('#levelcon').html('<option value="">请选择</option>');
		  layui.form.render("select");
		  }
		  else {
			  if (data.value == "0") {
			  $('#levelcon').html('<option value="">请选择</option>')
			  $('#levelcon').append(new Option("甲等", 0));
			  $('#levelcon').append(new Option("乙等", 1));
			  $('#levelcon').append(new Option("丙等", 2));
			  $('#levelcon').append(new Option("未评", 3));
			  $('#levelcon').append(new Option("其他", 4));
			  $('#levelcon').append(new Option("未知", 5));
			  }else if (data.value == "1") {
			  $('#levelcon').html('<option value="">请选择</option>')
			  $('#levelcon').append(new Option("甲等", 0));
			  $('#levelcon').append(new Option("乙等", 1));
			  $('#levelcon').append(new Option("合格", 2));
			  $('#levelcon').append(new Option("未定等", 3));
			  $('#levelcon').append(new Option("未知", 4));
			  }
		  } 
		  layui.form.render("select");
	   });
	});
	
 layui.use('element', function(){
	  var element = layui.element;
	});
    $(function(){
        $('.box').step({
            stepDirection:'x',//x代表水平步骤，y 竖直步骤，默认x
            showStepButton:true,//是否显示上一步下一步的按钮 true代表显示，默认false
            stepCount:6,//步数：默认3
            stepTitles:['科室概况','业务信息','人员信息','教学开展','科研开展','意见与建议'],
        });
    })
     function checkFormOne(isstep){
    	var obj = {};
    	
    	 var alertcon='';
    	  //将页面全部复选框选中的值拼接到一个数组中
        var arr_box = [];
    		 /*  $(".verif[verification='true']").each(function(){
    			
    	        	if($(this).val()==""){
    	        		alertcon=$(this).attr("tstitle");
    	        		 isstep=false;
    	        		 return false; 
    	        	}else{
    	        		if($(this).attr("name")=="email" && !/[a-zA-Z0-9]{1,10}@[a-zA-Z0-9]{1,5}\.[a-zA-Z0-9]{1,5}/.test($(this).val())){
    	 				   alertcon="邮箱格式不正确";
    	 				   isstep=false;
    	 				  return false; 
    	 				}
    					if($(this).attr("name")=="year" && !/[0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3}/.test($(this).val())){
    	 				   alertcon="年份格式不正确";
    	 				   isstep=false;
    	 				  return false; 
    	 				}
    					if($(this).attr("name")=="phonenumber" && !/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/.test($(this).val())){
    					   alertcon="座机号码格式不正确";
    					   isstep=false;
    					   return false; 
    					}
    					if($(this).attr("name")=="mobilphonenumber" && !/^1[3|4|5|7|8][0-9]{9}$/.test($(this).val())){
    					   alertcon="手机号码格式不正确";
    					   isstep=false;
    					   return false; 
    					}
    					if($(this).attr("name")=="postcode" && ! /^[1-9][0-9]{5}$/.test($(this).val())){
    					   alertcon="邮编格式不正确";
    					   isstep=false;
    					   return false; 
    					}
    	        	} 
    	        	
    	          }); */
    		 
       if(isstep){
    	   $('input[type=checkbox]:checked').each(function() {
           	if($(this).attr("name")=="business"){
           		if($(this).val()=="其他"){
              		 	arr_box.push($("input[name='other']").val());
   	           	}else{
   	           		arr_box.push($(this).val());
   	           	}
           	}
             
             });
    	   if(arr_box.length<=0){
           	alertcon="请至少选择一项业务";
           	isstep=false;
           }else{
        	   obj['ywarr']=arr_box;
        	   isstep=true;
           }
       }
       if(!isstep){
    	   layer.msg(alertcon);
       }
   		obj['isstep']=isstep;
    	  return obj;
        	
        }
    function initStepTwo(obj){
    	$(".step-ul").html("");
    	var ywarr=obj.ywarr;
    	if(ywarr.length>0){
    		var str='';
    		for (var i = 0; i < ywarr.length; i++) {
    			str+='<li class="step-li"><span>'+ywarr[i]+'</span></li>';
			}
    		$(".step-ul").append(str);
    		var firsttext=ywarr[0];
    		if(firsttext=="单光子显像" ||firsttext=="符合线路显像"||firsttext=="正电子显像"){
    			$("#iframebody").attr("src","xianxiang.jsp");
    		}else if(firsttext=="放射免疫分析" ||firsttext=="化学发光分析"){
    			$("#iframebody").attr("src","fenxi.jsp");
    		}else if(firsttext=="脏器功能测定"){
    			$("#iframebody").attr("src","zangqi.jsp");
    		}else if(firsttext=="门诊"||firsttext=="核素治疗"||firsttext=="病房"){
    			$("#iframebody").attr("src","hesu.jsp");
    		}else if(firsttext=="科研实验室"){
    			$("#iframebody").attr("src","keyan.jsp");
    		}else if(firsttext!=""){
    			$("#iframebody").attr("src","qita.jsp");
    		}else{
    			$("#iframebody").attr("src","");
    		}
    	}
    	$(".step-ul li").click(function(){
    		$(".step-ul li").removeClass("step-click");
    		$(this).addClass("step-click");
    		var seltext=$(this).find("span").text().trim();
    		if(seltext=="单光子显像" ||seltext=="符合线路显像"||seltext=="正电子显像"){
    			$("#iframebody").attr("src","xianxiang.jsp");
    		}else if(seltext=="放射免疫分析" ||seltext=="化学发光分析"){
    			$("#iframebody").attr("src","fenxi.jsp");
    		}else if(seltext=="脏器功能测定"){
    			$("#iframebody").attr("src","zangqi.jsp");
    		}else if(seltext=="门诊"||seltext=="核素治疗"||seltext=="病房"){
    			$("#iframebody").attr("src","hesu.jsp");
    		}else if(seltext=="科研实验室"){
    			$("#iframebody").attr("src","keyan.jsp");
    		}else if(seltext!=""){
    			$("#iframebody").attr("src","qita.jsp");
    		}else{
    			$("#iframebody").attr("src","");
    		}
    		
    	});
    }
    
    laydate.render({
  	  elem: '#startdate' //指定元素
  	  ,theme: 'grid'
  	});
    
    layui.use('table', function(){
  	  var table = layui.table;
  	  
  	
  });
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
  