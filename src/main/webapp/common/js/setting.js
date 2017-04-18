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

$(function(){
	$('#type2').hide();
	$('#type3').hide();
//	getDrones();
});

var changeType = function(v){
	switch (v) {
	case "1":
		console.log("case 1")
		$('#type1').show();
		$('#type2').hide();
		$('#type3').hide();
		break;
	case "2":
		$('#type1').hide();
		$('#type2').show();
		$('#type3').hide();
		break;
	case "3":
		$('#type1').show();
		$('#type2').show();
		$('#type3').show();
	break;

	default:
		break;
	}
};

var getFormValues = function(){
	var droneType = $('#droneType').val();
	var droneName = $('#droneName').val();
	var droneSeq = $('#droneSeq').val();
	var startDelay = $('#startDelay').val();
	var dirPin = $('#dirPin').val();
	var pulPin = $('#pulPin').val();
	var interval = $('#interval').val();
	var beforePin = $('#beforePin').val();
	var backPin = $('#backPin').val();
	var rotateDelay = $('#rotateDelay').val();
	var startButton = $('#startButton').val();
	var backButton = $('#backButton').val();
	var stopButton = $('#stopButton').val();
	
	return {type:droneType,name:droneName,seq:droneSeq,startDelay:startDelay,dirPin:dirPin,
		pulPin:pulPin,interval:interval,beforePin:beforePin,backPin:backPin,
		rotateDelay:rotateDelay,startButton:startButton,backButton:backButton,stopButton:stopButton};
};

var saveDrone = function(){
	var drone = getFormValues();
	$.ajax({
		type : 'post',
		url : 'addDrone',
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

var dropType={"1":"单步进点击","2":"单继电器电机","3":"组合电机"};

var getDrones = function(){
	$.ajax({
		type : 'post',
		url : 'getDrones',
		dataType : 'json',
		success : function(result) {
//			$('#droneTbody').html('');
			$(result).each(function (index,drone){
				var tr = $('<tr>');
				$('<td>').html(drone.name).appendTo(tr);
				$('<td>').html(drone.seq).appendTo(tr);
				$('<td>').html(dropType[drone.type]).appendTo(tr);
				$('<td>').html(drone.startDelay).appendTo(tr);
				$('<td>').html(drone.dirPin).appendTo(tr);
				$('<td>').html(drone.pulPin).appendTo(tr);
				$('<td>').html(10-drone.interval).appendTo(tr);
				$('<td>').html(drone.beforePin).appendTo(tr);
				$('<td>').html(drone.backPin).appendTo(tr);
				$('<td>').html(drone.rotateDelay).appendTo(tr);
				$('<td>').html(drone.startButton).appendTo(tr);
				$('<td>').html(drone.backButton).appendTo(tr);
				$('<td>').html(drone.stopButton).appendTo(tr);
				$('<td><a>修改</a>|<a>删除</a></td>').appendTo(tr);
				tr.appendTo('#droneTbody');
			});
		},
		error : function(result) {
			alert("未知错误！");
		}
	});
};

