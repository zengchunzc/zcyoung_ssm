<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="head.jsp"></jsp:include>
<title>我的好友-ZCYOUNG年轻人</title>

</head>
<body>
	<div id='waitBar' class="zcwait">
		<img src='/images/load.gif'>
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span1"></div>
			<div class="span10">
				<div class="page-header">
					<h1>
						ZCYOUNG<small>小伙的主页</small>
					</h1>
				</div>
				<c:import url="/view/bar.do"></c:import>
			</div>
			<div class="span1"></div>
		</div>
		<div class="row-fluid">
			<div class="span1"></div>
			<div class="span8" id="pjax-container">
				<jsp:include page="pjax/myfriend.jsp"></jsp:include>
			</div>
			<div class="span2">
				<c:import url="/view/left.do"></c:import>
			</div>
			<div class="span1"></div>

		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>



</body>
</html>


