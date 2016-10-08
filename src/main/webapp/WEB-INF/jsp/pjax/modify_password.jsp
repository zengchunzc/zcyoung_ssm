<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>修改登录密码-ZCYOUNG年轻人</title>

<div style="text-align:center;">
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
				请输入原密码：<input type="password" id="password">
			</div>
			<div class="panel-footer">
				请输入新密码：<input type="password" id="newpassword">
			</div>
			<div class="panel-body">
				请重复新密码：<input type="password" id="renewpassword">
			</div>
		</div>
			<div id="msg">

			</div>
		<button class="btn btn-large btn-info" type="button" onclick="modifypw();">确认修改密码</button>
		<button class="btn btn-large btn-info" type="button" id="gosafe" >返回安全中心</button>
		<button class="btn btn-large btn-info" type="button" id="gohome" >返回个人中心</button>
</div>

<script type="text/javascript">

function go(url){ 
	location.href = url;
}

function getmsg(mes){
	return "<div class='alert alert-info'><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><strong>提示!</strong> " + mes +"</div>";
}

function modifypw(){
	var password = $("#password").val();
	var newpassword = $("#newpassword").val();
	var renewpassword = $("#renewpassword").val();
	if(newpassword != renewpassword){
		$("#msg").html(getmsg("两次密码不一致。"));
		return;
	}
	else {
		$.post("/user/modifypassword",{
		password : md5(password),
		newpassword : md5(newpassword)
		},
		function(data,status){
			if(status == "success"){
				if(data.indexOf("ok") != -1){
					$("#msg").html(getmsg("密码修改成功，三秒后跳转。"));
					setTimeout(go("/view/exit"), 3000);
				}
				else if(data.indexOf("error") != -1)
				$("#msg").html(getmsg("密码修改失败。"));
				else $("#msg").html(getmsg(data));
			}
		});
	}
}

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
