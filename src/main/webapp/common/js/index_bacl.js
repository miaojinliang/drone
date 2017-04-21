var motorMove = function(motorNum,direction,e) {
	$(e).parent().parent().find(".ui-state-disabled").each(function(index,element){
//		if($(this).prop("id").startWith("for-move-")||$(this).prop("id").startWith("puase-motor-")||$(this).prop("id").startWith("back-move-")){
			$(this).removeClass("ui-state-disabled");
//		}
	});
	$(e).addClass("ui-state-disabled");
	console.log("转动，马达编号："+motorNum);
	console.log(direction);
	$.ajax({
		type : 'post',
		url : 'motorMove',
		data : {
			moterNum : motorNum,
			direction : direction
		},
		dataType : 'json',
		success : function(result) {
			
		},
		error : function(result) {

		}
	});
}


var relayBefore = function(){
	$.ajax({
		type : 'post',
		url : 'relayBefore',
		dataType : 'json',
		success : function(result) {
			
		},
		error : function(result) {

		}
	});
}


var relayBack = function(){
	$.ajax({
		type : 'post',
		url : 'relayBack',
		dataType : 'json',
		success : function(result) {
			
		},
		error : function(result) {

		}
	});
}

var relayStop = function(){
	$.ajax({
		type : 'post',
		url : 'relayStop',
		dataType : 'json',
		success : function(result) {
			
		},
		error : function(result) {

		}
	});
}


var allMotorMove = function(direction){
	if($('#motorTable').find("input:checkbox:checked").length<=0){
		$('#popupDialog').popup( "open",{ x: 500, y: 500 });
	}
	$('#motorTable').find("input:checkbox:checked").each(function(index,element){
		var motorNum = $(element).prop("id").split("-")[1];
		var moveBtnId = "for-move-";
		if(direction==1){
			moveBtnId = "back-move-";
		}
		motorMove(motorNum, direction, $('#'+moveBtnId+motorNum));
	});
}
var allMotorPuase = function(){
	if($('#motorTable').find("input:checkbox:checked").length<=0){
		alert("没有选中要控制的设备！");
	}
	$('#motorTable').find("input:checkbox:checked").each(function(index,element){
		var motorNum = $(element).prop("id").split("-")[1];
		motorPuase(motorNum,$('#puase-motor-'+motorNum));
	});
}


var motorPuase = function(motorNum,e) {
	$(e).parent().parent().find(".ui-state-disabled").each(function(index,element){
		$(this).removeClass("ui-state-disabled");
	});
	$(e).addClass("ui-state-disabled");
	console.log("暂停，马达编号："+motorNum);
	$.ajax({
		type : 'post',
		url : 'motorPuase',
		data : {
			motorNum : motorNum
		},
		dataType : 'json',
		success : function(result) {

		},
		error : function(result) {

		}
	});
}

var addMotors = function() {
	$.ajax({
		type : 'post',
		url : 'addMotors',
		data : {
			dirPin : $('#dirPin').val(),
			pulPin : $('#pulPin').val(),
			motorNum : $('#motorNum').val()
		},
		dataType : 'json',
		success : function(result) {

		},
		error : function(result) {

		}
	});
}

var allMotorInterval = function(intervalMth){
	if($('#motorTable').find("input:checkbox:checked").length<=0){
		alert("没有选中要控制的设备！");
	}
	$('#motorTable').find("input:checkbox:checked").each(function(index,element){
		var motorNum = $(element).prop("id").split("-")[1];
		setInterval(motorNum, intervalMth)
	});
}

//最大10个档
var setInterval = function(motorNum,intervalMth) {
	if(intervalMth==UP){
		if($('#up_btn_'+motorNum).hasClass("ui-state-disabled")){
			return;
		}
	}else if(intervalMth==DOWN){
		if($('#down_btn_'+motorNum).hasClass("ui-state-disabled")){
			return;
		}
	}
	$.ajax({
		type : 'post',
		url : 'setInterval',
		data:{
			motorNum:motorNum,
			intervalMth:intervalMth
		},
		dataType : 'json',
		success : function(result) {
			if(result>=9){
				if(!$('#up_btn_'+motorNum).hasClass("ui-state-disabled")){
					$('#up_btn_'+motorNum).addClass("ui-state-disabled");
				}
			}else if(result<=1){
				if(!$('#down_btn_'+motorNum).hasClass("ui-state-disabled")){
					$('#down_btn_'+motorNum).addClass("ui-state-disabled");
				}
			}else{
				if($('#up_btn_'+motorNum).hasClass("ui-state-disabled")){
					$('#up_btn_'+motorNum).removeClass("ui-state-disabled");
				}
				if($('#down_btn_'+motorNum).hasClass("ui-state-disabled")){
					$('#down_btn_'+motorNum).removeClass("ui-state-disabled");
				}
			}
			$('#speed_'+motorNum).html(result);
			
		},
		error : function(result) {

		}
	});
}

