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
//==================================================dep列表==================================================
function list() {
	$.ajax({
        type: 'get',
        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/dep/list',
     
        dataType: 'json',
        async: false,
        cache: false,
        success: function (data) {
        	$('#depList').html('');
        	for (var i = 0; i < data.length; i++) {
        		addDep(data[i]);
        	}
        	var bodyHTML = '<tr>';
        	
        	bodyHTML += '<td>新增部門</td>';
        	bodyHTML += '<td><input type="text" id="names" value=""></td>';
        	bodyHTML += '<td><input type="text" id="supervisors" value=""></td>';
        	bodyHTML += '<td><button type="button" name="send" onclick="save()">新增</button></td></tr>';
        	
        	$('#depList').append(bodyHTML);

        }
    });
}
//==================================================抓dep列表==================================================
function addDep(data){
	var bodyHTML = '<tr>';
	bodyHTML += '<td>' + data.no +'</td>';
	bodyHTML += '<td><input id="name' + data.no +'" type="text" value="' + data.name +'"></td>';
	bodyHTML += '<td><input id="supervisor' + data.no +'" type="text" value="' + data.supervisor +'"></td>';
	bodyHTML += '<td><button type="button" name="send" onclick="update('+ data.no +')">修改</button></td>';
	bodyHTML += '<td><button type="button" name="send" onclick="depDel('+ data.no +')">刪除</button></td></tr>';
	$('#depList').append(bodyHTML);
}
	
//==================================================修改==================================================
	function update(no){
		$.ajax({
	        type: 'post',
	        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/dep/management/'+no,
	        data: {
            	no: no,
            	name: $('#name'+no).val(),
            	supervisor: $('#supervisor'+no).val(),
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
	        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/dep/management',
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
	function depDel(no){
		$.ajax({
	        type: 'DELETE',
	        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/dep/management/'+no,
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
      <th>部門編號</th>
      <th>部門名稱</th>
      <th>部門主管</th>
      <th></th>
      <th></th>
    </tr>
  </thead>
  <tbody id="depList">

<!-- 			   <tr> -->
<!-- 				<td><input type="text" id="no" value=""></td> -->
<!-- 				<td><input type="text" id="name" value=""></td> -->
<!-- 				<td><input type="text" id="supervisor" value=""></td> -->
<!-- 				<td><button type="button" name="send" onclick="list()">測試</button></td> -->
<!-- 				<td><Button type="button" name="send" value="delete">刪除</Button></td> -->
<!-- 			   </tr> -->
			
			
<!-- 			   <tr> -->
<!-- 				 <td>新增部門:<input type="hidden" name="no" value="0"></td> -->
<!-- 				 <td><input type="text" name="name"></td> -->
<!-- 				 <td><input type="text" name="supervisor"></td> -->
<!-- 				 <td><Button type="submit" name="send" value="insert">新增</Button></td> -->
<!-- 				 <td></td> -->
<!-- 			   </tr> -->
			
  </tbody>
</table>

</body></html>