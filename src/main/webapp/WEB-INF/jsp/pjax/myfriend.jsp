<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<title>我的好友-ZCYOUNG年轻人</title>
<c:if test="${msg!=null }">
	<div class="alert alert-info">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>提示!</strong> ${msg }.
	</div>
</c:if>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">我的好友</h3>
	</div>
</div>
<table class="table table-striped">
	<thead>
		<tr>
			<th>序号</th>
			<th>用户名</th>
			<th>昵称</th>
			<th>发送信息</th>
			<th>删除</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${Page!=null }">
			<c:forEach items="${Page.datas }" var="p" varStatus="no">
				<tr <c:if test="${no.count%5==1 }"></c:if>
					<c:if test="${no.count%5==2 }">class="success"</c:if>
					<c:if test="${no.count%5==3 }">class="error"</c:if>
					<c:if test="${no.count%5==4 }">class="warning"</c:if>
					<c:if test="${no.count%5==0 }">class="info"</c:if>>
					<td>${no.count+((Page.pageIndex-1)*Page.pageSize) }&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><a data-pjax href="/view/space.do?id=${p.id }">${p.username }</a></td>
					<td>${p.nickname }</td>
					<td><a data-pjax href="/view/sendmessage.do?toId=${p.id }">发送信息</a></td>
					<td><a onclick="return confirm('确定删除,这将从你和你好友的列表中同时删除?');"
						href="${pageContext.request.contextPath}/user/delete_friend.do?id=${p.id }">删除</a></td>

				</tr>
			</c:forEach>

		</c:if>
	</tbody>
</table>
<div class="pagination pagination-centered">
	<ul>
			<li><a data-pjax href="/view/myfriend.do?page=1">&lt;&lt;</a></li>
			<li><a data-pjax href="/view/myfriend.do?page=${Page.pageIndex-1 }">&lt;</a></li>
			<c:set value="1" var="now" />
			<c:forEach var="item" varStatus="vs" begin="1" end="5">
				<c:if test="${Page.pageIndex <= 3 }">
					<c:set value="${vs.count }" var="now" />
				</c:if>
				<c:if test="${Page.pageIndex > Page.totalPages }">
					<c:set value="${vs.count+Page.totalPages-5 }" var="now" />
				</c:if>
				<c:if
					test="${Page.pageIndex >3 && Page.pageIndex <= Page.totalPages }">
					<c:set value="${vs.count+Page.pageIndex-3 }" var="now" />
				</c:if>
				<li><a data-pjax href="/view/myfriend.do?page=${now }"><c:if
							test="${Page.pageIndex == now }">
							<strong><b><u>${now }</u></b></strong>
						</c:if> <c:if test="${Page.pageIndex != now }">${now }</c:if></a></li>
			</c:forEach>
			<li><a data-pjax href="/view/myfriend.do?page=${Page.pageIndex+1 }">&gt;</a></li>
			<li><a data-pjax href="/view/myfriend.do?page=${Page.totalPages }">&gt;&gt;</a></li>
		</ul>
	<br> <br>
	<button class="btn btn-large btn-info" type="button" id="addf" >添加好友</button>
	&nbsp;
	<button class="btn btn-large btn-info" type="button" id="cf" >审核好友请求</button>
	&nbsp;
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
    $('#addf').click(function(){
        $.pjax({
            url: '/view/addfriend.do',
            container: '#pjax-container',
            timeout: 10000
        });
    });
     $('#cf').click(function(){
        $.pjax({
            url: '/view/checkfriend.do',
            container: '#pjax-container',
            timeout: 10000
        });
    });
}); 
</script>