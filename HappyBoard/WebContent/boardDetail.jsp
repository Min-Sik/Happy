<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	page import="kr.co.happy.*" %>
<link rel="stylesheet" type="text/css" href="css/boardDetail.css">
<script>
	function regOrDel(str) {
		var a = ${param.bid };
		if(str=="reg") {
			frm1.action = "boardReg?bid=" + a;
		} else {
			frm1.action = 'boardDel?bid=' + a;
		}
	}
</script>
<% 
	String error = request.getParameter("error");
	if(error!=null) {
%> 
	<script>
		alert("비밀번호가 다릅니다.")
	</script>
<% 
	}
%>
<div class="container">
	<div class="title"> 
		${data.getBtitle() }
	</div>
	<div class="article">
		<pre>${data.getBcontent() }</pre>
	</div>
	<form name="frm1" method="post">
		<input type="password" name="pw" >
		<input type="submit" value="수정" onclick='regOrDel("reg")'>
		<input type="submit" value="삭제" onclick='regOrDel("del")'>
	</form>
</div>