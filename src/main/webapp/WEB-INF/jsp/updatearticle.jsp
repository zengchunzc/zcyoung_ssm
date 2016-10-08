<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<title>修改文章-ZCYOUNG 年轻人</title>
<jsp:include page="head.jsp"></jsp:include>
<link rel="stylesheet" href="../js/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="../js/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="../js/kindeditor/kindeditor-all.js"></script>
	<script charset="utf-8" src="../js/kindeditor/lang/zh-CN.js"></script>
	<script charset="utf-8" src="../js/kindeditor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content1"]', {
				cssPath : '../js/kindeditor/plugins/code/prettify.css',
				uploadJson : '../js/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '../js/kindeditor/jsp/file_manager_json.jsp',
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
		/*
		KindEditor.ready(function (K) {
            window.editor = K.create('#AContent', {
                afterBlur: function () { this.sync(); }
            });
        });*/
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
						<h3 class="panel-title">修改我的文章</h3>
						<c:if test="${msg!=null }">
							<div class="alert alert-info">
								<button type="button" class="close" data-dismiss="alert">×</button>
								<strong>提示!</strong> ${msg }.
							</div>
						</c:if>
						<form>
							<div class="panel panel-default">
								<div class="panel-body">
									标题：<input type="text" id="title" name="title" value="${Article.title }">
								</div>
								<div class="panel-footer">
									是否公开（所有人可见）：是&nbsp;<input type="radio" id="gk" name="gk" value="是" <c:if test="${Article.readpower==2 }">checked="checked"</c:if>>&nbsp;
									否&nbsp;<input type="radio" id="gk" name="gk" value="否" <c:if test="${Article.readpower==1 }">checked="checked"</c:if>>
								</div>
								
								<textarea name="content1" id="MyArticle" cols="100" rows="80" style="width:100%;height:400px;visibility:hidden;">
									${Article.body }
								</textarea>
								<input type="hidden" name="id" id="Aid" value="${Article.id }">

							</div>
							<div id="Writing"></div><br>
							<button class="btn btn-large btn-info" type="button" onclick="UpdateArticle()">保存修改</button>

						</form>

					</div>

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