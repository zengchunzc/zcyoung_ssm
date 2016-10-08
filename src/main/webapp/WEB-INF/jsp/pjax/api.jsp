<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>API服务-ZCYOUNG年轻人</title>
<div style="text-align:left;">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">api服务大厅</h3>
		</div>
		<div class="panel-body">
			<h4>亲，你可以选择如下的操作</h4>
			<br> 一：科技新闻api服务（需管理员权限）<br> 使用说明：<br>
			(1)先登录本网站，获取管理员cookie<br>
			(2)调用http://home.zcyoung.cn/api/news.do,利用get或者post传递参数<br>
			(3)请求格式为"size=5",5为个数,返回json串。 (4)返回json串。<br> <br>
			二：天气api服务(无需管理员权限)<br> 使用说明：<br>
			(1)调用http://home.zcyoung.cn/api/weather.do,利用get或者post传递参数,中文的城市名或者城市代码[<a
				href="/api/city.txt">代码下载</a>]<br> (2)请求参数为，"cityname=北京" 或
			"cityid=101010100"、
		</div>

	</div>
	<div style="text-align:center;">



		<button class="btn btn-large btn-info" type="button" id="goadmin" >返回管理</button>
		&nbsp;
		<button class="btn btn-large btn-info" type="button" id="gohome" >返回首页</button>
	</div>
</div>
<script type="text/javascript">
$(function(){
    $('#gohome').click(function(){
        $.pjax({
            url: '/view/home.do',
            container: '#pjax-container',
            timeout: 10000
        });
    });
    $('#goadmin').click(function(){
        $.pjax({
            url: '/view/admin.do',
            container: '#pjax-container',
            timeout: 10000
        });
    });
    
}); 
</script>