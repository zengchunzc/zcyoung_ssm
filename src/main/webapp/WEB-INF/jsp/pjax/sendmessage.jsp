<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<title>好友发送站内 ZCYOUNG 年轻人</title>

<c:if test="${msg!=null }">
	<div class="alert alert-info">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>提示!</strong> ${msg }.
	</div>
</c:if>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">发送站内信给好友(只能给好友发私信哦)</h3>
	</div>
	<br>
	<div style="text-align:center;">
		<form action="/user/sendMessage.do" method="post">
			发送给：<select name="toId">

				<c:forEach items="${friendlist }" var="p" varStatus="vs">
					<option value="${p.id }"
						<c:if test="${param.toId==p.id }">selected = "selected"</c:if>>${p.nickname }</option>
				</c:forEach>
			</select> <br>
			<textarea rows="5" name="ms"></textarea>
			<br> <input type="submit" value="发送"
				class="btn btn-large btn-info" type="button"> &nbsp;
			<button class="btn btn-large btn-info" type="button" id="goback">返回信息区</button>
		</form>
	</div>
</div>

<script type="text/javascript">

$(function(){

    $('#goback').click(function(){
        $.pjax({
            url: '/view/tomessage.do',
            container: '#pjax-container',
            timeout: 10000
        });
    });

}); 
</script>




