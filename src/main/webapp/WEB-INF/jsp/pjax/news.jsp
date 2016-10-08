<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<title>${News.name }-新闻区-ZCYOUNG年轻人</title>

<div class="jumbotron well">
	<c:if test="${msg!=null }">
		<div class="alert alert-info">
			<button type="button" class="close" data-dismiss="alert">×</button>
			${msg }
		</div>
	</c:if>
	<c:if test="${msg==null }">
		<button class="btn btn-info" type="button"
			onclick="javascript:history.go(-1);">返回</button>
		<h3 align="center">${News.name }</h3>
		<p align="center">
			标签：${News.author }&nbsp;&nbsp;&nbsp;发布于：<fmt:formatDate value="${News.sendtime }" type="both" />&nbsp;&nbsp;&nbsp;阅读数：${News.click }&nbsp;&nbsp;
			<a href="${News.source }" target="_blank">原始链接</a>
		</p>
	</c:if>
	<div id="nbody">${News.body }</div>
	<!-- end of nbody -->
</div>
<!-- end of jumbotron well -->