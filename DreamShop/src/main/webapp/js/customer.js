$(document).ready(function () {
	$("#login").on("click",function(){
		$.ajax({
			url:'../customer?method=login',
			/*data:{
				"customerName":$("#customerName").val(),
				"password":$("#password").val()
			},*/
			success:function(){
				window.location.href="../index.html";
			},
			error:function(){
				alert("登录失败");
			}
		})
	});
});