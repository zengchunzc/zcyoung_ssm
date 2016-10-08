<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<title>管理友链-ZCYOUNG年轻人</title>

<c:if test="${msg!=null }">
	<div class="alert alert-info">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>提示!</strong> ${msg }.
	</div>
</c:if>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">管理友链</h3>
	</div>
</div>
<table class="table table-striped">
	<thead>
		<tr>
			<th>序号</th>
			<th>网站名称</th>
			<th>地址</th>
			<th>申请时间</th>
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
					<td>${p.name }</td>
					<c:if test="${fn:length(p.url)>40}">
						<td><a href="/view/redirect.do?id=${p.id }" target="_Blank">${fn:substring(p.url, 0, 40)}...</a></td>
					</c:if>
					<c:if test="${fn:length(p.url)<=40}">
						<td><a href="/view/redirect.do?id=${p.id }" target="_Blank">${p.url }</a></td>
					</c:if>
					<td>${p.time }</td>
					<td>${p.click }</td>
					<td><c:if test="${p.check_state==1 }">已审核</c:if> <c:if
							test="${p.check_state!=1 }">
							<a href="/admin/checkfriendurl.do?id=${p.id }"
								onclick="return confirm('确定通过审核?');">审核</a>
						</c:if></td>
					<td><a href="/admin/deletefriendurl.do?id=${p.id }"
						onclick="return confirm('确定删除“${p.name}”这条友链?');">删除</a></td>

				</tr>
			</c:forEach>

		</c:if>
	</tbody>
</table>
<div class="pagination pagination-centered">
	<ul>
			<li><a data-pjax href="/view/admin_friendurl.do?page=1">&lt;&lt;</a></li>
			<li><a data-pjax href="/view/admin_friendurl.do?page=${Page.pageIndex-1 }">&lt;</a></li>
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
				<li><a data-pjax href="/view/admin_friendurl.do?page=${now }"><c:if
							test="${Page.pageIndex == now }">
							<strong><b><u>${now }</u></b></strong>
						</c:if> <c:if test="${Page.pageIndex != now }">${now }</c:if></a></li>
			</c:forEach>
			<li><a data-pjax href="/view/admin_friendurl.do?page=${Page.pageIndex+1 }">&gt;</a></li>
			<li><a data-pjax href="/view/admin_friendurl.do?page=${Page.totalPages }">&gt;&gt;</a></li>
		</ul>
</div>

<div style="text-align:center;">


	<div class="panel-footer">
		<button class="btn btn-large btn-info" type="button" id="goadmin" >返回管理</button>
		&nbsp;
		<button class="btn btn-large btn-info" type="button" id="gohome" >返回中心</button>

	</div>
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
    $('#goadmin').click(function(){
        $.pjax({
            url: '/view/admin.do',
            container: '#pjax-container',
            timeout: 10000
        });
    });
    
}); 
</script>

