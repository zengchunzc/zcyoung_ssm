<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<title>审核好友请求-ZCYOUNG 年轻人</title>

<c:if test="${msg!=null }">
	<div class="alert alert-info">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>提示!</strong> ${msg }.
	</div>
</c:if>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">审核好友请求</h3>
	</div>
	<br>
	<div style="text-align:center;">


		<table class="table table-striped">
			<thead>
				<tr>
					<th>序号</th>
					<th>用户名</th>
					<th>昵称</th>
					<th>空间</th>
					<th>同意</th>
					<th>拒绝</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${Page!=null }">
					<c:forEach items="${Page }" var="p" varStatus="no">
						<tr <c:if test="${no.count%5==1 }"></c:if>
							<c:if test="${no.count%5==2 }">class="success"</c:if>
							<c:if test="${no.count%5==3 }">class="error"</c:if>
							<c:if test="${no.count%5==4 }">class="warning"</c:if>
							<c:if test="${no.count%5==0 }">class="info"</c:if>>
							<td>${no.count}&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>${p.username }</td>
							<td>${p.nickname }</td>
							<td><a href="/view/space.do?id=${p.id }">访问</a></td>
							<td><a onclick="return confirm('同意？该好友将会加入你的好友列表。');"
								href="${pageContext.request.contextPath}/user/checkfriend.do?id=${p.id }">【同意】</a></td>
							<td><a onclick="return confirm('拒绝？该好友从请求列表中删除。');"
								href="${pageContext.request.contextPath}/user/delete_friend.do?id=${p.id }">删除</a></td>


						</tr>
					</c:forEach>

				</c:if>
			</tbody>
		</table>
		<br>
		<button class="btn btn-large btn-info" type="button" id="gohome" >返回我的好友列表</button>

	</div>
</div>

<script type="text/javascript">

$(function(){
    $('#gohome').click(function(){
        $.pjax({
            url: '/view/myfriend.do',
            container: '#pjax-container',
            timeout: 10000
        });
    });

}); 
</script>
