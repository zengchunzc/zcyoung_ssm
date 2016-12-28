<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  

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
				<a data-pjax href="/news/${p.id }">${p.name }</a>
			</h4>
			<img src="${p.img }" class="topic_img"/>
			<p>${p.bodyPre }</p>
			<div class="biaoqian">
			<p align="right">标签：${p.author }&nbsp;&nbsp;发布时间： <fmt:formatDate value="${p.sendtime }" type="both" /></p>
			</div>
			
		</div>
	</c:forEach>

	<div class="pagination pagination-centered">
		<ul>
			<li><a data-pjax href="/view/index/1">&lt;&lt;</a></li>
			<li><a data-pjax href="/view/index/${Page.pageIndex-1 }">&lt;</a></li>
			<c:set value="1" var="now" />
			<c:forEach var="item" varStatus="vs" begin="1" end="5">
				<c:if test="${Page.pageIndex <= 3 }"><c:set value="${vs.count }" var="now" /></c:if>
				<c:if test="${Page.pageIndex > 3 && Page.pageIndex < Page.totalPages - 3 }">
					<c:set value="${vs.count+Page.pageIndex-3 }" var="now" />
				</c:if>
				<c:if test="${Page.pageIndex > 3 && Page.pageIndex >= Page.totalPages - 3 }">
					<c:set value="${vs.count+Page.totalPages-5 }" var="now" />
				</c:if>
				<li><a data-pjax href="/view/index/${now }">
					<c:if test="${Page.pageIndex == now }"><strong><b><u>${now }</u></b></strong></c:if> 
					<c:if test="${Page.pageIndex != now }">${now }</c:if>
				</a></li>
			</c:forEach>
			<li><a data-pjax href="/view/index/${Page.pageIndex+1 }">&gt;</a></li>
			<li><a data-pjax href="/view/index/${Page.totalPages }">&gt;&gt;</a></li>
		</ul>
	</div>
</c:if>
<c:if test="${Page == null }">出错啦，嘿嘿嘿...</c:if>
