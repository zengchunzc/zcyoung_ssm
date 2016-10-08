<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>伙伴的空间-ZCYOUNG 年轻人</title>

<div style="text-align:center;">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				欢迎来到${friend.nickname }的空间<br>
				<img alt=""
					<c:if test="${friend.pic==null||friend.pic=='' }"> src="/upload/nopic.jpg"</c:if>
					<c:if test="${friend.pic!=null&&friend.pic!='' }">src="${friend.pic }" </c:if>
					width="10%" height="">
			</h3>
		</div>
		<div class="panel-body">用户账号：${friend.username  }</div>
		<div class="panel-footer">用户昵称：${friend.nickname  }</div>
		<div class="panel-body">个性签名：${friend.signature }</div>
		<div class="panel-footer">用户邮箱：${friend.email  }</div>

	</div>
	<button class="btn btn-large btn-info" type="button" 
		onclick="location='../view/sendmessage.do?toId=${param.id}'">发送私信</button>
	&nbsp;
	<button class="btn btn-large btn-info" type="button"
		onclick="location='../view/addfriend.do?ms=${friend.nickname}'">加为好友</button>
	&nbsp;
	<button class="btn btn-large btn-info" type="button"
		onclick="location='../view/farticle.do?id=${friend.id}'">查看${friend.nickname }的文章</button>

</div>
