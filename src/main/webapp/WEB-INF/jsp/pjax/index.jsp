<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<title>首页-ZCYOUNG 年轻人</title>
<c:if test="${Page != null }">
	<c:forEach items="${Page.datas }" varStatus="vs" var="p">
		<div class="jumbotron well">
			<h4>
				<a data-pjax href="/view/news.do?id=${p.id }">${p.name }</a>
			</h4>
			<p>${p.bodyPre }</p>
			<p align="right">标签：${p.author }&nbsp;&nbsp;发布时间：${p.sendtime }</p>
		</div>
	</c:forEach>

	<div class="pagination pagination-centered">
		<ul>
			<li><a data-pjax href="/view/index.do?page=1">&lt;&lt;</a></li>
			<li><a data-pjax href="/view/index.do?page=${Page.pageIndex-1 }">&lt;</a></li>
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
				<li><a data-pjax href="/view/index.do?page=${now }"><c:if
							test="${Page.pageIndex == now }">
							<strong><b><u>${now }</u></b></strong>
						</c:if> <c:if test="${Page.pageIndex != now }">${now }</c:if></a></li>
			</c:forEach>
			<li><a data-pjax href="/view/index.do?page=${Page.pageIndex+1 }">&gt;</a></li>
			<li><a data-pjax href="/view/index.do?page=${Page.totalPages }">&gt;&gt;</a></li>
		</ul>
	</div>
</c:if>
<c:if test="${Page == null }">出错啦，嘿嘿嘿...</c:if>