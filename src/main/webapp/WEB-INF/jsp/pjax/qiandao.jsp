<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<title>流量签到管理-ZCYOUNG年轻人</title>
<c:if test="${msg!=null }">
	<div class="alert alert-info">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>提示!</strong> ${msg }.
	</div>
</c:if>
<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">管理员你好，你可以在这里管理签到人员</h4>
	</div>
</div>
<table class="table table-striped">
	<thead>
		<tr>
			<th>序号</th>
			<th>姓名</th>
			<th>号码</th>
			<th>签到时刻</th>
			<th>加入时间</th>
			<th>上次签到</th>
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
					<td>${p.phoneno }</td>
					<td>每天${p.qtime }点  <a href="javascript:void(0);" onclick="dialog(${p.id},${p.qtime});">修改</a></td>
					<td>${p.r_time }</td>
					<td>${p.pre_time }</td>
					<td><a data-pjax href="/admin/qiandaonow.do?id=${p.id }" onclick="return confirm('确定立即签到?');">签到</a></td>
					<td><a data-pjax href="/admin/deleteqiandao.do?id=${p.id }" onclick="return confirm('确定删除?');">删除</a></td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
<div class="pagination pagination-centered">
	<ul>
		<li><a data-pjax href="/view/qiandao.do?page=1">&lt;&lt;</a></li>
		<li><a data-pjax
			href="/view/qiandao.do?page=${Page.pageIndex-1 }">&lt;</a></li>
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
			<li><a data-pjax href="/view/qiandao.do?page=${now }"><c:if
						test="${Page.pageIndex == now }">
						<strong><b><u>${now }</u></b></strong>
					</c:if> <c:if test="${Page.pageIndex != now }">${now }</c:if></a></li>
		</c:forEach>
		<li><a data-pjax
			href="/view/qiandao.do?page=${Page.pageIndex+1 }">&gt;</a></li>
		<li><a data-pjax href="/view/qiandao.do?page=${Page.totalPages }">&gt;&gt;</a></li>
	</ul>
</div>
<div style="text-align:center;">
	<form data-pjax action="/admin/addqiandao.do"
		method="post">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">添加号码</h4>
			</div>
			<div class="panel-body">
				姓名：<input type="text" name="name">
			</div>
			<div class="panel-footer">
				号码：<input type="text" name="no">
			</div>
			<div class="panel-body">
				签到时间（小时）：<input type="text" name="hour" value="5">
			</div>
			<div class="panel-footer">
				<button class="btn btn-large btn-info" type="submit">添加</button>
				<button class="btn btn-large btn-info" type="button" id="golog">查看日志</button>
				<button class="btn btn-large btn-info" type="button" id="gohome">返回管理</button>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">

$(function(){
    $('#gohome').click(function(){
        $.pjax({
            url: '/view/admin.do',
            container: '#pjax-container',
            timeout: 10000
        });
    });
    $('#golog').click(function(){
        $.pjax({
            url: '/view/qiandaolog.do',
            container: '#pjax-container',
            timeout: 10000
        });
    });
	$(document).on('submit', 'form[data-pjax]', function(event) {
  		$.pjax.submit(event, '#pjax-container')
	});
}); 

function dialog(id,time)
{
	var str=prompt("请输入要修改的每天签到时间 ",time);
	if(str)
	{
		$.pjax({
            url: '/admin/updateqiandao.do?id=' + id + '&time=' + str,
            container: '#pjax-container',
            timeout: 10000
        });
	}
}

</script>
