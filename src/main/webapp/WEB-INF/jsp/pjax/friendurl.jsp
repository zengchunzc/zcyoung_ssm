<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<title>友链-ZCYOUNG 年轻人</title>
<c:if test="${msg!=null }">
	<div class="alert alert-info">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>提示!</strong> ${msg }.
	</div>
</c:if>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">友链区，注册会员可以在此申请友链哦！</h3>
	</div>
</div>
<table class="table table-striped">
	<thead>
		<tr>
			<th>序号</th>
			<th>网站名称</th>
			<th>地址</th>
			<th>申请时间</th>
			<th>点击量</th>
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
					<td>${p.name }</td>
					<c:if test="${fn:length(p.url)>40}">
						<td><a href="/view/redirect.do?id=${p.id }" target="_Blank">${fn:substring(p.url, 0, 40)}...</a></td>
					</c:if>
					<c:if test="${fn:length(p.url)<=40}">
						<td><a href="/view/redirect.do?id=${p.id }" target="_Blank">${p.url }</a></td>
					</c:if>
					<td>${p.time }</td>
					<td>${p.click }</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
<div class="pagination pagination-centered">
	<ul>
		<li><a data-pjax href="/view/friendurl.do?page=1">&lt;&lt;</a></li>
		<li><a data-pjax
			href="/view/friendurl.do?page=${Page.pageIndex-1 }">&lt;</a></li>
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
			<li><a data-pjax href="/view/friendurl.do?page=${now }"><c:if
						test="${Page.pageIndex == now }">
						<strong><b><u>${now }</u></b></strong>
					</c:if> <c:if test="${Page.pageIndex != now }">${now }</c:if></a></li>
		</c:forEach>
		<li><a data-pjax
			href="/view/friendurl.do?page=${Page.pageIndex+1 }">&gt;</a></li>
		<li><a data-pjax
			href="/view/friendurl.do?page=${Page.totalPages }">&gt;&gt;</a></li>
	</ul>
</div>

<div style="text-align:center;">

	<form action="${pageContext.request.contextPath}/user/addfriendurl.do"
		method="post">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">申请友链</h3>
			</div>
			<div class="panel-body">
				网站名称：<input type="text" name="name">
			</div>
			<div class="panel-footer">
				网站地址：<input type="text" name="url">
			</div>
			<div class="panel-body">
				用户昵称：
				<c:if test="${User==null }">您还未登录，不能申请，点击<a
						href="${pageContext.request.contextPath }/view/login.do?fromUrl=/view/friendurl.do">登录</a>
				</c:if>
				<c:if test="${User!=null }">
					<input type="text" disabled="disabled" value="${User.nickname }">
				</c:if>
			</div>
			<div class="panel-footer">
				<button class="btn btn-large btn-info" type="submit"
					<c:if test="${User==null }">disabled="disabled"</c:if>>提交申请</button>
				<button class="btn btn-large btn-info" type="button" id="gohome" >返回首页</button>

			</div>
		</div>
	</form>
</div>

<script type="text/javascript">

$(function(){
    $('#gohome').click(function(){
        $.pjax({
            url: '/view/index.do',
            container: '#pjax-container',
            timeout: 10000
        });
    });


}); 
</script>