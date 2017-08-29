<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.apache.commons.lang.StringEscapeUtils" %>
<html>
<head>
<script  src="http://code.jquery.com/jquery-1.12.4.min.js"  integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>

<!-- <link rel='stylesheet prefetch' href='//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css'> -->
<link href="<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/resource/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/resource/css/font-awesome.min.css">
<script type="text/javascript">
$(document).ready(function(){
	check();
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
//==================================================changePW==================================================
function change() {
	$.ajax({
        type: 'post',
        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/emp/pw',
        data: {
        	oldPW: $('#old').val(),
        	newPW: $('#new').val(),
        	checkPW: $('#check').val(),
        },
        dataType: 'json',
        async: false,
        cache: false,
        success: function (data) {
        	if(data.result == "OK"){
        		document.location.href="../index.jsp";
        	}else{
        		$('#warn').html('');
        		$('#warn').append(data.result);
        	}
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
<legend>變更密碼</legend>

<!-- Text input-->
       <div class="form-group">
  <label class="col-md-4 control-label">舊密碼</label>  
    <div class="col-md-4 inputGroupContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="fa fa-unlock-alt" aria-hidden="true"></i></span>
  <input id="old" placeholder="輸入舊密碼" class="form-control"  type="password" value="" />
    </div>
    <small data-bv-validator="notEmpty" data-bv-validator-for="password" class="help-block" style="color: red;">${errorMsg.password}</small>
  </div>
</div>

<!-- Text input-->
       <div class="form-group">
  <label class="col-md-4 control-label">新密碼</label>  
    <div class="col-md-4 inputGroupContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="fa fa-unlock-alt" aria-hidden="true"></i></span>
  <input id="new" placeholder="輸入新密碼" class="form-control"  type="password" value="" />
    </div>
    <small data-bv-validator="notEmpty" data-bv-validator-for="password" class="help-block" style="color: red;"></small>
  </div>
</div>


<!-- Text input-->
       <div class="form-group">
  <label class="col-md-4 control-label">確認新密碼</label>  
    <div class="col-md-4 inputGroupContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="fa fa-unlock-alt" aria-hidden="true"></i></span>
  <input id="check" placeholder="輸入新密碼" class="form-control"  type="password" value="" />
    </div>
    <small id="warn" data-bv-validator="notEmpty" data-bv-validator-for="password" class="help-block" style="color: red;">${errorMsg.password}</small>
  </div>
</div>


<!-- Button -->
<div class="form-group" style="margin-left: 82px;">
  <label class="col-md-4 control-label"></label>
  <div class="col-md-4" style="width: 150px;">
    <button type="button" class="btn btn-warning" onclick="change()">確認</button>
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