<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

${GoForward.data}
<script>
onload = function(){ 
setTimeout(go, ${GoForward.time}); 
}; 
function go(){ 
	location.href='${GoForward.url}';
}
</script> 