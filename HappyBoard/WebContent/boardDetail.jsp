<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	page import="kr.co.happy.*" %>
<link rel="stylesheet" type="text/css" href="css/boardDetail.css">
<script>
	function regOrDel(str) {
		var a = ${param.bid };
		var b = ${param.btype };
		if(str=="reg") {
			frm1.action = "boardReg?bid=" + a + '&btype=' + b;
		} else {
			frm1.action = 'boardDel?bid=' + a + '&btype=' + b ;
		}
	}
</script>

<div class="container">
	<div class="title"> 
		${data.getBtitle() }
	</div>
	<div class="article">
		<pre>${data.getBcontent() }</pre>
	</div>
	<form name="frm1" method="post">
		비밀번호 : <input type="password" name="pw" >
		<input type="submit" value="수정" onclick='regOrDel("reg")'>
		<input type="submit" value="삭제" onclick='regOrDel("del")'>
	</form>
</div>