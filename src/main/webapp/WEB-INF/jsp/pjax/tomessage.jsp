<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<title>发件箱-ZCYOUNG 年轻人</title>

<c:if test="${msg!=null }">
	<div class="alert alert-info">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>提示!</strong> ${msg }.
	</div>
</c:if>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">我的发件箱</h3>
	</div>
</div>
<table class="table table-striped">
	<thead>
		<tr>
			<th>序号</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${Page!=null }">
			<c:forEach items="${Page.datas }" var="p" varStatus="no">
				<tr <c:if test="${no.count%2==0 }"></c:if>
					<c:if test="${no.count%2==1 }">class="info"</c:if>>
					<td>【${no.count+((Page.pageIndex-1)*Page.pageSize) }】&nbsp;&nbsp;&nbsp;&nbsp;
						发送给<a href="/view/space.do?id=${p.toUser.id }">${p.toUser.nickname }</a>：${p.data }
						(${p.time })
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
<div class="pagination pagination-centered">
	<ul>
		<li><a data-pjax href="/view/tomessage.do?page=1">&lt;&lt;</a></li>
		<li><a data-pjax
			href="/view/tomessage.do?page=${Page.pageIndex-1 }">&lt;</a></li>
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
			<li><a data-pjax href="/view/tomessage.do?page=${now }"><c:if
						test="${Page.pageIndex == now }">
						<strong><b><u>${now }</u></b></strong>
					</c:if> <c:if test="${Page.pageIndex != now }">${now }</c:if></a></li>
		</c:forEach>
		<li><a data-pjax
			href="/view/tomessage.do?page=${Page.pageIndex+1 }">&gt;</a></li>
		<li><a data-pjax
			href="/view/tomessage.do?page=${Page.totalPages }">&gt;&gt;</a></li>
	</ul>
</div>
<div style="text-align:center;">
	<button class="btn btn-large btn-info" type="button" id="gosend" >返回收件箱</button>
	&nbsp;
	<button class="btn btn-large btn-info" type="button" id="gohome" >返回个人中心</button>

</div>

<script type="text/javascript">

$(function(){
    $('#gohome').click(function(){
        $.pjax({
            url: '/view/home.do',
            container: '#pjax-container',
            timeout: 10000
        });
    });
    $('#gosend').click(function(){
        $.pjax({
            url: '/view/message.do',
            container: '#pjax-container',
            timeout: 10000
        });
    });
}); 
</script>

