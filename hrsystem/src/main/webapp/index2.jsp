<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="org.apache.commons.lang.StringEscapeUtils" %>
<html>
<head>
<script  src="http://code.jquery.com/jquery-1.12.4.min.js"  integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首頁</title>
<script type="text/javascript">
$(document).ready(function(){
	welcome();
}); 
//=======================================顯示登入者=====================================================
function welcome(){
	$.ajax({
        type: 'get',
        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/emp/welcome',
        
        dataType: 'json',
        async: false,
        cache: false,
        success: function (data) {
        	console.log("success");
        	var bodyHTML = 'Welcome ,'+data.name+'<br>';
        	bodyHTML += '<a href="#" onclick="logout()">登出</a><br>';
            bodyHTML += '<a href="<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/employee/changepwd.jsp">變更密碼</a>';
        	$('#welcome').html('');
        	$('#welcome').append(bodyHTML);
        },
        error: function (data) {
        	console.log("no loginToken");
        }
    });
}
//=======================================登出=====================================================
function logout(){
	$.ajax({
        type: 'get',
        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/emp/logout',
        
        dataType: 'json',
        async: false,
        cache: false,
        success: function (data) {
        	console.log("success");
        	var bodyHTML = '<a href="<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/employee/login.jsp">登入</a>';
        	$('#welcome').html('');
        	$('#welcome').append(bodyHTML);
        },
        error: function (data) {
        	console.log("error");
        }
    });
}
</script>
</head>
<body>
<jsp:include page="/headline.jsp"></jsp:include>
<div id="welcome"><a href="<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/employee/login.jsp">登入</a></div><br>

<a href="<c:url value="/management/DepControl.controller" />">部門資訊管理</a><br>
<a href="<c:url value="/management/JobControl.controller" />">職務資訊管理</a><br>
<a href="<c:url value="/management/EmpControl.controller" />">員工資訊管理</a><br>
<a href="<c:url value="/employee/Register.controller"/>">註冊</a><br>
<a href="infoservice/applicationform.jsp">ISO資訊服務申請單</a><br>
<a href="infoservice/pendinglist.jsp">待處理資訊服務申請單</a><br>
<a href="infoservice/inquiry.jsp">ISO資訊服務申請單查詢</a><br>

</body>
</html>