<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	<script type="text/javascript">
		$(document).pjax('[data-pjax] a, a[data-pjax]', '#pjax-container', {
			timeout : 18000
		});
		$(document).on('pjax:send', function() {
			$("#waitBar").css("display", "block");
		});
		$(document).on('pjax:complete', function() {
			$.post("/view/bar.do",{},
				function(data,status){
					if(status == "success"){
						$("#bar").html(data);
					}
				}
			);
			$("#waitBar").css("display", "none");
		});
	</script>