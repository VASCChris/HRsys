<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="org.apache.commons.lang.StringEscapeUtils" %>
<html>
<head>
<script src='//production-assets.codepen.io/assets/editor/live/console_runner-079c09a0e3b9ff743e39ee2d5637b9216b3545af0de366d4b9aad9dc87e26bfd.js'></script><script src='//production-assets.codepen.io/assets/editor/live/events_runner-73716630c22bbc8cff4bd0f07b135f00a0bdc5d14629260c3ec49e5606f98fdd.js'></script><script src='//production-assets.codepen.io/assets/editor/live/css_live_reload_init-2c0dc5167d60a5af3ee189d570b1835129687ea2a61bee3513dee3a50c115a77.js'></script><meta charset='UTF-8'><meta name="robots" content="noindex"><link rel="shortcut icon" type="image/x-icon" href="//production-assets.codepen.io/assets/favicon/favicon-8ea04875e70c4b0bb41da869e81236e54394d63638a1ef12fa558a4a835f1164.ico" /><link rel="mask-icon" type="" href="//production-assets.codepen.io/assets/favicon/logo-pin-f2d2b6d2c61838f7e76325261b7195c27224080bc099486ddd6dccb469b8e8e6.svg" color="#111" /><link rel="canonical" href="https://codepen.io/jaycbrf/pen/iBszr" />
<script src="https://s.codepen.io/assets/libs/modernizr.js" type="text/javascript"></script>


<link rel='stylesheet prefetch' href='//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css'><link rel='stylesheet prefetch' href='//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css'><link rel='stylesheet prefetch' href='//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/css/bootstrapValidator.min.css'>
<style class="cp-pen-styles">#success_message{ display: none;}</style>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/resource/css/font-awesome.min.css">
<script src="https://use.fontawesome.com/4e67c494c5.js"></script>
</head>
<body>
<div class="container">

<form class="well form-horizontal" action='<c:url value="/employee/Register.controller"/>' method="post"  id="contact_form">
<fieldset>

<!-- Form Name -->
<legend>員工資料表</legend>



<!-- Text input-->
       <div class="form-group">
  <label class="col-md-4 control-label">E-Mail(帳號)</label>  
    <div class="col-md-4 inputGroupContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
  <input name="account" placeholder="VASC信箱" class="form-control"  type="text" value="${param.account}">
    </div>
    <small data-bv-validator="notEmpty" data-bv-validator-for="account" class="help-block" style="color: red;">${errorMsg.account}</small>
  </div>
</div>

<!-- Text input-->
       <div class="form-group">
  <label class="col-md-4 control-label">密碼</label>  
    <div class="col-md-4 inputGroupContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="fa fa-unlock-alt" aria-hidden="true"></i></span>
  <input name="password" placeholder="密碼" class="form-control"  type="password" value="${param.password}">
    </div>
    <small data-bv-validator="notEmpty" data-bv-validator-for="password" class="help-block" style="color: red;">${errorMsg.password}</small>
  </div>
</div>

<!-- Text input-->
       <div class="form-group">
  <label class="col-md-4 control-label">員工編號</label>  
    <div class="col-md-4 inputGroupContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="fa fa-id-card-o" aria-hidden="true"></i></span>
  <input name="empNo" placeholder="A+3數字" class="form-control"  type="text"  value="${param.empNo}">
    </div>
    <small data-bv-validator="notEmpty" data-bv-validator-for="empNo" class="help-block" style="color: red;">${errorMsg.empNo}</small>
  </div>
</div>

<!-- Text input-->

<div class="form-group">
  <label class="col-md-4 control-label">員工姓名</label>  
  <div class="col-md-4 inputGroupContainer">
  <div class="input-group">
  <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
  <input  name="name" placeholder="中文姓名" class="form-control"  type="text" value="${param.name}">
    </div>
    <small data-bv-validator="notEmpty" data-bv-validator-for="name" class="help-block" style="color: red;">${errorMsg.name}</small>
  </div>
</div>

<!-- Text input-->

<div class="form-group">
  <label class="col-md-4 control-label" >英文姓名</label> 
    <div class="col-md-4 inputGroupContainer">
    <div class="input-group">
  <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
  <input name="engName" placeholder="英文名字" class="form-control"  type="text" value="${param.engName}">
    </div>
    <small data-bv-validator="notEmpty" data-bv-validator-for="engName" class="help-block" style="color: red;">${errorMsg.engName}</small>
  </div>
</div>
<!-- Text input-->
       
<div class="form-group">
  <label class="col-md-4 control-label">分機號碼</label>  
    <div class="col-md-4 inputGroupContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
  <input name="ext" placeholder="數字" class="form-control" type="text" value="${param.ext}">
    </div>
    <small data-bv-validator="notEmpty" data-bv-validator-for="ext" class="help-block" style="color: red;">${errorMsg.ext}</small>
  </div>
</div>

<!-- Select Basic -->
   
