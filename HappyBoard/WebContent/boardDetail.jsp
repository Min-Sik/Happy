<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	page import="kr.co.happy.*" %>
<%
	BoardDTO dto = (BoardDTO)request.getAttribute("data");
%>
<link rel="stylesheet" type="text/css" href="css/boardDetail.css">
<div class="container">
	<div class="title"> 
		<%=dto.getBtitle() %>
	</div>
	<div class="article">
		<pre><%=dto.getBcontent() %></pre>
	</div>
</div>
	