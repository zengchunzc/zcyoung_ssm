<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<title>添加好友-ZCYOUNG 年轻人</title>
<c:if test="${msg!=null }">
	<div class="alert alert-info">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>提示!</strong> ${msg }.
	</div>
</c:if>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">添加好友</h3>
	</div>
	<br>
	<div style="text-align:center;">
		<form action="/user/addfriend.do">
			方式：<select name="ways">
				<option value="nickname">昵称</option>
				<option value="username">用户名</option>
			</select> <br> 请输入：<input type="text" name="ms" value="${param.ms }" />
			<br> <input type="submit" value="申请"
				class="btn btn-large btn-info" type="button"> &nbsp;
			<button class="btn btn-large btn-info" type="button" id="goback" >返回我的好友</button>

		</form>
	</div>
</div>
<script type="text/javascript">
$(function(){
    $('#goback').click(function(){
        $.pjax({
            url: '/view/myfriend.do',
            container: '#pjax-container',
            timeout: 10000
        });
    });

}); 
</script>