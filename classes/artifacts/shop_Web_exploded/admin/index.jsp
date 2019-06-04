<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>网上商城管理中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath }/css/general.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/main.css"
	rel="stylesheet" type="text/css" />

<style type="text/css">
html{
	height:100%;
}
body {
	font-family: '微软雅黑', "宋体", "Arial Narrow", Helvetica, sans-serif;
	background-image:
		url('${pageContext.request.contextPath}/images/bg.jpg');
	background-repeat: no-repeat;
	background-size: 100% 100%;
}

input:focus{
	outline:none;
}

.login {
	position: absolute;
	background: white;
	width: 350px;
	height: 420px;
	margin-top: -210px;
	margin-left: -175px;
	top: 50%;
	left: 50%;
}

.login-label {
	font-size: 25px;
	color: blue;
	text-align: center;
	margin-top: 50px;
	margin-bottom: 60px;
}

.login-div {
	text-align: center;
	margin-top: 40px;
	width: 80%;
	margin-left: 10%;
}

.login-div label {
	/* width: 60px; */
	margin-right: 10px;
	text-align: left;
	display: inline-block;
}

.login-div input {
	border: none;
	border-bottom: 1px #bbb solid;
	font-size: 14px;
	padding: 4px;
}

button[type='submit'] {
	background: dodgerblue;
	font-size: 14px;
	padding: 10px 5px;
	border-radius: 3px;
	width: 70%;
	border: none;
	color: white;
}
</style>
<body>
	<center>
		<s:actionerror />
	</center>
	<div class="login">
		<form method="post"
			action="${pageContext.request.contextPath }/adminUser_login.action"
			target="_parent" name='theForm'>
			<div class="login-label">登录</div>
			<div class="login-div">
				<label><img
					src="${pageContext.request.contextPath }/images/name.png"
					width="20"></label> <input type="text" name="username"
					placeholder="请输入用户名" />
			</div>
			<div class="login-div">
				<label><img
					src="${pageContext.request.contextPath }/images/password.png"
					width="20"></label> <input type="password" name="password"
					placeholder="请输入密码" />
			</div>
			<div class="login-div">
				<button type="submit">进入管理中心</button>
				<input type="hidden" name="act" value="signin" />
			</div>
		</form>
	</div>

</body>