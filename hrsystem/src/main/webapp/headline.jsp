<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>


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
        	var bodyHTML = '<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">'+data.name+'<span class="caret"></span></a><ul class="dropdown-menu" role="menu"><li><a href="<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/employee/changepwd.jsp">變更密碼</a></li><li class="divider"></li><li><a href="#" onclick="logout()">登出</a></li></ul>';
        	$('#welcome').html('');
        	$('#member').html('');
        	$('#member').append(bodyHTML);
        	if(data.character=="admin" || data.character=="superadmin"){
        		$('#management').html('');
        		var bodyHTML2 = '<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">管理 <span class="caret"></span></a>';
        		bodyHTML2 += '<ul class="dropdown-menu" role="menu">';
        		bodyHTML2 += '<li><a href="<c:url value="/employee/Register.controller"/>">新員工資料註冊</a></li>';
        		bodyHTML2 += '<li class="divider"></li>';
        		bodyHTML2 += '<li><a href="<c:url value="/management/department.jsp"/>">部門資訊管理</a></li>';
        		bodyHTML2 += '<li class="divider"></li>';
        		bodyHTML2 += '<li><a href="<c:url value="/management/job.jsp"/>">職務資訊管理</a></li>';
        		bodyHTML2 += '<li class="divider"></li>';
        		bodyHTML2 += '<li><a href="<c:url value="/management/employee.jsp"/>">員工資訊管理</a></li>';
        		bodyHTML2 += '<li class="divider"></li>';
        		bodyHTML2 += '<li><a href="<c:url value="/management/leaveemployee.jsp"/>">離職員工資訊管理</a></li>';
        		bodyHTML2 += '<li class="divider"></li>';
        		bodyHTML2 += '<li><a href="<c:url value="/infoservice/inquiry.jsp"/>">ISO資訊服務申請單查詢</a></li>';
        		bodyHTML2 += '</ul>';
        		$('#management').append(bodyHTML2);
        	}
        	count();
        },
        error: function (data) {
        	console.log("no loginToken");
        }
    });
}
//=======================================查詢是否有未處理申請單=====================================================
function count(){
	$.ajax({
        type: 'get',
        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/infoservice/count',
        
        dataType: 'json',
        async: false,
        cache: false,
        success: function (data) {
        	console.log("success");
        	$('#mark').html('');
        	if(data!=0){
        		$('#list').prepend('<li id="mark"><img style="position:relative;top: 18px;left: 225px;" src="<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/resource/img/red.png"></li>');
        	}
        },
        error: function (data) {
        	console.log("error");
        	$('#mark').html('');
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
        	$('#member').html('');
        	$('#management').html('');
        	$('#welcome').append(bodyHTML);
        	document.location.href='<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/index.jsp';
        },
        error: function (data) {
        	console.log("error");
        }
    });
}
</script>
</head>

<body>

  <nav class="navbar navbar-default">
     <div class="container-fluid">
      <!-- start collapsed navbar-->
      <div class="navbar-header">
        <button
          type="button" 
          class="navbar-toggle collapsed"
          data-toggle="collapse" 
          data-target="#main_navbar"
          aria-expanded="false">open!</button>       
      </div><!-- /collapsed navbar-->

      <!-- start un-collapsed navbar-->
      <div class="collapse navbar-collapse" id="main_navbar">
        <ul id="list" class="nav navbar-nav">
          
          <li class="active li-header"><a href="<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/index.jsp">首頁</a></li>
          <li><a href="<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/infoservice/applicationform.jsp">ISO資訊服務申請單</a></li>
          <li style="position:relative"><a href="<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/infoservice/pendinglist.jsp">待處理資訊服務申請單</a></li>
          <li id="management" class="dropdown"></li> 
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li id="member" class="dropdown"></li>
          <li id="welcome"><a href="<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/employee/login.jsp">登入</a></li>
        </ul>
      <div>
    </div><!-- /container-->
  </nav>
</body>
<script>
    $('ul.nav li.dropdown').hover(function() {
      $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn(500);
      }, function() {
          $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeOut(500);
    });
</script>
</html>
