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
	<form action="boardRegTo" method="post">
		제목 : <br><input type="text" name="btitle" required><br>
		내용 : <br><textarea name="bcontent" required></textarea><br>
		비밀번호 : <br><input type="password" name="pw" required><br><br>
		<input class="submit" type="submit" value="완료">
		<input type="hidden" name="bid" value=${param.bid } >
		<input type="hidden" name="btype" value=${param.btype } >
	</form> 
<% 
	} else { 
%>
	<form action="boardRegTo?bid=${param.bid }&btype=${param.btype }" method="post"><br>
		제목 : <br><input type="text" name="btitle" value=${dto.getBtitle() } required><br>
		내용 : <br><pre><textarea name="bcontent" required>${dto.getBcontent() }</textarea></pre><br><br>
		<input class="submit" type="submit" value="완료">
		<input type="hidden" name="bid" value=${param.bid } >
		<input type="hidden" name="btype" value=${param.btype } >
	</form> 
<% 
	} 
%>
</div>