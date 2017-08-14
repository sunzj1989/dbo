<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE>
<html>
<head>
<%@ include file="/commons/msunsoftjs.jsp" %>
<%@ include file="/commons/basejs.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Basic Layout</title>
<script type="text/javascript">
</script>
</head>
<body >
	<select onchange="changeCss(window.top,this.value)">  
            <option value="default" selected="selected">  
                                                       默认皮肤  
            </option>  
            <option value="red">  
                                                       红色 
            </option>  
            <option value="blue">  
                                                      藍色 
            </option>  
        </select>
</body>
</html>