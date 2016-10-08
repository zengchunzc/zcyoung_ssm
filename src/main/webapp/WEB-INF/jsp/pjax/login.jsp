<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<title>欢迎您登陆，zcyoung.cn Welcome！</title>

<div style="text-align:center;">
	<div class="alert alert-info">
		<button type="button" class="close" data-dismiss="alert">×</button>
		提示：请注意你的账户密码安全.<br>
		<div id="msg"></div>
	</div>
	<form class="form-horizontal">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">欢迎登录</h3>
			</div>
			<div class="panel-body">
				用户账户：<input id="username" type="text" name="username" onkeydown='if(event.keyCode==13){SubmitLogin();}'>
			</div>
			<div class="panel-footer">
				用户密码：<input type="password" name="password" id="password" onkeydown='if(event.keyCode==13){SubmitLogin();}'> <input
					type="hidden" id="fromUrl" name="fromUrl"
					value="${param.fromUrl!=null?param.fromUrl:fromUrl }"> <br>
				<div id="Logining"></div>

				<p>
					<input type="checkbox" name="rem" id="rem" value="ok"
						checked="checked" />7天免登陆
				</p>
				<button class="btn btn-large" type="button" onclick="SubmitLogin()">登录</button>
				<button class="btn btn-large btn-info" type="button" id="regist" >注册</button>
			</div>
		</div>
	</form>
</div>

<script type="text/javascript">

$(function(){
    $('#regist').click(function(){
        $.pjax({
            url: '/view/regist.do',
            container: '#pjax-container',
            timeout: 10000
        });
    });
});
</script>
