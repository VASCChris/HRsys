<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="org.apache.commons.lang.StringEscapeUtils" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/resource/css/bootstrap.css" rel="stylesheet">
<title>首頁</title>

</head>
<body>
<jsp:include page="/headline.jsp"></jsp:include>

<center><img alt="" src="<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/resource/img/vasc.jpg" style="margin-top: 300px;"></center>

</body>
</html>