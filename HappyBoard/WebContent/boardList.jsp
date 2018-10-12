<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@	page import="kr.co.happy.*"%>
<% 
	ArrayList<BoardDTO> list = (ArrayList<BoardDTO>)request.getAttribute("list");
	String btype = request.getParameter("btype");
%>
<link rel="stylesheet" type="text/css" href="css/boardList.css">
<div class="container">
<% 
	if(list.size()!=0){
%>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성일자</th>
		</tr>
<%
	for(BoardDTO i : list) {
%>
		<tr>
			<td id="bid"><%=i.getSeq() %></td>
			<td id="btitle"><a href="boardDetail?bid=<%=i.getBid() %>"><%=i.getBtitle() %></a></td>
			<td id="bregdate"><%=i.getBregdate() %></td>
		</tr>
<%		
	}
%>
	</table>
<% 
	} else { 
%>
	<h2>게시글 없음</h2>
<% 
	} 
%>
</div>