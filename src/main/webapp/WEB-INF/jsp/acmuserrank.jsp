<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="head.jsp"></jsp:include>
<title>${U.nickname }的得分记录</title>
</head>
<body>

<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">${U.nickname }的得分记录(<a href="javascript:void(0);" onclick="javascript:history.go(-1);">返回上一页</a>)</h3>
	</div>
</div>
<table class="table table-striped">
	<thead>
		<tr>
			<th>序号</th>
			<th>信息</th>
			<th>时间</th>
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
					<td>${no.count }</td>
					<td>${p.mess }</td>
					<td><fmt:formatDate value="${p.time  }" type="both" /></td>
				</tr>
			</c:forEach>

		</c:if>
	</tbody>
</table>

<div  style="text-align:center;">
积分规则：（如有作弊行为，积分清零）<br />
		0：积分每月清零<br />
		1：做出一题		+10分<br />
		2：每道题1a		+2分<br />
		3：每场周赛第一名		+6分<br />
		4：每场周赛第二名		+3分<br />
		5：每场周赛第三名		+2分<br />
		6：Ak当次比赛全部题目		+3分<br />
		7：当次全部ac的题目中没有wa提交的		+2分<br />
		规则后续完善中......
</div>

</body>
</html>