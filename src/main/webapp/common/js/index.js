//主体电机动作
var droneMove = function(droneId,raspId,moveType){
//	console.log(droneId+"---"+raspId+"---"+moveType);
	$.ajax({
		type : 'post',
		url : 'motorMove',
		data : {
			droneId : droneId,
			raspId:raspId,
			moveType : moveType
		},
		dataType : 'json',
		success : function(result) {
			console.log("success!");
		},
		error : function(result) {

		}
	});
}

//辅助电机动作
var rotateMove = function(droneId,raspId,moveType){
//	console.log(droneId+"///"+raspId+"///"+moveType);
	$.ajax({
		type : 'post',
		url : 'rotateMove',
		data : {
			droneId : droneId,
			raspId:raspId,
			moveType : moveType
		},
		dataType : 'json',
		success : function(result) {
			console.log("success!");
		},
		error : function(result) {

		}
	});
}

var changeInterval = function(droneId,raspId,value){
	console.log(droneId+"...."+raspId+"..."+value);
	$.ajax({
		type : 'post',
		url : 'changeInterval',
		data : {
			droneId : droneId,
			raspId:raspId,
			value : value
		},
		dataType : 'json',
		success : function(result) {
			console.log("success!");
		},
		error : function(result) {

		}
	});
}