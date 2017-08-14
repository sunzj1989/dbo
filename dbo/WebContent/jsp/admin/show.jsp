<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../scripts/jquery-form.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div id="photo_show">
		<c:forEach  items="${imgsList }" var="PhotoEntity">
			<div id="photo_inner" style="width:280px;height:230px;float:left;margin-left:30px;" >
				<img style="width:280px;height:210px;margin-top:0px;" src="<c:url value='/imgs/downLoad?image_id=${PhotoEntity.image_id}&t_name=${PhotoEntity.t_name}' />"/>
				<font style="width:280px;height:20px;margin-bottom:0px;">${PhotoEntity.image_explain}</font>
			</div>
			<input type="hidden" id="image_id" value="${PhotoEntity.image_id}" />
			<input type="hidden" id="t_name" value="${PhotoEntity.t_name}" />
		</c:forEach>
	</div>
</body>
</html>