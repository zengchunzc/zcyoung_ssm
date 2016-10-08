<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>修改登录密码-ZCYOUNG年轻人</title>

<div style="text-align:center;">
	<form action="../user/modify_password.do" method="post">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">修改登录密码</h3>
			</div>
			<div class="panel-body">
				您的用户账号：<input type="text" value="${User.username  }"
					disabled="disabled">
			</div>
			<div class="panel-footer">
				您的用户昵称：<input type="text" name="nickname" value="${User.nickname  }"
					disabled="disabled">
			</div>
			<div class="panel-body">
				请输入原密码：<input type="password" name="password">
			</div>
			<div class="panel-footer">
				请输入新密码：<input type="password" name="newpassword">
			</div>
			<div class="panel-body">
				请重复新密码：<input type="password" name="renewpassword">
			</div>
		</div>
		<c:if test="${msg!=null }">
			<div class="alert alert-info">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<strong>提示!</strong> ${msg }.
			</div>
		</c:if>
		<button class="btn btn-large btn-info" type="submit">确认修改密码</button>
		<button class="btn btn-large btn-info" type="button" id="gosafe" >返回安全中心</button>
		<button class="btn btn-large btn-info" type="button" id="gohome" >返回个人中心</button>

	</form>
</div>

<script type="text/javascript">

$(function(){
    $('#gohome').click(function(){
        $.pjax({
            url: '/view/home.do',
            container: '#pjax-container',
            timeout: 10000
        });
    });
    $('#gosafe').click(function(){
        $.pjax({
            url: '/view/safe.do',
            container: '#pjax-container',
            timeout: 10000
        });
    });
}); 
</script>
