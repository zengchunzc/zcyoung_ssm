<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>写文章-ZCYOUNG 年轻人</title>
<jsp:include page="head.jsp"></jsp:include>
<link rel="stylesheet"
	href="/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="/js/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="/js/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="/js/kindeditor/lang/zh-CN.js"></script>
<script charset="utf-8" src="/js/kindeditor/plugins/code/prettify.js"></script>
<script>
	KindEditor.ready(function(K) {
		var editor1 = K.create('textarea[name="content1"]', {
			cssPath : '/js/kindeditor/plugins/code/prettify.css',
			uploadJson : '/file/upload_json',
			fileManagerJson : '/js/kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : true,
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
			}
		});
		prettyPrint();
	});
</script>

</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span1"></div>
			<div class="span10">
				<div class="page-header">
					<h1>
						ZCYOUNG<small>小伙的主页</small>
					</h1>
				</div>
				<c:import url="/view/bar.do"></c:import>
			</div>
			<div class="span1"></div>
		</div>
		<div class="row-fluid">

			<div class="span1"></div>
			<div class="span8" style="text-align:center;">

				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">欢迎发布一个新文章</h3>
						<form>
							<div class="panel panel-default">
								<div class="panel-body">
									标题：<input type="text" name="title" id="title">
								</div>
								<div class="panel-footer">
									是否公开（所有人可见）：是&nbsp;<input id="gk" type="radio" name="gk"
										value="是" checked="checked">&nbsp; 否&nbsp;<input
										id="gk" type="radio" name="gk" value="否">
								</div>

								<textarea name="content1" id="MyArticle" cols="100" rows="80"
									style="width:100%;height:400px;visibility:hidden;"></textarea>
							</div>
							<div id="msg"> </div>
							<button class="btn btn-large btn-info" type="button"
								onclick="WriteArticle()">发布文章</button>
						</form>
					</div>
				</div>
			</div>
			<div class="span2">
				<c:import url="/view/left"></c:import>
			</div>
			<div class="span1"></div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
<script>

function go(url){ 
	location.href = url;
}

function getmsg(mes){
	return "<div class='alert alert-info'><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><strong>提示!</strong> " + mes +"</div>";
}

function WriteArticle(){
	SyncEditor();
	
	var title = 	$("#title").val();
	var gk = 		GetRadioValue("gk");
	var MyArticle = $("#MyArticle").val();
	if(title.length == 0 || gk.length == 0 || MyArticle.length ==0){
		$("#msg").html(getmsg("提示：不能有为空项。"));
		return ;
	}
	$("#msg").html(getmsg("正在保存中..."));
	$.post("/article/add",
			{
		title:title,
		gk:gk,
		MyArticle:MyArticle
			},function(data,status){
				if(status == "success"){
					if(data.indexOf("ok")!=-1) {
						$("#msg").html(getmsg("发布成功，3s后跳转"));
						setTimeout(go("/article/my"), 3000);
					}
					else $("#msg").html(getmsg("发布失败。" + data.substring(6)));
				}
			});
}

</script>
</body>
</html>



