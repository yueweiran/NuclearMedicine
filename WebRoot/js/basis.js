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
  