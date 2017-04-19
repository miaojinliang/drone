<%@ page contentType="text/html;charset=UTF-8" session="false"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>控制台</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="common/jqueryMobile/jquery.mobile-1.4.5.css">
<script src="common/jqueryMobile/jquery.min.js"></script>
<script src="common/jqueryMobile/jquery.mobile-1.4.5.js"></script>
<script src="common/js/setting.js"></script>
<script src="common/js/droneModify.js"></script>
<style>
th {
    border-bottom: 1px solid #d6d6d6;
}

tr:nth-child(even) {
    background: #e9e9e9;
}
</style>
</head>
<body>
	<div data-role="page" id="droneModify">
      <div data-role="header">
        <h1>修改靶机</h1>
        <a href="setting" id="m_backToMain"  data-transition="pop" class="ui-btn ui-corner-all ui-shadow ui-btn-left ui-btn-b ui-icon-back ui-btn-icon-left">返回</a>
        <a href="#" onclick="updateDrone();" class="ui-btn ui-corner-all ui-shadow ui-btn-right ui-btn-b ui-icon-check ui-btn-icon-left">保存</a>
      </div>

      <div data-role="main" class="ui-content">
        <div class="ui-field-contain">
        <input type="hidden" name="id" value="${drone.id}" id="m_droneId">
	        <label for="m_droneName">名称：</label>
        	<input type="text" name="name" value="${drone.name}" id="m_droneName">
        	
        	<label for="m_droneSeq">序号：</label>
        	<input type="text" name="seq" value="${drone.seq}" id="m_droneSeq">
        	
        	<label for="m_startDelay">起步延迟(秒)：</label>
        	<input type="text" name="startDelay" id="m_startDelay" value="${drone.startDelay}">
        	
        	<label for="m_droneType">靶机类型:</label>
        	<select name="droneType" onchange="m_changeType(this.options[this.options.selectedIndex].value);" id="m_droneType">
	         <option value="1" <c:if test="${ drone.type==1}">selected="selected"</c:if>>单步进电机</option>
	         <option value="2" <c:if test="${ drone.type==2}">selected="selected"</c:if>>单继电器电机</option>
	         <option value="3" <c:if test="${ drone.type==3}">selected="selected"</c:if>>组合电机</option>
	        </select>
       	</div>
        	
       	<div id="type1" data-role="fieldcontain">
        	<label for="m_dirPin">转向Pin：</label>
        	<input type="text" name="dirPin" value="${drone.dirPin}" id="m_dirPin">
        	
        	<label for="m_pulPin">转速Pin：</label>
        	<input type="text" name="pulPin" value="${drone.pulPin}" id="m_pulPin">
        	
        	<label for="m_interval">速度:</label>
        	<select name="interval"  id="m_interval">
	         <option value="1" <c:if test="${ drone.interval==1}">selected="selected"</c:if>>1挡</option>
	         <option value="2" <c:if test="${ drone.interval==2}">selected="selected"</c:if>>2挡</option>
	         <option value="3" <c:if test="${ drone.interval==3}">selected="selected"</c:if>>3挡</option>
	         <option value="4" <c:if test="${ drone.interval==4}">selected="selected"</c:if>>4挡</option>
	         <option value="5" <c:if test="${ drone.interval==5}">selected="selected"</c:if>>5挡</option>
	         <option value="6" <c:if test="${ drone.interval==6}">selected="selected"</c:if>>6挡</option>
	         <option value="7" <c:if test="${ drone.interval==7}">selected="selected"</c:if>>7挡</option>
	         <option value="8" <c:if test="${ drone.interval==8}">selected="selected"</c:if>>8挡</option>
	         <option value="9" <c:if test="${ drone.interval==9}">selected="selected"</c:if>>9挡</option>
	        </select>
       	</div>
        	
       	<div id="m_type2" data-role="fieldcontain">
        	<label for="m_beforePin">正转Pin：</label>
        	<input type="text" name="beforePin" id="m_beforePin" value="${drone.beforePin}">
        	
        	<label for="m_backPin">反转Pin：</label>
        	<input type="text" name="backPin" id="m_backPin" value="${drone.backPin}">
        	
       	</div>
        	
       	<div id="m_type3" data-role="fieldcontain">
        	<label for="m_rotateDelay">旋转延迟(秒)：</label>
        	<input type="text" name="rotateDelay" id="m_rotateDelay" value="${drone.rotateDelay}">
       	</div>
        	
        	
       	<div data-role="fieldcontain">
        	<label for="m_startButton">开始按钮名称：</label>
        	<input type="text" name="startButton" id="m_startButton" value="${drone.startButton}">
        	
        	<label for="m_backButton">反向按钮名称：</label>
        	<input type="text" name="backButton" id="m_backButton" value="${drone.backButton}">
        	
        	<label for="m_stopButton">停止按钮名称：</label>
        	<input type="text" name="stopButton" id="m_stopButton" value="${drone.stopButton}">
        </div>
      </div>
  </div>
</body>
</html>

