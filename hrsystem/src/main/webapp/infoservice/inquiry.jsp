<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.apache.commons.lang.StringEscapeUtils" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script  src="http://code.jquery.com/jquery-1.12.4.min.js"  integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css'>
<style class="cp-pen-styles">
h1{
  font-size: 30px;
  color: #fff;
  text-transform: uppercase;
  font-weight: 300;
  text-align: center;
  margin-bottom: 15px;
}
table{
  width:100%;
  table-layout: fixed;
}
.tbl-header{
  background-color: rgba(255,255,255,0.3);
 }
.tbl-content{
  overflow-x:auto;
  margin-top: 0px;
  border: 1px solid rgba(255,255,255,0.3);
}
th{
  padding: 20px 15px;
  text-align: left;
  font-weight: 500;
  font-size: 21px;
  color: #fff;
  text-transform: uppercase;
}
td{
  padding: 15px;
  text-align: left;
  vertical-align:middle;
  font-weight: 300;
  font-size: 15px;
  color: #fff;
  border-bottom: solid 1px rgba(255,255,255,0.1);
}


/* demo styles */

@import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);
body{
  background: -webkit-linear-gradient(left, #25c481, #25b7c4);
  background: linear-gradient(to right, #25c481, #25b7c4);
  font-family: 'Roboto', sans-serif;
}
section{
  margin: 50px;
}


/* follow me template */
.made-with-love {
  margin-top: 40px;
  padding: 10px;
  clear: left;
  text-align: center;
  font-size: 10px;
  font-family: arial;
  color: #fff;
}
.made-with-love i {
  font-style: normal;
  color: #F50057;
  font-size: 14px;
  position: relative;
  top: 2px;
}
.made-with-love a {
  color: #fff;
  text-decoration: none;
}
.made-with-love a:hover {
  text-decoration: underline;
}


/* for custom scrollbar for webkit browser*/

::-webkit-scrollbar {
    width: 6px;
} 
::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3); 
} 
::-webkit-scrollbar-thumb {
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3); 
}</style>
<title>ISO資訊服務申請單查詢</title>
<script type="text/javascript">
$(document).ready(function(){
	list6();
});
//=======================================承辦清單6===============================================
function list6(){
	$.ajax({
        type: 'get',
        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/infoservice/list/stage/six',

        dataType: 'json',
        async: false,
        cache: false,
        success: function (data) {
        	for (var i = 0; i < data.length; i++) {
        		addList(data[i]);
        	}
        }
    });
}
//==================================================顯示清單列表==================================================
function addList(data){
	
	var bodyHTML = '<tr>';
	bodyHTML += '<td>資訊服務申請單'+  data.id + '</td>'  
	bodyHTML += '<td style="width: 170px;"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="detail(' + data.id + ')">查看</button></td></tr>'
	$('#tbody').append(bodyHTML);
	
}
//==================================================detail==================================================
function detail(num){
	$.ajax({
        type: 'get',
        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/infoservice/detail',
        data: {
        	num: num
        },
        dataType: 'json',
        async: false,
        cache: false,
        success: function (data) {
        	$('#warn').html('');
        	if(data.stage==6){
        		stage6(data);
        	}
        }
    });
}


