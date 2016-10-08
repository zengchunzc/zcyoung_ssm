<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>管理-ZCYOUNG年轻人</title>
<div style="text-align:center;">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">这里是管理员界面</h3>
		</div>
	</div>
	<c:if test="${msg!=null }">
		<div class="alert alert-info">
			<button type="button" class="close" data-dismiss="alert">×</button>
			<strong>提示!</strong> ${msg }.
		</div>
	</c:if>
	<button class="btn btn-large btn-info" type="button" id="af" >管理友链</button>
	&nbsp;
	<button class="btn btn-large btn-info" type="button" id="au" >管理会员</button>
	<br>
	<br>
	<button class="btn btn-large btn-info" type="button" id="gohome">返回个人中心</button>
</div>
<script type="text/javascript">

$(function(){
    $('#af').click(function(){
        $.pjax({
            url: '/admin/friendurl',
            container: '#pjax-container',
            timeout: 10000
        });
    });
    $('#gohome').click(function(){
        $.pjax({
            url: '/view/home',
            container: '#pjax-container',
            timeout: 10000
        });
    });
    $('#au').click(function(){
        $.pjax({
            url: '/admin/user',
            container: '#pjax-container',
            timeout: 10000
        });
    });
    $('#api').click(function(){
        $.pjax({
            url: '/view/api',
            container: '#pjax-container',
            timeout: 10000
        });
    });
}); 
</script>
