<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel panel-default" id="left">

	<div class="list-group">
		<a data-pjax href="/article" class="list-group-item active">文章区</a>

		<c:forEach items="${ArticlePage.datas }" var="p">
			<div class="list-group-item" >
				<a data-pjax href="/article/${p.id }" style="text-decoration : none; color:black;">${p.title }</a>
			</div>
		</c:forEach>

		<!--<div class="list-group-item">
			<span class="badge">58</span>更多文章
		</div>-->
		<a data-pjax href="javascript:void(0);" class="list-group-item active">工具区</a>
		<div class="list-group-item" >
		<a data-pjax href="/2weima" style="text-decoration : none; color:black;">二维码在线生成</a>
		</div>
		<a data-pjax href="/view/friendurl" class="list-group-item active">友链中心</a>
		
		<c:forEach items="${FUPage.datas }" var="p">
			<div class="list-group-item">
				<a href="/redirect/friendurl/${p.id }" style="text-decoration : none; color:black;" target="_blank">${p.name }</a>
			</div>
		</c:forEach>
		
	</div>
	<div class="panel-footer" style="text-align:center;">
	<!--<script charset="Shift_JIS" src="http://chabudai.sakura.ne.jp/blogparts/honehoneclock/honehone_clock_wh.js"></script>
	-->
	@ZCYOUNG 版权所有
	</div>

</div>