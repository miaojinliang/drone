<!DOCTYPE html>
<html>
<head>
<title>控制台</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="common/jqueryMobile/jquery.mobile-1.4.5.css">
<script src="common/jqueryMobile/jquery.min.js"></script>
<script src="common/jqueryMobile/jquery.mobile-1.4.5.js"></script>
<script src="common/js/index.js"></script>
</head>
<body>
<div data-role="page" id="pageone">
  <div data-role="header">
    <h1>Haier</h1>
  </div>
	控制马达编号<input type="text" id="con_motor" />
	<div data-role="main" class="ui-content">
    	<input type="button" value="前进" onclick="motorMove(0);">
  	</div>
	
	<div data-role="main" class="ui-content">
    	<input type="button" value="暂停" onclick="motorPuase();">
  	</div>
	
	<div data-role="main" class="ui-content">
    	<input type="button" value="后退" onclick="motorMove(1);">
  	</div>
  	
	方向pin：<input type="text" id="dirPin" />
	脉冲pin：<input type="text" id="pulPin" />
	马达编号：<input type="text" id="motorNum" />
	<div data-role="main" class="ui-content">
    	<input type="button" value="增加马达" onclick="addMotors();">
  	</div>
	
	
	<div data-role="main" class="ui-content">
    	<input type="button" value="列出马达" onclick="listMotors();">
  	</div>
  <div data-role="footer">
    <h1>联系电话：133xxxxxxxx</h1>
  </div>
</div> 


</body>
</html>

