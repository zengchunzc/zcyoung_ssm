<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<title>新闻列表-ZCYOUNG年轻人</title>

<c:if test="${msg!=null }">
	<div class="alert alert-info">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>提示!</strong> ${msg }.
	</div>
</c:if>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">新闻区</h3>
	</div>

</div>
<div style="text-align:right;">
	<form data-pjax action="/news" method="post">
		 <input type="text" name="key"  placeholder="请输入关键字，回车搜索新闻">
	</form>
</div>
<table class="table table-striped">
	<thead>
		<tr>
			<th>序号</th>
			<th>新闻名称</th>
			<th>标签</th>
			<th>发布时间</th>
			<th>点击量</th>
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
					<td><a data-pjax href="/news/${p.id }">${p.name }</a></td>
					<td>${p.author }</td>
					<td><fmt:formatDate value="${p.sendtime }" type="both" /></td>
					<td>${p.click }</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
<div class="pagination pagination-centered">
	<ul>
			<li><a data-pjax href="/news/list/1/${Key }">&lt;&lt;</a></li>
			<li><a data-pjax href="/news/list/${Page.pageIndex-1 }/${Key }">&lt;</a></li>
			<c:set value="1" var="now" />
			<c:forEach var="item" varStatus="vs" begin="1" end="5">
				<c:if test="${Page.pageIndex > 3 && Page.pageIndex < Page.totalPages - 3 }">
					<c:set value="${vs.count+Page.pageIndex-3 }" var="now" />
				</c:if>
				<c:if test="${Page.pageIndex > 3 && Page.pageIndex >= Page.totalPages - 3 }">
					<c:set value="${vs.count+Page.totalPages-5 }" var="now" />
				</c:if>
				<c:if test="${Page.pageIndex <= 3 || Page.totalPages <= 5}"><c:set value="${vs.count }" var="now" /></c:if>
				<li><a data-pjax href="/news/list/${now }/${Key }">
					<c:if test="${Page.pageIndex == now }"><strong><b><u>${now }</u></b></strong></c:if> 
					<c:if test="${Page.pageIndex != now }">${now }</c:if>
				</a></li>
			</c:forEach>
			<li><a data-pjax href="/news/list/${Page.pageIndex+1 }/${Key }">&gt;</a></li>
			<li><a data-pjax href="/news/list/${Page.totalPages }/${Key }">&gt;&gt;</a></li>
		</ul>
</div>

<script type="text/javascript">
$(document).on('submit', 'form[data-pjax]', function(event) {
  $.pjax.submit(event, '#pjax-container')
})
</script>


