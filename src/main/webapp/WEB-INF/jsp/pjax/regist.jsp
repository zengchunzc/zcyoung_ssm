<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>会员注册-ZCYOUNG 年轻人</title>
<div style="text-align:center;">
	<div class="alert alert-info">
		<button type="button" class="close" data-dismiss="alert">×</button>
		提示：请注意你的账户密码安全.<br> ${msg }
	</div>
	<form class="form-horizontal">

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">欢迎注册</h3>
			</div>
			<div class="panel-body">
				用户账户：<input id="username" type="text" name="username">
			</div>
			<div class="panel-footer">
				用户密码：<input type="password" name="password" id="password">
			</div>
			<div class="panel-body">
				重复密码：<input type="password" name="repassword" id="repassword" />
			</div>
			<div class="panel-footer">
				个性昵称：<input type="text" name="nickname" id="nickname"
					value="${tmpUser.nickname }" />
			</div>
			<div class="panel-body">
				个性签名：<input type="text" name="signature" id="signature"
					value="${tmpUser.signature }" />
			</div>
			<div class="panel-footer">
				常用邮箱：<input type="text" name="email" id="email"
					value="${tmpUser.email }" />
			</div>
			<div class="panel-body">
				<div id="Registing"></div>
				<br>
				<button class="btn btn-large" type="button" onclick="SubmitRegist()">注册</button>
				<button class="btn btn-large btn-info" type="button" id="login" >前往登录</button>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">

$(function(){
    $('#login').click(function(){
        $.pjax({
            url: '/view/login',
            container: '#pjax-container',
            timeout: 10000
        });
    });
});
</script>