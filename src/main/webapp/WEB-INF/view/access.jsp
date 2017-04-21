<%@ page contentType="text/html;charset=UTF-8" session="false"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>开关设置</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="common/jqueryMobile/jquery.mobile-1.4.5.css">
<script src="common/jqueryMobile/jquery.min.js"></script>
<script src="common/jqueryMobile/jquery.mobile-1.4.5.js"></script>
<script src="common/js/setting.js"></script>
<script src="common/js/access.js"></script>
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
<div data-role="page" id="accessPage">
  <div data-role="header">
    <h1>${drone.name }-开关管理</h1>
    <a href="${ctx }/setting"   data-transition="pop" class="ui-btn ui-corner-all ui-shadow ui-btn-left ui-btn-b ui-icon-back ui-btn-icon-left">返回</a>
  </div>
  <div data-role="main" class="ui-content" id="accessModify"> 
  	 <form action="addAccess"  method="post">
  	 <fieldset data-role="collapsible">
        <legend>添加开关</legend>
  	 		<input type="hidden" name="droneId" value="${drone.id }" />
  	 		
  	 		<label for="accessName">名称：</label>
        	<input type="text" name="name" id="accessName">
        	
        	<label for="accessType">开关类型:</label>
        	<select name="type" id="accessType">
	         <option value="1">停止开关</option>
	         <option value="2">返回开关</option>
	         <option value="3">旋转停止</option>
	         <option value="3">旋转返回</option>
	        </select>
	        
        	<label for="backDelay">返回延迟(秒)：</label>
        	<input type="text" name="backDelay" id="backDelay">
        	
        	<label for="openStatus">靶机类型:</label>
        	<select name="openStatus" id="openStatus">
	         <option value="1">高电位常开</option>
	         <option value="2">低电位常开</option>
	        </select>
	        
	        <label for="pinNum">Pin针脚：</label>
        	<input type="text" name="pinNum" id="pinNum">
	        
	        <input type="submit" data-inline="true" value="提交">
        </fieldset>
  	 </form>
  </div>
  <div data-role="main" class="ui-content" id="accessMain">
  	<table data-role="table" data-mode="columntoggle" class="ui-responsive" data-column-btn-text="显示/隐藏列">
      <thead>
        <tr>
          <th data-priority="1">名称</th>
          <th data-priority="1">类型</th>
          <th data-priority="1">Pin针脚</th>
          <th data-priority="1">返回延迟(秒)</th>
          <th data-priority="1">开关类型</th>
          <th data-priority="1">操作</th>
        </tr>
      </thead>
      <tbody id="droneTbody">
      <c:forEach items="${accessList}" var="access" varStatus="status">
      	<tr>
          <td>${access.name}</td>
          <td>
          	<c:choose>
	         	<c:when test="${access.type==1}">
		       		停止开关
		     	</c:when>
		     	<c:when test="${access.type==2}">
					返回开关
		     	</c:when>
				<c:when test="${access.type==3}">
		       		旋转停止
				</c:when>
				<c:when test="${access.type==4}">
		       		旋转返回
				</c:when>
				<c:otherwise>
					未知
				</c:otherwise>
			</c:choose>
          </td>
          <td>${ access.pinNum}</td>
          <td>${ access.backDelay} <c:if test="${access.backDelay!=null }">秒</c:if></td>
          <td>
          	<c:choose>
	         	<c:when test="${access.openStatus==1}">
		       		高电位常开
		     	</c:when>
		     	<c:when test="${access.openStatus==2}">
					低电位常开
		     	</c:when>
				<c:otherwise>
					未知
				</c:otherwise>
			</c:choose>
          </td>
          <td>
         	<a href = "${ctx }/deleteAccess?id=${access.id}&droneId=${drone.id}" onclick="return confirm('确定删除?');">删除</a>
         </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
	</div>
</div>
</body>
</html>

