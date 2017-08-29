<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.apache.commons.lang.StringEscapeUtils" %>
<html>
<head>
<script  src="http://code.jquery.com/jquery-1.12.4.min.js"  integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>

<link rel='stylesheet prefetch' href='//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css'>
<script src="https://use.fontawesome.com/1c7ea35d90.js"></script>
<link rel="stylesheet" href="<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/resource/css/font-awesome.min.css">
<script type="text/javascript">
$(document).ready(function(){
	check();
	setApplicantDepNo();
	depList();
	aEmpList();
});
//=======================================檢查LoginToken=====================================================
function check(){
	$.ajax({
        type: 'get',
        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/emp/welcome',
        
        dataType: 'json',
        async: false,
        cache: false,
        error: function (data) {
        	document.location.href="<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/employee/login.jsp";
        }
    });
}
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
function insert(){
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
<jsp:include page="/headline.jsp"></jsp:include>
<div class="container">

<form class="well form-horizontal" method="post" id="contact_form">
<fieldset>

<!-- Form Name -->
<legend>資訊服務申請單</legend>

<input id="applicantDep" type="hidden" value="" />

<!-- Select Basic -->

<div class="form-group"> 
  <label class="col-md-4 control-label">申請單類別</label>
    <div class="col-md-4 selectContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
    <select id="type" class="form-control selectpicker" >
         <option value="一般">一般</option>
         <option value="急件">急件</option>
    </select>
  </div>
 </div>
</div>

<!-- Select Basic -->
   
<div class="form-group"> 
  <label class="col-md-4 control-label">申請人</label>
    <div class="col-md-4 selectContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
    <select id="applicant" class="form-control selectpicker" >
    </select>
  </div>
 </div>
</div>

<!-- Select Basic -->
   
<div class="form-group"> 
  <label class="col-md-4 control-label">承辦單位</label>
    <div class="col-md-4 selectContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
    <select id="contractorDep" class="form-control selectpicker" >
    </select>
  </div>
</div>
</div>

<!-- Text input-->
       <div class="form-group">
  <label class="col-md-4 control-label">服務需求</label>  
    <div class="col-md-4 inputGroupContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="fa fa-question-circle-o" aria-hidden="true"></i></span>
  <textarea id="demand" placeholder="問題說明" class="form-control" ></textarea>
    </div>
  </div>
</div>

<!-- Button -->
<div class="form-group" style="margin-left: 82px;">
  <label class="col-md-4 control-label"></label>
  <div class="col-md-4" style="width: 150px;">
    <button type="button" class="btn btn-warning" onclick="insert()">確認</button>
  </div>
  <div class="col-md-4">
    <button type="reset" class="btn btn-warning" name="send" value="cancel">清除</button>
  </div>
</div>

</fieldset>
</form>
</div>
    </div>

</body></html>