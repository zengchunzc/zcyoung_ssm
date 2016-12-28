<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<title>${Article.title }-文章区-ZCYOUNG年轻人</title>

<div class="jumbotron well">
	<c:if test="${msg!=null }">
		<div class="alert alert-info">
			<button type="button" class="close" data-dismiss="alert">×</button>
			${msg }
		</div>
	</c:if>
	<c:if test="${msg==null }">
		<button class="btn btn-info" type="button" onclick="javascript:history.go(-1);">返回</button>
		<h3 align="center">${Article.title }</h3>
		<p align="center">
			作者：<a data-pjax href="/view/space/${author.username }">${author.nickname }</a>&nbsp;&nbsp;&nbsp;发布于：<fmt:formatDate value="${Article.wTime }" type="both" />&nbsp;&nbsp;&nbsp;
			公开状态：<c:if test="${Article.readpower==2 }">公开</c:if><c:if test="${Article.readpower==1 }">私有</c:if>&nbsp;&nbsp;&nbsp; 阅读量：${Article.click }
		</p>${Article.body }
	</c:if>
</div>

