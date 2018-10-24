<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@	page import="kr.co.happy.*"%>
<% 
	ArrayList<BoardDTO> list = (ArrayList<BoardDTO>)request.getAttribute("list");
	String btype = request.getParameter("btype");
	int pageMax = (int)request.getAttribute("pageMax");
	String pageNum = request.getParameter("page");
	int intPageNum = Integer.parseInt(pageNum);

	
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
			<td id="btitle"><a href="boardDetail?bid=<%=i.getBid() %>&btype=<%=btype %>"><%=i.getBtitle() %></a></td>
			<td id="bregdate"><%=i.getBregdate() %></td>
		</tr>
<%		
		}
%>
	</table>
	<div class="pageList">
		<input type="button" value="이전">
		<%=intPageNum %>
		<input type="button" value="다음" onclick="nextPage">
	</div>
<%
	} else {
%>
	<h2>게시글 없음</h2>
<% 
	} 
%>
	<button onclick="location.href='boardReg?bid=0&btype=${param.btype }'">글쓰기</button>
</div>