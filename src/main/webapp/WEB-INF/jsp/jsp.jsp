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
<title>${title }</title>

</head>
<body>
	<div id='waitBar' class="zcwait">
		<img src='/images/load.gif'>
	</div>
	<div class="container-fluid">
		<jsp:include page="page1.jsp"></jsp:include>
		<div class="row-fluid">
			<div class="span1"></div>
			<div class="span8" id="pjax-container">
				<jsp:include page="${url }"></jsp:include>
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


