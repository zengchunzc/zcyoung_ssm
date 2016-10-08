<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel panel-default" id="left">

	<div class="list-group">
		<a data-pjax href="${pageContext.request.contextPath }/view/articlelist.do" class="list-group-item active">文章区</a>


		<c:forEach items="${ArticlePage.datas }" var="p">
			<div class="list-group-item" >
				<a data-pjax href="/view/article.do?id=${p.id }" style="text-decoration : none; color:black;">${p.title }</a>
			</div>
		</c:forEach>

		<!--<div class="list-group-item">
			<span class="badge">58</span>更多文章
		</div>-->
		<a data-pjax href="/view/newslist.do" class="list-group-item active">新闻区</a>
		<c:forEach items="${NewsPage.datas }" var="p">
			<div class="list-group-item" >
				<a data-pjax href="/view/news.do?id=${p.id }" style="text-decoration : none; color:black;">${p.name }</a>
			</div>
		</c:forEach>


		<a data-pjax href="/view/friendurl.do" class="list-group-item active">友链中心</a>
		
		<c:forEach items="${FUPage.datas }" var="p">
			<div class="list-group-item">
				<a href="/view/redirect.do?id=${p.id }" style="text-decoration : none; color:black;" target="_blank">${p.name }</a>
			</div>
		</c:forEach>
		
	</div>
	<div class="panel-footer" style="text-align:center;">
	<!--<script charset="Shift_JIS" src="http://chabudai.sakura.ne.jp/blogparts/honehoneclock/honehone_clock_wh.js"></script>
	-->
	前往&nbsp;<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1259967917'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1259967917' type='text/javascript'%3E%3C/script%3E"));</script>
	</div>

</div>