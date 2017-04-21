<%@ page contentType="text/html;charset=UTF-8" session="false"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>控制器设置</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="common/jqueryMobile/jquery.mobile-1.4.5.css">
<script src="common/jqueryMobile/jquery.min.js"></script>
<script src="common/jqueryMobile/jquery.mobile-1.4.5.js"></script>
<style>
th {
    border-bottom: 1px solid #d6d6d6;
}

tr:nth-child(even) {
    background: #e9e9e9;
}
</style>


<script type="text/javascript">

var jumpBack = function(fromUrl){
	//alert("http://"+ip+"/"+port+"/drone/settingWithUrl?url=");
	window.location.href = fromUrl;
	
}

var jumpRasp = function(ip,port){
	//alert("http://"+ip+"/"+port+"/drone/settingWithUrl?url=");
	//window.location.href = "http://"+ip+":"+port+"/drone/settingWithUrl?url="+window.location.href;
	
	$('#jumpRaspForm').prop("action","http://"+ip+":"+port+"/drone/settingWithUrl");
	$('#backUrl').val(window.location.href);
	$('#jumpRaspForm').submit();
	
}
</script>
</head>
<body>
<div data-role="page" id="raspPage">

	<form action="" id="jumpRaspForm" method="post">
		<input type="hidden" id="backUrl" name="url"/>
	</form>

  <div data-role="header">
    <h1>控制器</h1>
    <a href="${ctx }/setting"   data-transition="pop" class="ui-btn ui-corner-all ui-shadow ui-btn-left ui-btn-b ui-icon-back ui-btn-icon-left">返回</a>
  </div>
  <div data-role="main" class="ui-content" id="raspAdd"> 
  	 <form action="addRasp"  method="post">
  	 <fieldset data-role="collapsible">
        <legend>添加控制器</legend>
  	 		
  	 		<label for="raspName">名称：</label>
        	<input type="text" name="name" id="raspName">
        	
        	<label for="raspIp">ip地址：</label>
        	<input type="text" name="ip" id="raspIp">
	        
	        <label for="raspPort">端口号：</label>
        	<input type="text" name="port" id="raspPort" value="80">
        	
        	<label for="raspRemark">备注说明：</label>
        	<input type="text" name="remark" id="raspRemark">
	        
	        <input type="submit" data-inline="true" value="提交">
        </fieldset>
  	 </form>
  </div>
  <div data-role="main" class="ui-content" id="raspMain">
  	<table data-role="table" data-mode="columntoggle" class="ui-responsive" data-column-btn-text="显示/隐藏列">
      <thead>
        <tr>
          <th data-priority="1">名称</th>
          <th data-priority="1">ip</th>
          <th data-priority="1">端口号</th>
          <th data-priority="4">备注</th>
          <th data-priority="1">操作</th>
          <th data-priority="1">快速入口</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${raspclients}" var="rasp" varStatus="status">
      	<tr>
          <td>${rasp.name}</td>
          <td>${rasp.ip}</td>
          <td>${rasp.port}</td>
          <td>${rasp.remark}</td>
          <td>
         	<a href = "${ctx }/deleteRasp?id=${rasp.id}" onclick="return confirm('确定删除?');">删除</a>
         </td>
         <td>
         	<a href="#" onclick="jumpRasp('${rasp.ip}','${rasp.port}');">后台管理</a>
         </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
	</div>
</div>
</body>
</html>

