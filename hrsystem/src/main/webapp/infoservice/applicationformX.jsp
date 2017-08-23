<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.apache.commons.lang.StringEscapeUtils" %>
<html>
<head>
<script  src="http://code.jquery.com/jquery-1.12.4.min.js"  integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(document).ready(function(){
	setApplicantDepNo();
	depList();
	aEmpList();
});
//==================================================抓取applicantDepNo==================================================
function setApplicantDepNo() {
	$.ajax({
        type: 'get',
        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/infoservice/get/applicantdep',
     
        dataType: 'json',
        async: false,
        cache: false,
        success: function (data) {
        	console.log("x="+data.applicantDepNo);
        	$('#applicantDep').val(data.applicantDepNo);
        },
        error: function (data) {
        	console.log("error");
        }
    });
}
// ==================================================dep列表==================================================
function depList() {
	$.ajax({
        type: 'get',
        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/dep/list',
     
        dataType: 'json',
        async: false,
        cache: false,
        success: function (data) {
        	for (var i = 0; i < data.length; i++) {
        		addDep(data[i]);
        	}
        }
    });
}
//==================================================抓dep列表==================================================
function addDep(data){
	var bodyHTML = '<option value="'+ data.no +'">'+ data.name +'</option>';
	$('#contractorDep').append(bodyHTML);
}
//==================================================申請人列表==================================================
function aEmpList() {
	$.ajax({
        type: 'get',
        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/emp/empsfordep',
        data: {
        	depNum: $('#applicantDep').val(),
        },
        dataType: 'json',
        async: false,
        cache: false,
        success: function (data) {
        	$('#applicant').html('');
        	for (var i = 0; i < data.length; i++) {
        		aEmpAdd(data[i]);
        	}
        }
    });
}
//==================================================抓申請人列表==================================================
function aEmpAdd(data){
	var bodyHTML = '<option value="'+ data.id +'">'+ data.name +'</option>';
	$('#applicant').append(bodyHTML);
}
//==================================================承辦人列表==================================================
function cEmpList() {
	$.ajax({
        type: 'get',
        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/emp/empsfordep',
        data: {
        	depNum: $('#contractorDep').val(),
        },
        dataType: 'json',
        async: false,
        cache: false,
        success: function (data) {
        	$('#contractor').html('');
        	for (var i = 0; i < data.length; i++) {
        		cEmpAdd(data[i]);
        	}
        }
    });
}
//==================================================抓承辦人列表==================================================
function cEmpAdd(data){
	var bodyHTML = '<option value="'+ data.id +'">'+ data.name +'</option>';
	$('#contractor').append(bodyHTML);
}
//==================================================服務列表==================================================
function serviceType() {
	$.ajax({
        type: 'get',
        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/servicetype/list',
        
        dataType: 'json',
        async: false,
        cache: false,
        success: function (data) {
        	console.log("A="+data);
        	for (var i = 0; i < data.length; i++) {
        		serviceTypeAdd(data[i]);
        	}
        }
    });
}
//==================================================抓服務列表==================================================
function serviceTypeAdd(data){
	var bodyHTML = '<option value="'+ data.no +'">'+ data.name +'</option>';
	$('#serviceType').append(bodyHTML);
}
//==================================================資安等級==================================================
function infoSecurity(){
	var event = $('#eventType').val();
	$('#security').html('');
	if(event=="資安事件"){
		$.ajax({
	        type: 'get',
	        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/securitylv/list',
	        
	        dataType: 'json',
	        async: false,
	        cache: false,
	        success: function (data) {
	        	for (var i = 0; i < data.length; i++) {
	        		securityLvAdd(data[i]);
	        	}
	        }
	    });
	}
}
//==================================================抓資安等級==================================================
function securityLvAdd(data){
	var bodyHTML = '<input type="radio" id="securityLv" name="securityLv" value="'+ data.lv +'">'+ data.content +'</input><br>';
	$('#security').append(bodyHTML);
}
//==================================================送出==================================================
function send(){
	$.ajax({
        type: 'post',
        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/infoservice/save',
        data: {
        	type: $('#type').val(),
        	applicant: $('#applicant').val(),
        	applicantDep: $('#applicantDep').val(),
        	contractorDep: $('#contractorDep').val(),
        	demand: $('#demand').val(),
        },
        dataType: 'json',
        async: false,
        cache: false,
        success: function (data) {
        	console.log("success");
        	document.location.href="../index.jsp";
        },
        error: function (data) {
        	console.log("error");
        }
    });
}
</script>
</head>
<body>
申請單類別:<select id="type"><option value="一般">一般</option><option value="急件">急件</option></select><br><br>
<input id="applicantDep" type="hidden" value="" />
申請人:<select id="applicant"><option value="0">請先選擇部門</option></select><br><br>
承辦單位:<select id="contractorDep" onchange="cEmpList()"><option value="0">選擇部門</option></select><br><br>
服務需求:<br>
<textarea id="demand" placeholder="問題說明" value=""></textarea><br><br>

<Button type="button" value="send" onclick="send()">送出</Button><br>
<a href="../index.jsp">上一頁</a>
</body>
</html>