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
var deps = '';
var jobs = '';
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
//==================================================emp列表==================================================
function list() {
	$.ajax({
        type: 'get',
        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/emp/list',
     
        dataType: 'json',
        async: false,
        cache: false,
        success: function (data) {
        	depList();
        	jobList();
        	$('#empList').html('');
        	for (var i = 0; i < data.length; i++) {
        		addEmp(data[i]);
        		$('#depNo'+data[i].id).append(deps);
        		$('#jobNo'+data[i].id).append(jobs);
        	}
        }
    });
}
//==================================================抓emp列表==================================================
function addEmp(data){
	var id = data.id;
	var bodyHTML = '<tr>';
	bodyHTML += '<td><input id="empNo' + id +'" type="text" value="' + data.empNo +'"></td>';
	bodyHTML += '<td><input id="account' + id +'" type="text" value="' + data.account +'"></td>';
	bodyHTML += '<td><input id="name' + id +'" type="text" value="' + data.name +'"></td>';
	bodyHTML += '<td><input id="engName' + id +'" type="text" value="' + data.engName +'"></td>';
	bodyHTML += '<td><input id="ext' + id +'" type="text" value="' + data.ext +'"></td>';
	bodyHTML += '<td><select id="depNo' + id +'"><option value="'+data.depNo+'">'+ data.depName +'</option></select></td>';
	bodyHTML += '<td><select id="jobNo' + id +'"><option value="'+data.jobNo+'">'+ data.jobName +'</option></select></td>';
	bodyHTML += '<td><select id="character' + id +'"><option value="'+data.character+'">'+ data.character +'</option><option value="superadmin">superadmin</option><option value="admin">admin</option><option value="common">common</option></select></td>';
	bodyHTML += '<td><button type="button" name="send" style="width: 45px;" onclick="update('+ id +')">修改</button></td>';
	bodyHTML += '<td><button type="button" name="send" style="width: 45px;" onclick="empDel('+ id +')">刪除</button></td></tr>';
	$('#empList').append(bodyHTML);
}	
	
//==================================================修改==================================================
	function update(id){
		$.ajax({
	        type: 'post',
	        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/emp/update',
	        data: {
	        	id: id,
	        	empNo: $('#empNo'+id).val(),
	        	account: $('#account'+id).val(),
	        	name: $('#name'+id).val(),
	        	engName: $('#engName'+id).val(),
	        	ext: $('#ext'+id).val(),
	        	depNo: $('#depNo'+id).val(),
	        	jobNo: $('#jobNo'+id).val(),
	        	character: $('#character'+id).val(),
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
		//==================================================刪除==================================================	
	function empDel(id){
		$.ajax({
	        type: 'post',
	        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/emp/del',
	        data: {
	        	id: id,
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
	//==================================================dep列表==================================================
	function depList() {
		$.ajax({
	        type: 'get',
	        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/dep/list',
	     
	        dataType: 'json',
	        async: false,
	        cache: false,
	        success: function (data) {
	        	var bodyHTML='';
	        	$('#depList').html('');
	        	for (var i = 0; i < data.length; i++) {
	        		var dep = addDep(data[i]);
	        		bodyHTML += dep;
	        	}
	        	deps = bodyHTML;
	        }
	    });
	}
	//==================================================抓dep列表==================================================
	function addDep(data){
		var bodyHTML = '<option value="'+ data.no +'">'+ data.name +'</option>';
		return bodyHTML;
	}
	//==================================================job列表==================================================
	function jobList() {
		$.ajax({
	        type: 'get',
	        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/job/list',
	     
	        dataType: 'json',
	        async: false,
	        cache: false,
	        success: function (data) {
	        	var bodyHTML='';
	        	$('#jobList').html('');
	        	for (var i = 0; i < data.length; i++) {
	        		var job = addJob(data[i]);
	        		bodyHTML += job;
	        	}
	        	jobs = bodyHTML;
	        }
	    });
	}
	//==================================================抓job列表==================================================
	function addJob(data){
		var bodyHTML = '<option value="'+ data.no +'">'+ data.name +'</option>';
		return bodyHTML;
	}
</script>
</head>
	
<body>
<jsp:include page="/headline.jsp"></jsp:include>
<table>
  <thead>
    <tr>
      <th>員工編號</th>
      <th>帳號</th>
      <th>姓名</th>
      <th>英文名</th>
      <th>分機</th>
      <th>部門</th>
      <th>職務</th>
      <th>權限</th>
      <th></th>
      <th></th>
    </tr>
  </thead>
  <tbody id="empList">

  </tbody>
</table>

</body></html>