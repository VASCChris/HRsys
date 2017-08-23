<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.apache.commons.lang.StringEscapeUtils" %>
<html>
<head>
<meta charset="utf-8">
<title>jQuery cxCalendar 日期选择器 &raquo; 选择日期和时间 &raquo; 在线演示 - 前端开发仓库</title>
<link rel="stylesheet" href="resource/css/jquery.cxcalendar.css">
<link rel="stylesheet" href="resource/css/base.css">
<link rel="stylesheet" href="resource/css/layout.css">
<!-- <link rel="stylesheet" href="http://code.ciaoca.com/static/css/base.css"> -->
<!-- <link rel="stylesheet" href="http://code.ciaoca.com/jquery/cxCalendar/demo/css/layout.css"> -->


<script  src="http://code.jquery.com/jquery-1.12.4.min.js"  integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css'>

</head>
<body>

        <fieldset>
          <legend>日期</legend>
          <label for="date_c">选择日期：</label>
          <input id="date_c" type="text" class="input" size="16" value="" readonly>
        </fieldset>

=========================================================================================================

        <fieldset>
          <legend>日期+時分</legend>
          <label for="date_d">选择日期：</label>
          <input id="date_d" type="text" class="input" size="16" value="" readonly>
        </fieldset>



<script src="resource/js/jquery.cxcalendar.js"></script>
<!-- <script src="http://code.ciaoca.com/jquery/cxCalendar/demo/js/jquery.cxcalendar.min.js"></script> -->
<script>

//选择日期
$('#date_c').cxCalendar({
  format: 'YYYY-MM-DD',
  baseClass: 'cxcalendar_notsecs'
});

// 选择日期和時間
$('#date_d').cxCalendar({
  type: 'datetime',
  format: 'YYYY-MM-DD HH:mm',
  baseClass: 'cxcalendar_notsecs'
});
</script>

</body>
</html>