//==================================================stage6==================================================
function stage6(data){
	var type = data.type;
	var applicationTime = dateFormat(data.applicationTime.time);
    var fileNo = data.fileNo;
    var applicant = data.applicant;
    var ext = data.ext;
    var applicantDep = data.applicantDep;
    var applicantSupervisor = data.applicantSupervisor;
    var contractor = data.contractor;
    var contractorSupervisor = data.contractorSupervisor;
    var demand = data.demand;
    var infoServiceType = data.infoServiceType;
    var eventType = data.eventType;
    var eventRemark = data.eventRemark;
    var infoSecurityLv = data.infoSecurityLv;
    var processWay = data.processWay;
    var pStartTime = dateFormat(data.pStartTime.time);
    var pEndTime = dateFormat(data.pEndTime.time);
    var pTotalTime = data.pTotalTime;
    var correction = data.correction;
    var cEstimated = dateFormat(data.cEstimated.time);
    var cActual = dateFormat(data.cActual.time);
    var cTotal = data.cTotal;
    var improvement = data.improvement;
    var iEstimated = dateFormat(data.iEstimated.time);
    var iActual = dateFormat(data.iActual.time);
    var iTotal = data.iTotal;
    var verification = data.verification;
    var remark = data.remark;
	
    if(infoSecurityLv == undefined){
    	infoSecurityLv = "";
    }
    if(remark == undefined){
    	remark = "";
    }
    
    
    $('#typeContent').html('');
    $('#applicationTime').html('');
    $('#fileNo').html('');
    $('#applicant').html('');
    $('#ext').html('');
    $('#applicantDep').html('');
    $('#applicantSupervisor').html('');
    $('#contractor').html('');
    $('#contractorSupervisor').html('');
    $('#demand').html('');
    $('#infoServiceType').html('');
    $('#eventType').html('');
    $('#eventRemarkContent').html('');
    $('#infoSecurityLv').html('');
    $('#pWay').html('');
    $('#pStart').html('');
    $('#pEnd').html('');
    $('#pTotal').html('');
    $('#cWay').html('');
    $('#cEst').html('');
    $('#cAct').html('');
    $('#cTot').html('');
    $('#iWay').html('');
    $('#iEst').html('');
    $('#iAct').html('');
    $('#iTot').html('');
    $('#ver').html('');
    $('#returnReasonContent').html('');
    $('#remarkContent').html('');
    
    
    $('#type').append("類別:&nbsp;&nbsp;"+type+"&nbsp;&nbsp;&nbsp;");
    $('#fileNo').append('文件編號:&nbsp;&nbsp;'+fileNo+'&nbsp;&nbsp;&nbsp;');
    $('#applicationTime').append("申請時間:&nbsp;&nbsp;"+applicationTime+"&nbsp;&nbsp;&nbsp;");
    $('#applicant').append("申請人:&nbsp;&nbsp;"+applicant+"&nbsp;&nbsp;&nbsp;");
    $('#ext').append("分機:&nbsp;&nbsp;"+ext+"&nbsp;&nbsp;&nbsp;");
    $('#applicantDep').append("申請單位:&nbsp;&nbsp;"+applicantDep+"&nbsp;&nbsp;&nbsp;");
    $('#applicantSupervisor').append("申請單位主管:&nbsp;&nbsp;"+applicantSupervisor+"&nbsp;&nbsp;&nbsp;");
    $('#contractor').append("承辦人:&nbsp;&nbsp;"+contractor+"&nbsp;&nbsp;&nbsp;");
    $('#contractorSupervisor').append("承辦單位主管:&nbsp;&nbsp;"+contractorSupervisor+"&nbsp;&nbsp;&nbsp;");
    $('#demand').append("服務需求:&nbsp;&nbsp;"+demand+"&nbsp;&nbsp;&nbsp;");
    $('#infoServiceType').append("服務類別:&nbsp;&nbsp;"+infoServiceType+"&nbsp;&nbsp;&nbsp;");
    $('#eventType').append("事件類別:&nbsp;&nbsp;"+eventType+"&nbsp;&nbsp;&nbsp;");
    $('#eventRemarkContent').append("事件類別備註:&nbsp;&nbsp;"+eventRemark+"&nbsp;&nbsp;&nbsp;");
    $('#infoSecurityLv').append("資安等級:&nbsp;&nbsp;"+infoSecurityLv+"&nbsp;&nbsp;&nbsp;");
    $('#pWay').append("處理方式:&nbsp;&nbsp;"+processWay+"&nbsp;&nbsp;&nbsp;");
    $('#pStart').append("執行開始時間:&nbsp;&nbsp;"+pStartTime+"&nbsp;&nbsp;&nbsp;");
    $('#pEnd').append("執行完成時間:&nbsp;&nbsp;"+pEndTime+"&nbsp;&nbsp;&nbsp;");
    $('#pTotal').append("執行花費總時間:&nbsp;&nbsp;"+pTotalTime+"分&nbsp;&nbsp;&nbsp;");
    $('#cWay').append("矯正措施:&nbsp;&nbsp;"+correction+"&nbsp;&nbsp;&nbsp;");
    $('#cEst').append("預定完成時間:&nbsp;&nbsp;"+cEstimated+"&nbsp;&nbsp;&nbsp;");
    $('#cAct').append("實際完成時間:&nbsp;&nbsp;"+cActual+"&nbsp;&nbsp;&nbsp;");
    $('#cTot').append("處理所需時間:&nbsp;&nbsp;"+cTotal+"分&nbsp;&nbsp;&nbsp;");
    $('#iWay').append("改善措施:&nbsp;&nbsp;"+improvement+"&nbsp;&nbsp;&nbsp;");
    $('#iEst').append("預定完成時間:&nbsp;&nbsp;"+iEstimated+"&nbsp;&nbsp;&nbsp;");
    $('#iAct').append("實際完成時間:&nbsp;&nbsp;"+iActual+"&nbsp;&nbsp;&nbsp;");
    $('#iTot').append("處理所需時間:&nbsp;&nbsp;"+iTotal+"分&nbsp;&nbsp;&nbsp;");
    $('#ver').append("驗證人員:&nbsp;&nbsp;"+verification+"&nbsp;&nbsp;&nbsp;");
    $('#remarkContent').append("備註:&nbsp;&nbsp;"+remark+"&nbsp;&nbsp;&nbsp;");
    
    
    
//     var bodyHTML = '<input type="hidden" id="id" value="'+data.id+'" />';
//     bodyHTML += '<input type="hidden" id="type" value="'+type+'" />';
//     bodyHTML += '<input type="hidden" id="applicationT" value="'+applicationTime+'" />';
//     bodyHTML += '<input type="hidden" id="applicantId" value="'+data.applicantId+'" />';
//     bodyHTML += '<input type="hidden" id="applicantDepNo" value="'+data.applicantDepNo+'" />';
//     bodyHTML += '<input type="hidden" id="contractorId" value="'+data.contractorId+'" />';
//     bodyHTML += '<input type="hidden" id="contractorDepNo" value="'+data.contractorDepNo+'" />';
//     bodyHTML += '<input type="hidden" id="demandContent" value="'+data.demand+'" />';
//     bodyHTML += '<input type="hidden" id="infoServiceTypeNo" value="'+data.infoServiceTypeNo+'" />';
//     bodyHTML += '<input type="hidden" id="event" value="'+data.eventType+'" />';
//     bodyHTML += '<input type="hidden" id="securityLv" value="'+data.infoSecurityLv+'" />';
//     bodyHTML += '<input type="hidden" id="processWay" value="'+data.processWay+'" />';
//     bodyHTML += '<input type="hidden" id="pStartTime" value="'+pStartTime+'" />';
//     bodyHTML += '<input type="hidden" id="pEndTime" value="'+pEndTime+'" />';
//     bodyHTML += '<input type="hidden" id="correction" value="'+data.correction+'" />';
//     bodyHTML += '<input type="hidden" id="cEstimated" value="'+cEstimated+'" />';
//     bodyHTML += '<input type="hidden" id="cActual" value="'+cActual+'" />';
//     bodyHTML += '<input type="hidden" id="improvement" value="'+data.improvement+'" />';
//     bodyHTML += '<input type="hidden" id="iEstimated" value="'+iEstimated+'" />';
//     bodyHTML += '<input type="hidden" id="iActual" value="'+iActual+'" />';
//     bodyHTML += '<input type="hidden" id="verificationId" value="'+data.verificationId+'" />';
//     bodyHTML += '<input type="hidden" id="remark" value="'+data.remark+'" />';
//     bodyHTML += '<input type="hidden" id="stage" value="5" />';
//     $('#formDetail').append(bodyHTML);
}
//==================================================日期轉換==================================================
function dateFormat(timestamp) {
    var date = new Date(parseInt(timestamp)) ;    
    return date.getFullYear() + '-'
         + ('0' + (date.getMonth()+1)).slice(-2) + '-'
         + ('0' + date.getDate()).slice(-2)  + ' '
         + ('0' + date.getHours()).slice(-2) + ':'
         + ('0' + date.getMinutes()).slice(-2) + ':'
         + ('0' + date.getSeconds()).slice(-2);
}
</script>
</script>
</head>
<body>
<!-- list -->
<center>
<section style="width: 500px;">
  <h1>待處理資訊服務申請單</h1>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
        <tr>
          <th>申請單列表</th>
          <th></th>
        </tr>
      </thead>
    </table>
  </div>
  <div class="tbl-content">
   <center>
    <table cellpadding="0" cellspacing="0" border="0">
        <tbody id="tbody">
        
        </tbody>
    </table>
   </center>
  </div>
