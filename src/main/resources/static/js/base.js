/** POST请求 **/
function post(form, url, data) {
		$.post(url, data, function(json) {
			console.log(json);
            if(json.code == 412){
            	var error = form.find("[data-error='password']");
            	console.log(00000);
            	error.html(json.msg);
            }else if(json.code == 413){
            	var error = form.find("[data-error='password']");
            	console.log(1111);
            	error.html(json.msg);
            }else if(json.code == 200) {
            	window.location.href = json.tourl;
            }
            
            //console.log(json.tourl);
            //window.location.reload();
        }, "json");
	};
$(function(){
	/** 异步表单提交 **/
	$(document).on("click", "#login", function() {
		var form = $(this).parents("form");
	    var url = form.attr("action");
	    var data = form.serialize();
	    $("[data-error]").html("");
	    post(form, url, data);
	    
	});
})
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