<%@ page contentType="text/html;charset=UTF-8" session="false"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
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
<!-- <div data-role="popup" id="popupDialog" data-overlay-theme="b" data-theme="b" data-dismissible="false" style="max-width:400px;">
    <div data-role="header" data-theme="a">
    <h1>提示</h1>
    </div>
    <div role="main" class="ui-content">
        <h3 class="ui-title">请选择需要控制的设备！</h3>
        <a href="#" class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-b" data-rel="back">关闭</a>
    </div>
</div> -->
<div data-role="page" id="pageCenter">
  <div data-role="header">
    <h1>中心控制台</h1>
    <a href="${ctx }/auto"  data-transition="pop" class="ui-btn ui-btn-right ui-corner-all ui-shadow ui-icon-gear ui-btn-icon-left">自动化控制</a>
  </div>
  <div data-role="main" class="ui-content">
  
  <c:forEach items="${allDrones}" var="drone" varStatus="status">
  	<div data-role="collapsible" data-collapsed="false" data-collapsed-icon="carat-d" data-expanded-icon="carat-u">
        <h3>${drone.name }</h3>
	      <a href="#" class="ui-btn ui-btn-inline" onclick="droneMove('${drone.id}','${drone.raspClient.id }','START')" >${drone.startButton }</a>
	      <a href="#" class="ui-btn ui-btn-inline" onclick="droneMove('${drone.id}','${drone.raspClient.id }','STOP')" >${drone.stopButton }</a>
	      <a href="#" class="ui-btn ui-btn-inline" onclick="droneMove('${drone.id}','${drone.raspClient.id }','BACK')" >${drone.backButton }</a>
	      <c:if test="${drone.type==3 }">
	      	 &nbsp;|&nbsp;&nbsp;
	      <a href="#" class="ui-btn ui-btn-inline" onclick="rotateMove('${drone.id}','${drone.raspClient.id }','START')" >${drone.rotateStart }</a>
	      <a href="#" class="ui-btn ui-btn-inline" onclick="rotateMove('${drone.id}','${drone.raspClient.id }','STOP')">${drone.rotateStop }</a>
	      <a href="#" class="ui-btn ui-btn-inline" onclick="rotateMove('${drone.id}','${drone.raspClient.id }','BACK')">${drone.rotateBack }</a>
	      </c:if>
	      <c:if test="${drone.type!=2 }">
	      	<input type="range" name="points" id="points" onchange="changeInterval('${drone.id}','${drone.raspClient.id }',this.value)" value="${drone.interval }" min="1" max="9" data-popup-enabled="true">
      	</c:if>
      </div>
  </c:forEach>
  </div>
  <div data-role="footer">
    <h1>联系电话：133xxxxxxxx</h1>
  </div>
</div> 


</body>
</html>

