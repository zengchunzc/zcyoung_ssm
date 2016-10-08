<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="row-fluid">
	<div class="span1"></div>
	<div class="span10">
		<div class="page-header">
			<h1> ZCYOUNG<small>小伙的主页</small> </h1>
		</div>
		<div id="bar">
			<c:import url="/view/bar.do"></c:import>
		</div>
	</div>
	<div class="span1"></div>
</div>
