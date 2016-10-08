<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	// 销毁session
	session.invalidate();
	// 跳回主页
	response.sendRedirect(request.getContextPath()+"/view/index.do");
%>
