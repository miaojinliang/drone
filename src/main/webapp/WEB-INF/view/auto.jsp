<%@ page contentType="text/html;charset=UTF-8" session="false"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>自动化控制</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="common/jqueryMobile/jquery.mobile-1.4.5.css">
<script src="common/jqueryMobile/jquery.min.js"></script>
<script src="common/jqueryMobile/jquery.mobile-1.4.5.js"></script>
<script src="common/js/auto.js"></script>
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
<div data-role="page" id="autoMain">
  <div data-role="header">
  <a data-transition="pop" href="${ctx }/index" class="ui-btn ui-corner-all ui-shadow ui-icon-back ui-btn-icon-left">返回</a>
    <h1>自动化控制</h1>
    <a href="#addPlan"  data-transition="pop" class="ui-btn ui-btn-right ui-corner-all ui-shadow ui-icon-plus ui-btn-icon-left">添加控制方案</a>
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


<div data-role="page" id="addPlan">
      <div data-role="header">
        <h1>添加靶机</h1>
        <a href="#autoMain"  data-transition="pop" class="ui-btn ui-corner-all ui-shadow ui-btn-left ui-btn-b ui-icon-back ui-btn-icon-left">返回</a>
        <a href="#" onclick="savePlan();" class="ui-btn ui-corner-all ui-shadow ui-btn-right ui-btn-b ui-icon-check ui-btn-icon-left">保存</a>
      </div>

      <div data-role="main" class="ui-content">
        <div class="ui-field-contain">
	        <label for="droneName">名称：</label>
        	<input type="text" name="name" id="droneName">
        	
        	<label for="droneSeq">序号：</label>
        	<input type="text" name="seq" id="droneSeq">
        	
        	<label for="startDelay">起步延迟(秒)：</label>
        	<input type="text" name="startDelay" id="startDelay">
        	
        	<label for="droneType">靶机类型:</label>
        	<select name="droneType" onchange="changeType(this.options[this.options.selectedIndex].value);" id="droneType">
	         <option value="1">单步进电机</option>
	         <option value="2">单继电器电机</option>
	         <option value="3">组合电机</option>
	        </select>
	        
       	</div>
        	
       	<div id="type1" data-role="fieldcontain" >
        	<label for="dirPin">转向Pin：</label>
        	<input type="text" name="dirPin" id="dirPin">
        	
        	<label for="pulPin">转速Pin：</label>
        	<input type="text" name="pulPin" id="pulPin">
        	
        	<label for="interval">速度:</label>
        	<select name="interval"  id="interval">
	         <option value="1">1挡</option>
	         <option value="2">2挡</option>
	         <option value="3">3挡</option>
	         <option value="4">4挡</option>
	         <option selected="selected" value="5">5挡</option>
	         <option value="6">6挡</option>
	         <option value="7">7挡</option>
	         <option value="8">8挡</option>
	         <option value="9">9挡</option>
	        </select>
       	</div>
        	
       	<div id="type2" data-role="fieldcontain">
        	<label for="beforePin">正转Pin：</label>
        	<input type="text" name="beforePin" id="beforePin">
        	
        	<label for="backPin">反转Pin：</label>
        	<input type="text" name="backPin" id="backPin">
        	
       	</div>
        	
       	<div id="type3" data-role="fieldcontain">
        	<label for="rotateDelay">旋转延迟(秒)：</label>
        	<input type="text" name="rotateDelay" id="rotateDelay">
        	
        	<label for="rotateStart">开始旋转按钮：</label>
        	<input type="text" name="rotateStart" id="rotateStart" value="正向旋转">
        	
        	<label for="rotateBack">反向旋转按钮：</label>
        	<input type="text" name="rotateBack" id="rotateBack" value="反向旋转">
        	
        	<label for="rotateStop">停止旋转按钮：</label>
        	<input type="text" name="rotateStop" id="rotateStop" value="停止旋转">
       	</div>
        	
        	
       	<div data-role="fieldcontain">
        	<label for="startButton">开始按钮名称：</label>
        	<input type="text" name="startButton" id="startButton" value="开始">
        	
        	<label for="backButton">反向按钮名称：</label>
        	<input type="text" name="backButton" id="backButton" value="返回">
        	
        	<label for="stopButton">停止按钮名称：</label>
        	<input type="text" name="stopButton" id="stopButton" value="暂停">
        </div>
      </div>
  </div>

</body>
</html>

