<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<title>我的好友-ZCYOUNG年轻人</title>
<c:if test="${msg!=null }">
	<div class="alert alert-info">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>提示!</strong> ${msg }.
	</div>
</c:if>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">我的好友</h3>
	</div>
</div>
<table class="table table-striped">
	<thead>
		<tr>
			<th>序号</th>
			<th>用户名</th>
			<th>昵称</th>
			<th>发送信息</th>
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
					<td><a data-pjax href="/view/space/${p.username }">${p.username }</a></td>
					<td>${p.nickname }</td>
					<td><a data-pjax href="javascript:void(0);" onclick="sendmessage(${p.id});">发送信息</a></td>
					<td><a data-pjax href="/user/deletefriend/${p.username }" onclick="return confirm('确定删除?删除后将在两人列表中删除,并向对方发送删除信息.');" >删除</a></td>
				</tr>
			</c:forEach>

		</c:if>
	</tbody>
</table>
<div class="pagination pagination-centered">
	<ul>
			<li><a data-pjax href="/view/myfriend/1/">&lt;&lt;</a></li>
			<li><a data-pjax href="/view/myfriend/${Page.pageIndex-1 }/${Key}">&lt;</a></li>
			<c:set value="1" var="now" />
			<c:forEach var="item" varStatus="vs" begin="1" end="5">
				<c:if test="${Page.pageIndex > 3 && Page.pageIndex < Page.totalPages - 3 }">
					<c:set value="${vs.count+Page.pageIndex-3 }" var="now" />
				</c:if>
				<c:if test="${Page.pageIndex > 3 && Page.pageIndex >= Page.totalPages - 3 }">
					<c:set value="${vs.count+Page.totalPages-5 }" var="now" />
				</c:if>
				<c:if test="${Page.pageIndex <= 3 || Page.totalPages <= 5}"><c:set value="${vs.count }" var="now" /></c:if>
				<li><a data-pjax href="/view/myfriend/${now }/${Key}">
					<c:if test="${Page.pageIndex == now }"><strong><b><u>${now }</u></b></strong></c:if> 
					<c:if test="${Page.pageIndex != now }">${now }</c:if>
				</a></li>
			</c:forEach>
			<li><a data-pjax href="/view/myfriend/${Page.pageIndex+1 }/${Key}">&gt;</a></li>
			<li><a data-pjax href="/view/myfriend/${Page.totalPages }/${Key}">&gt;&gt;</a></li>
		</ul>
	<br> <br>
	<button class="btn btn-large btn-info" type="button" onclick="dialog();" >添加好友</button>

	&nbsp;
	<button class="btn btn-large btn-info" type="button" id="gohome">返回个人中心</button>
</div>


<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">待审核好友</h3>
	</div>
</div>

<c:if test="${fn:length(CPage.datas) > 0}">

<table class="table table-striped">
	<thead>
		<tr>
			<th>序号</th>
			<th>用户名</th>
			<th>昵称</th>
			<th>操作</th>
			<th>拒绝</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${CPage!=null }">
			<c:forEach items="${CPage.datas }" var="p" varStatus="no">
				<tr <c:if test="${no.count%5==1 }"></c:if>
					<c:if test="${no.count%5==2 }">class="success"</c:if>
					<c:if test="${no.count%5==3 }">class="error"</c:if>
					<c:if test="${no.count%5==4 }">class="warning"</c:if>
					<c:if test="${no.count%5==0 }">class="info"</c:if>>
					<td>${no.count+((CPage.pageIndex-1)*CPage.pageSize) }&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><a data-pjax href="/view/space/${p.username }">${p.username }</a></td>
					<td>${p.nickname }</td>
					<td><a data-pjax href="/user/checkfriend/${p.username }" onclick="return confirm('确定同意?');" ">同意</a></td>
					<td><a data-pjax href="/user/deletetfriend/${p.username }" onclick="return confirm('确定拒绝?');" >拒绝</a></td>
				</tr>
			</c:forEach>

		</c:if>
	</tbody>
</table>
</c:if>
<c:if test="${fn:length(CPage.datas) <= 0}">
	暂时还没有新的好友申请哦。
</c:if>

<script type="text/javascript">
$(function(){
    $('#gohome').click(function(){
        $.pjax({
            url: '/view/home',
            container: '#pjax-container',
            timeout: 10000
        });
    });
    $('#addf').click(function(){
        $.pjax({
            url: '/view/addfriend',
            container: '#pjax-container',
            timeout: 10000
        });
    });
     $('#cf').click(function(){
        $.pjax({
            url: '/view/checkfriend',
            container: '#pjax-container',
            timeout: 10000
        });
    });
    
}); 


function dialog()
{
	var str=prompt("请输入对方的用户名 ");
	if(str)
	{
	try{
        $.post("/user/addfriend/"+str,{
		},function(data,status){
		if(status == "success"){
			if(data.indexOf("ok") != -1) alert("申请已发送。");
			else if(data.indexOf("error") != -1)alert("申请失败，可能已经是好友或已发送申请");
			else alert(data);
		}
	});
    }catch(e){alert(e);}
	}
}

function sendmessage(id)
{
	var str=prompt("请输入要发送的信息 ");
	if(str)
	{
	try{
        $.post("/message/send/",{
        id: id,
        data: str
		},function(data,status){
		if(status == "success"){
			if(data.indexOf("ok") != -1) alert("消息已发送。");
			else if(data.indexOf("error") != -1)alert("消息发送失败。");
			else alert(data);
		}
	});
    }catch(e){alert(e);}
	}
}


</script>