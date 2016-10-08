<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>  
<title>文章列表-ZCYOUNG 年轻人</title>
<c:if test="${msg!=null }">
	<div class="alert alert-info">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>提示!</strong> ${msg }.
	</div>
</c:if>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">我的文章</h3>
	</div>
</div>
<div style="text-align:right;">
	<form data-pjax action="/article/my" method="post">
		 <input type="text" name="key"  placeholder="请输入关键字，回车搜索文章">
	</form>
</div>
<table class="table table-striped">
	<thead>
		<tr>
			<th>序号</th>
			<th>标题</th>
			<th>发布时间</th>
			<th>上次编辑</th>
			<th>点击量</th>
			<th></th>
			<th></th>
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
					<td><a data-pjax href="/article/${p.id }">${p.title }</a></td>
					<td><fmt:formatDate value="${p.wTime }" type="both" /></td>
					<td><fmt:formatDate value="${p.uTime }" type="both" /></td>
					<td>${p.click }</td>
					<td><a href="/article/rewrite/${p.id }">编辑</a></td>
					<td><a data-pjax onclick="return confirm('确定删除?删除后无法恢复。');"
						href="/article/delete/${p.id}">删除</a></td>
				</tr>
			</c:forEach>

		</c:if>
	</tbody>
</table>
<div class="pagination pagination-centered">
	<ul>
			<li><a data-pjax href="/article/my/1/${Key}">&lt;&lt;</a></li>
			<li><a data-pjax href="/article/my/${Page.pageIndex-1 }/${Key}">&lt;</a></li>
			<c:set value="1" var="now" />
			<c:forEach var="item" varStatus="vs" begin="1" end="5">
				<c:if test="${Page.pageIndex > 3 && Page.pageIndex < Page.totalPages - 3 }">
					<c:set value="${vs.count+Page.pageIndex-3 }" var="now" />
				</c:if>
				<c:if test="${Page.pageIndex > 3 && Page.pageIndex >= Page.totalPages - 3 }">
					<c:set value="${vs.count+Page.totalPages-5 }" var="now" />
				</c:if>
				<c:if test="${Page.pageIndex <= 3 || Page.totalPages <= 5}"><c:set value="${vs.count }" var="now" /></c:if>
				<li><a data-pjax href="/article/my/${now }/${Key}">
					<c:if test="${Page.pageIndex == now }"><strong><b><u>${now }</u></b></strong></c:if> 
					<c:if test="${Page.pageIndex != now }">${now }</c:if>
				</a></li>
			</c:forEach>
			<li><a data-pjax href="/article/my/${Page.pageIndex+1 }/${Key}">&gt;</a></li>
			<li><a data-pjax href="/article/my/${Page.totalPages }/${Key}">&gt;&gt;</a></li>
		</ul>
	<br>
	<br>
	<button class="btn btn-large btn-info" type="button" id="up" >写新文章</button>
	&nbsp;&nbsp;
	<button class="btn btn-large btn-info" type="button" id="gohome">返回个人中心</button>
</div>

<script type="text/javascript">
$(function(){
    $(document).on('submit', 'form[data-pjax]', function(event) {
  		$.pjax.submit(event, '#pjax-container')
	});
}); 
$(function(){
    $('#gohome').click(function(){
        $.pjax({
            url: '/view/home',
            container: '#pjax-container',
            timeout: 10000
        });
    });
}); 
$(function(){
    $('#up').click(function(){
        $.pjax({
            url: '/article/write',
            container: '#pjax-container',
            timeout: 10000
        });
    });
}); 
</script>
