<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.apache.commons.lang.StringEscapeUtils" %>
<html>
<head>
<script type="text/javascript" src="<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/resource/js/jquery-1.11.2.min.js"></script>
<link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css'><script src='https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js'></script>
<link href="<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/resource/css/bootstrap.css" rel="stylesheet">
<style class="cp-pen-styles">body {
	background: #fafafa url(https://jackrugile.com/images/misc/noise-diagonal.png);
	color: #444;
	font: 100%/30px 'Helvetica Neue', helvetica, arial, sans-serif;
	text-shadow: 0 1px 0 #fff;
}

strong {
	font-weight: bold; 
}

em {
	font-style: italic; 
}

table {
	background: #f5f5f5;
	border-collapse: separate;
	box-shadow: inset 0 1px 0 #fff;
	font-size: 12px;
	line-height: 24px;
	margin: 30px auto;
	text-align: left;
	width: 800px;
}	

th {
	background: url(https://jackrugile.com/images/misc/noise-diagonal.png), linear-gradient(#777, #444);
	border-left: 1px solid #555;
	border-right: 1px solid #777;
	border-top: 1px solid #555;
	border-bottom: 1px solid #333;
	box-shadow: inset 0 1px 0 #999;
	color: #fff;
  font-weight: bold;
	padding: 10px 15px;
	position: relative;
	text-shadow: 0 1px 0 #000;	
}

th:after {
	background: linear-gradient(rgba(255,255,255,0), rgba(255,255,255,.08));
	content: '';
	display: block;
	height: 25%;
	left: 0;
	margin: 1px 0 0 0;
	position: absolute;
	top: 25%;
	width: 100%;
}

th:first-child {
	border-left: 1px solid #777;	
	box-shadow: inset 1px 1px 0 #999;
}

th:last-child {
	box-shadow: inset -1px 1px 0 #999;
}

td {
	border-right: 1px solid #fff;
	border-left: 1px solid #e8e8e8;
	border-top: 1px solid #fff;
	border-bottom: 1px solid #e8e8e8;
	padding: 10px 15px;
	position: relative;
	transition: all 300ms;
}

td:first-child {
	box-shadow: inset 1px 0 0 #fff;
}	

td:last-child {
	border-right: 1px solid #e8e8e8;
	box-shadow: inset -1px 0 0 #fff;
}	

tr {
	background: url(https://jackrugile.com/images/misc/noise-diagonal.png);	
}

tr:nth-child(odd) td {
	background: #f1f1f1 url(https://jackrugile.com/images/misc/noise-diagonal.png);	
}

tr:last-of-type td {
	box-shadow: inset 0 -1px 0 #fff; 
}

tr:last-of-type td:first-child {
	box-shadow: inset 1px -1px 0 #fff;
}	

tr:last-of-type td:last-child {
	box-shadow: inset -1px -1px 0 #fff;
}	

/* tbody:hover td { */
/* 	color: transparent; */
/* 	text-shadow: 0 0 3px #aaa; */
/* } */

/* tbody:hover tr:hover td { */
/* 	color: #444; */
/* 	text-shadow: 0 1px 0 #fff; */
/* } */
</style>

<script type="text/javascript">

$(document).ready(function(){
	check();
	list();
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
//==================================================job列表==================================================
function list() {
	$.ajax({
        type: 'get',
        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/job/list',
     
        dataType: 'json',
        async: false,
        cache: false,
        success: function (data) {
        	$('#jobList').html('');
        	for (var i = 0; i < data.length; i++) {
        		addJob(data[i]);
        	}
        	var bodyHTML = '<tr>';
        	
        	bodyHTML += '<td>新增職務</td>';
        	bodyHTML += '<td><input type="text" id="names" value=""></td>';
        	bodyHTML += '<td><button type="button" name="send" onclick="save()">新增</button></td></tr>';
        	
        	$('#jobList').append(bodyHTML);

        }
    });
}
//==================================================抓job列表==================================================
function addJob(data){
	var bodyHTML = '<tr>';
	bodyHTML += '<td>' + data.no +'</td>';
	bodyHTML += '<td><input id="name' + data.no +'" type="text" value="' + data.name +'"></td>';
	bodyHTML += '<td><button type="button" name="send" onclick="update('+ data.no +')">修改</button></td>';
	bodyHTML += '<td><button type="button" name="send" onclick="jobDel('+ data.no +')">刪除</button></td></tr>';
	$('#jobList').append(bodyHTML);
}
	
//==================================================修改==================================================
	function update(no){
		$.ajax({
	        type: 'post',
	        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/job/update',
	        data: {
            	no: no,
            	name: $('#name'+no).val(),
            },
	        dataType: 'json',
	        async: false,
	        cache: false,
	        success: function (data) {
	        	list();
	        },
            error: function (data) {
	        	list();
            }
	    });
}
	//==================================================新增==================================================
		function save(){
		$.ajax({
	        type: 'post',
	        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/job/management',
	        data: {
            	name: $('#names').val(),
            	supervisor: $('#supervisors').val(),
            },
	        dataType: 'json',
	        async: false,
	        cache: false,
	        success: function (data) {
	        	console.log("success");
	        	list();
	        },
            error: function (data) {
            	console.log("error");
	        	list();
            }
	    });
}
		//==================================================刪除==================================================	
	function jobDel(no){
		$.ajax({
	        type: 'post',
	        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/job/del',
	        data: {
            	no: no,
            },
	        dataType: 'json',
	        async: false,
	        cache: false,
	        success: function (data) {
	        	console.log("success");
	        	list();
	        },
            error: function (data) {
            	console.log("error");
	        	list();
            }
	    });
}
</script>
</head>
	
<body>
<jsp:include page="/headline.jsp"></jsp:include>
<table>
  <thead>
    <tr>
      <th>職務編號</th>
      <th>職務名稱</th>
      <th></th>
      <th></th>
    </tr>
  </thead>
  <tbody id="jobList">
			
  </tbody>
</table>
<a href="../index.jsp">上一頁</a>

</body></html>