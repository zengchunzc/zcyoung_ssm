<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 
<title>${User.nickname }的空间-ZCYOUNG 年轻人</title>
<div style="text-align:center;">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				欢迎来到${User.nickname }的空间<br> <img alt=""
					<c:if test="${User.pic==null||User.pic=='' }"> src="/upload/nopic.jpg"</c:if>
					<c:if test="${User.pic!=null&&User.pic!='' }">src="${User.pic }" </c:if>
					width="10%" height="">
			</h3>
		</div>
		<div class="panel-body">用户账号：${User.username  }</div>
		<div class="panel-footer">用户昵称：${User.nickname  }</div>
		<div class="panel-body">个性签名：${User.signature }</div>
		<div class="panel-footer">
			用户邮箱：${User.email  }
			<div id="MailYz">
				<c:if test="${User.emailYz==1 }">[已验证]</c:if>
				<c:if test="${User.emailYz==0 }">(邮箱还未验证，会影响您的体验与安全。)<a
						href="javascript:void(0);" onclick="SendMailYz();">[点击验证]</a>
				</c:if>
			</div>
		</div>
		<div class="panel-body">用户等级：${User.role  }</div>
		<div class="panel-footer">注册时间：<fmt:formatDate value="${User.rTime  }" type="both" /></div>
	</div>
	<button class="btn btn-large btn-info" type="button" id="Rmodify">修改个人信息</button>
	&nbsp;
	<button class="btn btn-large btn-info" type="button" id="Rsafe">账户安全设置</button>
	&nbsp;
	<button class="btn btn-large btn-info" type="button" id="Rmyfriend">查看我的好友</button>
	<br> <br>
	<button class="btn btn-large btn-info" type="button" id="Rmyarticle">查看我的文章</button>
	&nbsp;
	<button class="btn btn-large btn-info" type="button" id="Rmyfile">查看我的附件</button>
	&nbsp;
	<c:if test="${User.role=='admin' }"> 
	<button class="btn btn-large btn-info" type="button" id="Radmin">管理员的界面</button>
	</c:if>
</div>
<script type="text/javascript">

$(function(){
    $('#Rmyfriend').click(function(){
        $.pjax({
            url: '/view/myfriend',
            container: '#pjax-container',
            timeout: 10000
        });
    });
    $('#Rmyarticle').click(function(){
    	$.pjax({
            url: '/article/my',
            container: '#pjax-container',
            timeout: 10000
        });
    });
    $('#Rmyfile').click(function(){
    	$.pjax({
            url: '/file/my',
            container: '#pjax-container',
            timeout: 10000
        });
    });
    $('#Radmin').click(function(){
    	$.pjax({
            url: '/view/admin',
            container: '#pjax-container',
            timeout: 10000
        });
    });
    $('#Rsafe').click(function(){
    	$.pjax({
            url: '/view/safe',
            container: '#pjax-container',
            timeout: 10000
        });
    });
    $('#Rmodify').click(function(){
    	$.pjax({
            url: '/view/modify',
            container: '#pjax-container',
            timeout: 10000
        });
    });
});
</script>
