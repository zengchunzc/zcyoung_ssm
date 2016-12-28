<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="head.jsp"></jsp:include>
<title>acm基地周赛记录</title>
</head>
<body>

<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">acm基地周赛记录(<a href="/acm/rank">刷新</a>)</h3>
	</div>
</div>
<table class="table table-striped">
	<thead>
		<tr>
			<th>学号</th>
			<th>姓名</th>
			<th>本月积分</th>
			<th>年度积分</th>
			<th>查看积分明细</th>
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
					<td>${p.user.username }</td>
					<td>${p.user.nickname }</td>
					<td>${p.month }</td>
					<td>${p.year }</td>
					<td><a href="/acm/rank/${p.user.username }">查看</a></td>
				</tr>
			</c:forEach>

		</c:if>
	</tbody>
</table>
<c:if test="${User!=null && User.role=='admin' }">
	<div  style="text-align:center;">
		<c:if test="${msg!=null }">${msg }<br></c:if>
		删除某一场周赛记录
		<form action="/acm/delete" method="post">
		周赛名称：<input type="text" name="title" /><br>
		<input type="submit" value="提交"/>
		</form>
	
		新增周赛信息
		<form action="/acm/add" method="post">
			周赛名称：<input type="text" name="title" /><br>
			实时排名页面网页源码<br>
			<textarea rows="10" cols="10" name="html"></textarea><br />
			<input type="submit" value="提交" />
		</form>
		<a href="/acm/newmonth" onclick="return confirm('请确认，当前已经进行了4次周赛时，可以点击')">【开始新的一个月】</a>
	</div>
</c:if>
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