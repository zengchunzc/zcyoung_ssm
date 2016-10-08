<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<title>登录历史-ZCYOUNG 年轻人</title>

<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">${User.nickname }，这是您的登录记录：</h3>
	</div>
</div>
<table class="table table-striped">
	<thead>
		<tr>
			<th>序号</th>
			<th>登录时间</th>
			<th>IP地址</th>
			<th>浏览器</th>
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
					<td>${p.l_time }</td>
					<td>${p.l_ip }</td>
					<c:if test="${fn:length(p.user_agent)>90}">
						<td>${fn:substring(p.user_agent, 0, 90)}...</td>
					</c:if>
					<c:if test="${fn:length(p.user_agent)<=90}">
						<td>${p.user_agent }</td>
					</c:if>

				</tr>
			</c:forEach>

		</c:if>
	</tbody>
</table>
<div class="pagination pagination-centered">
	<ul>
			<li><a data-pjax href="/view/login_history.do?page=1">&lt;&lt;</a></li>
			<li><a data-pjax href="/view/login_history.do?page=${Page.pageIndex-1 }">&lt;</a></li>
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
				<li><a data-pjax href="/view/login_history.do?page=${now }"><c:if
							test="${Page.pageIndex == now }">
							<strong><b><u>${now }</u></b></strong>
						</c:if> <c:if test="${Page.pageIndex != now }">${now }</c:if></a></li>
			</c:forEach>
			<li><a data-pjax href="/view/login_history.do?page=${Page.pageIndex+1 }">&gt;</a></li>
			<li><a data-pjax href="/view/login_history.do?page=${Page.totalPages }">&gt;&gt;</a></li>
		</ul>
</div>
<div style="text-align:center;">
	<button class="btn btn-large btn-info" type="button" id="ToSafe">返回安全中心</button>
	<button class="btn btn-large btn-info" type="button" id="gohome">返回个人中心</button>
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
    $('#ToSafe').click(function(){
        $.pjax({
            url: '/view/safe.do',
            container: '#pjax-container',
            timeout: 10000
        });
    });
}); 
</script>