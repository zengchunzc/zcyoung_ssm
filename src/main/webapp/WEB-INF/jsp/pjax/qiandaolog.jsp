<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<title>流量签到日志-ZCYOUNG年轻人</title>
<c:if test="${msg!=null }">
	<div class="alert alert-info">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>提示!</strong> ${msg }.
	</div>
</c:if>
<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">管理员你好，你可以在这里查看日志</h4>
	</div>
</div>
<table class="table table-striped">
	<thead>
		<tr>
			<th>序号</th>
			<th>内容</th>
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
					<td>${no.count+((Page.pageIndex-1)*Page.pageSize) }&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>${p.data }</td>
					<td>${p.time }</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
<div class="pagination pagination-centered">
	<ul>
		<li><a data-pjax href="/view/qiandaolog.do?page=1">&lt;&lt;</a></li>
		<li><a data-pjax
			href="/view/qiandaolog.do?page=${Page.pageIndex-1 }">&lt;</a></li>
		<c:set value="1" var="now" />
		<c:forEach var="item" varStatus="vs" begin="1" end="5">
			<c:if test="${Page.pageIndex <= 3 }">
				<c:set value="${vs.count }" var="now" />
			</c:if>
			<c:if test="${Page.pageIndex > Page.totalPages }">
				<c:set value="${vs.count+Page.totalPages-5 }" var="now" />
			</c:if>
			<c:if
				test="${Page.pageIndex >3 && Page.pageIndex <= Page.totalPages }">
				<c:set value="${vs.count+Page.pageIndex-3 }" var="now" />
			</c:if>
			<li><a data-pjax href="/view/qiandaolog.do?page=${now }"><c:if
						test="${Page.pageIndex == now }">
						<strong><b><u>${now }</u></b></strong>
					</c:if> <c:if test="${Page.pageIndex != now }">${now }</c:if></a></li>
		</c:forEach>
		<li><a data-pjax
			href="/view/qiandaolog.do?page=${Page.pageIndex+1 }">&gt;</a></li>
		<li><a data-pjax href="/view/qiandaolog.do?page=${Page.totalPages }">&gt;&gt;</a></li>
	</ul>
</div>

<div style="text-align:center;">
		<button class="btn btn-large btn-info" type="button" id="goliuliang">返回签到</button>
		<button class="btn btn-large btn-info" type="button" id="gohome">返回管理</button>
</div>
<script type="text/javascript">

$(function(){
    $('#goliuliang').click(function(){
        $.pjax({
            url: '/view/qiandao.do',
            container: '#pjax-container',
            timeout: 10000
        });
    });
    $('#gohome').click(function(){
        $.pjax({
            url: '/view/admin.do',
            container: '#pjax-container',
            timeout: 10000
        });
    });
}); 

</script>