var listMotors = function() {
	$.ajax({
		type : 'post',
		url : 'listMotors',
		dataType : 'json',
		success : function(result) {

		},
		error : function(result) {

		}
	});
}


var UP="UP";
var DOWN="DOWN";

var getMotors = function() {
	$.ajax({
		type : 'post',
		url : 'getMotors',
		dataType : 'json',
		success : function(result) {
			$('#motorTable').html("");
			//群体控制
			var tr = $('<tr>');
			$('<td></td>').appendTo(tr);
			$('<th><h2>群体控制</h2></th>').appendTo(tr);
			$('<td><a class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-icon-left ui-icon-arrow-l" onclick="allMotorMove(0)">开始</a></td>').appendTo(tr);
			$('<td><a class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-icon-left ui-icon-arrow-forbidden" onclick="allMotorPuase()">暂停</a></td>').appendTo(tr);
			$('<td><a class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-icon-left ui-icon-arrow-r" onclick="allMotorMove(1)">返回</a></td>').appendTo(tr);
			$('<td><a href="#" class="ui-btn ui-btn-inline ui-btn-icon-left ui-icon-plus" onclick="allMotorInterval(UP)">加速</a></td>').appendTo(tr);
			$('<td><h3></h3></td>').appendTo(tr);
			$('<td><a href="#" class="ui-btn ui-btn-inline ui-btn-icon-left ui-icon-minus" onclick="allMotorInterval(DOWN)">减速</a></td>').appendTo(tr);
			tr.appendTo("#motorTable");
			for(var index in result){
				motor = result[index];
				console.log(motor)
				var tr = $('<tr>');
				$('<td><input type="checkbox" id="checkbox-'+motor.motorNum+'" name="checkbox-'+motor.motorNum+'" class="ui-btn-inline"></td>').appendTo(tr);
				$('<th><h3>'+motor.motorNum+'号马达</h3></th>').appendTo(tr);
				var status = 0;//0：暂停，1：前进
				var direction = motor.direction;
				var running = motor.running;
				$('<td><a id="for-move-'+motor.motorNum+'" class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-icon-left ui-icon-arrow-l '+(direction==0&&running==true?"ui-state-disabled":"")+'" onclick="motorMove('+motor.motorNum+',0,this)">开始</a></td>').appendTo(tr);
				$('<td><a id="puase-motor-'+motor.motorNum+'" class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-icon-left ui-icon-arrow-forbidden '+(running==false?"ui-state-disabled":"")+'" onclick="motorPuase('+motor.motorNum+',this)">暂停</a></td>').appendTo(tr);
				$('<td><a id="back-move-'+motor.motorNum+'" class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-icon-left ui-icon-arrow-r '+(direction==1&&running==true?"ui-state-disabled":"")+'" onclick="motorMove('+motor.motorNum+',1,this)">返回</a></td>').appendTo(tr);
				$('<td><a href="#" class="ui-btn ui-btn-inline ui-btn-icon-left ui-icon-plus '+((10-motor.interval)>=9?"ui-state-disabled":"")+'" id="up_btn_'+motor.motorNum+'" onclick="setInterval('+motor.motorNum+','+UP+')">加速</a></td>').appendTo(tr);
				$('<td><h3>当前速度：<a id="speed_'+motor.motorNum+'">'+(10-motor.interval)+'</a></h3></td>').appendTo(tr);
				$('<td><a href="#" class="ui-btn ui-btn-inline ui-btn-icon-left ui-icon-minus '+((10-motor.interval)<=1?"ui-state-disabled":"")+'" id="down_btn_'+motor.motorNum+'"  onclick="setInterval('+motor.motorNum+','+DOWN+')">减速</a></td>').appendTo(tr);
				tr.appendTo("#motorTable");
			}
		},
		error : function(result) {

		}
	});
}

$(function() {
	getMotors();
});