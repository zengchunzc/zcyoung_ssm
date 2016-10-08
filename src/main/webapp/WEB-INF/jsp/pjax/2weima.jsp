<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>在线二维码生成器-ZCYOUNG 年轻人</title>


<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">在线二维码生成器</h3>
	</div>
</div>
<div style="text-align:center;">
	请输入你要生成的文本:<br>
	<textarea rows="4" cols="100" id="txt"></textarea>
	<br /> 宽度:<input type="number" style="width:50px;" value="200"
		id="kuandu" />高度:<input type="number" style="width:50px;" value="200"
		id="gaodu" />
	<button id="create" class="btn btn-info">生成</button>
	<button id="down" class="btn btn-info" style="display: none;">下载</button>
	<br /> <br />
	<div style="text-align:center;" id="code"></div>
</div>
<script>
	$(function() {
		$('#create').click(function() {
			var txt = $("#txt").val();
			var w = $("#kuandu").val();
			var h = $("#gaodu").val();
			txt = toUtf8(txt);
			$("#code").html("");
			$("#code").qrcode({
				//render: "table", //table方式 
				width : w, //宽度 
				height : h, //高度 
				text : txt
			//任意内容 
			});
			$("#down").css("display", "inline");
		});
		$("#down").click(function() {
			try{
					var canvas = $($("#code").children()).get(0);
					// 图片导出为 png 格式
					var type = 'png';
					var imgData = canvas.toDataURL(type);
					/**
					 * 获取mimeType
					 * @param  {String} type the old mime-type
					 * @return the new mime-type
					 */
					var _fixType = function(type) {
						type = type.toLowerCase().replace(/jpg/i, 'jpeg');
						var r = type.match(/png|jpeg|bmp|gif/)[0];
						return 'image/' + r;
					};

					// 加工image data，替换mime type
					imgData = imgData.replace(_fixType(type),
							'image/octet-stream');
					/**
					 * 在本地进行文件保存
					 * @param  {String} data     要保存到本地的图片数据
					 * @param  {String} filename 文件名
					 */
					var saveFile = function(data, filename) {
						var save_link = document.createElementNS(
								'http://www.w3.org/1999/xhtml', 'a');
						save_link.href = data;
						save_link.download = filename;

						var event = document.createEvent('MouseEvents');
						event.initMouseEvent('click', true, false, window,
										0, 0, 0, 0, 0, false, false, false,
										false, 0, null);
						save_link.dispatchEvent(event);
					};

					// 下载后的问题名
					var filename = 'baidufe_' + (new Date()).getTime() + '.'
							+ type;
					// download
					saveFile(imgData, filename);
				}catch(e){alert(e);}
		});

	});

	function toUtf8(str) {
		var out, i, len, c;
		out = "";
		len = str.length;
		for (i = 0; i < len; i++) {
			c = str.charCodeAt(i);
			if ((c >= 0x0001) && (c <= 0x007F)) {
				out += str.charAt(i);
			} else if (c > 0x07FF) {
				out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
				out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
				out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
			} else {
				out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
				out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
			}
		}
		return out;
	}
</script>

