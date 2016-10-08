<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>安全中心-ZCYOUNG 年轻人</title>

<div style="text-align:center;">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">安全中心</h3>
		</div>
		<div class="panel-body">${User.username  }，你可以选择如下的操作</div>
	</div>
	<button class="btn btn-large btn-info" type="button" id="ToMP" >修改登录密码</button>
	<button class="btn btn-large btn-info" type="button" id="ToHistory" >查看登录历史</button>
	<button class="btn btn-large btn-info" type="button" id="gohome">返回个人中心</button>
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
    $('#ToMP').click(function(){
        $.pjax({
            url: '/view/modify_password.do',
            container: '#pjax-container',
            timeout: 10000
        });
    });
    $('#ToHistory').click(function(){
        $.pjax({
            url: '/view/login_history.do',
            container: '#pjax-container',
            timeout: 10000
        });
    });
}); 
</script>