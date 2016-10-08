<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="navbar">
	<div class="navbar-inner">
		<div class="container-fluid">
			<a data-target=".navbar-responsive-collapse" data-toggle="collapse"
				class="btn btn-navbar"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a> <a data-pjax href="/view/index" class="brand">主页</a> 
			<a data-pjax href="/news" class="brand">新闻</a> 
			<a data-pjax href="/article" class="brand">文章</a> 
			<a data-pjax href="/file" class="brand">下载</a> 
			

			<div class="nav-collapse collapse navbar-responsive-collapse">
				<ul class="nav">

				</ul>
				<ul class="nav pull-right">
					<li>
					<c:if test="${User==null }">
						<a data-pjax href="/view/login">游客你好，请先登录</a>
					</c:if>
					<c:if test="${User!=null }">
						<c:if test="${messagecount!=null&&messagecount>0 }">
							<li><a data-pjax href="/message">【你有${messagecount }条新的消息！！】</a> </li>
						</c:if>
						<li><a data-pjax href="/view/home">欢迎你，${User.nickname }</a> </li>
					</c:if>
					
					<li class="divider-vertical"></li>
					<li class="dropdown"><a data-toggle="dropdown"
						class="dropdown-toggle" href="#">更多<strong class="caret"></strong></a>
						<ul class="dropdown-menu">
							<c:if test="${User==null }">
								<li><a data-pjax href="/view/login">登录</a></li>
								<li><a data-pjax href="/view/regist">注册</a></li>
							</c:if>
							<c:if test="${User!=null }">
								<li><a href="/article/write">新建文章</a></li>
								<li><a data-pjax href="/message">消息</a></li>
								<li class="divider"></li>
								<li><a href="/view/exit">注销</a></li>

							</c:if>
							<li><a data-pjax href="/view/findpassword">找回密码</a></li>
							<li class="divider"></li>
							<li><a data-pjax href="/view/about">关于</a></li>
							<!-- <li><a data-pjax href="/view/api">API服务</a></li>-->
							<li><a> <script type="text/javascript">/*var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1259967917'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1259967917' type='text/javascript'%3E%3C/script%3E"));*/</script></a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</div>
</div>