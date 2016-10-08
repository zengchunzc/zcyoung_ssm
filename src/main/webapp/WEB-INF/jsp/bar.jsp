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
			</a> <a data-pjax href="/view/index.do" class="brand">主页</a> <a
				class="brand" data-pjax href="/view/home.do">个人中心</a> <a
				class="brand" data-pjax href="/view/about.do">关于</a>

			<div class="nav-collapse collapse navbar-responsive-collapse">
				<ul class="nav">

				</ul>
				<ul class="nav pull-right">
					<li>
					<c:if test="${User==null }">
						<a data-pjax href="/view/login.do">游客你好，请先登录</a>
					</c:if>
					<c:if test="${User!=null }">
						<c:if test="${messagecount!=null&&messagecount>0 }">
							<li><a data-pjax href="/view/message.do">【你有${messagecount }条新的消息！！】</a> </li>
						</c:if>
						<li><a data-pjax href="/view/home.do">欢迎你，${User.nickname }</a> </li>
					</c:if>
					
					<li class="divider-vertical"></li>
					<li class="dropdown"><a data-toggle="dropdown"
						class="dropdown-toggle" href="#">更多<strong class="caret"></strong></a>
						<ul class="dropdown-menu">
							<c:if test="${User==null }">
								<li><a data-pjax href="/view/login.do">登录</a></li>
								<li><a data-pjax href="/view/regist.do">注册</a></li>
							</c:if>
							<c:if test="${User!=null }">
								<li><a href="/view/writearticle.do">新建文章</a></li>
								<li><a data-pjax href="/view/message.do">消息</a></li>
								<li class="divider"></li>
								<li><a href="/view/exit.do">注销</a></li>

							</c:if>
							<li><a data-pjax href="/view/findpassword.do">找回密码</a></li>
							<li class="divider"></li>
							<li><a data-pjax href="/view/about.do">联系我们</a></li>
							<li><a data-pjax href="/view/api.do">API服务</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</div>
</div>