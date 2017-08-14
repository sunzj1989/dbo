<%--标签 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="edge" />
<script type="text/javascript" src="${staticPath }/scripts/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/scripts/theme/skin.js" charset="utf-8"></script>

<script type="text/javascript">  
	var cssPath = getCookie("cssPath",'${staticPath}'); //获取默认皮肤路径  
	//判断用户Cookie中是否有路径,无采用默认,有采用用户的信息  
	if (cssPath != null && cssPath != ""){   
	    document.write("<link href='${staticPath}/styles/theme/" + cssPath + "/"+cssPath+".css' id='style' rel='stylesheet' type='text/css' />");   
	}else{   
	    setCookie('cssPath',"default",365);   
	    document.write("<link href='${staticPath}/styles/theme/default/default.css' id='style' rel='stylesheet' type='text/css' />");   
	}  
</script> 