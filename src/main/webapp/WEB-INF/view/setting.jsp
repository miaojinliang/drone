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

<div data-role="page" id="mainPage">
  <div data-role="header">
    <h1>靶机列表</h1>
    <a href="#addPage"  data-transition="flip" class="ui-btn ui-btn-right ui-corner-all ui-shadow ui-icon-plus ui-btn-icon-left">添加靶机</a>
  </div>
  <div data-role="main" class="ui-content" id="droneMain">
  	<table data-role="table" data-mode="columntoggle" class="ui-responsive" data-column-btn-text="显示/隐藏列">
      <thead>
        <tr>
          <th data-priority="1">名称</th>
          <th data-priority="1">序号</th>
          <th data-priority="1">类型</th>
          <th data-priority="6">起步延迟时间</th>
          <th data-priority="2">转向Pin</th>
          <th data-priority="2">转速Pin</th>
          <th data-priority="3">速度</th>
          <th data-priority="2">正转Pin</th>
          <th data-priority="2">反转Pin</th>
          <th data-priority="6">旋转延迟</th>
          <th data-priority="6">开始按钮</th>
          <th data-priority="6">返回按钮</th>
          <th data-priority="6">停止按钮</th>
          <th data-priority="1">操作</th>
        </tr>
      </thead>
      <tbody id="droneTbody">
      <c:forEach items="${dronesList}" var="drone" varStatus="status">
      	<tr>
          <td>${ drone.name}</td>
          <td>${ drone.seq}</td>
          <td>
			<c:choose>
	         	<c:when test="${drone.type==1}">
		       		单步进点击
		     	</c:when>
		     	<c:when test="${drone.type==2}">
					单继电器电机
		     	</c:when>
				<c:when test="${drone.type==3}">
		       		组合电机
				</c:when>
				<c:otherwise>
					未知
				</c:otherwise>
			</c:choose>
          </td>
          <td>${ drone.startDelay}</td>
          <td>${ drone.dirPin}</td>
          <td>${ drone.pulPin}</td>
          <td>${ 10-drone.interval}</td>
          <td>${ drone.beforePin}</td>
          <td>${ drone.backPin}</td>
          <td>${ drone.rotateDelay}</td>
          <td>${ drone.startButton}</td>
          <td>${ drone.backButton}</td>
          <td>${ drone.stopButton}</td>
          <td><a>修改</a>|<a>删除</a></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
      <!-- <div data-role="collapsible" data-collapsed="false">
        <h3>靶机名称            执行顺序</h3>
	      <a href="#" class="ui-btn ui-btn-inline">前进</a>
	      <a href="#" class="ui-btn ui-btn-inline">暂停</a>
	      <a href="#" class="ui-btn ui-btn-inline">后退</a>
	      &nbsp;|&nbsp;
	      <a href="#" class="ui-btn ui-btn-inline">修改</a>
	      <a href="#" class="ui-btn ui-btn-inline">删除</a>
	      	<input type="range" name="points" id="points" value="5" min="1" max="9" data-popup-enabled="true">
      </div> -->
	</div>
</div>

	<div data-role="page" id="addPage">
      <div data-role="header">
        <h1>添加靶机</h1>
        <a href="#mainPage" id="backToMain"  data-transition="flip" class="ui-btn ui-corner-all ui-shadow ui-btn-left ui-btn-b ui-icon-back ui-btn-icon-left">返回</a>
        <a href="#" onclick="saveDrone();" class="ui-btn ui-corner-all ui-shadow ui-btn-right ui-btn-b ui-icon-check ui-btn-icon-left">保存</a>
      </div>

      <div data-role="main" class="ui-content">
        <div class="ui-field-contain">
        	<label for="droneType">靶机类型:</label>
        	<select name="droneType" onchange="changeType(this.options[this.options.selectedIndex].value);" id="droneType">
	         <option value="1">单步进电机</option>
	         <option value="2">单继电器电机</option>
	         <option value="3">组合电机</option>
	        </select>
	        
	        <label for="droneName">名称：</label>
        	<input type="text" name="name" id="droneName">
        	
        	<label for="droneSeq">序号：</label>
        	<input type="text" name="seq" id="droneSeq">
        	
        	<label for="startDelay">起步延迟：</label>
        	<input type="text" name="startDelay" id="startDelay">
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
        	<label for="rotateDelay">旋转延迟：</label>
        	<input type="text" name="rotateDelay" id="rotateDelay">
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

