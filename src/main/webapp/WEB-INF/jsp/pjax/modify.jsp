<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<title>修改我的资料-ZCYOUNG 年轻人</title>
<div style="text-align:center;">
	<c:if test="${msg!=null }">
		<div class="alert alert-info">
			<button type="button" class="close" data-dismiss="alert">×</button>
			<strong>提示!</strong> ${msg }.
		</div>
	</c:if>
	<form action="/user/modify" method="post"
		enctype="multipart/form-data">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">修改我的资料</h3>
			</div>
			<div class="panel-body">
				<img alt="" src="${User.pic }" width="50px"><br>
				<br> 个性头像：<input type="file" name="pic">
			</div>
			<div class="panel-footer">
				用户账号：<input type="text" value="${User.username  }"
					disabled="disabled">
			</div>
			<div class="panel-body">
				用户昵称：<input type="text" name="nickname" value="${User.nickname  }">
			</div>
			<div class="panel-footer">
				个性签名：<input type="text" name="signature" value="${User.signature }">
			</div>
			<div class="panel-body">
				用户邮箱：<input type="text" value="${User.email  }" disabled="disabled">
			</div>
			<div class="panel-footer">
				用户等级：<input type="text" value="${User.role  }" disabled="disabled">
			</div>
			<div class="panel-body">
				注册时间：<input type="text" value="<fmt:formatDate value="${User.rTime  }" type="both" />" disabled="disabled">
			</div>
		</div>
		<button class="btn btn-large btn-info" type="submit">保存修改</button>
		<button class="btn btn-large btn-info" type="button" id="ToMP" >修改密码</button>
		<button class="btn btn-large btn-info" type="button" id="gohome">返回个人中心</button>
	</form>
</div>
<script type="text/javascript">
$(function(){
    $('#gohome').click(function(){
        $.pjax({
            url: '/view/home',
            container: '#pjax-container',
            timeout: 10000
        });
    });
    $('#ToMP').click(function(){
        $.pjax({
            url: '/view/modifypassword',
            container: '#pjax-container',
            timeout: 10000
        });
    });
}); 
</script>