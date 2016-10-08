<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
out.print("正在跳转中。。。");
String url=(String)request.getAttribute("url");
response.sendRedirect(url);

%>
