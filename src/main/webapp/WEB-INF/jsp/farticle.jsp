<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<jsp:include page="head.jsp"></jsp:include>
<title>${Friend.nickname }的文章-ZCYOUNG 年轻人</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="page1.jsp"></jsp:include>
		<div class="row-fluid">

			<div class="span1"></div>
			<div class="span8">
				<c:if test="${msg!=null }">
					<div class="alert alert-info">
						<button type="button" class="close" data-dismiss="alert">×</button>
						<strong>提示!</strong> ${msg }.
					</div>
				</c:if>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">${Friend.nickname }的文章</h3>
					</div>
				</div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>序号</th>
							<th>标题</th>
							<th>发布时间</th>
							<th>上次编辑</th>
							<th>点击量</th>
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
									<td><a href="/view/article.do?id=${p.id }">${p.title }</a></td>
									<td>${p.w_time }</td>
									<td>${p.u_time }</td>
									<td>${p.click }</td>
									
								</tr>
							</c:forEach>

						</c:if>
					</tbody>
				</table>
				<div class="pagination pagination-centered">
					<ul>
						<li><a
							href="${pageContext.request.contextPath}/view/farticle.do?page=1&id=${Friend.id }">&lt;&lt;</a>
						</li>
						<li><a
							href="${pageContext.request.contextPath}/view/farticle.do?page=${Page.pageIndex-1}&id=${Friend.id }">&lt;</a>
						</li>
						<c:forEach var="item" varStatus="vs" begin="1" end="5">

							<c:if test="${Page.pageIndex<=3 }">
								<li><a
									href="${pageContext.request.contextPath}/view/farticle.do?page=${vs.count}&id=${Friend.id }">
										<c:if test="${vs.count==Page.pageIndex }">
											<strong><b><u>${vs.count}</u></b></strong>
										</c:if> <c:if test="${vs.count!=Page.pageIndex }">
  								${vs.count}
  							</c:if>

								</a></li>
							</c:if>

							<c:if
								test="${Page.pageIndex>3 && Page.pageIndex<Page.totalPages-3}">
								<li><a
									href="${pageContext.request.contextPath}/view/farticle.do?page=${vs.count+Page.pageIndex-3}&id=${Friend.id }">


										<c:if test="${vs.count+Page.pageIndex-3==Page.pageIndex }">
											<strong><b><u>${vs.count+Page.pageIndex-3}</u></b></strong>
										</c:if> <c:if test="${vs.count+Page.pageIndex-3!=Page.pageIndex }">
  								${vs.count+Page.pageIndex-3}
  							</c:if>

								</a></li>
							</c:if>

							<c:if
								test="${Page.pageIndex>3 && Page.pageIndex>=Page.totalPages-3}">
								<li><a
									href="${pageContext.request.contextPath}/view/farticle.do?page=${Page.totalPages-5+vs.count}&id=${Friend.id }">

										<c:if test="${Page.totalPages-5+vs.count==Page.pageIndex }">
											<strong><b><u>${Page.totalPages-5+vs.count}</u></b></strong>
										</c:if> <c:if test="${Page.totalPages-5+vs.count!=Page.pageIndex }">
  								${Page.totalPages-5+vs.count}
  							</c:if>

								</a></li>
							</c:if>


						</c:forEach>


						<li><a
							href="${pageContext.request.contextPath}/view/farticle.do?page=${Page.pageIndex+1}&id=${Friend.id } ">&gt;</a>
						</li>
						<li><a
							href="${pageContext.request.contextPath}/view/farticle.do?page=${Page.totalPages }&id=${Friend.id }">&gt;&gt;</a>
						</li>
					</ul>
					<br> <br>
					<button class="btn btn-large btn-info" type="button"
						onclick="location='../view/space.do?id=${Friend.id}'">返回好友空间</button>

				</div>
			</div>
			<div class="span2">
				<c:import url="/view/left.do"></c:import>
			</div>
			<div class="span1"></div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>