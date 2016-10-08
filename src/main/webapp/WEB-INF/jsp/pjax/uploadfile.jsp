<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>上传文件-ZCYOUNG年轻人</title>
<div style="text-align:center;">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">上传文件</h3>
		</div>
	</div>
	<div class="panel panel-default">
		<br>
		<c:if test="${msg!=null }">
			<div class="alert alert-info">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<strong>提示!</strong> ${msg }.
			</div>
		</c:if>
		<form action="/file/upload/now" method="post"
			enctype="multipart/form-data">
			请选择文件(50m以内)：<input type="file" name="uf"> &nbsp;
			<p>
				<input type="checkbox" name="pri" value="ok" checked="checked" />是否私有（仅自己下载）
			</p>
			<input type="submit" value="上传">
		</form>
	</div>
	<br> <br>
	<button class="btn btn-large btn-info" type="button" id="adminf" >返回附件管理</button>
	&nbsp;&nbsp;
	<button class="btn btn-large btn-info" type="button" id="gohome" >返回个人中心</button>
</div>
<script type="text/javascript">
$(function(){
    $('#gohome').click(function(){
        $.pjax({
            url: '/view/home',
            container: '#pjax-container',
            timeout: 10000
        });
    });
    $('#adminf').click(function(){
        $.pjax({
            url: '/file/my',
            container: '#pjax-container',
            timeout: 10000
        });
    });
    
}); 
</script>

