<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 
<title>下载区-ZCYOUNG年轻人</title>

<c:if test="${msg!=null }">
	<div class="alert alert-info">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>提示!</strong> ${msg }.
	</div>
</c:if>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">下载区</h3>
	</div>
</div>
<table class="table table-striped">
	<thead>
		<tr>
			<th>序号</th>
			<th>文件名</th>
			<th>大小</th>
			<th>上传时间</th>
			<th>地址</th>
			
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
					<c:if test="${fn:length(p.name)>20}">
						<td>${fn:substring(p.name, 0, 20)}...</td>
					</c:if>
					<c:if test="${fn:length(p.name)<=20}">
						<td>${p.name }</td>
					</c:if>
					<td>${p.size }</td>
					<td><fmt:formatDate value="${p.time }" type="both" /></td>
					<td><a href="/file/down/${p.id }">下载</a></td>
					
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
<div class="pagination pagination-centered">
	<ul>
			<li><a data-pjax href="/file/1">&lt;&lt;</a></li>
			<li><a data-pjax href="/file/${Page.pageIndex-1 }">&lt;</a></li>
			<c:set value="1" var="now" />
			<c:forEach var="item" varStatus="vs" begin="1" end="5">
				<c:if test="${Page.pageIndex <= 3 }"><c:set value="${vs.count }" var="now" /></c:if>
				<c:if test="${Page.pageIndex > 3 && Page.pageIndex < Page.totalPages - 3 }">
					<c:set value="${vs.count+Page.pageIndex-3 }" var="now" />
				</c:if>
				<c:if test="${Page.pageIndex > 3 && Page.pageIndex >= Page.totalPages - 3 }">
					<c:set value="${vs.count+Page.totalPages-5 }" var="now" />
				</c:if>
				<li><a data-pjax href="/file/${now }">
					<c:if test="${Page.pageIndex == now }"><strong><b><u>${now }</u></b></strong></c:if> 
					<c:if test="${Page.pageIndex != now }">${now }</c:if>
				</a></li>
			</c:forEach>
			<li><a data-pjax href="/file/${Page.pageIndex+1 }">&gt;</a></li>
			<li><a data-pjax href="/file/${Page.totalPages }">&gt;&gt;</a></li>
		</ul>
	<br> <br>

</div>
