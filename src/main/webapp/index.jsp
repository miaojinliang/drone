<!DOCTYPE html>
<html>
<head>
<title>控制台</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="common/jqueryMobile/jquery.mobile-1.4.5.css">
<script src="common/jqueryMobile/jquery.min.js"></script>
<script src="common/jqueryMobile/jquery.mobile-1.4.5.js"></script>
<script src="common/js/index.js"></script>
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
<div data-role="popup" id="popupDialog" data-overlay-theme="b" data-theme="b" data-dismissible="false" style="max-width:400px;">
    <div data-role="header" data-theme="a">
    <h1>提示</h1>
    </div>
    <div role="main" class="ui-content">
        <h3 class="ui-title">请选择需要控制的设备！</h3>
        <a href="#" class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-b" data-rel="back">关闭</a>
    </div>
</div>
<div data-role="page" id="pageone">
  <div data-role="header">
    <h1>步进电机控制台</h1>
  </div>
	<table margin="0" id="motorTable" >
<!-- 		<tbody>
			<tr>
				<th><h3>1号马达</h3></th>
				<td><a class="ui-btn ui-corner-all ui-shadow ui-btn-inline">前进</a></td>
				<td><a class="ui-btn ui-corner-all ui-shadow ui-btn-inline">暂停</a></td>
				<td><a class="ui-btn ui-corner-all ui-shadow ui-btn-inline">后退</a></td>
			</tr>
			<tr>
				<th><h3>2号马达</h3></th>
				<td><a class="ui-btn ui-corner-all ui-shadow ui-btn-inline">前进</a></td>
				<td><a class="ui-btn ui-corner-all ui-shadow ui-btn-inline">暂停</a></td>
				<td><a class="ui-btn ui-corner-all ui-shadow ui-btn-inline">后退</a></td>
			</tr>
		</tbody> -->
	</table>
	
	<div data-role="header">
    <h1>继电器控制台</h1>
  </div>
	<table margin="0" >
		<tbody>
			<tr>
				<th><h3>继电器机组</h3></th>
				<td><a class="ui-btn ui-corner-all ui-shadow ui-btn-inline" onclick="relayBefore();">A（正传）</a></td>
				<td><a class="ui-btn ui-corner-all ui-shadow ui-btn-inline" onclick="relayBack();">B（反转）</a></td>
				<td><a class="ui-btn ui-corner-all ui-shadow ui-btn-inline" onclick="relayStop();">C（全部停止）</a></td>
			</tr>
		</tbody>
	</table>
  <div data-role="footer">
    <h1>联系电话：133xxxxxxxx</h1>
  </div>
</div> 


</body>
</html>

