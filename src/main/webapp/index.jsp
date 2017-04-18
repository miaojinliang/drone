<!DOCTYPE html>
<html>
<head>
<title>控制台</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="common/jqueryMobile/jquery.mobile-1.4.5.css">
<script src="common/jqueryMobile/jquery.min.js"></script>
<script src="common/jqueryMobile/jquery.mobile-1.4.5.js"></script>
<script src="common/js/login.js"></script>
<style type="text/css">
	table { width:100%; border-spacing: 0; }
	th { text-align:left; }
	th h3 { margin:.6em 0 .6em .5em; }
	th, td { vertical-align:top; border-top:1px solid #eee; padding: 1px 3px; background-color:#fcfcfc; }
	td .ui-btn { margin:.4em 0 .5em 0; }
	td .ui-btn-inner { padding: .4em 15px; }
</style>
</head>
<body>


<div data-role="page">
  <div data-role="header">
  <h1>登陆页</h1>
  </div>

  <div data-role="main" class="ui-content">
      <div class="ui-field-contain">
        <label for="fullname">用户名：</label>
        <input type="text" name="username" id="username">       
        <label for="bday">密码：</label>
        <input type="password" name="password" id="password">
      </div>
      <a id="message"></a>
      <div data-role="main" class="ui-content">
	    <a onclick="checkUser();" class="ui-btn">登陆</a>
	  </div>
  </div>
</div>


</body>
</html>

