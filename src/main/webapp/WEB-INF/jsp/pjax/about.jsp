<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>关于-ZCYOUNG 年轻人</title>
<div style="text-align:center;">

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">关于小伙的主页</h3>
			<h4>联系我们：zc@zcyoung.cn</h4>
			<h5>
				Copyright ©2016 <span>zcyoung.cn版权所有</span> All Rights Reserved.
			</h5>
		</div>

	</div>
	<c:if test="${msg!=null }">
		<div class="alert alert-info">
			<button type="button" class="close" data-dismiss="alert">×</button>
			<strong>提示!</strong> ${msg }.
		</div>
	</c:if>

</div>