</section>
</center>



<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">資訊服務申請單</h4>
      </div>
      <div class="modal-body" id="formDetail">
                                  <!-- 表單內容塞這 -->
          
          <div id="fileNo"></div><br>
          <div id="typeContent"></div><br>
          <div id="applicationTime"></div><br>
          <div id="applicant"></div><br>
          <div id="ext"></div><br>
          <div id="applicantDep"></div><br>
          <div id="applicantSupervisor"></div><br>
          <div id="contractor"></div><br>
          <div id="contractorSupervisor"></div><br>
          <div id="demand"></div><br>
          <div id="infoServiceType"></div><br>
          <div id="eventType"></div><br>
          <div id="eventRemarkContent"></div><br>
          <div id="infoSecurityLv"></div><br>
          <div id="pWay"></div><br>
          <div id="pStart"></div><br>
          <div id="pEnd"></div><br>
          <div id="pTotal"></div><br>
          <div id="cWay"></div><br>
          <div id="cEst"></div><br>
          <div id="cAct"></div><br>
          <div id="cTot"></div><br>
          <div id="iWay"></div><br>
          <div id="iEst"></div><br>
          <div id="iAct"></div><br>
          <div id="iTot"></div><br>
          <div id="ver"></div><br>
          <div id="remarkContent"></div><br>
          <div id="form"></div><br>
          <div id="returnReasonContent"></div><br>
          
      </div>
      <div class="modal-footer"><div id="warn"></div>
        <button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
<!--         <button type="button" class="btn btn-primary" onclick="send()">送出表單</button> -->
      </div>
    </div>
  </div>
</div>
<a href="../index.jsp">上一頁</a>
</body>
</html>