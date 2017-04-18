var checkUser = function(){
	$.ajax({
		type : 'post',
		url : 'checkUser',
		dataType : 'json',
		data : {
			username : $('#username').val(),
			password : $('#password').val()
		},
		success : function(result) {
			console.log(result);
			if(result==1){
				window.location.href="index";
			}else{
				$('#message').html("用户名或密码错误！");
			}
		},
		error : function(result) {

		}
	});
};

