//var checkUser = function(){
//	$.ajax({
//		type : 'post',
//		url : 'checkUser',
//		dataType : 'json',
//		data : {
//			username : $('#username').val(),
//			password : $('#password').val()
//		},
//		success : function(result) {
//			console.log(result);
//			if(result==1){
//				window.location.href="index";
//			}else{
//				$('#message').html("用户名或密码错误！");
//			}
//		},
//		error : function(result) {
//
//		}
//	});
//};
//

$(document).on("pageshow","#droneModify",function(){
	var droneType = $('#m_droneType').val();
	m_changeType(droneType);
});


var m_getFormValues = function(){
	var droneId = $('#m_droneId').val();
	var droneType = $('#m_droneType').val();
	var droneName = $('#m_droneName').val();
	var droneSeq = $('#m_droneSeq').val();
	var startDelay = $('#m_startDelay').val();
	var dirPin = $('#m_dirPin').val();
	var pulPin = $('#m_pulPin').val();
	var interval = $('#m_interval').val();
	var beforePin = $('#m_beforePin').val();
	var backPin = $('#m_backPin').val();
	var rotateDelay = $('#m_rotateDelay').val();
	var startButton = $('#m_startButton').val();
	var backButton = $('#m_backButton').val();
	var stopButton = $('#m_stopButton').val();
	
	return {id:droneId,type:droneType,name:droneName,seq:droneSeq,startDelay:startDelay,dirPin:dirPin,
		pulPin:pulPin,interval:interval,beforePin:beforePin,backPin:backPin,
		rotateDelay:rotateDelay,startButton:startButton,backButton:backButton,stopButton:stopButton};
};

var updateDrone = function(){
	var drone = m_getFormValues();
	$.ajax({
		type : 'post',
		url : 'updateDrone',
		dataType : 'json',
		data : drone,
		success : function(result) {
			if(result==1){
				window.location.href="setting";
			}else{
				alert("填写信息错误，请检查！");
			}
		},
		error : function(result) {
			alert("填写信息错误，请检查！");
		}
	});
};

var m_changeType = function(v){
	switch (v) {
	case "1":
		$('#m_type1').show();
		$('#m_type2').hide();
		$('#m_type3').hide();
		break;
	case "2":
		$('#m_type1').hide();
		$('#m_type2').show();
		$('#m_type3').hide();
		break;
	case "3":
		$('#m_type1').show();
		$('#m_type2').show();
		$('#m_type3').show();
	break;

	default:
		break;
	}
};

