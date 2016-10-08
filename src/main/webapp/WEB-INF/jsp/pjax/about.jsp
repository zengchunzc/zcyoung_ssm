<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>关于-ZCYOUNG 年轻人</title>


<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">关于</h3>
	</div>
</div>


		<div class="panel-heading" style="text-align:center;">
			<embed height="415" width="544" quality="high" allowfullscreen="true" type="application/x-shockwave-flash" src="http://static.hdslb.com/miniloader.swf" flashvars="aid=3081194&amp;page=1" pluginspage="http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash"/>
			<h4>联系我们：zc@zcyoung.cn</h4>
			<h5>
				Copyright ©2016 <span>zcyoung.cn版权所有</span> All Rights Reserved.
			</h5>
		</div>

	
	<c:if test="${msg!=null }">
		<div class="alert alert-info">
			<button type="button" class="close" data-dismiss="alert">×</button>
			<strong>提示!</strong> ${msg }.
		</div>
	</c:if>


