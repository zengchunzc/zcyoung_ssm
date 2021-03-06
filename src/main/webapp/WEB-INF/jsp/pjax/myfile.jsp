<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 
<title>我的文件-ZCYOUNG年轻人</title>

<c:if test="${msg!=null }">
	<div class="alert alert-info">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>提示!</strong> ${msg }.
	</div>
</c:if>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">我的附件</h3>
	</div>
</div>
<table class="table table-striped">
	<thead>
		<tr>
			<th>序号</th>
			<th>文件名</th>
			<th>大小</th>
			<th>上传时间</th>
			<th>是否公开</th>
			<th>地址</th>
			<th>删除</th>
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
					<td><c:if test="${p.pri==0 }">是</c:if>
						<c:if test="${p.pri==1 }">否</c:if></td>
					<td><a href="/file/down/${p.id }">下载</a></td>
					<td><a data-pjax href="/file/delete/${p.id }"
						onclick="return confirm('确定删除?删除后无法恢复。');">删除</a></td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
<div class="pagination pagination-centered">
	<ul>
			<li><a data-pjax href="/file/my/1">&lt;&lt;</a></li>
			<li><a data-pjax href="/file/my/${Page.pageIndex-1 }">&lt;</a></li>
			<c:set value="1" var="now" />
			<c:forEach var="item" varStatus="vs" begin="1" end="5">
				<c:if test="${Page.pageIndex > 3 && Page.pageIndex < Page.totalPages - 3 }">
					<c:set value="${vs.count+Page.pageIndex-3 }" var="now" />
				</c:if>
				<c:if test="${Page.pageIndex > 3 && Page.pageIndex >= Page.totalPages - 3 }">
					<c:set value="${vs.count+Page.totalPages-5 }" var="now" />
				</c:if>
				<c:if test="${Page.pageIndex <= 3 || Page.totalPages <= 5}"><c:set value="${vs.count }" var="now" /></c:if>
				<li><a data-pjax href="/file/my/${now }">
					<c:if test="${Page.pageIndex == now }"><strong><b><u>${now }</u></b></strong></c:if> 
					<c:if test="${Page.pageIndex != now }">${now }</c:if>
				</a></li>
			</c:forEach>
			<li><a data-pjax href="/file/my/${Page.pageIndex+1 }">&gt;</a></li>
			<li><a data-pjax href="/file/my/${Page.totalPages }">&gt;&gt;</a></li>
		</ul>
	<br> <br>
	<button class="btn btn-large btn-info" type="button" id="up" >上传文件</button>
	&nbsp;&nbsp;
	<button class="btn btn-large btn-info" type="button" id="gohome">返回个人中心</button>

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
}); 
$(function(){
    $('#up').click(function(){
        $.pjax({
            url: '/file/upload',
            container: '#pjax-container',
            timeout: 10000
        });
    });
}); 
</script>