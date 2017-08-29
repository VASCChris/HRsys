<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.apache.commons.lang.StringEscapeUtils" %>
<html>
<head>
<script  src="http://code.jquery.com/jquery-1.12.4.min.js"  integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
<script src='//production-assets.codepen.io/assets/editor/live/css_live_reload_init-2c0dc5167d60a5af3ee189d570b1835129687ea2a61bee3513dee3a50c115a77.js'></script><meta charset='UTF-8'><meta name="robots" content="noindex"><link rel="shortcut icon" type="image/x-icon" href="//production-assets.codepen.io/assets/favicon/favicon-8ea04875e70c4b0bb41da869e81236e54394d63638a1ef12fa558a4a835f1164.ico" /><link rel="mask-icon" type="" href="//production-assets.codepen.io/assets/favicon/logo-pin-f2d2b6d2c61838f7e76325261b7195c27224080bc099486ddd6dccb469b8e8e6.svg" color="#111" /><link rel="canonical" href="https://codepen.io/milliomola/pen/kXpOLO" />
<link href="<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/resource/css/bootstrap.css" rel="stylesheet">
<link rel='stylesheet prefetch' href="https:////netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
<style class="cp-pen-styles">*
{
    box-sizing:         border-box;
    -moz-box-sizing:    border-box;
    -webkit-box-sizing: border-box;
}

body
{
  margin: 0;
  padding: 0;
/*   background-image : url(../pictures/vasc.jpg); */
  background-color: #000000;
  font-family: arial, sans-serif;
}

a
{
  text-decoration: none
}

header{
  width: 100%;
  height: 80px;
  background: #222;
  text-align: center
}

h1
{
  color: #ddd;
  padding: 20px 20px;
  font: 10px 40px;
  margin: 0;
  display: inline-block
}

.by
{
  color: #a7a7a7;
  font-size: 16px;
  margin: 33px 0px;
  display: inline-block
}

.heart
{
  color: brown;
  display: inline-block;
  font-size: 20px;
  animation: heart 0.7s ease-in-out infinite;
}

header a
{
  color: #03b3b5;
  font-size: 22px;
  margin: 28px 10px;
  display: inline-block
}

header a:hover
{
  text-decoration: underline
}

.user-card
{
	width: 350px;
	height: 350px;
	margin: 50px auto;
  position: relative;
	background: #fff;
	overflow: hidden;
	box-shadow:         0 1px 3px 0 rgba(0,0,0,0.08);
	-moz-box-shadow:    0 1px 3px 0 rgba(0,0,0,0.08);
	-webkit-box-shadow: 0 1px 3px 0 rgba(0,0,0,0.08);
  border-radius:         5px;
  -moz-border-radius:    5px;
  -webkit-border-radius: 5px;
}

input
{
	width: 100%;
	height: 40px;
	border-radius:         3px;
	-moz-border-radius:    3px;
	-webkit-border-radius: 3px;
	border: 1px solid #dee3e4;
	padding: 3px 12px;
	margin: 6px 0;
}

input:focus
{
	outline: none;
	border: 1px solid #03b3b5;
	transition:         all .5s ease-in-out;
	-o-transition:      all .5s ease-in-out;
	-moz-transition:    all .5s ease-in-out;
	-webkit-transition: all .5s ease-in-out;
}

.login-box,
.signup-box
{
	position: absolute;
	top: 0;
	right: 0;
	width: 100%;
	height: calc(100% - 25px);
	background: #fff;
  border-radius:         5px;
  -moz-border-radius:    5px;
  -webkit-border-radius: 5px;
}

.login-box{
	padding: 20px 40px;
	right: 0px;
}

.signup-box{
	padding: 40px;
	right: -350px;
}

.login, .signup
{
	text-align: center;
	color: #fff;
	background: #03b3b5;
	line-height: 30px;
	opacity: 0.95;
}

.login:hover{opacity: 1}

.or
{
	display: block;
	width: 100%;
	height: 1px;
	border-bottom: 1px solid #dee3e4;
	position: relative;
	margin: 20px 0;
}

