<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	<form data-pjax action="/view/newslist.do" method="post">
		搜索新闻： <input type="text" name="key" value="${key }"> <input
			type="submit" class="btn btn-info" value="搜索">
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
					<td><a data-pjax href="/view/news.do?id=${p.id }">${p.name }</a></td>
					<td>${p.author }</td>
					<td>${p.sendtime }</td>
					<td>${p.click }</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
<div class="pagination pagination-centered">
	<ul>
		<li><a data-pjax href="/view/newslist.do?page=1&key=${key}">&lt;&lt;</a>
		</li>
		<li><a data-pjax href="/view/newslist.do?page=${Page.pageIndex-1}&key=${key}">&lt;</a>
		</li>
		<c:forEach var="item" varStatus="vs" begin="1" end="5">
			<c:if test="${Page.pageIndex<=3 }">
				<li><a data-pjax href="/view/newslist.do?page=${vs.count}&key=${key}">
						<c:if test="${vs.count==Page.pageIndex }">
							<strong><b><u>${vs.count}</u></b></strong>
						</c:if> <c:if test="${vs.count!=Page.pageIndex }">
  								${vs.count}
  							</c:if>
				</a></li>
			</c:if>
			<c:if test="${Page.pageIndex>3 && Page.pageIndex<Page.totalPages-3}">
				<li><a data-pjax href="/view/newslist.do?page=${vs.count+Page.pageIndex-3}&key=${key}">


						<c:if test="${vs.count+Page.pageIndex-3==Page.pageIndex }">
							<strong><b><u>${vs.count+Page.pageIndex-3}</u></b></strong>
						</c:if> <c:if test="${vs.count+Page.pageIndex-3!=Page.pageIndex }">
  								${vs.count+Page.pageIndex-3}
  							</c:if>
				</a></li>
			</c:if>
			<c:if test="${Page.pageIndex>3 && Page.pageIndex>=Page.totalPages-3}">
				<li><a data-pjax href="/view/newslist.do?page=${Page.totalPages-5+vs.count}&key=${key}">
						<c:if test="${Page.totalPages-5+vs.count==Page.pageIndex }">
							<strong><b><u>${Page.totalPages-5+vs.count}</u></b></strong>
						</c:if> <c:if test="${Page.totalPages-5+vs.count!=Page.pageIndex }">
  								${Page.totalPages-5+vs.count}
  							</c:if>
				</a></li>
			</c:if>
		</c:forEach>
		<li><a data-pjax href="${pageContext.request.contextPath}/view/newslist.do?page=${Page.pageIndex+1}&key=${key}">&gt;</a>
		</li>
		<li><a data-pjax href="${pageContext.request.contextPath}/view/newslist.do?page=${Page.totalPages }&key=${key}">&gt;&gt;</a>
		</li>
	</ul>
</div>

<script type="text/javascript">
$(document).on('submit', 'form[data-pjax]', function(event) {
  $.pjax.submit(event, '#pjax-container')
})
</script>