<div class="form-group"> 
  <label class="col-md-4 control-label">部門</label>
    <div class="col-md-4 selectContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
    <select name="dep" class="form-control selectpicker" >
         <option value="0">選擇部門</option>
         <c:forEach var="depList" items="${depList}">
         <option value="${depList.no}">${depList.name}</option>
         </c:forEach>
    </select>
  </div>
  <small data-bv-validator="notEmpty" data-bv-validator-for="dep" class="help-block" style="color: red;">${errorMsg.depNo}</small>
</div>
</div>

<!-- Select Basic -->
   
<div class="form-group"> 
  <label class="col-md-4 control-label">職務</label>
    <div class="col-md-4 selectContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
    <select name="job" class="form-control selectpicker" >
         <option value="0">選擇職務</option>
         <c:forEach var="jobList" items="${jobList}">
         <option value="${jobList.no}">${jobList.name}</option>
         </c:forEach>
    </select>
  </div>
  <small data-bv-validator="notEmpty" data-bv-validator-for="job" class="help-block" style="color: red;">${errorMsg.jobNo}</small>
</div>
</div>

<!-- Select Basic -->
   
<div class="form-group"> 
  <label class="col-md-4 control-label">權限</label>
    <div class="col-md-4 selectContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
    <select name="character" class="form-control selectpicker" >
         <option value="0">選擇職務</option>
         <option value="admin">admin</option>
         <option value="common">common</option>
    </select>
  </div>
  <small data-bv-validator="notEmpty" data-bv-validator-for="character" class="help-block" style="color: red;">${errorMsg.character}</small>
</div>
</div>

<!-- Success message -->
<!-- <div class="alert alert-success" role="alert" id="success_message">Success <i class="glyphicon glyphicon-thumbs-up"></i> Thanks for contacting us, we will get back to you shortly.</div> -->

<!-- Button -->
<div class="form-group" style="margin-left: 82px;">
  <label class="col-md-4 control-label"></label>
  <div class="col-md-4" style="width: 150px;">
    <button type="submit" class="btn btn-warning" name="send" value="register">確認</button>
  </div>
  <div class="col-md-4">
    <button type="reset" class="btn btn-warning" name="send" value="cancel">清除</button>
  </div>
</div>

</fieldset>
</form>
</div>
    </div><!-- /.container -->
<a href="../index.jsp">上一頁</a>
<script src='//production-assets.codepen.io/assets/common/stopExecutionOnTimeout-b2a7b3fe212eaa732349046d8416e00a9dec26eb7fd347590fbced3ab38af52e.js'></script><script src='//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script><script src='//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script><script src='//cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js'></script>
<script>  $(document).ready(function() {
    $('#contact_form').bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                validators: {
                        stringLength: {
                        min: 2,
                    },
                        notEmpty: {
                        message: '請輸入姓名'
                    }
                }
            },
            engName: {
                validators: {
                     stringLength: {
                        min: 2,
                    },
                    notEmpty: {
                        message: '請輸入英文姓名'
                    }
                }
            },
            account: {
                validators: {
                    notEmpty: {
                        message: '請輸入VASC電子郵件'
                    },
                    emailAddress: {
                        message: '請輸入有效電子郵件'
                    }
                }
            },
            password: {
                validators: {
                     stringLength: {
                        min: 6,
                    },
                    notEmpty: {
                        message: '請輸入密碼'
                    }
                }
            },
            empNo: {
                validators: {
                     stringLength: {
                        min: 4,
                    },
                    notEmpty: {
                        message: '請輸入員工編號'
                    }
                }
            },
            ext: {
            	validators: {
                    stringLength: {
                       min: 2,
                   },
                   notEmpty: {
                       message: '請輸入分機號碼'
                   }
               }
            },
            address: {
                validators: {
                     stringLength: {
                        min: 8,
                    },
                    notEmpty: {
                        message: 'Please supply your street address'
                    }
                }
            },
            city: {
                validators: {
                     stringLength: {
                        min: 2,
                    },
                    notEmpty: {
                        message: 'Please supply your city'
                    }
                }
            },
            state: {
                validators: {
                    notEmpty: {
                        message: 'Please select your state'
                    }
                }
            },
            zip: {
                validators: {
                    notEmpty: {
                        message: 'Please supply your zip code'
                    },
                    zipCode: {
                        country: 'US',
                        message: 'Please supply a vaild zip code'
                    }
                }
            },
            comment: {
                validators: {
                      stringLength: {
                        min: 10,
                        max: 200,
                        message:'Please enter at least 10 characters and no more than 200'
                    },
                    notEmpty: {
                        message: 'Please supply a description of your project'
                    }
                    }
                }
            }
        })
        .on('success.form.bv', function(e) {
            $('#success_message').slideDown({ opacity: "show" }, "slow") // Do something ...
                $('#contact_form').data('bootstrapValidator').resetForm();

            // Prevent form submission
            e.preventDefault();

            // Get the form instance
            var $form = $(e.target);

            // Get the BootstrapValidator instance
            var bv = $form.data('bootstrapValidator');

            // Use Ajax to submit form data
            $.post($form.attr('action'), $form.serialize(), function(result) {
                console.log(result);
            }, 'json');
        });
});


//# sourceURL=pen.js
</script>
</body></html>