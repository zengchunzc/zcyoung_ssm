<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 
<title>管理会员-ZCYOUNG 年轻人</title>

<c:if test="${msg!=null }">
	<div class="alert alert-info">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>提示!</strong> ${msg }.
	</div>
</c:if>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">管理会员</h3>
	</div>
</div>
<div style="text-align:right;">
	<form data-pjax action="/admin/user" method="post">
		 <input type="text" name="key"  placeholder="请输入关键字，回车搜索会员">
	</form>
</div>
<table class="table table-striped">
	<thead>
		<tr>
			<th>序号</th>
			<th>用户名</th>
			<th>昵称</th>
			<th>邮箱</th>
			<th>等级</th>
			<th>上次登录</th>
			<th>重置密码</th>
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
					<td><a data-pjax href="/view/space/${p.username }">${p.username }</a></td>
					<td>${p.nickname }</td>
					<td>${p.email }</td>
					<td>${p.role }</td>
					<td><fmt:formatDate value="${p.preTime }" type="both" /></td>
					<td><a data-pjax href="/admin/repassword/${p.id }"
						onclick="return confirm('确定重置${p.nickname}的密码?');">重置</a></td>
				</tr>
			</c:forEach>

		</c:if>
	</tbody>
</table>
<div class="pagination pagination-centered">
	<ul>
			<li><a data-pjax href="/admin/user/1/${Key}">&lt;&lt;</a></li>
			<li><a data-pjax href="/admin/user/${Page.pageIndex-1 }/${Key}">&lt;</a></li>
			<c:set value="1" var="now" />
			<c:forEach var="item" varStatus="vs" begin="1" end="5">
				<c:if test="${Page.pageIndex > 3 && Page.pageIndex < Page.totalPages - 3 }">
					<c:set value="${vs.count+Page.pageIndex-3 }" var="now" />
				</c:if>
				<c:if test="${Page.pageIndex > 3 && Page.pageIndex >= Page.totalPages - 3 }">
					<c:set value="${vs.count+Page.totalPages-5 }" var="now" />
				</c:if>
				<c:if test="${Page.pageIndex <= 3 || Page.totalPages <= 5}"><c:set value="${vs.count }" var="now" /></c:if>
				<li><a data-pjax href="/admin/user/${now }/${Key}">
					<c:if test="${Page.pageIndex == now }"><strong><b><u>${now }</u></b></strong></c:if> 
					<c:if test="${Page.pageIndex != now }">${now }</c:if>
				</a></li>
			</c:forEach>
			<li><a data-pjax href="/admin/user/${Page.pageIndex+1 }/${Key}">&gt;</a></li>
			<li><a data-pjax href="/admin/user/${Page.totalPages }/${Key}">&gt;&gt;</a></li>
		</ul>
</div>

<div style="text-align:center;">


	<div class="panel-footer">
		<button class="btn btn-large btn-info" type="button" id="goadmin" >返回管理</button>
		&nbsp;
		<button class="btn btn-large btn-info" type="button" id="gohome" >返回首页</button>

	</div>
</div>


<script type="text/javascript">

$(function(){
    $('#gohome').click(function(){
        $.pjax({
            url: '/view/home',
            container: '#pjax-container',
            timeout: 10000
        });
    });
    $('#goadmin').click(function(){
        $.pjax({
            url: '/view/admin',
            container: '#pjax-container',
            timeout: 10000
        });
    });
}); 
</script>