.or:before
{
	content: 'or';
	width: 40px;
	height: 18px;
	position: absolute;
	top: -5px;
	right: calc(50% - 20px);
	background-color: #fff;
	text-align: center;
	line-height: 10px;
	color: #555
}

.login-with-fb,
.login-with-google
{
	width: 100%;
	height: 40px;
	display: block;
	margin: 15px 0;
	color: #fff;
	text-align: center;
	line-height: 40px;
	font-size: 14px;
	opacity: .95;
  border-radius:         3px;
  -moz-border-radius:    3px;
  -webkit-border-radius: 3px;
}

.login-with-fb:hover,
.login-with-google:hover
{
	opacity: 1;
}

.login-with-fb
{
	background: #527EBF
}

.login-with-google
{
	background: #DB4A37;
}

.login-with-fb .icon,
.login-with-google .icon
{
	float: left;
	font-size: 21px;
	width: 50px;
	height: 26px;
	margin: 7px;
	padding: 2px;
	text-align: center;
	border-right: 1px solid #fff;
}


/**** footer *****/

.user-card .footer
{
	position: absolute;
	bottom: 0;
	right: 0;
	width: 100%;
	height: 25px;
	text-align: center;
	color: #666;
	font-size: 13px;
	font-family: sans-serif;
}

.footer a
{
	color: #03b3b5;
}

@keyframes heart{
  0%{
    transform:         scale(1, 1);
    -ms-transform:     scale(1, 1);
    -webkit-transform: scale(1, 1);
  }
  50%{
    transform:         scale(1.5, 1.5);
    -ms-transform:     scale(1.5, 1.5);
    -webkit-transform: scale(1.5, 1.5);
  }
  100%{
    transform:         scale(1, 1);
    -ms-transform:     scale(1, 1);
    -webkit-transform: scale(1, 1);
  }
}</style>
<script type="text/javascript">
//==================================================login==================================================
function login() {
	$.ajax({
        type: 'get',
        url: '<%=StringEscapeUtils.escapeHtml(request.getContextPath())%>/emp/login',
        data: {
        	account: $('#account').val(),
        	password: $('#password').val(),
        },
        dataType: 'json',
        async: false,
        cache: false,
        success: function (data) {
        	if(data.result=="success"){
        		document.location.href="../index.jsp";
        	}else{
        		console.log("FAIL");
        		$('#msg').html('');
        		$('#msg').append(data.result);
        	}
        },
        error: function (data) {
        	console.log("error");
        }
    });
}
</script>

</head><body>
<jsp:include page="/headline.jsp"></jsp:include>

<div class="user-card round5">
	<div class="login-box">
			<input type="username" id="account" name="username" class="username" placeholder="username" />
			<input type="password" id="password" name="password" class="password" placeholder="password" />
			<small id="msg" style="color: red;"></small>
			<br><br>
			<input type="button" name="login" value="Login" class="login" onclick="login()" />

<!-- 		<div class="or"></div> -->

<!-- 		<a href="#" class="login-with-google"> -->
<!-- 			<span class="icon fa fa-google-plus"></span> -->
<!-- 			Login with google -->
<!-- 		</a> -->
	</div>

	<div class="footer">
	</div>
</div>

<script src='//production-assets.codepen.io/assets/common/stopExecutionOnTimeout-b2a7b3fe212eaa732349046d8416e00a9dec26eb7fd347590fbced3ab38af52e.js'></script><script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>
<script>$(document).ready(function(){

	/* Login & Signup Toggle */

	var cardToggle = 0;

	

$('.toggle-link').on('click', function(event){
    event.preventDefault();
		if(cardToggle == 0 ){
			$(this).text('Login');
			$('.login-box').animate({
				right: '350px'
			});
			$('.signup-box').animate({
				right: '0'
			});	

			cardToggle = 1;

		}else{
			$(this).text('Signup');
			$('.login-box').animate({
				right: '0'
			});
			$('.signup-box').animate({
				right: '-350px'
			});

			cardToggle = 0;
		}
	})
})
//# sourceURL=pen.js
</script>
</body>
</html>