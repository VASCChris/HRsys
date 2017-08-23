<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.apache.commons.lang.StringEscapeUtils" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script  src="http://code.jquery.com/jquery-1.12.4.min.js"  integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
<script language="JavaScript" type="text/javascript" src="<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/resource/js/WdatePicker.js"></script>

<title>TEST</title>
<script>
function pEnd(){
	var x = "'";
	var y = 'WdatePicker({skin:"whyGreen",dateFmt:"yyyy-MM-dd HH:mm",minDate:"'+$('#pStartTime').val()+'"})';
	var bodyHTML = '<input type="text" class="Wdate" id="d412" onclick='+x+y+x+' value=""/>';
	$('#pEnd').append(bodyHTML);
}
</script>
</head>
<body>
<!-- <input type="text" class="Wdate" id="d412" onclick='WdatePicker({skin:"whyGreen",dateFmt:"yyyy-MM-dd HH:mm"})' value=""/> -->

<input type="text" class="Wdate" id="pStartTime" onclick='WdatePicker({skin:"whyGreen",dateFmt:"yyyy-MM-dd HH:mm"}),pEnd()' value=""/>
<div id="pEnd"></div>
</body>
</html>