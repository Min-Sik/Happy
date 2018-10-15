<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.co.happy.*" %>
<%	String bid = (String)request.getParameter("bid");
	%>
<link rel="stylesheet" type="text/css" href="css/boardReg.css">
<div>
<% 
	if(bid.equals("0")) {
%>
	<form action="boardRegTo?bid=0&btype=${param.btype }" method="post">
		<input type="text" name="btitle" required><br><br>
		<textarea name="bcontent" required></textarea><br><br>
		<input type="password" name="pw" required><br><br>
		<input class="submit" type="submit" value="완료">
	</form> 
<% 
	} else { 
%>
	<form action="boardRegTo?bid=${param.bid }&btype=${param.btype }" method="post">
		<input type="text" name="btitle" value=${dto.getBtitle() } required><br><br>
		<pre><textarea name="bcontent" required>${dto.getBcontent() }</textarea></pre><br><br>
		<input class="submit" type="submit" value="완료">
	</form> 
<% 
	} 
%>
</div>