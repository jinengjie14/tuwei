/** POST请求 **/
function post(form, url, data) {
		$.post(url, data, function(json) {
			console.log(json);
            if(json.code == 412){
            	var error = form.find("[data-error='password']");
            	error.html(json.msg);
            }else if(json.code == 413){
            	var error = form.find("[data-error='password']");
            	console.log(1111);
            	error.html(json.msg);
            }else if(json.code == 200) {
            	window.location.href = json.tourl;
            }
            //window.location.reload();
        }, "json");
	};
	
$(function(){
	/** 登录提交 **/
	$(document).on("click", "#login", function() {
		var form = $(this).parents("form");
	    var url = form.attr("action");
	    var data = form.serialize();
	    $("[data-error]").html("");
	    post(form, url, data);
	});
})

$(function(){
	/** 文章表单提交 **/
	$(document).on("click", "[name='sumbit']", function() {
		var form = $(this).parents("form");
	    var url = form.attr("action");
	    var data = form.serialize();
	    console.log(data);
	    $("[data-error]").html("");
	    $.post(url, data, function(json) {
	    	console.log(json);
	    	console.log(json.tourl);
	    	if(json.msg != null){
	    		var error = form.find("[data-error='title']");
	    		error.html(json.msg);
	    	}else{
	    		 console.log(json.tourl);
	    		 window.location.href = json.tourl;
	    	}
        }, "json");
	});
	
	/** 删除 **/
	$(document).on("click","[name='delete']",function(event){
        event.preventDefault();
         $.get($(this).attr("href"),function(json){
        	 window.location.href = json.tourl;
         });
   }); 
	
})

function getKeyWord(){  
    if(event.keyCode==13){  
    	var form = $(this).parents("form");
    	var url = form.attr("action");
    	var data = form.serialize();
    	$.get(url,data,function(){
    		
    	});
    }     
}  




/** jquery请求接口 
function addUser(){
    //var account=$("#account").val(), password=$("#password").val();
    console.log(account, password);
    $.ajax({
        url:'http://10.1.65.33:81/login',
        data:{"account":account, "password":password},
        type:'POST',
        dataType:'json',
        success:function(json){
            if(json.code==200){
                console.log('登录成功');
            }else{
                console.log('登录失败')
            }
        }
         
    });
}
$(function() {
	 $(document).on("click","#login", function() {
	    	addUser();
	});
})**/